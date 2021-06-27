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

public class ReportManager
{
	public static ArrayList<ReportDB> getAllSumReport()
	{
		ArrayList<ReportDB> list = new ArrayList<ReportDB>();
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      String query = "Select a.nickname, SUM(b.payment_price), c.nickname AS toWho, d.billing_date, d.numofpot \r\n"
	      		+ "FROM users AS a \r\n"
	      		+ "INNER JOIN billing_details_result AS b \r\n"
	      		+ "ON a.users_id = b.users_id \r\n"
	      		+ "INNER JOIN users AS c \r\n"
	      		+ "ON b.payment_to_who_id = c.users_id\r\n"
	      		+ "INNER JOIN billing_details_main AS d\r\n"
	      		+ "ON b.billing_id = d.billing_id\r\n"
	      		+ "AND a.nickname IN (SELECT a.nickname)\r\n"
	      		+ "GROUP BY a.nickname, toWho\r\n"
	      		+ "ORDER BY a.nickname ASC";
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      while (rs.next())
	      {
	        String payerName = rs.getString("nickname");
	        double sumpayment = rs.getDouble("SUM(b.payment_price)");
	        String toWhoName = rs.getString("toWho");
	        java.sql.Date theDate = rs.getDate("billing_date");
	        int potno = rs.getInt("numofpot");

	        ReportDB cc = new ReportDB(payerName,sumpayment,toWhoName,theDate,potno);
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
	
	public static ArrayList<ReportDB> getSumReportbyName(String x)
	{
		ArrayList<ReportDB> list = new ArrayList<ReportDB>();
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      String query = "Select a.nickname, SUM(b.payment_price), c.nickname AS toWho, d.billing_date, d.numofpot \r\n"
	      		+ "FROM users AS a \r\n"
	      		+ "INNER JOIN billing_details_result AS b \r\n"
	      		+ "ON a.users_id = b.users_id \r\n"
	      		+ "INNER JOIN users AS c \r\n"
	      		+ "ON b.payment_to_who_id = c.users_id\r\n"
	      		+ "INNER JOIN billing_details_main AS d\r\n"
	      		+ "ON b.billing_id = d.billing_id\r\n"
	      		+ "AND a.nickname = '"+x+"'"
	      		+ "GROUP BY a.nickname, toWho\r\n"
	      		+ "ORDER BY a.nickname ASC";
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      while (rs.next())
	      {
	    	  	String payerName = rs.getString("nickname");
		        double sumpayment = rs.getDouble("SUM(b.payment_price)");
		        String toWhoName = rs.getString("toWho");
		        java.sql.Date theDate = rs.getDate("billing_date");
		        int potno = rs.getInt("numofpot");

		        ReportDB cc = new ReportDB(payerName,sumpayment,toWhoName,theDate,potno);
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
	
	public static ArrayList<ReportDB> getSumReportbyDate(String dateBegin, String dateEnd)
	{
		ArrayList<ReportDB> list = new ArrayList<ReportDB>();
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      String query = "Select a.nickname, SUM(b.payment_price), c.nickname AS toWho, d.billing_date, d.numofpot \r\n"
	      		+ "FROM users AS a \r\n"
	      		+ "INNER JOIN billing_details_result AS b \r\n"
	      		+ "ON a.users_id = b.users_id \r\n"
	      		+ "INNER JOIN users AS c \r\n"
	      		+ "ON b.payment_to_who_id = c.users_id\r\n"
	      		+ "INNER JOIN billing_details_main AS d\r\n"
	      		+ "ON b.billing_id = d.billing_id\r\n"
	      		+ "WHERE b.billing_id IN (SELECT d.billing_id WHERE billing_date BETWEEN '"+dateBegin+"' AND '"+dateEnd+"')\r\n"
	      		+ "AND a.nickname IN (SELECT a.nickname)\r\n"
	      		+ "GROUP BY a.nickname, toWho\r\n"
	      		+ "ORDER BY a.nickname ASC";
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);

	      while (rs.next())
	      {
	    	  	String payerName = rs.getString("nickname");
		        double sumpayment = rs.getDouble("SUM(b.payment_price)");
		        String toWhoName = rs.getString("toWho");
		        java.sql.Date theDate = rs.getDate("billing_date");
		        int potno = rs.getInt("numofpot");

		        ReportDB cc = new ReportDB(payerName,sumpayment,toWhoName,theDate,potno);
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
	
	public static ArrayList<ReportDB> getSumReportbyNameDate(String x, String dateBegin, String dateEnd)
	{
		ArrayList<ReportDB> list = new ArrayList<ReportDB>();
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      String query = "Select a.nickname, SUM(b.payment_price), c.nickname AS toWho, d.billing_date, d.numofpot \r\n"
	      		+ "FROM users AS a \r\n"
	      		+ "INNER JOIN billing_details_result AS b \r\n"
	      		+ "ON a.users_id = b.users_id \r\n"
	      		+ "INNER JOIN users AS c \r\n"
	      		+ "ON b.payment_to_who_id = c.users_id\r\n"
	      		+ "INNER JOIN billing_details_main AS d\r\n"
	      		+ "ON b.billing_id = d.billing_id\r\n"
	      		+ "WHERE b.billing_id IN (SELECT d.billing_id WHERE billing_date BETWEEN '"+dateBegin+"' AND '"+dateEnd+"')\r\n"
	      		+ "AND a.nickname = '"+x+"'\r\n"
	      		+ "GROUP BY a.nickname, toWho\r\n"
	      		+ "ORDER BY a.nickname ASC";
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      while (rs.next())
	      {
	    	  String payerName = rs.getString("nickname");
		        double sumpayment = rs.getDouble("SUM(b.payment_price)");
		        String toWhoName = rs.getString("toWho");
		        java.sql.Date theDate = rs.getDate("billing_date");
		        int potno = rs.getInt("numofpot");

		        ReportDB cc = new ReportDB(payerName,sumpayment,toWhoName,theDate,potno);
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
	
	public static ArrayList<ReportDB> getAllReport()
	{
		ArrayList<ReportDB> list = new ArrayList<ReportDB>();
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      String query = "Select a.nickname, b.payment_price, c.nickname as toWho, d.billing_date, d.numofpot\r\n"
	      		+ "FROM users AS a \r\n"
	      		+ "INNER JOIN billing_details_result AS b \r\n"
	      		+ "ON a.users_id = b.users_id \r\n"
	      		+ "INNER JOIN users AS c \r\n"
	      		+ "ON b.payment_to_who_id = c.users_id\r\n"
	      		+ "INNER JOIN billing_details_main AS d\r\n"
	      		+ "ON b.billing_id = d.billing_id\r\n"
	      		+ "ORDER BY a.nickname ASC";
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      while (rs.next())
	      {
	        String payerName = rs.getString("nickname");
	        double payment = rs.getDouble("payment_price");
	        String toWhoName = rs.getString("toWho");
	        java.sql.Date theDate = rs.getDate("billing_date");
	        int potno = rs.getInt("numofpot");

	        ReportDB cc = new ReportDB(payerName,payment,toWhoName,theDate,potno);
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
	
	public static ArrayList<ReportDB> getReportbyName(String x)
	{
		ArrayList<ReportDB> list = new ArrayList<ReportDB>();
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      String query = "Select a.nickname, b.payment_price, c.nickname as toWho, d.billing_date, d.numofpot\r\n"
	      		+ "FROM users AS a \r\n"
	      		+ "INNER JOIN billing_details_result AS b \r\n"
	      		+ "ON a.users_id = b.users_id \r\n"
	      		+ "INNER JOIN users AS c \r\n"
	      		+ "ON b.payment_to_who_id = c.users_id\r\n"
	      		+ "INNER JOIN billing_details_main AS d\r\n"
	      		+ "ON b.billing_id = d.billing_id\r\n"
	      		+ "WHERE a.nickname = '"+x+"'\r\n"
	      		+ "ORDER BY a.nickname ASC";
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      while (rs.next())
	      {
	        String payerName = rs.getString("nickname");
	        double payment = rs.getDouble("payment_price");
	        String toWhoName = rs.getString("toWho");
	        java.sql.Date theDate = rs.getDate("billing_date");
	        int potno = rs.getInt("numofpot");

	        ReportDB cc = new ReportDB(payerName,payment,toWhoName,theDate,potno);
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
	
	public static ArrayList<ReportDB> getReportbyDate(String dateBegin, String dateEnd)
	{
		ArrayList<ReportDB> list = new ArrayList<ReportDB>();
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      String query = "Select a.nickname, b.payment_price, c.nickname as toWho, d.billing_date, d.numofpot\r\n"
	      		+ "FROM users AS a \r\n"
	      		+ "INNER JOIN billing_details_result AS b \r\n"
	      		+ "ON a.users_id = b.users_id \r\n"
	      		+ "INNER JOIN users AS c \r\n"
	      		+ "ON b.payment_to_who_id = c.users_id\r\n"
	      		+ "INNER JOIN billing_details_main AS d\r\n"
	      		+ "ON b.billing_id = d.billing_id\r\n"
	      		+ "WHERE b.billing_id IN (SELECT d.billing_id WHERE billing_date BETWEEN '"+dateBegin+"' AND '"+dateEnd+"')\r\n"
	      		+ "ORDER BY a.nickname ASC";

	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);

	      while (rs.next())
	      {
	    	  	String payerName = rs.getString("nickname");
		        double payment = rs.getDouble("payment_price");
		        String toWhoName = rs.getString("toWho");
		        java.sql.Date theDate = rs.getDate("billing_date");
		        int potno = rs.getInt("numofpot");

		        ReportDB cc = new ReportDB(payerName,payment,toWhoName,theDate,potno);
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
	
	public static ArrayList<ReportDB> getReportbyNameDate(String x, String dateBegin, String dateEnd)
	{
		ArrayList<ReportDB> list = new ArrayList<ReportDB>();
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      String query = "Select a.nickname, b.payment_price, c.nickname as toWho, d.billing_date, d.numofpot\r\n"
	      		+ "FROM users AS a \r\n"
	      		+ "INNER JOIN billing_details_result AS b \r\n"
	      		+ "ON a.users_id = b.users_id \r\n"
	      		+ "INNER JOIN users AS c \r\n"
	      		+ "ON b.payment_to_who_id = c.users_id\r\n"
	      		+ "INNER JOIN billing_details_main AS d\r\n"
	      		+ "ON b.billing_id = d.billing_id\r\n"
	      		+ "WHERE b.billing_id IN (SELECT d.billing_id WHERE billing_date BETWEEN '"+dateBegin+"' AND '"+dateEnd+"')\r\n"
	      		+ "AND a.nickname = '"+x+"'\r\n"
	      		+ "ORDER BY a.nickname ASC";

	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      while (rs.next())
	      {
	    	  String payerName = rs.getString("nickname");
		        double payment = rs.getDouble("payment_price");
		        String toWhoName = rs.getString("toWho");
		        java.sql.Date theDate = rs.getDate("billing_date");
		        int potno = rs.getInt("numofpot");

		        ReportDB cc = new ReportDB(payerName,payment,toWhoName,theDate,potno);
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
	
	public static ArrayList<ReportDB> getAllGetBackSumReport()
	{
		ArrayList<ReportDB> list = new ArrayList<ReportDB>();
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      String query = "Select c.nickname, SUM(b.payment_price) AS 'Get Back', a.nickname AS 'from Who', d.billing_date, d.numofpot \r\n"
	      		+ "FROM users AS a \r\n"
	      		+ "INNER JOIN billing_details_result AS b \r\n"
	      		+ "ON a.users_id = b.users_id \r\n"
	      		+ "INNER JOIN users AS c \r\n"
	      		+ "ON b.payment_to_who_id = c.users_id \r\n"
	      		+ "INNER JOIN billing_details_main AS d \r\n"
	      		+ "ON b.billing_id = d.billing_id \r\n"
	      		+ "AND c.nickname IN (SELECT c.nickname) \r\n"
	      		+ "GROUP BY c.nickname, a.nickname \r\n"
	      		+ "ORDER BY c.nickname ASC";

	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      while (rs.next())
	      {
	        String getbackName = rs.getString("nickname");
	        double sumgetback = rs.getDouble("Get Back");
	        String fromWhoName = rs.getString("from Who");
	        java.sql.Date theDate = rs.getDate("billing_date");
	        int potno = rs.getInt("numofpot");

	        ReportDB cc = new ReportDB(getbackName,sumgetback,fromWhoName,theDate,potno);
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
	
	public static ArrayList<ReportDB> getGetBackSumReportbyName(String x)
	{
		ArrayList<ReportDB> list = new ArrayList<ReportDB>();
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      String query = "Select c.nickname, SUM(b.payment_price) AS 'Get Back', a.nickname AS 'from Who', d.billing_date, d.numofpot \r\n"
	      		+ "FROM users AS a \r\n"
	      		+ "INNER JOIN billing_details_result AS b \r\n"
	      		+ "ON a.users_id = b.users_id \r\n"
	      		+ "INNER JOIN users AS c \r\n"
	      		+ "ON b.payment_to_who_id = c.users_id \r\n"
	      		+ "INNER JOIN billing_details_main AS d \r\n"
	      		+ "ON b.billing_id = d.billing_id \r\n"
	      		+ "AND c.nickname IN (SELECT c.nickname) \r\n"
	      		+ "WHERE c.nickname = '"+x+"'\r\n"
	      		+ "GROUP BY c.nickname, a.nickname \r\n"
	      		+ "ORDER BY c.nickname ASC";

	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      while (rs.next())
	      {
	        String getbackName = rs.getString("nickname");
	        double sumgetback = rs.getDouble("Get Back");
	        String fromWhoName = rs.getString("from Who");
	        java.sql.Date theDate = rs.getDate("billing_date");
	        int potno = rs.getInt("numofpot");

	        ReportDB cc = new ReportDB(getbackName,sumgetback,fromWhoName,theDate,potno);
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
	
	public static ArrayList<ReportDB> getGetBackSumReportbyDate(String dateBegin, String dateEnd)
	{
		ArrayList<ReportDB> list = new ArrayList<ReportDB>();
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      String query = "Select c.nickname, SUM(b.payment_price) AS 'Get Back', a.nickname AS 'from Who', d.billing_date, d.numofpot \r\n"
	      		+ "FROM users AS a \r\n"
	      		+ "INNER JOIN billing_details_result AS b \r\n"
	      		+ "ON a.users_id = b.users_id \r\n"
	      		+ "INNER JOIN users AS c \r\n"
	      		+ "ON b.payment_to_who_id = c.users_id \r\n"
	      		+ "INNER JOIN billing_details_main AS d \r\n"
	      		+ "ON b.billing_id = d.billing_id \r\n"
	      		+ "AND c.nickname IN (SELECT c.nickname) \r\n"
	      		+ "WHERE b.billing_id IN (SELECT d.billing_id WHERE billing_date BETWEEN '"+dateBegin+"' AND '"+dateEnd+"')\r\n"
	      		+ "GROUP BY c.nickname, a.nickname \r\n"
	      		+ "ORDER BY c.nickname ASC";

	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      while (rs.next())
	      {
	        String getbackName = rs.getString("nickname");
	        double sumgetback = rs.getDouble("Get Back");
	        String fromWhoName = rs.getString("from Who");
	        java.sql.Date theDate = rs.getDate("billing_date");
	        int potno = rs.getInt("numofpot");

	        ReportDB cc = new ReportDB(getbackName,sumgetback,fromWhoName,theDate,potno);
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
	
	public static ArrayList<ReportDB> getGetBackSumReportbyNameDate(String x,String dateBegin, String dateEnd)
	{
		ArrayList<ReportDB> list = new ArrayList<ReportDB>();
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      String query = "Select c.nickname, SUM(b.payment_price) AS 'Get Back', a.nickname AS 'from Who', d.billing_date, d.numofpot \r\n"
	      		+ "FROM users AS a \r\n"
	      		+ "INNER JOIN billing_details_result AS b \r\n"
	      		+ "ON a.users_id = b.users_id \r\n"
	      		+ "INNER JOIN users AS c \r\n"
	      		+ "ON b.payment_to_who_id = c.users_id \r\n"
	      		+ "INNER JOIN billing_details_main AS d \r\n"
	      		+ "ON b.billing_id = d.billing_id \r\n"
	      		+ "AND c.nickname IN (SELECT c.nickname) \r\n"
	      		+ "WHERE b.billing_id IN (SELECT d.billing_id WHERE billing_date BETWEEN '"+dateBegin+"' AND '"+dateEnd+"')\r\n"
	      		+ "AND c.nickname = '"+x+"'\r\n"
	      		+ "GROUP BY c.nickname, a.nickname \r\n"
	      		+ "ORDER BY c.nickname ASC";

	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      while (rs.next())
	      {
	        String getbackName = rs.getString("nickname");
	        double sumgetback = rs.getDouble("Get Back");
	        String fromWhoName = rs.getString("from Who");
	        java.sql.Date theDate = rs.getDate("billing_date");
	        int potno = rs.getInt("numofpot");

	        ReportDB cc = new ReportDB(getbackName,sumgetback,fromWhoName,theDate,potno);
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
	
	public static ArrayList<ReportDB> getAllGetBackReport()
	{
		ArrayList<ReportDB> list = new ArrayList<ReportDB>();
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      String query = "Select c.nickname, b.payment_price AS 'Get Back', a.nickname AS 'from Who', d.billing_date, d.numofpot \r\n"
	      		+ "FROM users AS a \r\n"
	      		+ "INNER JOIN billing_details_result AS b \r\n"
	      		+ "ON a.users_id = b.users_id \r\n"
	      		+ "INNER JOIN users AS c \r\n"
	      		+ "ON b.payment_to_who_id = c.users_id \r\n"
	      		+ "INNER JOIN billing_details_main AS d \r\n"
	      		+ "ON b.billing_id = d.billing_id \r\n"
	      		+ "AND c.nickname IN (SELECT c.nickname) \r\n"
	      		+ "ORDER BY c.nickname ASC";

	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      while (rs.next())
	      {
	        String getbackName = rs.getString("nickname");
	        double sumgetback = rs.getDouble("Get Back");
	        String fromWhoName = rs.getString("from Who");
	        java.sql.Date theDate = rs.getDate("billing_date");
	        int potno = rs.getInt("numofpot");

	        ReportDB cc = new ReportDB(getbackName,sumgetback,fromWhoName,theDate,potno);
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
	
	public static ArrayList<ReportDB> getAllGetBackReportbyName(String x)
	{
		ArrayList<ReportDB> list = new ArrayList<ReportDB>();
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      String query = "Select c.nickname, b.payment_price AS 'Get Back', a.nickname AS 'from Who', d.billing_date, d.numofpot \r\n"
	      		+ "FROM users AS a \r\n"
	      		+ "INNER JOIN billing_details_result AS b \r\n"
	      		+ "ON a.users_id = b.users_id \r\n"
	      		+ "INNER JOIN users AS c \r\n"
	      		+ "ON b.payment_to_who_id = c.users_id \r\n"
	      		+ "INNER JOIN billing_details_main AS d \r\n"
	      		+ "ON b.billing_id = d.billing_id \r\n"
	      		+ "AND c.nickname IN (SELECT c.nickname)\r\n"
	      		+ "WHERE c.nickname = '"+x+"'\r\n"
	      		+ "ORDER BY c.nickname ASC";

	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      while (rs.next())
	      {
	        String getbackName = rs.getString("nickname");
	        double sumgetback = rs.getDouble("Get Back");
	        String fromWhoName = rs.getString("from Who");
	        java.sql.Date theDate = rs.getDate("billing_date");
	        int potno = rs.getInt("numofpot");

	        ReportDB cc = new ReportDB(getbackName,sumgetback,fromWhoName,theDate,potno);
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
	
	public static ArrayList<ReportDB> getAllGetBackReportbyDate(String dateBegin, String dateEnd)
	{
		ArrayList<ReportDB> list = new ArrayList<ReportDB>();
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      String query = "Select c.nickname, b.payment_price AS 'Get Back', a.nickname AS 'from Who', d.billing_date, d.numofpot \r\n"
	      		+ "FROM users AS a \r\n"
	      		+ "INNER JOIN billing_details_result AS b \r\n"
	      		+ "ON a.users_id = b.users_id \r\n"
	      		+ "INNER JOIN users AS c \r\n"
	      		+ "ON b.payment_to_who_id = c.users_id \r\n"
	      		+ "INNER JOIN billing_details_main AS d \r\n"
	      		+ "ON b.billing_id = d.billing_id \r\n"
	      		+ "AND c.nickname IN (SELECT c.nickname)\r\n"
	      		+ "WHERE b.billing_id IN (SELECT d.billing_id WHERE billing_date BETWEEN '"+dateBegin+"' AND '"+dateEnd+"')\r\n"
	      		+ "ORDER BY c.nickname ASC";

	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      while (rs.next())
	      {
	        String getbackName = rs.getString("nickname");
	        double sumgetback = rs.getDouble("Get Back");
	        String fromWhoName = rs.getString("from Who");
	        java.sql.Date theDate = rs.getDate("billing_date");
	        int potno = rs.getInt("numofpot");

	        ReportDB cc = new ReportDB(getbackName,sumgetback,fromWhoName,theDate,potno);
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
	
	public static ArrayList<ReportDB> getAllGetBackReportbyNameDate(String x,String dateBegin, String dateEnd)
	{
		ArrayList<ReportDB> list = new ArrayList<ReportDB>();
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
	      String query = "Select c.nickname, b.payment_price AS 'Get Back', a.nickname AS 'from Who', d.billing_date, d.numofpot \r\n"
	      		+ "FROM users AS a \r\n"
	      		+ "INNER JOIN billing_details_result AS b \r\n"
	      		+ "ON a.users_id = b.users_id \r\n"
	      		+ "INNER JOIN users AS c \r\n"
	      		+ "ON b.payment_to_who_id = c.users_id \r\n"
	      		+ "INNER JOIN billing_details_main AS d \r\n"
	      		+ "ON b.billing_id = d.billing_id \r\n"
	      		+ "AND c.nickname IN (SELECT c.nickname)\r\n"
	      		+ "WHERE b.billing_id IN (SELECT d.billing_id WHERE billing_date BETWEEN '"+dateBegin+"' AND '"+dateEnd+"')\r\n"
	      		+ "AND c.nickname = '"+x+"'\r\n"
	      		+ "ORDER BY c.nickname ASC";

	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      while (rs.next())
	      {
	        String getbackName = rs.getString("nickname");
	        double sumgetback = rs.getDouble("Get Back");
	        String fromWhoName = rs.getString("from Who");
	        java.sql.Date theDate = rs.getDate("billing_date");
	        int potno = rs.getInt("numofpot");

	        ReportDB cc = new ReportDB(getbackName,sumgetback,fromWhoName,theDate,potno);
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
}
