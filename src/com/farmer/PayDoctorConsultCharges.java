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
 * Servlet implementation class PayDoctorConsultCharges
 */
@WebServlet("/PayDoctorConsultCharges")
public class PayDoctorConsultCharges extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		int pid = 0;
		String responseId = request.getParameter("responseId");
		String farmerName = request.getParameter("farmerName");
		String doctorName = request.getParameter("doctorName");
		int doctorFees = Integer.parseInt(request.getParameter("doctorFees"));
		String card_holder_name = request.getParameter("dholderName");
		String cardNumber = request.getParameter("cardNumber");
		String emonth = request.getParameter("emonth");
		String eyear = request.getParameter("eyear");
		String expiration_month_year = emonth + " " + eyear;
		int cvv = Integer.parseInt(request.getParameter("cvv"));
		
		try {
			int payDoctorCharges=DatabaseConnection.insertUpdateFromSqlQuery("insert into tbldoctorfees(pid,response_id,farmerName,doctorName,doctorFees,card_holder_name,debit_card_number,expiration_month_year,cvv)values('"+pid+"','"+responseId+"','"+farmerName+"','"+doctorName+"','"+doctorFees+"','"+card_holder_name+"','"+cardNumber+"','"+expiration_month_year+"','"+cvv+"')");
			int responseStatus=DatabaseConnection.insertUpdateFromSqlQuery("update tblresponse set status=1 where response_id='"+responseId+"'");
			if(payDoctorCharges>0) {
				String message="Doctor consult fees pay sucessfully.";
				hs.setAttribute("addedMessage", message);
				response.sendRedirect("view-response-of-consult-doctors.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
