package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import C.GlobalData;
import M.UserManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.TextField;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginFrame extends JFrame
{

	private JPanel contentPane;
	private JTextField textField_username;
	private JPasswordField passwordField;

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
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
					{
						if ("Nimbus".equals(info.getName()))
						{
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame()
	{
		setTitle("ACPCalculator");
		
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 486, 313);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(28, 68, 128, 36);
		contentPane.add(lblNewLabel);
		
		textField_username = new JTextField();
		textField_username.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					versionCheck();
					login();
				}
			}
		});
		textField_username.setColumns(10);
		textField_username.setBounds(177, 68, 214, 36);
		contentPane.add(textField_username);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(28, 127, 128, 36);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					versionCheck();
					login();
				}
			}
		});
		passwordField.setBounds(177, 127, 214, 36);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				versionCheck();
				login();
			}
		});
		btnLogin.setBounds(71, 214, 144, 36);
		contentPane.add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(247, 214, 144, 36);
		contentPane.add(btnExit);
		
		JLabel lblv = new JLabel("Version");
		lblv.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblv.setHorizontalAlignment(SwingConstants.CENTER);
		lblv.setBounds(382, 11, 53, 14);
		contentPane.add(lblv);
		
		JLabel lbl_version = new JLabel(""+GlobalData.ProgramVersion);
		lbl_version.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_version.setBounds(435, 11, 25, 14);
		contentPane.add(lbl_version);
	}

	public void login()
	{
		if (UserManager.checkLogin(textField_username.getText(), new String (passwordField.getPassword())))
		{
			MainFrame f = new MainFrame();
			f.setVisible(true);
			
			LoginFrame.this.setVisible(false);
		}
		else
		{
			JOptionPane.showMessageDialog(LoginFrame.this, "Wrong Username or Password!!!");
		}
	}
	
	public void versionCheck()
	{
		if (UserManager.checkVersion(GlobalData.ProgramVersion))
		{
			
		}
		else
		{
			JOptionPane.showMessageDialog(LoginFrame.this, GlobalData.ProgramComment);
			return;
		}
	}
}
