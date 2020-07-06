package com.recommender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

//contributed by Maleeha

public class MergeOntologyFiles {

	public void mergeFiles() throws IOException{

		   PrintWriter pw = new PrintWriter(System.getProperty("user.dir")+ "/resources/lucene_ontology"); 
	       
		    File f1 = new File(System.getProperty("user.dir")+ "/resources/filteredSignature_ontology");
		    File f2 = new File(System.getProperty("user.dir")+ "/resources/objectPropertyDomain_ontology");
		    File f3 = new File(System.getProperty("user.dir")+ "/resources/objectPropertyRange_ontology");
		    File f4 = new File(System.getProperty("user.dir")+ "/resources/subclass_ontology");
		    
	        BufferedReader br1=new BufferedReader(new FileReader(f1));
	        BufferedReader br2=new BufferedReader(new FileReader(f2));
	        BufferedReader br3=new BufferedReader(new FileReader(f3));
	        BufferedReader br4=new BufferedReader(new FileReader(f4));
	        
	        String line1=br1.readLine();
	        String line2=br2.readLine();
	        String line3=br3.readLine();
	        String line4=br4.readLine();
	        
	     
	      
	       while (line1 != null || line2 !=null || line3 !=null || line4 !=null) 
	       { 
	           if(line1 != null) 
	           { 
	               pw.print(line1); 
	               line1 = br1.readLine(); 
	              
	           } 
	             
	           if(line2 != null) 
	           { 
	               pw.print(line2); 
	               line2 = br2.readLine(); 
	           
	           } 
	           if(line3 != null) 
	           { 
	               pw.print(line3); 
	               line3 = br3.readLine(); 
	           
	           } 
	             
	           if(line4 != null) 
	           { 
	               pw.print(line4); 
	               line4 = br4.readLine(); 
	          
	           } 
	       } 	      
	       pw.flush(); 	          
	       // closing the  resources 
	       br1.close(); 
	       br2.close(); 
	       br3.close();
	       br4.close();
	       
	       pw.close();
		
	              }
	      }


