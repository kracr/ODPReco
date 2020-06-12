package com.reco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.google.common.collect.Lists;

//Contributed By Maleeha
public class StartODPReco {

	@SuppressWarnings("unchecked")
	public static void main(String abc[]) throws Exception{

	/*-----------------------------ODPReco-TOOL FOR ODP RECOMMENDATIONS---------------------------------
	 * ODPReco is a tool that is used for recommending ODPs to a particular ontology. The Ontology is given
	 * by the user along with its competency Questions and description. The input data is divided for
	 * structural, behavioural and lexical analysis.After analysing against these three parameters,
	 * the set of ODPs are recommended.
	 * -----------------------------------INPUT ONTOLOGY-----------------------------------------------
	 */
		
		//Taking command line arguments after the jar file has been created.
		Options options = new Options();

	    Option description = new Option("ontdes", true, "description of ontology");
	    description .setRequired(false);
	    options.addOption(description);
	   

	    Option cq = new Option("ontcq", true, "cq of ontology");
	    cq.setRequired(false);
	    options.addOption(cq);
	   

	    Option owlfile = new Option("ontowl", true, "owl file");
	    owlfile.setRequired(true);
	    options.addOption(owlfile);

	    CommandLineParser parser = new DefaultParser();
	    HelpFormatter formatter = new HelpFormatter();
	    CommandLine cmd;

	    try {
	        cmd = parser.parse(options, abc);
	    } catch (ParseException e) {
	        System.out.println(e.getMessage());
	        formatter.printHelp("ODPReco", options);

	        return;
	    }
	    
 //<--------------------------------------Obtaining the input values-------------------------------------->
	    String input_description = cmd.getOptionValue("ontdes");
	    input_description=input_description.replaceAll("\\[", "").replaceAll("\\]","");
	    String input_cq = cmd.getOptionValue("ontcq");
	    input_cq=input_cq.replaceAll("\\[", "").replaceAll("\\]","");
	    String input_owlfile = cmd.getOptionValue("ontowl");
	    input_owlfile=input_owlfile.replaceAll("\\[", "").replaceAll("\\]","");

	    
//<-------------------------------------Reading Ontology from the OWL file---------------------------------->
	    
	  //This java file has code for OWL file and classifying different properties
		
		 Owl_Lexical owl_ontology=new Owl_Lexical();
	     owl_ontology.owlOntology(input_owlfile);
		
	     //asking the user to wait for few seconds 
		     PrintStream wait=new PrintStream(new FileOutputStream(FileDescriptor.out)); 
		 	 System.setOut(wait); 
		 	 System.out.println("Ontology has been stored");
		 	 System.out.println("Recommendations will follow in some seconds...");
	       
	    
	    
/*<------------------------------------------Lexical Analysis------------------------------------------------>
  Lexical Analysis involves the analysis by use of the description and by the names of classes
	  properties present in the OWL file. The description along with the description(classes and properties) 
	  of the OWL file of the ontology are compared against the ODPs. All the ODPs present have description
	  along with the classes and properties.
 
	     1. DESCRIPTION- 
		    if description is not entered by the user, then the doc2vec code is bypassed
  */
    
	    //reading the description file
       if(input_description.isEmpty()){
    	   FileWriter des_empty=new FileWriter(System.getProperty("user.dir")+ "/resources/ontology_description");
			for(int x=1;x<74;x++){
				des_empty.write("0");
				des_empty.write("\n");
					   }
					   des_empty.close();
				   }

              
        else{
	    //Reading the description file of ontology
         File f_des=new File(input_description);
         BufferedReader br_des = new BufferedReader(new FileReader(f_des));          
         PrintWriter pw_des = new PrintWriter(System.getProperty("user.dir")+ "/resources/ontology_description");
			
         String s_des=br_des.readLine();
              if(s_des==null){ //if it is empty
            	  FileWriter des_empty=new FileWriter(System.getProperty("user.dir")+ "/resources/ontology_description");
  				for(int x=1;x<74;x++){
  					des_empty.write("0");
  					des_empty.write("\n");
  						   }
  						   des_empty.close();
  					   }
              
              else{
			while (s_des != null) { // read a line
				
				pw_des.print(s_des);
				pw_des.print(" ");
				s_des=br_des.readLine();
			}
				
			pw_des.flush();		
			br_des.close();
			pw_des.close();
			
			//Stop Words from the description are removed
			StopWords_Ontology stop_des=new StopWords_Ontology();
			stop_des.stopWordsDesOntology();
			//Using Doc2Vec for finding similarity
			ParagraphVectorsTextExample te=new ParagraphVectorsTextExample(); 
			te.description();
                    
         }  //else of description file ends
        }   //description first else
            
         //SIGNATURE
   	        ParagraphVectors_Signature vs=new ParagraphVectors_Signature();
	        vs.signature();
       
       
		     
/*<-----------------------------------------BEHAVIOURAL ANALYSIS-------------------------------------------->
	The Competency Question of the ontology are saved and mappped with the competency questions 
	of the ODPs in the list. The ODPs obtained manchester site do not have competency questions, but 
	the rest all ontologies have.So, approximately 15 among the 73 ODPs present are without CQs	
		*/
		//if the user has not entered cq's, then doc2vec code will be bypassed
	        
              //Reading from the competency questions
               if(input_cq.isEmpty()){
            	   FileWriter fw_empty=new FileWriter(System.getProperty("user.dir")+ "/resources/ontology_cq");
       			for(int i=1;i<74;i++){
       				fw_empty.write("0");
       				fw_empty.write("\n");
       					   }
       					   fw_empty.close();
       				   }

            else{
              File f_cq=new File(input_cq);
              BufferedReader br_cq = new BufferedReader(new FileReader(f_cq));          
              PrintWriter pw_cq = new PrintWriter(System.getProperty("user.dir")+ "/resources/ontology_cq");
     			
              String s_cq=br_cq.readLine();
                   if(s_cq==null){
                	   FileWriter fw_empty=new FileWriter(System.getProperty("user.dir")+ "/resources/ontology_cq");
           			for(int i=1;i<74;i++){
           				fw_empty.write("0");
           				fw_empty.write("\n");
           					   }
           					   fw_empty.close();
           				   
                   }
                   else{
     			while (s_cq != null) { // read a line
     				
     				pw_cq.print(s_cq);
     				pw_cq.print(" ");
     				s_cq=br_cq.readLine();
     			}
     				
     			pw_cq.flush();		  
     			br_cq.close();
     			pw_cq.close();
                         
     			//Stop words from competency questions are removed
    			StopWords_CqOntology stop_cq=new StopWords_CqOntology();
    			stop_cq.stopWordsCQOntology();
    		//Passing DOC2vec for finding similarity between ontology and the ODPs
    			ParagraphVectors_CQ cq_sim=new ParagraphVectors_CQ();
    			cq_sim.cqMapping();
    			
              }  //else of description ends
}// cq first else
						
		 	 
               

  	    
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
			    		
			 		     			        			    
	
	/*<--------------------------------------INTEGRATION OF SCORES----------------------------------------->
	 * After obtaining the numeric values of lexical, structural and behavioural parts, the cosine 
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


