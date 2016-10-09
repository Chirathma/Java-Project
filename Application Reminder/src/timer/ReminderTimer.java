package timer;

import beans.Reminder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import controllers.ReminderManager;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 *
 * @author Home
 */
public class ReminderTimer {
    
    private static ReminderTimer instance = null;
    private HashMap<String, Timer> timerMap;
    
    private ReminderTimer() {
        timerMap = new HashMap<>();
    }
    
    public void cancelTimer(String id) {
        Timer timer = timerMap.get(id);
        if( timer != null) {
            timer.cancel();
        };
    }

    public static ReminderTimer getInstance() {
        if(instance==null) {
            instance = new ReminderTimer();
        }
        return instance;
    }
    
    public void startTimer(Reminder reminder) {

        Timer timer = new Timer();
        Timestamp timestamp = reminder.getRemDateTime();
        LocalDateTime fromDateTime = LocalDateTime.now();
        //LocalDateTime toDateTime = LocalDateTime.of(2016, 10, 9, 15, 42, 00);
        System.out.println(fromDateTime);
        LocalDateTime toDateTime = LocalDateTime.of(timestamp.getYear()+1900, timestamp.getMonth()+1
                , timestamp.getDate(), timestamp.getHours(), timestamp.getMinutes(), timestamp.getSeconds());
        System.out.println(toDateTime);
        
        LocalDateTime tempDateTime = LocalDateTime.from(fromDateTime);
        long milliseconds = tempDateTime.until(toDateTime, ChronoUnit.MILLIS);
        System.out.println(milliseconds);
        timer.schedule(new ReminderTimerTask(reminder.getRemNumber()), milliseconds);
    }

    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date;
        try {
            date = dateFormat.parse("09-10-2016 17:21:00");
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            Reminder reminder = new Reminder("1", "blahblah", ts, "aadfaffa");
            new ReminderTimer().startTimer(reminder);
        } catch (ParseException ex) {
            Logger.getLogger(ReminderTimer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
