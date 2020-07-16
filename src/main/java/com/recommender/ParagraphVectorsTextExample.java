package com.recommender;


import org.deeplearning4j.models.paragraphvectors.ParagraphVectors;
import org.deeplearning4j.models.word2vec.VocabWord;
import org.deeplearning4j.models.word2vec.wordstore.inmemory.AbstractCache;
import org.deeplearning4j.text.documentiterator.LabelsSource;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.sentenceiterator.labelaware.LabelAwareFileSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.labelaware.LabelAwareSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.labelaware.LabelAwareUimaSentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.UimaTokenizerFactory;
import org.nd4j.linalg.io.ClassPathResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.PrintWriter;

//Contributed by Maleeha

public class ParagraphVectorsTextExample {
                //Logging Information
    private static final Logger log = LoggerFactory.getLogger(ParagraphVectorsTextExample.class);
    public static String dataLocalPath;

       public void description() throws Exception {
    	 /*PrintWriter used so that the description of the ontology and ODPs is merged 
    	  * in the file 'merged_file.txt'. On merging the two, the first line of the 
    	  * file represents the description of the ontology while as the rest of the lines
    	  * that follow represent the description of the ODPs in the list.   	   
    	  */
        PrintWriter pw = new PrintWriter(System.getProperty("user.dir")+ "/resources/merged_file.txt"); 
          
        // BufferedReader for obtaining the description files of the ontology & ODP
        BufferedReader br1 = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/ontology_description")); 
        BufferedReader br2 = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/odps_description.txt"));          
        String line1 = br1.readLine(); 
        String line2 = br2.readLine(); 
          
        // loop is used to copy the lines of file 1 to the other 
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
        // closing the resources 
        br1.close(); 
        br2.close(); 
        pw.close(); 
          
       // System.out.println("Merged"); -> for checking the code execution
       /* On obtaining the merged file, Doc2Vec model is implemented so that vectors are
        * obtained. And the, similarity ratio of the ontology description with the ODPs 
        * can be found using cosine similarity 
        */
    	File file = new File(System.getProperty("user.dir")+ "/resources/merged_file.txt");   	
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
             
        //System.out.println("Check the file");->for execution of code
        PrintStream p=new PrintStream(new File(System.getProperty("user.dir")+ "/resources/description_values")); //storing the numeric values
       
       
        Similarity s=new Similarity(); //method that checks the cosine similarity
        s.similarityCheck(p,vec);
        
       
    }
}
