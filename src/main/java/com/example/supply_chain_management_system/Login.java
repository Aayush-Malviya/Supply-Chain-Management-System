package com.example.supply_chain_management_system;

import java.sql.ResultSet;

public class Login {
    public boolean customerLogin(String email, String password){
        String query = String.format("SELECT * FROM CUSTOMER WHERE email = '%s' AND password = '%s'",email, password );
        try{
            DatabaseConnection dbCon = new DatabaseConnection();
            ResultSet rs = dbCon.getQueryTable(query);
            if(rs != null && rs.next()){
                return true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) { //write psvm + enter for shortcut
        Login login = new Login();
        System.out.print(login.customerLogin("aayushmalviya91@gmail.com", "abc123"));
    }
}
