package com.reco;

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

public class ParagraphVectorsObjectDomainProperty {
	//Logging Information
	 private static final Logger log = LoggerFactory.getLogger(ParagraphVectorsTextExample.class);
	    public static String dataLocalPath;
	    public  String objectDomainProperty() throws Exception {
	    	 /*PrintWriter to create a file that saves the merged text from the object 
	        domain property files of the ontology as well as the odp. The first line in
	        the merged file contains the object domain property of the ontology and the 
	        following lines contain the property of the ODPs in the list
	        */
	        PrintWriter pw = new PrintWriter(System.getProperty("user.dir")+ "/resources/mergedFile_objectPropertyDomain"); 	          
	  // BufferedReader for obtaining the files of ontology and odp with objectDomainProperty 
	     BufferedReader br1 = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/objectPropertyDomain_ontology")); 
	     BufferedReader br2 = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/objectPropertyDomain_odp")); 	          	          
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
	        //System.out.println("Merged"); - to check the execution of the code
	    
	        /*After merging the files, Doc2Vec is run on the merged file, so that the model 
	         gets trained & similarity is obtained of the ontology with the ODPs. Line0 represents the
	         * property of the ontology and the rest of the lines (Line1-73) represent the property
	         * of the ODPs in the list. 
	         */
	    	File file = new File(System.getProperty("user.dir")+ "/resources/mergedFile_objectPropertyDomain");	    	
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
	        //System.out.println("Check the file");-to check the execution of the code
	        PrintStream p=new PrintStream(new File(System.getProperty("user.dir")+ "/resources/objectPropertyDomain_ontology")); //storing numeric values
	
	        Similarity s=new Similarity(); //method that checks the cosine similarity
	        s.similarityCheck(p,vec);
	        
	
	        return"object-domain";
	    }
	}



