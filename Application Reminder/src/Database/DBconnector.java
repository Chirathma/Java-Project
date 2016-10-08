
package Database;
import java.sql.*;
/**
 *
 * @author Devni
 */
public class DBconnector 
{
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public DBconnector()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:    /test","root","");
            //st con.createStatement();
       
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex);
        }              
    }
    public void getData()
    {
        try{
            String query = "select * from Reminder";
            rs= st.executeQuery(query);
            System.out.println("Records from DB");
            /*while(rs.next())
            {
                String Reminder Name = rs.getString("Name");
                String 
            }*/
        }   
        
        
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}
