package com; 
import model.RedNotice; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/RedNotice") 
public class RedNoticeService 
{ 
	RedNotice RedNoticeObj = new RedNotice(); 
 @GET
 @Path("/") 
 @Produces(MediaType.TEXT_HTML) 
 public String readRedNotice() 
  { 
  return RedNoticeObj.readRedNotice(); 
 }
 
 @POST
 @Path("/") 
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String insertRedNotice(@FormParam("AccountID") String AccountID, 
  @FormParam("CustomerName") String CustomerName, 
  @FormParam("Charges") String Charges, 
  @FormParam("Duedate") String Duedate) 
 { 
  String output = RedNoticeObj.insertRedNotice(AccountID, CustomerName, Charges, Duedate); 
 return output; 
 }

 @PUT
 @Path("/") 
 @Consumes(MediaType.APPLICATION_JSON) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String updateRedNotice(String RedNoticeData) 
 { 
 //Convert the input string to a JSON object 
  JsonObject RedNoticeObject = new JsonParser().parse(RedNoticeData).getAsJsonObject(); 
 //Read the values from the JSON object
  String AccountCode = RedNoticeObject.get("AccountCode").getAsString(); 
  String AccountID = RedNoticeObject.get("AccountID").getAsString(); 
  String CustomerName = RedNoticeObject.get("CustomerName").getAsString(); 
  String Charges = RedNoticeObject.get("Charges").getAsString(); 
  String Duedate = RedNoticeObject.get("Duedate").getAsString(); 
  String output = RedNoticeObj.updateRedNotice(AccountCode, AccountID, CustomerName, Charges, Duedate); 
 return output; 
 }

 @DELETE
 @Path("/") 
 @Consumes(MediaType.APPLICATION_XML) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String deleteRedNotice(String RedNoticeData) 
 { 
 //Convert the input string to an XML document
  Document doc = Jsoup.parse(RedNoticeData, "", Parser.xmlParser()); 
  
 //Read the value from the element <AccountCode>
  String AccountCode = doc.select("AccountID").text(); 
  String output = RedNoticeObj.deleteRedNotice(AccountCode); 
 return output; 
 }
}
