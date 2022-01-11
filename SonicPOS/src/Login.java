import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

class Login extends JPanel {
    public String userName;
    public char[] password;
    public static final int UNIT_SIZE = 50;
    public boolean correctName;
    public boolean correctPass;
    public static Time cTime = new Time();
    public ArrayList<String> usernames;
    private ArrayList<String> passes = new ArrayList<>();
    public ClassLoader tl = this.getClass().getClassLoader();
    public URL imageURL = tl.getResource("resources/person.png");
    public ImageIcon icon = new ImageIcon(imageURL);
    public JPanel panel2 = new JPanel();
    public JPanel panel3 = new JPanel();
    public JPanel panel4 = new JPanel();
    public JPanel panel5 = new JPanel();
    public JPanel panel = new JPanel();
    public JTextField name = new JTextField();
    public JPasswordField pass = new JPasswordField();

    public Login() {
        usernames = new ArrayList<>();
        panel.setLayout(new BorderLayout(2, 9));
        panel.setMinimumSize(new Dimension(800, 800));
        panel.setBackground(Color.decode("#F5F1ED"));
        panel.add(panel1(), BorderLayout.CENTER);
        panel.add(panel2(), BorderLayout.SOUTH);
        panel.add(panel3(), BorderLayout.NORTH);
        panel.add(panel4(), BorderLayout.EAST);
        panel.add(panel5(), BorderLayout.WEST);
        getPasses().add("123");
        this.add(panel);

    }

    public static String getDate() {
        Calendar today = Calendar.getInstance();
        int day = today.get(Calendar.DAY_OF_MONTH);
        int month = today.get(Calendar.MONTH);
        int year = today.get(Calendar.YEAR);
        return day + "/" + (month + 1) + "/" + year;
    }

    public JPanel panel1 = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(60, 60);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setColor(Color.decode("#DAD2BC"));
            graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
            graphics.setColor(getForeground());
            graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint border
        }
    };


    public JButton button = new JButton() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(30, 30); //Border corners
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setColor(Color.decode("#DAD2BC"));
            graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint background
            graphics.setColor(getForeground());
            graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint border
        }
    };



    public JPanel panel1() {
        panel1.setPreferredSize(new Dimension(200, 500));
        panel1.setLayout(null);
        panel1.setOpaque(false);
        panel1.add(passwordLabel());
        panel1.add(passwordField());
        panel1.add(nameLabel());
        panel1.add(nameField());
        panel1.add(loginButton());
        panel1.add(label2());
        return panel1;

    }

    public JPanel panel2() {
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel2.setBackground(Color.decode("#F5F1ED"));
        panel2.add(timeAndDate());
        panel2.add(cTime);
        panel2.setPreferredSize(new Dimension(800, 90));


        return panel2;
    }

    public JPanel panel3() {
        panel3.setBackground(Color.decode("#F5F1ED"));
        panel3.setLayout(null);
        panel3.setPreferredSize(new Dimension(800, 150));
        panel3.add(label1());
        return panel3;
    }

    public JPanel panel4() {
        panel4.setBackground(Color.decode("#F5F1ED"));
        panel4.setPreferredSize(new Dimension(90, 150));
        return panel4;
    }

    public JPanel panel5() {
        panel5.setBackground(Color.decode("#F5F1ED"));
        panel5.setPreferredSize(new Dimension(90, 90));
        return panel5;
    }


    public JLabel timeAndDate() {
        JLabel label = new JLabel();
        label.setFont(new Font("Courier", Font.BOLD, 15));
        label.setText("Date: " + getDate());
        label.setBounds(0, 0, (UNIT_SIZE * 5), (UNIT_SIZE));
        return label;

    }

    public JLabel label1() {
        JLabel label1 = new JLabel();
        label1.setFont(new Font("Broadway", Font.BOLD, 120));
        label1.setForeground(Color.decode("#A99985"));
        label1.setText("SONIC");
        label1.setBounds(200, 20, (UNIT_SIZE * 15), (UNIT_SIZE) * 3);
        return label1;
    }

    public JLabel label2() {
        JLabel label2 = new JLabel(icon);
        label2.setFont(new Font("Courier", Font.BOLD, 30));
        label2.setBounds(185, 10, UNIT_SIZE * 5, UNIT_SIZE * 3);
        return label2;
    }

    public JLabel passwordLabel() {
        JLabel label = new JLabel();
        label.setText("Password:");
        label.setFont(new Font("Courier New", Font.BOLD, 22));
        label.setBounds(UNIT_SIZE, UNIT_SIZE * 5, UNIT_SIZE * 4, UNIT_SIZE);
        label.setOpaque(false);
        return label;

    }

    public JLabel nameLabel() {
        JLabel label = new JLabel();
        label.setText("Username:");
        label.setFont(new Font("Courier New", Font.BOLD, 22));
        label.setBounds(UNIT_SIZE, UNIT_SIZE * 3, UNIT_SIZE * 4, UNIT_SIZE);
        label.setOpaque(false);
        return label;

    }

    public JTextField nameField() {

        name.setFont(new Font("Courier New", Font.BOLD, 21));
        name.setBounds(175, UNIT_SIZE * 3, UNIT_SIZE * 6, UNIT_SIZE);
        name.setOpaque(false);

        return name;


    }

    public JPasswordField passwordField() {

        pass.setFont(new Font("Courier New", Font.BOLD, 21));
        pass.setBounds(175, UNIT_SIZE * 5, UNIT_SIZE * 6, UNIT_SIZE);
        pass.setOpaque(false);
        return pass;


    }

    public JButton loginButton() {
        JLabel alb = new JLabel("Login");
        button.add(alb);
        alb.setFont(new Font("Courier New", Font.BOLD, 21));
        button.setBackground(Color.decode("#A99985"));
        button.setBounds(UNIT_SIZE * 5, UNIT_SIZE * 7, 100, UNIT_SIZE);
        button.setOpaque(true);

        return button;

    }


    public void check() throws IOException, ClassNotFoundException {
        userName = name.getText();
        password = pass.getPassword();

        for (String username : usernames) {
            if (username.equals(userName)) {
                correctName = true;
                break;
            }
        }

        for (String pass1 : getPasses()) {
            if (pass1.equals(String.valueOf(password))) {
                correctPass = true;
                break;
            }
        }
        System.out.println(usernames);
    }


    public ArrayList<String> getPasses() {
        return passes;
    }
}