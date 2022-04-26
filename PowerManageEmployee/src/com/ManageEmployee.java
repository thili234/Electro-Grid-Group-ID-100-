package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Employee;

@Path("/Employee")
public class ManageEmployee {
	Employee EmployeeObj = new Employee();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readEmployee() {
		return EmployeeObj.readEmployee();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertEmployee(@FormParam("employeename") String employeename,			
	 @FormParam("phonenumber") String phonenumber,
	 @FormParam("Employeetype") String Employeetype,
	 @FormParam("NIC") String NIC)
	{
	 String output = EmployeeObj.insertEmployee(employeename, phonenumber, Employeetype, NIC);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEmployee(String employeeData)
	{
	//Convert the input string to a JSON object
	 JsonObject EmployeeObject = new JsonParser().parse(employeeData).getAsJsonObject();
	//Read the values from the JSON object
	 String employeeID = EmployeeObject.get("employeeID").getAsString();
	 String employeename = EmployeeObject.get("employeename").getAsString();
	 String phonenumber = EmployeeObject.get("phonenumber").getAsString();
	 String Employeetype = EmployeeObject.get("Employeetype").getAsString();
	 String NIC = EmployeeObject.get("NIC").getAsString();
	 String output = EmployeeObj.updateEmployee(employeeID, employeename, phonenumber, Employeetype, NIC);
	return output;
	} 
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEmployee(String employeeData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(employeeData, "", Parser.xmlParser());

	//Read the value from the element 
	 String employeeID = doc.select("employeeID").text();
	 String output = EmployeeObj.deleteEmployee(employeeID);
	return output;
	}
}
 