import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SearchStudent extends JInternalFrame {
	private JTextField textField;
	private JScrollPane scrollPane;
	JTable table;
	void disp()
	{
		try
		{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/qe?user=root&password=root");
String query="select * from student";
PreparedStatement statement=connection.prepareStatement(query);
ResultSet result=statement.executeQuery();
DefaultTableModel model=new DefaultTableModel();
model.addColumn("Pno");
model.addColumn("Password");
model.addColumn("Name");

while(result.next())
{
	String row[]= {result.getString(1),result.getString(2),result.getString(3)};
	model.addRow(row);
}
table.setModel(model);
connection.close();

		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	
	void disp(String n)
	{
		try
		{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/qe?user=root&password=root");
String query="select * from student where name like '"+n+"%'";
PreparedStatement statement=connection.prepareStatement(query);
ResultSet result=statement.executeQuery();
DefaultTableModel model=new DefaultTableModel();
model.addColumn("Pno");
model.addColumn("Password");
model.addColumn("Name");

while(result.next())
{
	String row[]= {result.getString(1),result.getString(2),result.getString(3)};
	model.addRow(row);
}
table.setModel(model);
connection.close();

		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	public SearchStudent() {
		setBounds(0, 0, 701, 463);
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			disp(textField.getText());
			}
		});
		getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		table=new JTable();
		
		scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		disp();
	}

}
