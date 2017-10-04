/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 07228620674
 */
class DatabaseLocator {
    private static DatabaseLocator instance = null;
    
    private DatabaseLocator(){
        
    }
    
    static DatabaseLocator getInstance() throws ClassNotFoundException, SQLException{
        if(instance == null)
            instance = new DatabaseLocator();
        return instance;
    }
    
    Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT-3&allowMultiQueries=true", "root", "root");
    }
}


