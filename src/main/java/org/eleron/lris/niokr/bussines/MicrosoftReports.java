package org.eleron.lris.niokr.bussines;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.eleron.lris.niokr.model.Report;
import org.eleron.lris.niokr.util.AlertUtil;
import org.eleron.lris.niokr.util.DateUtil;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.List;

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

    public static void formReportTo(List<String> parameters, List<Report> reportList, String pathSave, String fileTemplate){

        try {
            XWPFDocument document = new XWPFDocument(new FileInputStream(fileTemplate));

            XWPFParagraph paragraph1Line = document.getLastParagraph();
            XWPFRun run1LineOne = paragraph1Line.createRun();
            paragraph1Line.setAlignment(ParagraphAlignment.RIGHT);
            run1LineOne.setText("Начальнику НТО");

            XWPFParagraph paragraph2Line = document.createParagraph();
            XWPFRun run2LineOne = paragraph2Line.createRun();
            paragraph2Line.setAlignment(ParagraphAlignment.RIGHT);
            run2LineOne.setText(parameters.get(0));

            XWPFParagraph paragraph3Line = document.createParagraph();
            XWPFRun run3LineOne = paragraph3Line.createRun();
            paragraph3Line.setAlignment(ParagraphAlignment.CENTER);
            run3LineOne.setBold(true);
            run3LineOne.setText("СЛУЖЕБНАЯ ЗАПИСКА");

            XWPFParagraph paragraph4Line = document.createParagraph();
            XWPFRun run4LineOne = paragraph4Line.createRun();
            paragraph4Line.setAlignment(ParagraphAlignment.CENTER);
            run4LineOne.setText("О ходе выполнения ОКР в " + parameters.get(1)+ " " + parameters.get(2));

            for(Report report : reportList){
                XWPFParagraph paragraph1Report = document.createParagraph();
                XWPFRun run1Report = paragraph1Report.createRun();
                run1Report.setUnderline(UnderlinePatterns.SINGLE);
                run1Report.setText("СЧ ОКР \"" + report.getNameLong() + "\", шифр \"" + report.getNameShort() + "\"." );

                XWPFParagraph paragraph2Report = document.createParagraph();
                XWPFRun run2Report = paragraph2Report.createRun();
                run2Report.setText("а) Ведущий по ОКР: " + report.getOwner());

                XWPFParagraph paragraph3Report = document.createParagraph();
                XWPFRun run3Report = paragraph3Report.createRun();
                run3Report.setText("б) Ход выполнения НИР в соответствии с план-графиком:");

                XWPFParagraph paragraph4Report = document.createParagraph();
                XWPFRun run4Report = paragraph4Report.createRun();
                run4Report.setText("    - Процент выполнения от общего объёма работ (на "
                        + parameters.get(2)
                        + "г.) (09.01 - 29.12) - "
                        + report.getPersentOfYear()
                        + "%");

                XWPFParagraph paragraph5Report = document.createParagraph();
                XWPFRun run5Report = paragraph5Report.createRun();
                run5Report.setText("    - Процент выполнения за текущий месяц (01." +
                        (Calendar.MONTH+10)%13 +
                        " - " +
                        (new GregorianCalendar(Calendar.YEAR,Calendar.MONTH,1)).getActualMaximum(Calendar.DAY_OF_MONTH) +
                        "." +
                        (Calendar.MONTH+10)%13 +
                        ") - " +
                        report.getPersentOfMonth() + "%");

                for(String str : ("        " + report.getText()).split("\n")){
                    XWPFParagraph paragraph = document.createParagraph();
                    XWPFRun run = paragraph.createRun();
                    run.setText(str);
                }

                XWPFParagraph paragraph6Report = document.createParagraph();
                XWPFRun run6Report = paragraph6Report.createRun();
                run6Report.setText("в) Проблемные вопросы:");

                for(String str : ("        " + report.getTrouble()).split("\n")){
                    XWPFParagraph paragraph = document.createParagraph();
                    XWPFRun run = paragraph.createRun();
                    run.setText(str);
                }
            }

            XWPFTable table = document.createTable(1,2);

            CTTbl tableCT        = table.getCTTbl();
            CTTblPr pr         = tableCT.getTblPr();
            CTTblWidth tblW = pr.getTblW();
            tblW.setW(BigInteger.valueOf(5000));
            tblW.setType(STTblWidth.PCT);
            pr.setTblW(tblW);
            pr.unsetTblBorders();
            tableCT.setTblPr(pr);

            table.getRow(0).getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(500));

            table.getRow(0).getCell(0).getParagraphs().get(0).createRun().setText("Начальник " + parameters.get(3));
            table.getRow(0).getCell(1).getParagraphs().get(0).createRun().setText("__________" + parameters.get(4));

            FileOutputStream out = new FileOutputStream(pathSave);
            document.write(out);
            out.close();
        } catch (IOException e) {
            log.error("Document template missing or incorrect path or can't write file",e);
            e.printStackTrace();
        }
    }
}
