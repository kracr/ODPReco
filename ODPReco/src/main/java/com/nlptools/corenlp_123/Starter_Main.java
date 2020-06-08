package com.nlptools.corenlp_123;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.common.collect.Lists;

//Contributed by Maleeha
public class Starter_Main {
	
	
	
	
@SuppressWarnings("unchecked")
public static void main(String abc[]) throws Exception{

/*-----------------------------ODPReco-TOOL FOR ODP RECOMMENDATIONS---------------------------------
 * ODPReco is a tool that is used for recommending ODPs to a particular ontology. The Ontology is given
 * by the user along with its competency Questions and description. The input data is divided for
 * structural, behavioural and lexical analysis.After analysing against these three parameters,
 * the set of ODPs are recommended.
 * -----------------------------------INPUT ONTOLOGY-----------------------------------------------
 */
		
	 Scanner scan=new Scanner(System.in); //for taking the input from user
	//Asking the user for the description	
	System.out.println("Enter Description" );
	String s1_des=scan.nextLine();
	//Asking the user for the competency questions
	System.out.println("Enter Competency Questions");
	String s2_cq=scan.nextLine();	
	//Asking the user for owl file
	System.out.println("Enter OWL file path with.owl extension");
	String s3_owl=scan.nextLine();
	if(s3_owl.isEmpty())
	{
   System.out.println("Please specify the OWL file otherwise it won't be able to recommend the ODPs");
   s3_owl=scan.nextLine();
	}
	
	//This java file has code for OWL file and classifying different properties
	
	 Owl_Lexical owl_ontology=new Owl_Lexical();
     owl_ontology.owlOntology(s3_owl);
	
     //asking the user to wait for few seconds 
	     PrintStream wait=new PrintStream(new FileOutputStream(FileDescriptor.out)); 
	 	 System.setOut(wait); 
	 	 System.out.println("Ontology has been stored");
	 	 System.out.println("Recommendations will follow in some seconds...");
	 	 
	 	//Saving the description of the ontology in a file
	 	BufferedWriter writer_des=new BufferedWriter(new FileWriter(System.getProperty("user.dir")+ "/resources/ontology_description"));
	 	writer_des.write(s1_des);
	 	writer_des.close();
	 	 
	 	//Saving the competency questions of the ontology in a file
			BufferedWriter writer_cq=new BufferedWriter(new FileWriter(System.getProperty("user.dir")+ "/resources/ontology_cq"));
			writer_cq.write(s2_cq);
			writer_cq.close();
			
			
		//Stop Words from the description are removed
		StopWords_Ontology stop_des=new StopWords_Ontology();
		stop_des.stopWordsDesOntology();
		
		//Stop words from competency questions are removed
		StopWords_CqOntology stop_cq=new StopWords_CqOntology();
		stop_cq.stopWordsCQOntology();
		
			
	 	 
/*--------------------------------EXECUTION PART------------------------------------------------
 <-------------------------------STRUCTURAL ANALYSIS---------------------------------------------->	
 Properties obtained from the OWL file are analysed. Doc2Vec is used and cosine similarity 
 gives the numeric score of the ontology against each ODP.The below functions call the respective
 class that holds the code for each Structural Property that we have taken under consideration 
 for the analysis. */
	
 /*To increase the efficiency, threads are used so that some tasks run parallelly */	
	
		
		// 1. DISJOINT CLASSES
		final ParagraphVectorsDisjointClasses dc=new ParagraphVectorsDisjointClasses();
	
		// 2. OBJECT DOMAIN PROPERTY
		final ParagraphVectorsObjectDomainProperty od=new ParagraphVectorsObjectDomainProperty();

		// 3. OBJECT RANGE PROPERTY
		ParagraphVectorsObjectPropertyRange op=new ParagraphVectorsObjectPropertyRange();
		 op.objectRangeProperty();
		// 4. SUB-CLASS PROPERTY
		final ParagraphVectorsSubClassProperty sc=new ParagraphVectorsSubClassProperty();
		
		// 5. SUB-OBJECT PROPERTY
		final ParagraphVectorsSubObjectProperty so=new ParagraphVectorsSubObjectProperty();
	
			
		//Parrallelization -I	
		 final ExecutorService executorService = Executors.newFixedThreadPool(4);

		    final ArrayList<Callable<String>> tasks = Lists.newArrayList(
		    		 new Callable<String>()
		          {
		                public String call() throws Exception
		                {
		                	synchronized(executorService){ 
		                		

				        		return sc.subClassProperty();
		                	
		                	}               
		               }
		            },
		    		
			        new Callable<String>()
		             {
		                 public String call() throws Exception
		                 {
		                	synchronized(executorService){ 
			         
		                	 return dc.disjointClasses();  
		                }
		              }
		             },
		             new Callable<String>()
		             {
		                 public String call() throws Exception
		                 {
		                	synchronized(executorService){ 
			         
		                	 return  so.subObjectProperty(); 
		                }
		              }
		             },
		             
		             new Callable<String>()
		             {
		                 public String call() throws Exception
		                 {
		                	synchronized(executorService){ 
			         
		                	 return od.objectDomainProperty(); 
		                }
		              }
		             }
		             
		     );

		     executorService.invokeAll(tasks); 
		     executorService.shutdown();
		    		
		 		     			        			    
	
	
		     
/*<-----------------------------------BEHAVIOURAL ANALYSIS-------------------------------------------->
  The Competency Question of the ontology are saved and mappped with the competency questions 
  of the ODPs in the list. The ODPs obtained manchester site do not have competency questions, but 
  the rest all ontologies have.So, approximately 15 among the 73 ODPs present are without CQs	
	*/
	//if the user has not entered cq's, then doc2vec code will be bypassed
			   
	if(s2_cq.isEmpty()){
	 FileWriter fw_empty=new FileWriter(System.getProperty("user.dir")+ "/resources/ontology_cq");
		for(int i=1;i<74;i++){
			fw_empty.write("0");
			fw_empty.write("\n");
				   }
				   fw_empty.close();
			   }
			   else{
	
	ParagraphVectors_CQ cq=new ParagraphVectors_CQ();
	cq.cqMapping();
			   }
/*<------------------------------------LEXICAL ANALYSIS------------------------------------------------>
  Lexical Analysis involves the analysis by use of the description and by the names of classes
  properties present in the OWL file. The description along with the description(classes and properties) 
  of the OWL file of the ontology are compared against the ODPs. All the ODPs present have description
 *along with the classes and properties.
 */
    // 1. DESCRIPTION
	  //if description is not entered by the user, then the doc2vec code is bypassed
	if(s1_des.isEmpty()){
		 FileWriter des_empty=new FileWriter(System.getProperty("user.dir")+ "/resources/ontology_description");
			for(int x=1;x<74;x++){
				des_empty.write("0");
				des_empty.write("\n");
					   }
					   des_empty.close();
				   }
	else{
			
	ParagraphVectorsTextExample te=new ParagraphVectorsTextExample();
	te.description();
	}
	// 2. SIGNATURE
	ParagraphVectors_Signature vs=new ParagraphVectors_Signature();
	vs.signature();
/*<--------------------------------------INTEGRATION OF SCORES----------------------------------------->
 * After obtaning the numeric values of lexical, structural and behavioural parts, the cosine 
 * similarity scores are combined together of the ontology and the ODPs and placed in a single
 * file.
 */
	Integration_Of_Scores os=new Integration_Of_Scores();
	os.integratingScores();
/*<----------------------------------------NORMALISING SCORES------------------------------------------->
 * After all the scores of an ODP (against the given ontology) are added up, the scores are normalised
 *  that these range between 0 to 1 and we can set a particular threshold for the ODP recommendations
 */
	Normalising_Scores ns=new Normalising_Scores();
	ns.normalisingScores();
/*<------------------------------------------ODP RECOMMENDER-------------------------------------------->
 * 	After we have normalised the scores against the ODPs, we run the this java program from where the
 * recommendations can be suggested. 
 */
   ODPRecommender reco=new ODPRecommender();
    reco.odpRecommender();
	
}
}
