package Data.cupcakes;

import Data.Users.User;

public class Invoice
{
	private int id;
	private User user;
	public Invoice(int id, User user) {
		this.user = user;
	}

	public User getUser()
	{
		return this.user;
	}

	public int getId()
	{
		return this.id;
	}
}
