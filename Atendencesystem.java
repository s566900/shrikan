import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Atendencesystem {

    static String[] tblHead = { "S.No", "Email", "name" };
    static DefaultTableModel dtm = new DefaultTableModel(tblHead, 0);
    static DefaultTableModel dtm1 = new DefaultTableModel(tblHead, 0);
    static JTable tbl = new JTable(dtm);
    static JTable tbl1 = new JTable(dtm1);

    static void frame() {
        JFrame jf = new JFrame("attendance-system");
        jf.setSize(600, 600);
        jf.setLayout(null);
        JButton auth = new JButton("AUTHORITY LOGIN");
        auth.setBounds(200, 100, 200, 100);
        jf.add(auth);
        JButton clint = new JButton("CLIENT LOGIN");
        clint.setBounds(200, 210, 200, 100);
        jf.add(clint);
        JButton newuser = new JButton("NEW CLIENT REGISTER");
        newuser.setBounds(200, 320, 200, 100);
        jf.add(newuser);
        auth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authorty();
            }
        });
        clint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clintf();
            }
        });
        newuser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newClintf();
            }
        });
        jf.setVisible(true);
    }

    static void authorty() {
        JFrame jfa = new JFrame("AUTHORITY LOGIN");
        jfa.setSize(600, 600);
        jfa.setLayout(null);
        JLabel email = new JLabel("ENTER YOUR EMAIL");
        email.setBounds(100, 150, 150, 40);
        jfa.add(email);
        JTextField emf = new JTextField();
        emf.setBounds(260, 150, 200, 40);
        jfa.add(emf);
        JLabel password = new JLabel("ENTER YOUR PASWORD");
        password.setBounds(100, 200, 150, 40);
        jfa.add(password);
        JTextField pf = new JTextField();
        pf.setBounds(260, 200, 200, 40);
        jfa.add(pf);
        JButton loga = new JButton("LOGIN");
        loga.setBounds(200, 250, 200, 60);
        jfa.add(loga);
        loga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dheerajdb", "user1",
                            "123456");
                    Statement stmt = con.createStatement();
                    System.out.println("connetion created");

                    String sql = "select email,password from employee where email=" + '"' + "drnitharwal" + '"'
                            + "and password="
                            + '"' + "123456" + '"' + ';';
                    ResultSet ras = stmt.executeQuery(sql);
                    while (ras.next()) {
                        String em = ras.getString("Email");
                        String us = ras.getString("password");
                        if (em.equals(emf.getText()) && us.equals(pf.getText())) {
                            JFrame jf = new JFrame("AUTHORITY");
                            jf.setSize(400, 400);
                            jf.setLayout(null);
                            JButton allpr = new JButton("present employe");
                            allpr.setBounds(100, 50, 200, 40);
                            jf.add(allpr);
                            JButton allab = new JButton("absent employe");
                            allab.setBounds(100, 100, 200, 40);
                            jf.add(allab);
                            JButton leave = new JButton("leave");
                            leave.setBounds(100, 160, 200, 40);
                            jf.add(leave);
                            allpr.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        Class.forName("com.mysql.cj.jdbc.Driver");
                                        Connection con = DriverManager.getConnection(
                                                "jdbc:mysql://localhost:3306/dheerajdb", "user1", "123456");
                                        Statement stmt = con.createStatement();

                                        String qr = "Select a.sn, e.name,e.email from employee e INNER JOIN attanance a on e.email=a.email";
                                        ResultSet rm = stmt.executeQuery(qr);
                                        while (rm.next()) {
                                            int snnumber = rm.getInt("SN");
                                            String name = rm.getString("name");
                                            jf.add(tbl);

                                            String email = rm.getString("Email");
                                            String[] item = { String.valueOf(snnumber), email, name, " " };
                                            dtm.addRow(item);
                                            tbl.setBounds(00, 200, 400, 200);
                                        }

                                    } catch (Exception aa) {
                                        System.out.println(aa);
                                    }
                                }
                            });
                            allab.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        Class.forName("com.mysql.cj.jdbc.Driver");
                                        Connection con = DriverManager.getConnection(
                                                "jdbc:mysql://localhost:3306/dheerajdb", "user1", "123456");
                                        Statement stmt = con.createStatement();

                                        String qr = "Select sn, name,email from employee where email not in(select Email from attanance)";
                                        ResultSet rm = stmt.executeQuery(qr);
                                        while (rm.next()) {
                                            int snnumber = rm.getInt("SN");
                                            String name = rm.getString("name");
                                            jf.add(tbl1);

                                            String email = rm.getString("Email");
                                            String[] item = { String.valueOf(snnumber), email, name, " " };
                                            dtm1.addRow(item);
                                            tbl.setVisible(false);
                                            tbl1.setBounds(00, 200, 400, 200);
                                        }

                                    } catch (Exception aa) {
                                        System.out.println(aa);
                                    }
                                }
                            });
                            jf.setVisible(true);
                        }

                    }

                } catch (Exception ea) {
                    System.out.println(ea);
                }

            }
        });
        jfa.setVisible(true);
    }

    static void clintf() {
        JFrame jfc = new JFrame("CLIENT LOGIN");
        jfc.setSize(600, 600);
        jfc.setLayout(null);
        JLabel cemail = new JLabel("ENTER YOUR EMAIL");
        cemail.setBounds(100, 150, 150, 40);
        jfc.add(cemail);
        JTextField cemf = new JTextField();
        cemf.setBounds(260, 150, 200, 40);
        jfc.add(cemf);
        JLabel cpassword = new JLabel("ENTER YOUR PASWORD");
        cpassword.setBounds(100, 200, 150, 40);
        jfc.add(cpassword);
        JTextField cpf = new JTextField();
        cpf.setBounds(260, 200, 200, 40);
        jfc.add(cpf);
        JButton loga = new JButton("LOGIN");
        loga.setBounds(200, 250, 200, 60);
        jfc.add(loga);
        JLabel jlp = new JLabel();
        jlp.setBounds(150, 330, 300, 30);
        jfc.add(jlp);
        loga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dheerajdb", "user1",
                            "123456");
                    Statement stmt = con.createStatement();

                    String sql = "select email,password from employee";
                    System.out.println(sql);
                    ResultSet rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        String emaild = rs.getString("Email");
                        String userd = rs.getString("password");
                        System.out.println(emaild);
                        System.out.println(userd);
                        if (emaild.equals(cemf.getText()) && userd.equals(cpf.getText())) {

                            String att = "INSERT into attanance(email,password) values(" + '"' + cemf.getText() + '"'
                                    + "," + '"'
                                    + cpf.getText() + '"' + ')' + ";";
                            stmt.executeUpdate(att);
                            jlp.setText("  present registered");
                            break;
                        }
                    }

                } catch (Exception ea) {
                    System.out.println(ea);
                }

            }
        });

        jfc.setVisible(true);
    }

    static void newClintf() {
        JFrame jfnc = new JFrame("NEW CLIENT LOGIN");
        jfnc.setSize(600, 600);
        jfnc.setLayout(null);
        JLabel ncname = new JLabel("ENTER YOUR NAME");
        ncname.setBounds(10, 50, 150, 40);
        jfnc.add(ncname);
        JTextField ncnamef = new JTextField();
        ncnamef.setBounds(170, 50, 200, 40);
        jfnc.add(ncnamef);
        JLabel ncpost = new JLabel("ENTER YOUR POST");
        ncpost.setBounds(10, 90, 150, 40);
        jfnc.add(ncpost);
        JTextField ncpostf = new JTextField();
        ncpostf.setBounds(170, 90, 200, 40);
        jfnc.add(ncpostf);
        JLabel ncaddress = new JLabel("ENTER YOUR ADDRESS");
        ncaddress.setBounds(10, 140, 150, 40);
        jfnc.add(ncaddress);
        JTextField ncaddressf = new JTextField();
        ncaddressf.setBounds(170, 140, 200, 40);
        jfnc.add(ncaddressf);
        JLabel ncmob = new JLabel("ENTER YOUR MOBILE N.");
        ncmob.setBounds(10, 190, 150, 40);
        jfnc.add(ncmob);
        JTextField ncmobf = new JTextField();
        ncmobf.setBounds(170, 190, 200, 40);
        jfnc.add(ncmobf);
        JLabel ncemail = new JLabel("ENTER YOUR EMAIL");
        ncemail.setBounds(10, 240, 150, 40);
        jfnc.add(ncemail);
        JTextField ncemf = new JTextField();
        ncemf.setBounds(170, 240, 200, 40);
        jfnc.add(ncemf);
        JLabel ncpassword = new JLabel("ENTER YOUR PASSWORD");
        ncpassword.setBounds(10, 290, 150, 40);
        jfnc.add(ncpassword);
        JTextField ncpf = new JTextField();
        ncpf.setBounds(170, 290, 200, 40);
        jfnc.add(ncpf);
        JButton nloga = new JButton("REGISTER");
        nloga.setBounds(170, 350, 200, 60);
        jfnc.add(nloga);
        JLabel jl = new JLabel();
        jl.setBounds(150, 430, 400, 30);
        jfnc.add(jl);
        nloga.addActionListener(new ActionListener() {
            static int i = 1;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dheerajdb", "user1",
                            "123456");
                    Statement stmt = con.createStatement();
                    System.out.println("connetion created");
                    /*
                     * String sql = "CREATE TABLE employee("
                     * + "SN INT NOT NULL, "+"NAME VARCHAR (30) NOT NULL, "+"POST VARCHAR (30), "
                     * +"ADDRESS VARCHAR(50), "+"MOBILEN VARCHAR(30) ,"
                     * + "Email VARCHAR (20) NOT NULL, "
                     * +"PASSWORD VARCHAR (100) )";
                     * 
                     */
                    String sql = "INSERT into employee(name,post, address,mobilen,email,password) VALUES(" + '"'
                            + ncnamef.getText() + '"' + "," + '"' + ncpostf.getText() + '"' + "," + '"'
                            + ncaddressf.getText() + '"' +
                            "," + '"' + ncmobf.getText() + '"' + "," + '"' + ncemf.getText() + '"' + "," + '"'
                            + ncpf.getText() + '"' + ");";
                    System.out.println(sql);
                    // VALUES ('"+Text1+"','"+Text2+"')"
                    stmt.executeUpdate(sql);
                    i++;
                    jl.setText("you are sucessfully registered");
                    System.out.println("Data sucessfully insert ");
                } catch (Exception ea) {
                    System.out.println(ea);
                }

            }
        });

        jfnc.setVisible(true);
    }

    public static void main(String[] args) {
        frame();
    }

}
