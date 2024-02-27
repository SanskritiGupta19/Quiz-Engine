import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class AddStudent extends JInternalFrame {
	private JTextField txtPno;
	private JTextField txtPass;
	private JTextField txtName;

	
	public AddStudent() {
		setBounds(0, 0, 450, 300);
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		txtPno = new JTextField();
		txtPno.setBounds(108, 26, 221, 20);
		getContentPane().add(txtPno);
		txtPno.setColumns(10);
		
		txtPass = new JTextField();
		txtPass.setColumns(10);
		txtPass.setBounds(108, 80, 221, 20);
		getContentPane().add(txtPass);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(108, 136, 221, 20);
		getContentPane().add(txtName);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
		try
			{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/qe?user=root&password=root");
	String query="insert into student values(?,?,?)";
	PreparedStatement statement=connection.prepareStatement(query);
	statement.setString(1,txtPno.getText());
	statement.setString(2,txtPass.getText());
	statement.setString(3,txtName.getText());
	statement.executeUpdate();
	connection.close();
	JOptionPane.showMessageDialog(null, "Data Saved");
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			
			}
		});
		btnNewButton.setBounds(167, 189, 89, 23);
		getContentPane().add(btnNewButton);
	}
}
