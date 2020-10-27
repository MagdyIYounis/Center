/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;


import java.awt.AWTException;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.Date;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author adel yons
 */
public class Tools {

    /**
     *
     * @param text
     */
    public static void Show_M(Object text){
        JOptionPane.showMessageDialog(null, text);
    }

    /**
     *
     * @param Massege
     * @return
     */
    public static boolean conf(String Massege){
    int x = JOptionPane.showConfirmDialog(null, Massege);
    if(x == JOptionPane.YES_OPTION){
     return true;
    }else{
     return false;
    }
    }

    /**
     *
     * @param Name
     * @param Path
     */
    public static void Create_folder(String Name , String Path){
        
        if(Path == null){
            File C = new File(Name);
            C.mkdir();
        }else{
            File C = new File(Path + Name);
            C.mkdir();
        }
    }

    /**
     *
     * @param a
     * @param type
     * @param Path
     * @throws IOException
     */
    public static void Create_file(String a ,String type , String Path) throws IOException{
      
        if(Path == ""){
            File C = new File(a + "." + type);
            C.createNewFile();
        }else{
            File C = new File(Path + a + "." + type);
            C.createNewFile();
        }
        
    }

    /**
     *
     * @param Name
     * @param Type
     * @param Path
     * @param Text
     * @throws FileNotFoundException
     */
    public static void Wirte_File(String Name,String Type,String Path, Object[] Text) throws FileNotFoundException{
        
                if(Name == ""){
                    if(Path == ""){
                        try (PrintWriter W = new PrintWriter(Name + "." + Type)) {
                        for(Object a : Text){
                            W.println(a);
                            W.println("ـــــــــــــــــــــــــــــ");
                        }   }
                    }else{
                        try (PrintWriter W = new PrintWriter(Path + File.separator + Text[0] + "." + Type)) {
                            for(Object a : Text){
                                W.println(a);
                                W.println("ـــــــــــــــــــــــــــــ");
                            }   }
                    }
                }else{
                        if(Path == ""){
                            try (PrintWriter W = new PrintWriter(Name + "." + Type)) {
                                for(Object a : Text){
                                    W.println(a);
                                    W.println("ــــــــــــــــ");
                                }   }
                        }else{
                            try (PrintWriter W = new PrintWriter(Path + File.separator + Name + "." + Type)) {
                                for(Object a : Text){
                                    W.println(a);
                                    W.println("ــــــــــــــــ");
                                }   }
                        }
                }  
             {       
        }
    }

    /**
     *
     * @param Massege
     * @param Name
     * @param Name_Image
     * @return 
     * @throws IOException
     */

    public static Object Input(String Massege){
    
        return JOptionPane.showInputDialog(Massege);
    }
    
    /**
     *
     * @param Type
     * @return 
     */
    public static String Date(String Type){
        Date D = new Date();
        java.text.SimpleDateFormat S = new java.text.SimpleDateFormat(Type);
        return  S.format(D);
    }

    /**
     *
     * @return
     */
    public static Object Scanner(){
    
        java.util.Scanner Me = new java.util.Scanner(System.in);
        return Me.nextLine();
        
    }

    /**
     *
     * @param Name_File
     * @return
     */
    public static URI Path(String Name_File){
        File F = new File(Name_File);
        return F.toURI();
    }

    /**
     *
     * @param Name
     */
    public static void Mp3(String Name){
        JFXPanel n = new JFXPanel();
        Media N = new Media(Tools.Path(Name + ".mp3").toString());
        MediaPlayer M = new MediaPlayer(N);
        M.play();
        
    }

    /**
     *
     * @param Name
     * @param Type
     * @throws IOException
     */
    public static void video(String Name,String Type) throws IOException{
        Desktop.getDesktop().open(new File(Name + "." + Type));
    }
    
    /**
     *
     * @param Name
     * @throws IOException
     */
    public static void browse(String Name) throws IOException{
        Desktop.getDesktop().browse(Tools.Path(Name));
    }
    
    /**
     *
     * @param Start
     * @param increment
     */
    public static void random(int Start , int increment){
        int rand =  Start + (int)(Math.random()*increment) ;
        System.out.println(rand);
    }
    
    /**
     *
     * @param Name
     * @param Type
     * @param W
     * @param H
     * @return
     * @throws IOException
     */
    public static ImageIcon Image(String Name , String Type , int W , int H) throws IOException{
        
        Image Img = ImageIO.read(new File(Name + "." + Type));
        Img = Img.getScaledInstance(W, H, Image.SCALE_AREA_AVERAGING);
        ImageIcon I = new ImageIcon(Img);
        return I;
        
    }
    
    /**
     *
     * @param Text
     * @param Type_Filter
     * @return
     */
    public static File Chooser(String Text , String [] Type_Filter){
        JFileChooser C = new JFileChooser();
        if (Type_Filter[0] == ""){
            C.showDialog(null, Text);
            return C.getSelectedFile();
        }else{
            FileNameExtensionFilter F = new FileNameExtensionFilter("Filter Image", (Type_Filter[0]),(Type_Filter[1]) ,(Type_Filter[2]));
            C.setFileFilter(F);
            C.showDialog(null, Text);
            return C.getSelectedFile();
        }
    }
    
    /**
     *
     * @param Name
     * @throws IOException
     */
    public static void install(String Name) throws IOException{
    ProcessBuilder P = new ProcessBuilder(Tools.Path(Name).toString());
    P.start();
    }
    
    /**
     *
     * @param Cont
     */
    public static void clear_Input(Container Cont){
    
        for (Component F : Cont.getComponents()){
           
            if (F instanceof JTextField){
                JTextField txt = (JTextField)F;
                txt.setText("");
            }else if (F instanceof Container){
                Tools.clear_Input((Container)F);
            }
            
            
        }
        
    
    }

    /**
     *
     * @param Name
     * @param Type
     * @throws AWTException
     * @throws IOException
     */
    public static void PrintScreen(String Name , String Type) throws AWTException, IOException{
        
        Robot R = new Robot();
        Rectangle Size = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage Screen = R.createScreenCapture(Size);
        ImageIO.write(Screen, Type , new File(Name+"."+Type));
    
    }

    /**
     *
     */
    public class  Table {

        /**
         *
         */
        public int cul;

        /**
         *
         */
        public Object[][] Table;
    
        /**
         *
         * @param cul
         */
        public Table(int cul){
        this.cul = cul;
    this.Table = new Object[0][cul];
    }
    
        /**
         *
         * @param rows
         */
        public void addRow(Object rows[]){
    
        Object store[][] = Table;
        Table = new Object[Table.length +1 ][cul];
        for(int x =0; x < store.length ;x++ ){
        
            this.Table[x] = store[x];
        
        }
        this.Table[this.Table.length - 1] = rows;
        
        
    
    }

        /**
         *
         */
        public void PrintTable(){
    
        for(Object X[] : this.Table){
            for(Object y : X){
            System.out.println(y + "/n");
            }
        
        }
        
    }

}
    
   
}


