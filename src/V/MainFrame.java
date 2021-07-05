package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import C.GlobalData;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;

public class MainFrame extends JFrame
{

	private JPanel contentPane;

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
					MainFrame frame = new MainFrame();
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
	public MainFrame()
	{
		setTitle("ACPCalculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 411);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("USER :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(21, 15, 79, 32);
		contentPane.add(lblNewLabel);
		
		JButton btn_toDB = new JButton("Calculator");
		btn_toDB.setBackground(Color.YELLOW);
		btn_toDB.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_toDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainResultFrame f = new MainResultFrame();
				f.setVisible(true);
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel(GlobalData.CurrentUser_nickname);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(96, 15, 201, 32);
		contentPane.add(lblNewLabel_1);
		btn_toDB.setBounds(21, 81, 276, 56);
		contentPane.add(btn_toDB);
		
		JButton btn_stat = new JButton("Statistics/Report");
		btn_stat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportFrame f = new ReportFrame();
				f.setVisible(true);
			}
		});
		btn_stat.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_stat.setBounds(21, 193, 276, 56);
		contentPane.add(btn_stat);
		
		JButton btn_UserManager = new JButton("User Manager");
		btn_UserManager.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_UserManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserFrame f = new UserFrame();
				f.setVisible(true);
			}
		});
		btn_UserManager.setBounds(21, 299, 276, 56);
		contentPane.add(btn_UserManager);
		
		JButton btnNewButton = new JButton("EXIT");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(565, 14, 113, 36);
		contentPane.add(btnNewButton);
		
		ImagePanel imagePanel = new ImagePanel();
		imagePanel.setBounds(333, 81, 345, 274);
		contentPane.add(imagePanel);
		
		try
		{	
			BufferedImage bimg = ImageIO.read(getClass().getResource("/bicLighter.png"));
			imagePanel.setImage(bimg);
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
	}
}
