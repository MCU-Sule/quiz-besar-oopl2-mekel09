package com.example.quiz02_1872005.controller;

import com.example.quiz02_1872005.MainApplication;
import com.example.quiz02_1872005.dao.AnggotaDaoImpl;
import com.example.quiz02_1872005.dao.BukuDaoImpl;
import com.example.quiz02_1872005.dao.PeminjamanDaoImpl;
import com.example.quiz02_1872005.entitas.AnggotaEntity;
import com.example.quiz02_1872005.entitas.BukuEntity;
import com.example.quiz02_1872005.entitas.PeminjamanEntity;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

/**Michael Sebastian Gunadi-1872005*/
public class Controller1 implements Initializable {

    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnReset;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<BukuEntity,String> col1;
    @FXML
    private TableColumn<AnggotaEntity,String> col2;
    @FXML
    private TableColumn<BukuEntity,String> col3;
    @FXML
    private TableColumn<PeminjamanEntity,Date> col4;
    @FXML
    private TableColumn<PeminjamanEntity,Date> col5;
    @FXML
    private TextField id;
    @FXML
    private ComboBox bahasa;
    @FXML
    private ComboBox<AnggotaEntity> member;
    @FXML
    private ComboBox<BukuEntity> buku;
    @FXML
    private DatePicker pinjam;
    @FXML
    private DatePicker balik;
    @FXML
    private ObservableList<BukuEntity> blist;
    @FXML
    private ObservableList<AnggotaEntity> alist;
    @FXML
    private ObservableList<PeminjamanEntity> plist;

    private AnggotaDaoImpl anggotaDao;
    private PeminjamanDaoImpl peminjamanDao;
    private BukuDaoImpl bukuDao;
    public PeminjamanEntity updatepinjam;

    public PeminjamanEntity getSelectedRow(){
        return this.updatepinjam = (PeminjamanEntity) tableView.getSelectionModel().getSelectedItem();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        peminjamanDao = new PeminjamanDaoImpl();
        anggotaDao = new AnggotaDaoImpl();
        bukuDao = new BukuDaoImpl();
        blist = FXCollections.observableArrayList();
        alist = FXCollections.observableArrayList();
        plist = FXCollections.observableArrayList();
        blist.addAll(bukuDao.fetchall());
        alist.addAll(anggotaDao.fetchall());
        plist.addAll(peminjamanDao.fetchall());
        member.setItems(alist);
        buku.setItems(blist);
        tableView.setItems(plist);
    }

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void filemember(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation((MainApplication.class.getResource("MemberManagement.fxml")));
        Parent root = loader.load();
        Controller2 dua = loader.getController();
        dua.setMainApplication(this);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }

    public void filebuku(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation((MainApplication.class.getResource("bookManagement.fxml")));
        Parent root = loader.load();
        Controller3 tiga = loader.getController();
        tiga.setMainApplication(this);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }

    public void save(ActionEvent actionEvent) {
    }

    public void update(ActionEvent actionEvent) {
    }

    private void resetItem(){
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void delete(Object object) {
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setContentText("Yakin mau dihapus?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK){
            if (object instanceof PeminjamanEntity){
                if (peminjamanDao.deleteData(getSelectedRow())==1){
                    plist.clear();
                    plist.addAll(peminjamanDao.fetchall());
                    resetItem();
                }
            }
        }
    }

    public void reset(ActionEvent actionEvent) {

    }
}
