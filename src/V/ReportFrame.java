package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import com.toedter.calendar.JDateChooser;

import M.CalculatorDB;
import M.CalculatorManager;
import M.ReportDB;
import M.ReportManager;
import M.UserDB;
import M.UserManager;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;

public class ReportFrame extends JFrame
{

	private JPanel contentPane;
	private int panelcount = 0;
	ArrayList<UserDB> list_user;
	ArrayList<String> list_nickname;
	private JTable table_PayPrimary;
	private JTable table_PaySecondary;
	ArrayList<ReportDB> list;
	ArrayList<ReportDB> list2;
	java.sql.Date sqlDateBegin;
	java.sql.Date sqlDateEnd;
	java.sql.Date sqlDateGBBegin;
	java.sql.Date sqlDateGBEnd;
	private JTable table_gb1;
	private JButton btn_gbclear;
	private JTable table_gb2;

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
					ReportFrame frame = new ReportFrame();
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
	public ReportFrame()
	{
		setTitle("Report Database");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1023, 615);
		setLocationRelativeTo(null);
		
		loadnickname();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 45, 987, 525);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel_pay = new JPanel();
		panel_pay.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		layeredPane.add(panel_pay, "name_589840630127700");
		panel_pay.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pay");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(794, 10, 183, 91);
		panel_pay.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Name :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 32, 59, 27);
		panel_pay.add(lblNewLabel_2);
		
		JComboBox comboBox_payName = new JComboBox(list_nickname.toArray(new String[0]));
		comboBox_payName.setBounds(77, 33, 110, 27);
		panel_pay.add(comboBox_payName);
		
		JLabel lblNewLabel_3 = new JLabel("Date Range :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(10, 75, 95, 27);
		panel_pay.add(lblNewLabel_3);
		
		JDateChooser dCpay_begin = new JDateChooser();
		dCpay_begin.setBounds(107, 78, 198, 27);
		dCpay_begin.setDateFormatString("yyyy-MM-dd");
		panel_pay.add(dCpay_begin);
		
		JDateChooser dCpay_end = new JDateChooser();
		dCpay_end.setBounds(362, 79, 198, 27);
		dCpay_end.setDateFormatString("yyyy-MM-dd");
		panel_pay.add(dCpay_end);
		
		JLabel lbldash = new JLabel("-");
		lbldash.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbldash.setHorizontalAlignment(SwingConstants.CENTER);
		lbldash.setBounds(306, 79, 55, 27);
		panel_pay.add(lbldash);
		
		JLabel lblBigDate = new JLabel("Date");
		lblBigDate.setForeground(new Color(255, 0, 0));
		lblBigDate.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblBigDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblBigDate.setBounds(362, 10, 363, 58);
		panel_pay.add(lblBigDate);
		lblBigDate.setVisible(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 113, 479, 401);
		panel_pay.add(scrollPane);
		
		table_PayPrimary = new JTable();
		scrollPane.setViewportView(table_PayPrimary);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(498, 113, 479, 401);
		panel_pay.add(scrollPane_1);
		
		table_PaySecondary = new JTable();
		scrollPane_1.setViewportView(table_PaySecondary);
		
		JButton btn_Search = new JButton("Search");
		btn_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (dCpay_begin.getDate() == null && dCpay_end.getDate() != null)
				{
					JOptionPane.showMessageDialog(ReportFrame.this, "Please input Begin Date");
					dCpay_begin.requestFocusInWindow();
					return;
				}
				else if (dCpay_begin.getDate() != null && dCpay_end.getDate() == null)
				{
					JOptionPane.showMessageDialog(ReportFrame.this, "Please input End Date");
					dCpay_end.requestFocusInWindow();
					return;
				}
				
				if (!comboBox_payName.getSelectedItem().toString().equals("") && dCpay_begin.getDate() == null && dCpay_end.getDate() == null)
				{
//					System.out.println("ByName");
					loadSumReportbyName(comboBox_payName.getSelectedItem().toString());
					loadReportbyName(comboBox_payName.getSelectedItem().toString());
				}
				else if (comboBox_payName.getSelectedItem().toString().equals("") && dCpay_begin.getDate() != null && dCpay_end.getDate() != null)
				{
//					System.out.println("ByDate");
					Date myDateBegin = (dCpay_begin.getDate());
					Date myDateEnd = (dCpay_end.getDate());
					sqlDateBegin = new java.sql.Date(myDateBegin.getTime());
					sqlDateEnd = new java.sql.Date(myDateEnd.getTime());
					loadSumReportbyDate(sqlDateBegin.toString(), sqlDateEnd.toString());
					loadReportbyDate(sqlDateBegin.toString(), sqlDateEnd.toString());
				}
				else if (!comboBox_payName.getSelectedItem().toString().equals("") && dCpay_begin.getDate() != null && dCpay_end.getDate() != null)
				{
//					System.out.println("ByNameDate");
					Date myDateBegin = (dCpay_begin.getDate());
					Date myDateEnd = (dCpay_end.getDate());
					sqlDateBegin = new java.sql.Date(myDateBegin.getTime());
					sqlDateEnd = new java.sql.Date(myDateEnd.getTime());
					loadSumReportbyNameandDate(comboBox_payName.getSelectedItem().toString(), sqlDateBegin.toString(), sqlDateEnd.toString());
					loadReportbyNameandDate(comboBox_payName.getSelectedItem().toString(), sqlDateBegin.toString(), sqlDateEnd.toString());
				}
				else
				{
//					System.out.println("ByNothing");
					loadAllSumReport();
					loadAllReport();
				}
				
				String beginDate = ""+dCpay_begin.getDate().toString().substring(4,10);
				String endDate = ""+dCpay_end.getDate().toString().substring(4,10);
				System.out.println(beginDate);
				System.out.println(endDate);
				
				if(beginDate.equals(endDate))
				{
					lblBigDate.setVisible(true);
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					lblBigDate.setText(""+formatter.format(dCpay_begin.getDate()));
				}
				else
				{
					lblBigDate.setVisible(false);
				}
				
			}
		});
		btn_Search.setBackground(Color.YELLOW);
		btn_Search.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_Search.setBounds(581, 79, 95, 27);
		panel_pay.add(btn_Search);
		
		JButton btn_payclear = new JButton("CLEAR");
		btn_payclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_payName.setSelectedIndex(0);
				dCpay_begin.setDate(null);
				dCpay_end.setDate(null);
			}
		});
		btn_payclear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_payclear.setBounds(272, 35, 89, 23);
		panel_pay.add(btn_payclear);
		
		JPanel panel_getback = new JPanel();
		panel_getback.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		layeredPane.add(panel_getback, "name_589866846376800");
		panel_getback.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("GetBack");
		lblNewLabel_1.setForeground(new Color(34, 139, 34));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(794, 10, 183, 91);
		panel_getback.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Name :");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_1.setBounds(10, 35, 59, 27);
		panel_getback.add(lblNewLabel_2_1);
		
		JComboBox comboBox_gbName = new JComboBox(list_nickname.toArray(new String[0]));
		comboBox_gbName.setBounds(77, 36, 110, 27);
		panel_getback.add(comboBox_gbName);
		
		JLabel lblNewLabel_3_1 = new JLabel("Date Range :");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3_1.setBounds(10, 78, 95, 27);
		panel_getback.add(lblNewLabel_3_1);
		
		JDateChooser dCgb_begin = new JDateChooser();
		dCgb_begin.setBounds(107, 78, 198, 27);
		dCgb_begin.setDateFormatString("yyyy-MM-dd");
		panel_getback.add(dCgb_begin);
		
		JDateChooser dCgb_end = new JDateChooser();
		dCgb_end.setBounds(362, 79, 198, 27);
		dCgb_end.setDateFormatString("yyyy-MM-dd");
		panel_getback.add(dCgb_end);
		
		JLabel lbldash_1 = new JLabel("-");
		lbldash_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbldash_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbldash_1.setBounds(306, 79, 55, 27);
		panel_getback.add(lbldash_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 116, 479, 401);
		panel_getback.add(scrollPane_2);
		
		table_gb1 = new JTable();
		scrollPane_2.setViewportView(table_gb1);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(498, 116, 479, 401);
		panel_getback.add(scrollPane_1_1);
		
		table_gb2 = new JTable();
		scrollPane_1_1.setViewportView(table_gb2);
		
		JButton btn_gb_search = new JButton("Search");
		btn_gb_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (dCgb_begin.getDate() == null && dCgb_end.getDate() != null)
				{
					JOptionPane.showMessageDialog(ReportFrame.this, "Please input Begin Date");
					dCgb_begin.requestFocusInWindow();
					return;
				}
				else if (dCgb_begin.getDate() != null && dCgb_end.getDate() == null)
				{
					JOptionPane.showMessageDialog(ReportFrame.this, "Please input End Date");
					dCgb_end.requestFocusInWindow();
					return;
				}
				
				if (!comboBox_gbName.getSelectedItem().toString().equals("") && dCgb_begin.getDate() == null && dCgb_end.getDate() == null)
				{
//					System.out.println("ByName2");
					loadAllGetBackReportbyName(comboBox_gbName.getSelectedItem().toString());
					loadGetBackSumReportByName(comboBox_gbName.getSelectedItem().toString());
				}
				else if (comboBox_gbName.getSelectedItem().toString().equals("") && dCgb_begin.getDate() != null && dCgb_end.getDate() != null)
				{
//					System.out.println("ByDate2");
					Date myDateGBBegin = (dCgb_begin.getDate());
					Date myDateGBEnd = (dCgb_end.getDate());
					sqlDateGBBegin = new java.sql.Date(myDateGBBegin.getTime());
					sqlDateGBEnd = new java.sql.Date(myDateGBEnd.getTime());
					loadAllGetBackReportbyDate(sqlDateGBBegin.toString(), sqlDateGBEnd.toString());
					loadGetBackSumReportByDate(sqlDateGBBegin.toString(), sqlDateGBEnd.toString());
				}
				else if (!comboBox_gbName.getSelectedItem().toString().equals("") && dCgb_begin.getDate() != null && dCgb_end.getDate() != null)
				{
//					System.out.println("ByNameDate2");
					Date myDateGBBegin = (dCgb_begin.getDate());
					Date myDateGBEnd = (dCgb_end.getDate());
					sqlDateGBBegin = new java.sql.Date(myDateGBBegin.getTime());
					sqlDateGBEnd = new java.sql.Date(myDateGBEnd.getTime());
					loadAllGetBackReportbyNameDate(comboBox_gbName.getSelectedItem().toString(), sqlDateGBBegin.toString(), sqlDateGBEnd.toString());
					loadGetBackSumReportByNameDate(comboBox_gbName.getSelectedItem().toString(), sqlDateGBBegin.toString(), sqlDateGBEnd.toString());
				}
				else
				{
//					System.out.println("ByNothing2");
					loadAllGetBackSumReport();
					loadAllGetBackReport();
				}
				
			}
		});
		btn_gb_search.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_gb_search.setBackground(Color.YELLOW);
		btn_gb_search.setBounds(581, 79, 95, 27);
		panel_getback.add(btn_gb_search);
		
		btn_gbclear = new JButton("CLEAR");
		btn_gbclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_gbName.setSelectedIndex(0);
				dCgb_begin.setDate(null);
				dCgb_end.setDate(null);
			}
		});
		btn_gbclear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_gbclear.setBounds(272, 38, 89, 23);
		panel_getback.add(btn_gbclear);
		
		JButton btnNewButton = new JButton("Switch Panel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(panelcount == 0)
				{
					layeredPane.removeAll();
					layeredPane.add(panel_getback);
					layeredPane.repaint();
					layeredPane.revalidate();
					panelcount = 1;
				}
				else
				{
					layeredPane.removeAll();
					layeredPane.add(panel_pay);
					layeredPane.repaint();
					layeredPane.revalidate();
					panelcount = 0;
				}
			}
		});
		btnNewButton.setBounds(430, 11, 137, 24);
		contentPane.add(btnNewButton);
		
		JButton btn_savePicture = new JButton("Save As Picture");
		btn_savePicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveImage(contentPane);
			}
		});
		btn_savePicture.setForeground(Color.BLACK);
		btn_savePicture.setBackground(new Color(50, 205, 50));
		btn_savePicture.setBounds(850, 11, 147, 23);
		contentPane.add(btn_savePicture);
		
		loadAllSumReport();
		loadAllReport();
		loadAllGetBackSumReport();
		loadAllGetBackReport();
	}
	
	public ArrayList<String> loadnickname()
	{
		list_user = UserManager.getAllUser();
		list_nickname = new ArrayList<String>();
		list_nickname.add("");
		for (UserDB c: list_user)
		{
			list_nickname.add(c.nickname);
		}
		return list_nickname;
	}
	
	public void loadAllSumReport()
	{
		//Create new arraylist for result
		list = ReportManager.getAllSumReport();
		// ตัวแปรชื่อ model (ต้อง cast)
		DefaultTableModel model = new DefaultTableModel();
		//สร้าง column
		model.addColumn("Name");
		model.addColumn("SUM Payment");
		model.addColumn("toWho");
		
		//วน loop ตามทุกตัวที่อยู่ใน list
		for (ReportDB c: list)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model.addRow(new Object[] {c.payerName, c.payment, c.toWhoName});
		}
		
		table_PayPrimary.setModel(model);
		
		//Sort table
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_PayPrimary.getModel());
		table_PayPrimary.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	}
	
	public void loadSumReportbyName(String x)
	{
		//Create new arraylist for result
		list = ReportManager.getSumReportbyName(x);
		// ตัวแปรชื่อ model (ต้อง cast)
		DefaultTableModel model = new DefaultTableModel();
		//สร้าง column
		model.addColumn("Name");
		model.addColumn("SUM Payment");
		model.addColumn("toWho");
		
		//วน loop ตามทุกตัวที่อยู่ใน list
		for (ReportDB c: list)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model.addRow(new Object[] {c.payerName, c.payment, c.toWhoName});
		}
		
		table_PayPrimary.setModel(model);
		
		//Sort table
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_PayPrimary.getModel());
		table_PayPrimary.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	}
	
	public void loadSumReportbyDate(String x1, String x2)
	{
		//Create new arraylist for result
		list = ReportManager.getSumReportbyDate(x1, x2);
		// ตัวแปรชื่อ model (ต้อง cast)
		DefaultTableModel model = new DefaultTableModel();
		//สร้าง column
		model.addColumn("Name");
		model.addColumn("SUM Payment");
		model.addColumn("toWho");
		
		//วน loop ตามทุกตัวที่อยู่ใน list
		for (ReportDB c: list)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model.addRow(new Object[] {c.payerName, c.payment, c.toWhoName});
		}
		
		table_PayPrimary.setModel(model);
		
		//Sort table
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_PayPrimary.getModel());
		table_PayPrimary.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	}
	
	public void loadSumReportbyNameandDate(String x1, String x2, String x3)
	{
		//Create new arraylist for result
		list = ReportManager.getSumReportbyNameDate(x1, x2, x3);
		// ตัวแปรชื่อ model (ต้อง cast)
		DefaultTableModel model = new DefaultTableModel();
		//สร้าง column
		model.addColumn("Name");
		model.addColumn("SUM Payment");
		model.addColumn("toWho");
		
		//วน loop ตามทุกตัวที่อยู่ใน list
		for (ReportDB c: list)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model.addRow(new Object[] {c.payerName, c.payment, c.toWhoName});
		}
		
		table_PayPrimary.setModel(model);
		
		//Sort table
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_PayPrimary.getModel());
		table_PayPrimary.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	}
	
	public void loadAllReport()
	{
		//Create new arraylist for result
		list2 = ReportManager.getAllReport();
		// ตัวแปรชื่อ model (ต้อง cast)
		DefaultTableModel model2 = new DefaultTableModel();
		//สร้าง column
		model2.addColumn("Name");
		model2.addColumn("Payment");
		model2.addColumn("toWho");
		model2.addColumn("Date");
		model2.addColumn("POT#");
		
		//วน loop ตามทุกตัวที่อยู่ใน list
		for (ReportDB c: list2)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model2.addRow(new Object[] {c.payerName, c.payment, c.toWhoName, c.theDate, c.potno});
		}
		
		table_PaySecondary.setModel(model2);
		
		//Sort table
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_PaySecondary.getModel());
		table_PaySecondary.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	}
	
	public void loadReportbyName(String x)
	{
		//Create new arraylist for result
		list2 = ReportManager.getReportbyName(x);
		// ตัวแปรชื่อ model (ต้อง cast)
		DefaultTableModel model2 = new DefaultTableModel();
		//สร้าง column
		model2.addColumn("Name");
		model2.addColumn("Payment");
		model2.addColumn("toWho");
		model2.addColumn("Date");
		model2.addColumn("POT#");
		
		//วน loop ตามทุกตัวที่อยู่ใน list
		for (ReportDB c: list2)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model2.addRow(new Object[] {c.payerName, c.payment, c.toWhoName, c.theDate, c.potno});
		}
		
		table_PaySecondary.setModel(model2);
		
		//Sort table
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_PaySecondary.getModel());
		table_PaySecondary.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	}
	
	public void loadReportbyDate(String x1, String x2)
	{
		//Create new arraylist for result
		list2 = ReportManager.getReportbyDate(x1, x2);
		// ตัวแปรชื่อ model (ต้อง cast)
		DefaultTableModel model2 = new DefaultTableModel();
		//สร้าง column
		model2.addColumn("Name");
		model2.addColumn("Payment");
		model2.addColumn("toWho");
		model2.addColumn("Date");
		model2.addColumn("POT#");
		
		//วน loop ตามทุกตัวที่อยู่ใน list
		for (ReportDB c: list2)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model2.addRow(new Object[] {c.payerName, c.payment, c.toWhoName, c.theDate, c.potno});
		}
		
		table_PaySecondary.setModel(model2);
		
		//Sort table
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_PaySecondary.getModel());
		table_PaySecondary.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	}
	
	public void loadReportbyNameandDate(String x1, String x2, String x3)
	{
		//Create new arraylist for result
		list2 = ReportManager.getReportbyNameDate(x1, x2, x3);
		// ตัวแปรชื่อ model (ต้อง cast)
		DefaultTableModel model2 = new DefaultTableModel();
		//สร้าง column
		model2.addColumn("Name");
		model2.addColumn("Payment");
		model2.addColumn("toWho");
		model2.addColumn("Date");
		model2.addColumn("POT#");
		
		//วน loop ตามทุกตัวที่อยู่ใน list
		for (ReportDB c: list2)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model2.addRow(new Object[] {c.payerName, c.payment, c.toWhoName, c.theDate, c.potno});
		}
		
		table_PaySecondary.setModel(model2);
		
		//Sort table
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_PaySecondary.getModel());
		table_PaySecondary.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	}
	
	public void loadAllGetBackSumReport()
	{
		//Create new arraylist for result
		list = ReportManager.getAllGetBackSumReport();
		// ตัวแปรชื่อ model (ต้อง cast)
		DefaultTableModel model = new DefaultTableModel();
		//สร้าง column
		model.addColumn("Name");
		model.addColumn("SUM Get Back");
		model.addColumn("from Who");
		
		//วน loop ตามทุกตัวที่อยู่ใน list
		for (ReportDB c: list)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model.addRow(new Object[] {c.payerName, c.payment, c.toWhoName});
		}
		
		table_gb1.setModel(model);
		
		//Sort table
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_gb1.getModel());
		table_gb1.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	}
	
	public void loadGetBackSumReportByName(String x)
	{
		//Create new arraylist for result
		list = ReportManager.getGetBackSumReportbyName(x);
		// ตัวแปรชื่อ model (ต้อง cast)
		DefaultTableModel model = new DefaultTableModel();
		//สร้าง column
		model.addColumn("Name");
		model.addColumn("SUM Get Back");
		model.addColumn("from Who");
		
		//วน loop ตามทุกตัวที่อยู่ใน list
		for (ReportDB c: list)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model.addRow(new Object[] {c.payerName, c.payment, c.toWhoName});
		}
		
		table_gb1.setModel(model);
		
		//Sort table
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_gb1.getModel());
		table_gb1.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	}
	
	public void loadGetBackSumReportByDate(String x1, String x2)
	{
		//Create new arraylist for result
		list = ReportManager.getGetBackSumReportbyDate(x1, x2);
		// ตัวแปรชื่อ model (ต้อง cast)
		DefaultTableModel model = new DefaultTableModel();
		//สร้าง column
		model.addColumn("Name");
		model.addColumn("SUM Get Back");
		model.addColumn("from Who");
		
		//วน loop ตามทุกตัวที่อยู่ใน list
		for (ReportDB c: list)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model.addRow(new Object[] {c.payerName, c.payment, c.toWhoName});
		}
		
		table_gb1.setModel(model);
		
		//Sort table
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_gb1.getModel());
		table_gb1.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	}
	
	public void loadGetBackSumReportByNameDate(String x1, String x2, String x3)
	{
		//Create new arraylist for result
		list = ReportManager.getGetBackSumReportbyNameDate(x1, x2, x3);
		// ตัวแปรชื่อ model (ต้อง cast)
		DefaultTableModel model = new DefaultTableModel();
		//สร้าง column
		model.addColumn("Name");
		model.addColumn("SUM Get Back");
		model.addColumn("from Who");
		
		//วน loop ตามทุกตัวที่อยู่ใน list
		for (ReportDB c: list)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model.addRow(new Object[] {c.payerName, c.payment, c.toWhoName});
		}
		
		table_gb1.setModel(model);
		
		//Sort table
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_gb1.getModel());
		table_gb1.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	}
	
	public void loadAllGetBackReport()
	{
		//Create new arraylist for result
		list = ReportManager.getAllGetBackReport();
		// ตัวแปรชื่อ model (ต้อง cast)
		DefaultTableModel model = new DefaultTableModel();
		//สร้าง column
		model.addColumn("Name");
		model.addColumn("Get Back");
		model.addColumn("from Who");
		model.addColumn("Date");
		model.addColumn("POT#");
		
		//วน loop ตามทุกตัวที่อยู่ใน list
		for (ReportDB c: list)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model.addRow(new Object[] {c.payerName, c.payment, c.toWhoName, c.theDate, c.potno});
		}
		
		table_gb2.setModel(model);
		
		//Sort table
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_gb2.getModel());
		table_gb2.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	}
	
	public void loadAllGetBackReportbyName(String x)
	{
		//Create new arraylist for result
		list = ReportManager.getAllGetBackReportbyName(x);
		// ตัวแปรชื่อ model (ต้อง cast)
		DefaultTableModel model = new DefaultTableModel();
		//สร้าง column
		model.addColumn("Name");
		model.addColumn("Get Back");
		model.addColumn("from Who");
		model.addColumn("Date");
		model.addColumn("POT#");
		
		//วน loop ตามทุกตัวที่อยู่ใน list
		for (ReportDB c: list)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model.addRow(new Object[] {c.payerName, c.payment, c.toWhoName, c.theDate, c.potno});
		}
		
		table_gb2.setModel(model);
		
		//Sort table
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_gb2.getModel());
		table_gb2.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	}
	
	public void loadAllGetBackReportbyDate(String x1, String x2)
	{
		//Create new arraylist for result
		list = ReportManager.getAllGetBackReportbyDate(x1, x2);
		// ตัวแปรชื่อ model (ต้อง cast)
		DefaultTableModel model = new DefaultTableModel();
		//สร้าง column
		model.addColumn("Name");
		model.addColumn("Get Back");
		model.addColumn("from Who");
		model.addColumn("Date");
		model.addColumn("POT#");
		
		//วน loop ตามทุกตัวที่อยู่ใน list
		for (ReportDB c: list)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model.addRow(new Object[] {c.payerName, c.payment, c.toWhoName, c.theDate, c.potno});
		}
		
		table_gb2.setModel(model);
		
		//Sort table
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_gb2.getModel());
		table_gb2.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	}
	
	public void loadAllGetBackReportbyNameDate(String x1, String x2, String x3)
	{
		//Create new arraylist for result
		list = ReportManager.getAllGetBackReportbyNameDate(x1, x2, x3);
		// ตัวแปรชื่อ model (ต้อง cast)
		DefaultTableModel model = new DefaultTableModel();
		//สร้าง column
		model.addColumn("Name");
		model.addColumn("Get Back");
		model.addColumn("from Who");
		model.addColumn("Date");
		model.addColumn("POT#");
		
		//วน loop ตามทุกตัวที่อยู่ใน list
		for (ReportDB c: list)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model.addRow(new Object[] {c.payerName, c.payment, c.toWhoName, c.theDate, c.potno});
		}
		
		table_gb2.setModel(model);
		
		//Sort table
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_gb2.getModel());
		table_gb2.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	}
	
	private void saveImage(JPanel p) {
        int w = p.getWidth(), h = p.getHeight();
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.createGraphics();
		p.setVisible(true);
		p.paint(g);
       // Browse WHERE to save file (open file dialog)
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Specify location to save the picture");

		int returnVal = fc.showSaveDialog(ReportFrame.this);
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			File fileToSave = fc.getSelectedFile();
			try
			{
				ImageIO.write(image, "jpeg", new File(fileToSave+".jpeg"));
			} catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}
    }
}


