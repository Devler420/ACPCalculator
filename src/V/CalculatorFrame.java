package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import C.GlobalData;
import M.CalculatorDB;
import M.CalculatorManager;
import M.DetailedResultManager;
import M.DrinkerDB;
import M.ItemDB;
import M.SetPaymentDB;
import M.UserDB;
import M.UserManager;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.CardLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Calendar;
import com.toedter.calendar.JCalendar;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JProgressBar;

public class CalculatorFrame extends JFrame
{

	private JPanel contentPane;
	private JTextField textField_price1;
	private JTextField textField_price2;
	private JTextField textField_price3;
	private JTextField textField_price4;
	private JTextField textField_price5;
	private JTextField textField_price6;
	private JComboBox comboBox_drinker1;
	private JComboBox comboBox_drinker2;
	private JComboBox comboBox_drinker3;
	private JComboBox comboBox_drinker4;
	private JComboBox comboBox_drinker5;
	private JComboBox comboBox_drinker6;
	private JComboBox comboBox_drinker7;
	private JComboBox comboBox_drinker8;
	ArrayList<UserDB> list_user;
	ArrayList<String> list_nickname;
	ArrayList<String> list_nickname_drinker;
	private JRadioButton rdbtnByPOT;
	private JRadioButton rdbtnByCUP;
	private String cuptype;
	private double totalcup;
	private double totalPerPot;
	private double countDrinker;
	java.sql.Date sqlDate;
	private JDateChooser dateChooser;
	private JTextField textField_advpay1;
	private JTextField textField_advpay2;
	private JTextField textField_advpay3;
	private JTextField textField_advpay4;
	private JTextField textField_advpay5;
	private JTextField textField_advpay6;
	private JTextField textField_advpay7;
	private JTextField textField_advpay8;
	private JTextField textField_getback1;
	private JTextField textField_getback2;
	private JTextField textField_getback3;
	private JTextField textField_getback4;
	private JTextField textField_getback5;
	private JTextField textField_getback6;
	private JTextField textField_getback7;
	private JTextField textField_getback8;
	private JComboBox comboBox_cup1;
	private JComboBox comboBox_cup2;
	private JComboBox comboBox_cup3;
	private JComboBox comboBox_cup4;
	private JComboBox comboBox_cup5;
	private JComboBox comboBox_cup6;
	private JComboBox comboBox_cup7;
	private JComboBox comboBox_cup8;
	private JComboBox comboBox_sup1;
	private JComboBox comboBox_sup2;
	private JComboBox comboBox_sup3;
	private JComboBox comboBox_sup4;
	private JComboBox comboBox_sup5;
	private JComboBox comboBox_sup6;
	
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
					CalculatorFrame frame = new CalculatorFrame();
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
	public CalculatorFrame()
	{
		setTitle("Calculate");
		//Load nickname for CBO
		loadnickname();
		loadnicknameDrinker();
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 804, 485);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 121, 768, 308);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel_bypot = new JPanel();
		layeredPane.add(panel_bypot, "name_163565903196200");
		panel_bypot.setLayout(null);
		
		JPanel panel_bycup = new JPanel();
		panel_bycup.setBounds(489, 11, 82, 287);
		panel_bypot.add(panel_bycup);
		panel_bycup.setLayout(null);
		panel_bycup.setVisible(false);
		
		JLabel lbl_Cup = new JLabel("Cup");
		lbl_Cup.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_Cup.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Cup.setBounds(10, 11, 58, 13);
		panel_bycup.add(lbl_Cup);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(51, 74, 147, 30);
		contentPane.add(dateChooser);
		dateChooser.setDateFormatString("dd/MM/yyyy");
		
		JSpinner spinner_potno = new JSpinner();
		spinner_potno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinner_potno.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinner_potno.setBounds(259, 74, 45, 30);
		contentPane.add(spinner_potno);
		
		Double[] numberofcup = new Double[11];
		double inc=0;
		for(int i=0;i<11;i++){
		   	numberofcup[i]= inc;
		    inc += 0.5;
		}
		
		comboBox_cup1 = new JComboBox(numberofcup);
		comboBox_cup1.setBounds(10, 35, 58, 25);
		panel_bycup.add(comboBox_cup1);
		
		comboBox_cup2 = new JComboBox(numberofcup);
		comboBox_cup2.setEnabled(false);
		comboBox_cup2.setBounds(10, 66, 58, 25);
		panel_bycup.add(comboBox_cup2);
		
		comboBox_cup3 = new JComboBox(numberofcup);
		comboBox_cup3.setEnabled(false);
		comboBox_cup3.setBounds(10, 97, 58, 25);
		panel_bycup.add(comboBox_cup3);
		
		comboBox_cup4 = new JComboBox(numberofcup);
		comboBox_cup4.setEnabled(false);
		comboBox_cup4.setBounds(10, 128, 58, 25);
		panel_bycup.add(comboBox_cup4);
		
		comboBox_cup5 = new JComboBox(numberofcup);
		comboBox_cup5.setEnabled(false);
		comboBox_cup5.setBounds(10, 159, 58, 25);
		panel_bycup.add(comboBox_cup5);
		
		comboBox_cup6 = new JComboBox(numberofcup);
		comboBox_cup6.setEnabled(false);
		comboBox_cup6.setBounds(10, 190, 58, 25);
		panel_bycup.add(comboBox_cup6);
		
		comboBox_cup7 = new JComboBox(numberofcup);
		comboBox_cup7.setEnabled(false);
		comboBox_cup7.setBounds(10, 222, 58, 25);
		panel_bycup.add(comboBox_cup7);
		
		comboBox_cup8 = new JComboBox(numberofcup);
		comboBox_cup8.setEnabled(false);
		comboBox_cup8.setBounds(10, 254, 58, 25);
		panel_bycup.add(comboBox_cup8);
		
		rdbtnByPOT = new JRadioButton("By POT");
		rdbtnByPOT.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnByPOT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnByCUP.setSelected(false);
				cuptype = "ByPOT";
				
				panel_bypot.setVisible(true);
				panel_bycup.setVisible(false);
			}
		});
		rdbtnByPOT.setBounds(10, 36, 103, 31);
		contentPane.add(rdbtnByPOT);
		
		rdbtnByCUP = new JRadioButton("By CUP");
		rdbtnByCUP.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnByCUP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnByPOT.setSelected(false);
				cuptype = "ByCUP";
				
				panel_bycup.setVisible(true);
			}
		});
		rdbtnByCUP.setBounds(171, 36, 103, 31);
		contentPane.add(rdbtnByCUP);
		
		
		JLabel lblNewLabel = new JLabel("USER :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(11, 11, 71, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lbl_current_user = new JLabel(GlobalData.CurrentUser_nickname);
		lbl_current_user.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_current_user.setBounds(96, 11, 130, 28);
		contentPane.add(lbl_current_user);
		
		JLabel lblNewLabel_3 = new JLabel("Item");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(21, 23, 70, 13);
		panel_bypot.add(lblNewLabel_3);
		
		String[] items = {"item1", "item2", "etc", "ice",""};
		
		JComboBox comboBox_item1 = new JComboBox(items);
		comboBox_item1.setBounds(21, 46, 70, 25);
		panel_bypot.add(comboBox_item1);
		
		JComboBox comboBox_item2 = new JComboBox(items);
		comboBox_item2.setSelectedIndex(1);
		comboBox_item2.setBounds(21, 77, 70, 25);
		panel_bypot.add(comboBox_item2);
		
		JComboBox comboBox_item3 = new JComboBox(items);
		comboBox_item3.setSelectedIndex(3);
		comboBox_item3.setBounds(21, 108, 70, 25);
		panel_bypot.add(comboBox_item3);
		
		JComboBox comboBox_item4 = new JComboBox(items);
		comboBox_item4.setSelectedIndex(2);
		comboBox_item4.setBounds(21, 139, 70, 25);
		panel_bypot.add(comboBox_item4);
		
		JComboBox comboBox_item5 = new JComboBox(items);
		comboBox_item5.setSelectedIndex(4);
		comboBox_item5.setBounds(21, 170, 70, 25);
		panel_bypot.add(comboBox_item5);
		
		JComboBox comboBox_item6 = new JComboBox(items);
		comboBox_item6.setSelectedIndex(4);
		comboBox_item6.setBounds(21, 201, 70, 25);
		panel_bypot.add(comboBox_item6);
		
		JLabel lblNewLabel_4 = new JLabel("Price");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(118, 23, 62, 13);
		panel_bypot.add(lblNewLabel_4);
		
		textField_price1 = new JTextField();
		textField_price1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_price1.setBounds(118, 47, 62, 25);
		panel_bypot.add(textField_price1);
		textField_price1.setColumns(10);
		
		textField_price2 = new JTextField();
		textField_price2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_price2.setBounds(118, 78, 62, 25);
		panel_bypot.add(textField_price2);
		textField_price2.setColumns(10);
		
		textField_price3 = new JTextField();
		textField_price3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_price3.setBounds(118, 109, 62, 25);
		panel_bypot.add(textField_price3);
		textField_price3.setColumns(10);
		
		textField_price4 = new JTextField();
		textField_price4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_price4.setBounds(118, 140, 62, 25);
		panel_bypot.add(textField_price4);
		textField_price4.setColumns(10);
		
		textField_price5 = new JTextField();
		textField_price5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_price5.setBounds(118, 171, 62, 25);
		panel_bypot.add(textField_price5);
		textField_price5.setColumns(10);
		
		textField_price6 = new JTextField();
		textField_price6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_price6.setBounds(118, 202, 62, 25);
		panel_bypot.add(textField_price6);
		textField_price6.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Supplier");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(201, 23, 118, 13);
		panel_bypot.add(lblNewLabel_5);
		
		comboBox_sup1 = new JComboBox(list_nickname.toArray(new String[0]));
		comboBox_sup1.setBounds(204, 46, 115, 25);
		panel_bypot.add(comboBox_sup1);
		
		comboBox_sup2 = new JComboBox(list_nickname.toArray(new String[0]));
		comboBox_sup2.setBounds(204, 77, 115, 25);
		panel_bypot.add(comboBox_sup2);
		
		comboBox_sup3 = new JComboBox(list_nickname.toArray(new String[0]));
		comboBox_sup3.setBounds(204, 108, 115, 25);
		panel_bypot.add(comboBox_sup3);
		
		comboBox_sup4 = new JComboBox(list_nickname.toArray(new String[0]));
		comboBox_sup4.setBounds(204, 139, 115, 25);
		panel_bypot.add(comboBox_sup4);
		
		comboBox_sup5 = new JComboBox(list_nickname.toArray(new String[0]));
		comboBox_sup5.setBounds(204, 170, 115, 25);
		panel_bypot.add(comboBox_sup5);
		
		comboBox_sup6 = new JComboBox(list_nickname.toArray(new String[0]));
		comboBox_sup6.setBounds(204, 201, 115, 25);
		panel_bypot.add(comboBox_sup6);
		
		JLabel lblNewLabel_6 = new JLabel("Drinker");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(358, 23, 115, 13);
		panel_bypot.add(lblNewLabel_6);
		
		comboBox_drinker1 = new JComboBox(list_nickname_drinker.toArray(new String[0]));
		comboBox_drinker1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == e.SELECTED)
				{
					comboBox_drinker1.getSelectedIndex();
					list_nickname_drinker.remove(comboBox_drinker1.getSelectedIndex());
					comboBox_drinker2.setEnabled(true);
					comboBox_drinker2.setModel(new DefaultComboBoxModel<>(list_nickname_drinker.toArray()));
					comboBox_cup2.setEnabled(true);
				}
			}
		});
		
		comboBox_drinker1.setBounds(358, 46, 115, 25);
		panel_bypot.add(comboBox_drinker1);
		
		comboBox_drinker2 = new JComboBox();
		comboBox_drinker2.setEnabled(false);
		comboBox_drinker2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == e.SELECTED)
				{
					comboBox_drinker2.getSelectedIndex();
					list_nickname_drinker.remove(comboBox_drinker2.getSelectedIndex());
					comboBox_drinker3.setEnabled(true);
					comboBox_drinker3.setModel(new DefaultComboBoxModel<>(list_nickname_drinker.toArray()));
					comboBox_cup3.setEnabled(true);
				}
			}
		});
		comboBox_drinker2.setBounds(358, 77, 114, 25);
		panel_bypot.add(comboBox_drinker2);
		
		comboBox_drinker3 = new JComboBox();
		comboBox_drinker3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == e.SELECTED)
				{
					comboBox_drinker3.getSelectedIndex();
					list_nickname_drinker.remove(comboBox_drinker3.getSelectedIndex());
					comboBox_drinker4.setEnabled(true);
					comboBox_drinker4.setModel(new DefaultComboBoxModel<>(list_nickname_drinker.toArray()));
					comboBox_cup4.setEnabled(true);
				}
			}
		});
		comboBox_drinker3.setEnabled(false);
		comboBox_drinker3.setBounds(358, 108, 114, 25);
		panel_bypot.add(comboBox_drinker3);
		
		comboBox_drinker4 = new JComboBox();
		comboBox_drinker4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == e.SELECTED)
				{
					comboBox_drinker4.getSelectedIndex();
					list_nickname_drinker.remove(comboBox_drinker4.getSelectedIndex());
					comboBox_drinker5.setEnabled(true);
					comboBox_drinker5.setModel(new DefaultComboBoxModel<>(list_nickname_drinker.toArray()));
					comboBox_cup5.setEnabled(true);
				}
			}
		});
		comboBox_drinker4.setEnabled(false);
		comboBox_drinker4.setBounds(358, 139, 114, 25);
		panel_bypot.add(comboBox_drinker4);
		
		comboBox_drinker5 = new JComboBox();
		comboBox_drinker5.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == e.SELECTED)
				{
					comboBox_drinker5.getSelectedIndex();
					list_nickname_drinker.remove(comboBox_drinker5.getSelectedIndex());
					comboBox_drinker6.setEnabled(true);
					comboBox_drinker6.setModel(new DefaultComboBoxModel<>(list_nickname_drinker.toArray()));
					comboBox_cup6.setEnabled(true);
				}
			}
		});
		comboBox_drinker5.setEnabled(false);
		comboBox_drinker5.setBounds(358, 170, 114, 25);
		panel_bypot.add(comboBox_drinker5);
		
		comboBox_drinker6 = new JComboBox();
		comboBox_drinker6.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == e.SELECTED)
				{
					comboBox_drinker6.getSelectedIndex();
					list_nickname_drinker.remove(comboBox_drinker6.getSelectedIndex());
					comboBox_drinker7.setEnabled(true);
					comboBox_drinker7.setModel(new DefaultComboBoxModel<>(list_nickname_drinker.toArray()));
					comboBox_cup7.setEnabled(true);
				}
			}
		});
		comboBox_drinker6.setEnabled(false);
		comboBox_drinker6.setBounds(358, 201, 114, 25);
		panel_bypot.add(comboBox_drinker6);
		
		comboBox_drinker7 = new JComboBox();
		comboBox_drinker7.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == e.SELECTED)
				{
					comboBox_drinker7.getSelectedIndex();
					list_nickname_drinker.remove(comboBox_drinker7.getSelectedIndex());
					comboBox_drinker8.setEnabled(true);
					comboBox_drinker8.setModel(new DefaultComboBoxModel<>(list_nickname_drinker.toArray()));
					comboBox_cup8.setEnabled(true);
				}
			}
		});
		comboBox_drinker7.setEnabled(false);
		comboBox_drinker7.setBounds(358, 233, 114, 25);
		panel_bypot.add(comboBox_drinker7);
		
		comboBox_drinker8 = new JComboBox();
		comboBox_drinker8.setEnabled(false);
		comboBox_drinker8.setBounds(358, 265, 114, 25);
		panel_bypot.add(comboBox_drinker8);
		
		textField_advpay1 = new JTextField();
		textField_advpay1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_advpay1.setEditable(false);
		textField_advpay1.setBounds(592, 46, 70, 25);
		panel_bypot.add(textField_advpay1);
		textField_advpay1.setColumns(10);
		
		textField_advpay2 = new JTextField();
		textField_advpay2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_advpay2.setEditable(false);
		textField_advpay2.setColumns(10);
		textField_advpay2.setBounds(592, 78, 70, 25);
		panel_bypot.add(textField_advpay2);
		
		textField_advpay3 = new JTextField();
		textField_advpay3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_advpay3.setEditable(false);
		textField_advpay3.setColumns(10);
		textField_advpay3.setBounds(592, 109, 70, 25);
		panel_bypot.add(textField_advpay3);
		
		textField_advpay4 = new JTextField();
		textField_advpay4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_advpay4.setEditable(false);
		textField_advpay4.setColumns(10);
		textField_advpay4.setBounds(592, 140, 70, 25);
		panel_bypot.add(textField_advpay4);
		
		textField_advpay5 = new JTextField();
		textField_advpay5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_advpay5.setEditable(false);
		textField_advpay5.setColumns(10);
		textField_advpay5.setBounds(592, 171, 70, 25);
		panel_bypot.add(textField_advpay5);
		
		textField_advpay6 = new JTextField();
		textField_advpay6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_advpay6.setEditable(false);
		textField_advpay6.setColumns(10);
		textField_advpay6.setBounds(594, 202, 68, 25);
		panel_bypot.add(textField_advpay6);
		
		textField_advpay7 = new JTextField();
		textField_advpay7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_advpay7.setEditable(false);
		textField_advpay7.setColumns(10);
		textField_advpay7.setBounds(594, 234, 68, 25);
		panel_bypot.add(textField_advpay7);
		
		textField_advpay8 = new JTextField();
		textField_advpay8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_advpay8.setEditable(false);
		textField_advpay8.setColumns(10);
		textField_advpay8.setBounds(592, 266, 70, 25);
		panel_bypot.add(textField_advpay8);
		
		textField_getback1 = new JTextField();
		textField_getback1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_getback1.setEditable(false);
		textField_getback1.setColumns(10);
		textField_getback1.setBounds(671, 46, 70, 25);
		panel_bypot.add(textField_getback1);
		
		textField_getback2 = new JTextField();
		textField_getback2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_getback2.setEditable(false);
		textField_getback2.setColumns(10);
		textField_getback2.setBounds(671, 78, 70, 25);
		panel_bypot.add(textField_getback2);
		
		textField_getback3 = new JTextField();
		textField_getback3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_getback3.setEditable(false);
		textField_getback3.setColumns(10);
		textField_getback3.setBounds(671, 109, 70, 25);
		panel_bypot.add(textField_getback3);
		
		textField_getback4 = new JTextField();
		textField_getback4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_getback4.setEditable(false);
		textField_getback4.setColumns(10);
		textField_getback4.setBounds(671, 140, 70, 25);
		panel_bypot.add(textField_getback4);
		
		textField_getback5 = new JTextField();
		textField_getback5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_getback5.setEditable(false);
		textField_getback5.setColumns(10);
		textField_getback5.setBounds(671, 171, 70, 25);
		panel_bypot.add(textField_getback5);
		
		textField_getback6 = new JTextField();
		textField_getback6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_getback6.setEditable(false);
		textField_getback6.setColumns(10);
		textField_getback6.setBounds(673, 202, 68, 25);
		panel_bypot.add(textField_getback6);
		
		textField_getback7 = new JTextField();
		textField_getback7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_getback7.setEditable(false);
		textField_getback7.setColumns(10);
		textField_getback7.setBounds(673, 234, 68, 25);
		panel_bypot.add(textField_getback7);
		
		textField_getback8 = new JTextField();
		textField_getback8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_getback8.setEditable(false);
		textField_getback8.setColumns(10);
		textField_getback8.setBounds(671, 266, 70, 25);
		panel_bypot.add(textField_getback8);
		
		JLabel lbl_advpay = new JLabel("Adv Pay");
		lbl_advpay.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_advpay.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_advpay.setBounds(594, 23, 68, 14);
		panel_bypot.add(lbl_advpay);
		
		JLabel lblNewLabel_1_1 = new JLabel("Get Back");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(673, 23, 68, 14);
		panel_bypot.add(lblNewLabel_1_1);

		
		JButton btn_Cal = new JButton("CALCULATE");
		btn_Cal.setBackground(Color.YELLOW);
		btn_Cal.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_Cal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (rdbtnByPOT.isSelected() != true && rdbtnByCUP.isSelected() != true)
				{
					JOptionPane.showMessageDialog(CalculatorFrame.this, "Please Select ByPOT or ByCUP");
					return;
				}
				
				if (dateChooser.getDate() == null)
				{
					JOptionPane.showMessageDialog(CalculatorFrame.this, "Please Select DATE!");
					dateChooser.requestFocusInWindow();
					return;
				}
				
				totalPerPot = 0;
				countDrinker = 0;
				
//add POT# to DB
				
				int numofpot = (int) spinner_potno.getValue();
				
//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				
				Date myDate = (dateChooser.getDate());
				sqlDate = new java.sql.Date(myDate.getTime());
				
//Check already existed pot in DB
				if(CalculatorManager.checkExistPot(numofpot, sqlDate) == true)
				{
					JOptionPane.showMessageDialog(CalculatorFrame.this, "POT#"+numofpot+" already existed, Please change POT#");
					spinner_potno.requestFocusInWindow();
					return;
				}
				
				totalcup = ((double) comboBox_cup1.getSelectedItem());
				
				if (comboBox_cup2.isEnabled() == true)
				{
					totalcup += (double) comboBox_cup2.getSelectedItem();
				}
				if (comboBox_cup3.isEnabled() == true)
				{
					totalcup += (double) comboBox_cup3.getSelectedItem();
				}
				if (comboBox_cup4.isEnabled() == true)
				{
					totalcup += (double) comboBox_cup4.getSelectedItem();
				}
				if (comboBox_cup5.isEnabled() == true)
				{
					totalcup += (double) comboBox_cup5.getSelectedItem();
				}
				if (comboBox_cup6.isEnabled() == true)
				{
					totalcup += (double) comboBox_cup6.getSelectedItem();
				}
				if (comboBox_cup7.isEnabled() == true)
				{
					totalcup += (double) comboBox_cup7.getSelectedItem();
				}
				if (comboBox_cup8.isEnabled() == true)
				{
					totalcup += (double) comboBox_cup8.getSelectedItem();
				}
				
//CHECK BUGS
				
				if (!comboBox_item1.getSelectedItem().toString().equals("") || !textField_price1.getText().toString().equals("") || !comboBox_sup1.getSelectedItem().toString().equals(""))
				{
					if (!textField_price1.getText().trim().matches("[-+]?\\d*\\.?\\d+"))
					{
						JOptionPane.showMessageDialog(CalculatorFrame.this, "Input Number only please!!");
						textField_price1.requestFocus();
						textField_price1.selectAll();
						return;
					}
					if (comboBox_sup1.getSelectedItem().toString().equals(""))
					{
						JOptionPane.showMessageDialog(CalculatorFrame.this, "Please select Supplier");
						comboBox_sup1.requestFocus();
						return;
					}
					if(comboBox_item1.getSelectedItem().toString().equals(""))
					{
						JOptionPane.showMessageDialog(CalculatorFrame.this, "Please select Item");
						comboBox_item1.requestFocus();
						return;
					}
				}
				
				if (!comboBox_item2.getSelectedItem().toString().equals("") || !textField_price2.getText().toString().equals("")|| !comboBox_sup2.getSelectedItem().toString().equals(""))
				{
					if (!textField_price2.getText().trim().matches("[-+]?\\d*\\.?\\d+"))
					{
						JOptionPane.showMessageDialog(CalculatorFrame.this, "Input Number only please!!");
						textField_price2.requestFocus();
						textField_price2.selectAll();
						return;
					}
					if (comboBox_sup2.getSelectedItem().toString().equals(""))
					{
						JOptionPane.showMessageDialog(CalculatorFrame.this, "Please select Supplier");
						comboBox_sup2.requestFocus();
						return;
					}
					if(comboBox_item2.getSelectedItem().toString().equals(""))
					{
						JOptionPane.showMessageDialog(CalculatorFrame.this, "Please select Item");
						comboBox_item2.requestFocus();
						return;
					}
				}
				
				if (!comboBox_item3.getSelectedItem().toString().equals("") || !textField_price3.getText().toString().equals("")|| !comboBox_sup3.getSelectedItem().toString().equals(""))
				{
					if (!textField_price3.getText().trim().matches("[-+]?\\d*\\.?\\d+"))
					{
						JOptionPane.showMessageDialog(CalculatorFrame.this, "Input Number only please!!");
						textField_price3.requestFocus();
						textField_price3.selectAll();
						return;
					}
					if (comboBox_sup3.getSelectedItem().toString().equals(""))
					{
						JOptionPane.showMessageDialog(CalculatorFrame.this, "Please select Supplier");
						comboBox_sup3.requestFocus();
						return;
					}
					if(comboBox_item3.getSelectedItem().toString().equals(""))
					{
						JOptionPane.showMessageDialog(CalculatorFrame.this, "Please select Item");
						comboBox_item3.requestFocus();
						return;
					}
				}
				
				if (!comboBox_item4.getSelectedItem().toString().equals("") || !textField_price4.getText().toString().equals("")|| !comboBox_sup4.getSelectedItem().toString().equals(""))
				{
					if (!textField_price4.getText().trim().matches("[-+]?\\d*\\.?\\d+"))
					{
						JOptionPane.showMessageDialog(CalculatorFrame.this, "Input Number only please!!");
						textField_price4.requestFocus();
						textField_price4.selectAll();
						return;
					}
					if (comboBox_sup4.getSelectedItem().toString().equals(""))
					{
						JOptionPane.showMessageDialog(CalculatorFrame.this, "Please select Supplier");
						comboBox_sup4.requestFocus();
						return;
					}
					if(comboBox_item4.getSelectedItem().toString().equals(""))
					{
						JOptionPane.showMessageDialog(CalculatorFrame.this, "Please select Item");
						comboBox_item4.requestFocus();
						return;
					}
				}
				
				if (!comboBox_item5.getSelectedItem().toString().equals("") || !textField_price5.getText().toString().equals("")|| !comboBox_sup5.getSelectedItem().toString().equals(""))
				{
					if (!textField_price5.getText().trim().matches("[-+]?\\d*\\.?\\d+"))
					{
						JOptionPane.showMessageDialog(CalculatorFrame.this, "Input Number only please!!");
						textField_price5.requestFocus();
						textField_price5.selectAll();
						return;
					}
					if (comboBox_sup5.getSelectedItem().toString().equals(""))
					{
						JOptionPane.showMessageDialog(CalculatorFrame.this, "Please select Supplier");
						comboBox_sup5.requestFocus();
						return;
					}
					if(comboBox_item5.getSelectedItem().toString().equals(""))
					{
						JOptionPane.showMessageDialog(CalculatorFrame.this, "Please select Item");
						comboBox_item5.requestFocus();
						return;
					}
				}
				
				if (!comboBox_item6.getSelectedItem().toString().equals("") || !textField_price6.getText().toString().equals("")|| !comboBox_sup6.getSelectedItem().toString().equals(""))
				{
					if (!textField_price6.getText().trim().matches("[-+]?\\d*\\.?\\d+"))
					{
						JOptionPane.showMessageDialog(CalculatorFrame.this, "Input Number only please!!");
						textField_price6.requestFocus();
						textField_price6.selectAll();
						return;
					}
					if (comboBox_sup6.getSelectedItem().toString().equals(""))
					{
						JOptionPane.showMessageDialog(CalculatorFrame.this, "Please select Supplier");
						comboBox_sup6.requestFocus();
						return;
					}
					if(comboBox_item6.getSelectedItem().toString().equals(""))
					{
						JOptionPane.showMessageDialog(CalculatorFrame.this, "Please select Item");
						comboBox_item6.requestFocus();
						return;
					}
				}
				
				if (comboBox_drinker1.getSelectedItem().toString().equals(""))
				{
					JOptionPane.showMessageDialog(CalculatorFrame.this, "Please select at least 2 Drinkers!");
					comboBox_drinker1.requestFocus();
					return;
				}
				
				if(comboBox_drinker2.isEnabled() == true && comboBox_drinker2.getSelectedItem().toString().equals(""))
				{
					JOptionPane.showMessageDialog(CalculatorFrame.this, "Please select at least 2 Drinkers!");
					comboBox_drinker2.requestFocus();
					return;
				}
				
				ArrayList<String> decoySupplier = new ArrayList<>();
				ArrayList<String> decoyDrinker = new ArrayList<>();
				
				if (!comboBox_item1.getSelectedItem().toString().equals(""))
				{
					decoySupplier.add(comboBox_sup1.getSelectedItem().toString());
				}
				if (!comboBox_item2.getSelectedItem().toString().equals(""))
				{
					decoySupplier.add(comboBox_sup2.getSelectedItem().toString());
				}
				if (!comboBox_item3.getSelectedItem().toString().equals(""))
				{
					decoySupplier.add(comboBox_sup3.getSelectedItem().toString());
				}
				if (!comboBox_item4.getSelectedItem().toString().equals(""))
				{
					decoySupplier.add(comboBox_sup4.getSelectedItem().toString());
				}
				if (!comboBox_item5.getSelectedItem().toString().equals(""))
				{
					decoySupplier.add(comboBox_sup5.getSelectedItem().toString());
				}
				if (!comboBox_item6.getSelectedItem().toString().equals(""))
				{
					decoySupplier.add(comboBox_sup6.getSelectedItem().toString());
				}
				
				if(comboBox_drinker2.isEnabled() == true)
				{
					decoyDrinker.add(comboBox_drinker1.getSelectedItem().toString());
					decoyDrinker.add(comboBox_drinker2.getSelectedItem().toString());
				}
				if(comboBox_drinker3.isEnabled() == true)
				{
					decoyDrinker.add(comboBox_drinker3.getSelectedItem().toString());
				}
				if(comboBox_drinker4.isEnabled() == true)
				{
					decoyDrinker.add(comboBox_drinker4.getSelectedItem().toString());
				}
				if(comboBox_drinker5.isEnabled() == true)
				{
					decoyDrinker.add(comboBox_drinker5.getSelectedItem().toString());
				}
				if(comboBox_drinker6.isEnabled() == true)
				{
					decoyDrinker.add(comboBox_drinker6.getSelectedItem().toString());
				}
				if(comboBox_drinker7.isEnabled() == true)
				{
					decoyDrinker.add(comboBox_drinker7.getSelectedItem().toString());
				}
				if(comboBox_drinker8.isEnabled() == true)
				{
					decoyDrinker.add(comboBox_drinker8.getSelectedItem().toString());
				}
				
				for (int i=0;i<decoySupplier.size();i++)
				{
					if (decoyDrinker.contains(decoySupplier.get(i)) == false)
					{
						JOptionPane.showMessageDialog(CalculatorFrame.this, "All Supplier must exist in Drinker!! (For more info: Call BOOM)");
						return;
					}
				}
				
				if(JOptionPane.showConfirmDialog(CalculatorFrame.this, "Are you sure inputs are correct?", "Confirm Calculate", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.CANCEL_OPTION)
				{
					return;
				}
				
				CalculatorFrame.this.setVisible(false);
				
//Add to NEW billing_id
				
				CalculatorDB x = new CalculatorDB(0,
						sqlDate,
						numofpot,
						cuptype,
						totalcup,
						0,
						0,
						GlobalData.CurrentUser_nickname);
				CalculatorManager.addBill(x);
				
//add ingredient to DB
				
				if (comboBox_item1.isEnabled() == true && !comboBox_item1.getSelectedItem().toString().equals(""))
				{
					ItemDB x1 = new ItemDB(
							(String)comboBox_item1.getSelectedItem(),
							0,
							Double.parseDouble(textField_price1.getText()),
							(String) comboBox_sup1.getSelectedItem());
					CalculatorManager.addItem(x1);
					totalPerPot += Double.parseDouble(textField_price1.getText());
				}
				if (comboBox_item2.isEnabled() == true && !comboBox_item2.getSelectedItem().toString().equals(""))
				{
					ItemDB x1 = new ItemDB(
							(String)comboBox_item2.getSelectedItem(),
							0,
							Double.parseDouble(textField_price2.getText()),
							(String) comboBox_sup2.getSelectedItem());
					CalculatorManager.addItem(x1);
					totalPerPot += Double.parseDouble(textField_price2.getText());
				}
				if (comboBox_item3.isEnabled() == true && !comboBox_item3.getSelectedItem().toString().equals(""))
				{
					ItemDB x1 = new ItemDB(
							(String)comboBox_item3.getSelectedItem(),
							0,
							Double.parseDouble(textField_price3.getText()),
							(String) comboBox_sup3.getSelectedItem());
					CalculatorManager.addItem(x1);
					totalPerPot += Double.parseDouble(textField_price3.getText());
				}
				if (comboBox_item4.isEnabled() == true && !comboBox_item4.getSelectedItem().toString().equals(""))
				{
					ItemDB x1 = new ItemDB(
							(String)comboBox_item4.getSelectedItem(),
							0,
							Double.parseDouble(textField_price4.getText()),
							(String) comboBox_sup4.getSelectedItem());
					CalculatorManager.addItem(x1);
					totalPerPot += Double.parseDouble(textField_price4.getText());
				}
				if (comboBox_item5.isEnabled() == true && !comboBox_item5.getSelectedItem().toString().equals(""))
				{
					ItemDB x1 = new ItemDB(
							(String)comboBox_item5.getSelectedItem(),
							0,
							Double.parseDouble(textField_price5.getText()),
							(String) comboBox_sup5.getSelectedItem());
					CalculatorManager.addItem(x1);
					totalPerPot += Double.parseDouble(textField_price5.getText());
				}
				if (comboBox_item6.isEnabled() == true && !comboBox_item6.getSelectedItem().toString().equals(""))
				{
					ItemDB x1 = new ItemDB(
							(String)comboBox_item6.getSelectedItem(),
							0,
							Double.parseDouble(textField_price6.getText()),
							(String) comboBox_sup6.getSelectedItem());
					CalculatorManager.addItem(x1);
					totalPerPot += Double.parseDouble(textField_price6.getText());
				}
				
//Add drinker to DB & Calculate Adv_pay
				
				if (comboBox_drinker1.isEnabled() == true && !comboBox_drinker1.getSelectedItem().toString().equals(""))
				{
					DrinkerDB x1 = new DrinkerDB(
							0,
							(String) comboBox_drinker1.getSelectedItem(),
							Double.parseDouble(comboBox_cup1.getSelectedItem().toString()));
					CalculatorManager.setDrinker(x1);
					textField_advpay1.setText(""+CalculatorManager.setAdvpay(x1));
					countDrinker++;
				}
				
				if (comboBox_drinker2.isEnabled() == true && !comboBox_drinker2.getSelectedItem().toString().equals(""))
				{
					DrinkerDB x1 = new DrinkerDB(
							0,
							(String) comboBox_drinker2.getSelectedItem(),
							Double.parseDouble(comboBox_cup2.getSelectedItem().toString()));
					CalculatorManager.setDrinker(x1);
					textField_advpay2.setText(""+CalculatorManager.setAdvpay(x1));
					countDrinker++;
				}
				
				if (comboBox_drinker3.isEnabled() == true && !comboBox_drinker3.getSelectedItem().toString().equals(""))
				{
					DrinkerDB x1 = new DrinkerDB(
							0,
							(String) comboBox_drinker3.getSelectedItem(),
							Double.parseDouble(comboBox_cup3.getSelectedItem().toString()));
					CalculatorManager.setDrinker(x1);
					textField_advpay3.setText(""+CalculatorManager.setAdvpay(x1));
					countDrinker++;
				}
				
				if (comboBox_drinker4.isEnabled() == true && !comboBox_drinker4.getSelectedItem().toString().equals(""))
				{
					DrinkerDB x1 = new DrinkerDB(
							0,
							(String) comboBox_drinker4.getSelectedItem(),
							Double.parseDouble(comboBox_cup4.getSelectedItem().toString()));
					CalculatorManager.setDrinker(x1);
					textField_advpay4.setText(""+CalculatorManager.setAdvpay(x1));
					countDrinker++;
				}
				
				if (comboBox_drinker5.isEnabled() == true && !comboBox_drinker5.getSelectedItem().toString().equals(""))
				{
					DrinkerDB x1 = new DrinkerDB(
							0,
							(String) comboBox_drinker5.getSelectedItem(),
							Double.parseDouble(comboBox_cup5.getSelectedItem().toString()));
					CalculatorManager.setDrinker(x1);
					textField_advpay5.setText(""+CalculatorManager.setAdvpay(x1));
					countDrinker++;
				}
				
				if (comboBox_drinker6.isEnabled() == true && !comboBox_drinker6.getSelectedItem().toString().equals(""))
				{
					DrinkerDB x1 = new DrinkerDB(
							0,
							(String) comboBox_drinker6.getSelectedItem(),
							Double.parseDouble(comboBox_cup6.getSelectedItem().toString()));
					CalculatorManager.setDrinker(x1);
					textField_advpay6.setText(""+CalculatorManager.setAdvpay(x1));
					countDrinker++;
				}
				
				if (comboBox_drinker7.isEnabled() == true && !comboBox_drinker7.getSelectedItem().toString().equals(""))
				{
					DrinkerDB x1 = new DrinkerDB(
							0,
							(String) comboBox_drinker7.getSelectedItem(),
							Double.parseDouble(comboBox_cup7.getSelectedItem().toString()));
					CalculatorManager.setDrinker(x1);
					textField_advpay7.setText(""+CalculatorManager.setAdvpay(x1));
					countDrinker++;
				}
				
				if (comboBox_drinker8.isEnabled() == true && !comboBox_drinker8.getSelectedItem().toString().equals(""))
				{
					DrinkerDB x1 = new DrinkerDB(
							0,
							(String) comboBox_drinker8.getSelectedItem(),
							Double.parseDouble(comboBox_cup8.getSelectedItem().toString()));
					CalculatorManager.setDrinker(x1);
					textField_advpay8.setText(""+CalculatorManager.setAdvpay(x1));
					countDrinker++;
				}
				
				GlobalData.CurrentResult_date = sqlDate.toString();
				GlobalData.CurrentResult_potno = numofpot;
				GlobalData.CurrentResult_totalcup = totalcup;
				GlobalData.CurrentResult_user_nickname = GlobalData.CurrentUser_nickname;
				
//Get Price Per Person by POT & Price Per Person by CUP
//And calculate Get Back

				if (rdbtnByPOT.isSelected() == true)
				{
					GlobalData.CurrentResult_pottype = "ByPOT";
					double pricePPP = Math.ceil(totalPerPot/countDrinker);
					CalculatorManager.setPPP_PPC(pricePPP, 0);
					
					calculateGetBackPPP(textField_advpay1.getText(), pricePPP, textField_getback1, comboBox_drinker1);
					calculateGetBackPPP(textField_advpay2.getText(), pricePPP, textField_getback2, comboBox_drinker2);
					calculateGetBackPPP(textField_advpay3.getText(), pricePPP, textField_getback3, comboBox_drinker3);
					calculateGetBackPPP(textField_advpay4.getText(), pricePPP, textField_getback4, comboBox_drinker4);
					calculateGetBackPPP(textField_advpay5.getText(), pricePPP, textField_getback5, comboBox_drinker5);
					calculateGetBackPPP(textField_advpay6.getText(), pricePPP, textField_getback6, comboBox_drinker6);
					calculateGetBackPPP(textField_advpay7.getText(), pricePPP, textField_getback7, comboBox_drinker7);
					calculateGetBackPPP(textField_advpay8.getText(), pricePPP, textField_getback8, comboBox_drinker8);
				}
				else if (rdbtnByCUP.isSelected() == true)
				{
					GlobalData.CurrentResult_pottype = "ByCUP";
					double pricePPC = Math.ceil(totalPerPot/totalcup);
					CalculatorManager.setPPP_PPC(0, pricePPC);
					
					calculateGetBackPPC(textField_advpay1.getText(), pricePPC, comboBox_cup1, textField_getback1, comboBox_drinker1);
					calculateGetBackPPC(textField_advpay2.getText(), pricePPC, comboBox_cup2, textField_getback2, comboBox_drinker2);
					calculateGetBackPPC(textField_advpay3.getText(), pricePPC, comboBox_cup3, textField_getback3, comboBox_drinker3);
					calculateGetBackPPC(textField_advpay4.getText(), pricePPC, comboBox_cup4, textField_getback4, comboBox_drinker4);
					calculateGetBackPPC(textField_advpay5.getText(), pricePPC, comboBox_cup5, textField_getback5, comboBox_drinker5);
					calculateGetBackPPC(textField_advpay6.getText(), pricePPC, comboBox_cup6, textField_getback6, comboBox_drinker6);
					calculateGetBackPPC(textField_advpay7.getText(), pricePPC, comboBox_cup7, textField_getback7, comboBox_drinker7);
					calculateGetBackPPC(textField_advpay8.getText(), pricePPC, comboBox_cup8, textField_getback8, comboBox_drinker8);
				}
				
				JOptionPane.showMessageDialog(CalculatorFrame.this, "Calculate Complete!!");
				
				DetailedResultFrame f = new DetailedResultFrame();
				for (SetPaymentDB xyz : f.paymentList)
				{
					DetailedResultManager.setPayment(xyz);
				}
				f.setVisible(true);
				
			}
		});
		btn_Cal.setBounds(640, 36, 138, 55);
		contentPane.add(btn_Cal);
		
		
		JLabel lblNewLabel_2 = new JLabel("Date");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(11, 74, 45, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel lbl_potno = new JLabel("POT#");
		lbl_potno.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_potno.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_potno.setBounds(208, 74, 46, 30);
		contentPane.add(lbl_potno);
		
		JButton btn_resetDrinker = new JButton("RESET Drinker");
		btn_resetDrinker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				loadnicknameDrinker();
				comboBox_drinker1.setModel(new DefaultComboBoxModel<>(list_nickname_drinker.toArray()));
				
				comboBox_drinker2.setEnabled(false);
				comboBox_cup2.setEnabled(false);
				
				comboBox_drinker3.setEnabled(false);
				comboBox_cup3.setEnabled(false);
				
				comboBox_drinker4.setEnabled(false);
				comboBox_cup4.setEnabled(false);
				
				comboBox_drinker5.setEnabled(false);
				comboBox_cup5.setEnabled(false);
				
				comboBox_drinker6.setEnabled(false);
				comboBox_cup6.setEnabled(false);
				
				comboBox_drinker7.setEnabled(false);
				comboBox_cup7.setEnabled(false);
				
				comboBox_drinker8.setEnabled(false);
				comboBox_cup8.setEnabled(false);
			
				comboBox_drinker1.setEnabled(true);
				comboBox_cup1.setEnabled(true);
				comboBox_drinker1.setSelectedIndex(0);
				
			}
		});
		btn_resetDrinker.setFont(new Font("Tahoma", Font.BOLD, 10));
		btn_resetDrinker.setBounds(368, 88, 115, 26);
		contentPane.add(btn_resetDrinker);
		
	}
	
	/**
	load nickname in UserDB
	*/
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
	
	/**
	load nickname in UserDB specifically for Drinker Combobox ONLY!
	*/
	public ArrayList<String> loadnicknameDrinker()
	{
		list_user = UserManager.getAllUser();
		list_nickname_drinker = new ArrayList<String>();
		list_nickname_drinker.add("");
		for (UserDB c: list_user)
		{
			list_nickname_drinker.add(c.nickname);
		}
		return list_nickname_drinker;
	}
	
	/**
	Calculate & Set DB for Get Back (PPP)
	*/
	public void calculateGetBackPPP(String advpay, double price, JTextField getback, JComboBox<String> drinker)
	{
		if (!advpay.equals(""))
		{
			if((Double.parseDouble(advpay)-price) < 0)
			{
				getback.setText(""+0.0);
			}
			else
			{
				getback.setText(""+(Double.parseDouble(advpay)-price));
			}
			CalculatorManager.setGetBack(Double.parseDouble(getback.getText().toString()), (String) drinker.getSelectedItem());
		}
	}
	
	/**
	Calculate & Set DB for Get Back (PPC)
	*/
	public void calculateGetBackPPC(String advpay, double price,JComboBox<String> cupdrank, JTextField getback, JComboBox<String> drinker)
	{
		if (!advpay.equals(""))
		{
			if((Double.parseDouble(advpay)-((price)*(Double.parseDouble(cupdrank.getSelectedItem().toString())))) < 0)
			{
				getback.setText(""+0.0);
			}
			else
			{
				getback.setText(""+(Double.parseDouble(advpay)-((price)*(Double.parseDouble(cupdrank.getSelectedItem().toString())))));
			}
			CalculatorManager.setGetBack(Double.parseDouble(getback.getText().toString()), (String) drinker.getSelectedItem());
		}
	}
}
