import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JScrollPane; 
import javax.swing.JTable; 
import javax.swing.event.*;  
import java.sql.*;

public class LoginPage extends JFrame
{
    Container c;
    JTextField txt1;
    JPasswordField p1;
    JLabel lbl1;
    JLabel lbl2;
    JButton btn1;
    JButton btn2;
    JButton btn3;
    LoginPage()
    {
        c=getContentPane();
        setLayout(null);

        Font font = new Font("times new roman", Font.BOLD,20);

        lbl1=new JLabel("User Name :");
        lbl1.setFont(font);
        lbl1.setForeground(Color.BLACK);
        lbl1.setBounds(50,50,150,25);
        add(lbl1);
        txt1=new JTextField(10); 
        txt1.setFont(font);
        txt1.setBounds(200,50,150,25);
        add(txt1);

        lbl2=new JLabel("Password    :");
        lbl2.setFont(font);
        lbl2.setForeground(Color.BLACK);
        lbl2.setBounds(50,100,150,25);
        add(lbl2);
        p1=new JPasswordField(10);
        p1.setBackground(Color.BLACK);
        p1.setForeground(Color.WHITE);
        p1.setFont(font);
        p1.setEchoChar('*');
        p1.setBounds(200,100,150,25);
        add(p1);

        Checkbox cb1=new Checkbox("Login as admin",true);
    //  cb1.setFont(font);
        cb1.setForeground(Color.white);
        cb1.setBackground(Color.black);
        cb1.setBounds(50,150,120,20);
        add(cb1);

        btn1=new JButton("LOGIN");
        btn1.setBackground(Color.WHITE);
    //    btn1.setFont(font);
        btn1.setBounds(200,190,100,50);
        add(btn1);

        btn2=new JButton("USER");
        btn2.setBackground(Color.BLACK);
        btn2.setForeground(Color.WHITE);
    //    btn1.setFont(font);
        btn2.setBounds(310,190,100,50);
        add(btn2);

        btn3=new JButton("REGISTER");
        btn3.setBackground(Color.BLUE);
        btn3.setForeground(Color.WHITE);
        btn3.setBounds(240,270,150,40);
        add(btn3);

        btn1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                String usrname=txt1.getText();
                String pass=p1.getText().toString();
                if(usrname.equals("admin") && pass.equals("123"))
                {
                    JOptionPane.showMessageDialog(c,"You are logged in successfully to ADMIN PAGE!!! ","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
                    dispose();
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
                else
                {
                    JOptionPane.showMessageDialog(c,"Incorrect login or password","Error",JOptionPane.ERROR_MESSAGE); 
                    txt1.setText("");
                    p1.setText("");
                    txt1.requestFocus();
                }
            }
        });

        btn2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                JOptionPane.showMessageDialog(c,"You are logged in successfully to USER PAGE!!! ","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
                dispose();

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
        });

        // for background image
        setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("image1.jpg"));
        add(background);
        background.setLayout(new FlowLayout());
    }

    public static void main(String args[])
    {
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
}