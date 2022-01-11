import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;


public class Mpage extends JPanel implements ActionListener {

    public CardLayout cl = new CardLayout();
    public JPanel mainPanel = new JPanel(cl);
    public ClassLoader tl = this.getClass().getClassLoader();
    public URL imageURL1 = tl.getResource("resources/kekle.png");
    public URL imageURL2 = tl.getResource("resources/fekle.png");
    public URL imageURL4 = tl.getResource("resources/kcikar.png");
    public Icon iconk = new ImageIcon(imageURL1);
    public Icon iconf = new ImageIcon(imageURL2);
    public Icon iconekc = new ImageIcon(imageURL4);
    public JButton ekleK = new JButton("+");
    public JButton ekleF = new JButton("+");
    public JButton cikarK = new JButton("-");
    public JButton foto = new JButton("Photo");
    public JTextField nameField;
    public JTextField nameField2;
    public JTextField nameField3;
    public JTextField nameField4;
    public JTextField priceField;
    public String[] menuStrings = {"Food", "Drink", "Dessert"};
    public JComboBox menu = new JComboBox(menuStrings);
    public JPanel panel1 = new JPanel();
    public JPanel panel2 = new JPanel();
    public JPanel panel3 = new JPanel();
    public JPanel panel4 = new JPanel();
    public JPanel panel5 = new JPanel();
    public JPanel panel6 = new JPanel();
    public JButton button = new JButton("Menu");
    public JButton button2 = new JButton("Logs");
    public JButton button4 = new JButton("User");
    public JButton button5 = new JButton(iconf);
    public JButton button6 = new JButton(iconk);
    public JButton button7 = new JButton(iconekc);
    public JButton logout = new JButton("↵");
    public JButton Back = new JButton("↵");

    public Mpage() {
        mainPanel.add("1", menuEkle());
        mainPanel.add("2", kullanciEkle());
        mainPanel.add("3", kullanciCikar());
        this.setLayout(new BorderLayout());
        this.add(panel1(), BorderLayout.CENTER);
        this.add(panel2(), BorderLayout.NORTH);
        this.add(panel3(), BorderLayout.EAST);
        this.add(panel4(), BorderLayout.WEST);
        this.add(panel5(), BorderLayout.SOUTH);

    }


    public JPanel panel1() {
        panel1.setPreferredSize(new Dimension(400, 500));
        panel1.setLayout(new GridLayout(5, 1, 2, 4));
        panel1.setBackground(Color.decode("#DAD2BC"));
        panel1.add("2", buttonMaker(button2));
        panel1.add("3", buttonMaker(button));
        panel1.add("5", buttonMaker(button4));

        return panel1;
    }

    public JPanel panel2() {
        panel2.setPreferredSize(new Dimension(799, 100));
        panel2.setLayout(new FlowLayout(FlowLayout.LEADING, 2, 4));
        logout.setPreferredSize(new Dimension(70, 70));
        logout.setBackground(Color.decode("#DAD2BC"));
        logout.setFont(new Font("Courier", Font.BOLD, 60));
        panel2.add(logout, BorderLayout.WEST);

        panel2.setBackground(Color.decode("#DAD2BC"));

        return panel2;
    }

    public JPanel panel3() {
        panel3.setPreferredSize(new Dimension(200, 800));
        panel3.setLayout(new BorderLayout());
        panel3.setBackground(Color.decode("#F5F1ED"));

        return panel3;
    }

    public JPanel panel4() {
        panel4.setPreferredSize(new Dimension(200, 800));
        panel4.setLayout(new BorderLayout());

        panel4.setBackground(Color.decode("#F5F1ED"));
        return panel4;
    }

    public JPanel panel5() {
        panel5.setPreferredSize(new Dimension(799, 100));
        panel5.setLayout(new BorderLayout());
        panel5.setBackground(Color.decode("#DAD2BC"));

        return panel5;
    }

    public JButton buttonMaker(JButton button) {

        button.setBackground(Color.decode("#A99985"));
        button.setFont(new Font("Courier", Font.BOLD, 30));
        button.setPreferredSize(new Dimension(180, 84));
        return button;
    }

    public JLabel label2(JLabel label2) {
        label2.setFont(new Font("Courier", Font.BOLD, 20));
        label2.setForeground(Color.decode("#000000"));

        return label2;
    }

    public JPanel panel6() {
        JPanel panel = new JPanel();
        panel6.setPreferredSize(new Dimension(800, 800));
        panel6.setLayout(new BorderLayout());
        panel6.setBackground(Color.decode("#F5F1ED"));
        Back.setFont(new Font("Courier", Font.BOLD, 88));
        Back.setForeground(Color.decode("#000000"));
        Back.setMinimumSize(new Dimension(200, 80));
        Back.setBackground(Color.decode("#A99985"));
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        panel.add(Back);
        panel.add(buttonMaker(button5));
        panel.add(buttonMaker(button6));
        panel.add(buttonMaker(button7));
        panel6.add(panel, BorderLayout.NORTH);
        panel6.add(mainPanel, BorderLayout.CENTER);
        cl.show(mainPanel, "1");


        return panel6;
    }

    public JPanel menuEkle() {
        JPanel anapanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 45, 40));
        anapanel.setBackground(Color.decode("#DAD2BC"));
        JLabel label = new JLabel("Eklemek Istedigin menu'nun tipi seciniz:");
        menu.setFont(new Font("Courier", Font.BOLD, 20));
        menu.setForeground(Color.decode("#000000"));
        nameField4 = new JTextField();
        nameField3 = new JTextField();
        priceField = new JTextField();
        anapanel.add(label2(label));
        anapanel.add(menu);
        anapanel.add(label2(new JLabel("Name:")));
        anapanel.add(nameField4);
        anapanel.add(label2(new JLabel("Price:")));
        nameField4.setForeground(Color.decode("#000000"));
        nameField4.setPreferredSize(new Dimension(100, 20));
        nameField3.setForeground(Color.decode("#000000"));
        nameField3.setPreferredSize(new Dimension(400, 20));
        priceField.setForeground(Color.decode("#000000"));
        priceField.setPreferredSize(new Dimension(100, 20));
        anapanel.add(priceField);
        anapanel.add(label2(new JLabel("Foto adi(Uzantisiz): ")));
        anapanel.add(nameField3);
        anapanel.add(buttonMaker(ekleF));
        anapanel.add(buttonMaker(foto));
        JTextArea tA= new JTextArea("Yeni bir menu eklemek icin:\n 1.Menunun adini yaziniz.\n 2.Menunun fiyatini giriniz.\n 3.Foto eklemek icin 'Photo' butonunu tiklayip bir resimi seciniz.\n 4.Fotonun adi boyle yaziniz:\n -Food ise: menu\n -Drink ise: drink\n -Dessert ise: dessert.\n 5.Fotosu olmayan Menuleri systemde GOZUKMEZ, foto eklemeye ozen veriniz.");
        anapanel.add(tA);
        tA.setBackground(Color.decode("#DAD2BC"));
        tA.setLineWrap(true);
        tA.setPreferredSize(new Dimension(700, 300));
        tA.setFont(new Font("Courier", Font.BOLD, 15));

        return anapanel;


    }

    public JPanel kullanciEkle() {
        JPanel anapanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 20, 40));
        anapanel.setBackground(Color.decode("#DAD2BC"));
        JLabel label1 = new JLabel("Ad:");
        nameField = new JTextField();
        nameField.setFont(new Font("Courier", Font.BOLD, 20));
        nameField.setForeground(Color.decode("#000000"));
        nameField.setPreferredSize(new Dimension(300, 20));
        anapanel.add(label2(label1));
        anapanel.add(nameField);
        anapanel.add(buttonMaker(ekleK));
        JTextArea tA= new JTextArea("-Not: Eklemek Istedigin kullancinin adini giriniz ^ ");
        anapanel.add(tA);
        tA.setBackground(Color.decode("#DAD2BC"));
        tA.setFont(new Font("Courier", Font.BOLD, 20));
        return anapanel;

    }


    public JPanel kullanciCikar() {
        JPanel anapanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 20, 40));
        anapanel.setBackground(Color.decode("#DAD2BC"));
        JLabel label1 = new JLabel("Ad:");
        nameField2 = new JTextField();
        nameField2.setFont(new Font("Courier", Font.BOLD, 20));
        nameField2.setForeground(Color.decode("#000000"));
        nameField2.setPreferredSize(new Dimension(300, 20));
        anapanel.add(label2(label1));
        anapanel.add(nameField2);
        anapanel.add(buttonMaker(cikarK));
        JTextArea tA= new JTextArea("-Not: Cikarmak Istedigin kullancinin adini giriniz ^ ");
        anapanel.add(tA);
        tA.setBackground(Color.decode("#DAD2BC"));
        tA.setFont(new Font("Courier", Font.BOLD, 20));

        return anapanel;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button5) {
            cl.show(mainPanel, "1");
        }

        if (e.getSource() == button6) {
            cl.show(mainPanel, "2");
        }
        if (e.getSource() == button7) {
            cl.show(mainPanel, "3");
        }
    }
}



