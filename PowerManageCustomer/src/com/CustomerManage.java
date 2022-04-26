package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Customer;

@Path("/Customer")
public class CustomerManage {
	Customer CustomerObj = new Customer();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCustomer() {
		return CustomerObj.readCustomer();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomer(
	 @FormParam("name") String name,			
	 @FormParam("address") String address,
	 @FormParam("email") String email,
	 @FormParam("contactnumber") String contactnumber)
	{
	 String output = CustomerObj.insertCustomer(name, address, email, contactnumber);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomer(String customerData)
	{
	//Convert the input string to a JSON object
	 JsonObject registerObject = new JsonParser().parse(customerData).getAsJsonObject();
	//Read the values from the JSON object
	 String ID = registerObject.get("ID").getAsString();
	 String name = registerObject.get("name").getAsString();
	 String address = registerObject.get("address").getAsString();
	 String email = registerObject.get("email").getAsString();
	 String contactnumber = registerObject.get("contactnumber").getAsString();
	 String output = CustomerObj.updateCustomer(ID, name, address, email, contactnumber);
	return output;
	} 
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(String customerData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(customerData, "", Parser.xmlParser());

	//Read the value from the element 
	 String ID = doc.select("ID").text();
	 String output = CustomerObj.deleteCustomer(ID);
	return output;
	}
	
}
