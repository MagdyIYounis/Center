/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IReport;

import Tools.mysql;
import java.awt.BorderLayout;
import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author goda4
 */
public class IReport {
//JasperViewer

    public JasperPrint R1(String Name, HashMap HMap, JPanel node) {
        try {
            System.out.println("1");
            JasperDesign jd = JRXmlLoader.load(new File("").getAbsolutePath() + "\\src\\IReport\\" + Name + ".jrxml");
            System.out.println("2");
            String URL_Image = new File("").getAbsolutePath() + "\\src\\image\\";
            System.out.println("3");
            HMap.put("URL_Image", URL_Image);
            System.out.println("4");
            JasperReport jr = JasperCompileManager.compileReport(jd);
            System.out.println("5");
            JasperPrint jp = JasperFillManager.fillReport(jr, HMap, mysql.con);

            System.out.println("6");

            if (jp != null) {
                JRViewer viewer = new JRViewer(jp);
                node.setLayout(new BorderLayout());
                node.setPreferredSize(new java.awt.Dimension(1070, 475));
                node.add(viewer);
                node.repaint();
                node.revalidate();

                return jp;
            } else {

                return null;
            }

        } catch (Exception ex) {

            Tools.Tools_JavaFX.error(ex);
        }

        return null;

    }

    public void R2(String Name, JPanel node, HashMap HMap, String Statment) {
        try {
            JasperDesign jd = JRXmlLoader.load(new File("").getAbsolutePath() + "\\src\\IReport\\" + Name + ".jrxml");
            String URL_Image = new File("").getAbsolutePath() + "\\src\\image";
            HMap.put("URL_Image", URL_Image);
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(Statment);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp;
            jp = JasperFillManager.fillReport(jr, null, mysql.con);
            if (jp != null) {
                JRViewer viewer = new JRViewer(jp);
                node.setLayout(new BorderLayout());
                node.setPreferredSize(new java.awt.Dimension(1070, 475));
                node.add(viewer);
                node.repaint();
                node.revalidate();
            }

        } catch (Exception ex) {
            Tools.Tools_JavaFX.error(ex);
        }
    }

    public void JR_PDF(JasperPrint JP, String ID_SU) {
        String Path = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\Center";
        String Sub = (String) mysql.PrintTable("SELECT `Name_SU`,`ID_YE` FROM `subject` WHERE `ID_SU`=" + ID_SU).Table[0][0];
        String ID_YE = (String) mysql.PrintTable("SELECT `Name_SU`,`ID_YE` FROM `subject` WHERE `ID_SU`=" + ID_SU).Table[0][1];
        String YE = (String) mysql.PrintTable("SELECT `Name_YE`,`ID_FA` FROM `years` WHERE `ID_YE`=" + ID_YE).Table[0][0];
        String ID_FA = (String) mysql.PrintTable("SELECT `Name_YE`,`ID_FA` FROM `years` WHERE `ID_YE`=" + ID_YE).Table[0][1];
        String Fa = (String) mysql.PrintTable("SELECT `Name_FA` FROM `faculty` WHERE `ID_FA`=" + ID_FA).Table[0][0];
        String ID_UN = (String) mysql.PrintTable("SELECT `ID_UN` FROM `faculty` WHERE `ID_FA`=" + ID_FA).Table[0][0];
        String UN = (String) mysql.PrintTable("SELECT `Name_UN` FROM `universty` WHERE `ID_UN`=" + ID_UN).Table[0][0];

        if (!JP.equals(null)) {
            try {
                File F = new File(Path);
                if (F.isDirectory()) {
                    Path = Path + "\\" + UN;
                    File F1 = new File(Path);
                    if (F1.isDirectory()) {
                        Path = Path + "\\" + Fa;
                        File F2 = new File(Path);
                        if (F2.isDirectory()) {
                            Path = Path + "\\" + YE;
                            File F3 = new File(Path);
                            if (F3.isDirectory()) {
                                Path = Path + "\\" + Sub;
                                File F4 = new File(Path);
                                if (F4.isDirectory()) {
                                    String pdf = Path + "\\" + "(" + ID_SU + ")" + Tools.Tools.Date("yyyy-MM-dd HH-mm-ss") + ".pdf";
                                    JasperExportManager.exportReportToPdfFile(JP, pdf);
                                } else {
                                    boolean mkdir = F4.mkdir();
                                    if (mkdir) {
                                        JR_PDF(JP, ID_SU);
                                    } else {
                                        Tools.Tools_JavaFX.Alert_T(Alert.AlertType.ERROR, "مشكلة الحفظ", "لم نستطيع انشاء الملف", "يرجوا التواصل مع الدعم الفني");
                                    }

                                }
                            } else {
                                boolean mkdir = F3.mkdir();

                                if (mkdir) {
                                    JR_PDF(JP, ID_SU);
                                } else {
                                    Tools.Tools_JavaFX.Alert_T(Alert.AlertType.ERROR, "مشكلة الحفظ", "لم نستطيع انشاء الملف", "يرجوا التواصل مع الدعم الفني");
                                }

                            }
                        } else {
                            boolean mkdir = F2.mkdir();

                            if (mkdir) {
                                JR_PDF(JP, ID_SU);
                            } else {
                                Tools.Tools_JavaFX.Alert_T(Alert.AlertType.ERROR, "مشكلة الحفظ", "لم نستطيع انشاء الملف", "يرجوا التواصل مع الدعم الفني");
                            }

                        }
                    } else {
                        boolean mkdir = F1.mkdir();

                        if (mkdir) {
                            JR_PDF(JP, ID_SU);
                        } else {
                            Tools.Tools_JavaFX.Alert_T(Alert.AlertType.ERROR, "مشكلة الحفظ", "لم نستطيع انشاء الملف", "يرجوا التواصل مع الدعم الفني");
                        }

                    }
                } else {
                    boolean mkdir = F.mkdir();

                    if (mkdir) {
                        JR_PDF(JP, ID_SU);
                    } else {
                        Tools.Tools_JavaFX.Alert_T(Alert.AlertType.ERROR, "مشكلة الحفظ", "لم نستطيع انشاء الملف", "يرجوا التواصل مع الدعم الفني");
                    }

                }

            } catch (Exception ex) {
                Tools.Tools_JavaFX.error(ex);
            }
        } else {
            Tools.Tools_JavaFX.Alert_T(Alert.AlertType.ERROR, "لا يوجد كشف متاح", "يجب اختيار الكشف", "");
        }

    }

    public JasperPrint R_V(String Name, HashMap HMap) {
        try {
            JasperDesign jd = JRXmlLoader.load(new File("").getAbsolutePath() + "\\src\\IReport\\" + Name + ".jrxml");
            String URL_Image = new File("").getAbsolutePath() + "\\src\\image\\";
            HMap.put("URL_Image", URL_Image);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp;
            jp = JasperFillManager.fillReport(jr, HMap, mysql.con);
            JasperViewer.viewReport(jp, false);

        } catch (Exception ex) {

            Tools.Tools_JavaFX.error(ex);

        }
        return null;

    }

}
