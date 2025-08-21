package com.farmer;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.connection.DatabaseConnection;

/**
 * Servlet implementation class FarmerAddAnimalDetailsToSell
 */
@WebServlet("/FarmerAddAnimalDetailsToSell")
public class FarmerAddAnimalDetailsToSell extends HttpServlet {

	private final String UPLOAD_DIRECTORY = "F:/workspace/OnlineVeternaryClinicSystem/WebContent/uploads/";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				String animalId = null;
				String animalName = null;
				String animalType = null;
				String description = null;
				String price = null;
				String contact = null;
				String farmerName = null;
				String imageName = null;

				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						imageName = new File(item.getName()).getName();
						item.write(new File(UPLOAD_DIRECTORY + File.separator + imageName));

						FileItem aId = (FileItem) multiparts.get(0);
						animalId = aId.getString();
						System.out.println("animalId" + animalId);

						FileItem aName = (FileItem) multiparts.get(1);
						animalName = aName.getString();
						System.out.println("animalName" + animalName);

						FileItem aType = (FileItem) multiparts.get(2);
						animalType = aType.getString();
						System.out.println("animalType" + animalType);

						FileItem descp = (FileItem) multiparts.get(3);
						description = descp.getString();
						System.out.println("description" + description);

						FileItem aprice = (FileItem) multiparts.get(4);
						price = aprice.getString();
						System.out.println("price" + price);

						FileItem scontact = (FileItem) multiparts.get(5);
						contact = scontact.getString();
						System.out.println("contact" + contact);
						
						FileItem fName = (FileItem) multiparts.get(6);
						farmerName = fName.getString();
						System.out.println("farmerName" + farmerName);

					}
				}
				try {
					int id = 0;
					int status = 0;
					String imagePath = UPLOAD_DIRECTORY + imageName;
					int i = DatabaseConnection.insertUpdateFromSqlQuery(
							"insert into tblanimal(animal_id,animal_type,animal_name,description,price,image,image_path,contact,farmer_name,status) values('"
									+ animalId + "','" + animalType + "','" + animalName + "','" + description + "','"
									+ price + "','" + imageName + "','" + imagePath + "','" + contact + "','"+farmerName+"','"+status+"')");
					if (i > 0) {
						String success = "Farmer added animal to sell successfully.";
						session.setAttribute("addedMessage", success);
						response.sendRedirect("farmer-add-animal-details-to-sell.jsp");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				request.setAttribute("message", "File Upload Failed due to " + ex);
			}

		} else {
			request.setAttribute("message", "Sorry this Servlet only handles file upload request");
		}
	}

}
