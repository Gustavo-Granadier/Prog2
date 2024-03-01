package app;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import modelo.Operador;
import util.DaoOperador;

public class LoginController {

    @FXML
    private TextField campoLogin;

    @FXML
    private TextField campoSenha;

    @FXML
    private void autenticar() throws IOException {
        
            App.setRoot("menu");
        

    }

}