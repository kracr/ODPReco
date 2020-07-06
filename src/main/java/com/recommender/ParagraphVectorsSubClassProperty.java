package com.recommender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import org.deeplearning4j.models.paragraphvectors.ParagraphVectors;
import org.deeplearning4j.models.word2vec.VocabWord;
import org.deeplearning4j.models.word2vec.wordstore.inmemory.AbstractCache;
import org.deeplearning4j.text.documentiterator.LabelsSource;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Contributed by Maleeha

public class ParagraphVectorsSubClassProperty {
	
    public String subClassProperty() throws Exception {
   	 /* PrintWriter used so that the merged subClassProperty file of the
   	  * ontology is merged with that of the ODPs in the list and are contained in
   	  * mergedSubClassProperty. The first line of the merged file has the property of
   	  * the ontology and the rest of the lines have the sub-class property of the
   	  * ODPs present in the list
   	  */
       PrintWriter pw = new PrintWriter(System.getProperty("user.dir")+ "/resources/mergedSubClassProperty"); 	          
       // BufferedReader to obtain the files that are then merged 
       BufferedReader br1 = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/subclass_ontology")); 
       BufferedReader br2 = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/subclass_odp")); 	          	          
       String line1 = br1.readLine(); 
       String line2 = br2.readLine(); 
       if(line1 == null){//first line if empty, then move to the next line for merging 
   		pw.print("\n");
       } 
       while (line1 != null || line2 !=null) 
       { 	        	
       	 if(line1 != null) 
           { 
               pw.println(line1); 
               line1 = br1.readLine(); 
           } 
             
           if(line2 != null) 
           { 
               pw.println(line2); 
               line2 = br2.readLine(); 
           } 
       }
       	      
       pw.flush(); 	          
       // closing the resources 
       br1.close(); 
       br2.close(); 
       pw.close(); 
         
      // System.out.println("Merged"); -> to check the proper code execution	    
   	/*The merged file obtained is then passed to the Doc2Vec model so that
   	 * the vectors of the subclass property of the ontology and the ODP is obtained.
   	 * Following code represents the implementation of Doc2Vec model.
   	 */
       File file = new File(System.getProperty("user.dir")+ "/resources/mergedSubClassProperty");	    	
       SentenceIterator iter = new BasicLineIterator(file);
       AbstractCache<VocabWord> cache = new AbstractCache<VocabWord>();
       TokenizerFactory t = new DefaultTokenizerFactory();
       t.setTokenPreProcessor(new CommonPreprocessor());	        
       LabelsSource source = new LabelsSource("Line_");
       ParagraphVectors vec = new ParagraphVectors.Builder()
       		 .minWordFrequency(1) //min word frequency in training corpus
	            .labelsSource(source) //pre-defined labels
	            .layerSize(100)      //no.of dimensions for output vectors
	            .windowSize(5)       //defines context window size
	            .iterate(iter)       //to iterate over sentences
	            .allowParallelTokenization(false)  //no random order - false
                .workers(1)                      //to enable it to run in single thread
                .seed(1)                         // same initialization everytime
                .tokenizerFactory(t)   //string tokenisation     
	            .build();              // sequence vectors to be built
	             vec.fit();
       //System.out.println("Check the file");-> checking of the proper code execution
       PrintStream p=new PrintStream(new File(System.getProperty("user.dir")+ "/resources/subclass_values")); //file that stores the numeric values
   	  
       Similarity s=new Similarity();  //method that checks the cosine similarity
       s.similarityCheck(p,vec);

       return "sub-class";
   }
}






