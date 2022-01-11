import javax.swing.*;
import java.awt.*;


public class Logs extends JPanel {
    public JPanel panel1 = new JPanel();
    public JPanel panel2 = new JPanel();
    public JPanel panel3 = new JPanel();
    public JButton Back = new JButton("Back");
    public JButton Show = new JButton("Show");
    public JTextArea TextLog = new JTextArea();
    public JScrollPane scrollPane;


    public Logs() {
        setPreferredSize(new Dimension(800, 800));
        setLayout(new BorderLayout());
        panel1.add(buttonMaker(Back), BorderLayout.EAST);
        panel1.add(buttonMaker(Show), BorderLayout.WEST);
        add(panelMaker((panel1)), BorderLayout.NORTH);
        add(panelMaker1((panel2)), BorderLayout.WEST);
        add(panel3(), BorderLayout.CENTER);


    }

    public JPanel panelMaker(JPanel x) {
        x.setPreferredSize(new Dimension(800, 100));
        x.setBackground(Color.decode("#DAD2BC"));
        return x;
    }

    public JPanel panelMaker1(JPanel x) {
        x.setPreferredSize(new Dimension(100, 800));
        x.setBackground(Color.decode("#DAD2BC"));
        return x;
    }

    public JPanel panel3() {
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setEnabled(true);
        scrollPane.setViewportView(TextLog);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        TextLog.setBackground(Color.decode("#F5F1ED"));
        TextLog.setFont(new Font("Courier", Font.BOLD, 18));
        TextLog.setEditable(false);
        TextLog.setLineWrap(true);
        panel3.setLayout(new BorderLayout());
        panel3.add(scrollPane, BorderLayout.CENTER);
        return panel3;
    }

    public JButton buttonMaker(JButton button) {

        button.setBackground(Color.decode("#A99985"));
        button.setFont(new Font("Courier", Font.BOLD, 30));
        button.setPreferredSize(new Dimension(200, 100));
        return button;
    }


}
