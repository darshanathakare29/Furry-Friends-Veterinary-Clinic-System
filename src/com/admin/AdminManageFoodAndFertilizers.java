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
 * Servlet implementation class AdminManageFoodAndFertilizers
 */
@WebServlet("/AdminManageFoodAndFertilizers")
public class AdminManageFoodAndFertilizers extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=0;
		String fname=request.getParameter("fname");
		String category=request.getParameter("category");
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		String description=request.getParameter("description");
		int amount=Integer.parseInt(request.getParameter("amount"));
		HttpSession hs=request.getSession();
		try {
			int addFoodOrFertilizer=DatabaseConnection.insertUpdateFromSqlQuery("insert into tblfoodproducts(id,food_name,category,quantity,description,amount)values('"+id+"','"+fname+"','"+category+"','"+quantity+"','"+description+"','"+amount+"')");
			if(addFoodOrFertilizer>0) {
				String message="Added sucessfully.";
				hs.setAttribute("addedMessage", message);
				response.sendRedirect("manage-food-products-and-fertilizers.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
