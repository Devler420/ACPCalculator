package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import C.GlobalData;
import M.CalculatorDB;
import M.CalculatorManager;
import M.DetailedResultByCupDB;
import M.DetailedResultByPotDB;
import M.DetailedResultManager;
import M.SetPaymentDB;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class DetailedResultFrame extends JFrame
{

	private JPanel contentPane;
	private JTable table;
	ArrayList<DetailedResultByPotDB> listbypot;
	ArrayList<DetailedResultByCupDB> listbycup;
	ArrayList<SetPaymentDB> paymentList;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					DetailedResultFrame frame = new DetailedResultFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DetailedResultFrame()
	{
		setTitle("Detailed Result");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 988, 395);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("USER :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 64, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(GlobalData.CurrentUser_nickname);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(73, 11, 106, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date :");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_2.setBounds(10, 38, 79, 47);
		contentPane.add(lblNewLabel_2);
		
		JLabel label_date = new JLabel(GlobalData.CurrentResult_date);
		label_date.setVerticalAlignment(SwingConstants.BOTTOM);
		label_date.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label_date.setBounds(84, 38, 182, 47);
		contentPane.add(label_date);
		
		JLabel lblNewLabel_potnumber = new JLabel("POT#");
		lblNewLabel_potnumber.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_potnumber.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_potnumber.setBounds(237, 38, 79, 47);
		contentPane.add(lblNewLabel_potnumber);
		
		JLabel lbl_potno = new JLabel(""+GlobalData.CurrentResult_potno);
		lbl_potno.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl_potno.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_potno.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lbl_potno.setBounds(311, 38, 24, 47);
		contentPane.add(lbl_potno);
		
		JLabel lblNewLabel_3 = new JLabel("Type :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(395, 61, 45, 24);
		contentPane.add(lblNewLabel_3);
		
		JLabel lbl_pottype = new JLabel(GlobalData.CurrentResult_pottype);
		lbl_pottype.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_pottype.setBounds(440, 62, 46, 23);
		contentPane.add(lbl_pottype);
		
		JLabel lblNewLabel_4 = new JLabel("Total Cup :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(496, 61, 74, 24);
		contentPane.add(lblNewLabel_4);
		
		JLabel lbl_totalcup = new JLabel(""+GlobalData.CurrentResult_totalcup);
		lbl_totalcup.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_totalcup.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_totalcup.setBounds(569, 62, 36, 23);
		contentPane.add(lbl_totalcup);
		
		JLabel lblNewLabel_5 = new JLabel("Created By :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(615, 61, 85, 24);
		contentPane.add(lblNewLabel_5);
		
		JLabel lbl_createdbyusers = new JLabel(GlobalData.CurrentResult_user_nickname);
		lbl_createdbyusers.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_createdbyusers.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_createdbyusers.setBounds(699, 62, 116, 23);
		contentPane.add(lbl_createdbyusers);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 102, 954, 240);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		if (GlobalData.CurrentResult_pottype.equals("ByPOT"))
		{
			loadtablebyPOT();
		}
		else
		{
			loadtablebyCUP();
		}
		
		
		//Sort table
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		 
//		int columnIndexToSort = 4;
//		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
//		 
		sorter.setSortKeys(sortKeys);
	}
	
	public void loadtablebyPOT()
	{
		//Create new arraylist for result
		listbypot = DetailedResultManager.getDetailedResultByPot();
		
		DefaultTableModel model = new DefaultTableModel();
		//สร้าง column
		model.addColumn("Drinker");
		model.addColumn("Price/Person");
		model.addColumn("Reserved");
		model.addColumn("Get Back");
		model.addColumn("Pay");
		model.addColumn("To Who?");
		
		for (DetailedResultByPotDB c: listbypot)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model.addRow(new Object[] {c.users_nickname, c.ppp, c.advPay, c.getBack});
		}
		
		table.setModel(model);
		
		double totalgetback = 0.0;
		for (int i=0;i<table.getRowCount();i++)
		{
			totalgetback += getbackbyPOT(table, i);
		}
		
		//calculate PAYMENT!!!!!
		for (int i=0;i<table.getRowCount();i++)
		{
			double getback = getbackbyPOT(table, i);
			if (getback > 0)
			{
				table.setValueAt(0.0, i, 4);
			}
			else
			{
				double priceperperson = Double.parseDouble(table.getValueAt(i, 1).toString());
				double reserved = Double.parseDouble(table.getValueAt(i, 2).toString());
				double pay = priceperperson - reserved;
				table.setValueAt(pay, i, 4);
			}
		}
		
		double getbackArr [] = new double [table.getRowCount()];
		for (int i=0;i<getbackArr.length;i++)
		{
			getbackArr[i] = getbackbyPOT(table, i);
		}
		
		ArrayList<Double> payArr = new ArrayList<Double>();
		for (int i=0;i<getbackArr.length;i++)
		{
			payArr.add(paybyPOT(table, i));
		}
		
		for (int i=0;i<getbackArr.length;i++)
		{
			if (getbackArr[i] > 0)
			{
				for (int j=0;j<getbackArr.length;j++)
				{
					if (payArr.get(j) > 0)
					{
						if (payArr.get(j) < getbackArr[i]) 	//pay < getback
						{
//							System.out.println("Start loop1 pay < getback: "+payArr);
							getbackArr[i] = getbackArr[i] - payArr.get(j);
							payArr.set(j, 0.0);
							table.setValueAt(table.getValueAt(i, 0), j, 5);
//							System.out.println("END   loop1 pay < getback: "+payArr);
						}
						else if (getbackArr[i] != 0.0)		//pay > getback
						{
							model.insertRow(table.getRowCount(), new Object[] {table.getValueAt(j, 0),"" ,"" ,"" ,getbackArr[i],table.getValueAt(i, 0)});
//							System.out.println("Start loop2 pay > getback: "+payArr);
							table.setValueAt(payArr.get(j)-getbackArr[i], j, 4);
							payArr.set(j, payArr.get(j)-getbackArr[i]);
							getbackArr[i] = 0.0;
//							System.out.println("END   loop2 pay > getback: "+payArr);
						}
					}
					else
					{
						
					}
				}
			}
			else
			{
				
			}
		}
		
		//find Maintenance Fee
		for (int i=0;i<payArr.size();i++)
		{
			if (payArr.get(i) != 0.0)
			{
				table.setValueAt("Maintenance", i, 5);
			}
		}
		
		paymentList = new ArrayList<SetPaymentDB>();
		for (int i=0;i<table.getRowCount();i++)
		{
			if (table.getValueAt(i, 5)!= null)
			{
				SetPaymentDB cc = new SetPaymentDB(0,
						GlobalData.CurrentResult_billing_id,
						table.getValueAt(i, 0).toString(),
						paybyPOT(table, i),
						table.getValueAt(i, 5).toString());
				paymentList.add(cc);
			}
		}
	}
	
	public void loadtablebyCUP()
	{
		//Create new arraylist for result
		listbycup = DetailedResultManager.getDetailedResultByCup();
		
		DefaultTableModel model = new DefaultTableModel();
		//สร้าง column
		model.addColumn("Drinker");
		model.addColumn("Price/Cup");
		model.addColumn("Cup Drank");
		model.addColumn("Reserved");
		model.addColumn("Get Back");
		model.addColumn("Pay");
		model.addColumn("To Who?");
		
		for (DetailedResultByCupDB c: listbycup)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model.addRow(new Object[] {c.users_nickname, c.ppc, c.cup_drank, c.advPay, c.getBack});
		}
		
		table.setModel(model);
		
		double totalgetback = 0.0;
		for (int i=0;i<table.getRowCount();i++)
		{
			totalgetback += getbackbyCUP(table, i);
		}
		
		//calculate PAYMENT!!!!!
		for (int i=0;i<table.getRowCount();i++)
		{
			double getback = getbackbyCUP(table, i);
			if (getback > 0)
			{
				table.setValueAt(0.0, i, 5);
			}
			else
			{
				double pricepercup = Double.parseDouble(table.getValueAt(i, 1).toString());
				double cupdrank = Double.parseDouble(table.getValueAt(i, 2).toString());
				double reserved = Double.parseDouble(table.getValueAt(i, 3).toString());
				double pay = (pricepercup*cupdrank) - reserved;
				table.setValueAt(pay, i, 5);
			}
		}
		
		double getbackArr [] = new double [table.getRowCount()];
		for (int i=0;i<getbackArr.length;i++)
		{
			getbackArr[i] = getbackbyCUP(table, i);
		}
		
		ArrayList<Double> payArr = new ArrayList<Double>();
		for (int i=0;i<getbackArr.length;i++)
		{
			payArr.add(paybyCUP(table, i));
		}
		
		for (int i=0;i<getbackArr.length;i++)
		{
			if (getbackArr[i] > 0)
			{
				for (int j=0;j<getbackArr.length;j++)
				{
					if (payArr.get(j) > 0)
					{
						if (payArr.get(j) < getbackArr[i]) 	//pay < getback
						{
//							System.out.println("Start loop1 pay < getback: "+payArr);
							getbackArr[i] = getbackArr[i] - payArr.get(j);
							payArr.set(j, 0.0);
							table.setValueAt(table.getValueAt(i, 0), j, 6);
//							System.out.println("END   loop1 pay < getback: "+payArr);
						}
						else if (getbackArr[i] != 0.0)		//pay > getback
						{
							model.insertRow(table.getRowCount(), new Object[] {table.getValueAt(j, 0),"" ,"" ,"", "",getbackArr[i],table.getValueAt(i, 0)});
//							System.out.println("Start loop2 pay > getback: "+payArr);
							table.setValueAt(payArr.get(j)-getbackArr[i], j, 5);
							payArr.set(j, payArr.get(j)-getbackArr[i]);
							getbackArr[i] = 0.0;
//							System.out.println("END   loop2 pay > getback: "+payArr);
						}
					}
					else
					{
						
					}
				}
			}
			else
			{
				
			}
		}
		
		//find Maintenance Fee
		for (int i=0;i<payArr.size();i++)
		{
			if (payArr.get(i) != 0.0)
			{
				table.setValueAt("Maintenance", i, 6);
			}
		}
		
		paymentList = new ArrayList<SetPaymentDB>();
		for (int i=0;i<table.getRowCount();i++)
		{
			if (table.getValueAt(i, 6)!= null)
			{
				SetPaymentDB cc = new SetPaymentDB(0,
						GlobalData.CurrentResult_billing_id,
						table.getValueAt(i, 0).toString(),
						paybyCUP(table, i),
						table.getValueAt(i, 6).toString());
				paymentList.add(cc);
			}
		}
	}
	
	
	
	/**
	convert from Obj. JTable to Double for byPOT calculate type
	*/
	public double getbackbyPOT(JTable x, int row)
	{
		return Double.parseDouble(x.getValueAt(row, 3).toString());
	}
	/**
	convert from Obj. JTable to Double for byCUP calculate type
	*/
	public double getbackbyCUP(JTable x, int row)
	{
		return Double.parseDouble(x.getValueAt(row, 4).toString());
	}
	
	/**
	convert from Obj. JTable to Double for byPOT calculate type
	*/
	public double paybyPOT(JTable x, int row)
	{
		return Double.parseDouble(x.getValueAt(row, 4).toString());
	}
	/**
	convert from Obj. JTable to Double for byCUP calculate type
	*/
	public double paybyCUP(JTable x, int row)
	{
		return Double.parseDouble(x.getValueAt(row, 5).toString());
	}
}
