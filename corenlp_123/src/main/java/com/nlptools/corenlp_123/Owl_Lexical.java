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
	public void owlOntology(String s3) throws OWLOntologyCreationException, OWLOntologyStorageException, IOException {
			
		//Obtaining the OWL file and saving it
		 OWLOntologyManager man = OWLManager.createOWLOntologyManager();		 
		  File file = new File(s3);
		  OWLOntology o = man.loadOntologyFromOntologyDocument(file);
		  File f=new File(System.getProperty("user.dir")+ "/resources/ontology");
		  man.saveOntology(o, new FunctionalSyntaxDocumentFormat(),
				  new FileOutputStream(f));
		//Saving the signature of the ontology in the file
		 PrintStream pq = new PrintStream(new File(System.getProperty("user.dir")+ "/resources/filteredSignature_ontology")); 		
		 Set<OWLEntity> p = new HashSet<OWLEntity>();
		 p=o.getSignature();
		 String s1=p.toString();
			s1=s1.replaceAll("http://[^>]*/",""); //removing the IRI of the ontology
			s1=s1.replaceAll("https://[^>]*/","");
		 System.setOut(pq);
		 System.out.print(s1);
		 	 
		 
		 //AXIOMS SEPARATELY : COMPONENT AXIOMS
		//SUBCLASSES
		   PrintStream ps=new PrintStream(new File(System.getProperty("user.dir")+ "/resources/subclass_ontology"));		 
		    System.setOut(ps);		 
		 for(OWLAxiom zx:o.getAxioms(AxiomType.SUBCLASS_OF)){
		   String s=zx.toString();	
		s=s.replaceAll("https://[^>]*/",""); //removing the IRI of the ontology
		s=s.replaceAll("http://[^>]*/","");
		 System.out.print(s);	
		}
		   
			//DATA PROPERTY DOMAIN
			   PrintStream ps2=new PrintStream(new File(System.getProperty("user.dir")+ "/resources/dataPropertyDomain_ontology"));				 
				 System.setOut(ps2);				 
				 for(OWLAxiom zx:o.getAxioms(AxiomType.DATA_PROPERTY_DOMAIN)){
				String s=zx.toString();			
				s=s.replaceAll("https://[^>]*/","");
				s=s.replaceAll("http://[^>]*/","");
				 System.out.print(s);
				}
		  
				 //DISJOINT CLASSES
				 PrintStream ps4=new PrintStream(new File(System.getProperty("user.dir")+ "/resources/disjointClasses123_ontology"));								 
				System.setOut(ps4);								 
				for(OWLAxiom zx:o.getAxioms(AxiomType.DISJOINT_CLASSES)){
					String s=zx.toString();							
					s=s.replaceAll("https://[^>]*/","");
					s=s.replaceAll("http://[^>]*/","");
					System.out.print(s);
								}	             
				//Object Property Domain
				 PrintStream ps6=new PrintStream(new File(System.getProperty("user.dir")+ "/resources/objectPropertyDomain_ontology"));							 
				System.setOut(ps6);								 
				for(OWLAxiom zx:o.getAxioms(AxiomType.OBJECT_PROPERTY_DOMAIN)){
					String s=zx.toString();							
					s=s.replaceAll("https://[^>]*/","");
					s=s.replaceAll("http://[^>]*/","");
					System.out.print(s);
								}
				//Object Property Range
				 PrintStream ps7=new PrintStream(new File(System.getProperty("user.dir")+ "/resources/objectPropertyRange_ontology"));							 
					System.setOut(ps7);							 
				for(OWLAxiom zx:o.getAxioms(AxiomType.OBJECT_PROPERTY_RANGE)){
					String s=zx.toString();					
					s=s.replaceAll("https://[^>]*/","");
					s=s.replaceAll("http://[^>]*/","");
					System.out.print(s);
								}
	            
	           //SUB-OBJECT PROPERTY
	          PrintStream ps10=new PrintStream(new File(System.getProperty("user.dir")+ "/resources/subObjectProperty_ontology"));	 
	           System.setOut(ps10);	 
               for(OWLAxiom zx:o.getAxioms(AxiomType.SUB_OBJECT_PROPERTY)){
               String s=zx.toString();	
               s=s.replaceAll("https://[^>]*/","");
               s=s.replaceAll("http://[^>]*/","");
               System.out.print(s);
		            }
				//To remove the IRI from signature	
               //  RemoveFromSignature_Ontology obj=new RemoveFromSignature_Ontology();
                    //  obj.removeFromSignature_Ont();
	                      }
                       }


