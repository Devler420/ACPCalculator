package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import C.GlobalData;
import M.CalculatorDB;
import M.CalculatorManager;
import M.UserDB;
import M.UserManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.awt.Font;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JTextField;

public class MainResultFrame extends JFrame
{

	private JPanel contentPane;
	private JTable table;
	ArrayList<CalculatorDB> list;
	private JTextField textField_date;

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
					MainResultFrame frame = new MainResultFrame();
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
	public MainResultFrame()
	{
		setTitle("Result Database");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1023, 500);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("USER :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 74, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(GlobalData.CurrentUser_nickname);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(70, 11, 134, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Result Database");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel_2.setBounds(10, 70, 337, 26);
		contentPane.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 107, 829, 346);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRowCount() < 1)
				{
					return;
				}
				
				int tableindex = table.getSelectedRow();
				
				int mybilling_id = Integer.parseInt(table.getValueAt(tableindex, 0).toString());
				String myDate = table.getValueAt(tableindex, 1).toString();
				int numofpot = Integer.parseInt(table.getValueAt(tableindex, 2).toString());
				String pottype = table.getValueAt(tableindex, 3).toString();
				double totalcup = Double.parseDouble(table.getValueAt(tableindex, 4).toString());
				double total_ppp = Double.parseDouble(table.getValueAt(tableindex, 5).toString());
				double total_ppc = Double.parseDouble(table.getValueAt(tableindex, 6).toString());
				String createdby_users = table.getValueAt(tableindex, 7).toString();
				
				GlobalData.CurrentResult_billing_id = mybilling_id;
				GlobalData.CurrentResult_date = myDate;
				GlobalData.CurrentResult_potno = numofpot;
				GlobalData.CurrentResult_pottype = pottype;
				GlobalData.CurrentResult_totalcup = totalcup;
				GlobalData.CurrentResult_user_nickname = createdby_users;
				
				ResultFrame f = new ResultFrame();
				f.setVisible(true);
				
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(table);
		
		JButton btnNewCal = new JButton("NEW");
		btnNewCal.setBackground(Color.YELLOW);
		btnNewCal.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculatorFrame f = new CalculatorFrame();
				f.setVisible(true);
			}
		});
		btnNewCal.setBounds(861, 107, 128, 40);
		contentPane.add(btnNewCal);
		
		JButton btnDeleteCal = new JButton("DELETE"); //DELTE
		btnDeleteCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!GlobalData.CurrentUser_nickname.equals("Boom"))
				{
					JOptionPane.showMessageDialog(MainResultFrame.this, "Permission Denied");
					return;
				}
				
				if (JOptionPane.showConfirmDialog(MainResultFrame.this, "Are you sure you want to Delete?", "Confirm Delete?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
				{
					CalculatorManager.deleteBill();
					load();
					JOptionPane.showMessageDialog(MainResultFrame.this, "Delete Complete");
				}
				else
				{
					return;
				}
				
			}
		});
		btnDeleteCal.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDeleteCal.setBounds(861, 259, 128, 40);
		contentPane.add(btnDeleteCal);
		
		JButton btn_refresh = new JButton("Refresh");
		btn_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		btn_refresh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_refresh.setBounds(711, 67, 128, 33);
		contentPane.add(btn_refresh);
		
		textField_date = new JTextField();
		textField_date.setBounds(579, 67, 114, 33);
		contentPane.add(textField_date);
		textField_date.setColumns(10);
		
		JButton btn_search = new JButton("Search (By Date)");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchbyDate();
			}
		});
		btn_search.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_search.setBounds(435, 67, 134, 33);
		contentPane.add(btn_search);
		
		load();
	}
	
	public void load()
	{
		//Create new arraylist for result
		list = CalculatorManager.getAllResult();
		// ตัวแปรชื่อ model (ต้อง cast)
		DefaultTableModel model = new DefaultTableModel();
		//สร้าง column
		model.addColumn("Billing_id");
		model.addColumn("Date");
		model.addColumn("POT NO#");
		model.addColumn("Type");
		model.addColumn("Totalcup");
		model.addColumn("Price_byPOT");
		model.addColumn("Price_byCUP");
		model.addColumn("Created_by");
		//model.addColumn("edited_by_users");
		
		//วน loop ตามทุกตัวที่อยู่ใน list
		for (CalculatorDB c: list)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model.addRow(new Object[] {c.billing_id, c.billing_date, c.numofpot, c.type, c.totalcup,c.price_ppp,c.price_ppc, c.created_by_users});
		}
		
		table.setModel(model);
		
		//Sort table
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	}
	
	public void searchbyDate()
	{
		list = CalculatorManager.searchAllResultByDate(textField_date.getText().toString());
		DefaultTableModel model = new DefaultTableModel();
		//สร้าง column
		model.addColumn("Billing_id");
		model.addColumn("Date");
		model.addColumn("POT NO#");
		model.addColumn("Type");
		model.addColumn("Totalcup");
		model.addColumn("Price_byPOT");
		model.addColumn("Price_byCUP");
		model.addColumn("Created_by");
		//model.addColumn("edited_by_users");
		
		//วน loop ตามทุกตัวที่อยู่ใน list
		for (CalculatorDB c: list)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model.addRow(new Object[] {c.billing_id, c.billing_date, c.numofpot, c.type, c.totalcup,c.price_ppp,c.price_ppc, c.created_by_users});
		}
		
		table.setModel(model);
		
		//Sort table
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	}
}
