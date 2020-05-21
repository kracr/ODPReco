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

	var desfile = fso.OpenTextFile("C:\\Users\\Admin\\workspace\\spring-boot-application\\ontology_description",2,true);
	
    var description = document.getElementById('description').value; 

    desfile.WriteLine(description);
	desfile.close();
	alert("Please fill the feedback form after recommendations");
	alert("ODPReco is compiling the results, please wait for a minute or two.Kindly don't refresh");
}

function extractFilename(s){ 
	  // returns string containing everything from the end of the string 
	  //   that is not a back/forward slash or an empty string on error
	  //   so one can check if return_value===''
	  typeof s==='string' && (s=s.match(/[^\\\/]+$/)) && s[0] || '';
	  var changeowl=document.getElementById("owlpath").value;
	  document.getElementById("owlpath").value=s;
	  
	} 