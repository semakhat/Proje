import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MenuP extends JPanel implements ActionListener {

    public Drink drink = new Drink();
    public Food food = new Food();
    public Dessert dessert = new Dessert();
    public static float KDV;
    public static final DecimalFormat df = new DecimalFormat("0.00");

    public ClassLoader tl = this.getClass().getClassLoader();
    public URL imageURL = tl.getResource("resources/menuteklo.png");
    public URL imageURL1 = tl.getResource("resources/drinksLO.png");
    public URL imageURL2 = tl.getResource("resources/Dessertl.png");
    public URL imageURL3 = tl.getResource("resources/x.png");
    public ImageIcon icon = new ImageIcon(imageURL);
    public ImageIcon icon1 = new ImageIcon(imageURL1);
    public ImageIcon icon2 = new ImageIcon(imageURL2);
    public ImageIcon icon3 = new ImageIcon(imageURL3);
    public JButton tekbtn = new JButton(icon);
    public JButton drinksbtn = new JButton(icon1);
    public JButton mealsbtn = new JButton(icon2);
    public JButton back = new JButton(icon3);
    public JPanel menu = new JPanel();
    public JPanel sepet = new JPanel();
    public JPanel panel = new JPanel();
    public static JLabel priclab = new JLabel();
    public static JLabel KDVL = new JLabel();
    public static DefaultListModel listModel;
    public static ArrayList<Double> orderPrices;
    public JList shoppingCL;
    public JButton checkOut = new JButton("Check out");
    public JButton clear = new JButton("Clear");
    public JButton delete = new JButton("Delete");
    public CardLayout cl = new CardLayout();
    public JPanel mainPanel = new JPanel(cl);

    public MenuP() throws SQLException {
        setPreferredSize(new Dimension(800, 800));
        setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));
        add(menu());
        mainPanel.add("1", food.display());
        mainPanel.add("2", drink.display());
        mainPanel.add("3", dessert.display());
        cl.show(mainPanel, "1");
        tekbtn.addActionListener(this);
        drinksbtn.addActionListener(this);
        mealsbtn.addActionListener(this);
        clear.addActionListener(this);
        delete.addActionListener(this);
        add(mainPanel);
        add(sepet());
        this.add(panel);
    }

    private JPanel sepet() {
        sepet.setPreferredSize(new Dimension(155, 799));
        sepet.setLayout(new FlowLayout(FlowLayout.LEADING, 1, 4));
        sepet.setBorder(new LineBorder(Color.black));
        JLabel label = new JLabel("SEPET");
        label.setFont(new Font("Courier New", Font.BOLD, 25));
        sepet.add(label, BorderLayout.NORTH);
        sepet.add(buildShoppingCartPanel(), BorderLayout.CENTER);
        sepet.setBackground(Color.decode("#F5F1ED"));
        sepet.add(billingPanel(), BorderLayout.SOUTH);


        return sepet;
    }

    private JPanel menu() {
        menu.setPreferredSize(new Dimension(100, 800));
        menu.setLayout(new FlowLayout(FlowLayout.LEADING, 2, 10));
        menu.setBackground(Color.decode("#DAD2BC"));
        menu.add(btnMaker(tekbtn));
        menu.add(btnMaker(drinksbtn));
        menu.add(btnMaker(mealsbtn));
        menu.add(btnMaker(back));

        return menu;
    }

// i should make an array of names,ids,prices so i can access them with actionlistener


    public JButton btnMaker(JButton x) {
        x.setPreferredSize(new Dimension(100, 100));
        x.setBackground(Color.decode("#DAD2BC"));
        return x;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tekbtn) {
            cl.show(mainPanel, "1");
        }
        if (e.getSource() == drinksbtn) {
            cl.show(mainPanel, "2");
        }
        if (e.getSource() == mealsbtn) {
            cl.show(mainPanel, "3");

        }

        int x = shoppingCL.getSelectedIndex();
        if (e.getSource() == delete) {
            if (x != -1) {
                listModel.removeElementAt(x);
                orderPrices.remove(x);
                Menu.toplam = 0;
                MenuP.KDV = 0;
                for (int q = 0; q < MenuP.orderPrices.size(); q++) {
                    Menu.toplam += MenuP.orderPrices.get(q);
                    KDV = (float) (Menu.toplam * 0.02);
                    Menu.toplam += MenuP.KDV;
                }
                System.out.println(Menu.toplam);
                MenuP.priclab.setText(String.valueOf(MenuP.df.format(Menu.toplam)));
                MenuP.KDVL.setText(String.valueOf(MenuP.df.format(MenuP.KDV)));
            }
        }

        if (e.getSource() == clear) {
            listModel.clear();
            orderPrices.clear();
            Menu.toplam = 0;
            MenuP.KDV = 0;
            for (int q = 0; q < MenuP.orderPrices.size(); q++) {
                Menu.toplam += MenuP.orderPrices.get(q);
                MenuP.KDV = (float) (Menu.toplam * 0.02);
                Menu.toplam += MenuP.KDV;
            }
            System.out.println(Menu.toplam);
            MenuP.priclab.setText(String.valueOf(MenuP.df.format(Menu.toplam)));
            MenuP.KDVL.setText(String.valueOf(MenuP.df.format(MenuP.KDV)));
        }


    }

    private JPanel buildShoppingCartPanel() {
        JPanel x = new JPanel();
        x.setLayout(new GridLayout(1, 0));
        x.setPreferredSize((new Dimension(155, 500)));
        listModel = new DefaultListModel();
        shoppingCL = new JList(listModel);
        shoppingCL.setFont(new Font("Courier New", Font.BOLD, 13));
        shoppingCL.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        shoppingCL.setVisibleRowCount(7);
        JScrollPane shoppingCartScroll = new JScrollPane(shoppingCL);
        x.add(shoppingCartScroll);
        x.setBackground(Color.decode("#F5F1ED"));


        return x;
    }

    public JPanel billingPanel() {
        JPanel anaPaneli = new JPanel();
        anaPaneli.setPreferredSize(new Dimension(155, 200));
        orderPrices = new ArrayList<>();
        JLabel price = new JLabel("Toplam: ");
        JLabel kdv = new JLabel("KDV (2%) :");
        price.setFont(new Font("Courier New", Font.BOLD, 15));
        priclab.setFont(new Font("Courier New", Font.BOLD, 15));
        anaPaneli.add(clear);
        anaPaneli.add(delete);
        anaPaneli.add(price);
        anaPaneli.add(priclab);
        clear.setPreferredSize(new Dimension(100, 40));
        clear.setBackground(Color.decode("#DAD2BC"));
        delete.setPreferredSize(new Dimension(100, 40));
        delete.setBackground(Color.decode("#DAD2BC"));
        anaPaneli.setBackground(Color.decode("#F5F1ED"));
        price.setForeground(Color.black);
        kdv.setForeground(Color.black);
        KDVL.setFont(new Font("Courier New", Font.BOLD, 15));
        checkOut.setFont(new Font("Courier New", Font.BOLD, 20));
        checkOut.setPreferredSize(new Dimension(155, 50));
        checkOut.setBackground(Color.decode("#DAD2BC"));
        anaPaneli.add(kdv);
        anaPaneli.add(KDVL);
        anaPaneli.add((checkOut));

        return anaPaneli;

    }


}
