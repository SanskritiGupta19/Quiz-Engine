import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtPno;
	private JTextField txtPass;
static String pno;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtPno = new JTextField();
		txtPno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPno.setBounds(204, 68, 257, 39);
		contentPane.add(txtPno);
		txtPno.setColumns(10);
		
		txtPass = new JTextField();
		txtPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPass.setColumns(10);
		txtPass.setBounds(204, 150, 257, 39);
		contentPane.add(txtPass);
		
		JComboBox cmbChoice = new JComboBox();
		cmbChoice.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbChoice.setModel(new DefaultComboBoxModel(new String[] {"Administrator", "Student"}));
		cmbChoice.setBounds(204, 228, 257, 39);
		contentPane.add(cmbChoice);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(cmbChoice.getSelectedItem().toString().equals("Student"))
					{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/qe?user=root&password=root");
		String query="select * from student where pno=? and pass=?";
		PreparedStatement statement=connection.prepareStatement(query);
		statement.setString(1,txtPno.getText());
		statement.setString(2,txtPass.getText());
		
		ResultSet result=statement.executeQuery();
		if(result.next())
		{
			pno=txtPno.getText();
			Quiz quiz=new Quiz();
			quiz.setVisible(true);
		}
		else
			JOptionPane.showMessageDialog(null,"Invalid pno or password");
		
		connection.close();
					}
					else 
					{
						if(txtPno.getText().equals("admin") && txtPass.getText().equals("india"))
						{
							Home home=new Home();
							home.setVisible(true);
						}
						else
						JOptionPane.showMessageDialog(null,"Invalid pno or password");
					}
		Login.this.dispose();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(204, 301, 178, 23);
		contentPane.add(btnNewButton);
	}
}
