package com.recommender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

public class ParagraphVectorsDisjointClasses {
	 private static final Logger log = LoggerFactory.getLogger(ParagraphVectorsTextExample.class);
	    public static String dataLocalPath;
	   public String disjointClasses() throws Exception {
	   
	    	 /*PrintWriter used for obtaining the merged results of the disjointClasses and 
	    	saving in mergedFile_disjointClasses. The merged file has the first line as the 
	    	property of the ontology and the rest of the lines that follow represent the 
	    	disjoint class property of each odp from the list.
	    	*/
	        PrintWriter pw = new PrintWriter(System.getProperty("user.dir")+ "/resources/mergedFile_disjointClasses"); 	          
	// BufferedReader for obtaining the disjoint class property that is saved in the respective files
	        BufferedReader br1 = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/disjointClasses_ontology")); 
	        BufferedReader br2 = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/disjointClasses_odp")); 	                   
	        String line1 = br1.readLine(); 
	        String line2 = br2.readLine(); 
	        if(line1 == null){//first line 
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
	        // closing resources 
	        br1.close(); 
	        br2.close(); 
	        pw.close(); 	          
	        //System.out.println("Merged"); - to check the merged code
	    	File file = new File(System.getProperty("user.dir")+ "/resources/mergedFile_disjointClasses");
       /*After obtaining the merged file, Doc2Vec code is implemented for
        * the similarity of the ontology with the ODP 	    	
         */
	        SentenceIterator iter = new BasicLineIterator(file);
	        AbstractCache<VocabWord> cache = new AbstractCache<VocabWord>();
	        TokenizerFactory t = new DefaultTokenizerFactory();
	        t.setTokenPreProcessor(new CommonPreprocessor());	        
	        LabelsSource source = new LabelsSource("Line_");
	        ParagraphVectors vec = new ParagraphVectors.Builder()
	        		.minWordFrequency(1)
	 	            .labelsSource(source)
	 	            .layerSize(100)
	 	            .windowSize(5)
	 	            .iterate(iter)
	 	            .allowParallelTokenization(false)
	                .workers(1)
	                .seed(1)
	                .tokenizerFactory(t)  
	                .build();
	             vec.fit();
	             
	 //System.out.println("Check the file");- for checking the proper execution of code
	        PrintStream p=new PrintStream(new File(System.getProperty("user.dir")+ "/resources/disjointClasses_ontology")); //for storing the values
	       
	        Similarity s=new Similarity();//method that checks the cosine similarity
	        s.similarityCheck(p,vec);
	       
	        return "disjoint";
	    }
	}




