package com.example.quiz02_1872005.controller;

import com.example.quiz02_1872005.dao.BukuDaoImpl;
import com.example.quiz02_1872005.entitas.BukuEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**Michael Sebastian Gunadi-1872005*/
public class Controller3 implements Initializable {
    public TableView<BukuEntity> tableView;
    public TableColumn<BukuEntity,String> col1;
    public TableColumn<BukuEntity,String> col2;
    public TableColumn<BukuEntity,String> col3;
    public TableColumn<BukuEntity,String> col4;
    public TableColumn<BukuEntity,String > col5;
    public TableColumn<BukuEntity,String> col6;
    @FXML
    private TextField id;
    @FXML
    private TextField judul;
    @FXML
    private TextField tahun;
    @FXML
    private TextField publisher;
    @FXML
    private TextField pengarang;
    @FXML
    private TextField tipe;
    @FXML
    private BukuDaoImpl bukuDao;
    @FXML
    private ObservableList<BukuEntity> bukus;
    @FXML
    private Controller1 controller1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bukuDao = new BukuDaoImpl();
        bukus = FXCollections.observableArrayList();
        bukus.addAll(bukuDao.fetchall());
        tableView.setItems(bukus);
        col1.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        col2.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getJudul())));
        col3.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getPenerbit())));
        col4.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getTahunTerbit())));
        col5.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getPengarang())));
        col6.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getJenisBuku())));
    }

    public void save(ActionEvent actionEvent) {
        if (id.getText().isEmpty()||judul.getText().isEmpty()||tahun.getText().isEmpty()||publisher.getText().isEmpty()||pengarang.getText().isEmpty()||tipe.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Tolong isi yang benar");
            alert.showAndWait();
        }else{
            BukuEntity bukuEntity = new BukuEntity();
            bukuEntity.setId(Integer.parseInt(id.getText()));
            bukuEntity.setJudul(judul.getText().trim());
            bukuEntity.setPenerbit(publisher.getText().trim());
            bukuEntity.setTahunTerbit(tahun.getText().trim());
            bukuEntity.setPengarang(pengarang.getText().trim());
            bukuEntity.setJenisBuku(tipe.getText().trim());
            bukus.add(bukuEntity);
            if (bukuDao.addData(bukuEntity) == 1) {
                bukus.clear();
                bukus.addAll(bukuDao.fetchall());
            }
        }
    }
    public void setMainApplication(Controller1 controller1) {
        this.controller1 = controller1;
    }
}
