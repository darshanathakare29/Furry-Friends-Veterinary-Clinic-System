package com.doctor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

/**
 * Servlet implementation class EditDoctorProfile
 */
@WebServlet("/EditDoctorProfile")
public class EditDoctorProfile extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String doctor_name=request.getParameter("doctor_name");
		String department=request.getParameter("department");
		int fees=Integer.parseInt(request.getParameter("fees"));
		HttpSession session=request.getSession();
		try {
			int editProfile=DatabaseConnection.insertUpdateFromSqlQuery("update tbldoctor set doctor_name='"+doctor_name+"',department='"+department+"',doctor_fees='"+fees+"' where uname='"+session.getAttribute("uname")+"'");
			if(editProfile>0) {
				String message="Profile updated successfully.";
				session.setAttribute("profile-update", message);
				response.sendRedirect("doctor-profile.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
