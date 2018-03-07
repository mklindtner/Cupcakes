package Servlet;

import Data.Users.User;
import Data.Users.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/Servlet001")
public class Servlet001 extends HttpServlet
{
	UserDAO udawg;
	public Servlet001() {
		udawg = new UserDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String s = request.getParameter("originPost"); //doesn't work with switch?
		User u = null;
		if ("create".equals(s)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			udawg.insertUser(username, password, email);
			u = udawg.validateUser(username, password);
			request.getSession().setAttribute("user", u);
			response.sendRedirect("userHomepage.jsp");
			return;
		}
		//fix this into create
		if("adminCreate".equals(s)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			udawg.insertUser(username, password, email);
			List<User> users = udawg.getAllUsers();
			request.getSession().setAttribute("users", users);
			response.sendRedirect("Administrator.jsp");
			return;
		}
		if("login".equals(s)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			u = udawg.validateUser(username, password);
			List<User> users = udawg.getAllUsers();
			request.getSession().setAttribute("users", users);
			request.getSession().setAttribute("user", u);
			response.sendRedirect("userHomepage.jsp");
			return;
		}
		if("delete".equals(s)) {
			udawg.deleteUser(request.getParameter("usernameDelete"));
			List<User> users = udawg.getAllUsers();
			request.getSession().setAttribute("users", users);
			response.sendRedirect("Administrator.jsp");
			return;
			//delete choosen customer
		}
		out.println(s);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String s = request.getParameter("originGet");
		if("Admin".equals(s)) {
			response.sendRedirect("Administrator.jsp");
		}

	}


	//check parameters
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		Enumeration<String> allParamRequest = request.getParameterNames();

		while(allParamRequest.hasMoreElements()) {
			String currParam = allParamRequest.nextElement();
			out.print("parm:"+ currParam + "\tvalue:");
			String[] allParamValues = request.getParameterValues(currParam);
			for(int i = 0; i < allParamValues.length; i++) {
				String paramValue = allParamValues[i];
				out.write(paramValue + "\n");
			}

			//for values: make a list of each enumeration value and print them out
		}
	}

	private String testAccessUser() {
		UserDAO dao = new UserDAO();
		User u = dao.findUser("usercake1");
		return u.getEmail();
	}

	private String testInsertUser() {
		UserDAO dawg = new UserDAO();
		dawg.insertUser("usercake2", "123","anEmailAgain");
		return dawg.findUser("usercake2").getUsername();
	}
/*
	public String testInsert() {
		UserDAO udao = new UserDAO();
		udao.testInsert();
		User u = udao.findUser("usercakeTest");
		return u.getUsername();
	}

	/*
	private String testDB() {
		DBConnector_cloud db = new DBConnector_cloud();
		String            s  = "";
		try {
			Statement st  = db.getConnection().createStatement();
			String    sql = "Select * FROM table1";
			ResultSet rs  = st.executeQuery(sql);
			if(rs.next()) {
				s = rs.getString("colString1");
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return s;
	}*/
}
