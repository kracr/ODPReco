package com.recommender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

//Contributed by Maleeha

public class GraphIsomorphism {

	public void graphiso() throws IOException{
	//implementing procedure for graph isomorphism
	int i=0;
	BufferedReader br=new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/odp_graph"));
	BufferedReader br2=new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/ontology_graph"));
	PrintWriter pwg = new PrintWriter(System.getProperty("user.dir")+ "/resources/isomorphic_values"); 
	
	String l=br.readLine();
	String l2=br2.readLine();
	if (l2 != null){
     while(l != null){
  	   if(l2.equals(l)){
  		   pwg.println("0.02"); //that is the weight we assign for structural isomorphism and 0.2 is assigned when all are same.
  	   l=br.readLine();
     }
     
  	//if the two aren't equal then it is checked word by word
  	   
     else {
  	   String s1_words[] = l.split("\\s");
  	    String s2_words[] = l2.split("\\s");

  	    int num_words = s2_words.length;
  	    for(String s : s1_words){
  	        for(String ss : s2_words){
  	            if(ss.equals(s)){
  	                i++;
  	            }
  	        }
  	    }
  	    //System.out.println("value of i " + i );
  	    if(i>=2)
  	    	pwg.println("0.01"); // 2 or greater than two instances are same
  	    else
  	       pwg.println("0"); //less than 2 instances are same

  	   i=0;
  	   l=br.readLine();
     }
		
	}
	}
	else{
		for(int n=0;n<73;n++){
				
		pwg.println("0"); //since it is empty, so isomorphic value is set to 0 for all
		}
	}
	br.close();
	br2.close();
	pwg.close();
}
}
