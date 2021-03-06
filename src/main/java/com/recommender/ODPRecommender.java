package com.recommender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;



//Contributed by Maleeha

public class ODPRecommender {
	
    public void odpRecommender() throws NumberFormatException, IOException{

	File f=new File(System.getProperty("user.dir")+ "/resources/final_values"); //taking the normalised values from this file
	File f2=new File(System.getProperty("user.dir")+ "/resources/odp_list"); //the list of ODPs present in this file
	BufferedReader br = new BufferedReader(new FileReader(f)); //reading the files
	BufferedReader br2=new BufferedReader(new FileReader(f2));
	int n=73; //73 is taken as total ODPs present are 73
	String arrayStr[]=new String[n];
	  Double arrayDouble[]=new Double[n];
	  String stD, stS;
	  int i=0;
	  int j=0;
	  //for storing double values in array
	  while ((stD = br.readLine()) != null) {
		  Double db=Double.parseDouble(stD);
		  arrayDouble[i]=db;
	      i++;
	  
  }
	  //for storing odp names in string array
	  while ((stS = br2.readLine()) != null) {
		  //Double db=Double.parseDouble(stS);
		  arrayStr[j]=stS;
	       j++;
	  
  }
	  //now setting the threshold and saving results in a file
	  
	  PrintStream preco=new PrintStream(System.getProperty("user.dir")+ "/resources/recommender");	 
	  
	  for(int k=0;k<n;k++){
		  if(arrayDouble[k]>0.85) //threshold is set to 0.8, so only the ODPs ranging between 0.8-1 will be recommended
	
		  //preco.println(arrayStr[k] + "with the score as : " + arrayDouble[k]);
		  preco.println(arrayDouble[k] + " " + arrayStr[k]);
	  }
	    preco.close();
    
    }	
}
