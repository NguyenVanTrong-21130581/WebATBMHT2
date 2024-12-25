package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.Dao;
import model.Account;

/**
 * Servlet implementation class AddControl
 */
@WebServlet(name = "AddControl", urlPatterns = {"/add"})
public class AddControl extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	
		String name = request.getParameter("name");
		String image = request.getParameter("image");
		String pimage2 = request.getParameter("image2");
        String pimage3 = request.getParameter("image3");
        String pimage4 = request.getParameter("image4");
        String pmodel = request.getParameter("model");
        String pcolor = request.getParameter("color");
        String pdelivery = request.getParameter("delivery");
		String price = request.getParameter("price");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		HttpSession session = request.getSession();
		Account acc = (Account) session.getAttribute("acc");
		int sid = acc.getId();
		Dao dao = new Dao();
		
		dao.insertProduct(name, image, price, title, description, category, sid, pmodel, pcolor, pdelivery, pimage2, pimage3, pimage4);

		request.getRequestDispatcher("manager").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	private String extractFileName(Part part) {
//
//		String contentDisp = part.getHeader("content-disposition");
//
//		String[] items = contentDisp.split(";");
//
//		for (String s : items) {
//
//		if (s.trim().startsWith("filename")) {
//
//		return s.substring(s.indexOf("=") + 2, s.length()-1);
//
//		}
//
//		}
//
//		return "";
//
//		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);

		// gets absolute path of the web application
//
//		String appPath = request.getServletContext().getRealPath("");
//		Part path = request.getPart(image);
//		// constructs path of the directory to save uploaded file
//
//		String savePath = appPath + File.separator + SAVE_DIR;
//		String Path = savePath + image;
//		// creates the save directory if it does not exists
//
//		File fileSaveDir = new File(savePath);
//		path = request.getPart("image");
//		savePath = appPath + File.separator + SAVE_DIR + "/";
//		Path = savePath + extractFileName(path);
//		
//		fileSaveDir = new File(Path);
//		String imgPath = "";
//		
//		if (!fileSaveDir.exists()) {
//			System.out.print("ko up file");
//		fileSaveDir.mkdir();
//
//		}else {
//			System.out.print("up file");
//			imgPath = extractFileName(path);
//			for (Part part : request.getParts()) {
//				fileName = extractFileName(part);
//				try {
//					part.write(savePath + File.separator + fileName);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//		}
//		request.setAttribute("mess",
//		"Upload has been done successfully!");
//		
				
	}
	 @Override
	    public String getServletInfo() {
	        return "Short description";
	    }// </editor-fold>
}
