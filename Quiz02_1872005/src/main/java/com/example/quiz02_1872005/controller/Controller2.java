package com.example.quiz02_1872005.controller;

import com.example.quiz02_1872005.dao.AnggotaDaoImpl;
import com.example.quiz02_1872005.entitas.AnggotaEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**Michael Sebastian Gunadi-1872005*/
public class Controller2 implements Initializable {
    public DatePicker lahir;
    public DatePicker daftar;
    @FXML
    private TableView<AnggotaEntity> tableView;
    @FXML
    private TableColumn<AnggotaEntity,String> col1;
    @FXML
    private TableColumn<AnggotaEntity,String> col2;
    @FXML
    private TableColumn<AnggotaEntity,String> col3;
    @FXML
    private TableColumn<AnggotaEntity, Date> col4;
    @FXML
    private TableColumn<AnggotaEntity,Date> col5;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private Date ultah;
    @FXML
    private Date masuk;
    private AnggotaDaoImpl anggotaDao;
    private Controller1 controller1;
    private ObservableList<AnggotaEntity> anggota;

    public void save(ActionEvent actionEvent) {
        if(id.getText().isEmpty()||name.getText().isEmpty()||phone.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Tolong isi yang benar");
            alert.showAndWait();
        }else{
            AnggotaEntity anggotaEntity = new AnggotaEntity();
            anggotaEntity.setId(Integer.parseInt(id.getText()));
            anggotaEntity.setNama(name.getText().trim());
            anggotaEntity.setNotelpon(phone.getText().trim());
            anggotaEntity.getTglLahir(ultah.getTime());
            anggotaEntity.setTglMasuk(masuk.getTime());
            anggota.add(anggotaEntity);
            if(anggotaDao.addData(anggotaEntity)==1){
                anggota.clear();
                anggota.addAll(anggotaDao.fetchall());
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anggotaDao = new AnggotaDaoImpl();
        anggota = FXCollections.observableArrayList();
        anggota.addAll(anggotaDao.fetchall());
        tableView.setItems(anggota);
        col1.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        col2.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getNama())));
        col3.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getNotelpon())));
//        col4.setCellValueFactory(data-> {
//            SimpleStringProperty property = new SimpleStringProperty();
//            DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
//            property.setValue(dateformat.format(data.getValue().getTglLahir()));
//            return property;
//        });
//        col5.setCellValueFactory(data-> {
//            SimpleStringProperty property = new SimpleStringProperty();
//            DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
//            property.setValue(dateformat.format(data.getValue().getTglMasuk()));
//            return property;
//        });
    }

    public void setMainApplication(Controller1 controller1) {
        this.controller1 = controller1;
    }
}