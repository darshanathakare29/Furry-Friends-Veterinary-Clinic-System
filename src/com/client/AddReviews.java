package com.client;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

/**
 * Servlet implementation class AddReviews
 */
@WebServlet("/AddReviews")
public class AddReviews extends HttpServlet {
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rid = 0;
		String clientName=request.getParameter("clientName");
		String review = request.getParameter("review");
		double rating = Double.parseDouble(request.getParameter("rating"));
		System.out.println("Ratings "+rating);
		HttpSession hs=request.getSession();
		try {
			int addQuestion = DatabaseConnection.insertUpdateFromSqlQuery("insert into tblreview(review_id,user,content,ratings)values('"
							+ rid + "','" + clientName + "','" + review + "','" + rating + "')");
			if (addQuestion > 0) {
				String message = "Reviews submitted successfully.";
				hs.setAttribute("review", message);
				response.sendRedirect("client-add-review.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
