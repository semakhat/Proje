import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Dessert extends Menu {
    public JPanel Panel;
    public BufferedImage[] image;
    public File path = new File("src\\resources\\Dessert");
    public File[] allFiles = path.listFiles();
    public int sayac;


    @Override
    public JPanel display() throws SQLException {
        Connection connection = null;
        Dbhelper helper = new Dbhelper();
        Statement statement;
        ResultSet resultSet;
        buttonArray = new ArrayList<JButton>();
        nameArray = new ArrayList<>();
        idArray = new ArrayList<Integer>();
        priceArray = new ArrayList<Double>();
        Panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        Panel.setPreferredSize(new Dimension(525, 2000));
        Panel.setBackground(Color.decode("#A99985"));
        image = new BufferedImage[allFiles.length];
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setEnabled(true);
        scrollPane.setViewportView(Panel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JPanel Mainp = new JPanel(new BorderLayout());
        Mainp.add(scrollPane, BorderLayout.CENTER);
        Mainp.setPreferredSize(new Dimension(525, 800));
        try {
            connection = helper.getConnection();
            System.out.println("baglanti olustu");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from dessert");
            for (sayac = 0; sayac < allFiles.length; sayac++) {
                resultSet.next();
                nameArray.add(sayac, resultSet.getString(2));
                idArray.add(sayac, resultSet.getInt(1));
                priceArray.add(sayac, resultSet.getDouble(3));
                JLabel sqlLabel = new JLabel(idArray.get(sayac) + " - " + nameArray.get(sayac) + " - " + priceArray.get(sayac));
                sqlLabel.setFont(new Font("Courier", Font.BOLD, 13));
                buttonArray.add(sayac, new JButton("+"));
                buttonArray.get(sayac).setFont(new Font("Courier", Font.BOLD, 50));
                buttonArray.get(sayac).setMinimumSize(new Dimension(30, 10));
                buttonArray.get(sayac).setBackground(Color.decode("#F5F1ED"));
                buttonArray.get(sayac).addActionListener(this::actionPerformed);
                sqlLabel.setBorder(new LineBorder(Color.decode("#000000")));
                sqlLabel.setForeground(Color.decode("#000000"));
                JPanel panel = new JPanel();
                GroupLayout layout = new GroupLayout(panel);
                //spinner.setMinimumSize(new Dimension(20,20));
                image[sayac] = ImageIO.read(allFiles[sayac]);
                ImageIcon icon = new ImageIcon(image[sayac]);
                JLabel iconL = new JLabel();
                iconL.setIcon(icon);
                panel.setBackground(Color.decode("#F5F1ED"));
                panel.setMinimumSize(new Dimension(200, 200));
                layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, true).addComponent(iconL).addComponent(sqlLabel).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)).addComponent(buttonArray.get(sayac)))));
                layout.setVerticalGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(iconL)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(sqlLabel)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(buttonArray.get(sayac))));
                // layout.linkSize(SwingConstants.HORIZONTAL, spinner, adet);
                layout.setAutoCreateGaps(true);
                layout.setAutoCreateContainerGaps(true);
                panel.setLayout(layout);
                Panel.add(panel);


            }


        } catch (SQLException | IOException e) {
            helper.showErrorMessage((SQLException) e);
            System.out.println("error");
            e.printStackTrace();

        } finally {
            connection.close();
        }


        return Mainp;
    }


    private void actionPerformed(ActionEvent e) {
        for (int i = 0; i < sayac; i++) {

            if (e.getSource() == buttonArray.get(i)) {
                menuArray = new ArrayList<>();
                menuArray.add(String.valueOf(idArray.get(i)));
                menuArray.add(nameArray.get(i));
                menuArray.add(String.valueOf(priceArray.get(i)));
                // System.out.println(menuArray);
                if (menuArray != null) {
                    MenuP.orderPrices.add((priceArray.get(i)));
                    System.out.println(MenuP.orderPrices);
                    orderArray.add(menuArray);
                    MenuP.listModel.addElement(menuArray);
                    toplam = 0;
                    MenuP.KDV = 0;
                    for (int q = 0; q < MenuP.orderPrices.size(); q++) {

                        toplam += MenuP.orderPrices.get(q);
                        MenuP.KDV = (float) (toplam * 0.02);
                        toplam += MenuP.KDV;
                    }
                    System.out.println(toplam);
                    MenuP.priclab.setText(String.valueOf(MenuP.df.format(toplam)));
                    MenuP.KDVL.setText(String.valueOf(MenuP.df.format(MenuP.KDV)));

                }
            }


        }


    }


}


