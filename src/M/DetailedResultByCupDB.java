package M;

public class DetailedResultByCupDB
{
	public String users_nickname;
	public double ppc;
	public double cup_drank;
	public double advPay;
	public double getBack;
	
	public DetailedResultByCupDB()
	{
		
	}
	public DetailedResultByCupDB(
			String xusers_nickname,
			double xppc,
			double xcup_drank,
			double xadvPay,
			double xgetBack)
	{
		users_nickname = xusers_nickname;
		ppc = xppc;
		cup_drank = xcup_drank;
		advPay = xadvPay;
		getBack = xgetBack;
	}
}
