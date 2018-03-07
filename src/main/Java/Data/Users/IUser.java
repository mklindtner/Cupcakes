package Data.Users;

public interface IUser
{
	void insertUser(String username, String password, String email);
	User findUser(String username);
}
