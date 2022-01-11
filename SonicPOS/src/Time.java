import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Time extends JPanel {

    private JLabel label;
    private final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("hh:mm:ss");

    public Time() {
        label = new JLabel(" ");
        label.setFont(new Font("Courier", Font.BOLD, 15));
        add(label);
        setBackground(Color.decode("#F5F1ED"));


        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                while (true) {
                    Date date = getDate();
                    String time;
                    time = SIMPLE_DATE_FORMAT.format(date);
                    label.setText(time);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread t = new Thread(runnable);
        t.start();


    }

    public JLabel getLabel() {
        return label;
    }

    public static java.util.Date getDate() {
        java.util.Date date = new java.util.Date();
        return date;
    }
}


