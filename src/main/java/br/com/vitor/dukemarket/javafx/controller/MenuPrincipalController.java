package br.com.vitor.dukemarket.javafx.controller;

import br.com.vitor.dukemarket.javafx.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vitormtoz
 */
public class MenuPrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void btnProdutosClick() throws IOException{
        
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("scrProdutos.fxml"));
            
            Scene scene = new Scene(fxmlLoader.load(),900,400);
            Stage stage = new Stage();
            
            stage.setTitle("CRUD Produtos");
            stage.setScene(scene);
            stage.show();
            
        }catch(IOException e){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to creat new Window scrProdutos.",e);
        }
    }
    
    
     public void btnClientesClick() throws IOException{
        
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("scrClientes.fxml"));
            
            Scene scene = new Scene(fxmlLoader.load(),900,400);
            Stage stage = new Stage();
            
            stage.setTitle("CRUD Clientes");
            stage.setScene(scene);
            stage.show();
            
        }catch(IOException e){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to creat new Window scrClientes.",e);
        }
    }
    
}
