/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;


import Classes.Product;
import Classes.Product;
import Server.Server;
import Classes.User;
import Classes.User;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author gbouz_000
 */
public class ServerWindow extends Application implements EventHandler<ActionEvent> {
    public Button registerMenu = new Button("Register new product");
    public Button listMenu = new Button("List all products");
    public Button updateQtdMenu = new Button("Update a product");
    public Button generateMenu = new Button("Reports");
    public Button exit = new Button("Exit");
    public Label mainLabel = new Label(" CARRO&4\nSupermarket");
    
    public TextField txtName = new TextField();
    public TextField txtPrice = new TextField();
    public DatePicker txtValidate = new DatePicker();
    public TextField txtProvider = new TextField();
    public Button register = new Button("Register");
    public Button backRegister = new Button("Back");
    
    public TableView products = new TableView();
    public Button backList = new Button("Back");
    
    public TextField newQtd = new TextField();
    public Button update = new Button("Update");
    public Button backUpdate = new Button("Back");
    
    public ComboBox txtMonth = new ComboBox();
    public TextField txtYear = new TextField();
    public DatePicker dp = new DatePicker();
    public Button myPDF = new Button("Generate!"); //myPDF = month / year PDF
    public Button datePDF = new Button("Generate!");
    public Button backGen = new Button("Back");
    public Label lblUpdate = new Label("To update an item, please select one from the table above!!");
    public Label lblGen1 = new Label("Generate purchase reports by month and year.");
    public Label lblGen2 = new Label("Generate purchase reports by date");
    
    
    
    public Server sv = new Server();
    
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(4444);
        
        Runnable wait = () -> {
            try{
                while(true){
                    Socket socket = ss.accept();
                    new Server(socket).start();
                }
            }catch(IOException e){}
            
        };
        Thread t1 = new Thread(wait);
        t1.start();
        
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("CARRO&4 - Server Application");
//Main menu
        registerMenu.setMaxSize(200, 50);
        registerMenu.setTranslateY(-100);
        registerMenu.setOnAction(this);
        listMenu.setMaxSize(200, 50);
        listMenu.setTranslateY(-40);
        listMenu.setOnAction(this);
        updateQtdMenu.setMaxSize(200, 50);
        updateQtdMenu.setTranslateY(20);
        updateQtdMenu.setOnAction(this);
        generateMenu.setMaxSize(200, 50);
        generateMenu.setTranslateY(80);
        generateMenu.setOnAction(this);
        exit.setMaxSize(70, 50);
        exit.setTranslateY(210);
        exit.setOnAction(this);
        mainLabel.setMaxSize(300, 100);
        mainLabel.setTranslateY(-190);
        mainLabel.setTranslateX(50);
        mainLabel.setFont(new Font("Cooper Black", 30));
        mainLabel.setTextFill(Color.web("#0000FF"));
        
//Register Menu
        txtName.setVisible(false);
        txtName.setMaxSize(300, 40);
        txtName.setTranslateY(-100);
        txtName.setPromptText("Product Name");
        txtPrice.setVisible(false);
        txtPrice.setMaxSize(300, 40);
        txtPrice.setTranslateY(-40);
        txtPrice.setPromptText("Product price");
        txtValidate.setVisible(false);
        txtValidate.setMaxSize(300, 40);
        txtValidate.setTranslateY(20);
        txtValidate.setPromptText("Expiration date");
        txtProvider.setVisible(false);
        txtProvider.setMaxSize(300, 40);
        txtProvider.setTranslateY(80);
        txtProvider.setPromptText("Product suppier");
        register.setVisible(false);
        register.setMaxSize(100, 40);
        register.setTranslateX(100);
        register.setTranslateY(140);
        register.setOnAction(this);
        backRegister.setVisible(false);
        backRegister.setMaxSize(100, 40);
        backRegister.setTranslateX(-100);
        backRegister.setTranslateY(140);
        backRegister.setOnAction(this);

//List menu        
        products.setVisible(false);
        products.setEditable(false);
        products.setMaxSize(600, 400);
        products.setTranslateY(-45);
        
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setMinWidth(200);
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn validate = new TableColumn("Validate date");
        validate.setCellValueFactory(new PropertyValueFactory<>("validate"));
        TableColumn provider = new TableColumn("Provider");
        provider.setCellValueFactory(new PropertyValueFactory<>("provider"));
        provider.setMinWidth(150);
        TableColumn qtd = new TableColumn("Qtd");
        qtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        products.getColumns().addAll(name, price, validate, provider, qtd);
        backList.setVisible(false);
        backList.setTranslateY(220);
        backList.setMaxSize(200, 40);
        backList.setOnAction(this);
        
//Update Menu
        newQtd.setVisible(false);
        newQtd.setMaxSize(150, 40);
        newQtd.setTranslateY(220);
        newQtd.setTranslateX(-220);
        newQtd.setPromptText("How many?");
        update.setVisible(false);
        update.setMaxSize(100, 40);
        update.setTranslateY(220);
        update.setTranslateX(-50);
        update.setOnAction(this);
        backUpdate.setVisible(false);
        backUpdate.setMaxSize(50, 40);
        backUpdate.setTranslateX(220);
        backUpdate.setTranslateY(220);
        backUpdate.setOnAction(this);
        lblUpdate.setVisible(false);
        lblUpdate.setMaxSize(500, 50);
        lblUpdate.setTranslateY(170);
        lblUpdate.setTranslateX(0);
        
//reports menu
        dp.setVisible(false);
        dp.setMaxSize(300, 40);
        dp.setTranslateY(-140);
        dp.setPromptText("Date");
        datePDF.setVisible(false);
        datePDF.setMaxSize(100, 40);
        datePDF.setTranslateY(-80);
        datePDF.setOnAction(this);
        txtMonth.setVisible(false);
        txtMonth.setMaxSize(180, 40);
        txtMonth.setTranslateY(40);
        txtMonth.setTranslateX(-60);
        txtMonth.getItems().addAll("01", "02", "03", "04", "05", "06",
                "07", "08", "09", "10", "11", "12");
        txtMonth.setPromptText("Month");
        txtYear.setVisible(false);
        txtYear.setMaxSize(100, 40);
        txtYear.setTranslateY(40);
        txtYear.setTranslateX(100);
        txtYear.setPromptText("Year");
        myPDF.setVisible(false);
        myPDF.setMaxSize(100, 40);
        myPDF.setTranslateY(100);
        myPDF.setOnAction(this);
        backGen.setVisible(false);
        backGen.setMaxSize(100, 40);
        backGen.setTranslateY(200);
        backGen.setTranslateX(-230);
        backGen.setOnAction(this);
        lblGen1.setVisible(false);
        lblGen1.setTranslateY(-10);
        lblGen1.setMaxSize(400, 40);
        lblGen1.setTranslateX(30);
        lblGen2.setVisible(false);
        lblGen2.setTranslateY(-190);
        lblGen2.setMaxSize(400, 40);
        lblGen2.setTranslateX(30);
        
        StackPane layout = new StackPane();
        layout.getChildren().addAll(registerMenu, listMenu, updateQtdMenu, generateMenu, exit,
                txtName, txtProvider, txtValidate, txtPrice, register, backRegister,
                products, backList, newQtd, update, backUpdate, backGen, datePDF,
                myPDF, txtMonth, txtYear, dp, mainLabel, lblUpdate, lblGen1, lblGen2);
        
        Scene scene = new Scene(layout, 600, 500);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource() == registerMenu){
            setMainMenuInvisible();
            setRegisterMenuVisible();
        } else if(event.getSource() == backRegister){
            setRegisterMenuInvisible();
            setMainMenuVisible();
        } else if(event.getSource() == listMenu){
            setMainMenuInvisible();
            setListVisible();
            try {
                products.setItems(sv.searchAllProducts());
            } catch (Exception ex) {}
        } else if(event.getSource() == backList){
            setListInvisible();
            setMainMenuVisible();
        } else if(event.getSource() == updateQtdMenu){
            setMainMenuInvisible();
            setUpdateMenuVisible();
            try {
                products.setItems(sv.searchAllProducts());
            } catch (Exception ex) {}
        } else if(event.getSource() == backUpdate) {
            setUpdateMenuInvisible();
            setMainMenuVisible();
        } else if(event.getSource() == generateMenu){
            setMainMenuInvisible();
            setReportsMenuVisible();
        } else if(event.getSource() == backGen) {
            setReportsMenuInvisible();
            setMainMenuVisible();
        } else if(event.getSource() == register){ //cadastro de produto
            try {
                sv.addNewProduct(new Product(txtName.getText(), txtPrice.getText(),
                        txtValidate.getValue().toString(), txtProvider.getText()));
            } catch (Exception ex) {
            } finally {
                cleanRegisterMenuFields();
            }
        } else if(event.getSource() == update){
            
            Product p = (Product) products.getSelectionModel().getSelectedItem();
            try {
                int qtd = Integer.parseInt(p.getQtd()) + Integer.parseInt(newQtd.getText());
                ObservableList<User> list;
                
                sv.updateProductQtd(p.getName(), qtd);
                list = sv.sendEmailToNotificatedUsers(p.getName() + " - U$" + p.getPrice()
                    + " - " + qtd + " units");
                products.setItems(sv.searchAllProducts());
                
                for(User u: list){
                    sv.updateNotificationField(u.getId(), false);
                }
                newQtd.setText("");
            } catch (Exception ex) {
                Logger.getLogger(ServerWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(event.getSource() == datePDF){
            
            try {
                sv.generateDayReport(dp.getValue().toString());
            } catch (Exception ex) {
                Logger.getLogger(ServerWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if(event.getSource() == myPDF){
            try {
                sv.generateMonthReport(txtMonth.getValue().toString(), txtYear.getText());
            } catch (Exception ex) {
                Logger.getLogger(ServerWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(event.getSource() == exit){
            System.exit(0);
        }
    }
    
    public void setMainMenuInvisible(){
        registerMenu.setVisible(false);
        listMenu.setVisible(false);
        updateQtdMenu.setVisible(false);
        generateMenu.setVisible(false);
        exit.setVisible(false);
        mainLabel.setVisible(false);
    }
    public void setMainMenuVisible(){
        registerMenu.setVisible(true);
        listMenu.setVisible(true);
        updateQtdMenu.setVisible(true);
        generateMenu.setVisible(true);
        exit.setVisible(true);
        mainLabel.setVisible(true);
    }
    public void setRegisterMenuInvisible(){
        txtName.setVisible(false);
        txtPrice.setVisible(false);
        txtValidate.setVisible(false);
        txtProvider.setVisible(false);
        register.setVisible(false);
        backRegister.setVisible(false);
    }
    public void setRegisterMenuVisible(){
        txtName.setVisible(true);
        txtPrice.setVisible(true);
        txtValidate.setVisible(true);
        txtProvider.setVisible(true);
        register.setVisible(true);
        backRegister.setVisible(true); 
    }
    public void setListInvisible(){
        products.setVisible(false);
        backList.setVisible(false);
    }
    public void setListVisible(){
        products.setVisible(true);
        backList.setVisible(true);
    }
    public void setUpdateMenuInvisible(){
        products.setVisible(false);
        newQtd.setVisible(false);
        update.setVisible(false);
        backUpdate.setVisible(false);
        lblUpdate.setVisible(false);
    }
    public void setUpdateMenuVisible(){
        products.setVisible(true);
        newQtd.setVisible(true);
        update.setVisible(true);
        backUpdate.setVisible(true);
        lblUpdate.setVisible(true);
    }
    public void setReportsMenuInvisible(){
       dp.setVisible(false);
       backGen.setVisible(false);
       txtMonth.setVisible(false);
       txtYear.setVisible(false);
       myPDF.setVisible(false);
       datePDF.setVisible(false);
       lblGen1.setVisible(false);
       lblGen2.setVisible(false);
    }
    public void setReportsMenuVisible(){
       dp.setVisible(true);
       backGen.setVisible(true);
       txtMonth.setVisible(true);
       txtYear.setVisible(true);
       myPDF.setVisible(true);
       datePDF.setVisible(true); 
       lblGen1.setVisible(true);
       lblGen2.setVisible(true);
    }
    
    public void cleanRegisterMenuFields(){
        txtName.setText("");
        txtPrice.setText("");
        txtValidate.setValue(null);
        txtProvider.setText("");
    }

    
}
