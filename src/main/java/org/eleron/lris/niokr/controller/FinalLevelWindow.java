package org.eleron.lris.niokr.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.apache.log4j.Logger;
import org.eleron.lris.niokr.bussines.LoadScenes;
import org.eleron.lris.niokr.dao.DateOfReportsDAO;
import org.eleron.lris.niokr.dao.DateOfReportsDAOImplements;
import org.eleron.lris.niokr.dao.DepartmentDAO;
import org.eleron.lris.niokr.dao.DepartmentDAOImplements;
import org.eleron.lris.niokr.model.Department;
import org.eleron.lris.niokr.model.Report;
import org.eleron.lris.niokr.util.DateUtil;

import java.util.*;

public class FinalLevelWindow {

    private final static Logger log = Logger.getLogger(FinalLevelWindow.class);

    private List<Integer> datesOfReport;

    private List<String> monthsOfReport;

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
    public void backToDepartment(){}

    @FXML
    public void settingsOpen(){

        LoadScenes.load("view/ReviewMenu.fxml");
    }

    @FXML
    public void reportForDepartment(){}

    @FXML
    public void reportForAllDepartments(){}

    public void initialize(){
        DateOfReportsDAO dateOfReportsDAO = new DateOfReportsDAOImplements();
        datesOfReport = dateOfReportsDAO.getDates();
        ObservableList<Integer> dateData= FXCollections.observableArrayList(datesOfReport);
        yearsChoiceBox.setItems(dateData);
        yearsChoiceBox.setValue(Calendar.getInstance().get(Calendar.YEAR));

        monthsOfReport = new ArrayList<>();
        Map<Integer,String> mon = DateUtil.getMonths();
        for(Integer key : mon.keySet()){
            if(key < 13) monthsOfReport.add(mon.get(key));
        }
        ObservableList<String> monthsData = FXCollections.observableArrayList(monthsOfReport);
        monthsChoiceBox.setItems(monthsData);
        monthsChoiceBox.setValue(DateUtil.getCurrentMonth());

        DepartmentDAO departmentDAO = new DepartmentDAOImplements();
        Integer departmentsGood = 0;
        for(Department department : departmentDAO.listDepartment()){
            List<Report> reports = department.getReports();
            if(reports.isEmpty()){
                departmentsGood += 1;
            } else {
                int tmp = 0;
                for(Report report : reports){
                    tmp = report.getStatus()==2?tmp:tmp+1;
                }
                if(tmp == reports.size()){
                    departmentsGood += 1;
                }
            }
        }

        allDepartments.setText("Все (" + departmentDAO.listDepartment().size() + ")");
        receiveDepartments.setText("Прислали (" + departmentsGood +")");
        notReceiveDepartments.setText("НЕ прислали (" + (departmentDAO.listDepartment().size() - departmentsGood) +")");
    }
}
