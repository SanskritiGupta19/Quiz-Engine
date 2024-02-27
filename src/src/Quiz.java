import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Quiz extends JFrame {

	String yourAnswer="";
	private JPanel contentPane;
	private JButton btnNewButton;
	private JRadioButton rdbtnOp2;
	private JRadioButton rdbtnOp3;
	private JRadioButton rdbtnOp4;
	private JRadioButton rdbtnOp1;
	private ArrayList<Question> questions;
int count=-1;
int score;
private JLabel lblNewLabel;
	void fill()
	{
		questions=new ArrayList<>();
		try
		{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/qe?user=root&password=root");
String query="select * from question";
PreparedStatement statement=connection.prepareStatement(query);
ResultSet result=statement.executeQuery();
while(result.next())
{
Question question=new Question(result.getString(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7));
questions.add(question);
}
connection.close();

		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	
	void next()
	{
		if(count<questions.size()-1)
		{
		count++;
		Question question=questions.get(count);
		lblNewLabel.setText(question.getQuestion());
		rdbtnOp1.setText(question.getOp1());
		rdbtnOp2.setText(question.getOp2());
		rdbtnOp3.setText(question.getOp3());
		rdbtnOp4.setText(question.getOp4());
		}
		else
		{
			saveScore();
			JOptionPane.showMessageDialog(null,"You have already reached at last question");
			JOptionPane.showMessageDialog(null,"Your Score is "+score);
			this.dispose();
		}
	
	}
	
	public Quiz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 892, 806);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(59, 36, 785, 109);
		contentPane.add(lblNewLabel);
		
		rdbtnOp1 = new JRadioButton("New radio button");
		rdbtnOp1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnOp1.setBounds(61, 196, 783, 71);
		contentPane.add(rdbtnOp1);
		
		rdbtnOp2 = new JRadioButton("New radio button");
		rdbtnOp2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnOp2.setBounds(59, 304, 783, 71);
		contentPane.add(rdbtnOp2);
		
		rdbtnOp3 = new JRadioButton("New radio button");
		rdbtnOp3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnOp3.setBounds(59, 410, 783, 71);
		contentPane.add(rdbtnOp3);
		
		rdbtnOp4 = new JRadioButton("New radio button");
		rdbtnOp4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnOp4.setBounds(59, 508, 783, 71);
		contentPane.add(rdbtnOp4);
		
		ButtonGroup gp=new ButtonGroup();
		gp.add(rdbtnOp1);
		gp.add(rdbtnOp2);
		gp.add(rdbtnOp3);
		gp.add(rdbtnOp4);
	btnNewButton = new JButton("Next");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		Question currentQuestion=questions.get(count);
		
		if(rdbtnOp1.isSelected())
			yourAnswer=rdbtnOp1.getText();
		else if(rdbtnOp2.isSelected())
			yourAnswer=rdbtnOp2.getText();
		else if(rdbtnOp3.isSelected())
			yourAnswer=rdbtnOp3.getText();
		else if(rdbtnOp4.isSelected())
			yourAnswer=rdbtnOp4.getText();
		
		if(yourAnswer.equals(currentQuestion.getCa()))
			score++;
		
			next();
		}
	});
		btnNewButton.setBounds(736, 382, 89, 23);
		contentPane.add(btnNewButton);
		fill();
		next();
	}
	
	void saveScore()
	{
		try
		{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/qe?user=root&password=root");
String query="insert into result values(?,?,?)";
PreparedStatement statement=connection.prepareStatement(query);
statement.setString(1,Login.pno);
statement.setInt(2,score);
java.util.Date date=new java.util.Date();

String currentDate=(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate();
statement.setString(3,currentDate);
statement.executeUpdate();
connection.close();
//JOptionPane.showMessageDialog(null, "Data Saved");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	
}
