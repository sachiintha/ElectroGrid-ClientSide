
$(document).ready(function() 
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateRedNoticeForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidAccountCodeSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "RedNoticeAPI", 
 type : type, 
 data : $("#formItem").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onRedNoticeSaveComplete(response.responseText, status); 
 } 
 }); 
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
$("#hidAccountCodeSave").val($(this).data("AccountCode")); 
 $("#AccountID").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#CustomerName").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#Charges").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#Duedate").val($(this).closest("tr").find('td:eq(3)').text()); 
});

$(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "RedNoticeAPI", 
 type : "DELETE", 
 data : "AccountCode=" + $(this).data("AccountCode"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onRedNoticeDeleteComplete(response.responseText, status); 
 } 
 }); 
});
// CLIENT-MODEL================================================================
function validateRedNoticeForm() 
{ 
// CODE
if ($("#AccountID").val().trim() == "") 
 { 
 return "Insert RedNotice Code."; 
 } 
// NAME
if ($("#CustomerName").val().trim() == "") 
 { 
 return "Insert RedNotice Name."; 
 } 
// PRICE-------------------------------
if ($("#Charges").val().trim() == "") 
 { 
 return "Insert Charges."; 
 } 
// is numerical value
var tmpCharges = $("#Charges").val().trim(); 
if (!$.isNumeric(tmpCharges)) 
 { 
 return "Insert a numerical value for Charges."; 
 } 
// convert to decimal Charges
 $("#itemCharges").val(parseFloat(tmpCharges).toFixed(2)); 
// DESCRIPTION------------------------
if ($("#itemDuedate").val().trim() == "") 
 { 
 return "RedNotice Item Duedate."; 
 } 
return true; 
}

function onRedNoticeSaveComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
 $("#hidItemIDSave").val(""); 
 $("#formItem")[0].reset(); 
}


function onRedNoticeDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}




