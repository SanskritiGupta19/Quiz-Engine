import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class AddQuestion extends JInternalFrame {
	private JTextField txtQuestion;
	private JTextField txtOp1;
	private JTextField txtOp2;
	private JTextField txtOp3;
	private JTextField txtOp4;
	private JTextField txtCA;
	public AddQuestion() {
		setBounds(0, 0, 876, 621);
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		txtQuestion = new JTextField();
		txtQuestion.setBounds(89, 28, 682, 45);
		getContentPane().add(txtQuestion);
		txtQuestion.setColumns(10);
		
		txtOp1 = new JTextField();
		txtOp1.setColumns(10);
		txtOp1.setBounds(89, 107, 682, 45);
		getContentPane().add(txtOp1);
		
		txtOp2 = new JTextField();
		txtOp2.setColumns(10);
		txtOp2.setBounds(89, 179, 682, 45);
		getContentPane().add(txtOp2);
		
		txtOp3 = new JTextField();
		txtOp3.setColumns(10);
		txtOp3.setBounds(89, 256, 682, 45);
		getContentPane().add(txtOp3);
		
		txtOp4 = new JTextField();
		txtOp4.setColumns(10);
		txtOp4.setBounds(89, 337, 682, 45);
		getContentPane().add(txtOp4);
		
		txtCA = new JTextField();
		txtCA.setColumns(10);
		txtCA.setBounds(89, 415, 682, 45);
		getContentPane().add(txtCA);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/qe?user=root&password=root");
		String query="insert into question(ques,op1,op2,op3,op4,correct) values(?,?,?,?,?,?)";
		PreparedStatement statement=connection.prepareStatement(query);
		statement.setString(1,txtQuestion.getText());
		statement.setString(2,txtOp1.getText());
		statement.setString(3,txtOp2.getText());
		statement.setString(4,txtOp3.getText());
		statement.setString(5,txtOp4.getText());
		statement.setString(6,txtCA.getText());
		statement.executeUpdate();
		connection.close();
		JOptionPane.showMessageDialog(null, "Data Saved");
		txtOp1.setText("");
		txtOp2.setText("");
		txtOp3.setText("");
		txtOp4.setText("");
		txtCA.setText("");
		txtQuestion.setText("");
		
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			
			}
		});
		btnNewButton.setBounds(405, 503, 89, 23);
		getContentPane().add(btnNewButton);
	}
}
