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

public class ParagraphVectorsSubObjectProperty {

	//Logging the information
	 private static final Logger log = LoggerFactory.getLogger(ParagraphVectorsTextExample.class);
	    public static String dataLocalPath;
	    public  String subObjectProperty() throws Exception {
	    	
	    	/*PrintWriter to save the merging in the file - mergedFile_subObjectProperty
	    	 * The subObjectProperty file of the ontology and the ODP are merged so that
	    	 * both are clubbed in a single file. The first line of the merged file containes
	    	 * the property from the ontology and the rest of the lines that follow it have 
	    	 * the property of the ODPs present in the list.
	    	 */
	        PrintWriter pw = new PrintWriter(System.getProperty("user.dir")+ "/resources/mergedFile_subObjectProperty"); 	          
	        // BufferedReader to obtain the files of the ontology and the ODP 
	        BufferedReader br1 = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/subObjectProperty_ontology")); 
	        BufferedReader br2 = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/subObjectProperty_odp")); 	          
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
	        // closing the  resources 
	        br1.close(); 
	        br2.close(); 
	        pw.close(); 	          
	        
	        //System.out.println("Merged");-> for checking the execution flow of code 
	        /* After merging the file, Doc2Vec is implemented over the merged file 
	         * so that the vectors are obtained.
	         */
	    	File file = new File(System.getProperty("user.dir")+ "/resources/mergedFile_subObjectProperty");
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
	       // System.out.println("Check the file");-> for checking the execution of the code
	        PrintStream p=new PrintStream(new File(System.getProperty("user.dir")+ "/resources/subObjectProperty_ontology")); //storing the numeric values
	             
	        Similarity s=new Similarity(); //method that checks the cosine similarity
	        s.similarityCheck(p,vec);
	
	        return "sub-obj";
	    }
	}




