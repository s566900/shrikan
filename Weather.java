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




public class Weatherinfo {
        public static void main(String[] args) throws MalformedURLException {
            LplnGu ln=new LplnGu();
            ln.fui();
        }
    }








    class LplnGu{
        static void fui() throws MalformedURLException {
            JFrame frame=new JFrame("WEATHER REPORT");
            frame.setSize(600,600);
            frame.setLayout(null);
            frame.getContentPane().setBackground(Color.gray);
            JLabel jp=new JLabel("enter your city name :");
            jp.setBounds(230,20,200,30);
            frame.add(jp);
            JTextField jf=new JTextField();
            jf.setBounds(190,70,200,30);
            frame.add(jf);
            JButton jget=new JButton("GET WETHER REPORT");
            jget.setBounds(190,110,200,50);
            frame.add(jget);
            JLabel jlp=new JLabel("Weather report is:");
            jlp.setBounds(240,170,200,30);
            frame.add(jlp);


            JLabel Temperature=new JLabel("Temperature K:");
            Temperature.setBounds(200,210,190,20);
            frame.add(Temperature);
            JTextField tempf=new JTextField();
           tempf .setBounds(200,230,190,30);
            frame.add(tempf);
            JLabel tempc=new JLabel("temp in c:");
            tempc.setBounds(10,270,190,20);
            frame.add(tempc);
            JTextField tempcf=new JTextField();
            tempcf.setBounds(10,310,190,30);
            frame.add(tempcf);
            JLabel humidity=new JLabel("humidity:");
            humidity.setBounds(210,270,180,20);
            frame.add(humidity);
            JTextField humidityf=new JTextField();
            humidityf.setBounds(210,310,180,30);
            frame.add(humidityf);
            JLabel Pressure=new JLabel(" Pressure:");
            Pressure.setBounds(400,270,190,20);
            frame.add(Pressure);
            JTextField Pressuref=new JTextField();
            Pressuref.setBounds(400,310,190,30);
            frame.add(Pressuref);
            JLabel Visibility=new JLabel("Visibility :");
            Visibility.setBounds(10,360,190,20);
            frame.add(Visibility);
            JTextField Visibilityf=new JTextField();
            Visibilityf.setBounds(10,390,190,30);
            frame.add(Visibilityf);
            JLabel speed=new JLabel("Wind speed :");
            speed.setBounds(210,360,180,20);
            frame.add(speed);
            JTextField speedf=new JTextField();
            speedf.setBounds(210,390,180,30);
            frame.add(speedf);
            JLabel Description=new JLabel("Description:");
            Description.setBounds(400,360,190,20);
            frame.add(Description);
            JTextField Descriptionf=new JTextField();
            Descriptionf.setBounds(400,390,190,30);
            frame.add(Descriptionf);

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
                        URL url1 = new URL("https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=c06001cc8670a7eaa42a881d74fec52c");
                        HttpURLConnection urlcon1 = (HttpURLConnection) url1.openConnection();
                        urlcon1.setRequestMethod("GET");
                        InputStreamReader in2 = new InputStreamReader(urlcon1.getInputStream());
                        BufferedReader in3 = new BufferedReader(in2);
                        String dt1;
                        String dss1="";
                        while ((dt1 = in3.readLine()) != null) {
                            dss1+= dt1;
                        }
                        System.out.println(dss1);
                      // frame.setContentPane(new JLabel(new ImageIcon("out/istockphoto-1368170023-612x612.jpg")));
                        JSONParser par1 = new JSONParser();
                        JSONObject obj1 = (JSONObject) par1.parse(dss1);
                        JSONObject obj2 = (JSONObject)obj1.get("main");
                       tempf.setText(""+obj2.get("temp"));
                        double db= (double) obj2.get("temp");
                        tempcf.setText(""+(db-273));
                        humidityf.setText(""+obj2.get("humidity"));
                        Pressuref.setText(""+obj2.get("pressure"));
                        Visibilityf.setText(""+obj1.get("visibility"));

                        JSONObject obj3 = (JSONObject)obj1.get("wind");
                        speedf.setText(""+obj3.get("speed"));

                        JSONArray array1 = (JSONArray) obj1.get("weather");
                        JSONObject ob3 = (JSONObject) array1.get(0);
                        Descriptionf.setText(""+ob3.get("description"));

                        urlcon1.disconnect();




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