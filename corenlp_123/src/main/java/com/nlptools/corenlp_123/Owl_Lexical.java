package com.nlptools.corenlp_123;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.FunctionalSyntaxDocumentFormat;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.parameters.Imports;

//Contributed by Maleeha

class Owl_Lexical{
	@SuppressWarnings("deprecation")
	/*It is the java program which obtains the OWL file of the ontology and obtains different
	 * properties from the OWL file as well as the signature of the ontology. Each of the 
	 * property is placed in a separate file. 
	 */
	public void owlOntology() throws OWLOntologyCreationException, OWLOntologyStorageException, IOException {
		//Asking the path from the user
		Scanner scan123=new Scanner(System.in);
		System.out.println("Please mention the complete path of the OWL file related to the ontology,including the .owl file extension");
		String s3_owl=scan123.nextLine();
		if(s3_owl.isEmpty())
		{
			System.out.println("Please specify the OWL file otherwise it won't be able to recommend the score");
			s3_owl=scan123.nextLine();
		}
		//Obtaining the OWL file and saving it
		 OWLOntologyManager man = OWLManager.createOWLOntologyManager();		 
		  File file = new File(s3_owl);
		  OWLOntology o = man.loadOntologyFromOntologyDocument(file);
		  File f=new File("ontology");
		  man.saveOntology(o, new FunctionalSyntaxDocumentFormat(),
				  new FileOutputStream(f));
		//Saving the signature of the ontology in the file
		 PrintStream pq = new PrintStream(new File("signature_ontology")); 		
		 Set<OWLEntity> p = new HashSet<OWLEntity>();
		 p=o.getSignature();		
		 System.setOut(pq);
		 System.out.print(p);
		 //getting axioms and saving in other file for STRUCTURAL part
		 PrintStream kq=new PrintStream(new File("structural_ontology"));
		 System.setOut(kq);
		 for(OWLAxiom ax:o.getAxioms()){
			 System.out.println(ax);
		 }
		 //AXIOMS SEPARATELY : COMPONENT AXIOMS
		//SUBCLASSES
		   PrintStream ps=new PrintStream(new File("subclass_ontology"));		 
		    System.setOut(ps);		 
		 for(OWLAxiom zx:o.getAxioms(AxiomType.SUBCLASS_OF)){
		   String s=zx.toString();	
		s=s.replaceAll("https://[^>]*/",""); //removing the IRI of the ontology
		s=s.replaceAll("http://[^>]*/","");
		 System.out.print(s);	
		}
		   //ANNOTATION ASSERTION
		   PrintStream ps1=new PrintStream(new File("annotationAssert_ontology"));			 
			 System.setOut(ps1);			 
			 for(OWLAxiom zx:o.getAxioms(AxiomType.ANNOTATION_ASSERTION)){
			String s=zx.toString();		
			s=s.replaceAll("https://[^>]*/","");
			s=s.replaceAll("http://[^>]*/","");
			 System.out.print(s);
			}
			//DATA PROPERTY DOMAIN
			   PrintStream ps2=new PrintStream(new File("dataPropertyDomain_ontology"));				 
				 System.setOut(ps2);				 
				 for(OWLAxiom zx:o.getAxioms(AxiomType.DATA_PROPERTY_DOMAIN)){
				String s=zx.toString();			
				s=s.replaceAll("https://[^>]*/","");
				s=s.replaceAll("http://[^>]*/","");
				 System.out.print(s);
				}
				 //DATA PROPERTY RANGE
               PrintStream ps3=new PrintStream(new File("dataPropertyRange_ontology"));				 
				 System.setOut(ps3);			 
				 for(OWLAxiom zx:o.getAxioms(AxiomType.DATA_PROPERTY_RANGE)){
				String s=zx.toString();		
				s=s.replaceAll("https://[^>]*/","");
				s=s.replaceAll("http://[^>]*/","");
				 System.out.print(s);
				}
				 //DISJOINT CLASSES
				 PrintStream ps4=new PrintStream(new File("disjointClasses123_ontology"));								 
				System.setOut(ps4);								 
				for(OWLAxiom zx:o.getAxioms(AxiomType.DISJOINT_CLASSES)){
					String s=zx.toString();							
					s=s.replaceAll("https://[^>]*/","");
					s=s.replaceAll("http://[^>]*/","");
					System.out.print(s);
								}	             
				//Object Property Domain
				 PrintStream ps6=new PrintStream(new File("objectPropertyDomain_ontology"));							 
				System.setOut(ps6);								 
				for(OWLAxiom zx:o.getAxioms(AxiomType.OBJECT_PROPERTY_DOMAIN)){
					String s=zx.toString();							
					s=s.replaceAll("https://[^>]*/","");
					s=s.replaceAll("http://[^>]*/","");
					System.out.print(s);
								}
				//Object Property Range
				 PrintStream ps7=new PrintStream(new File("objectPropertyRange_ontology"));							 
					System.setOut(ps7);							 
				for(OWLAxiom zx:o.getAxioms(AxiomType.OBJECT_PROPERTY_RANGE)){
					String s=zx.toString();					
					s=s.replaceAll("https://[^>]*/","");
					s=s.replaceAll("http://[^>]*/","");
					System.out.print(s);
								}
				//SUB-PROPERTY CHAIN OF
				 PrintStream ps8=new PrintStream(new File("chainOf_ontology"));				 
				 System.setOut(ps8);							 
				for(OWLAxiom zx:o.getAxioms(AxiomType.SUB_PROPERTY_CHAIN_OF)){
					String s=zx.toString();			
					s=s.replaceAll("https://[^>]*/","");
					s=s.replaceAll("http://[^>]*/","");
					System.out.print(s);
								}
				//SUB-DATA PROPERTY
				PrintStream ps9=new PrintStream(new File("subDataProperty_ontology")); 
				System.setOut(ps9);			 
	            for(OWLAxiom zx:o.getAxioms(AxiomType.SUB_DATA_PROPERTY)){
		        String s=zx.toString();			
		        s=s.replaceAll("https://[^>]*/","");
		        s=s.replaceAll("http://[^>]*/","");
		        System.out.print(s);
				    }
	           //SUB-OBJECT PROPERTY
	          PrintStream ps10=new PrintStream(new File("subObjectProperty_ontology"));	 
	           System.setOut(ps10);	 
               for(OWLAxiom zx:o.getAxioms(AxiomType.SUB_OBJECT_PROPERTY)){
               String s=zx.toString();	
               s=s.replaceAll("https://[^>]*/","");
               s=s.replaceAll("http://[^>]*/","");
               System.out.print(s);
		            }
				//To remove the IRI from signature	
                 RemoveFromSignature_Ontology obj=new RemoveFromSignature_Ontology();
                      obj.removeFromSignature_Ont();
	                      }
                       }


