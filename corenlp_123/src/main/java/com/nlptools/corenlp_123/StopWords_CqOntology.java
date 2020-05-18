package com.nlptools.corenlp_123;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

//Contributed by Maleeha

public class StopWords_CqOntology {
	 /*Stop word file is maintained that stores the stop words i.e those words which are
	 * used commonly in phrases. These include- "the,from,how,what etc". These are used in almost
	 * all documents (sentences ), so considering these in paragraph vectors of competency question
	 * of ontology with ODPs will give rise to the similarity value but however, semantically these
	 * aren't coherent. So, therefore such words are removed from the competency questions of the 
	 * ontology, so as to reduce the irrelevant similarity among the two.
	 */
	public void stopWordsCQOntology() {
		int k=0,i,j;
		ArrayList<String> wordsList = new ArrayList<String>();
		String sCurrentLine;
		String[] stopwords = new String[2000];
		try{
		        FileReader fr=new FileReader("stopwords.txt"); //file which has stopWords
		        BufferedReader br= new BufferedReader(fr);
		        while ((sCurrentLine = br.readLine()) != null){
		            stopwords[k]=sCurrentLine; //reading the stop words and storing in array
		            k++;
		        }
		        InputStream is = new FileInputStream("ontology_cq"); //input file
		        BufferedReader buf = new BufferedReader(new InputStreamReader(is)); 
		        String line = buf.readLine(); 
		        StringBuilder sb = new StringBuilder(); 
		        while(line != null){ sb.append(line); line = buf.readLine(); }
		        String[] words = sb.toString().split(" ");
		        for (String word : words){
		        	/*Taking the words from the competency questions of the ontology and 
		        	 *placing them one by one in the list named wordList
		        	 */
		            wordsList.add(word);
		        }
		        for(int ii = 0; ii < wordsList.size(); ii++){
		            for(int jj = 0; jj < k; jj++){
		       /*if stop word present in the description, remove it and then traverse for 
	             next words and do the same if stopWords found */
		                if(stopwords[jj].contains(wordsList.get(ii).toLowerCase())){
		                    wordsList.remove(ii);
		                    break;
		                }
		             }
		        }
		        //writing in the file with the stop words being removed
		        PrintStream o = new PrintStream(new File("ontology_cq")); 	
		      //loop for writing the words without the stop words
		        for (String str : wordsList){		           
		        	System.setOut(o);
		        	System.out.print(str+" ");
		        }   
		  o.close();  }
		catch(Exception ex){
		        System.out.println(ex);
		    }

	}

}
