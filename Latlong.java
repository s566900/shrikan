import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class LapLatGui {
    public static void main(String[] args) throws MalformedURLException {
        LplnGui ln=new LplnGui();
        ln.fui();
    }
}
class LplnGui{
    static void fui() throws MalformedURLException {
        JFrame frame=new JFrame("Latitude and Longitude Finder");
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.gray);
        JLabel jp=new JLabel("enter your city name :");
        jp.setBounds(130,50,200,40);
        frame.add(jp);
        JTextField jf=new JTextField();
        jf.setBackground(Color.getColor( "black", Color.lightGray ));
        jf.setBounds(90,100,200,40);
        frame.add(jf);
        JLabel jlp=new JLabel("latitude is:");

        jlp.setBounds(75,220,90,30);
        frame.add(jlp);
        JTextField jlpf=new JTextField();
        jlpf.setBounds(10,280,180,40);
        frame.add(jlpf);
        jlpf.setBackground(Color.getColor( "black", Color.lightGray ));
        JLabel jlng=new JLabel("longitude is :");
        jlng.setBounds(275,220,90,30);
        frame.add(jlng);
        JTextField jlgf=new JTextField();
        jlgf.setBackground(Color.getColor( "black", Color.lightGray ));
        jlgf.setBounds(200,280,180,40);
        frame.add(jlgf);
        JButton jget=new JButton("GET");
        jget.setBounds(90,150,200,60);
        frame.add(jget);
        ActionListener clickget=new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                String ct = jf.getText();
                String dss = "";
                try {
                    URL url = new URL("http://api.openweathermap.org/geo/1.0/direct?q=" + ct + ",91&limit=1&appid=c06001cc8670a7eaa42a881d74fec52c");
                    HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
                    urlcon.setRequestMethod("GET");
                    InputStreamReader in = new InputStreamReader(urlcon.getInputStream());
                    BufferedReader in1 = new BufferedReader(in);
                    String dt;
                    while ((dt = in1.readLine()) != null) {
                        dss = dt;
                    }
                    JSONParser par = new JSONParser();
                    JSONArray array = (JSONArray) par.parse(dss);
                    JSONObject obj = (JSONObject) array.get(0);
                    double lat=(double)obj.get("lat");
                    double lon=(double)obj.get("lon");
                    urlcon.disconnect();

                    jlpf.setText(String.valueOf(lat));
                    jlgf.setText(String.valueOf(lon));


                } catch (ProtocolException ea) {
                    throw new RuntimeException(ea);
                } catch (IOException eb) {
                    throw new RuntimeException(eb);
                } catch (ParseException ec) {
                    throw new RuntimeException(ec);
                }};};
        jget.addActionListener(clickget);
        frame.setVisible(true);
    }}
