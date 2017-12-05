package org.eleron.lris.niokr.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.apache.log4j.Logger;
import org.eleron.lris.niokr.bussines.Enter;
import org.eleron.lris.niokr.bussines.LoadScenes;
import org.eleron.lris.niokr.dao.DateOfReportsDAOImplements;
import org.eleron.lris.niokr.dao.DepartmentDAO;
import org.eleron.lris.niokr.dao.DepartmentDAOImplements;
import org.eleron.lris.niokr.model.Department;
import org.eleron.lris.niokr.model.Report;
import org.eleron.lris.niokr.util.DateUtil;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FinalLevelWindow {

    /*
    logger
     */

    private final static Logger log = Logger.getLogger(FinalLevelWindow.class);

    /*
    Fields
     */

    private List<Integer> datesOfReport;

    private List<String> monthsOfReport;

    private List<Department> listOfDepartment;

    private Map<Department,Integer> howReportsCome;

    /**
     * FXML Fields
     */

    @FXML
    private Label allDepartments;

    @FXML
    private Label receiveDepartments;

    @FXML
    private Label notReceiveDepartments;

    @FXML
    private TableView<Department> departmentTableView;

    @FXML
    private ChoiceBox yearsChoiceBox;

    @FXML
    private ChoiceBox monthsChoiceBox;

    @FXML
    private TableColumn<Department,Integer> numberReportsOfDepartment;

    @FXML
    private TableColumn<Department,String> departmentName;

    @FXML
    private TableColumn<Department,Integer> numberReceiveReportsOfDepartment;

    /**
     * FXML Methods
     */

    @FXML
    public void backToDepartment(){}

    @FXML
    public void settingsOpen(){

        LoadScenes.load("view/ReviewMenu.fxml");
    }

    @FXML
    public void reportForDepartment(){}

    @FXML
    public void reportForAllDepartments(){}

    /**
     * Init method
     */

    public void initialize(){
        //Enter class fill
        Enter.setDateOfReports(new DateOfReportsDAOImplements().getDates());

        datesOfReport = Enter.getDateOfReports();
        ObservableList<Integer> dateData= FXCollections.observableArrayList(datesOfReport);

        yearsChoiceBox.setItems(dateData);
        yearsChoiceBox.setValue(Calendar.getInstance().get(Calendar.YEAR));

        monthsOfReport = DateUtil.listOfMonths();
        ObservableList<String> monthsData = FXCollections.observableArrayList(monthsOfReport);

        monthsChoiceBox.setItems(monthsData);
        monthsChoiceBox.setValue(DateUtil.getCurrentMonth());

        listOfDepartment = new DepartmentDAOImplements().listDepartment();
        howReportsCome = new HashMap<>();
        Integer departmentsGood = 0;
        for(Department department : listOfDepartment){
            List<Report> reports = department.getReports();
            if(reports.isEmpty()){
                departmentsGood += 1;
                howReportsCome.put(department,0);
            } else {
                int sendDepartment = 0;
                for(Report report : reports){
                    sendDepartment = report.getStatus()==2?sendDepartment+1:sendDepartment;
                }
                howReportsCome.put(department,sendDepartment);
                if(sendDepartment == reports.size()){
                    departmentsGood += 1;
                }
            }
        }

        allDepartments.setText("Все (" + listOfDepartment.size() + ")");
        receiveDepartments.setText("Прислали (" + departmentsGood +")");
        notReceiveDepartments.setText("НЕ прислали (" + (listOfDepartment.size() - departmentsGood) +")");
    }
}
