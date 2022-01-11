import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Menu {
    public static float toplam;
    public ArrayList<JButton> buttonArray;
    public ArrayList<String> nameArray;
    public ArrayList<Integer> idArray;
    public ArrayList<Double> priceArray;
    public ArrayList<String> menuArray;
    public static ArrayList<ArrayList<String>> orderArray = new ArrayList<>();




    public abstract Component display() throws SQLException;


}