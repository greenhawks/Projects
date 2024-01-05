import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.text.*;

public class Keyboard {
    JFrame f = new JFrame("Typing test");
    JLabel l, score;
    JButton retry, exit;
    int i;
    int scoreint;
    JTextPane type;
    Timer timer;
    int cursorPosition = 0,size=0;
    String[] paragraphs = {
            "There is no royal road to anything. One thing at a time, all things in succession. That which grows fast, withers as rapidly. That which grows slowly, endures\n" +
                    "Josiah Gilbert Holland",
            "The quick brown fox jumps over the lazy dog.",
            "Programming is fun!",
            "Java Swing makes GUI programming easy.",
            "Enjoy the little things in life because one day\n" +
                    "you 11 look back and realize they were the big things.",
            "Random text for the typing test."
    };

    Keyboard() {
        i = 0;
        scoreint = 0;
        l = new JLabel("Type the following:");
        score = new JLabel();
        f.setVisible(true);
        f.setLayout(null);
        f.setSize(400, 400);
    }

    void key() {
        score.setBounds(20, 80, 300, 20);
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveCursor();
            }
        });

        type = new JTextPane();
        type.setBounds(20, 20, 300, 300);
        getRandomParagraph();
        type.setEditable(false);
        retry = new JButton("Retry");
        retry.setBounds(30, 325, 100, 20);
        retry.setFocusable(false);
        retry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i=0;
                getRandomParagraph();
                cursorPosition = 0;
                timer.start();
            }
        });

        exit = new JButton("Exit");
        exit.setBounds(325, 05, 60, 20);
        exit.setFocusable(false);
        type.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                StyledDocument doc = type.getStyledDocument();
                StringBuilder text = new StringBuilder(type.getText());
                char k = e.getKeyChar();

                if (i < text.length() && text.charAt(i) == k) {
                    SimpleAttributeSet attrs = new SimpleAttributeSet();
                    StyleConstants.setForeground(attrs, Color.GREEN);

                    doc.setCharacterAttributes(i, 1, attrs, false);

                    scoreint += 1;
                }
                else
                {
                    SimpleAttributeSet attrs = new SimpleAttributeSet();
                    StyleConstants.setForeground(attrs, Color.red);

                    doc.setCharacterAttributes(i, 1, attrs, false);
                }

                i += 1;

                if (i == text.length()) {
                    display(scoreint);
                }
            }
        });




        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(f, "Thanks for using this app");
                f.setVisible(false);
            }
        });

        f.add(retry);
        f.add(type);
        f.add(score);
        f.add(exit);
    }

    private void moveCursor() {
        if (cursorPosition <= type.getText().length()) {
            type.setCaretPosition(cursorPosition);
            cursorPosition++;
        } else {
            timer.stop();
        }
    }

    private void getRandomParagraph() {
        Random random = new Random();
        int index = random.nextInt(paragraphs.length);
        String text = paragraphs[index];
        type.setText(text);
        size = text.length();
        i = 0;
    }

    public void display(int score) {
        JOptionPane.showMessageDialog(f, "Your score is: " + score+"/"+size);
        scoreint = 0;
        i = 0;
        key();
    }
}