package app;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modelo.Operador;
import util.Dao;
import javax.persistence.NoResultException;


public class LoginController {

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private void login() throws IOException {
        String usuario = campoUsuario.getText().trim();
        String senha = campoSenha.getText().trim();
    
        Dao<Operador> daoOperador = new Dao<>(Operador.class);
        Operador operador = null;
        try {
            operador = daoOperador.buscarOperadorPorLogin(usuario);
        } catch (NoResultException e) {
            exibirAlertaErro("Erro de autenticação", "Usuário ou senha incorretos.");
            return;
        }
    
        if (operador != null && operador.getSenha().equals(senha)) {
            App.setRoot("menu");
        } else {
            exibirAlertaErro("Erro de autenticação", "Usuário ou senha incorretos.");
        }
    }

    private void exibirAlertaErro(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

   
}