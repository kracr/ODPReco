package com.recommender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

//Contributed by Maleeha

public class NormalisingLuceneScore {
	
	   public  void normalisingScores(int n) throws IOException{
		
		File file = new File(System.getProperty("user.dir")+ "/resources/lucene_scores"); //File that has the scores
		
		int i=0;
		  Double array1[]=new Double[n];
		  BufferedReader br = new BufferedReader(new FileReader(file)); 	  
		  String st; 
		  //the values from the integrated_values file are taken and stored as array
		  while ((st = br.readLine()) != null) {
				  Double db=Double.parseDouble(st);
				  array1[i]=db;
			  //System.out.println(array1[i]);
			  i++;		  
		  }
		  /*after storing in array, the min value and max value is found
		   *  from the given array  
		   */
		  double minValue, maxValue;
		  minValue=array1[0];
		  maxValue=array1[0];
		  for (int j = 1; j < n; j++) 
		  {
			  if(array1[j] > maxValue)
	              maxValue = array1[j];  //max value
	      else if (array1[j] < minValue)
	              minValue = array1[j];   //max value
		  }
		  // normalizing the values
		  PrintStream kq123=new PrintStream(System.getProperty("user.dir")+ "/resources/normalised_lucenescores");//file for saving the normalised value
		  double denom=maxValue-minValue;  //code below is for normalisation
		  System.setOut(kq123);
		  for(int k=0;k<n;k++){
			  array1[k]= (array1[k]-minValue)/denom;
			  System.out.println(array1[k]);
		  }
		  //Merging the normalised scores and top 5 ODPs
		  
		  PrintStream mr=new PrintStream(System.getProperty("user.dir")+ "/resources/lucene_ontology");
	      BufferedReader br_nv=new BufferedReader(new FileReader(System.getProperty("user.dir")+"/resources/normalised_lucenescores"));
	      BufferedReader br_li=new BufferedReader(new FileReader(System.getProperty("user.dir")+"/resources/lucene_recowithIRI"));
	       String line_nor=br_nv.readLine();
	       String line_li=br_li.readLine();
	      while(line_nor !=null && line_li != null ){
	            mr.println(line_nor + " " + line_li);
	            line_nor=br_nv.readLine();
	            line_li=br_li.readLine();
	      
	}
	br_nv.close();
	br_li.close();
	mr.close();
	 
	//Retaining only top 5 files for lucene recommendation
	
	BufferedReader br_lf=new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/lucene_ontology"));
	PrintStream ms=new PrintStream(System.getProperty("user.dir")+"/resources/final_lucenereco");
	String lf=br_lf.readLine();
	int i_lf=1;
	while(lf != null && i_lf<=5)
	{
		ms.println(lf);
	    lf=br_lf.readLine();
	    i_lf++;
	}
	
}
}
