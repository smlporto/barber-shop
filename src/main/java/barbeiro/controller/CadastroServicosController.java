package barbeiro.controller;

import barbeiro.dao.ServicoDao;
import barbeiro.model.Servico;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class CadastroServicosController implements Initializable {
    private ServicoDao servicoDao = new ServicoDao();
    public static int ALTERAR = 0;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldPreco;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       carregarDados();
    }    
    private void carregarDados() {
        if (ALTERAR == 1) {
            Servico Servico = ServicosController.servicoSelecionado;
            textFieldNome.setText(Servico.getNome());
            textFieldPreco.setText(String.valueOf(Servico.getPreco()));
        }
    }
    
    @FXML
    private void salvar(ActionEvent event) {
        if (textFieldNome.getText().trim().isEmpty() || textFieldPreco.getText().trim().isEmpty()){
            if (textFieldNome.getText().trim().isEmpty()){
                textFieldNome.requestFocus();
                JOptionPane.showMessageDialog(null,"O campo nome é obrigatório");
            }else {
                textFieldPreco.requestFocus();
                JOptionPane.showMessageDialog(null,"O campo preço é obrigatório");
            }

        }else{
            if (ALTERAR == 1) {
                ServicosController.servicoSelecionado.setNome(textFieldNome.getText());
                ServicosController.servicoSelecionado.setPreco(Double.parseDouble(textFieldPreco.getText()));
                servicoDao.salvar(ServicosController.servicoSelecionado);

                Stage thisStage = (Stage) btnSalvar.getScene().getWindow();
                thisStage.close();
                JOptionPane.showMessageDialog(null,"Serviço atualizado com sucesso!");

            }else{
                ServicosController.novoServico.setNome(textFieldNome.getText());
                ServicosController.novoServico.setPreco(Double.parseDouble(textFieldPreco.getText()));
                servicoDao.salvar(ServicosController.novoServico);

                Stage thisStage = (Stage) btnSalvar.getScene().getWindow();
                thisStage.close();
                JOptionPane.showMessageDialog(null,"Serviço cadastrado com sucesso!");

            }
        }

    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage thisStage = (Stage) btnCancelar.getScene().getWindow();
        thisStage.close();
    }
    
}
