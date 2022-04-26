package com;

import model.Payment;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")
public class ManagePayment {
	Payment PaymentObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		return PaymentObj.readPayment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("BillID") String BillID, 
			@FormParam("cardID") String cardID,
			@FormParam("cardType") String cardType,
			@FormParam("Totalamount") String Totalamount) {
		String output = PaymentObj.insertPayment(BillID,cardID, cardType, Totalamount);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updatePayment(String paymentData) {
		
		JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
		String payment_ID = paymentObject.get("payment_ID").getAsString();
		String BillID = paymentObject.get("BillID").getAsString();
		String cardID = paymentObject.get("cardID").getAsString();
		String cardType = paymentObject.get("cardType").getAsString();
		String Totalamount = paymentObject.get("Totalamount").getAsString();
		
		String output = PaymentObj.updatePayment(payment_ID, BillID, cardID, cardType, Totalamount);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData) {
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

		String payment_ID = doc.select("payment_ID").text();
		String output = PaymentObj.deletePayment(payment_ID);
		return output;
	}
}
