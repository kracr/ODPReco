package com.reco;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

//Contributed By Maleeha

public class Normalising_Scores {
	
    /* The integrated scores have varied values. So, these are passed into this file. This is 
     * done so that all have values ranging from 0-1. This is done so that it is easy to choose
     * a particular threshold for the ODP recommendation. The normalised range of values is
     * sobtained by following the below code. 
     */
	
	public  void normalisingScores() throws IOException{
	File file = new File(System.getProperty("user.dir")+ "/resources/integrated_values"); //File that has the scores
	int n=73; //it is taken as 73 since 73 odps are taken into consideration
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
	  PrintStream kq123=new PrintStream(new File(System.getProperty("user.dir")+ "/resources/normalized_values"));//file for saving the normalised value
	  double denom=maxValue-minValue;  //code below is for normalisation
	  System.setOut(kq123);
	  for(int k=0;k<n;k++){
		  array1[k]= (array1[k]-minValue)/denom;
		  System.out.println(array1[k]);
	  }
	  
}
}



