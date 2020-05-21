function validate(){
	var owlpath=document.getElementById('owlpath').value;
	if(owlpath==''){
		alert("Please enter the path of OWL file");
		return false;
	}
	else{
		return true;
	}
}
function writeToForm(passForm){
	var fso = new ActiveXObject("Scripting.FileSystemObject");
	var cqfile = fso.OpenTextFile("C:\\Users\\Admin\\workspace\\spring-boot-application\\ontology_cq",2,true);
	var desfile = fso.OpenTextFile("C:\\Users\\Admin\\workspace\\spring-boot-application\\ontology_description",2,true);
	var cq = document.getElementById('cq').value;
    var description = document.getElementById('description').value; 
    cqfile.WriteLine(cq);
    desfile.WriteLine(description);
	cqfile.close();
	desfile.close();
	alert("Please fill the form after recommendations");
}
function selectedFileChanged(){
	var sop = new ActiveXObject("Scripting.FileSystemObject");
	var owlfile = sop.OpenTextFile("C:\\Users\\Admin\\workspace\\spring-boot-application\\ontology_owl",2,true);
	var owlload;
	  var fr=new FileReader(); 
      fr.onload=function(){ 
    	  owlload=fr.result; 
    	   owlfile.WriteLine(owlload);                
      }      
     var file = event.target.files[0];
     fr.readAsText(file); 	   
	    
}
