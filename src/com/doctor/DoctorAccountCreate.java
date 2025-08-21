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
 * Servlet implementation class DoctorAccountCreate
 */
@WebServlet("/DoctorAccountCreate")
public class DoctorAccountCreate extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String doctorId=request.getParameter("doctorId");
		String doctorName = request.getParameter("doctorName");
		String department = request.getParameter("department");
		String uname = request.getParameter("uname");
		String upass = request.getParameter("upass");
		HttpSession hs = request.getSession();
		
		try {
			int clientAccount=DatabaseConnection.insertUpdateFromSqlQuery("insert into tbldoctor(doctor_id,doctor_name,department,uname,upass) values('"+doctorId+"','"+doctorName+"','"+department+"','"+uname+"','"+upass+"')");
			if(clientAccount>0) {
				String message="Doctor account created successfully.";
				hs.setAttribute("success-message", message);
				response.sendRedirect("doctor-register.jsp");
			}else {
				response.sendRedirect("doctor-register.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
