package M;

import java.sql.Date;

public class CalculatorDB
{
	public int billing_id;
	public Date billing_date;
	public int numofpot;
	public String type;
	public double totalcup;
	public double price_ppp;
	public double price_ppc;
	public String created_by_users;
//	public int edited_by_users;
	
	public CalculatorDB()
	{
		
	}
	public CalculatorDB(
			int xbilling_id,
			Date xbilling_date,
			int xnumofpot,
			String xtype,
			double xtotalcup,
			double xprice_ppp,
			double xprice_ppc,
			String xcreated_by_users)
			//int xedited_by_users)
	{
		billing_id = xbilling_id;
		billing_date = xbilling_date;
		numofpot = xnumofpot;
		type = xtype;
		totalcup = xtotalcup;
		price_ppp = xprice_ppp;
		price_ppc = xprice_ppc;
		created_by_users = xcreated_by_users;
//		edited_by_users = xedited_by_users;
	}
}
