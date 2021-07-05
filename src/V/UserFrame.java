package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import C.GlobalData;
import M.UserDB;
import M.UserManager;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserFrame extends JFrame
{

	private JPanel contentPane;
	private JTable table;
	private JTextField textField_users_id;
	private JTextField textField_username;
	private JTextField textField_password;
	private JTextField textField_nickname;
	private JTextField textField_email;
	private JTextField textField_mobile;
	ArrayList<UserDB> list;
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
					UserFrame frame = new UserFrame();
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
	public UserFrame()
	{
		setTitle("Users Management");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 984, 547);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 677, 469);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRowCount() < 1)
				{
					return;
				}
				int tableindex = 	table.getSelectedRow();
				
				int id = 			Integer.parseInt(table.getValueAt(tableindex, 0).toString());
				String username = 	table.getValueAt(tableindex, 1).toString();
				String nickname = 	table.getValueAt(tableindex, 3).toString();
				
				textField_users_id.setText("" + id);
				textField_username.setText("" + username);
				textField_nickname.setText("" + nickname);

				//password
				if (table.getValueAt(tableindex, 2) != null)
				{
					String password = table.getValueAt(tableindex, 2).toString();
					textField_password.setText(""+ password);
				}
				else
				{
					String password = "";
					textField_password.setText(""+ password);
				}
				//email
				if (table.getValueAt(tableindex, 4) != null)
				{
					String email = table.getValueAt(tableindex, 4).toString();
					textField_email.setText(""+ email);
				}
				else
				{
					String email = "";
					textField_email.setText(""+ email);
				}
				//mobile no.
				if (table.getValueAt(tableindex, 5) != null)
				{
					String mobile = table.getValueAt(tableindex, 5).toString();
					textField_mobile.setText(""+ mobile);
				}
				else
				{
					String mobile = "";
					textField_mobile.setText(""+mobile);
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblUsersid = new JLabel("Users_id");
		lblUsersid.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsersid.setBounds(697, 34, 75, 30);
		contentPane.add(lblUsersid);
		
		textField_users_id = new JTextField();
		textField_users_id.setFont(new Font("Courier MonoThai", Font.PLAIN, 11));
		textField_users_id.setEditable(false);
		textField_users_id.setColumns(10);
		textField_users_id.setBackground(Color.YELLOW);
		textField_users_id.setBounds(782, 34, 176, 30);
		contentPane.add(textField_users_id);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setBounds(698, 75, 75, 30);
		contentPane.add(lblUsername);
		
		textField_username = new JTextField();
		textField_username.setFont(new Font("Courier MonoThai", Font.PLAIN, 11));
		textField_username.setColumns(10);
		textField_username.setBounds(782, 75, 176, 30);
		contentPane.add(textField_username);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(698, 116, 75, 30);
		contentPane.add(lblPassword);
		
		textField_password = new JTextField();
		textField_password.setFont(new Font("Courier MonoThai", Font.PLAIN, 11));
		textField_password.setColumns(10);
		textField_password.setBounds(782, 116, 176, 30);
		contentPane.add(textField_password);
		
		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNickname.setBounds(698, 157, 75, 30);
		contentPane.add(lblNickname);
		
		textField_nickname = new JTextField();
		textField_nickname.setFont(new Font("Courier MonoThai", Font.PLAIN, 11));
		textField_nickname.setColumns(10);
		textField_nickname.setBounds(782, 157, 176, 30);
		contentPane.add(textField_nickname);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(698, 198, 75, 30);
		contentPane.add(lblEmail);
		
		textField_email = new JTextField();
		textField_email.setFont(new Font("Courier MonoThai", Font.PLAIN, 11));
		textField_email.setColumns(10);
		textField_email.setBounds(782, 198, 176, 30);
		contentPane.add(textField_email);
		
		JLabel lblMobile = new JLabel("Mobile No.");
		lblMobile.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMobile.setBounds(698, 239, 75, 30);
		contentPane.add(lblMobile);
		
		textField_mobile = new JTextField();
		textField_mobile.setFont(new Font("Courier MonoThai", Font.PLAIN, 11));
		textField_mobile.setColumns(10);
		textField_mobile.setBounds(782, 239, 176, 30);
		contentPane.add(textField_mobile);
		
		JButton btn_SaveNew = new JButton("SAVE NEW USER");
		btn_SaveNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!textField_users_id.getText().equals(""))
				{
					JOptionPane.showMessageDialog(UserFrame.this, "Please press RESET ALL button before SAVING NEW USER!!");
					return;
				}
				
				UserDB x = new UserDB(0,
						textField_username.getText().trim(),
						textField_password.getText().trim(),
						textField_nickname.getText().trim(),
						textField_email.getText().trim(),
						textField_mobile.getText().trim());
				UserManager.saveNewUser(x);
				load();
				
				JOptionPane.showMessageDialog(UserFrame.this, "Register Complete!");
			}
		});
		btn_SaveNew.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_SaveNew.setBounds(747, 302, 169, 23);
		contentPane.add(btn_SaveNew);
		
		JButton btn_Edit = new JButton("EDIT");
		btn_Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserDB x = new UserDB(Integer.parseInt(textField_users_id.getText()),
						textField_username.getText().trim(),
						textField_password.getText().trim(),
						textField_nickname.getText().trim(),
						textField_email.getText().trim(),
						textField_mobile.getText().trim());
				UserManager.editUser(x);
				load();
				
				textField_users_id.setText("");
				textField_username.setText("");
				textField_password.setText("");
				textField_nickname.setText("");
				textField_email.setText("");
				textField_mobile.setText("");
				
				JOptionPane.showMessageDialog(UserFrame.this, "Edit Complete!");
			}
		});
		btn_Edit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_Edit.setBounds(747, 336, 169, 23);
		contentPane.add(btn_Edit);
		
		JButton btn_Delete = new JButton("DELETE");
		btn_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!GlobalData.CurrentUser_nickname.equals("Boom"))
				{
					JOptionPane.showMessageDialog(UserFrame.this, "Permission Denied");
					return;
				}
				
				if (JOptionPane.showConfirmDialog(UserFrame.this, "Are you sure you want to delete?", "Delete?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
				{
					UserDB x = new UserDB(Integer.parseInt(textField_users_id.getText()),
							textField_username.getText().trim(),
							textField_password.getText().trim(),
							textField_nickname.getText().trim(),
							textField_email.getText().trim(),
							textField_mobile.getText().trim());
					UserManager.deleteUser(x);;
					load();
					
					textField_users_id.setText("");
					textField_username.setText("");
					textField_password.setText("");
					textField_nickname.setText("");
					textField_email.setText("");
					textField_mobile.setText("");
					
					JOptionPane.showMessageDialog(UserFrame.this, "Delete Complete!");
				}
			}
		});
		btn_Delete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_Delete.setBounds(747, 370, 169, 23);
		contentPane.add(btn_Delete);
		
		JButton btnNewButton = new JButton("Clear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_username.setText("");
				textField_password.setText("");
				textField_nickname.setText("");
				textField_email.setText("");
				textField_mobile.setText("");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(787, 423, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Clear All");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_users_id.setText("");
				textField_username.setText("");
				textField_password.setText("");
				textField_nickname.setText("");
				textField_email.setText("");
				textField_mobile.setText("");
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(774, 457, 117, 23);
		contentPane.add(btnNewButton_1);
		
		load();
	}
	
	public void load()
	{
		//Create new arraylist for result
		list = UserManager.getAllUser();
		// ตัวแปรชื่อ model (ต้อง cast)
		DefaultTableModel model = new DefaultTableModel();
		//สร้าง column
		model.addColumn("users_id");
		model.addColumn("username");
		model.addColumn("password");
		model.addColumn("nickname");
		model.addColumn("email");
		model.addColumn("mobile");
		
		//วน loop ตามทุกตัวที่อยู่ใน list
		for (UserDB c: list)
		{
			// เอาทุกอย่าง add ไปในโมเดล
			model.addRow(new Object[] {c.users_id, c.username, "**********", c.nickname, c.email, c.mobile});
		}
		
		table.setModel(model);
	}
}
