package com.recommender;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Contributed by Maleeha

public class OrderLuceneODPsAndAssignWeight {
   public void orderAndWeight() throws IOException{
	   
	   BufferedReader blf=new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/lucene_ontology"));
	   BufferedReader blfodp=new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/odp_list_for_lucene"));
	   BufferedReader b=new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/lucene_odplist"));
	   PrintWriter pwlf = new PrintWriter(System.getProperty("user.dir")+ "/resources/lucene_odplist"); 
	   PrintWriter pwlf2 = new PrintWriter(System.getProperty("user.dir")+ "/resources/lucene_nonredundantodplist"); 
	   
	   
	   
	   String brodp=blfodp.readLine();
	   String str_lucene="";
	   String s="";
	   //convert values from lucene ontology to list
	   
	   List<String> l_lucene = new ArrayList<String>();
	   while((str_lucene = blf.readLine())!=null){
	      l_lucene.add(str_lucene);
	    
	   }
           
	  // System.out.println(l_lucene);
	   
	   //converting the list to String array
	   
	   String arr_lucene[] = new String[l_lucene.size()];              
		for(int j =0;j<l_lucene.size();j++){
		  arr_lucene[j] = l_lucene.get(j);
		}
	   
	  
	   
		while(brodp != null){
			
			
	    
			for(int i=0;i<arr_lucene.length;i++){
				//System.out.println(arr_lucene[i]);
				//System.out.println(brodp);
				if(arr_lucene[i].contains(brodp)){
					pwlf.println(arr_lucene[i]);
					
				}
				
				//brodp=blfodp.readLine();
			}
			
	          pwlf.println("0.0 " + brodp);
				
			
		
			
			brodp=blfodp.readLine();
			
		}
		
		pwlf.close();
		blfodp.close();
		blf.close();
		
		//now we see if array string is present, then we delete its next entry
		
		//Converting into string
		
		  
		   List<String> lucene_nonr = new ArrayList<String>();
		   while((s = b.readLine())!=null){
		      lucene_nonr.add(s);
		    
		   }
	           
		   
		   
		   //converting the list to String array
		   
		   String nonr_lucene[] = new String[lucene_nonr.size()];              
			for(int j =0;j<lucene_nonr.size();j++){
			  nonr_lucene[j] = lucene_nonr.get(j);
			}
			String val="";
		
			 //checking if it prints output
			for(int x=0;x<nonr_lucene.length;x++)
			{
			
				nonr_lucene[x]= nonr_lucene[x].replaceAll("[^a-zA-Z0-9.]", ""); 
				nonr_lucene[x]= nonr_lucene[x].replaceAll("[^0-9.]", "");
				//nonr_lucene[x]=nonr_lucene.
			//nonr_lucene[x]= nonr_lucene[x].replaceAll("[^0-9]", "");
				// nonr_lucene[x]=nonr_lucene[x].replace("[\\.//]",""); 
    if(! nonr_lucene[x].equals("0.0"))
    {
    	pwlf2.println(Double.parseDouble(nonr_lucene[x]));
         x=x+1;
    }
    else{
				pwlf2.println(Double.parseDouble(nonr_lucene[x]));
			}
			}  
				
		pwlf2.close();
		
     //Assigning .4 weight to Doc2Vec approach
		BufferedReader nor_doc=new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/normalized_values"));
		 PrintWriter pwlf23 = new PrintWriter(System.getProperty("user.dir")+ "/resources/normalised_weighteddocvalues"); 
		String nor_sc=nor_doc.readLine();
		while(nor_sc != null){
			pwlf23.println(Double.parseDouble(nor_sc));
			nor_sc=nor_doc.readLine();
		}
		
	 pwlf23.close();
	 
	 //combining the 2 scores
	 BufferedReader fl=new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/lucene_nonredundantodplist")); 
	 BufferedReader fd=new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/normalised_weighteddocvalues"));
	 PrintWriter pwlf56 = new PrintWriter(System.getProperty("user.dir")+ "/resources/final_values"); 
	 String sl=fl.readLine();
	 String sd=fd.readLine();
	   while(sl != null && sd != null){
		   pwlf56.println(Double.parseDouble(sl)+Double.parseDouble(sd));
		   sl=fl.readLine();
		   sd=fd.readLine();
	   }
	   pwlf56.close();
   }
}
