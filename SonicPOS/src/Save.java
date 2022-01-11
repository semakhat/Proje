import javax.swing.*;
import java.io.*;
import java.util.ArrayList;


public class Save {

    public void fis(String date, String toplam, String verilenPara, File fileName, ArrayList<ArrayList<String>> list, JLabel x) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("\n-------------------------\n");
            bw.write("\n-------------------------\n");
            bw.write("Id      Name     Price");
            bw.write("\n-------------------------\n");
            for (int i = 0; i < list.size(); i++) {
                bw.write(String.valueOf(list.get(i)));
                bw.newLine();
            }
            bw.write("-------------------------\n");
            bw.write("Date: " + date + " " + x.getText());
            bw.newLine();
            bw.write("Total: " + toplam + " TL ");
            bw.newLine();
            bw.write("Payment: " + verilenPara + " TL ");
            bw.newLine();
            bw.write("-------------------------");
            bw.newLine();
            bw.write("-------------------------");
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }


    }


    public void loginw(File filename, String name, String date, JLabel x) {
        try {
            FileWriter fw = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("- User " + name + " has logged in on " + date + " " + x.getText());
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }


    public void logoutw(File filename, String name, String date, JLabel x) {
        try {
            FileWriter fw = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);

            if (name == null || name.isEmpty()) {
                bw.write("- Session terminated on  " + date + " " + x.getText());
            } else {
                bw.write("- User " + name + " has logged out on " + date + " " + x.getText());

            }
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }

    }

    public void read(File filename, JTextArea x) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                if (!currentLine.startsWith(">")) {
                    x.read(br, filename); //Object of JTextArea;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
