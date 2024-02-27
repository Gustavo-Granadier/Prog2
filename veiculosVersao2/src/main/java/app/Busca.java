package app;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import modelo.UsoVeiculo;
import util.Dao;
import modelo.Motorista;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;


public class Busca {
    
    @FXML
    private ComboBox<UsoVeiculo> boxUsoVeiculo;
    
    private List<UsoVeiculo> usoVeiculo;
    private ObservableList<UsoVeiculo> listUsoVeiculo;
    
    
    @FXML
    private Text motorista;
    
    @FXML
    private void initialize() {
        Dao<UsoVeiculo> daoUsoVeiculo = new Dao(UsoVeiculo.class);
        
        usoVeiculo = daoUsoVeiculo.listarTodos();
        listUsoVeiculo = FXCollections.observableArrayList(usoVeiculo);
        boxUsoVeiculo.getItems().addAll(listUsoVeiculo);  
    }
    
    @FXML
    private void mostrarMotorista() {
        UsoVeiculo usoVeiculoSelecionado = boxUsoVeiculo.getValue();
        if (usoVeiculoSelecionado != null) {
            Motorista motoristaDoUso = usoVeiculoSelecionado.getMotorista();
            if (motoristaDoUso != null) {
                String nomeMotorista = motoristaDoUso.getNome();
                motorista.setText(nomeMotorista);
            } else {
                Alert alerta = new Alert(AlertType.WARNING);
                alerta.setTitle("Aviso");
                alerta.setHeaderText(null);
                alerta.setContentText("UsoVeiculo selecionado não possui um motorista associado.");
                alerta.showAndWait();
            }
        } else {
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Aviso");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, selecione um UsoVeiculo");
            alerta.showAndWait();
        }
    }
    
    @FXML
    private void voltarAoMenu() throws IOException{
        App.setRoot("menu");
    }
    
}