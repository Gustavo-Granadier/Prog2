package app;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;


public class MenuController {
    
    @FXML
    private void retiradaVeiculo() throws IOException {
        App.setRoot("retiradaVeiculo");
    }
    
    @FXML
    private void menuExcluirMotorista() throws IOException {
        App.setRoot("excluirMotorista");
    }
    
    @FXML
    private void cadastrarMotorista() throws IOException {
        App.setRoot("novoMotorista");
    }
    
    @FXML
    private void sair(){
        Platform.exit();
        System.exit(0);
    }
    
}
