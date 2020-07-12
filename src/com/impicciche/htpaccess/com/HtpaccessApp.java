package com.impicciche.htpaccess.com;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import at.favre.lib.crypto.bcrypt.BCrypt;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class HtpaccessApp {

	private JFrame frame;
	private JPanel panel_3;
	private JLabel lblPath;
	private JTextField textFieldPath;
	private JLabel lblNewLabel;
	private JTextField textFieldUsername;
	private JTextField textPassword;
	private final int NUM_GEN_CHAR = 20;
	private JPanel panel;
	private JTextField lblEncryptedPassword;
	private JLabel lblCreatedByGiuseppe;
	private JTextArea textAreaHtaccess;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HtpaccessApp window = new HtpaccessApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HtpaccessApp() {
		initialize();
	}

	
	private void initialize()  {
		frame = new JFrame("Htpasswd and Htaccess generator");
		frame.setTitle("Htpasswd and Htaccess generator");
		
		URL iconURL = getClass().getResource("/resources/key.png");
		// iconURL is null when not found
		ImageIcon icon = new ImageIcon(iconURL);
		frame.setIconImage(icon.getImage());
		
		frame.setResizable(false);
		frame.setBounds(100, 100, 703, 531);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.dispose();
		frame.repaint();
		frame.revalidate();
		
		panel_3 = new JPanel();
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(6, 6, 108, 16);
		panel_3.add(lblNewLabel);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(6, 34, 256, 36);
		panel_3.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setBounds(274, 34, 256, 36);
		textPassword.setColumns(10);
		panel_3.add(textPassword);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(274, 6, 108, 16);
		panel_3.add(lblPassword);
		
		JButton btnGeneratePassword = new JButton("Generate Password");
		btnGeneratePassword.setBounds(538, 34, 156, 36);
		btnGeneratePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String patternGen = "abcdefghilmnopqrstuvzyxwkABCDEFGHILMNOPQRSTUVZYXWK=+0123456789";
				String genPass = "";
				for(int i=0; i<NUM_GEN_CHAR;i++) {
					genPass = genPass + (patternGen.charAt((int)(Math.random()*62)));
				}
				
				textPassword.setText(genPass);
				
				
			}
		});
		panel_3.add(btnGeneratePassword);
		
		textFieldPath = new JTextField();
		textFieldPath.setBounds(6, 110, 688, 38);
		panel_3.add(textFieldPath);
		textFieldPath.setColumns(10);
		
		lblPath = new JLabel("path");
		lblPath.setBounds(6, 82, 28, 16);
		panel_3.add(lblPath);
		
		JButton btnCreate = new JButton("Create file");
		btnCreate.setBounds(6, 442, 108, 36);
		panel_3.add(btnCreate);
		
		panel = new JPanel();
		panel.setBounds(6, 243, 688, 162);
		panel_3.add(panel);
		panel.setLayout(null);
		
		textAreaHtaccess = new JTextArea(9,10);
		
		JScrollPane scrollPane = new JScrollPane(textAreaHtaccess);
		scrollPane.setBounds(6, 6, 676, 148);
		panel.add(scrollPane);
		
		
		JLabel lblhtaccessContent = new JLabel(".htaccess Content");
		lblhtaccessContent.setBounds(6, 215, 113, 16);
		panel_3.add(lblhtaccessContent);
		
		lblEncryptedPassword = new JTextField("Encrypted password");
		lblEncryptedPassword.setEditable(false);
		lblEncryptedPassword.setBounds(6, 165, 688, 29);
		panel_3.add(lblEncryptedPassword);
		
		lblCreatedByGiuseppe = new JLabel("Created by Giuseppe Impiccichè");
		lblCreatedByGiuseppe.setBounds(487, 487, 207, 16);
		panel_3.add(lblCreatedByGiuseppe);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textFieldUsername.getText();
				String password = textPassword.getText();
				
				if(username.length()==0) {
					JOptionPane.showMessageDialog(frame,"Username field must have some value","Username field",JOptionPane.WARNING_MESSAGE);
					textFieldUsername.requestFocus();
					return;
				}
				if(password.length()==0) {
					JOptionPane.showMessageDialog(frame,"Password field must have some value","Password field",JOptionPane.WARNING_MESSAGE);
					textPassword.requestFocus();
					return;
				}
				
				String htaccess = textAreaHtaccess.getText();
				
				String path = textFieldPath.getText();
				if(path.length()>0) {
					
					if(!path.startsWith("/")) {
						path = "/" + path;
					}
					if(!path.endsWith("/")) {
						path = path.concat("/");
					}
					
				}
				String headerHtaccess = String.format("AuthUserFile %s.htpasswd\n" + 
						"AuthType Basic\n" + 
						"AuthName \"My restricted Area\"\n" + 
						"Require valid-user\n\n", path);
				textAreaHtaccess.setText(headerHtaccess+htaccess);
				
				
				
				String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
				
				lblEncryptedPassword.setText(username+":"+bcryptHashString);
				
				
				
				
				
				
			}
		});
		
	}
}
