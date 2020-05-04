import java.awt.*;
import javax.swing.*;    
import javax.swing.event.*;  
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Color;
import java.sql.*;

public class UserPage extends JFrame
{
	UserPage()
	{
			JButton btn=new JButton("SEARCH");
			btn.setBounds(780,605,145,50);
			btn.setBackground(Color.blue);
			btn.setForeground(Color.black);
			add(btn);

			JLabel lbl1=new JLabel("DISH NAME :");
			lbl1.setForeground(Color.blue);
			lbl1.setBounds(45,606,100,40);
			add(lbl1);

			JTextField txt2=new JTextField();
			txt2.setBounds(135,605,320,45);
			add(txt2);
		
	/*		JLabel lbl2=new JLabel("DISH NAME :");
			lbl2.setForeground(Color.blue);
			lbl2.setBounds(320,603,120,40);
			add(lbl2);

			JTextField txt2=new JTextField();
			txt2.setBounds(400,603,120,40);
			add(txt2);*/


            String data[][]={{"1","Biryani","200","CLICK HERE TO ORDER"},{"1","Biryani","200","CLICK HERE TO ORDER"},
        	{"1","Biryani","200","CLICK HERE TO ORDER"},{"1","Biryani","200","CLICK HERE TO ORDER"},
        	{"1","Biryani","200","CLICK HERE TO ORDER"},{"1","Biryani","200","CLICK HERE TO ORDER"},
        	{"1","Biryani","200","CLICK HERE TO ORDER"},{"1","Biryani","200","CLICK HERE TO ORDER"},
        	{"1","Biryani","200","CLICK HERE TO ORDER"},{"1","Biryani","200","CLICK HERE TO ORDER"},
        	{"1","Biryani","200","CLICK HERE TO ORDER"},{"1","Biryani","200","CLICK HERE TO ORDER"},
        	{"1","Biryani","200","CLICK HERE TO ORDER"},{"1","Biryani","200","CLICK HERE TO ORDER"},
        	{"1","Biryani","200","CLICK HERE TO ORDER"},{"1","Biryani","200","CLICK HERE TO ORDER"},
        	{"1","Biryani","200","CLICK HERE TO ORDER"},{"1","Biryani","200","CLICK HERE TO ORDER"},
        	{"1","Biryani","200","CLICK HERE TO ORDER"},{"1","Biryani","200","CLICK HERE TO ORDER"},
        	{"1","Biryani","200","CLICK HERE TO ORDER"},{"1","Biryani","200","CLICK HERE TO ORDER"},
        	{"1","Biryani","200","CLICK HERE TO ORDER"},{"1","Biryani","200","CLICK HERE TO ORDER"},
        	{"1","Biryani","200","CLICK HERE TO ORDER"},{"1","Biryani","200","CLICK HERE TO ORDER"},
        	{"1","Biryani","200","CLICK HERE TO ORDER"},{"1","Biryani","200","CLICK HERE TO ORDER"},
        	{"1","Biryani","200","CLICK HERE TO ORDER"},{"1","Biryani","200","CLICK HERE TO ORDER"},
        	{"1","Biryani","200","CLICK HERE TO ORDER"},{"1","Biryani","200","CLICK HERE TO ORDER"},	
        	{"1","Biryani","200","CLICK HERE TO ORDER"},{"1","Biryani","200","CLICK HERE TO ORDER"},
        	{"1","Biryani","200","CLICK HERE TO ORDER"},{"1","Biryani","200","CLICK HERE TO ORDER"}};

            String column[]={"ORDER ID","DISH","PRICE","ORDER"};         
            final JTable jt=new JTable(data,column); 
         //   jt.setRowHeight(31);    
            jt.setCellSelectionEnabled(true); 

            //Line 5,18,19 and 20 are used to align the rows data at CENTER
            DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();     
			tableRenderer.setHorizontalAlignment(JLabel.CENTER); //Aligning the table data centrally.
			jt.setDefaultRenderer(Object.class, tableRenderer);

            ListSelectionModel select= jt.getSelectionModel();  
            select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            select.addListSelectionListener(new ListSelectionListener() {  
              public void valueChanged(ListSelectionEvent e) {  
              	String data = "Your selected dish is : " +  jt.getValueAt(jt.getSelectedRow(), 1).toString() + " !!!" + "\n" + "You are directed to the payment page.";
              	String data1 = "You seems not interested !!!";
             // 	System.out.println(data);
             // 	System.out.println("THE SELECTED DISH : " + jt.getValueAt(jt.getSelectedRow(), 1).toString()); 
             // 	JOptionPane.showMessageDialog(f,data,"SUCCESS",JOptionPane.INFORMATION_MESSAGE);
              	
              	ImageIcon icon = new ImageIcon("food7.png");
              	ImageIcon icon1 = new ImageIcon("sad1.png");
             	 int response = JOptionPane.showConfirmDialog(null, "Do you want to order?", "Confirm",
        		 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				   if (response == JOptionPane.NO_OPTION) {
				   		JOptionPane.showMessageDialog(null,data1,"Cancel",JOptionPane.INFORMATION_MESSAGE,icon1);
				    //  System.out.println("You have not ordered.");
				    } else if (response == JOptionPane.YES_OPTION) {
				    //  System.out.println(data);
				      JOptionPane.showMessageDialog(null,data,"SUCCESS",JOptionPane.INFORMATION_MESSAGE,icon);
				      dispose();
				      PaymentPage a=new PaymentPage();
						a.setSize(500,400);
						a.setTitle("RSM");
						a.setLocation(300,120);
					//		Image icon = Toolkit.getDefaultToolkit().getImage("icon1.png");    
					//	a.setIconImage(icon);    
						a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						a.setVisible(true);
						a.setResizable(false);
				    } 
              	}     
            }); 

            btn.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
				//	String str = txt1.getText(); 
					String str1 = txt2.getText();
					try
					{
						DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
						Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","rms_user","user");
						PreparedStatement st = con.prepareStatement("select * from rms_admin.dish where  d_name=?");  
			         //   st.setString(1, str);  
			            st.setString(1, str1);
			            ResultSet rs = st.executeQuery(); 

						if (rs.next()) 
						{ 
							String data = "ID : " + rs.getString(1) +"   DISH : "+rs.getString(2) + "   PRICE : "+rs.getString(3) ;
							JOptionPane.showMessageDialog(null,data,"SUCCESS",JOptionPane.INFORMATION_MESSAGE);
						} 
						else
						{ 
							String data1 = "No Dish Found...";
							JOptionPane.showMessageDialog(null,data1,"FAILURE",JOptionPane.INFORMATION_MESSAGE);
						//	System.out.println("Record Not Found..."); 
						} 
						con.close(); 
					} 
					catch(Exception e) 
					{ 
						System.out.println(e); 
					} 
				      		}
			});

		/*	btn.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					try
					{
						DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
						Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
						
						String sql = "select name FROM student";
	 					Statement  stmt  =  con.createStatement();
						ResultSet  rs = stmt.executeQuery(sql);

						while(rs.next())
	     				{
	     					String name = rs.getString(1);
	     					System.out.println(name);
	     				}
	     			}
					catch(SQLException e)
					    {
	        
	         				System.out.println(e);
	      				}  
				}
			});*/


            JScrollPane sp=new JScrollPane(jt);    
            add(sp);  

        }
         public static void main(String args[])
    {
        UserPage f=new UserPage();
        f.setResizable(false);
        f.setSize(950,700);
        f.setTitle("RSM");
        f.setLocation(300,120);
        Image icon = Toolkit.getDefaultToolkit().getImage("icon1.png");    
        f.setIconImage(icon);    
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }
}