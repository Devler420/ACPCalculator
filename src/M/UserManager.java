package M;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import C.GlobalData;

public class UserManager
{
	public static ArrayList<UserDB> getAllUser()
	{
		ArrayList<UserDB> list = new ArrayList<UserDB>();
		try
	    {
	      // create our mysql database connection
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      String query = "SELECT * FROM users";
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      // iterate through the java result set
	      while (rs.next())
	      {
	    	  //!!!!!!!!!!!!!!Set to match with our DB
	        int id = rs.getInt("users_id");
	        String username = rs.getString("username");
	        String password = rs.getString("password");
	        String nickname = rs.getString("nickname");
	        String email = rs.getString("email");
	        String mobile = rs.getString("mobile");
	        // create new variable สร้างใหม่
	        UserDB cc = new UserDB(id,username,password,nickname,email,mobile);
	        list.add(cc);
	        
	        // print the results
//	        System.out.format("%d, %s, %s, %s, %s, %s \n", id,username,password,nickname,email,mobile);
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		
		return list;
	}
	
	public static void saveNewUser(UserDB x)
	{
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      String query = "INSERT INTO users VALUES (0 , '"+x.username+"' , '"+x.password+"' , '"+x.nickname+"' , '"+x.email+"' , '"+x.mobile+"' )";  //   " belong to JAVA ,  ' belong to SQL
	      Statement st = conn.createStatement();
	      st.executeUpdate(query);
	      
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	}
	
	public static void editUser(UserDB x)
	{
			try
		    {
		      String myDriver = "com.mysql.cj.jdbc.Driver";
		      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
		      Class.forName(myDriver);
		      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)

		      String query = "UPDATE users SET username = '"+x.username+"' , password = '"+x.password+"' , nickname = '"+x.nickname+"' , email = '"+x.email+"' , mobile = '"+x.mobile+"' WHERE users_id = " +x.users_id;  //   " belong to JAVA ,  ' belong to SQL
		      Statement st = conn.createStatement();
		      st.executeUpdate(query); //executeUpdate = no result
		      
		      st.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
	}
	
	public static void deleteUser(UserDB x)
	{
		//code for connect with DB
			try
		    {
		      String myDriver = "com.mysql.cj.jdbc.Driver";
		      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
		      Class.forName(myDriver);
		      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
		      
		      
		     //*** MAIN SQL command to UPDATE!!!!!!!!!!
		      //must have WHERE!
		      String query = "DELETE FROM users WHERE users_id = " +x.users_id+" ";  //   " belong to JAVA ,  ' belong to SQL
		      Statement st = conn.createStatement();
		      st.executeUpdate(query); //executeUpdate = no result
		      
		      st.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
	}
	
	public static boolean checkLogin(String username, String password)
	{
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      
	     //*** MAIN SQL command to UPDATE!!!!!!!!!!
	      //must have WHERE!
	      String query = "SELECT * FROM users WHERE username = ? AND password = ? ";
	      PreparedStatement st = conn.prepareStatement(query);
	      st.setString(1, username);
	      st.setString(2, password);
	      ResultSet rs = st.executeQuery();
	      
	      while (rs.next())
	      {
	    	  GlobalData.CurrentUser_userID = rs.getInt(1);
	    	  GlobalData.CurrentUser_username = rs.getString(2);
	    	  GlobalData.CurrentUser_nickname = rs.getString(4);
	    	  GlobalData.CurrentUser_email = rs.getString(5);
	    	  GlobalData.CurrentUser_mobile = rs.getString(6);
	    	  //if correct pw!!!!!!!!!!!!!!!
	    	  return true;

	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		
		return false;
	}
	
	public static boolean checkVersion(double x)
	{
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      
	     //*** MAIN SQL command to UPDATE!!!!!!!!!!
	      //must have WHERE!
	      String query = "SELECT * FROM version_control WHERE version_control_version = ?";
	      PreparedStatement st = conn.prepareStatement(query);
	      st.setDouble(1, x);
	      ResultSet rs = st.executeQuery();
	      
	      double ver;
	      boolean verPerm;
	      String verComment;
	      
	      while (rs.next())
	      {
	    	  ver = rs.getDouble(2);
	    	  verPerm = rs.getBoolean(3);
	    	  verComment = rs.getString(4);
	    	  //if correct pw!!!!!!!!!!!!!!!
	    	  if (verPerm == true)
	    	  {
	    		  return true;
	    	  }
	    	  else
	    	  {
	    		  GlobalData.ProgramComment = verComment;
	    		  return false;
	    	  }
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return false;
	}
}
