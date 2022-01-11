import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Frame extends JFrame implements ActionListener {
    public Save save = new Save();
    public Login login = new Login();
    public MenuP menu = new MenuP();
    public Logs logs = new Logs();
    public CheckOut chekOut = new CheckOut();
    public Mpage mpage = new Mpage();
    public File logsFile = new File("src\\resources\\Logs.txt");
    public CardLayout cardLayout = new CardLayout();
    public JPanel mainPanel = new JPanel(cardLayout);
    public int sayac = 9;

    public Frame() throws SQLException, IOException, ClassNotFoundException {
        mainPanel.add("1", login);
        mainPanel.add("2", mpage);
        mainPanel.add("2.1", mpage.panel6());
        mainPanel.add("3", menu);
        mainPanel.add("4", logs);
        mainPanel.add("5", chekOut);

        login.usernames.add("hagar");
        login.usernames.add("semahat");
        login.usernames.add("hoca");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("filename.txt"));
        login.usernames = (ArrayList<String>) ois.readObject();
        ois.close();

        login.button.addActionListener(this);
        mpage.button.addActionListener(this);
        mpage.logout.addActionListener(this);
        mpage.ekleF.addActionListener(this);
        mpage.ekleK.addActionListener(this);
        mpage.cikarK.addActionListener(this);
        mpage.button2.addActionListener(this);
        mpage.button4.addActionListener(this);
        mpage.menu.addActionListener(this);
        mpage.foto.addActionListener(this);
        mpage.Back.addActionListener(this);
        menu.checkOut.addActionListener(this);
        menu.back.addActionListener(this);
        logs.Back.addActionListener(this);
        logs.Show.addActionListener(this);
        chekOut.Back.addActionListener(this);
        chekOut.Done.addActionListener(this);
        cardLayout.show(mainPanel, "1");
        ImageIcon img = new ImageIcon("src\\resources\\person.png");
        this.setPreferredSize(new Dimension(800, 800));
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Sonic.");
        this.setIconImage(img.getImage());
        this.add(mainPanel);
        this.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mpage.logout) {
            save.logoutw(logsFile, login.userName, login.getDate(), Login.cTime.getLabel());
            cardLayout.show(mainPanel, "1");
            login.name.setText(null);
            login.userName = "";
            login.password = null;
            login.pass.setText(null);
            login.revalidate();

            try {

                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("filename.txt"));
                oos.writeObject(login.usernames);
                oos.flush();
                oos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }


        }


        if (e.getSource() == login.button) {
            try {
                login.check();

                if (login.correctName && login.correctPass) {
                    cardLayout.show(mainPanel, "2");
                    save.loginw(logsFile, login.userName, login.getDate(), Login.cTime.getLabel());

                } else {
                    JOptionPane.showMessageDialog(null, "You've entered a wrong Username or Password");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }


        }
        if (e.getSource() == mpage.button) {
            cardLayout.show(mainPanel, "3");


        }
        if (e.getSource() == menu.back) {
            cardLayout.show(mainPanel, "2");


        }
        if (e.getSource() == mpage.button2) {
            cardLayout.show(mainPanel, "4");


        }
        if (e.getSource() == mpage.button4) {
            cardLayout.show(mainPanel, "2.1");


        }
        if (e.getSource() == mpage.Back) {
            cardLayout.show(mainPanel, "2");


        }
        if (e.getSource() == logs.Back) {
            cardLayout.show(mainPanel, "2");


        }
        if (e.getSource() == logs.Show) {
            save.read(logsFile, logs.TextLog);


        }
        if (e.getSource() == mpage.ekleK) {
            if (mpage.nameField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username can not be left blank.");
            } else {

                login.usernames.add(mpage.nameField.getText());
                System.out.println(login.usernames);
            }

        }

        if (e.getSource() == mpage.cikarK) {

            if (mpage.nameField2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username can not be left blank.");

            } else {
                login.usernames.remove(mpage.nameField2.getText());
                System.out.println(login.usernames);
            }

        }
        if (e.getSource() == mpage.ekleF) {
            if (mpage.priceField != null && mpage.nameField2 != null) {
                try {
                    if (mpage.menu.getSelectedItem() == "Food") {
                        System.out.println(mpage.nameField4.getText());
                        insertDemo("insert into food (name, price) values(?,?);", mpage.nameField4.getText(), Double.parseDouble(mpage.priceField.getText()));
                        JOptionPane.showMessageDialog(null, "Success.");
                        menu.revalidate();
                    } else if (mpage.menu.getSelectedItem() == "Drink") {
                        insertDemo("insert into drink (name, price) values(?,?);", mpage.nameField4.getText(), Double.parseDouble(mpage.priceField.getText()));
                        JOptionPane.showMessageDialog(null, "Success.");
                        menu.revalidate();
                    } else if (mpage.menu.getSelectedItem() == "Meal") {
                        insertDemo("insert into dessert (name, price) values(?,?);", mpage.nameField4.getText(), Double.parseDouble(mpage.priceField.getText()));
                        JOptionPane.showMessageDialog(null, "Success.");
                        menu.revalidate();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            } else if (mpage.nameField2.getText() == "" || mpage.priceField.getText() == "") {
                JOptionPane.showMessageDialog(null, "Menu name/price can't be left blank.");

            }
        }

        if (e.getSource() == mpage.foto) {
            JFileChooser chooser = new JFileChooser();
            int status = chooser.showOpenDialog(null);
            if (status == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                String fileName = chooser.getSelectedFile().getPath();
                if (file == null) {
                    return;
                }
                if (mpage.menu.getSelectedItem().equals("Drink")) {
                    try {
                        Path source = Paths.get(fileName);
                        String fname = mpage.nameField3.getText();
                        if (fname != null) {
                            Path dest = Paths.get("src\\resources\\Drinks\\" + fname + sayac + ".png");
                            Files.move(source, dest);
                            JOptionPane.showMessageDialog(null, "Success.");
                            ++sayac;
                            menu.revalidate();
                        } else {
                            JOptionPane.showMessageDialog(null, "Name the picture.");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }


                } else if (mpage.menu.getSelectedItem().equals("Food")) {
                    try {
                        Path source = Paths.get(fileName);
                        String fname = mpage.nameField3.getText();
                        if (fname != "") {
                            Path dest = Paths.get("src\\resources\\Food\\" + fname + sayac + ".png");
                            Files.move(source, dest);
                            JOptionPane.showMessageDialog(null, "Success.");
                            ++sayac;
                            menu.revalidate();
                        } else {
                            JOptionPane.showMessageDialog(null, " Name the picture.");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }


                } else if (mpage.menu.getSelectedItem().equals("Dessert")) {
                    try {
                        Path source = Paths.get(fileName);
                        String fname = mpage.nameField3.getText();
                        if (fname != null) {
                            Path dest = Paths.get("src\\resources\\Dessert\\" + fname + sayac + ".png");
                            Files.move(source, dest);
                            JOptionPane.showMessageDialog(null, "Success.");
                            ++sayac;
                            menu.revalidate();
                        } else {
                            JOptionPane.showMessageDialog(null, "Name the picture.");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }


                }

            }

        }
        if (e.getSource() == menu.checkOut) {
            if (Menu.toplam > 0) {
                cardLayout.show(mainPanel, "5");
                chekOut.toplam.setText(String.valueOf(MenuP.df.format(Menu.toplam)));
            } else
                JOptionPane.showMessageDialog(null, "Please make a choice.");
        }
        if (e.getSource() == chekOut.Back) {
            cardLayout.show(mainPanel, "3");
            chekOut.paraUstu.setText("");
            chekOut.fisText.setText("");
            chekOut.verilenPara.setText("");
            chekOut.toplam.setText("");
            chekOut.fis1.delete();
        }
        if (e.getSource() == chekOut.Done) {
            cardLayout.show(mainPanel, "2");
            chekOut.paraUstu.setText("");
            chekOut.fisText.setText("");
            chekOut.verilenPara.setText("");
            chekOut.toplam.setText("");
            MenuP.listModel.clear();
            Menu.toplam = 0;
            MenuP.KDVL.setText("");
            MenuP.KDV = 0;
            MenuP.priclab.setText("");
            MenuP.orderPrices.clear();

        }
    }


    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            save.logoutw(logsFile, login.userName, login.getDate(), login.cTime.getLabel());
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("filename.txt"));
                oos.writeObject(login.usernames);
                oos.flush();
                oos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }


            System.exit(0);
        }
    }


    public static void insertDemo(String sql, String name, double price) throws SQLException {
        Connection connection = null;
        Dbhelper helper = new Dbhelper();
        PreparedStatement statement = null;


        try {
            connection = helper.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.executeUpdate();


        } catch (SQLException e) {
            helper.showErrorMessage(e);
        } finally {
            statement.close();
            connection.close();
        }

    }

}


