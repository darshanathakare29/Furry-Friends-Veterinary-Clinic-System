package com.farmer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

/**
 * Servlet implementation class FarmerAccountCreate
 */
@WebServlet("/FarmerAccountCreate")
public class FarmerAccountCreate extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String farmerId=request.getParameter("farmerId");
		String farmerName = request.getParameter("farmerName");
		String emailId = request.getParameter("emailId");
		String mobile = request.getParameter("mobile");
		String uname = request.getParameter("uname");
		String upass = request.getParameter("upass");
		HttpSession hs = request.getSession();
		
		try {
			int farmerAccount=DatabaseConnection.insertUpdateFromSqlQuery("insert into tblfarmer(farmer_id,farmer_name,email,mobile_no,uname,upass) values('"+farmerId+"','"+farmerName+"','"+emailId+"','"+mobile+"','"+uname+"','"+upass+"')");
			if(farmerAccount>0) {
				String message="Farmer account created successfully.";
				hs.setAttribute("success-message", message);
				response.sendRedirect("farmer-register.jsp");
			}else {
				response.sendRedirect("farmer-register.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
