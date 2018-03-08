package Data.cupcakes;

import Data.DBConnector;
import Data.Users.User;
import Data.datasource.datasource_cloud;
import Data.datasource.datasource_local;

import javax.xml.transform.Result;
import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO
{
	private DBConnector dbcon = new DBConnector(new datasource_local().getDataSource());
	//private DBConnector dbcon = new DBConnector(new datasource_cloud().getDataSource());
	public List<Bottom> getAllBottoms() {
		List<Bottom> bottomList = new ArrayList<Bottom>();
		try {
			dbcon.open();
			String            sql = "Select * FROM BOTTOM";
			PreparedStatement st  = dbcon.preparedStatement(sql);
			ResultSet         rs  = st.executeQuery();
			while(rs.next()) {
				int    cost         = rs.getInt("PRICE");
				String bottomFlavor = rs.getString("BOT");
				int id = rs.getInt("id");
				Bottom b            = new Bottom(id, cost, bottomFlavor);
				bottomList.add(b);
			}
			dbcon.close();
		}catch(SQLException ex) {
			throw new IllegalStateException("Could not get all buttoms");
		}
		return bottomList;
	}

	public List<Topping> getAllToppings() {
		List<Topping> toppingList = new ArrayList();
		try {
			dbcon.open();
			String sql = "Select * FROM TOPPING";
			PreparedStatement st = dbcon.preparedStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				int cost = rs.getInt("PRICE");
				String ToppingFlavor = rs.getString("TOP");
				int id = rs.getInt("id");
				Topping t = new Topping(id, cost, ToppingFlavor);
				toppingList.add(t);
			}
			dbcon.close();
		}catch(SQLException ex) {
			throw new IllegalStateException("Could not get all toppings");
		}
		return toppingList;
	}


	public Topping getTopping(String name) {
		Topping t = null;
		try {
			dbcon.open();
			String sql = "Select * FROM TOPPING WHERE TOP=?";
			PreparedStatement st = dbcon.preparedStatement(sql);
			st.setString(1, name);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				String flavor = rs.getString("TOP");
				int cost = rs.getInt("PRICE");
				int id = rs.getInt("id");
				t = new Topping(id, cost, flavor);
			}

		}catch(SQLException ex) {
			throw new IllegalStateException("Exception from topping");
		}
		return t;
	}

	//made for invoiceDetails, this annoys me
	public Topping getTopping(int id) {
		Topping top = null;
		try {
			dbcon.open();
			String sql = "Select * FROM TOPPING WHERE ID=?";
			PreparedStatement st = dbcon.preparedStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				String topping = rs.getString("TOP");
				int price = rs.getInt("PRICE");
				top = new Topping(id, price, topping);
			}
			return top;
		}catch (SQLException ex) {
			throw new IllegalStateException("Unable to find topping from ID");
		}
	}

	public Bottom getButtom(int id) {
		Bottom bot = null;
		try {
			dbcon.open();
			String sql = "Select * FROM BOTTOM WHERE ID=?";
			PreparedStatement st = dbcon.preparedStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				String bottom = rs.getString("BOT");
				int price = rs.getInt("PRICE");
				bot = new Bottom(id, price, bottom);
			}
			return bot;
		} catch(SQLException ex) {
			throw new IllegalStateException("Unable to find Bottom from ID");
		}
	}

	public Bottom getBottom(String name) {
		Bottom b = null;
		try {
			dbcon.open();
			String sql = "Select * FROM BOTTOM WHERE BOT=?";
			PreparedStatement st = dbcon.preparedStatement(sql);
			st.setString(1, name);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				String flavor = rs.getString("BOT");
				int count = rs.getInt("PRICE");
				int id = rs.getInt("ID");
				b = new Bottom(id, count, flavor);
			}
		} catch(SQLException ex) {
			throw new IllegalStateException("Exception from Bottom");
		}
		return b;
	}

	public Invoice createInvoice(User user, Cupcake cupcake, int quantity) {
		Invoice invoice = null;
		//ResultSet rs;
		try {
			dbcon.open();
			dbcon.setAutoCommit(false);
			String sql = "Insert Into invoice(BotID, TopID, UserID, quantity, totalCost) VALUES (?, ?, ?, ?, ?);";
			PreparedStatement st = dbcon.preparedStatement(sql);
			int botId = cupcake.getBot().getId();
			int topId = cupcake.getTop().getId();
			int quant = quantity;
			st.setInt(1, botId);
			st.setInt(2, topId);
			st.setString(3, user.getUsername());
			st.setInt(4, quant);
			st.setInt(5, cupcake.getTotalPrice() * quantity);
			st.executeUpdate();
			dbcon.setAutoCommit(true);
		}catch(SQLException ex) {
			if(dbcon.haveConnection())
				try {
					System.out.println("rolling back to previous state");
					dbcon.rollback();
				} catch(SQLException re) {
					System.out.println("unable to rollback");
					throw new IllegalStateException("unable to create invoice");
				}
				ex.printStackTrace();
		} finally {
			try {
				dbcon.setAutoCommit(true);
			}catch (SQLException fex) {
				fex.printStackTrace();
			}
		}

		return invoice;
	}

	public List<Invoice> getUserInvoices(String username) {
		List<Invoice> userInvoice = new ArrayList<Invoice>();
		try {
			dbcon.open();
			String sql = "Select * FROM invoice WHERE userID=?";
			PreparedStatement st = dbcon.preparedStatement(sql);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				int topID = rs.getInt("TopID");
				int botID = rs.getInt("BotID");
				int quantity = rs.getInt("quantity");
				int totalCost = rs.getInt("totalCost");
				Invoice invoice = new Invoice(id, username, topID, botID, quantity, totalCost);
				userInvoice.add(invoice);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return userInvoice;
	}

	public List<Invoice> getUsersInvoice() {
		List<Invoice> usersInvoice = new ArrayList<Invoice>();
		try {
			dbcon.open();
			String sql = "Select * FROM invoice";
			PreparedStatement st = dbcon.preparedStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				int topID = rs.getInt("TopID");
				int botID = rs.getInt("BotID");
				int quantity = rs.getInt("quantity");
				int totalCost = rs.getInt("totalCost");
				String userID = rs.getString("UserID");
				Invoice invoice = new Invoice(id, userID, topID, botID, quantity, totalCost);
				usersInvoice.add(invoice);
			}
			return usersInvoice;
		}catch(SQLException ex) {
			throw new IllegalStateException("Unable to find all users invoice");
		}
	}
}
