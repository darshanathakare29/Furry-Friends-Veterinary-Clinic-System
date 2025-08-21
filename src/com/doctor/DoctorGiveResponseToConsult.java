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
 * Servlet implementation class DoctorGiveResponseToConsult
 */
@WebServlet("/DoctorGiveResponseToConsult")
public class DoctorGiveResponseToConsult extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int status = 0;
		String responseId = request.getParameter("responseId");
		String farmer_name = request.getParameter("farmer_name");
		String doctor_id = request.getParameter("doctor_id");
		String responsee = request.getParameter("response");
		int fees = Integer.parseInt(request.getParameter("fees"));
		HttpSession hs=request.getSession();
		try {
			int giveResponses = DatabaseConnection.insertUpdateFromSqlQuery("insert into tblresponse(response_id,farmer_name,doctor_id,response,fees,status)values('" + responseId + "','"+ farmer_name + "','" + doctor_id + "','" + responsee + "','"+fees+"','"+status+"')");
			if (giveResponses > 0) {
				String message="Response submitted successfully.";
				hs.setAttribute("addedMessage", message);
				response.sendRedirect("doctor-give-response-of-consult.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
