package com.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

/**
 * Servlet implementation class AssignDoctorToFarmerPet
 */
@WebServlet("/AssignDoctorToFarmerPet")
public class AssignDoctorToFarmerPet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		String doctorName=request.getParameter("doctorName");
		HttpSession hs=request.getSession();
		try {
			int assignDoctor=DatabaseConnection.insertUpdateFromSqlQuery("update tblsymptoms set doctor_name='"+doctorName+"' where id='"+id+"'");
			if(assignDoctor>0) {
				String message="Assign doctor successfully.";
				hs.setAttribute("addedMessage", message);
				response.sendRedirect("admin-view-farmers-animals-disease.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
