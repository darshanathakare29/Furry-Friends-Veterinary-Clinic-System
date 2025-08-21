package com.farmer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

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
 * Servlet implementation class AskPetsDiseaseDaubts
 */
@WebServlet("/AskPetsDiseaseDaubts")
public class AskPetsDiseaseDaubts extends HttpServlet {
	private final String UPLOAD_DIRECTORY = "F:/workspace/OnlineVeternaryClinicSystem/WebContent/uploads/";
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				String farmerName=null;
				String animalName = null;
				String animalType = null;
				String description =null;
				String diseaseName=null;
				String imageName=null;
				
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						imageName = new File(item.getName()).getName();
						item.write(new File(UPLOAD_DIRECTORY + File.separator + imageName));
						
						FileItem fName = (FileItem) multiparts.get(0);
						farmerName = fName.getString();
						System.out.println("farmerName"+farmerName);
						
						FileItem aName = (FileItem) multiparts.get(1);
						animalName = aName.getString();	
						System.out.println("animalName"+animalName);
						
						FileItem aType = (FileItem) multiparts.get(2);
						animalType = aType.getString();
						System.out.println("animalType"+animalType);
						
						FileItem descp = (FileItem) multiparts.get(3);
						description = descp.getString();
						System.out.println("description"+description);
						
						FileItem dName = (FileItem) multiparts.get(4);
						diseaseName = dName.getString();
						System.out.println("diseaseName"+diseaseName);
								
					}
				}
				try {
					int id = 0;
					String imagePath = UPLOAD_DIRECTORY + imageName;
					int i = DatabaseConnection.insertUpdateFromSqlQuery("insert into tblsymptoms(id,farmer_name,disease_name,disease_image_name,disease_image_path,description,animal_name,animal_type) values('" + id+ "','" + farmerName + "','"+diseaseName+"','" + imageName + "','" + imagePath + "','"+description+"','"+animalName+"','"+animalType+"')");
					if (i > 0) {
						String success = "Daubts asking successfully.";
						session.setAttribute("addedMessage", success);
						response.sendRedirect("farmer-ask-daubts-about-animal-disease.jsp");
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
