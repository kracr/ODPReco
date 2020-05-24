package com.nlptools.corenlp_123;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

//Contributed by Maleeha
public class Starter_Main {
public static void main(String abc[]) throws Exception{

/*-----------------------------ODPReco-TOOL FOR ODP RECOMMENDATIONS---------------------------------
 * ODPReco is a tool that is used for recommending ODPs to a particular ontology. The Ontology is given
 * by the user along with its competency Questions and description. The input data is divided for
 * structural, behavioural and lexical analysis.After analysing against these three parameters,
 * the set of ODPs are recommended.
 * -----------------------------------INPUT ONTOLOGY-----------------------------------------------
 */
	
	
	//Scanner scan=new Scanner(System.in); //for taking the input from user
	//Asking the user for the description
	System.out.println("<description>" + abc[0] );
	String s1_des;
	s1_des=abc[0];
	//Asking the user for the competency questions
	System.out.println("<competency questions>" + abc[1]);
	String s2_cq;
	s2_cq=abc[1];	
	//This java file has code for OWL file and classifying different properties
	
	//Scanner scan123=new Scanner(System.in);
	System.out.println("<owl file with .owl extension>" +abc[2]);
	String s3_owl=abc[2];
	if(s3_owl.isEmpty())
	{
		System.out.println("Please specify the OWL file otherwise it won't be able to recommend the score");
		s3_owl=abc[2];
	}
	
	Owl_Lexical owl_ontology=new Owl_Lexical();
	owl_ontology.owlOntology(s3_owl);
	//Saving the description of the ontology in a file
	BufferedWriter writer_des=new BufferedWriter(new FileWriter(System.getProperty("user.dir")+ "/resources/ontology_description"));
	writer_des.write(s1_des);
	writer_des.close();
	//Saving the competency questions of the ontology in a file
	BufferedWriter writer_cq=new BufferedWriter(new FileWriter(System.getProperty("user.dir")+ "/resources/ontology_cq"));
	writer_cq.write(s2_cq);
	writer_cq.close();
	//Stop words from competency questions are removed
	StopWords_CqOntology stop_cq=new StopWords_CqOntology();
	stop_cq.stopWordsCQOntology();
	//Stop Words from the description are removed
	StopWords_Ontology stop_des=new StopWords_Ontology();
	stop_des.stopWordsDesOntology();	
	
/*--------------------------------EXECUTION PART------------------------------------------------
 <-------------------------------STRUCTURAL ANALYSIS---------------------------------------------->	
 Properties obtained from the OWL file are analysed. Doc2Vec is used and cosine similarity 
 gives the numeric score of the ontology against each ODP.The below functions call the respective
 class that holds the code for each Structural Property that we have taken under consideration 
 for the analysis. */
	// 1. CHAIN OF PROPERTY
	ParagraphVectorsChainOfProperty cp=new ParagraphVectorsChainOfProperty();
	cp.chainOf();
	// 2. DATA DOMAIN Property
	ParagraphVectorsDataPropertyDomain pd=new ParagraphVectorsDataPropertyDomain();
	pd.dataPropertyDomain();
	// 3. DATA RANGE PROPERTY
	ParagraphVectorsDataPropertyRange pr=new ParagraphVectorsDataPropertyRange();
	pr.dataPropertyRange();
	// 4. DISJOINT CLASSES
	ParagraphVectorsDisjointClasses dc=new ParagraphVectorsDisjointClasses();
	dc.disjointClasses();
	// 5. OBJECT DOMAIN PROPERTY
	ParagraphVectorsObjectDomainProperty od=new ParagraphVectorsObjectDomainProperty();
	od.objectDomainProperty();
	// 6. OBJECT RANGE PROPERTY
	ParagraphVectorsObjectPropertyRange op=new ParagraphVectorsObjectPropertyRange();
	op.objectRangeProperty();
	// 7. SUB-CLASS PROPERTY
	ParagraphVectorsSubClassProperty sc=new ParagraphVectorsSubClassProperty();
	sc.subClassProperty();
	// 8. SUB-DATA PROPERTY
	ParagraphVectorsSubDataProperty sd=new ParagraphVectorsSubDataProperty();
	sd.subDataProperty();
	// 9. SUB-OBJECT PROPERTY
	ParagraphVectorsSubObjectProperty so=new ParagraphVectorsSubObjectProperty();
	so.subObjectProperty();
	
/*<-----------------------------------BEHAVIOURAL ANALYSIS-------------------------------------------->
  The Competency Question of the ontology are saved and mappped with the competency questions 
  of the ODPs in the list. The ODPs obtained manchester site do not have competency questions, but 
  the rest all ontologies have.So, approximately 15 among the 73 ODPs present are without CQs	
	*/
	ParagraphVectors_CQ cq=new ParagraphVectors_CQ();
	cq.cqMapping();
/*<------------------------------------LEXICAL ANALYSIS------------------------------------------------>
  Lexical Analysis involves the analysis by use of the description and by the names of classes
  properties present in the OWL file. The description along with the description(classes and properties) 
  of the OWL file of the ontology are compared against the ODPs. All the ODPs present have description
 *along with the classes and properties.
 */
    // 1. DESCRIPTION
	ParagraphVectorsTextExample te=new ParagraphVectorsTextExample();
	te.description();
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
 * so that these range between 0 to 1 and we can set a particular threshold for the ODP recommendations
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
