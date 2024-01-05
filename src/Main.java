import javax.swing.*;
import java.util.*;
class Main
{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            login Login = new login();
            Login.register();
        });
    }

}