package org.eleron.lris.niokr.bussines;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eleron.lris.niokr.model.Report;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;

public class MicrosoftReports {

    private static final Logger log = Logger.getLogger(MicrosoftReports.class);

    private static final String REPORT_PATH = "templates/reports";
//    private static final String REPORT_PATH = "view/Main.fxml";
    public static void fromReportToWord(Report report){
        if(report != null){
            try{
                XWPFDocument document = new XWPFDocument();
                XWPFParagraph paragraph = document.createParagraph();
                paragraph.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun paragraphConfig = paragraph.createRun();
                paragraphConfig.setItalic(true);
                paragraphConfig.setFontSize(14);
                paragraphConfig.setText("Hello World");
//                System.out.println(String.valueOf(MicrosoftReports.class.getClassLoader().getResource(REPORT_PATH)));
                String str = String.valueOf(MicrosoftReports.class.getClassLoader().getResource(REPORT_PATH)) + "\\report.docx";
                FileOutputStream out = new FileOutputStream(new File(str.substring(6)));
                document.write(out);
                out.close();
                /*if (Desktop.isDesktopSupported()) {
                    File file = new File(String.valueOf(MicrosoftReports.class.getClassLoader().getResource(REPORT_PATH)));
                    Desktop.getDesktop().open(file);
                }*/
                log.info("document docx successfully construated");
            }catch(Exception e){
                log.error("fail create docx document",e);
            }
        }
    }
}
