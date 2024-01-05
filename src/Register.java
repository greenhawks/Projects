import javax.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
public class Register {
    JFrame f = new JFrame("Register");
    JTextField username , full_name , mobileNumber , email;
    JPasswordField newpassword,currentpassword;
    JButton register;
    JLabel login;
    void UserRegister()
    {
        login l = new login();
        Database db = new Database();
        JLabel user = new JLabel("Username : ");
        user.setBounds(20,40,100,20);
        user.setToolTipText("Enter Username");
        username = new JTextField();
        username.setBounds(170,40,300,20);
        JLabel full = new JLabel("Full Name : ");
        full.setBounds(20,80,100,20);
        full.setToolTipText("Enter Full Name");
        full_name = new JTextField();
        full_name.setBounds(170,80,300,20);
        JLabel em = new JLabel("Email : ");
        em.setBounds(20,120,100,20);
        em.setToolTipText("Enter Your Email");
        email = new JTextField();
        email.setBounds(170,120,300,20);
        JLabel mob = new JLabel("Mobile Number : ");
        mob.setBounds(20,160,100,20);
        mob.setToolTipText("Enter your Mobile Number");
        mobileNumber = new JTextField();
        mobileNumber.setBounds(170,160,300,20);
        JLabel pa = new JLabel("New Password : ");
        pa.setBounds(20,200,100,20);
        pa.setToolTipText("Enter new Password");
        newpassword = new JPasswordField();
        newpassword.setBounds(170,200,300,20);
        JLabel cur = new JLabel("Current Password : ");
        cur.setBounds(20,240,120,20);
        cur.setToolTipText("Retype the new Password");
        currentpassword = new JPasswordField();
        currentpassword.setBounds(170,240,300,20);
        register = new JButton("Register");
        register.setBounds(200,300,100,20);
        login = new JLabel("Login");
        login.setBounds(230,340,100,20);
        login.setToolTipText("Redirect to login page");
        login.setForeground(Color.blue);
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                l.register();
                f.setVisible(false);
            }
        });
        register.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String usern = username.getText().toString();
                    String passw = newpassword.getText().toString();
                    String ema = email.getText().toString();
                    String mobnum = mobileNumber.getText().toString();
                    String fulln = full_name.getText().toString();
                    db.create(usern,fulln,mobnum,ema, passw);
                    JOptionPane.showMessageDialog(f,"Registered successfully");
                }
                catch (Exception h)
                {
                    System.out.println(h);
                }
            }
        });
        f.add(user);
        f.add(username);
        f.add(full);
        f.add(full_name);
        f.add(em);
        f.add(email);
        f.add(mob);
        f.add(mobileNumber);
        f.add(pa);
        f.add(newpassword);
        f.add(cur);
        f.add(currentpassword);
        f.add(register);
        f.add(login);
        f.setSize(500,500);
        f.setLayout(null);
        f.setVisible(true);
    }
}
