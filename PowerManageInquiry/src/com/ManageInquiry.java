package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Inquiry;

@Path("/Inquiry")
public class ManageInquiry{
	Inquiry InquiryObj = new Inquiry();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readInquiry() {
		return InquiryObj.readInquiry();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertInquiry(
	 @FormParam("username") String username,			
	 @FormParam("useremail") String useremail,
	 @FormParam("inquirytype") String inquirytype,
	 @FormParam("reason") String reason)
	{
	 String output = InquiryObj.insertInquiry(username, useremail, inquirytype, reason);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateInquiry(String inquiryData)
	{
	//Convert the input string to a JSON object
	 JsonObject inquiryObject = new JsonParser().parse(inquiryData).getAsJsonObject();
	//Read the values from the JSON object
	 String ID = inquiryObject.get("ID").getAsString();
	 String username = inquiryObject.get("username").getAsString();
	 String useremail = inquiryObject.get("useremail").getAsString();
	 String inquirytype = inquiryObject.get("inquirytype").getAsString();
	 String reason = inquiryObject.get("reason").getAsString();
	 String output = InquiryObj.updateInquiry(ID, username, useremail, inquirytype, reason);
	return output;
	} 
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInquiry(String inquiryData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(inquiryData, "", Parser.xmlParser());

	//Read the value from the element
	 String ID = doc.select("ID").text();
	 String output = InquiryObj.deleteInquiry(ID);
	return output;
	}
}
