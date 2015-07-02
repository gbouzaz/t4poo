
package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import Classes.Product;
import Classes.Product;
import Classes.User;
import Classes.User;


public class ClientWindow extends Application implements EventHandler<ActionEvent> {
    public Button loginMenu = new Button("Login");
    public Button registerMenu = new Button("Register new client");
    public Button listMenu = new Button("List all products");
    public Button notification = new Button("Request notification!");
    public Button buyMenu = new Button("Buy our products!");
    public Button exit = new Button("Exit");
    public Label mainLabel = new Label(" CARRO&4\nSupermarket");
    public Label lblUser = new Label("User: ");
    
    public TextField txtLoginID = new TextField();
    public PasswordField txtLoginPassword = new PasswordField();
    public Button login = new Button("Go!");
    public Button backLoginMenu = new Button("Back");
    
    public TextField txtName = new TextField();
    public TextField txtAddress = new TextField();
    public TextField txtPhone = new TextField();
    public TextField txtEmail = new TextField();
    public TextField txtID = new TextField();
    public PasswordField txtPass = new PasswordField();
    public Button registerClient = new Button("Register!");
    public Button backRegister = new Button("Back");
    
    public TableView products = new TableView();
    public Button backList = new Button("Back");
    
    public TextField txtQtd = new TextField();
    public DatePicker txtDate = new DatePicker();
    public Button buy = new Button("Buy!");
    public Button backBuy = new Button("Back!");
    public Label lblBuy = new Label("To purchase an item, please select one from the table above!!");
    
    public Alert warning = new Alert(AlertType.WARNING);
    public Alert information = new Alert(AlertType.INFORMATION);
    
    
    public String currentID;
    public static Socket clientSocket;
    public static PrintWriter pw;
    public static BufferedReader br;
    
    
    public static void main(String[] args) throws IOException{
        clientSocket = new Socket("localhost", 4444);
        pw = new PrintWriter(clientSocket.getOutputStream(), true);
        br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        launch(args);
        
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("CARRO&4 - Client Application");
        
// Menu principal
        loginMenu.setMaxSize(200, 50);
        loginMenu.setTranslateY(-130);
        loginMenu.setOnAction(this);
        registerMenu.setMaxSize(200, 50);
        registerMenu.setTranslateY(-70);
        registerMenu.setOnAction(this);
        listMenu.setMaxSize(200, 50);
        listMenu.setTranslateY(-10);
        listMenu.setOnAction(this);
        notification.setMaxSize(200, 50);
        notification.setTranslateY(50);
        notification.setOnAction(this);
        notification.setDisable(true);
        buyMenu.setMaxSize(200, 50);
        buyMenu.setTranslateY(110);
        buyMenu.setOnAction(this);
        buyMenu.setDisable(true);
        exit.setMaxSize(70, 50);
        exit.setTranslateY(210);
        exit.setTranslateX(230);
        exit.setOnAction(this);
        mainLabel.setMaxSize(300, 100);
        mainLabel.setTranslateY(-190);
        mainLabel.setTranslateX(50);
        mainLabel.setFont(new Font("Cooper Black", 30));
        mainLabel.setTextFill(Color.web("#0000FF"));
        lblUser.setMaxSize(200, 50);
        lblUser.setTranslateY(-130);
        lblUser.setTranslateX(-200);
        
//Login menu
        txtLoginID.setVisible(false);
        txtLoginID.setMaxSize(300, 40);
        txtLoginID.setTranslateY(-60);
        txtLoginID.setPromptText("Your User ID");
        txtLoginPassword.setVisible(false);
        txtLoginPassword.setMaxSize(300, 40);
        txtLoginPassword.setPromptText("Your Password");
        login.setVisible(false);
        login.setMaxSize(100, 40);
        login.setTranslateY(60);
        login.setTranslateX(100);
        login.setOnAction(this);
        backLoginMenu.setVisible(false);
        backLoginMenu.setMaxSize(100, 40);
        backLoginMenu.setTranslateY(60);
        backLoginMenu.setTranslateX(-100);
        backLoginMenu.setOnAction(this);

//Client register menu
        txtName.setVisible(false);
        txtName.setMaxSize(300, 40);
        txtName.setTranslateY(-150);
        txtName.setPromptText("Your name");
        txtAddress.setVisible(false);
        txtAddress.setMaxSize(300, 40);
        txtAddress.setTranslateY(-90);
        txtAddress.setPromptText("Your address");
        txtPhone.setVisible(false);
        txtPhone.setMaxSize(300, 40);
        txtPhone.setTranslateY(-30);
        txtPhone.setPromptText("Your phone");
        txtEmail.setVisible(false);
        txtEmail.setMaxSize(300, 40);
        txtEmail.setTranslateY(30);
        txtEmail.setPromptText("Your email");
        txtID.setVisible(false);
        txtID.setMaxSize(300, 40);
        txtID.setTranslateY(90);
        txtID.setPromptText("Your user ID");
        txtPass.setVisible(false);
        txtPass.setMaxSize(300, 40);
        txtPass.setTranslateY(150);
        txtPass.setPromptText("Your password");
        registerClient.setVisible(false);
        registerClient.setMaxSize(100, 40);
        registerClient.setTranslateX(100);
        registerClient.setTranslateY(210);
        registerClient.setOnAction(this);
        backRegister.setVisible(false);
        backRegister.setMaxSize(100, 40);
        backRegister.setTranslateX(-100);
        backRegister.setTranslateY(210);
        backRegister.setOnAction(this);

//Products List
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
        
//Buy menu
        txtDate.setVisible(false);
        txtDate.setMaxSize(150, 40);
        txtDate.setTranslateY(220);
        txtDate.setTranslateX(-220);
        txtDate.setPromptText("Purchase date");
        txtQtd.setVisible(false);
        txtQtd.setMaxSize(100, 40);
        txtQtd.setTranslateY(220);
        txtQtd.setTranslateX(-80);
        txtQtd.setPromptText("Quantity");
        buy.setVisible(false);
        buy.setMaxSize(50, 40);
        buy.setTranslateY(220);
        buy.setTranslateX(20);
        buy.setOnAction(this);
        backBuy.setVisible(false);
        backBuy.setMaxSize(50, 40);
        backBuy.setTranslateY(220);
        backBuy.setTranslateX(220);
        backBuy.setOnAction(this);
        lblBuy.setVisible(false);
        lblBuy.setMaxSize(500, 50);
        lblBuy.setTranslateY(170);
        lblBuy.setTranslateX(0);
     
      
        StackPane layout = new StackPane();
        layout.getChildren().addAll(loginMenu, registerMenu, listMenu,
                notification, buyMenu, exit, txtLoginID, txtLoginPassword,
                backLoginMenu, login, txtName, txtAddress, txtPhone, txtEmail,
                txtID, txtPass, registerClient, backRegister, products, backList,
                txtDate, txtQtd, backBuy, buy, mainLabel, lblUser, lblBuy);
        
        Scene scene = new Scene(layout, 600, 500);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource() == loginMenu){
            setMainMenuInvisible();
            setLoginMenuVisible();
        } else if(event.getSource() == backLoginMenu){
            setLoginMenuInvisible();
            setMainMenuVisible();
        } else if(event.getSource() == registerMenu){
           setMainMenuInvisible();
           setRegisterMenuVisible();
        } else if(event.getSource() == backRegister){
           setRegisterMenuInvisible();
           setMainMenuVisible();
        } else if(event.getSource() == listMenu){
            setMainMenuInvisible();
            setListVisible();
            recoverList();
        } else if(event.getSource() == backList){
            setListInvisible();
            setMainMenuVisible();
        } else if(event.getSource() == buyMenu){
            setMainMenuInvisible();
            setBuyMenuVisible();
            recoverList();
        } else if(event.getSource() == backBuy){
            setBuyMenuInvisible();
            setMainMenuVisible();
        } else if(event.getSource() == registerClient){
            pw.println("Register");
            pw.println(getInformationFromClient());
            cleanRegisterFields();
        } else if(event.getSource() == login){
            
            if(!txtLoginID.getText().equals("") && 
                    !txtLoginPassword.getText().equals("")){
                
                boolean exists;
                String str = "false";
                String toPass = txtLoginID.getText() + "," + txtLoginPassword.getText();
                pw.println("Login");
                pw.println(toPass);
                try {
                    str = br.readLine();  
                } catch (IOException ex) {}
                exists = Boolean.parseBoolean(str);
                if(exists){
                    this.currentID = txtLoginID.getText();
                    notification.setDisable(false);
                    buyMenu.setDisable(false);
                    setLoginMenuInvisible();
                    setMainMenuVisible();
                    lblUser.setText("User: " + this.currentID);
                } else {
                    warning.setTitle("Warning");
                    warning.setHeaderText(null);
                    warning.setContentText("User not found!");
                    warning.showAndWait();
                }
                cleanLoginFields();
            }
        } else if(event.getSource() == notification){
            pw.println("Notification");
            pw.println(this.currentID);
            pw.println("true");
            information.setTitle("Information");
            information.setHeaderText(null);
            information.setContentText("Notification received!\n"
                    + "You'll be noted soon! Check your email!");
            information.showAndWait();
        } else if(event.getSource() == buy){
            pw.println("Buy");
            Product p = (Product) products.getSelectionModel().getSelectedItem();
            double price = Double.parseDouble(p.getPrice()) * Integer.parseInt(txtQtd.getText());
            pw.println(this.currentID + "," + p.getName() + "," + txtDate.getValue().toString()
                + "," + txtQtd.getText() + "," + p.getQtd() + "," + Double.toString(price));
            try {
                if(!Boolean.parseBoolean(br.readLine())){
                    warning.setTitle("Warning");
                    warning.setHeaderText(null);
                    warning.setContentText("Purchase not finished!");
                    warning.showAndWait();
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            cleanBuyFields();
            recoverList();
        } else if(event.getSource() == exit){
            System.exit(0);
        }
    }
    
    /**
     * Método que gera a string s, que será passada para o servidor com a finalidade
     * de registro de um usuário.
     * @return 
     */
    public String getInformationFromClient(){
        User u = new User(txtName.getText(), txtAddress.getText(), txtPhone.getText(),
                txtEmail.getText(), txtID.getText(), txtPass.getText());
        
        String s = u.getName() + "," + u.getAddress() + "," + u.getPhone() + ","
                + u.getEmail() + "," + u.getId() + "," + u.getPassword() + ","
                + u.getNotification();
        
        return s;
    }
    
    /**
     * Envia ao servidor o comando Search com a finalidade de obter todos os produtos
     * cadastrados no sistema. Esses valores são utilizados para a visualização em tabela.
     */
    public void recoverList(){
        pw.println("Search");
            
            String data;
            ObservableList<Product> list = FXCollections.observableArrayList();
            
            try {
                while(!(data = br.readLine()).equals("fim")){
                    String[] parts = data.split(",");
                    Product p = new Product();
                    p.setName(parts[0]);
                    p.setPrice(parts[1]);
                    p.setValidate(parts[2]);
                    p.setProvider(parts[3]);
                    p.setQtd(parts[4]);
                    list.add(p);
                }
                list.stream().map(Product::getQtd).sorted();
                
                products.setItems(list);
            } catch (IOException ex) {
                Logger.getLogger(ClientWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void setMainMenuInvisible(){
        loginMenu.setVisible(false);
        registerMenu.setVisible(false);
        listMenu.setVisible(false);
        notification.setVisible(false);
        buyMenu.setVisible(false);
        exit.setVisible(false);
        lblUser.setVisible(false);
        mainLabel.setVisible(false);
    }
    public void setMainMenuVisible(){
        loginMenu.setVisible(true);
        registerMenu.setVisible(true);
        listMenu.setVisible(true);
        notification.setVisible(true);
        buyMenu.setVisible(true);
        exit.setVisible(true);
        lblUser.setVisible(true);
        mainLabel.setVisible(true);
    }
    public void setLoginMenuInvisible(){
        txtLoginID.setVisible(false);
        txtLoginPassword.setVisible(false);
        login.setVisible(false);
        backLoginMenu.setVisible(false);
    }
    public void setLoginMenuVisible(){
        txtLoginID.setVisible(true);
        txtLoginPassword.setVisible(true);
        login.setVisible(true);
        backLoginMenu.setVisible(true);
        mainLabel.setVisible(true);
    }
    public void setRegisterMenuInvisible(){
        txtName.setVisible(false);
        txtAddress.setVisible(false);
        txtPhone.setVisible(false);
        txtEmail.setVisible(false);
        txtID.setVisible(false);
        txtPass.setVisible(false);
        registerClient.setVisible(false);
        backRegister.setVisible(false);
    }
    public void setRegisterMenuVisible(){
        txtName.setVisible(true);
        txtAddress.setVisible(true);
        txtPhone.setVisible(true);
        txtEmail.setVisible(true);
        txtID.setVisible(true);
        txtPass.setVisible(true);
        registerClient.setVisible(true);
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
    public void setBuyMenuInvisible(){
        products.setVisible(false);
        backBuy.setVisible(false);
        buy.setVisible(false);
        txtQtd.setVisible(false);
        txtDate.setVisible(false);
        lblBuy.setVisible(false);
    }
    public void setBuyMenuVisible(){
        products.setVisible(true);
        backBuy.setVisible(true);
        buy.setVisible(true);
        txtQtd.setVisible(true);
        txtDate.setVisible(true);
        lblBuy.setVisible(true);
    }
    
    public void cleanRegisterFields(){
        txtName.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtID.setText("");
        txtPass.setText("");
    }
    
    public void cleanLoginFields(){
        txtLoginID.setText("");
        txtLoginPassword.setText("");
    }
    
    public void cleanBuyFields(){
        txtDate.setValue(null);
        txtQtd.setText("");
    }
}
