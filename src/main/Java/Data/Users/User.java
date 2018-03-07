package Data.Users;

public class User
{
	private String username, password, email;
	private int balance;
	private boolean admin;

	public User(String username, String password) {
		this(username, password, 0, null, false);
	}

	public User(String username, String password, String email) {
		this(username, password, 0, email, false);
	}

	public User(String username, String password, int balance, String email, boolean admin) {
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.email = email;
		this.admin = admin;
	}

	public int getBalance()
	{
		return this.balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getPassword()
	{
		return this.password;
	}

	public String getUsername()
	{
		return this.username;
	}

	public String getEmail()
	{
		return this.email;
	}

	public boolean isAdmin()
	{
		return this.admin;
	}

	@Override
	public String toString() {
		return username + ", email: " + email + ", balanace: " + balance;
	}

}
