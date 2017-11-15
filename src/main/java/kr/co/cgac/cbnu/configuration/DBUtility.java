package kr.co.cgac.cbnu.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by cgac_315 on 6/16/2017.
 */
public class DBUtility {

    public static Connection getConnection(){
        Connection cnn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnn = DriverManager.getConnection("jdbc:mysql://210.125.146.146:3306/ApartmentDesignDB?useUnicode=true&characterEncoding=UTF-8", "cgac", "gac81-344");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cnn;
    }
}
