package Servlet;

import Data.ShoppingCart.LineItems;
import Data.ShoppingCart.ShoppingCart;
import Data.Users.User;
import Data.Users.UserDAO;
import Data.cupcakes.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/ServletProducts")
public class ServletProducts extends HttpServlet
{
	private ProductDAO pdawg;
	private ShoppingCart sc;
	public ServletProducts() {
		pdawg = new ProductDAO();

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String s = request.getParameter("originPost");
		if(s.equals("createCupcake")) {
			Topping top = pdawg.getTopping(request.getParameter("topping"));
			Bottom bot = pdawg.getBottom(request.getParameter("bottom"));
			Cupcake cake = new Cupcake(top, bot);
			LineItems litem = new LineItems(Integer.parseInt(request.getParameter("quantity")), cake);
			if(sc == null) {
				sc = new ShoppingCart();
				request.getSession().setAttribute("shoppingCart", sc);
			}
			sc.add(litem);
			response.sendRedirect("userHomepage.jsp");
			/*out.println("Cupcake order consists of:\n" + "top: " + cake.getTopFlavor() +"\nbot:" + cake.getBotFlavor
					()); */
		}
		if(s.equals("Confirmation")) {
			ShoppingCart sc             = (ShoppingCart) request.getSession().getAttribute("shoppingCart");
			User         u              = (User) request.getSession().getAttribute("user");
			UserDAO udawg = new UserDAO();
			int totalPrice = 0;
			//create invoice
			for(int i = 0; i < sc.getList().size(); i++) {
				pdawg.createInvoice(u, sc.getItem(i).getCupcake(), sc.getItem(i).getQuantity());
			}
			//find balance
			for(int i = 0; i < sc.getList().size(); i++) {
				totalPrice += sc.getItem(i).getCupcake().getTotalPrice() * sc.getItem(i).getQuantity();
			}
			out.println("user balance: " + u.getBalance());
			out.println("totalPrice: " + totalPrice);

			if(u.getBalance() >= totalPrice) {
				udawg.updateUserBalance(u.getUsername(), u.getBalance()-totalPrice);
				out.println("in here");
				response.sendRedirect("confirmation.jsp");
			} else
				response.sendRedirect("userHomepage.jsp");
		}
		if(s.equals("clearCart")) {
			ShoppingCart sc = (ShoppingCart) request.getSession().getAttribute("shoppingCart");
			out.println("sc: " + sc.getList());
			sc.clearShoppingCart();
			response.sendRedirect("userHomepage.jsp");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter  out        = response.getWriter();
		String s = request.getParameter("originGet");
		if(s.equals("ButTop")) {
			List<Bottom>  listBottom  = pdawg.getAllBottoms();
			List<Topping> listTopping = pdawg.getAllToppings();
			request.getSession().setAttribute("listButtom", listBottom);
			request.getSession().setAttribute("listTopping", listTopping);
			response.sendRedirect("Products.jsp");
		}
	}

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
}
