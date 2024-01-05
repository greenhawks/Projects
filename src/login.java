import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class login
{
    JFrame f = new JFrame("Login");
    JButton login;
    JTextField username;
    JPasswordField password;
    JLabel register;
    boolean flag = false;
    Register reg = new Register();
    void register()
    {

        Database db = new Database();
        f.setBounds(400,400,400,400);
        JLabel l = new JLabel();
        l.setText("Username : ");
        l.setBounds(20,30,100,20);
        username = new JTextField();
        username.setBounds(100,30,170,20);
        password = new JPasswordField();
        JLabel pass = new JLabel();
        pass.setText("Password : ");
        pass.setBounds(20,80,100,20);
        password.setBounds(100,80,170,20);
        login = new JButton("Login");
        login.setBounds(110,120,100,30);
        register = new JLabel("Register");
        register.setForeground(Color.BLUE);
        register.setBounds(135,160,100,20);
        register.setToolTipText("Click to Register");
        register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                f.setVisible(false);
                reg.UserRegister();
            }
        });
        login.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usern = username.getText().toString();
                String passw = password.getText().toString();
                if(usern.isEmpty() || passw.isEmpty())
                {
                    JOptionPane.showMessageDialog(f,"Please Fill the details");
                }
                else {
                    try {

                        if (db.check(usern, passw)) {
                            flag = true;
                            f.setVisible(false);
                            JOptionPane.showMessageDialog(f, "Login Success");
                        } else {
                            JOptionPane.showMessageDialog(f, "Register first");
                        }
                        if (flag) {
                            Keyboard k = new Keyboard();
                            k.key();
                        }
                    } catch (Exception h) {
                        System.out.println(h);
                    }
                }
            }

        });
        f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int enter = e.getKeyCode();
                if (enter == 10) {
                    String usern = username.getText().toString();
                    String passw = password.getText().toString();
                    if (usern.isEmpty() || passw.isEmpty()) {
                        JOptionPane.showMessageDialog(f, "Please Fill the details");
                    } else {
                        try {

                            if (db.check(usern, passw)) {
                                flag = true;
                                f.setVisible(false);
                                JOptionPane.showMessageDialog(f, "Login Success");
                            } else {
                                JOptionPane.showMessageDialog(f, "Register first");
                            }
                            if (flag) {

                                Keyboard k = new Keyboard();
                                k.key();
                            }
                        } catch (Exception h) {
                            System.out.println(h);
                        }
                    }
                }
            }
        });
        f.add(username);
        f.add(l);
        f.add(pass);
        f.add(password);
        f.add(login);
        f.add(register);
        f.setSize(300,300);
        f.setLayout(null);
        f.setVisible(true);
    }
}
