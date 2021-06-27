package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import C.GlobalData;
import M.CalculatorDB;
import M.CalculatorManager;
import M.ResultPayDB;
import M.ResultPayManager;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;


//THIS FRAME COMES FROM DOUBLE-CLICKING ONE OF THE ROWS IN MAINRESULTFRAME's TABLE!!!!!!!!!!!
public class ResultFrame extends JFrame
{

	private JPanel contentPane;
	private JTable table_pay;
	private JTable table_return;
	ArrayList<ResultPayDB> list;
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
					ResultFrame frame = new ResultFrame();
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
	public ResultFrame()
	{
		setTitle("Result");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 887, 509);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("USER :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(11, 17, 73, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(GlobalData.CurrentUser_nickname);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(83, 17, 148, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date :");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_2.setBounds(11, 45, 73, 45);
		contentPane.add(lblNewLabel_2);
		
		JLabel label_date = new JLabel(GlobalData.CurrentResult_date);
		label_date.setVerticalAlignment(SwingConstants.BOTTOM);
		label_date.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label_date.setBounds(83, 44, 123, 46);
		contentPane.add(label_date);
		
		JLabel lblNewLabel_potnumber = new JLabel("POT#");
		lblNewLabel_potnumber.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_potnumber.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_potnumber.setBounds(213, 45, 73, 46);
		contentPane.add(lblNewLabel_potnumber);
		
		JLabel lbl_potno = new JLabel(""+GlobalData.CurrentResult_potno);
		lbl_potno.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl_potno.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_potno.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lbl_potno.setBounds(278, 45, 24, 46);
		contentPane.add(lbl_potno);
		
		JLabel lblNewLabel_3 = new JLabel("Type :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(324, 66, 45, 24);
		contentPane.add(lblNewLabel_3);
		
		JLabel lbl_pottype = new JLabel(GlobalData.CurrentResult_pottype);
		lbl_pottype.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pottype.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_pottype.setBounds(358, 66, 46, 24);
		contentPane.add(lbl_pottype);
		
		JLabel lblNewLabel_4 = new JLabel("Total Cup :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(414, 66, 72, 24);
		contentPane.add(lblNewLabel_4);
		
		JLabel lbl_totalcup = new JLabel(""+GlobalData.CurrentResult_totalcup);
		lbl_totalcup.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_totalcup.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_totalcup.setBounds(483, 67, 41, 23);
		contentPane.add(lbl_totalcup);
		
		JLabel lblNewLabel_5 = new JLabel("Created By :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(534, 66, 90, 24);
		contentPane.add(lblNewLabel_5);
		
		JLabel lbl_createdbyusers = new JLabel(GlobalData.CurrentResult_user_nickname);
		lbl_createdbyusers.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_createdbyusers.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_createdbyusers.setBounds(616, 66, 84, 24);
		contentPane.add(lbl_createdbyusers);
		
		JLabel lblNewLabel_pay = new JLabel("Pay");
		lblNewLabel_pay.setForeground(Color.RED);
		lblNewLabel_pay.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel_pay.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_pay.setBounds(11, 112, 400, 40);
		contentPane.add(lblNewLabel_pay);
		
		JLabel lblNewLabel_getback = new JLabel("Get Back");
		lblNewLabel_getback.setForeground(new Color(50, 205, 50));
		lblNewLabel_getback.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel_getback.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_getback.setBounds(462, 112, 399, 40);
		contentPane.add(lblNewLabel_getback);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 163, 400, 296);
		contentPane.add(scrollPane);
		
		table_pay = new JTable();
		scrollPane.setViewportView(table_pay);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(461, 163, 400, 296);
		contentPane.add(scrollPane_1);
		
		table_return = new JTable();
		scrollPane_1.setViewportView(table_return);
		
		JButton btnNewButton = new JButton("Result in-detailed");
		btnNewButton.setBackground(Color.YELLOW);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DetailedResultFrame f = new DetailedResultFrame();
				f.setVisible(true);
			}
		});
		btnNewButton.setBounds(707, 17, 154, 33);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Save As Picture");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveImage(contentPane);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(707, 63, 154, 33);
		contentPane.add(btnNewButton_1);
		
		loadtable_pay();
		
		loadtable_return();
		
	}
	
	public void loadtable_pay()
	{
		//Create new array list for result
		list = ResultPayManager.getResultPay();
		// ตัวแปรชื่อ model (ต้อง cast)
		DefaultTableModel model = new DefaultTableModel();
		//สร้าง column
		model.addColumn("Name");
		model.addColumn("Pay");
		model.addColumn("To Who?");
		
		//วน loop ตามทุกตัวที่อยู่ใน list
		for (ResultPayDB c: list)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model.addRow(new Object[] {c.users_nickname, c.payment_price, c.payment_to_who_id});
		}
		
		table_pay.setModel(model);
		
		//Sort table
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_pay.getModel());
		table_pay.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		 
		int columnIndexToSort = 0;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
		 
		sorter.setSortKeys(sortKeys);
		sorter.sort();
	}
	
	public void loadtable_return()
	{
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Name");
		model.addColumn("Get Back");
		model.addColumn("fromWho?");
		
		for (int i=0;i<table_pay.getRowCount();i++)
		{
			model.addRow(new Object[] {table_pay.getValueAt(i, 2).toString(), Double.parseDouble(table_pay.getValueAt(i, 1).toString()), table_pay.getValueAt(i, 0).toString()});
		}
		
		table_return.setModel(model);
		
		//Sort table
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_return.getModel());
		table_return.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		 
		int columnIndexToSort = 0;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
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

		int returnVal = fc.showSaveDialog(ResultFrame.this);
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			File fileToSave = fc.getSelectedFile();
//		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
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

//implementation for FileFilter
class OpenFileFilter extends FileFilter
{

	String description = "";
	String fileExt = "";

	public OpenFileFilter(String extension)
	{
		fileExt = extension;
	}

	public OpenFileFilter(String extension, String typeDescription)
	{
		fileExt = extension;
		this.description = typeDescription;
	}

	@Override
	public boolean accept(File f)
	{
		if (f.isDirectory())
			return true;
		return (f.getName().toLowerCase().endsWith(fileExt));
	}

	@Override
	public String getDescription()
	{
		return description;
	}

}
