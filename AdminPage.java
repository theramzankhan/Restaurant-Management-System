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

	public class AdminPage extends JFrame 
	{
		AdminPage()
		{
			setLayout(null);

			Font font1 = new Font("times new roman", Font.BOLD,12);
			Font font = new Font("times new roman", Font.BOLD,20);

			JLabel lbl01=new JLabel("RESTAURANT MANAGEMENT SYSTEM");
			lbl01.setHorizontalAlignment(JLabel.CENTER);
			lbl01.setFont(font);
			lbl01.setForeground(Color.white);
			lbl01.setBounds(000,000,720,50);
			lbl01.setOpaque(true);
			lbl01.setBackground(Color.BLACK);
			add(lbl01);

			JTextField txt1=new JTextField();
			txt1.setBounds(400,100,100,30);
			add(txt1);
		
			JTextField txt2=new JTextField();
			txt2.setBounds(400,140,100,30);
			add(txt2);

			JTextField txt3=new JTextField();
			txt3.setBounds(700,100,100,30);
			add(txt3);
		
			JTextField txt4=new JTextField();
			txt4.setBounds(700,140,100,30);
			add(txt4);

			JLabel lbl1=new JLabel("DISH ID : ");
			lbl1.setBounds(300,100,100,30);
			add(lbl1);

			JLabel lbl2=new JLabel("DISH NAME :");
			lbl2.setBounds(300,140,100,30);
			add(lbl2);

			JLabel lbl3=new JLabel("DISH PRICE : ");
			lbl3.setBounds(600,100,100,30);
			add(lbl3);

			JLabel lbl4=new JLabel("CATEGORY ID :");
			lbl4.setBounds(600,140,100,30);
			add(lbl4);

			JButton btn1=new JButton(new ImageIcon("add1.png"));
			JButton btn2=new JButton(new ImageIcon("sub.png"));
			JButton btn3=new JButton(new ImageIcon("update2.png"));
			JButton btn4=new JButton(new ImageIcon("view.png"));
			JButton btn5=new JButton(new ImageIcon("clear-button.png"));

			TextArea area=new TextArea();

			JButton btn01=new JButton("HOME");
			JButton btn02=new JButton("EXIT");
			btn01.setBounds(720,0,82,50);
			btn01.setBackground(Color.white);
			btn02.setBounds(802,0,82,50);
			btn02.setBackground(Color.black);
			btn02.setForeground(Color.white);
			add(btn01);
			add(btn02);

			JLabel label=new JLabel();
			label.setBounds(400,180,200,30);
			label.setForeground(Color.red);
			add(label);

			btn1.setBounds(0,50,200,120);
			btn1.setBackground(Color.white);
			btn2.setBounds(0,170,200,120);
			btn2.setBackground(Color.white);
			btn3.setBounds(0,290,200,120);
			btn3.setBackground(Color.white);
			btn4.setBounds(0,410,200,120);
			btn4.setBackground(Color.white);
			btn5.setBounds(0,530,200,120);
			btn5.setBackground(Color.white);
			
			area.setFont(font1);
			area.setForeground(Color.white);
			area.setBackground(Color.black);
			area.setBounds(200,250,690,350);

			add(btn1);
			add(btn2);
			add(btn3);
			add(btn4);
			add(btn5);
			add(area);

			JButton btnn1=new JButton("TOTAL DISHES");
			btnn1.setBounds(200,600,345,48);
			btnn1.setBackground(Color.black);
			btnn1.setForeground(Color.white);
			add(btnn1);

			JButton btnn2=new JButton("COSTLY DISH");
			btnn2.setBounds(550,600,325,48);
			btnn2.setBackground(Color.white);
			btnn2.setForeground(Color.black);
			add(btnn2);

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
						Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","rms_admin","admin");

                        cs = conn.prepareCall("{ call Add_Dish(? , ?, ?, ?)}");
                        cs.setString("ID",txt1.getText());
                        cs.setString("D_NAME",txt2.getText());
                        cs.setString("PRICE",txt3.getText());
                        cs.setString("C_ID",txt4.getText());
                        cs.executeUpdate();

                        JOptionPane.showMessageDialog(null,"Data inserted successfully");
						txt1.setText("");
						txt1.requestFocus();
						txt2.setText("");
						txt3.setText("");
						txt4.setText("");
					}	
					catch(SQLException e)
					{
						JOptionPane.showMessageDialog(null,"Data not inserted.");
						txt1.setText("");
						txt1.requestFocus();
						txt2.setText("");
						txt3.setText("");
						txt4.setText("");
					} 
				} 			
			});

			btn2.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					CallableStatement cs;
					try
					{
						DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
						Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","rms_admin","admin");

                        cs = conn.prepareCall("{ call Remove_Dish(?)}");
                        cs.setString(1,txt1.getText());
                        int rowsUpdated = cs.executeUpdate();

                        if (rowsUpdated < 0)
						{
							JOptionPane.showMessageDialog(null,"Data Not Removed.");
							txt1.setText("");
							txt1.requestFocus();
						}	
						else
						{
							JOptionPane.showMessageDialog(null,"Data Removed successfully!");
							txt1.setText("");
							txt1.requestFocus();
						}
					}
					catch(SQLException e)
					{
						System.out.println(e);
					} 
				} 			
			});

			btn3.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					CallableStatement cs;
					try
					{
						DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
						Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","rms_admin","admin");

                        cs = conn.prepareCall("{call Update_Dish(? , ?)}");
                        cs.setString(1,txt1.getText());
                        cs.setString(2,txt2.getText());
                        int rowsUpdated = cs.executeUpdate();

                        if (rowsUpdated > 0)
						{
							JOptionPane.showMessageDialog(null,"Data Updated successfully");
							txt1.setText("");
							txt1.requestFocus();
							txt2.setText("");
						}	
						else
						{
							JOptionPane.showMessageDialog(null,"Data not Updated.");
							txt1.setText("");
							txt1.requestFocus();
						}
					}
					catch(SQLException e)
					{
						System.out.println(e);
					} 
				} 			
			});
            
			 btn4.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					try
					{
						DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
						Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","rms_admin","admin");
						
						CallableStatement cstmt = con.prepareCall("{call dbms_output.enable(32000) }");
                        cstmt.execute();

                        Statement stmt = con.createStatement();
	      				String sql = 
    									"declare  \n" +
    									" i number;  \n" +
    									" n varchar2(20);  \n" +
    									" p number;  \n" +
    									" c number;  \n" +
   										" cursor c1 is select id , d_name, price, c_id from dish;  \n" +
    									"begin  \n" +
    									"  open c1; \n" +
    									"  loop \n" +
    									"    fetch c1 into i,n,p,c;  \n" +
    									"    exit when c1%notfound;  \n" +   
    									"    dbms_output.put_line(' - - - - - - - - - - - - + - - - - - - - - - - - - - - - - - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - - - - - - - - - - - - - - - - - - - - - - - - +' || '\n' || ' DISH ID' || '\t' || '\t' || '\t' || ' DISH NAME' || '\t' || '\t' || '\t' || ' PRICE ' || '\t' || '\t' || '\t' || ' CATEGORY ID ' || '\t' || '\t' || '\t' || '\n' || '- - - - - - - - - - - - + - - - - - - - - - - - - - - - - - - - - - - - - - + - - - - - - - - - - - - - - - - - + - - - - - - - - - - - - - - - - - - - - - - - - - - - - +'  ||'\n' || '       ' ||  to_char(i) || '\t' || '\t' || '\t' || '        ' || to_char(n) || '\t' || '\t' || '\t' || '     ' || to_char(p) || '\t' || '\t' || '\t' || '         ' ||to_char(c) || '\t' || '\t' || '\t' || '        ' || '\n'  || '\n'); \n" +
    									"  end loop; \n" +
    									"end;";

    					stmt.execute(sql);
	     				cstmt = con.prepareCall("{call dbms_output.get_line(?,?)}");
						cstmt.registerOutParameter(1,java.sql.Types.VARCHAR);
						cstmt.registerOutParameter(2,java.sql.Types.NUMERIC);

						int status = 0;
						while (status == 0)
						{
    						cstmt.execute();
    						String line = cstmt.getString(1);
						    status = cstmt.getInt(2);
						    if (line != null && status == 0)
						    {
						    //    System.out.println(line);
						        area.append(line);
						    }
						}
	     
	      			}
	        			catch(SQLException  se)
	      				{
	        
	         				System.out.println(se);
	      				}     			
	      		}
			});

			 btn5.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					area.setText("");
				}
			});

			txt1.addKeyListener(new KeyAdapter() 
			{
				public void keyPressed(KeyEvent EVT) 
				{
					String value = txt1.getText();
					int l = value.length();
					char c = EVT.getKeyChar();
				if(Character.isDigit(c) ||Character.isWhitespace(c)||Character.isISOControl(c))
					{
						txt1.setEditable(true);
						label.setText("");
					} 
					else 
					{
						txt1.setEditable(false);
						label.setText("Enter only numeric digits (0-9)");
					}
				}
			});
			txt2.addKeyListener(new KeyAdapter() 
			{
				public void keyPressed(KeyEvent EVT) 
				{
					String value = txt2.getText();
					int l = value.length();
					char c = EVT.getKeyChar();
					if (Character.isLetter(c) ||Character.isWhitespace(c)||Character.isISOControl(c)) 
					{
						txt2.setEditable(true);
						label.setText("");
					} 
					else 
					{
						txt2.setEditable(false);
						label.setText("Enter only letters only (a - z)");
					}
				}
			});

			txt3.addKeyListener(new KeyAdapter() 
			{
				public void keyPressed(KeyEvent EVT) 
				{
					String value = txt1.getText();
					int l = value.length();
					char c = EVT.getKeyChar();
				if(Character.isDigit(c) ||Character.isWhitespace(c)||Character.isISOControl(c))
					{
						txt1.setEditable(true);
						label.setText("");
					} 
					else 
					{
						txt1.setEditable(false);
						label.setText("Enter only numeric digits (0-9)");
					}
				}
			});

			txt4.addKeyListener(new KeyAdapter() 
			{
				public void keyPressed(KeyEvent EVT) 
				{
					String value = txt1.getText();
					int l = value.length();
					char c = EVT.getKeyChar();
				if(Character.isDigit(c) ||Character.isWhitespace(c)||Character.isISOControl(c))
					{
						txt1.setEditable(true);
						label.setText("");
					} 
					else 
					{
						txt1.setEditable(false);
						label.setText("Enter only numeric digits (0-9)");
					}
				}
			});

			btnn1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					Connection connection = null;
       				CallableStatement callableStatement = null;

        try {
 
             DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	     connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","rms_admin","admin");
 
            callableStatement = connection.prepareCall("{? = call totalDish4()}");

            callableStatement.registerOutParameter(1, Types.INTEGER);
 
            callableStatement.execute();
 
            String count = "Total number of Dishes is : " + callableStatement.getInt(1);
        //    System.out.println("Total number of Dishes is : " + count);
            JOptionPane.showMessageDialog(null,count,"SUCCESS",JOptionPane.INFORMATION_MESSAGE);
            }

        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }

        finally {

            try {
                if(null != connection) {
 
                    callableStatement.close();
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
				}
			});

			btnn2.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					Connection connection = null;
        			CallableStatement callableStatement = null;

					try {
 
             			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	     				connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","rms_admin","admin");
			            callableStatement = connection.prepareCall("{? = call maxDish4()}");
			            callableStatement.registerOutParameter(1, Types.INTEGER);			 
			            callableStatement.execute();			 
			            String count ="Costly Dish is : " + callableStatement.getInt(1);
			        //    System.out.println("Costly Dish is : " + count);
			            JOptionPane.showMessageDialog(null,count,"SUCCESS",JOptionPane.INFORMATION_MESSAGE);
            		}
			        catch(SQLException sqlex){
			        	sqlex.printStackTrace();
        			}
        			finally {
		            try {
		                if(null != connection) {
		                    callableStatement.close();
		                    connection.close();
		                }
		            }
		            catch (SQLException sqlex) {
		                sqlex.printStackTrace();
		            }
        		}  
				}
			});

		}	

		public static void main(String args[])
		{
			AdminPage a=new AdminPage();
			a.setSize(900,688);
			a.setTitle("RSM");
			a.setLocation(300,120);
			Image icon = Toolkit.getDefaultToolkit().getImage("icon1.png");    
			a.setIconImage(icon);    
			a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			a.setVisible(true);
			a.setResizable(false);
		}
	}