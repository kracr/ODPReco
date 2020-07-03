package com.recommender;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Structural_Isomorphism {

	public void isomorphism() throws IOException{

		
		//for sub-class
		int iso=0;
		PrintWriter pw = new PrintWriter(System.getProperty("user.dir")+ "/resources/isomorphic_values"); 
		BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/subclass_isomorphismvalues"));  
		BufferedReader b=new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/subclass_ontology"));
		String l=b.readLine();
		String line=null; //for else part
		if(l==null){
			for(int m=0;m<73;m++){   //if sub-class is empty
			pw.print("0");
			pw.print("\n");
		}
		}
		else{
		//System.out.println(l);
		String st[] = l.split("SubClassOf");
		   for(int i=0;i<st.length-1;i++){

				iso++;
		    }
		
		// System.out.println(iso);-to check
		 String s=String.valueOf(iso);
				
		while ((line = br.readLine()) != null)  
		{  
			 
	            	if(line.equals(s)){ //isomorphism
	            	pw.print("0.2");
	            	pw.print("\n");
	            	}
	           
	            else{
	            	pw.print("0");
	            	pw.print("\n");
	            }
		} 
		}
		pw.close(); 
		
		//for object properties
		int isop=0;
		PrintWriter pwop = new PrintWriter(System.getProperty("user.dir")+ "/resources/isomorphicvalues_objectproperty"); 
		BufferedReader brop = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/objectprop_isomorphismvalues"));  
		BufferedReader bop=new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/objectPropertyDomain_ontology"));
		String lop=bop.readLine();
		String lineop=null; //for else part
		if(lop==null){
			for(int mop=0;mop<73;mop++){   //if object property is empty
			pwop.print("0");
			pwop.print("\n");
		}
		}
		else{
			
			String stop[] = lop.split("ObjectPropertyDomain");
			   for(int iop=0;iop<stop.length-1;iop++){

					//System.out.println(stop[iop]);
					isop++;
			    }

				String sop=String.valueOf(isop);
				while ((lineop = brop.readLine()) != null)  
				{  
					 
			            	if(lineop.equals(sop)){ //isomorphism
			            	pwop.print("0.2");
			            	pwop.print("\n");
			            	}
			           
			            else{
			            	pwop.print("0");
			            	pwop.print("\n");
			            }
				} 
		}
				pwop.close();
	
}
}
