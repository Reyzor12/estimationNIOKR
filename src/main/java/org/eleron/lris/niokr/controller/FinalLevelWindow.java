package org.eleron.lris.niokr.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.log4j.Logger;
import org.eleron.lris.niokr.bussines.Enter;
import org.eleron.lris.niokr.bussines.LoadScenes;
import org.eleron.lris.niokr.dao.DateOfReportsDAOImplements;
import org.eleron.lris.niokr.dao.DepartmentDAOImplements;
import org.eleron.lris.niokr.model.Department;
import org.eleron.lris.niokr.model.Report;
import org.eleron.lris.niokr.util.DateUtil;
import org.eleron.lris.niokr.transletors.DepartmentStatistic;

import java.util.*;

public class  FinalLevelWindow {

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
    private TableView<DepartmentStatistic> departmentTableView;
    @FXML
    private ChoiceBox<Integer> yearsChoiceBox;

    @FXML
    private ChoiceBox<String> monthsChoiceBox;

    @FXML
    private TableColumn<DepartmentStatistic,Integer> numberReportsOfDepartmentColumn;

    @FXML
    private TableColumn<DepartmentStatistic,Department> departmentNameColumn;

    @FXML
    private TableColumn<DepartmentStatistic,Integer> numberReceiveReportsOfDepartmentColumn;

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
    public void reportForDepartment(){
        System.out.println("reportForDepartment");
    }

    @FXML
    public void reportForAllDepartments(){
        System.out.println("reportForAllDepartments");
    }

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
        departmentNameColumn.setCellValueFactory(new PropertyValueFactory<DepartmentStatistic,Department>("department"));
        numberReceiveReportsOfDepartmentColumn.setCellValueFactory(new PropertyValueFactory<DepartmentStatistic,Integer>("completeReports"));
        numberReportsOfDepartmentColumn.setCellValueFactory(new PropertyValueFactory<DepartmentStatistic,Integer>("allReports"));
        List<DepartmentStatistic> departmentStatisticsList = new ArrayList<>();
        for(Department department : listOfDepartment){
            departmentStatisticsList.add(new DepartmentStatistic(department,howReportsCome.get(department),department.getReports().size()));
        }
        ObservableList<DepartmentStatistic> departmentStatisticObservableList = FXCollections.observableArrayList(departmentStatisticsList);
        departmentTableView.setItems(departmentStatisticObservableList);
    }

    @FXML
    public void loadDataDepartmentReport(){
        Integer years = yearsChoiceBox.getValue();
        Integer month = DateUtil.getMonthByName(monthsChoiceBox.getValue());
        System.out.println(years);
        System.out.println(month);
    }

}
