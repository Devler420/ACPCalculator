package M;

import java.sql.Date;

public class ReportDB
{
	public String payerName;
	public double payment;
	public String toWhoName;
	public Date theDate;
	public int potno;
	
	public ReportDB()
	{
		
	}
	
	public ReportDB(
			String xpayerName,
			double xpayment,
			String xtoWhoName,
			Date xtheDate,
			int xpotno)
	{
		payerName = xpayerName;
		payment = xpayment;
		toWhoName = xtoWhoName;
		theDate = xtheDate;
		potno = xpotno;
	}
}
