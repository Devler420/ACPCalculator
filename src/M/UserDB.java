package M;

public class UserDB
{
	public int users_id;
	public String username;
	public String password;
	public String nickname;
	public String email;
	public String mobile;
	
	public UserDB()
	{
		
	}
	public UserDB(
			int xusers_id,
			String xusername,
			String xpassword,
			String xnickname,
			String xemail,
			String xmobile)
	{
		users_id = xusers_id;
		username = xusername;
		password = xpassword;
		nickname = xnickname;
		email = xemail;
		mobile = xmobile;
	}
}
