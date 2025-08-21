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
 * Servlet implementation class AccountCreate
 */
@WebServlet("/AccountCreate")
public class AccountCreate extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String clientId=request.getParameter("clientId");
		String clientName = request.getParameter("clientName");
		String emailId = request.getParameter("emailId");
		String mobile = request.getParameter("mobile");
		String uname = request.getParameter("uname");
		String upass = request.getParameter("upass");
		HttpSession hs = request.getSession();
		
		try {
			int clientAccount=DatabaseConnection.insertUpdateFromSqlQuery("insert into tblclient(client_id,client_name,email,mobile_no,uname,upass) values('"+clientId+"','"+clientName+"','"+emailId+"','"+mobile+"','"+uname+"','"+upass+"')");
			if(clientAccount>0) {
				String message="Client account created successfully.";
				hs.setAttribute("success-message", message);
				response.sendRedirect("client-register.jsp");
			}else {
				response.sendRedirect("client-register.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
