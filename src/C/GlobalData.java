package C;

import java.util.Date;

public class GlobalData
{
	public static final String DATABASE_LOCATION = "acp-db.crpkyjnpfhba.ap-southeast-1.rds.amazonaws.com";
	public static final String DATABASE_PORT = "3306";
	public static final String DATABASE_USERNAME = "devler420";
	public static final String DATABASE_PASSWORD = "Bookboom";
	public static final String DATABASE_DATABASE_NAME = "ACPDatabase";
	
	//Change this when there are new updates
	public static double ProgramVersion = 1.3;
	public static String ProgramComment;
	public static boolean ProgramVersionPerm;
	
	public static int CurrentUser_userID;
	public static String CurrentUser_username;
	public static String CurrentUser_nickname;
	public static String CurrentUser_email;
	public static String CurrentUser_mobile;
	
	public static int CurrentResult_billing_id;
	public static String CurrentResult_date;
	public static int CurrentResult_potno;
	public static String CurrentResult_pottype;
	public static double CurrentResult_totalcup;
	public static int CurrentResult_usersid;
	public static String CurrentResult_user_nickname;
	
	
}
