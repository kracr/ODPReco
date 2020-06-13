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

public class ParagraphVectors_Signature {
	  
	   //Logging Information
	private static final Logger log = LoggerFactory.getLogger(ParagraphVectorsTextExample.class);
	    public static String dataLocalPath;
	    
	   
	    public void signature() throws Exception {
	    	
	        /*PrintWriter is used so that the signature files of the ontology and the ODP
	         * are merged into a single file named- mergedFile_signature. The first line
	         * of this file has the signature of the ontology and the rest of the lines
	         * that follow have the signature of the ODPs present in the list. 
	         */
	        PrintWriter pw = new PrintWriter(System.getProperty("user.dir")+ "/resources/mergedFile_signature"); 	          
	        // BufferedReader to obtain the signature files of the ODP and the ontology
	        BufferedReader br1 = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/filteredSignature_ontology")); 
	        BufferedReader br2 = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/filteredSignature_odp")); 	          	          
	        String line1 = br1.readLine(); 
	        String line2 = br2.readLine(); 	          
	        // loop is used to copy the two files into the merged one	        
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
	        // closing the  resources 
	        br1.close(); 
	        br2.close(); 
	        pw.close(); 
	        
	       // System.out.println("Merged"); ->used for checking the proper execution	    
	    	/* After merging the two files, Doc2Vec is ran over the merged file so that
	    	 * the vectors are obtained 
	    	 */
	        File file = new File(System.getProperty("user.dir")+ "/resources/mergedFile_signature");	    	
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
	       // System.out.println("Check the file");-> used in order to check the code execution
	        PrintStream p=new PrintStream(new File(System.getProperty("user.dir")+ "/resources/filteredSignature_ontology"));
	      
	        Similarity s=new Similarity();
	        s.similarityCheck(p,vec);  //method that checks the cosine similarity
	     
	        
	    }
	}

