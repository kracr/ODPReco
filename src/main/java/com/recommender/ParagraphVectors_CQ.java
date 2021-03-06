package com.recommender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
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

public class ParagraphVectors_CQ {
	 public void cqMapping() throws Exception {
	    	/*PrintWriter is used to save the merged property files of the ontology and 
	    	 * the ODP in merged file. On merging, the first line of the file has the cq's of 
	    	 * the ontology and the rest of the lines that follow have cq of the ODPs 
	    	 */
	        PrintWriter pw = new PrintWriter(System.getProperty("user.dir")+ "/resources/mergedFile_cq"); 
	          
	        // BufferedReader to obtain the cq files of the ontology and odp and then merging.
	        BufferedReader br1 = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/ontology_cq")); 
	        BufferedReader br2 = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/odps_cq")); 	          	          
	        String line1 = br1.readLine(); 
	        String line2 = br2.readLine();  
	        if(line1==null){
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
	          
	        //System.out.println("Merged"); -> for checking the flow of code
	    /*The merged file obtained is then passed through the Doc2Vec model 
	     * for training. The vectors are obtained via the Doc2Vec model.
	     *The code is as below- 
	     */
	    	File file = new File(System.getProperty("user.dir")+ "/resources/mergedFile_cq");	    	
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
                 //storing in a file
	        //System.out.println("Check the file");-> checking the proper code execution
	        PrintStream p=new PrintStream(new File(System.getProperty("user.dir")+ "/resources/ontology_cq"));//storing the values
	        
	     
	        Similarity s=new Similarity(); 
	        s.similarityCheck(p,vec); //method that checks the cosine similarity
	       
	     
	       
	    }
	}







	  