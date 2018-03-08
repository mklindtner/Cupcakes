package Data.cupcakes;

import Data.Users.User;

public class Invoice
{
	private int topId, botID, id, quantity, totalCost;
	private String userId; //username
	private Cupcake cupcake;

	public Invoice(int id, String userId, int topID, int botID, int quantity, int totalCost) {
		this.id = id;
		this.userId = userId;
		this.topId = topID;
		this.botID = botID;
		this.quantity = quantity;
		this.totalCost = totalCost;
	}

	public int getId() {
		return id;
	}

	public int getTopId()
	{
		return this.topId;
	}

	public int getBotID()
	{
		return this.botID;
	}

	@Override
	public String toString() {
		return "quantity: " + quantity + ", totalCost: " + totalCost;
	}

	public String getUserId()
	{
		return this.userId;
	}
}
