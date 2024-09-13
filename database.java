package electricity.billing.system;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class database {
    Connection connection;
    Statement statement;

    database() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bill_system", "root", "Parker@2003");
            this.statement = this.connection.createStatement();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
}

