<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/resources/css/styling.css">
<script type="text/javascript" src="/resources/js/execute.js">
</script>
<title>ODPReco</title>
</head>
<body>
<h1>ODPReco- A tool for ODP Recommendations</h1>
<h2>ODPReco is a tool that recommends the ODPs for an ontology. Thus, it helps in making an ontology modular.</h2>
Upload your owl file (mandatory to upload owl file)along with the competency questions and a brief description about that ontology inorder to know the ODP recommendations by our tool.
After the ODPs have been recommended, kindly fill the <b>google form</b> for your valuable feedback.
</br>
<i><b>Note:</b>ODPReco will be available as a command-line tool and a Protege plug-in in future. This is for getting user feedback only!</i>
</br>
<b>Please use Google Chrome or Firefox browser only!</b>
<hr>
<h3>Kindly enter the details of the ontology in order to know the recommendations:-</h3>
<div class="form">
<form action="recommender" method="post" onsubmit="return (validate() && writeToForm(this))" enctype="multipart/form-data">
   
      
            <label>Enter the description of the ontology: </br>
            </br>
            <textarea name="description" rows="5" cols ="50"></textarea> </label>  </br>   
             </br>
             </br> 
            <label>Enter the competency questions of the ontology: </br>
            </br>
            <textarea name="cq" rows="5" cols="50"></textarea></label> </br>
     </br>
     </br>
           <label>Enter the path of the OWL file for the given ontology:  </br>
           </br>
           
           <input type="file" id="owlpath" accept=".owl" name="owlpath" size="100" ></label>  </br>          
           </br>  
           </br>     
           </br>      
            <input type="submit" value="Recommend ODPs" />
  
            
  </br>
  </br>
  </form>
</div>
<hr>
<div class="form">
<form action="odpList" method="get" >
<h4>To know about the ODPs, kindly click on the below ODPList button</h4> </br>
<input type="submit" value="ODPList"  />
</form>
</div>
</br> 
</br>
<div id="footer">
<hr>
<p>&copy;KRACR LAB, IIIT Delhi</p>
</div>
</body>
</html>
