
package Controlers;

/**
 *
 * @author Perera's
 */
import java.util.Date;
import java.util.Timer;
//import java.util.Timertask;
import java.time.LocalDate;
public class Reminder 
{
    
   private int remNumber;
   private String remName;
   
   private Date remDate;
   private Date createDate;
   
   
   
   //LocalDate date1;

    public int getRemNumber() 
    {
        return remNumber;
    }

    public void setRemNumber(int remNumber) 
    {
        this.remNumber = remNumber;
    }

    public String getRemName() 
    {
        return remName;
    }

    public void setRemName(String remName) 
    {
        this.remName = remName;
    }

    public Date getRemDate() 
    {
        return remDate;
    }

    public void setRemDate(Date remDate) 
    {
        this.remDate = remDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }

    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }
   
    
    

}
   
 
       
  
   
    

