package beans;
/**
 *
 * @author Perera's
 */
import java.sql.Timestamp;

//import java.util.Timertask;

public class Reminder 
{
   private String remNumber;

    public String getRemNumber() {
        return remNumber;
    }

    public String getRemName() {
        return remName;
    }

    public Timestamp getRemDateTime() {
        return remDateTime;
    }

    public String getRemDescription() {
        return remDescription;
    }
   private String remName;
   private Timestamp remDateTime;
   private String remDescription;
  
   public Reminder(String Name, String number, Timestamp datetime, String description)
   {
       this.remName = Name;
       this.remDateTime = datetime;
       this.remDescription = description;
       this.remNumber = number;
   }

    

}
   
 
       
  
   
    

