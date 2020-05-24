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

public class ParagraphVectorsObjectDomainProperty {
	//Logging Information
	 private static final Logger log = LoggerFactory.getLogger(ParagraphVectorsTextExample.class);
	    public static String dataLocalPath;
	    public  void objectDomainProperty() throws Exception {
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
	        PrintStream p=new PrintStream(new File(System.getProperty("user.dir")+ "/resources/values_objectDomainProperty")); //storing numeric values
	        System.setOut(p);
	        
	 /*Implementing cosine similarity so that to obtain the numeric values of the 
	  * similarity of the ontology with the list of ODPs present. Line0- objectDomainProperty 
	  * of the ontology and the rest of the lines represent the objectDomainProperty of the ODPs
	  * in the list. So, the similarity is found between Line0 with the rest of the lines    
	 */
	        double similarity1 = vec.similarity("Line_0", "Line_1");
	       System.out.println(similarity1);
	        double similarity2 = vec.similarity("Line_0", "Line_2");
	        System.out.println(similarity2);
	        double similarity3 = vec.similarity("Line_0", "Line_3");
	        System.out.println(similarity3);
	        double similarity4 = vec.similarity("Line_0", "Line_4");
	        System.out.println(similarity4);
	        double similarity5 = vec.similarity("Line_0", "Line_5");
	        System.out.println(similarity5);
	        double similarity6 = vec.similarity("Line_0", "Line_6");
	        System.out.println(similarity6);
	        double similarity7 = vec.similarity("Line_0", "Line_7");
	        System.out.println(similarity7);
	        double similarity8 = vec.similarity("Line_0", "Line_8");
	        System.out.println(similarity8);
	        double similarity9 = vec.similarity("Line_0", "Line_9");
	        System.out.println(similarity9);
	        double similarity10 = vec.similarity("Line_0", "Line_10");
	        System.out.println(similarity10);
	        double similarity11 = vec.similarity("Line_0", "Line_11");
	        System.out.println(similarity11);
	        double similarity12 = vec.similarity("Line_0", "Line_12");
	        System.out.println(similarity12);
	        double similarity13 = vec.similarity("Line_0", "Line_13");
	        System.out.println(similarity13);
	        double similarity14 = vec.similarity("Line_0", "Line_14");
	        System.out.println(similarity14);
	        double similarity15 = vec.similarity("Line_0", "Line_15");
	        System.out.println(similarity15);
	        double similarity16 = vec.similarity("Line_0", "Line_16");
	        System.out.println(similarity16);
	        double similarity17 = vec.similarity("Line_0", "Line_17");
	        System.out.println(similarity17);
	        double similarity18 = vec.similarity("Line_0", "Line_18");
	        System.out.println(similarity18);
	        double similarity19 = vec.similarity("Line_0", "Line_19");
	        System.out.println(similarity19);
	        double similarity20 = vec.similarity("Line_0", "Line_20");
	        System.out.println(similarity20);
	        double similarity21 = vec.similarity("Line_0", "Line_21");
	        System.out.println(similarity21);
	        double similarity22 = vec.similarity("Line_0", "Line_22");
	        System.out.println(similarity22);
	        double similarity23 = vec.similarity("Line_0", "Line_23");
	        System.out.println(similarity23);
	        double similarity24 = vec.similarity("Line_0", "Line_24");
	        System.out.println(similarity24);
	        double similarity25 = vec.similarity("Line_0", "Line_25");
	        System.out.println(similarity25);
	        double similarity26 = vec.similarity("Line_0", "Line_26");
	        System.out.println(similarity26);
	        double similarity27 = vec.similarity("Line_0", "Line_27");
	        System.out.println(similarity27);
	        double similarity28 = vec.similarity("Line_0", "Line_28");
	        System.out.println(similarity28);
	        double similarity29 = vec.similarity("Line_0", "Line_29");
	        System.out.println(similarity29);
	        double similarity30 = vec.similarity("Line_0", "Line_30");
	        System.out.println(similarity30);
	        double similarity31 = vec.similarity("Line_0", "Line_31");
	        System.out.println(similarity31);
	        double similarity32 = vec.similarity("Line_0", "Line_32");
	        System.out.println(similarity32);
	        double similarity33 = vec.similarity("Line_0", "Line_33");
	        System.out.println(similarity33);
	        double similarity34 = vec.similarity("Line_0", "Line_34");
	        System.out.println(similarity34);
	        double similarity35 = vec.similarity("Line_0", "Line_35");
	        System.out.println(similarity35);
	        double similarity36 = vec.similarity("Line_0", "Line_36");
	        System.out.println(similarity36);
	        double similarity37 = vec.similarity("Line_0", "Line_37");
	        System.out.println(similarity37);
	        double similarity38 = vec.similarity("Line_0", "Line_38");
	        System.out.println(similarity38);
	        double similarity39 = vec.similarity("Line_0", "Line_39");
	        System.out.println(similarity39);
	        double similarity40 = vec.similarity("Line_0", "Line_40");
	        System.out.println(similarity40);
	        double similarity41 = vec.similarity("Line_0", "Line_41");
	        System.out.println(similarity41);
	        double similarity42 = vec.similarity("Line_0", "Line_42");
	        System.out.println(similarity42);
	        double similarity43 = vec.similarity("Line_0", "Line_43");
	        System.out.println(similarity43);
	        double similarity44 = vec.similarity("Line_0", "Line_44");
	        System.out.println(similarity44);
	        double similarity45 = vec.similarity("Line_0", "Line_45");
	        System.out.println(similarity45);
	        double similarity46 = vec.similarity("Line_0", "Line_46");
	        System.out.println(similarity46);
	        double similarity47 = vec.similarity("Line_0", "Line_47");
	        System.out.println(similarity47);
	        double similarity48 = vec.similarity("Line_0", "Line_48");
	        System.out.println(similarity48);
	        double similarity49 = vec.similarity("Line_0", "Line_49");
	        System.out.println(similarity49);
	        double similarity50 = vec.similarity("Line_0", "Line_50");
	        System.out.println(similarity50);
	        double similarity51 = vec.similarity("Line_0", "Line_51");
	        System.out.println(similarity51);
	        double similarity52 = vec.similarity("Line_0", "Line_52");
	        System.out.println(similarity52);
	        double similarity53 = vec.similarity("Line_0", "Line_53");
	        System.out.println(similarity53);
	        double similarity54 = vec.similarity("Line_0", "Line_54");
	        System.out.println(similarity54);
	        double similarity55 = vec.similarity("Line_0", "Line_55");
	        System.out.println(similarity55);
	        double similarity56 = vec.similarity("Line_0", "Line_56");
	        System.out.println(similarity56);
	        double similarity57 = vec.similarity("Line_0", "Line_57");
	        System.out.println(similarity57);
	        double similarity58 = vec.similarity("Line_0", "Line_58");
	        System.out.println(similarity58);
	        double similarity59 = vec.similarity("Line_0", "Line_59");
	        System.out.println(similarity59);
	        double similarity60 = vec.similarity("Line_0", "Line_60");
	        System.out.println(similarity60);
	        double similarity61 = vec.similarity("Line_0", "Line_61");
	        System.out.println(similarity61);
	        double similarity62 = vec.similarity("Line_0", "Line_62");
	        System.out.println(similarity62);
	        double similarity63 = vec.similarity("Line_0", "Line_63");
	        System.out.println(similarity63);
	        double similarity64 = vec.similarity("Line_0", "Line_64");
	        System.out.println(similarity64);
	        double similarity65 = vec.similarity("Line_0", "Line_65");
	        System.out.println(similarity65);
	        double similarity66 = vec.similarity("Line_0", "Line_66");
	        System.out.println(similarity66);
	        double similarity67 = vec.similarity("Line_0", "Line_67");
	        System.out.println(similarity67);
	        double similarity68 = vec.similarity("Line_0", "Line_68");
	        System.out.println(similarity68);
	        double similarity69 = vec.similarity("Line_0", "Line_69");
	        System.out.println(similarity69);
	        double similarity70 = vec.similarity("Line_0", "Line_70");
	        System.out.println(similarity70);
	        double similarity71 = vec.similarity("Line_0", "Line_71");
	        System.out.println(similarity71);
	        double similarity72 = vec.similarity("Line_0", "Line_72");
	        System.out.println(similarity72);
	        double similarity73 = vec.similarity("Line_0", "Line_73");
	        System.out.println(similarity73);
	        p.close();
	    }
	}



