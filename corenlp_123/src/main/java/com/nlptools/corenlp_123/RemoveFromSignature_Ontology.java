package com.nlptools.corenlp_123;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

//Contributed by Maleeha

public class RemoveFromSignature_Ontology {
	public void removeFromSignature_Ont() throws IOException{
		/*This file is used to remove the complete IRI from the ontology and
		 * only have the signature that constitues the class names and all property names.
		 */
		 BufferedReader brf = new BufferedReader(new FileReader("signature_ontology"));
	     PrintStream ps=new PrintStream(new File("filteredSignature_ontology"));
	     System.setOut(ps); //writing into file
	     String line123 = brf.readLine(); // reading the file .
	     String lo = null;
	     while(line123 != null){	    	 
	    	lo=line123.replaceAll("https://[^>]*/",""); //https:______ is removed
	    	 lo=line123.replaceAll("http://[^>]*/",""); //http:________ is removed	    	
	    	 line123=brf.readLine();	     	     
	          System.out.println(lo);	     
	     }
		
	}

}

	

	

