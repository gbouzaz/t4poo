/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Classes.Product;
import Classes.Sale;
import Classes.User;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 *
 * @author gbouz_000
 */
public class Server extends Thread{    
    public Socket s;
    
    public Server(){
    }
    public Server(Socket s){
        this.s = s;
    }
    
    /**
     * Registra novo usuário no sistema, passado como uma string por parâmetro.
        Essa string passada por parâmetro contém o cadastro no formato csv.
     * @param str
     * @throws IOException 
     */
    
    public void addNewUser(String str) throws IOException{
        String[] toWrite = str.split(",");
        CSVWriter writer = new CSVWriter(new FileWriter("users.csv", true));
        writer.writeNext(toWrite);
        writer.close();
    }
    
    /**
     * Registra no arquivo de produtos o produto p.
     * @param p
     * @throws Exception 
     */
    public void addNewProduct(Product p) throws Exception{
        CSVWriter writer = new CSVWriter(new FileWriter("products.csv", true));
        
        
        String str = p.getName() + "," + p.getPrice() + "," + p.getValidate()
                + "," + p.getProvider() + "," + p.getQtd();
        String[] toWrite = str.split(",");
        
        writer.writeNext(toWrite);
        writer.close();
    }
    
    /**
     * Registra uma nova venda no sistema, caso a quantidade do produto em 
     * questão seja válida. Retorna um valor boolean que informa se a venda
     * foi registrada com sucesso.
     * @param s
     * @param qtd
     * @return
     * @throws Exception 
     */
    public boolean addNewSale(Sale s, int qtd) throws Exception{
        if(qtd == 0 || qtd < Integer.parseInt(s.getQtd()))
            return false;
        
        CSVWriter writer = new CSVWriter(new FileWriter("sales.csv", true));
        
        
        String str = s.getUserID() + "," + s.getProductName() + "," +
                s.getDate() + "," + s.getQtd() + "," + s.getTotalCost();
        String[] toWrite = str.split(",");
        
        writer.writeNext(toWrite);
        writer.close();
        
        updateProductQtd(s.getProductName(), qtd - Integer.parseInt(s.getQtd()));
        
        return true;
    }
    
    /**
     * Pesquisa no arquivo de produtos todos os produtos existentes.
     * Estes são retornados em uma lista (list).
     * @return
     * @throws Exception 
     */
    public ObservableList<Product> searchAllProducts() throws Exception{
        CSVReader reader = new CSVReader(new FileReader("products.csv"), ',');
        ObservableList<Product> list = FXCollections.observableArrayList();
        String[] readen;
        
        while((readen = reader.readNext()) != null){
            Product p = new Product();
            p.setName(readen[0]);
            p.setPrice(readen[1]);
            p.setValidate(readen[2]);
            p.setProvider(readen[3]);
            p.setQtd(readen[4]);
            list.add(p);
        }
        list.stream().map(Product::getQtd).sorted();
        
        reader.close();
        
        return list;
    }
    
    /**
     * Pesquisa no arquivo de usuários todos os usuários existentes.
     * Estes são retornados na lista list.
     * @return
     * @throws Exception 
     */
    public ObservableList<User> searchAllUsers() throws Exception{
        CSVReader reader = new CSVReader(new FileReader("users.csv"), ',');
        ObservableList<User> list = FXCollections.observableArrayList();
        String[] readen;
        
        while((readen = reader.readNext()) != null){
            User u = new User();
            u.setName(readen[0]);
            u.setAddress(readen[1]);
            u.setPhone(readen[2]);
            u.setEmail(readen[3]);
            u.setId(readen[4]);
            u.setPassword(readen[5]);
            u.setNotification(readen[6]);
            list.add(u);
        }
        
        reader.close();
        
        return list;
    }
    
    /**
     * Retorna em uma lista todas as vendas realizadas no dia passado
     * por parâmetro.
     * @param day
     * @return
     * @throws Exception 
     */
    public ObservableList<Sale> searchSalesByDay(String day) throws Exception{
        CSVReader reader = new CSVReader(new FileReader("sales.csv"), ',');
        ObservableList<Sale> list = FXCollections.observableArrayList();
        String[] readen;
        
        while((readen = reader.readNext()) != null){
            if(readen[2].equals(day)){
                Sale sl = new Sale();
                sl.setUserID(readen[0]);
                sl.setProductName(readen[1]);
                sl.setDate(readen[2]);
                sl.setQtd(readen[3]);
                sl.setTotalCost(readen[4]);
                list.add(sl);
            }
        }
        
        reader.close();
        
        return list;
    }
    
    /**
     * Retorna em uma lista todas as vendas realizadas em um mês de determinado
     * ano, onde ambos os valores de mês e ano são passados por parâmetro.
     * As vendas encontradas são retornadas.
     * @param month
     * @param year
     * @return
     * @throws Exception 
     */
    public ObservableList<Sale> searchSalesByMonth(String month, String year) throws Exception{
        CSVReader reader = new CSVReader(new FileReader("sales.csv"), ',');
        ObservableList<Sale> list = FXCollections.observableArrayList();
        String[] readen;
        
        while((readen = reader.readNext()) != null){
            String[] parts = readen[2].split("-");
            System.out.println(readen[2] + parts[1] + parts[0]);
            
            
            if(parts[1].equals(month) && parts[0].equals(year)){
                Sale sl = new Sale();
                sl.setUserID(readen[0]);
                sl.setProductName(readen[1]);
                sl.setDate(readen[2]);
                sl.setQtd(readen[3]);
                sl.setTotalCost(readen[4]);
                list.add(sl);
            }
        }
        
        reader.close();
        
        return list;
    }
    
    /**
     * Procura todos os usuários que desejassem receber notificação da atualização
     * no estoque de produtos. Os achados são adicionados em uma lista,
     * e com o e-mail de cada um, uma mensagem é enviada a cada usu[ario da lista.
     * @param itemUpdated
     * @return
     * @throws Exception 
     */
    public ObservableList<User> sendEmailToNotificatedUsers(String itemUpdated) throws Exception{
        CSVReader reader = new CSVReader(new FileReader("users.csv"), ',');
        ObservableList<User> list = FXCollections.observableArrayList();
        String[] readen;
        
        while((readen = reader.readNext()) != null){
            if(Boolean.parseBoolean(readen[6])){
                User u = new User();
                u.setName(readen[0]);
                u.setAddress(readen[1]);
                u.setPhone(readen[2]);
                u.setEmail(readen[3]);
                u.setId(readen[4]);
                u.setPassword(readen[5]);
                u.setNotification(readen[6]);
                list.add(u);
            }
        }
        
        reader.close();
        
        for(User u: list){
            sendEmail(u.getEmail(), itemUpdated);
        }
        
        return list;
    }
    
    /**
     * Realiza a busca de todos os produtos do sistema, com a finalidade de passar
     * para a aplicação cliente estes dados.
     * @param pw
     * @throws IOException 
     */
    public void recoverAllProducts(PrintWriter pw) throws IOException{
        CSVReader reader = new CSVReader(new FileReader("products.csv"), ',');
        
        String[] readen = null;
        
        while((readen = reader.readNext()) != null){
            String p = readen[0] + "," + readen[1] + "," + readen[2] + ","
                    + readen[3] + "," + readen[4];
            pw.println(p);
        }
        pw.println("fim");
        reader.close();
    }
    
    /**
     * Verifica se os campos de login e senha existem e coincidem à um mesmo usuário.
     * Retorna um valor boolean, que informa se existe ou não usuário com esses
     * parâmetros.
     * @param id
     * @param password
     * @return
     * @throws Exception 
     */
    public boolean verifyLogin(String id, String password) throws Exception{
        CSVReader reader = new CSVReader(new FileReader("users.csv"), ',');
        
        String[] readen = null;
        while((readen = reader.readNext()) != null){
            if(readen[4].equals(id) && readen[5].equals(password))
                return true;
        }
        
        reader.close();
        
        return false;
    }
    
    /**
     * Atualiza o interesse de um usuário com UserID id em receber notificações
     * de produtos em falta com o valor boolean value.
     * @param id
     * @param value
     * @throws Exception 
     */
    public void updateNotificationField(String id, boolean value) throws Exception{
        ObservableList<User> list = searchAllUsers();
        
        for(User u: list){
            if(u.getId().equals(id))
                u.setNotification(Boolean.toString(value));
        }
        
        CSVWriter writer = new CSVWriter(new FileWriter("users.csv", false));
        
        for(User u : list){    
            String str = u.getName() + "," + u.getAddress() + "," + u.getPhone()
                    + "," + u.getEmail() + "," + u.getId() + "," + u.getPassword() +
                    "," + u.getNotification();
        
            String[] toWrite = str.split(",");
        
            writer.writeNext(toWrite);
        
        }
        writer.close();
    }
    
    /**
     * Atualiza a quantidade de um produto com nome name com a quantidade newQtd.
     * @param name
     * @param newQtd
     * @throws Exception 
     */
    public void updateProductQtd(String name, int newQtd) throws Exception{
    ObservableList<Product> list = searchAllProducts();
        
        for(Product p: list){
            if(p.getName().equals(name))
                p.setQtd(Integer.toString(newQtd));
        }
        
        CSVWriter writer = new CSVWriter(new FileWriter("products.csv", false));
        
        for(Product p : list){    
            String str = p.getName() + "," + p.getPrice() + "," + p.getValidate()
                + "," + p.getProvider() + "," + p.getQtd();
        
            String[] toWrite = str.split(",");
        
            writer.writeNext(toWrite);
        
        }
        writer.close();    
    }
    
    
    /**
     * Dado um email destinyEmail e o item atualizado itemUpdated, envia à esse email
     * uma mensagem informando a atualização de estoque.
     * @param destinyEmail
     * @param itemUpdated 
     */
    public void sendEmail(String destinyEmail, String itemUpdated){
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(props, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("t4poo2015@gmail.com", "t4poo1234");
            }
        });

        String msgBody = "The product: \n\n-->  "+ itemUpdated +
                " <--\nhas been updated!\n\n"
                + "Carro&4 Supermarket.";

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("t4poo2015@gmail.com"));
            msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(destinyEmail));
            msg.setSubject("Carro&4: Item updated!");
            msg.setText(msgBody);
            Transport.send(msg);

        } catch (AddressException e) {
            System.out.println("Email address error!");
        } catch (MessagingException e) {
            System.out.println("Error!");
        }
    }
    
    /**
     * Dado o parâmetro day, cria um relatório de vendas do sistema nesse dia.
     * @param day
     * @throws Exception 
     */
    public void generateDayReport(String day) throws Exception{
        ObservableList<Sale> list = searchSalesByDay(day);
        
        Document dayReport = new Document();
        PdfWriter.getInstance(dayReport, new FileOutputStream(day + " report. C4 Supermarket.pdf"));
        
        PdfPTable table = new PdfPTable(5);
        
        table.addCell("User ID");
        table.addCell("Product Name");
        table.addCell("Sale Date");
        table.addCell("Qtd");
        table.addCell("Sale Total Cost");
        
        for(Sale sl: list){
            table.addCell(sl.getUserID());
            table.addCell(sl.getProductName());
            table.addCell(sl.getDate());
            table.addCell(sl.getQtd());
            table.addCell(sl.getTotalCost());
        }
        
        
        dayReport.open();
        Paragraph header = new Paragraph("\t\tCARRO&4 SUPERMARKET\n" + day + " sales report.\n\n\n"
                , new Font(FontFamily.COURIER, 30, Font.BOLD, new BaseColor(0, 0, 255)));
        dayReport.add(header);
        dayReport.add(table);
        
        dayReport.close();
    }
    
    /**
     * Dados os parâmetros month e year, cria uma relatório de vendas realizadas
     * no mês do ano informado.
     * @param month
     * @param year
     * @throws Exception 
     */
    public void generateMonthReport(String month, String year) throws Exception{
        ObservableList<Sale> list = searchSalesByMonth(month, year);
        String date = month + "-" + year;       
        
        Document dayReport = new Document();
        PdfWriter.getInstance(dayReport, new FileOutputStream(date + 
                " report. C4 Supermarket.pdf"));
        
               
        PdfPTable table = new PdfPTable(5);
        
        table.addCell("User ID");
        table.addCell("Product Name");
        table.addCell("Sale Date");
        table.addCell("Qtd");
        table.addCell("Sale Total Cost");
        
        for(Sale sl: list){
            table.addCell(sl.getUserID());
            table.addCell(sl.getProductName());
            table.addCell(sl.getDate());
            table.addCell(sl.getQtd());
            table.addCell(sl.getTotalCost());
        }
        
        
        dayReport.open();
        Paragraph header = new Paragraph("\t\tCARRO&4 SUPERMARKET\n" + date + " sales report.\n\n\n"
            , new Font(FontFamily.COURIER, 30, Font.BOLD, new BaseColor(0, 0, 255)));
        dayReport.add(header);
        dayReport.add(table);
        
        dayReport.close();
    }
    
    /**
     * Thread que aguarda constantemente dados enviados do cliente.
     * Uma vez que o cliente envia dados ao servidor, este filtra a mensagem e 
     * executa o comando desejado.
     */
    @Override
    public void run() {
        String toRead;
        String[] parts;
        while(true){
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
                PrintWriter pw = new PrintWriter(this.s.getOutputStream(), true);
                toRead = br.readLine();
                
                switch(toRead){
                    case "Register":
                        String client = br.readLine();
                        this.addNewUser(client);
                        break;
                    case "Search":
                        recoverAllProducts(pw);
                        break;
                    case "Login":
                        String data = br.readLine();
                        parts = data.split(",");
                        boolean answer = this.verifyLogin(parts[0], parts[1]);
                        pw.println(Boolean.toString(answer));
                        break;
                    case "Notification":
                        String id = br.readLine();
                        String newValue = br.readLine();
                        this.updateNotificationField(id, Boolean.parseBoolean(newValue));
                        break;
                    case "Buy":
                        String sale = br.readLine();
                        parts = sale.split(",");
                        Sale sl = new Sale(parts[0], parts[1], parts[2], parts[3], parts[5]);
                        pw.println(Boolean.toString(this.addNewSale(sl, Integer.parseInt(parts[4]))));
                        break;
                }
                
            } catch (IOException ex) {} catch (Exception ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
