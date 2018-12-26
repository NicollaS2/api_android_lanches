
package App.Class.error;

import App.DataBase.Database;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Teste {
    public static void main(String[] args) throws SQLException{
        try {
            Database database = new Database();
            
            database.open();
        } catch (PiError error) {
            System.out.println(error.getCode());
            System.out.println(error.getMsgErro());
            System.out.println(error.getApplicationName());
            System.out.println(error.getOrganizationName());
        }
    }
}
