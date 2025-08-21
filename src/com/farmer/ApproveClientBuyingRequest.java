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
 * Servlet implementation class ApproveClientBuyingRequest
 */
@WebServlet("/ApproveClientBuyingRequest")
public class ApproveClientBuyingRequest extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int requestId=Integer.parseInt(request.getParameter("requestId"));
		HttpSession hs=request.getSession();
		try {
			int approveRequest=DatabaseConnection.insertUpdateFromSqlQuery("update tblbuyrequest set status='Approved' where request_id='"+requestId+"'");
			if(approveRequest>0) {
				String message="Farmer approve request sucessfully.";
				hs.setAttribute("addedMessage", message);
				response.sendRedirect("farmer-view-client-buying-request.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
