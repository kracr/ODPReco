<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/resources/css/styling.css">
<script type="text/javascript" src="/resources/js/app.js"> 
</script>
<title>ODPReco</title>
</head>
<body>
<h1> ODPReco</h1>
<hr>
<h3>The ODP Recommendations by our tool are-</h3>
<%
BufferedReader reader = new BufferedReader(new FileReader("odpReco_results"));


String line123=reader.readLine();

while(line123!= null){
    out.println(line123);
    out.println("<br>");
    line123=reader.readLine();
}

%>

</br>
<hr>
<h4>Kindly fill the form for your valuable feedback.</h4>
<a href="https://forms.gle/47XeSMsK8K5thxyh6/">Feedback Form</a>
<br>
<br>
<div id="footer">
<hr>
<p>&copy;KRACR LAB, IIIT Delhi</p>
</div>
</body>
</html>