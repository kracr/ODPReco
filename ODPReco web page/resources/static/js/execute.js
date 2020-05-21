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
	alert("Please fill the feedback form after recommendations");
	alert("ODPReco is compiling the results, please wait for a minute or two.Kindly don't refresh");
}

