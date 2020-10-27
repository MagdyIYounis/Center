package Tools;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author goda4
 */
public class API_SMS_MISR {

    private String UserName;
    private String Password;
    private String Sender;
    private int Lang;
    private String Mobile;
    private String Massege;

    /**
     *
     * @param UserName
     * @param Password
     * @param Sender
     * @param Lang
     * @param Mobile
     * @param Massege
     */
    public API_SMS_MISR(String UserName, String Password, String Sender, int Lang, String Mobile, String Massege) {
        try {
            this.UserName = UserName;
            this.Password = Password;
            this.Sender = Sender.replace(" ", "%20");
            this.Lang = Lang;
            this.Mobile = Mobile;
            this.Massege = URLEncoder.encode((Massege), "utf-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(API_SMS_MISR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return UserName;
    }

    /**
     *
     * @param UserName
     */
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return Password;
    }

    /**
     *
     * @param Password
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     *
     * @return
     */
    public String getSender() {
        return Sender;
    }

    /**
     *
     * @param Sender
     */
    public void setSender(String Sender) {
        this.Sender = Sender.replace(" ", "%20");
    }

    /**
     *
     * @return
     */
    public int getLang() {
        return Lang;
    }

    /**
     *
     * @param Lang
     */
    public void setLang(int Lang) {
        this.Lang = Lang;
    }

    /**
     *
     * @return
     */
    public String getMobile() {
        return Mobile;
    }

    /**
     *
     * @param Mobile
     */
    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    /**
     *
     * @return
     */
    public String getMassege() {
        return Massege;
    }

    /**
     *
     * @param Massege
     */
    public void setMassege(String Massege) {
        this.Massege = Massege.replace(" ", "%20");
    }

    /**
     *
     * @return
     */
    public boolean call_me() {
        try {
            URL url = new URL("https://smsmisr.com/api/webapi/?username=" + getUserName() + "&password=" + getPassword() + "&language=" + getLang() + "&sender=" + getSender() + "&mobile=" + getMobile() + ",&message=" + getMassege() + "");
            StringBuilder postData = new StringBuilder();
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;) {
                sb.append((char) c);
            }
            /* 
            System.out.println(response);
            System.out.println(conn.getResponseCode());
            JSONObject myResponse = new JSONObject(response);
            System.out.println("result after Reading JSON Response");
            System.out.println("origin- " + myResponse.getString("code"));
             */
            if (conn.getResponseCode() == 200) {
                String response = sb.toString();
                JSONObject myResponse = new JSONObject(response);
                String Code = myResponse.getString("code");
                if ("1901".equals(Code)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(API_SMS_MISR.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (UnsupportedEncodingException | JSONException ex) {
            Logger.getLogger(API_SMS_MISR.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(API_SMS_MISR.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}
