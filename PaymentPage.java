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

	public class PaymentPage extends JFrame 
	{
		PaymentPage()
		{
			setLayout(null);

			JTextField txt1=new JTextField();
			txt1.setBounds(230,80,100,30);
			add(txt1);
		
			JTextField txt2=new JTextField();
			txt2.setBounds(230,120,100,30);
			add(txt2);

			JLabel label01=new JLabel("-------------- PAYMENT PAGE -------------");
			label01.setBounds(150,35,500,30);
			label01.setForeground(Color.red);
			add(label01);

			JLabel lbl1=new JLabel("ACC NO : ");
			lbl1.setBounds(155,80,110,30);
			add(lbl1);

			JLabel lbl2=new JLabel("AMOUNT :");
			lbl2.setBounds(155,120,110,30);
			add(lbl2);

			JButton btn01=new JButton("Login");
			JButton btn02=new JButton("Logout");
			btn01.setBounds(720,0,82,50);
			btn01.setBackground(Color.white);
			btn02.setBounds(802,0,82,50);
			btn02.setBackground(Color.black);
			btn02.setForeground(Color.white);
			add(btn01);
			add(btn02);

			JButton btn1=new JButton("PAY");
	        btn1.setBackground(Color.BLUE);
	        btn1.setForeground(Color.WHITE);
	        btn1.setBounds(160,200,170,40);
	        add(btn1);

	        JButton btn2=new JButton("CLICK HERE TO SEE AMOUNT : ");
	        btn2.setBackground(Color.PINK);
	        btn2.setForeground(Color.WHITE);
	        btn2.setBounds(00,320,485,40);
	        add(btn2);

	        JLabel label=new JLabel();
			label.setBounds(160,240,300,40);
			label.setForeground(Color.red);
			add(label);

			btn01.addActionListener(new ActionListener()
        	{
            	public void actionPerformed(ActionEvent ae)
            	{
            		dispose();
                	LoginPage f=new LoginPage();
			        f.setResizable(false);
			        f.setSize(900,638);
			        f.setTitle("RSM");
			        f.setLocation(300,120);
			        Image icon = Toolkit.getDefaultToolkit().getImage("icon1.png");    
			        f.setIconImage(icon);    
			        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			        f.setVisible(true);
            	}
        	});


			btn02.addActionListener(new ActionListener()
        	{
            	public void actionPerformed(ActionEvent ae)
            	{
                	System.exit(0);
            	}
        	});

			btn1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					CallableStatement cs;
					try
					{
						DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
						Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","rms_user","user");

                        cs = conn.prepareCall("{call bln_tra1(? , ?)}");
                        cs.setString(1,txt1.getText());
                        cs.setString(2,txt2.getText());
                        cs.executeQuery();

                        String data = "Thank You!!! " + "\n" + "Visit Again";
                        if (txt1.getText().isEmpty() || txt2.getText().isEmpty())
						{
							label.setText("THE FIELDS CANNOT BE LEFT BLANK.");
						}
                        else{
                        	label.setText("Payment done successfully!!!");
                        	JOptionPane.showMessageDialog(null,data,"SUCCESS",JOptionPane.INFORMATION_MESSAGE);
                        }
					}
					catch(SQLException e)
					{
						System.out.println(e);
					} 
				} 			
			});

			btn2.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					try
					{
						DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
						Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","rms_user","user");
	
	     				String sql = "select * from customer_table";
	     				Statement  stmt  =  con.createStatement();
						ResultSet  rs = stmt.executeQuery(sql);
						
	       				while(rs.next())
	     				{
	     					label.setText("");
	      					String id ="AMOUNT IS " + rs.getLong(3);
    					//	System.out.println(" Customer's Remaining Amount : " + id);
    						JOptionPane.showMessageDialog(null,id,"SUCCESS",JOptionPane.INFORMATION_MESSAGE);
	      				}
	      			}

	        			catch(SQLException  se)
	      				{
	        
	         				System.out.println(se);
	      				}     			
	      		}
			});
		}	

		public static void main(String args[])
		{
			PaymentPage a=new PaymentPage();
			a.setSize(500,400);
			a.setTitle("RSM");
			a.setLocation(300,120);
			Image icon = Toolkit.getDefaultToolkit().getImage("icon1.png");    
			a.setIconImage(icon);    
			a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			a.setVisible(true);
			a.setResizable(false);
		}
	}