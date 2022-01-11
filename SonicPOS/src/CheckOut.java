import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CheckOut extends JPanel implements ActionListener {

    public Save save = new Save();
    public File fis = new File("src\\resources\\Fis");
    public File fis1;
    public JLabel toplam;
    public JTextField verilenPara;
    public JButton ok;
    public JButton Done;
    public JTextArea paraUstu = new JTextArea("");
    public JTextArea fisText = new JTextArea("");
    public JButton Back = new JButton("Back");
    public JButton Show = new JButton("Receipt");


    public CheckOut() {
        setBackground(Color.decode("#A99985"));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(paymentPanel());
        add(fis());
        ok.addActionListener(this::actionPerformed);
        Show.addActionListener(this::actionPerformed);


    }

    public JPanel fis() {
        JPanel fis1 = new JPanel(new GridLayout(1, 1));
        fis1.setPreferredSize(new Dimension(775, 500));
        fisText.setBackground(Color.decode("#F5F1ED"));
        fisText.setFont(new Font("Courier", Font.BOLD, 15));
        fisText.setEditable(false);
        fisText.setLineWrap(true);
        JScrollPane sP = new JScrollPane(fisText);
        fis1.add(sP);


        return fis1;
    }

    public JPanel paymentPanel() {
        JPanel pp = new JPanel(new FlowLayout(FlowLayout.LEADING, 16, 2));
        pp.setPreferredSize(new Dimension(775, 190));
        pp.setBackground(Color.decode("#F5F1ED"));
        pp.add(labelMaker(new JLabel("Total Owed:")));
        toplam = new JLabel();
        pp.add(labelMaker(toplam));
        pp.add(labelMaker(new JLabel("Deposit:")));
        verilenPara = new JTextField();
        verilenPara.setPreferredSize(new Dimension(100, 40));
        pp.add(verilenPara);
        pp.add(new JLabel("Money Change: "));
        paraUstu.setPreferredSize(new Dimension(700, 50));
        paraUstu.setBackground(Color.decode("#F5F1ED"));
        paraUstu.setFont(new Font("Courier", Font.BOLD, 15));
        paraUstu.setForeground(Color.decode("#70798c"));
        paraUstu.setEditable(false);
        paraUstu.setLineWrap(true);
        pp.add(paraUstu);
        Done = new JButton("Finish");
        ok = new JButton("Change");
        pp.add(buttonMaker(Back));
        pp.add(buttonMaker(ok));
        pp.add(buttonMaker(Show));
        pp.add(buttonMaker(Done));


        return pp;
    }

    public JButton buttonMaker(JButton button) {

        button.setBackground(Color.decode("#A99985"));
        button.setFont(new Font("Courier", Font.BOLD, 15));
        button.setPreferredSize(new Dimension(100, 50));


        return button;
    }

    public JLabel labelMaker(JLabel x) {

        x.setFont(new Font("Courier", Font.BOLD, 12));
        x.setPreferredSize(new Dimension(90, 50));
        return x;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            paraUstu.setText("");
            double x = Double.parseDouble(verilenPara.getText());
            double t = Menu.toplam;
            if (t > x) {
                JOptionPane.showMessageDialog(null, "Insufficient funds.");

            } else {

                int k5, k10, k25, k50, l1, l5, l10, l20, l50, l100, l200;
                double para = x - t;
                l200 = (int) (para / 200);
                para %= 200;
                l100 = (int) (para / 100);
                para %= 100;
                l50 = (int) (para / 50);
                para %= 50;
                l20 = (int) (para / 20);
                para %= 20;
                l10 = (int) (para / 10);
                para %= 10;
                l5 = (int) (para / 5);
                para %= 5;
                l1 = (int) (para);
                para %= 1;

                k50 = (int) (para / 0.50);
                para %= 0.50;
                k25 = (int) (para / 0.25);
                para %= 0.25;
                k10 = (int) (para / 0.10);
                para %= 0.10;
                k5 = (int) (para / 0.05);
                para %= 0.05;

                if (l200 > 0) {
                    System.out.print(l200 + "x 200 TL");
                    paraUstu.setText(l200 +"x 200 TL");
                }
                if (l100 > 0) {
                    if (paraUstu.getText().isEmpty()) {
                        paraUstu.setText(l100 + "x 100 TL");
                    } else
                        paraUstu.setText(paraUstu.getText() + ", " + l100 + "x 100 TL");
                }
                if (l50 > 0) {
                    if (paraUstu.getText().isEmpty()) {
                        paraUstu.setText(l50 + "x 50 TL");
                    } else
                        paraUstu.setText(paraUstu.getText() + ", " + l50 + "x 50 TL");
                }
                if (l20 > 0) {
                    if (paraUstu.getText().isEmpty()) {
                        paraUstu.setText(l20 + "x 20 TL");
                    } else
                        paraUstu.setText(paraUstu.getText() + ", " + l20 + "x 20 TL");
                }
                if (l10 > 0) {
                    if (paraUstu.getText().isEmpty()) {
                        paraUstu.setText(l10 + "x 10 TL");
                    } else
                        paraUstu.setText(paraUstu.getText() + ", " + l10 + "x 10 TL");
                }
                if (l5 > 0) {
                    if (paraUstu.getText().isEmpty()) {
                        paraUstu.setText(l5 + "x 5 TL");
                    } else
                        paraUstu.setText(paraUstu.getText() + ", " + l5 + " x 5 TL");
                }
                if (l1 > 0) {
                    if (paraUstu.getText().isEmpty()) {
                        paraUstu.setText(l1 + "x 1 TL");
                    } else
                        paraUstu.setText(paraUstu.getText() + ", " + l1 + "x 1 TL ");
                }

                if (k50 > 0) {
                    if (paraUstu.getText().isEmpty()) {
                        paraUstu.setText(k50 + "x 50 KURUS");
                    } else
                        paraUstu.setText(paraUstu.getText() + ", " + k50 + "x 50 KURUS, ");
                }
                if (k25 > 0) {
                    if (paraUstu.getText().isEmpty()) {
                        paraUstu.setText(k25 + "x 20 KURUS");
                    } else
                        paraUstu.setText(paraUstu.getText() + ", " + k25 + "x 25 KURUS ");
                }
                if (k10 > 0) {
                    if (paraUstu.getText().isEmpty()) {
                        paraUstu.setText(k10 + "x 10 KURUS");
                    } else
                        paraUstu.setText(paraUstu.getText() + ", " + k10 + "x 10 KURUS ");
                }
                if (k5 > 0) {
                    if (paraUstu.getText().isEmpty()) {
                        paraUstu.setText(k5 + "x 5 KURUS");
                    } else
                        paraUstu.setText(paraUstu.getText() + ", " + k5 + "x 5 KURUS ");
                }
                save.fis(Login.getDate(), toplam.getText(), verilenPara.getText(), fis, Menu.orderArray, Login.cTime.getLabel());
                try {
                    fis1 = File.createTempFile("fii", ".txt", new File("src\\resources\\"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                save.fis(Login.getDate(), toplam.getText(), verilenPara.getText(), fis1, Menu.orderArray, Login.cTime.getLabel());


            }
        }
        if (e.getSource() == Show) {
            save.read(fis1, fisText);

        }
    }
}
