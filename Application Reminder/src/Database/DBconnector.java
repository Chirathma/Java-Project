package Database;
/**
 *
 * @author Devni
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnector 
{
    public static Connection getConnection()
    {
        Connection con;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "123");
            return con;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    
   
}