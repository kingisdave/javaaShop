import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// import java.util.Scanner;
import java.net.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.table.*;

public class JavaShop implements ActionListener{
    JFrame myFrame;
    JPanel homePanel,registerPanel,signInPanel,adminPanel,stockPanel;
    JScrollPane jScrollPane1;
    JTextField sIText;
    JDialog addStockform;
    // JMenu firstMenus, secondMenu;
    // JMenuBar menuContainer;
    // JTabbedPane jTabbedPane1;
    // JTable jTable1;
    boolean showPanel = false;
    String[] menus = {"Home", "Register", "Sign In"};
	char[] mAlias = { 'H', 'R', 'S'};
    String fullname, nameOfFile, newToStock, filepath, dataFile;

    public JavaShop(){
        // SplashClass sc = new SplashClass();
        myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myFrame.setLocation(new Point(400, 100)); // To set the default location to anywhere on the window
		myFrame.setSize(new Dimension(450, 400));
        myFrame.setTitle("JAVA-SHOP"); // To set the title of the window;	
		myFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("file.png"));
        myFrame.setLayout(new BorderLayout());

        JMenuBar menuLayer = new JMenuBar();
        for (int ii= 0; ii < menus.length; ii++) {
			
			JMenuItem firstMenus = new JMenuItem(menus[ii]);
			firstMenus.setMnemonic(mAlias[ii]);
            firstMenus.addActionListener(this);
            menuLayer.add(firstMenus);
        }
        myFrame.add(menuLayer, BorderLayout.NORTH);
        
        setStocks();
        // homePage();
        // signInForm();
        // registerForm();
        // myFrame.pack();   
		myFrame.setVisible(true);
    }

    public void homePage(){
        homePanel = new JPanel(new FlowLayout());
        JPanel homeWrapper = new JPanel(new BorderLayout());
        JLabel hLabel = new JLabel("HOME");
        homeWrapper.add(hLabel, BorderLayout.NORTH);
        JPanel cardPanel = new JPanel(new GridLayout(1,4));
        
        ImageIcon imgIcon = new ImageIcon("file.png");
        JLabel bgImg = new JLabel(imgIcon);
        bgImg.setPreferredSize(new Dimension(100, 100));
        cardPanel.add(bgImg);
        JLabel bgImg2 = new JLabel(imgIcon);
        cardPanel.add(bgImg2);
        JLabel bgImg3 = new JLabel(imgIcon);
        cardPanel.add(bgImg3);
        JLabel bgImg4 = new JLabel(imgIcon);
        cardPanel.add(bgImg4);
        homeWrapper.add(cardPanel, BorderLayout.CENTER);
        homePanel.add(homeWrapper);
        myFrame.add(homePanel, BorderLayout.CENTER);
    }
    public void setStocks(){
        stockPanel = new JPanel(new BorderLayout());
        JPanel stockHeader = new JPanel(new BorderLayout());
        JLabel hTitle = new JLabel("STOCKS");
        hTitle.setFont(new Font(null, 1, 25));
        stockHeader.add(hTitle, BorderLayout.CENTER);
        JButton showStockBtn = new JButton("Add Stock");
        showStockBtn.addActionListener(this);
        stockHeader.add(showStockBtn, BorderLayout.EAST);
        stockPanel.add(stockHeader, BorderLayout.NORTH);
        JPanel stockBody = new JPanel(new FlowLayout());
        
        String data[][]={ {"1","","Amit","67","40000","Delete"},
                            {"2","","Jai","40","780000","Delete"},    
                            {"3","","Sachin","20","700000","Delete"}};    
        String column[]={"S/N","IMG","STOCK NAME","QTY","PRICE","OPERATN"};         
        DefaultTableModel model = new DefaultTableModel(data, column);
        // final JTable jt=new JTable(data,column);
        final JTable jt = new JTable( model );
        jt.setCellSelectionEnabled(true);
        Action delete = new AbstractAction(){
            public void actionPerformed(ActionEvent e)
            {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                ((DefaultTableModel)table.getModel()).removeRow(modelRow);
            }
        };
        ButtonColumn buttonColumn = new ButtonColumn(jt, delete, 5);
        buttonColumn.setMnemonic(KeyEvent.VK_D);  
        ListSelectionModel select= jt.getSelectionModel();  
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        select.addListSelectionListener(new ListSelectionListener() {  
            public void valueChanged(ListSelectionEvent e) {  
            String Data = null;  
            int[] row = jt.getSelectedRows();  
            int[] columns = jt.getSelectedColumns();  
            for (int i = 0; i < row.length; i++) {  
                for (int j = 0; j < columns.length; j++) {  
                Data = (String) jt.getValueAt(row[i], columns[j]);  
                } }  
            System.out.println("Table element selected is: " + Data);    
            }       
        });  
        JScrollPane sp=new JScrollPane(jt);    
        stockBody.add(sp);
        stockPanel.add(stockBody, BorderLayout.CENTER);
        myFrame.add(stockPanel, BorderLayout.CENTER);
    }
    public void addStock(){
        addStockform = new JDialog(myFrame);
		addStockform.setSize(350, 400);
		addStockform.setLocation(300, 200); // To set the default location to anywhere on the window
		addStockform.setTitle("Add Stock");
        
        JPanel addStockPanel = new JPanel(new FlowLayout());
        JPanel addStockWrapper = new JPanel(new BorderLayout());
        
        JPanel aStockLabel = new JPanel(new FlowLayout());
        
        JLabel addStockLabel = new JLabel("Add to Stocks");
        addStockLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        aStockLabel.add(addStockLabel);
        addStockWrapper.add(aStockLabel, BorderLayout.NORTH);
        
        JPanel stocksForm = new JPanel(new GridLayout(5,1));
        JPanel stockName = new JPanel(new GridLayout(2,1));
        JLabel stockNameLabel = new JLabel("Stock Name:");
        stockName.add(stockNameLabel);
        JTextField sNameText = new JTextField(15);
        stockName.add(sNameText);
        stocksForm.add(stockName);

        JPanel stockDesc = new JPanel(new GridLayout(2,1));
        JLabel sDescLabel = new JLabel("Stock Description(Features):");
        stockDesc.add(sDescLabel);
        JTextField sDescText = new JTextField(15);
        stockDesc.add(sDescText);
        stocksForm.add(stockDesc);

        JPanel stockPrice = new JPanel(new GridLayout(2,1));
        JLabel sPriceLabel = new JLabel("Stock Price(Cost price):");
        stockPrice.add(sPriceLabel);
        JTextField sPriceText = new JTextField(15);
        stockPrice.add(sPriceText);
        stocksForm.add(stockPrice);
        
        JPanel stockQuantity = new JPanel(new GridLayout(2,1));
        JLabel sQLabel = new JLabel("Stock Quantity(How many):");
        stockQuantity.add(sQLabel);
        JTextField sQText = new JTextField(15);
        stockQuantity.add(sQText);
        stocksForm.add(stockQuantity);

        JPanel stockImage = new JPanel(new GridLayout(2,1));
        JLabel sILabel = new JLabel("Stock Image:");
        stockImage.add(sILabel);
        JPanel imgBtn = new JPanel(new BorderLayout()); 
        sIText = new JTextField(12);
        sIText.setFont(new Font("Tahoma", Font.PLAIN, 12));
        sIText.setEditable(false);
        JButton sImgBtn = new JButton("Choose Image");
        sImgBtn.addActionListener(this);
        imgBtn.add(sIText, BorderLayout.WEST);
        imgBtn.add(sImgBtn, BorderLayout.EAST);
        stockImage.add(imgBtn);
        stocksForm.add(stockImage);
        
        addStockWrapper.add(stocksForm, BorderLayout.CENTER);
        
        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton asBtn = new JButton("Add to store");
        asBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                String sname = sNameText.getText();
                String sdesc = sDescText.getText();
                String sprice = sPriceText.getText();
                String squantity = sQText.getText();
                String simage = sIText.getText();
                if(sname.isEmpty() || sdesc.isEmpty() || sprice.isEmpty() || squantity.isEmpty() || simage.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fill all the input with the correct details");
                } else{
                    if (nameOfFile.toLowerCase().endsWith("jpg") || nameOfFile.toLowerCase().endsWith("png") || nameOfFile.toLowerCase().endsWith("jpeg")) {
                        int width = 800;
                        int height = 500;
                        BufferedImage myimage = null;
                        
                        try{ 
                            File myFolder = new File("");
                            File stockFolder = new File(myFolder.getAbsolutePath()+"\\stock_upload");
                            if(!stockFolder.exists()){
                                stockFolder.mkdir();
                            }
                            
                            String[] stockOutput = stockFolder.list();   // Listing of all the files inside folder
                            for (int ii = 0; ii < stockOutput.length; ii++) {
                                if (stockOutput[ii].toLowerCase().equals(nameOfFile.toLowerCase()) ) {
                                    Pattern text = Pattern.compile(".", Pattern.LITERAL); // Match . character
                                    Matcher found = text.matcher(nameOfFile);
                                    nameOfFile = found.replaceFirst("_copy.");  
                                    newToStock = stockFolder.toString()+"\\"+ nameOfFile;
                                } else {
                                    newToStock = stockFolder.toString()+"\\"+ nameOfFile;
                                }
                                File oldsFile = new File(filepath);
                                myimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                                myimage = ImageIO.read(oldsFile);
                                File newsFile = new File(newToStock);
                                ImageIO.write(myimage, "png", newsFile);
                            }
                        }catch(Exception ex){
                            JOptionPane.showMessageDialog(null, "Image Upload Error"); 
                        }
                        simage = nameOfFile;
                        try {
                            // // register driver class
                            Class.forName("com.mysql.jdbc.Driver");
                
                            // //establish connection
                            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
            
                            String query = "insert into stocks_tb(stock_name, stock_features, stock_price, stock_quantity, stock_image) values(?,?,?,?,?)";
    
                            PreparedStatement enterData = conn.prepareStatement(query);
                            enterData.setString(1, sname);
                            enterData.setString(2, sdesc);
                            enterData.setString(3, sprice);
                            enterData.setString(4, squantity);
                            enterData.setString(5, simage);
                            enterData.execute();
                            // if(enterData.execute()){
                            //     JOptionPane.showMessageDialog(null, "Stock has been Saved!!");
                            // }
                            JOptionPane.showMessageDialog(null, "Stock has been Saved!!");
                            //close connection
                            conn.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Stock not saved!!! ");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Image must be jpg, jpeg or png file");
                    }                    
                    sNameText.setText(""); sDescText.setText("");
                    sPriceText.setText(""); sQText.setText(""); sIText.setText("");
                    addStockform.dispose();
                }
            }
        });
        asBtn.setPreferredSize(new Dimension(180, 30));
       
        btnPanel.add(asBtn);
        addStockWrapper.add(btnPanel, BorderLayout.SOUTH);
        // addStockWrapper.setPreferredSize(new Dimension(250,280));
        
        addStockPanel.add(addStockWrapper);
        addStockform.add(addStockPanel);
        addStockform.pack();
        addStockform.setVisible(true);
    }

    public void registerForm(){
        final JDialog rform = new JDialog(myFrame);
		rform.setSize(350, 350);
		rform.setLocation(300, 200); // To set the default location to anywhere on the window
		rform.setTitle("Register"); // To set the title of the window;

        registerPanel = new JPanel(new FlowLayout());
        JPanel registerWrapper = new JPanel(new BorderLayout());
        JPanel rLabel = new JPanel(new FlowLayout());
        
        JLabel regLabel = new JLabel("REGISTER");
        regLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        rLabel.add(regLabel);
        registerWrapper.add(rLabel, BorderLayout.NORTH);
        
        JPanel txtPanel = new JPanel(new GridLayout(4,1));
        JPanel txtPanel1 = new JPanel(new GridLayout(2,1));
        JLabel firstLabel = new JLabel("Firstname:");
        txtPanel1.add(firstLabel);
        JTextField regFirst = new JTextField(7);
        regFirst.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txtPanel1.add(regFirst);
        txtPanel.add(txtPanel1);

        JPanel txtPanel11 = new JPanel(new GridLayout(2,1));
        JLabel lastLabel = new JLabel("Lastname:");
        txtPanel11.add(lastLabel);
        JTextField regLast = new JTextField(7);
        regLast.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txtPanel11.add(regLast);
        txtPanel.add(txtPanel11);

        JPanel txtPanel13 = new JPanel(new GridLayout(2,1));
        JLabel emailLabel = new JLabel("Email:");
        txtPanel13.add(emailLabel);
        JTextField regEmail = new JTextField(10);
        regEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txtPanel13.add(regEmail);
        txtPanel.add(txtPanel13);
        
        JPanel txtPanel14 = new JPanel(new GridLayout(2,1));
        JLabel passwordLabel = new JLabel("Password:");
        txtPanel14.add(passwordLabel);
        JTextField regPassword = new JTextField(10);
        regPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txtPanel14.add(regPassword);
        txtPanel.add(txtPanel14);

        registerWrapper.add(txtPanel, BorderLayout.CENTER);
        
        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton regBtn = new JButton("REGISTER");
        regBtn.setPreferredSize(new Dimension(180, 30));
        // regBtn.addActionListener(this);
        regBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                String fname = regFirst.getText();
                String lname = regLast.getText();
                String email = regEmail.getText();
                String pass = regPassword.getText();
                if(fname.isEmpty() || lname.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fill all the input with the correct details");
                }else{
                    try{
                        //register driver class
                        Class.forName("com.mysql.jdbc.Driver");
            
                        //establish connection
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
            
                        String query = "insert into users_tb(full_name, email, password, account_type) values(?,?,?,?)";

                        PreparedStatement enterData = conn.prepareStatement(query);
                        enterData.setString(1, fname+" "+lname);
                        enterData.setString(2, email);
                        enterData.setString(3, pass);
                        enterData.setString(4, "0");
                        
                        if(enterData.execute()){
                            JOptionPane.showMessageDialog(null, "User Already Saved!!");
                        }else{
                            JOptionPane.showMessageDialog(null, enterData.getWarnings());
                        }
                        
                        //close connection
                        conn.close();
            
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
        btnPanel.add(regBtn);
        registerWrapper.add(btnPanel, BorderLayout.SOUTH);
        registerWrapper.setPreferredSize(new Dimension(250,280));
        
        registerPanel.add(registerWrapper);
        rform.add(registerPanel);
        rform.setVisible(true);
        // myFrame.add(registerPanel, BorderLayout.SOUTH);
    }
   
    public void signInForm(){
        final JDialog lform = new JDialog(myFrame);
		lform.setSize(250, 300);
		lform.setLocation(350, 200); // To set the default location to anywhere on the window
		lform.setTitle("Sign In"); // To set the title of the window;

        signInPanel = new JPanel(new FlowLayout());
        JPanel signWrapper = new JPanel(new BorderLayout());
        JPanel sLabel = new JPanel(new FlowLayout());
        
        JLabel signLabel = new JLabel("SIGN IN");
        signLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        sLabel.add(signLabel);
        signWrapper.add(sLabel, BorderLayout.NORTH);
        
        JPanel txtPanel = new JPanel(new BorderLayout());
        
        JPanel txtPanel1 = new JPanel(new GridLayout(2,1));
        JLabel emailLabel = new JLabel("Email:");
        txtPanel1.add(emailLabel);
        JTextField signEmail = new JTextField(8);
        signEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtPanel1.add(signEmail);
        txtPanel.add(txtPanel1, BorderLayout.NORTH);
        
        JPanel txtPanel2 = new JPanel(new GridLayout(2,1));
        JLabel passwordLabel = new JLabel("Password:");
        txtPanel2.add(passwordLabel);
        JTextField signPassword = new JTextField(8);
        signPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtPanel2.add(signPassword);
        
        txtPanel.add(txtPanel2, BorderLayout.SOUTH);
        signWrapper.add(txtPanel, BorderLayout.CENTER);
        
        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton signBtn = new JButton("SIGN IN");
        signBtn.setPreferredSize(new Dimension(180, 30));
        // signBtn.addActionListener(this);
        signBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                String email = signEmail.getText();
                String pass = signPassword.getText();
                if(email.isEmpty() || pass.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fill all the input with the correct details");
                }else{
                    try{
                        //register driver class
                        Class.forName("com.mysql.jdbc.Driver");
            
                        //establish connection
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");

                        String query = "select full_name, password from users_tb where email = ? AND password = ?";
                        PreparedStatement enterData = conn.prepareStatement(query);
                        enterData.setString(1, fullname);
                        enterData.setString(2, pass);
                        ResultSet rs = enterData.executeQuery();
                        if(rs.next()){
                            lform.dispose();
                            AdminPage adp = new AdminPage(fullname);
                            //  HomePage hp = new HomePage(fullname);
                            JOptionPane.showMessageDialog(null, "User Already Saved!!");
                        }else{
                            JOptionPane.showMessageDialog(null, enterData.getWarnings());
                        }

                        //close connection
                        conn.close();
            
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(null, ex.getStackTrace());
                        ex.printStackTrace();
                    }
                }
            }
        });
        btnPanel.add(signBtn);
        signWrapper.add(btnPanel, BorderLayout.SOUTH);
        signWrapper.setPreferredSize(new Dimension(180,250));
        
        signInPanel.add(signWrapper);
        lform.add(signInPanel);
        lform.setVisible(true);
        // myFrame.add(signInPanel, BorderLayout.SOUTH);
    }

    @Override
	public void actionPerformed(ActionEvent e){
		String com = e.getActionCommand().toString();
		if(com.equals("Home")){
        } else if(com.equals("Register")) {
            registerForm();
        } else if(com.equals("Sign In")){
            signInForm();
        } else if(com.equals("Add Stock")){
            addStock();
        } else if(com.equals("Choose Image")){
            JFileChooser fc=new JFileChooser();    
            int i=fc.showOpenDialog(myFrame);    
            if(i==JFileChooser.APPROVE_OPTION){    
                File f=fc.getSelectedFile();
                filepath=f.getPath();
                nameOfFile = f.getName();   
                try{  
                    sIText.setText(filepath);
                }catch (Exception ex) {ex.printStackTrace();  }                 
            }  
        } else if(com.equals("Add to store")){
            //     String sname = sNameText.getText();
            //     String sdesc = sDescText.getText();
            //     String sprice = sPriceText.getText();
            //     String squantity = sQText.getText();
            //     String simage = sIText.getText();
            //     if(sname.isEmpty() || sdesc.isEmpty() || sprice.isEmpty() || squantity.isEmpty() || simage.isEmpty()) {
            //         JOptionPane.showMessageDialog(null, "Fill all the input with the correct details");
            //     }else{
            //         try{
            //             //register driver class
            //             Class.forName("com.mysql.jdbc.Driver");
            
            //             //establish connection
            //             Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
            
            //                 String query = "insert into stocks_tb(stock_name, stock_features, stock_price, stock_quantity stock_image) values(?,?,?,?)";
    
            //                 PreparedStatement enterData = conn.prepareStatement(query);
            //                 enterData.setString(1, sname);
            //                 enterData.setString(2, sdesc);
            //                 enterData.setString(3, sprice);
            //                 enterData.setString(4, squantity);
            //                 enterData.setString(5, simage);
                            
            //                 if(enterData.execute()){
            //                     JOptionPane.showMessageDialog(null, "Stock has been Saved!!");
            //                 }else{
            //                     JOptionPane.showMessageDialog(null, enterData.getWarnings());
            //                 }
            //             //close connection
            //             conn.close();
            
            //         }catch(Exception ex){
            //             ex.printStackTrace();
            //         }
            //     }
            // addStockform.dispose();
        } else {
        }
	}

    public static void main(String[] args) {
        JavaShop jsp = new JavaShop();
    }
}

class AdminPage extends JavaShop {
    // private String name;
    public AdminPage(String myName){
        // this.name = myName;
    }

    
}

