package com.recommender;

//Contributed by Maleeha

import java.io.File;
import java.io.FileFilter;

public class TextFileFilter implements FileFilter {
	
	//used for lucene so that it accepts only .txt files
	  public boolean accept(File pathname) {
	      return pathname.getName().toLowerCase().endsWith(".txt");
	   }
	}

