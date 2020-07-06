package com.recommender;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

//Contributed By Maleeha

public class GraphConstruction {

	public void graphcons() throws IOException{
		
		//merging the sub-class, object property domain and range together
		
		PrintWriter pwm = new PrintWriter(System.getProperty("user.dir")+ "/resources/axioms_usedInGraph"); 
	    BufferedReader bx=new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/subclass_ontology"));
	    BufferedReader bxy=new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/objectPropertyDomain_ontology"));
	    BufferedReader bxyz=new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/objectPropertyRange_ontology"));
	    
	    
        String linex=bx.readLine();
        String linexy=bxy.readLine();
        String linexyz=bxyz.readLine();
        
        //merging
        while(linex !=null || linexy != null || linexyz !=null){
        	if(linex !=null){
        		pwm.println(linex);
        	      linex=bx.readLine();
        	}
        	if(linexy != null){
        		pwm.println(linexy);
        		linexy=bxy.readLine();
        	}
        	if(linexyz !=null){
        		pwm.println(linexyz);
        		linexyz=bxyz.readLine();
        	}
        }
       
        //closing
        bx.close();
        bxy.close();
        bxyz.close();
        pwm.close();
        
		
		
		// reading the sub-class,object property domain and range
		BufferedReader b=new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/axioms_usedInGraph"));
		PrintWriter pwc = new PrintWriter(System.getProperty("user.dir")+ "/resources/ontology_graph"); 
		String l=b.readLine();
		int g=1;;
		String s="";
		while(l !=null){
			l=l.replaceAll("ObjectPropertyDomain","");
			l=l.replaceAll("ObjectPropertyRange","");
			l=l.replaceAll("SubClassOf", "");
			l=l.replaceAll("\\(", "");
			s=s+l;
			//System.out.println(l);	
			
			l=b.readLine();
			
		}
		//splitting and creating an array
		//System.out.println(s);
		String[] sary=s.split(" |\\)");
		//System.out.println(Arrays.toString(sary));
		
		//if same name, then replacing by same integer
		
		//System.out.println(Arrays.toString(sary));
		for(int i=0;i<sary.length;i++){
		  for(int j=i+1;j<sary.length;j++){
			  if(sary[i].equals(sary[j])){
				  //System.out.println(sary[i]);
				  sary[i]=Integer.toString(g);
				  sary[j]=Integer.toString(g);
				  g++;
			  }
		  }	
		  
		}
		//System.out.println(g);
		//if a word present <>, replace with integer
		//System.out.println(Arrays.toString(sary));
		for(int bl=0;bl<sary.length;bl++){
			if(sary[bl].contains("<")){
				//System.out.println("true");
			sary[bl]=Integer.toString(g);
			g++;
		}	
		}
		//System.out.println(Arrays.toString(sary));
		
		String sl="";
		
		int x=sary.length;
		//System.out.println(x);
	
		//take two values at a time and separating them with comma	 
			 if(x%2==0){
				 for(int xl=0;xl<sary.length;xl++){
						
					 sl=sl+ sary[xl]+"," + sary[++xl] + " ";
					// System.out.println(sl);
				 }
			 
				 
			 }
			 else{
				 
				 for(int xl=0;xl<sary.length-2;xl++){
						
					 sl=sl+ sary[xl]+"," + sary[++xl] + " ";
					 //System.out.println(sl);
				 }
			 }
			pwc.println(sl);
			pwc.close();
			GraphIsomorphism gi=new GraphIsomorphism();
			gi.graphiso();
					
		 }
	}



