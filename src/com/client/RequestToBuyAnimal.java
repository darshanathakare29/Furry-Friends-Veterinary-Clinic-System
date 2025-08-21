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
 * Servlet implementation class RequestToBuyAnimal
 */
@WebServlet("/RequestToBuyAnimal")
public class RequestToBuyAnimal extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int requestId=0;
		String status="Pending";
		String animalId=request.getParameter("animalId");
		String farmerName=request.getParameter("farmerName");
		String client_name=request.getParameter("client_name");
		String mobile=request.getParameter("mobile");
		String description=request.getParameter("description");
		HttpSession hs=request.getSession();
		
		try {
			int requestToBuyAnimal=DatabaseConnection.insertUpdateFromSqlQuery("insert into tblbuyrequest(request_id,client_name,client_mobile,animal_id,farmer_name,description,status)values('"+requestId+"','"+client_name+"','"+mobile+"','"+animalId+"','"+farmerName+"','"+description+"','"+status+"')");
			if(requestToBuyAnimal>0) {
				String message="Post request send to buy animal sucessfully. ";
				hs.setAttribute("addedMessage", message);
				response.sendRedirect("client-post-request-to-buy.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
