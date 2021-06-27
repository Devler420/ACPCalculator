package M;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import C.GlobalData;

public class CalculatorManager
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
	
	public static ArrayList<CalculatorDB> getAllResult()
	{
		ArrayList<CalculatorDB> list = new ArrayList<CalculatorDB>();
		try
	    {
	      // create our mysql database connection
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      String query = "SELECT * FROM billing_details_main ORDER BY billing_date DESC, numofpot ASC";
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
//	      DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//	      df.format(rs.getDate("JOINING"));
	      // iterate through the java result set
	      while (rs.next())
	      {
	    	  //!!!!!!!!!!!!!!Set to match with our DB
	        int billing_id = rs.getInt("billing_id");
	        java.sql.Date billing_date = rs.getDate("billing_date");
	        int numofpot = rs.getInt("numofpot");
	        String type = rs.getString("type");
	        double totalcup = rs.getDouble("totalcup");
	        double price_ppp = rs.getDouble("price_ppp");
	        double price_ppc = rs.getDouble("price_ppc");
	        String created_by_users = rs.getString("created_by_users");
	        String edited_by_users = rs.getString("edited_by_users");
	        // create new variable สร้างใหม่
	        CalculatorDB cc = new CalculatorDB(billing_id,billing_date,numofpot,type,totalcup,price_ppp,price_ppc,created_by_users);
	        list.add(cc);
	        
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
	
	public static ArrayList<CalculatorDB> searchAllResultByDate(String s)
	{
		ArrayList<CalculatorDB> list = new ArrayList<CalculatorDB>();
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      String query = "SELECT * FROM billing_details_main WHERE billing_date LIKE '%"+s+"%'";
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      while (rs.next())
	      {
	        int billing_id = rs.getInt("billing_id");
	        java.sql.Date billing_date = rs.getDate("billing_date");
	        int numofpot = rs.getInt("numofpot");
	        String type = rs.getString("type");
	        double totalcup = rs.getDouble("totalcup");
	        double price_ppp = rs.getDouble("price_ppp");
	        double price_ppc = rs.getDouble("price_ppc");
	        String created_by_users = rs.getString("created_by_users");
	        String edited_by_users = rs.getString("edited_by_users");
	        // create new variable สร้างใหม่
	        CalculatorDB cc = new CalculatorDB(billing_id,billing_date,numofpot,type,totalcup,price_ppp,price_ppc,created_by_users);
	        list.add(cc);
	        
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
	
	public static void addBill(CalculatorDB x)
	{
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
	      
	      String query = "INSERT INTO billing_details_main VALUES (0 , '"+x.billing_date+"' , '"+x.numofpot+"' , '"+x.type+"' , '"+x.totalcup+"' , '"+x.price_ppp+"' , '"+x.price_ppc+"' , '"+x.created_by_users+"' , '"+0+"' )";
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
	
	public static void addItem(ItemDB x)
	{
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
	      
	      //SQL#1
	      String query = "SELECT MAX(billing_id) FROM billing_details_main ";
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      int id_max = 0;
	      while (rs.next())
	      {
	    	  id_max = rs.getInt(1);
	      }
	      GlobalData.CurrentResult_billing_id = id_max; ///////NOT SURE
	      
	      //SQL#2
	      query = "SELECT users_id FROM users WHERE nickname ='"+x.users_nickname+"'";
	      st = conn.createStatement();
	      rs = st.executeQuery(query);
	      int id_user = 0;
	      while (rs.next())
	      {
	    	  id_user = rs.getInt(1);
	      }
	      
	      //SQL#3
	      query = "INSERT INTO billing_details_"+x.db_name+" VALUES (0 , '"+x.billing_details_item_price+"' , '"+id_user+"' , '"+id_max+"' )";
	      st = conn.createStatement();
	      st.executeUpdate(query);
	      

	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	}
	
	public static void setDrinker(DrinkerDB x)
	{
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
	      
	      double adv_pay = 0, get_back = 0;
	      
	      //SQL#1
	      String query = "SELECT MAX(billing_id) FROM billing_details_main ";
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      int id_max = 0;
	      while (rs.next())
	      {
	    	  id_max = rs.getInt(1);
	      }
	      
	      //SQL#2
	      query = "SELECT users_id FROM users WHERE nickname ='"+x.users_nickname+"'";
	      st = conn.createStatement();
	      rs = st.executeQuery(query);
	      int id_user = 0;
	      while (rs.next())
	      {
	    	  id_user = rs.getInt(1);
	      }
	      
	      //SQL#3
	      query = "SELECT billing_details_item1_price FROM billing_details_item1 WHERE users_id ='"+id_user+"' AND billing_id = '"+id_max+"'";
	      st = conn.createStatement();
	      rs = st.executeQuery(query);
	      while (rs.next())
	      {
	    	  adv_pay += rs.getDouble(1);
	      }
	      
	      //SQL#4
	      query = "SELECT billing_details_item2_price FROM billing_details_item2 WHERE users_id ='"+id_user+"' AND billing_id = '"+id_max+"'";
	      st = conn.createStatement();
	      rs = st.executeQuery(query);
	      while (rs.next())
	      {
	    	  adv_pay += rs.getDouble(1);
	      }
	      
	      //SQL#5
	      query = "SELECT billing_details_ice_price FROM billing_details_ice WHERE users_id ='"+id_user+"' AND billing_id = '"+id_max+"'";
	      st = conn.createStatement();
	      rs = st.executeQuery(query);
	      while (rs.next())
	      {
	    	  adv_pay += rs.getDouble(1);
	      }
	      
	      //SQL#6
	      query = "SELECT billing_details_etc_price FROM billing_details_etc WHERE users_id ='"+id_user+"' AND billing_id = '"+id_max+"'";
	      st = conn.createStatement();
	      rs = st.executeQuery(query);
	      while (rs.next())
	      {
	    	  adv_pay += rs.getDouble(1);
	      }
	      
	      //SQL#7
	      query = "INSERT INTO billing_details_drinker VALUES (0 , '"+id_max+"' , '"+id_user+"' , '"+x.cup_drank+"' , '"+adv_pay+"' , '"+get_back+"' )";
	      st = conn.createStatement();
	      st.executeUpdate(query);

	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	}
	
	public static double setAdvpay(DrinkerDB x)
	{
		double adv_pay = 0;
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
	      
	      //SQL#1
	      String query = "SELECT MAX(billing_id) FROM billing_details_main ";
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      int id_max = 0;
	      while (rs.next())
	      {
	    	  id_max = rs.getInt(1);
	      }
	      
	      //SQL#2
	      query = "SELECT users_id FROM users WHERE nickname ='"+x.users_nickname+"'";
	      st = conn.createStatement();
	      rs = st.executeQuery(query);
	      int id_user = 0;
	      while (rs.next())
	      {
	    	  id_user = rs.getInt(1);
	      }
	      
	      //SQL#3
	      query = "SELECT adv_pay FROM billing_details_drinker WHERE users_id ='"+id_user+"' AND billing_id = '"+id_max+"'";
	      st = conn.createStatement();
	      rs = st.executeQuery(query);
	      while (rs.next())
	      {
	    	  adv_pay = rs.getDouble(1);
	      }
	      
	      st.close();
	      
	      }
	      catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
		return adv_pay;
	}
	
	public static void setGetBack(double z, String x)
	{
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
	      
	      //SQL#1
	      String query = "SELECT MAX(billing_id) FROM billing_details_main ";
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      int id_max = 0;
	      while (rs.next())
	      {
	    	  id_max = rs.getInt(1);
	      }
	      
	      //SQL#2
	      query = "SELECT users_id FROM users WHERE nickname ='"+x+"'";
	      st = conn.createStatement();
	      rs = st.executeQuery(query);
	      int id_user = 0;
	      while (rs.next())
	      {
	    	  id_user = rs.getInt(1);
	      }

	      //SQL#3
	      query = "UPDATE billing_details_drinker SET get_back = '"+z+"' WHERE billing_id = '"+id_max+"' AND users_id = '"+id_user+"'";
	      st = conn.createStatement();
	      st.executeUpdate(query);

	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	}
	
	public static void setPPP_PPC(double a, double b)
	{
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
	      
	      //SQL#1
	      String query = "SELECT MAX(billing_id) FROM billing_details_main ";
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      int id_max = 0;
	      while (rs.next())
	      {
	    	  id_max = rs.getInt(1);
	      }

	      //SQL#2
	      query = "UPDATE billing_details_main SET price_ppp = '"+a+"' , price_ppc = '"+b+"' WHERE billing_id = '"+id_max+"'";
	      st = conn.createStatement();
	      st.executeUpdate(query);

	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	}
	
	public static void deleteBill()
	{
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
	      
	      String query = "DELETE FROM billing_details_main WHERE billing_id = '"+GlobalData.CurrentResult_billing_id+"'";
	      Statement st = conn.createStatement();
	      st.executeUpdate(query);
	      
	      query = "DELETE FROM billing_details_result WHERE billing_id = '"+GlobalData.CurrentResult_billing_id+"'";
	      st = conn.createStatement();
	      st.executeUpdate(query);
	      
	      query = "DELETE FROM billing_details_drinker WHERE billing_id = '"+GlobalData.CurrentResult_billing_id+"'";
	      st = conn.createStatement();
	      st.executeUpdate(query);
	      
	      query = "DELETE FROM billing_details_item1 WHERE billing_id = '"+GlobalData.CurrentResult_billing_id+"'";
	      st = conn.createStatement();
	      st.executeUpdate(query);
	      
	      query = "DELETE FROM billing_details_item2 WHERE billing_id = '"+GlobalData.CurrentResult_billing_id+"'";
	      st = conn.createStatement();
	      st.executeUpdate(query);
	      
	      query = "DELETE FROM billing_details_ice WHERE billing_id = '"+GlobalData.CurrentResult_billing_id+"'";
	      st = conn.createStatement();
	      st.executeUpdate(query);
	      
	      query = "DELETE FROM billing_details_etc WHERE billing_id = '"+GlobalData.CurrentResult_billing_id+"'";
	      st = conn.createStatement();
	      st.executeUpdate(query);
	      
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	}
	
	public static boolean checkExistPot(int numpot, java.sql.Date date)
	{
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      String query = "SELECT numofpot, billing_date FROM ACPDatabase.billing_details_main WHERE billing_date = '"+date+"' AND numofpot = '"+numpot+"';";
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);

	      while (rs.next())
	      {

	        int potnum = rs.getInt("numofpot");
	        java.sql.Date theDate = rs.getDate("billing_date");
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
}
