package com.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

/**
 * Servlet implementation class BuyAnimal
 */
@WebServlet("/BuyAnimal")
public class BuyAnimal extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession hs = request.getSession();
		int pid = 0;
		String card_holder_name = request.getParameter("dholderName");
		String cardNumber = request.getParameter("cardNumber");
		String emonth = request.getParameter("emonth");
		String eyear = request.getParameter("eyear");
		String expiration_month_year = emonth + " " + eyear;
		int payment = Integer.parseInt(request.getParameter("animalPrice"));
		int cvv = Integer.parseInt(request.getParameter("cvv"));
		String client_name = (String) hs.getAttribute("client_name");
		String client_mobile = (String) hs.getAttribute("client_mobile");
		String animalId = request.getParameter("animalId");
		String animalName = request.getParameter("animalName");
		String farmerName = request.getParameter("farmerName");
		
		try {
			int animalPayment=DatabaseConnection.insertUpdateFromSqlQuery("insert into tblpayment(pid,payment,card_holder_name,debit_card_number,expiration_month_year,cvv,client_name,client_mobile,animal_id,animal_name,farner_name)values('"+pid+"','"+payment+"','"+card_holder_name+"','"+cardNumber+"','"+expiration_month_year+"','"+cvv+"','"+client_name+"','"+client_mobile+"','"+animalId+"','"+animalName+"','"+farmerName+"')");
			int animalStatus=DatabaseConnection.insertUpdateFromSqlQuery("update tblanimal set status=1 where animal_id='"+animalId+"'");
			if(animalStatus>0) {
				String message="Animal buys sucessfully.";
				hs.setAttribute("addedMessage", message);
				response.sendRedirect("client-buy-animal.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
