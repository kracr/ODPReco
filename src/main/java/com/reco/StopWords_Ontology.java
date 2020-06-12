package com.reco;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

public class StopWords_Ontology {

//Contributed by Maleeha	
		
	public void stopWordsDesOntology() {
		 /*Stop word file is maintained that stores the stop words i.e those words which are
		 * used commonly in phrases. These include- "the,from,how,what etc". These are used in almost
		 * all documents (sentences ), so considering these in paragraph vectors of description
		 * of ontology with ODPs will give rise to the similarity value but however, semantically these
		 * aren't coherent. So, therefore such words are removed from the description of the 
		 * ontology, so as to reduce the irrelevant similarity among the two.
		 */
		int k=0,i,j;
		ArrayList<String> wordsList = new ArrayList<String>();
		String sCurrentLine;
		String[] stopwords = new String[2000];
		try{
		        FileReader fr=new FileReader(System.getProperty("user.dir")+ "/resources/stopwords.txt"); //file that has stop Words
		        BufferedReader br= new BufferedReader(fr);
		        while ((sCurrentLine = br.readLine()) != null){
		            stopwords[k]=sCurrentLine; //reading the words and storing in array
		            k++;
		        }
		        InputStream is = new FileInputStream(System.getProperty("user.dir")+ "/resources/ontology_description"); // input file
		        BufferedReader buf = new BufferedReader(new InputStreamReader(is)); 
		        String line = buf.readLine(); 
		        StringBuilder sb = new StringBuilder(); 
		        while(line != null){ sb.append(line); line = buf.readLine(); }
		        String[] words = sb.toString().split(" ");
		        for (String word : words){
		        	/*Taking the words from the description of the ontology and placing them one
		        	 * by one in the list named wordList
		        	 */
		            wordsList.add(word); 
		        }
		        for(int ii = 0; ii < wordsList.size(); ii++){
		            for(int jj = 0; jj < k; jj++){
		                if(stopwords[jj].contains(wordsList.get(ii).toLowerCase())){ 
		            /*if stop word present in the description, remove it and then traverse for 
		                	next words and do the same if stopWords found */
		                	wordsList.remove(ii);
		                    break;
		                }
		             }
		        }
		        //writing in the file with the stop words removed
		        PrintStream o = new PrintStream(new File(System.getProperty("user.dir")+ "/resources/ontology_description")); 		        		        
		        for (String str : wordsList){  //loop for writing the words without the stop words
		        	System.setOut(o);
		        	System.out.print(str+" ");
		        }   
		   o.close();
		   }
		catch(Exception ex){
		        System.out.println(ex);
		    }

	}

}
