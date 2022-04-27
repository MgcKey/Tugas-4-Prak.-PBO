
package Tugas4;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Form extends JFrame implements ActionListener {
    public String username, password;
    String data[][] = new String[100][3];
    Connector connector = new Connector();
    
    JLabel ltitle;
    JLabel lulogin;
    JLabel lplogin;
    JLabel ludaftar;
    JLabel lpdaftar;
    JTextField fulogin;
    JTextField fplogin;
    JTextField fudaftar;
    JTextField fpdaftar;
    JButton btnlogin;
    JButton btndaftar;

    public Form() {
        setTitle("LOGIN & REGISTER");
        setSize(500, 400);
        setDefaultCloseOperation(3);
        setVisible(true);
        setLayout(null);
        setLocationRelativeTo(null);
        ltitle = new JLabel("TUGAS 4 - LOGIN & REGISTER");
        lulogin = new JLabel("Username");
        lplogin = new JLabel("Password");
        ludaftar = new JLabel("Username");
        lpdaftar = new JLabel("Password");
        fulogin = new JTextField();
        fplogin = new JTextField();
        fudaftar = new JTextField();
        fpdaftar = new JTextField();
        btnlogin = new JButton("Login");
        btnlogin.addActionListener(this);
        btndaftar = new JButton("Daftar");
        btndaftar.addActionListener(this);
        
        add(ltitle);
        add(lulogin);
        add(lplogin);
        add(ludaftar);
        add(lpdaftar);
        add(fulogin);
        add(fplogin);
        add(fudaftar);
        add(fpdaftar);
        add(btnlogin);
        add(btndaftar);
        
        ltitle.setBounds(160, 50, 250, 20);
        lulogin.setBounds(40, 100, 100, 20);
        fulogin.setBounds(40, 130, 150, 20);
        lplogin.setBounds(40, 170, 100, 20);
        fplogin.setBounds(40, 200, 150, 20);
        ludaftar.setBounds(290, 100, 100, 20);
        fudaftar.setBounds(290, 130, 150, 20);
        lpdaftar.setBounds(290, 170, 100, 20);
        fpdaftar.setBounds(290, 200, 150, 20);
        btnlogin.setBounds(65, 240, 100, 20);
        btndaftar.setBounds(315, 240, 100, 20); 
    }
    
    public String getUlogin(){
        return fulogin.getText();
    }
    
    public String getPlogin(){
        return fplogin.getText();
    }
    
    public String getUdaftar(){
        return fudaftar.getText();
    }
    
    public String getPdaftar(){
        return fpdaftar.getText();
    }
    
    @Override
        public void actionPerformed(ActionEvent evt) {
            if(evt.getSource() == btnlogin){
                if(fulogin.getText().equals("") || fplogin.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Username dan/atau password tidak boleh kosong");
                }
                else{
                    try{
                        int jumlahData = 0;
                        int checkLogin = 0;
                        String queryLcheck = "Select * from `users`";
                        connector.statement = connector.koneksi.createStatement();
                        ResultSet resultSet = connector.statement.executeQuery(queryLcheck); 
                        while(resultSet.next()){
                            data[jumlahData][0] = resultSet.getString("id");
                            data[jumlahData][1] = resultSet.getString("username");
                            data[jumlahData][2] = resultSet.getString("password");
                            jumlahData++;
                        }
                        connector.statement.close();
                        for(int i = 0; i <= jumlahData; i++){
                            if(getUlogin().equals(data[i][1])){
                                if(getPlogin().equals(data[i][2])){
                                    checkLogin = 1;
                                    JOptionPane.showMessageDialog(null, "Berhasil login!");
                                }
                                else{
                                    checkLogin = 1;
                                    JOptionPane.showMessageDialog(null, "Password anda salah!");
                                }
                            }
                        }
                        if(checkLogin != 1){
                            JOptionPane.showMessageDialog(null, "Username belum terdaftar!");
                        }
                    }catch(Exception error){
                        JOptionPane.showMessageDialog(null, error.getMessage()); 
                    }
                }
            }
            if(evt.getSource() == btndaftar){
                if(fudaftar.getText().equals("") || fpdaftar.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Username dan/atau password tidak boleh kosong");
                }
                else{
                    try{
                        int jumlahData = 0;
                        int checkDaftar = 0;
                        String queryDcheck = "Select * from `users`"; 
                        connector.statement = connector.koneksi.createStatement();
                        ResultSet resultSet = connector.statement.executeQuery(queryDcheck); 
                        while(resultSet.next()){
                            data[jumlahData][0] = resultSet.getString("id");
                            data[jumlahData][1] = resultSet.getString("username");
                            data[jumlahData][2] = resultSet.getString("password");
                            jumlahData++;
                        }
                        connector.statement.close();
                        for(int i = 0; i <= jumlahData; i++){
                            if(getUdaftar().equals(data[i][1])){
                                checkDaftar = 1;
                                JOptionPane.showMessageDialog(null, "Username telah digunakan!");
                            }
                        }
                        if(checkDaftar != 1){
                            String queryInsert = "INSERT INTO `users`(`username`, `password`) VALUES ('"+getUdaftar()+"','"+getPdaftar()+"')";
                            connector.statement = connector.koneksi.createStatement();
                            connector.statement.executeUpdate(queryInsert);
                            JOptionPane.showMessageDialog(null,"Berhasil mendaftar!");
                        }
                    }catch(Exception error){
                        JOptionPane.showMessageDialog(null, error.getMessage()); 
                    }
                }
            }
        }
    
            
}
