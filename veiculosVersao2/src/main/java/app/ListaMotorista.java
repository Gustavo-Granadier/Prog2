/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Motorista;
import util.Dao;
import javafx.fxml.Initializable;
import util.Dao;

public class ListaMotorista implements Initializable{
    
    @FXML
    private TableView<Motorista> tabela;
    
    @FXML
    private TableColumn<Motorista, String> colunaNome;
    
    @FXML
    private TableColumn<Motorista, String> colunaEndereco;
    
    @FXML
    private TableColumn<Motorista, String> colunaSetor;
    
    @FXML
    private TableColumn<Motorista, String> colunaCategoria;
    
    @FXML
    private TableColumn<Motorista, Long> colunaCnh;

    private List<Motorista> motorista;
    private ObservableList<Motorista> listarMotorista;
    
    
    public void initialize(URL location, ResourceBundle resources){
        Dao<Motorista> daoMotorista = new Dao(Motorista.class);
        
        motorista = daoMotorista.listarTodos();
        listarMotorista = FXCollections.observableArrayList(motorista);
        
        colunaNome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        colunaEndereco.setCellValueFactory(
                new PropertyValueFactory<>("endereco"));
        colunaSetor.setCellValueFactory(
                new PropertyValueFactory<>("setor"));
        colunaCategoria.setCellValueFactory(
                new PropertyValueFactory<>("categoria"));
        colunaCnh.setCellValueFactory(
                new PropertyValueFactory<>("cnh"));
        tabela.setItems(listarMotorista);
        
        tabela.sort();
        
    }
    
    @FXML
    private void voltarAoMenu() throws IOException{
        App.setRoot("menu");
    }
    
}





