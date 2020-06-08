package com.nlptools.corenlp_123;

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

public class ParagraphVectorsObjectPropertyRange {

	//Logging Information
	 private static final Logger log = LoggerFactory.getLogger(ParagraphVectorsTextExample.class);
	    public static String dataLocalPath;

	    public  String objectRangeProperty() throws Exception {
	    	 /*PrintWriter to save the output of merging. The objectRange property of the 
	    	  * ontology is merged with the objectRange property of the ODPs in the list.
	    	  * On merging, the results are saved in mergedFile_objectPropertyrange with the 
	    	  * first line representing the property of the ontology and the rest of the
	    	  * lines that follow represent the property of the ODPs in the list.   
	    	  */
	        PrintWriter pw = new PrintWriter(System.getProperty("user.dir")+ "/resources/mergedFile_objectPropertyRange"); 	          
	        /*BufferedReader for obtaining the objectRange property files of the ontology
	         * and the ODP*/	         
	        BufferedReader br1 = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/objectPropertyRange_ontology")); 
	        BufferedReader br2 = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/objectPropertyRange_odp")); 	          	          
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
	        // closing the resources 
	        br1.close(); 
	        br2.close(); 
	        pw.close(); 	          
	       // System.out.println("Merged"); -> to check the execution of code
	    /*After obtaining the mergedFile, Doc2Vec model is ran over the merged file to obtain
	     * the vectors. Doc2Vec generates the vectors for the document. After obtaining the 
	     * vectors, cosine similarity is implemented for finding the similarity of the 
	     *ontology with the list of ODPs 
	     */
	    	File file = new File(System.getProperty("user.dir")+ "/resources/mergedFile_objectPropertyRange");
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
	             
	       // System.out.println("Check the file");->to check the proper execution of code
	        
	        PrintStream p=new PrintStream(new File(System.getProperty("user.dir")+ "/resources/objectPropertyRange_ontology")); //storing the numeric values
	      
	        Similarity s=new Similarity(); //method that checks the cosine similarity
	        s.similarityCheck(p,vec);
	       	        
	        return "object-range";
	    }
	}




