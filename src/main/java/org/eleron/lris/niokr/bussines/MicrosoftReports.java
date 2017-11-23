package org.eleron.lris.niokr.bussines;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.eleron.lris.niokr.model.Report;
import org.eleron.lris.niokr.util.DateUtil;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MicrosoftReports {

    private static final Logger log = Logger.getLogger(MicrosoftReports.class);

    private static Map<String,String> replacer;

    public Map<String, String> getReplacer() {
        return replacer;
    }

    public void setReplacer(Map<String, String> replacer) {
        this.replacer = replacer;
    }

    private static Map<String,String> getData(){
        if(replacer == null){
            replacer = new HashMap<>();
            replacer.put("NTOCHEF","Жихарев С.Н.");
            replacer.put("INMONTH", DateUtil.getCurrentMonthR().substring(0,DateUtil.getCurrentMonthR().length()-1) + "е");
            replacer.put("YEAR", Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
            replacer.put("DEPARTMENT",Enter.getcUser().getDepartment().getName());
            replacer.put("DEPARTMENTCHEF",Enter.getcUser().getDepartment().getHead());
            return replacer;
        }else{
            return replacer;
        }
    }

    public static void fromReportToWord(Report report,String pathTemplete, String savePath){
        XWPFDocument document = null;
        replacer = getData();
        if(report != null){
            try{
                document = new XWPFDocument(OPCPackage.open(pathTemplete));
                for(XWPFParagraph paragraph : document.getParagraphs()){
                    for(XWPFRun run : paragraph.getRuns()){
                        String text = run.getText(0);
                        if(text != null){
                            for(String key : replacer.keySet()){
                                if(text.contains(key)){
                                    text = text.replace(key,replacer.get(key));
                                }
                            }
                        }
                        run.setText(text,0);
                    }
                }
                for(XWPFTable table : document.getTables()){
                    for(XWPFTableRow row : table.getRows()){
                        for(XWPFTableCell cell : row.getTableCells()){
                            for(XWPFParagraph paragraph : cell.getParagraphs()){
                                for(XWPFRun run : paragraph.getRuns()){
                                    String text = run.getText(0);
                                    if(text != null){
                                        for(String key : replacer.keySet()){
                                            if(text.contains(key)){
                                                text = text.replace(key,replacer.get(key));
                                            }
                                        }
                                    }
                                    run.setText(text,0);
                                }
                            }
                        }
                    }
                }
                document.write(new FileOutputStream(savePath));
/*
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
                if (Desktop.isDesktopSupported()) {
                    File file = new File(str.substring(6));
                    Desktop.getDesktop().open(file);
                }*/
                log.info("document docx successfully construated");
            }catch(Exception e){
                log.error("fail create docx document",e);
            }
        }
    }
}
