package M;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import C.GlobalData;

public class DetailedResultManager
{
	public static ArrayList<DetailedResultByPotDB> getDetailedResultByPot()
	{
		ArrayList<DetailedResultByPotDB> list = new ArrayList<DetailedResultByPotDB>();
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME; //!!!!!!!!!!!!!SET LOCAL HOST
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD); //!!!!!!!!!!Set our username (PHPmyadmin user)
	      
			String query = "SELECT users.nickname, billing_details_main.price_ppp, billing_details_drinker.adv_pay, billing_details_drinker.get_back FROM "
					+ "((users INNER JOIN billing_details_drinker ON users.users_id = billing_details_drinker.users_id) INNER JOIN billing_details_main "
					+ "ON billing_details_drinker.billing_id = billing_details_main.billing_id) WHERE billing_details_drinker.billing_id = '"
					+ GlobalData.CurrentResult_billing_id + "'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next())
			{

				String drinker = rs.getString("nickname");
				double pricePP = rs.getDouble("price_ppp");
				double adv_pay = rs.getDouble("adv_pay");
				double get_back = rs.getDouble("get_back");

				DetailedResultByPotDB cc = new DetailedResultByPotDB(drinker, pricePP, adv_pay, get_back);
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
	
	public static ArrayList<DetailedResultByCupDB> getDetailedResultByCup()
	{
		ArrayList<DetailedResultByCupDB> list = new ArrayList<DetailedResultByCupDB>();
		try
	    {
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME; // !!!!!!!!!!!!!SET LOCAL HOST
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query = "SELECT users.nickname, billing_details_main.price_ppc, billing_details_drinker.cup_drank, billing_details_drinker.adv_pay, billing_details_drinker.get_back FROM "
					+ "((users INNER JOIN billing_details_drinker ON users.users_id = billing_details_drinker.users_id) INNER JOIN billing_details_main "
					+ "ON billing_details_drinker.billing_id = billing_details_main.billing_id) WHERE billing_details_drinker.billing_id = '"
					+ GlobalData.CurrentResult_billing_id + "'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next())
			{

				String drinker = rs.getString("nickname");
				double pricePP = rs.getDouble("price_ppc");
				double cup_drank = rs.getDouble("cup_drank");
				double adv_pay = rs.getDouble("adv_pay");
				double get_back = rs.getDouble("get_back");

				DetailedResultByCupDB cc = new DetailedResultByCupDB(drinker, pricePP, cup_drank, adv_pay, get_back);
				list.add(cc);

			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}
	
	public static void setPayment(SetPaymentDB x)
	{
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
	      
	      //SQL#1
	      String query = "SELECT users_id FROM users WHERE nickname ='"+x.users_nickname+"'";
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      int id_user = 0;
	      while (rs.next())
	      {
	    	  id_user = rs.getInt(1);
	      }
	      
	      //SQL#2
	      query = "SELECT users_id FROM users WHERE nickname ='"+x.payment_to_who_id+"'";
	      st = conn.createStatement();
	      rs = st.executeQuery(query);
	      int id_user_payment = 0;
	      while (rs.next())
	      {
	    	  id_user_payment = rs.getInt(1);
	      }
	      
	      //SQL#3
	      query = "INSERT INTO billing_details_result VALUES (0 , '"+x.billing_id+"' , '"+id_user+"' , '"+x.payment_price+"' , '"+id_user_payment+"' )";
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
}
