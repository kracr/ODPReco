package com.springboot.controller;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.FunctionalSyntaxDocumentFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.ParagraphVectors_CQ;
import com.springboot.Starter_Main;
import com.springboot.FileService;
import com.springboot.Owl_Lexical;



@Controller


public class HelloController {

	@Autowired
    FileService fileService;
	
	@GetMapping("/")
	public String index() throws FileNotFoundException{
		 //calls the index jsp page
		return "index";
	}

	
	@PostMapping("/recommender") 	
	public String checkOWL(@RequestParam("owlpath")MultipartFile file,RedirectAttributes  redirectAttributes,
			 @RequestParam(value="description",defaultValue=" ")String description,
			@RequestParam(value="cq",defaultValue=" ")String cq,Model model) throws Exception{
		 
		
             fileService.uploadFile(file); //using multipart file to upload file

	
		      System.out.println(file.getOriginalFilename() + "!");
		   
				// model.addAttribute("owlpath", owlpath);
		           model.addAttribute("check", description);
		           model.addAttribute("cq", cq);	
		     		
		  /*replacing new lines and tabs with single space 
		     so that all the data is written  in a single line*/
		           
		 //for description :
		 description=description.replaceAll("[\\t|\\n|\\r]"," ");
		 BufferedWriter writer_des=new BufferedWriter(new FileWriter("ontology_description"));
		 writer_des.write(description);
		 writer_des.close();
		 
		 //for competency questions :
		 cq=cq.replaceAll("[\\t|\\n|\\r]"," ");
		 BufferedWriter writer_cq=new BufferedWriter(new FileWriter("ontology_cq"));
		 writer_cq.write(cq);
		 writer_cq.close(); 
		 
		 /*invoking the Owl_Lexical method which reads the OWL file
			and extracts the properties in respective files */
		 
		 String c=file.getOriginalFilename();
			Owl_Lexical abc=new Owl_Lexical();
			abc.owlOntology(c);
		/*calls the Starter_Main which has java files for the execution */
			Starter_Main sm=new Starter_Main();
			sm.starter();
		 
			System.out.println("done");
		
		 return "recommender";
	}
		 
	
	@RequestMapping("/odpList")
	public String odpList() {
		//calls the odpList page
		return "odpList";
	}
}

