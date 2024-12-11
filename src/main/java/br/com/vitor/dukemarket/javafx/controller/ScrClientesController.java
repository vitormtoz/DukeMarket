/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.vitor.dukemarket.javafx.controller;

import br.com.vitor.dukemarket.javafx.DAO.ClienteDAO;
import br.com.vitor.dukemarket.javafx.model.Cliente;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author qualifica
 */
public class ScrClientesController implements Initializable {

    boolean flagNovo;
    
    @FXML
    TableView <Cliente> tblCliente;

    @FXML
    TableColumn <Cliente, String> tcoNome;
    
    @FXML
    TableColumn <Cliente, String> tcoTelefone;
    
    @FXML
    TableColumn <Cliente, String> tcoEmail;
    
    @FXML
    TableColumn <Cliente, String> tcoEndereco;
    
    @FXML
    TableColumn <Cliente, String> tcoCidade;
    
    @FXML
    TableColumn <Cliente, String> tcoUf;
    
    @FXML
    TableColumn <Cliente, String> tcoCep;
    
    @FXML
    TableColumn <Cliente, Calendar> tcoDataNascimento;
    
    @FXML
    TextField txtNome;
    
    @FXML
    TextField txtTelefone;
    
    @FXML
    TextField txtEmail;
    
    @FXML
    TextField txtEndereco;
    
    @FXML
    TextField txtCidade;
    
    @FXML
    TextField txtUf;
    
    @FXML
    TextField txtCep;
        
    @FXML
    DatePicker dtpDataNascimento;
    
    ClienteDAO cDAO;
    Cliente cClicked;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        bindColumns();
        
        carregaDados();
    } 
    
    
    private void bindColumns(){
        
        //Vincula a coluna tcoId com o atributo id da Classe Produto
        

         
        tcoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        //Adiciona uma configuração extra no alinhamento à direita
        tcoNome.setStyle("-fx-alignment: CENTER_RIGHT;");
        
        tcoTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tcoTelefone.setStyle("-fx-alignment: CENTER_RIGHT;");
        
        tcoEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        tcoEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        
        tcoCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        tcoCidade.setStyle("-fx-alignment: CENTER_RIGHT;");
        
        tcoUf.setCellValueFactory(new PropertyValueFactory<>("uf"));
        tcoUf.setStyle("-fx-alignment: CENTER_RIGHT;");
        
        tcoCep.setCellValueFactory(new PropertyValueFactory<>("cep"));
        tcoCep.setStyle("-fx-alignment: CENTER_RIGHT;");
        
        tcoDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        tcoDataNascimento.setStyle("-fx-alignment: CENTER_RIGHT;");

        }
    
    public void carregaDados(){
        this.cDAO = new ClienteDAO();
        
        //Carrega view tblProduto com todos os resultados de produto
        this.tblCliente.getItems().setAll(cDAO.getResults());
    }
    
    @FXML
    private void btnNovoClienteClick(){
        
        txtNome.setText("");
        txtNome.requestFocus();
        
        txtTelefone.setText("");
        txtEmail.setText("");
        txtEndereco.setText("");
        txtCidade.setText("");
        txtUf.setText("");
        txtCep.setText("");
        dtpDataNascimento.setValue(null);
        
        this.flagNovo = true;
    }
    
    @FXML
    public void tblProdutoOnMouseClicked(){
        this.cClicked = tblCliente.getSelectionModel().getSelectedItem();
        
        if(cClicked !=null){
            txtNome.setText(String.valueOf(cClicked.getNome()));
            
            txtTelefone.setText(cClicked.getTelefone());
            txtEmail.setText(cClicked.getEmail());
            
            txtEndereco.setText(cClicked.getEndereco());
            txtCidade.setText(cClicked.getCidade());
            txtUf.setText(cClicked.getUf());
            txtCep.setText(cClicked.getCep());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            formatter = formatter.withLocale(Locale.US);
            LocalDate date = LocalDate.parse(cClicked.getDataNascimento(), formatter);
            
            dtpDataNascimento.setValue(date);
            dtpDataNascimento.setEditable(false);
            
            this.flagNovo = false;
            
        }
    }
    
    @FXML
    @SuppressWarnings("empty-statement")
    public void btnSalvarClienteClick(){
        Cliente c = new Cliente();
        
        c.setNome(txtNome.getText());
        c.setEmail(txtEmail.getText());
        c.setEndereco(txtEndereco.getText());
        c.setCidade(txtCidade.getText());
        c.setUf(txtUf.getText());
        c.setCep(txtCep.getText());
        c.setDataNascimento(dtpDataNascimento.getValue());
        
        if(this.flagNovo){
            
            this.cDAO.create(c);
            
        }else{
            this.cDAO.update(c);
        }
        
        this.carregaDados();
        
        txtNome.setText("");
        txtNome.requestFocus();
        
        txtTelefone.setText("");
        txtEmail.setText("");
        txtEndereco.setText("");
        txtCidade.setText("");
        txtUf.setText("");
        txtCep.setText("");
        dtpDataNascimento.setValue(null);
        
        this.flagNovo = false;
    }
    
    @FXML
    private void btnExcluirClienteClick() {
        
        if (this.cClicked != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirma exclusão do Cliente?");
            alert.setHeaderText(cClicked.getNome());
            alert.setContentText("Tem certeza que deseja excluir?");
            
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                this.cDAO.delete(this.cClicked.getId());
                
                this.tblCliente.getItems().remove(this.cClicked);
                
                txtNome.setText("");
                txtNome.requestFocus();

                txtTelefone.setText("");
                txtEmail.setText("");
                txtEndereco.setText("");
                txtCidade.setText("");
                txtUf.setText("");
                txtCep.setText("");
                dtpDataNascimento.setValue(null);
            }
            this.tblCliente.getSelectionModel().clearSelection();
            cClicked = null;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ops!");
            alert.setHeaderText("Atenção");
            alert.setContentText("Você deve selecionar um registro antes de excluí-lo!");
            
            alert.showAndWait();
        }
    }
}
