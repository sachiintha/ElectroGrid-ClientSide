<%@page import="model.RedNotice"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if (request.getParameter("itemCode") != null) 
{ 
	RedNotice RedNoticeObj = new RedNotice(); 
 String stsMsg = ""; 
//Insert--------------------------
if (request.getParameter("hidAccountCodeSave") == "") 
 { 
 stsMsg = RedNoticeObj.insertRedNotice(request.getParameter("AccountID"), 
 request.getParameter("CustomerName"), 
 request.getParameter("Charges"), 
 request.getParameter("Duedate")); 
 } 
else//Update----------------------
 { 
 stsMsg = RedNoticeObj.updateRedNotice(request.getParameter("hidAccountCodeSave"), 
 request.getParameter("AccountID"), 
 request.getParameter("CustomerName"), 
 request.getParameter("Charges"), 
 request.getParameter("Duedate")); 
 } 
 session.setAttribute("statusMsg", stsMsg); 
} 
//Delete-----------------------------
if (request.getParameter("hidAccountCodeDelete") != null) 
{ 
	RedNotice RedNoticeObj = new RedNotice(); 
 String stsMsg = 
		 RedNoticeObj.deleteRedNotice(request.getParameter("hidAccountCodeDelete")); 
 session.setAttribute("statusMsg", stsMsg); 
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>RedNotice Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>RedNotice Management </h1>
<form id="formRedNotice" name="formRedNotice">
 Account ID: 
 <input id="AccountID" name="AccountID" type="text" 
 class="form-control form-control-sm">
 <br> Customer Name: 
 <input id="CustomerName" name="CustomerName" type="text" 
 class="form-control form-control-sm">
 <br> Charges: 
 <input id="Charges" name="Charges" type="text" 
 class="form-control form-control-sm">
 <br> Due date: 
 <input id="Duedate" name="Duedate" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidAccountCodeSave" 
 name="hidAccountCodeSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 RedNotice RedNoticeObj = new RedNotice(); 
 out.print(RedNoticeObj.readRedNotice()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
