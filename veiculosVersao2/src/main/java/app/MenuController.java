package app;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;


public class MenuController {
    
   @FXML
    private void Login() throws IOException {
        App.setRoot("login");
    }
    
    
    @FXML
    private void Devolucao() throws IOException {
        App.setRoot("devolucao");
    }
   
    @FXML
    private void Busca() throws IOException {
        App.setRoot("buscar");
    }
    
    @FXML
    private void EditarMotorista() throws IOException {
        App.setRoot("editarMotorista");
    }
    
    @FXML
    private void editarOperador() throws IOException {
        App.setRoot("editarOperador");
    }
    
    @FXML
    private void editarVeiculo() throws IOException {
        App.setRoot("editarVeiculo");
    }
    
    @FXML
    private void NovoMotorista() throws IOException {
        App.setRoot("novoMotorista");
    }
    
    @FXML
    private void cadastrarOperador() throws IOException {
        App.setRoot("novoOperador");
    }
    
    @FXML
    private void cadastrarVeiculo() throws IOException {
        App.setRoot("novoVeiculo");
    }
    
    @FXML
    private void excluirMotorista() throws IOException{
        App.setRoot("excluirMotorista");
    }
    
    @FXML
    private void excluirOperador() throws IOException {
        App.setRoot("excluirOperador");
    }
    
    @FXML
    private void excluirVeiculo() throws IOException {
        App.setRoot("excluirVeiculo");
    }
    
    @FXML
    private void listarMotorista() throws IOException {
        App.setRoot("listarMotorista");
    }
    
    @FXML
    private void listarOperador() throws IOException {
        App.setRoot("listarOperador");
    }
    
    @FXML
    private void listarVeiculo() throws IOException {
        App.setRoot("listarVeiculo");
    }
    
    
    @FXML
    private void retirarVeiculo() throws IOException{
        App.setRoot("retiradaVeiculo");
    }
    
    @FXML
    private void listarUso() throws IOException{
        App.setRoot("listarUso");
    }
    
    @FXML
    private void sair(){
        Platform.exit();
        System.exit(0);
    }
    
}
