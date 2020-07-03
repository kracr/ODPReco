package com.recommender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;



public class LuceneTester {
	
	 String indexDir = System.getProperty("user.dir")+ "/resources/home_index";
	   String dataDir = System.getProperty("user.dir")+ "/resources/home_files";
	   Indexer indexer;
	   Searcher searcher;
	   static String s_check=" ";
	   int lines=0;
	   String s_rec=" ";
	 

	   @SuppressWarnings("resource")
	   
	public void lucene_recommender() throws OWLOntologyCreationException, OWLOntologyStorageException, IOException {
	      LuceneTester tester;
	      MergeOntologyFiles mo=new MergeOntologyFiles();
			mo.mergeFiles();
	      BooleanQuery.setMaxClauseCount( Integer.MAX_VALUE );
	   
	       
		 	 //Reading the file as string
		  BufferedReader br_read=new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/lucene_ontology"));
		 	  
		 	  String line_read=br_read.readLine();
		 	

		 		 if(line_read != null) 
		           { 
		               s_check=s_check + line_read; 
		               line_read = br_read.readLine(); 
		              
		           } 	 	 
		 	 
		 	s_check=s_check.replaceAll("http://[^>]*/",""); //removing the IRI of the ontology
			s_check=s_check.replaceAll("https://[^>]*/","");
			s_check = s_check.replaceAll("\\[", "").replaceAll("\\]","");
			s_check = s_check.replaceAll("\\(", "").replaceAll("\\)","");
			s_check = s_check.replaceAll("\\<", "").replaceAll("\\>","");
			s_check=s_check.replaceAll(":", " ");
			s_check=s_check.replaceAll(";", " ");
			s_check=s_check.replaceAll("'", " ");
			s_check=s_check.replaceAll("\"", " ");
			
			s_check=s_check.replaceAll("SubClassOf", " ");
			s_check=s_check.replaceAll("xsd", " ");
			s_check=s_check.replaceAll("rdfs", " ");
			s_check=s_check.replaceAll("ObjectPropertyRange", " ");
			
			s_check=s_check.replaceAll("ObjectPropertyDomain", " ");
			s_check=s_check.replaceAll("Annotation", " ");
			s_check=s_check.replaceAll("ObjectAllValuesFrom", " ");
			s_check=s_check.replaceAll("DataSomeValuesFrom", " ");
			
			
			s_check=s_check.replaceAll("anyURI", " ");
			s_check=s_check.replaceAll("double", " ");
			s_check=s_check.replaceAll("String", " ");
			s_check=s_check.replaceAll("int", " ");
			s_check=s_check.replaceAll("#", " ");
			s_check=s_check.replaceAll("\\^", " ");
			s_check=s_check.replaceAll("\\*", " ");
			s_check=s_check.replaceAll("\\?", " ");
			

		 	
	      
	      try {
	         tester = new LuceneTester();
	         tester.createIndex();
	         tester.search(s_check);
	      }
	      catch (IOException e) {
	         e.printStackTrace();
	      } catch (ParseException e) {
	         e.printStackTrace();
	      }
	   }

	   private void createIndex() throws IOException {
	      indexer = new Indexer(indexDir);
	      int numIndexed;
	      long startTime = System.currentTimeMillis();  
	      numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
	      long endTime = System.currentTimeMillis();
	      indexer.close();
	      //System.out.println(numIndexed+" File indexed, time taken: "
	        // +(endTime-startTime)+" ms");    
	   }

	   private void search(String searchQuery) throws IOException, ParseException {
	      searcher = new Searcher(indexDir);
	      long startTime = System.currentTimeMillis();
	      TopDocs hits = searcher.search(searchQuery);
	      long endTime = System.currentTimeMillis();
	   
	
		 PrintStream preco_lucene=new PrintStream(System.getProperty("user.dir")+ "/resources/lucene_recommender");	 
		  
	      for(ScoreDoc scoreDoc : hits.scoreDocs) {
	         Document doc = searcher.getDocument(scoreDoc);
	                        String s=doc.get(LuceneConstants.FILE_NAME);
	                        s=s.replaceAll(".txt","");
	                        preco_lucene.println(s);
	         // preco_lucene.println(doc.get(LuceneConstants.FILE_NAME)+ " with score " + scoreDoc.score); 
	           //lines++;- for keeping count of files that can be used for normalising scores.
	              
	      } 
	      
	      //reading the text file into string array
	      
	      BufferedReader rec_reader=new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/lucene_recommender"));
	      List<String> lines = new ArrayList<String>();
	        String line = null;
	         
	        while ((line = rec_reader.readLine()) != null)
	        {
	            lines.add(line);
	        }
	         
	        rec_reader.close();
    
	      //mapping with IRIs so that IRI is also present alongwith ODP name
   
		 	String c=" ";
		 	 PrintStream preco_luceneIRI=new PrintStream(System.getProperty("user.dir")+ "/resources/lucene_recowithIRI");	 
			  
			for(int kl=0;kl<lines.size();kl++){
			   BufferedReader bodp=new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources/odp_list"));
			   String line_odp=bodp.readLine();

				 while(line_odp != null) 
		 			 
	           { 
			      if(line_odp.contains(lines.get(kl))){

                  c=line_odp;
                  preco_luceneIRI.println(c); 
                  
                    line_odp=bodp.readLine();
			 }
			      else{							 
				 line_odp=bodp.readLine();
			 }
         } 
				
		 	}

	    	      
	      
	      // to delete the files from index directory in order to avoid repetition
	      String path=System.getProperty("user.dir")+ "/resources/home_index";
	        File file = new File(path);
	        File[] files = file.listFiles(); 
	        for (File f:files) 
	        {if (f.isFile() && f.exists()) 
	            { f.delete();	           

	            } }
	      

	        
	      }  
	   }
	
	


