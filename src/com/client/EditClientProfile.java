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
 * Servlet implementation class EditClientProfile
 */
@WebServlet("/EditClientProfile")
public class EditClientProfile extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String client_name=request.getParameter("client_name");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		HttpSession session=request.getSession();
		try {
			int editProfile=DatabaseConnection.insertUpdateFromSqlQuery("update tblclient set client_name='"+client_name+"',email='"+email+"',mobile_no='"+mobile+"' where uname='"+session.getAttribute("uname")+"'");
			if(editProfile>0) {
				String message="Profile updated successfully.";
				session.setAttribute("profile-update", message);
				response.sendRedirect("client-profile.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
