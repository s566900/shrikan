import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class PasswordG {
        public static void main(String[] args) {
            JFrame frame=new JFrame("facebook");
            frame.setSize(600,800);
            frame.setLayout(null);
            frame.getContentPane().setBackground(Color.lightGray);
            JLabel name = new JLabel("name  :");
            name.setBounds(100,70,150,30);
            JTextField jt=new JTextField(15);
            jt.setBounds(250,70,250,30);
            JLabel Email = new JLabel("Email  :");
            Email.setBounds(100,100,150,30);
            JLabel Password = new JLabel("password :");
            Password.setBounds(100,130,150,30);
            JTextField jpe = new JTextField(15);
            jpe.setBounds(250,100,250,30);
            JPasswordField jp=new JPasswordField();
            jp.setBounds(250,130,250,30);
            JButton Lb = new JButton("Login");
            Lb.setBounds(100,170,200,30);
            JButton Ln = new JButton("new user");
            Ln.setBounds(300,170,200,30);
            JLabel oute = new JLabel();
            oute.setBounds(10,210,350,30);
            frame.add(Email);frame.add(Password);frame.add(jpe);frame.add(jp);frame.add(Lb);frame.add(Ln);frame.add(name);frame.add(jt);
            frame.add(oute);
            Email.setVisible(false);
            Password.setVisible(false);
            name.setVisible(false);
            jp.setVisible(false);
            jpe.setVisible(false);
            jt.setVisible(false);

            ActionListener click=new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Email.setVisible(true);
                    Password.setVisible(true);
                    name.setVisible(true);
                    jp.setVisible(true);
                    jpe.setVisible(true);
                    jt.setVisible(true);
                    if(jp.getText().length()!=0 && jpe.getText().length() !=0 && jt.getText().length()!=0){
                    try {
                        File obj = new File("new2.txt");
                        obj.createNewFile();
                        Scanner read = new Scanner(obj);
                        int i = 0;
                        String nn = jt.getText();
                        String ee = jpe.getText();
                        String pp = jp.getText();
                        String bio = nn + "' " + ee+"$"+pp;
                        while (read.hasNextLine()) {
                            String data = read.nextLine();
                            if (bio.equals(data)) {
                                i++;
                            }
                        }
                        if (i >= 1) {
                            oute.setText("log in sucessfully");
                            jp.setText("");
                            jt.setText("");
                            jpe.setText("");
                        } else {
                            oute.setText("you are not registered pl. register ");
                            jp.setText("");
                            jt.setText("");
                            jpe.setText("");
                        }  } catch (Exception f) {
                        System.out.println(f);}
                };}};
            ActionListener usern=new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Email.setVisible(true);
                    Password.setVisible(false);
                    name.setVisible(true);
                    jp.setVisible(false);
                    jpe.setVisible(true);
                    jt.setVisible(true);
                    if ( jpe.getText().length() != 0 && jt.getText().length() != 0) {
                        try {
                            File obj = new File("new2.txt");
                            obj.createNewFile();
                            Scanner read = new Scanner(obj);
                            int i = 0;
                            String nn = jt.getText();
                            String ee = jpe.getText();
                            String pp = String.valueOf((int) (Math.random() * 1000000));
                            System.out.println(pp);
                            String bio = nn + "' " + ee + "$";
                            while (read.hasNextLine()) {
                                String data = read.nextLine();
                                if (data.contains(bio)) {
                                    i++;
                                }
                            }
                            if (i >= 1) {
                                oute.setText("all ready registered  ");
                                jp.setText("");
                                jt.setText("");
                                jpe.setText("");
                            } else {
                                FileWriter fw = new FileWriter("new2.txt", true);
                                fw.write(nn + "' " + ee + "$" + pp + "\n");
                                fw.close();
                                oute.setText("register sucessfully  password is " + pp);
                                jp.setText("");
                                jt.setText("");
                                jpe.setText("");
                            }


                        } catch (Exception f) {
                            System.out.println(f);
                        }
                    }
                    ;

                }

            };
            Lb.addActionListener(click);

            Ln.addActionListener(usern);

            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        };
    }
