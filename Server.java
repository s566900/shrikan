package ChattingApplication;

import javax.swing.border.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.net.*;

public class Server  implements ActionListener {

    JTextField text;
    JPanel a1;
  static   Box vertical= Box.createVerticalBox();
   static JFrame f =new JFrame();
   static DataOutputStream dout;
    Server(){
        f.setLayout(null);

        JPanel p1 =new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);
        p1.setLayout(null);
        f. add(p1);


//        ImageIcon i1 = new ImageIcon("C:\\Users\\HP\\Downloads\\back.png");
//        f.setIconImage(i1.getImage());
//         Image i2 = i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
//         ImageIcon i3 =new ImageIcon(i2);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon\\back.png"));
        Image i2 = i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon i3 =new ImageIcon(i2);
         JLabel back = new JLabel(i3);
        back.setBounds(5,20,25,25);
         p1.add(back);




        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });

        ImageIcon i4 = new ImageIcon("C:\\Users\\HP\\Downloads\\shri.jpeg");
        f.setIconImage(i4.getImage());
        Image i5 = i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon i6 =new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40,10,50,50);
        p1.add(profile);

        ImageIcon i7 = new ImageIcon("C:\\Users\\HP\\Downloads\\video.png");
        f.setIconImage(i7.getImage());
        Image i8 = i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i9 =new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(270,20,30,30);
        p1.add(video);

        ImageIcon i10 = new ImageIcon("C:\\Users\\HP\\Downloads\\calling.png");
        f.setIconImage(i10.getImage());
        Image i11 = i10.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i12 =new ImageIcon(i11);
        JLabel call = new JLabel(i12);
        call.setBounds(330,20,30,30);
         p1.add(call);

        ImageIcon i13 = new ImageIcon("C:\\Users\\HP\\Downloads\\more-op.png");
        f.setIconImage(i13.getImage());
        Image i14 = i13.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT);
        ImageIcon i15 =new ImageIcon(i14);
        JLabel opp = new JLabel(i15);
        opp.setBounds(380,20,40,40);
        p1.add(opp);

        JLabel name = new JLabel("Shrikant");
        name.setBounds(110,15,100,18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN SERIF",Font.BOLD,18));
        p1.add(name);

        JLabel status = new JLabel("Active now");
        status.setBounds(110,35,100,18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN SERIF",Font.BOLD,14));
        p1.add(status);

         a1 = new JPanel();
        a1.setBounds(5,75,435,565);
        f.add(a1);

         text =new JTextField();
        text.setBounds(5,650,310,45);
        text.setFont(new Font("SAN SERIF",Font.PLAIN,16));
        f.add(text);

//        ImageIcon i16 = new ImageIcon("C:\\Users\\HP\\Downloads\\sands.jpg");
//        setIconImage(i16.getImage());
//        Image i17 = i16.getImage().getScaledInstance(75,35,Image.SCALE_DEFAULT);
//        ImageIcon i18 =new ImageIcon(i17);
//        JLabel sand = new JLabel(i18);
//        sand.setBounds(345,650,75,35);
//        sand.addAncestorListener(this);
//        add(sand);
        JButton send =new JButton("send");
        send.setBounds(345,650,75,35);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN SERIF",Font.PLAIN,16));
        f.add(send);





        f.setSize(450,700);
        f.setLocation(200,50);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);

        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {
        try {
            String out = text.getText();


            JPanel p2 = formatLabel(out);


            a1.setLayout(new BorderLayout());

            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));

            a1.add(vertical, BorderLayout.PAGE_START);

            dout.writeUTF(out);

            text.setText("");

            f.repaint();
            f.invalidate();
            f.validate();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    public  static JPanel formatLabel(String out){
       JPanel panel = new JPanel();

       panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

       JLabel output =new JLabel(out);
       output.setFont(new Font("Tahoma",Font.PLAIN,16));
       output.setBackground(new Color(164, 68, 163, 81));
       output.setOpaque(true);
//       output.setBackground(new EmptyBorder(15,15,15,50));

       panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);

       return  panel;
    }

    public static void main(String[] args) {

        new Server();

        try {
            ServerSocket skt = new ServerSocket(6001);
            while (true){
                Socket s=skt.accept();
                DataInputStream din =new DataInputStream(s.getInputStream());
                 dout =new DataOutputStream(s.getOutputStream());
                while (true){
                    String msg = din.readUTF();
                    JPanel panel = formatLabel(msg);

                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel,BorderLayout.LINE_START);
                    vertical.add(left);
                    f.validate();
                }
            }
        } catch (Exception e)
        {
            System.out.println(e);
        }



    }
}
