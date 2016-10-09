package controllers;

/**
 *
 * @author Perera's
 */
import beans.Reminder;
import Database.DBconnector;
import views.ReminderFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import timer.ReminderTimer;

public class ReminderManager {

    private static ReminderManager instance = null;

    private ReminderManager() {

    }

    public static ReminderManager getInstance() {
        if (instance == null) {
            instance = new ReminderManager();
        }
        return instance;
    }

    public ArrayList<Reminder> getReminders() {
        Connection conn = DBconnector.getConnection();
        ArrayList<Reminder> reminderList = new ArrayList<>();
        try {
            Statement st = conn.createStatement();

            //to check the db
            String selectSql = "SELECT * FROM reminder";

            //String insertSql = "INSERT INTO reminder " + "VALUES ('01', 'MyBDay', '2016-10-09 12:06:00' ,'hnjncje')";
            ResultSet rs = st.executeQuery(selectSql);
            Reminder reminder;
            while (rs.next()) {
                //Retrieve by column name

                String no = rs.getString("NO");
                String name = rs.getString("Name");
                Timestamp datetime = rs.getTimestamp("DateTime");
                String desc = rs.getString("Description");
                reminder = new Reminder(name, no, datetime, desc);
                reminderList.add(reminder);

            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ReminderFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReminderFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return reminderList;
    }

    public Reminder getReminder(String id) {
        Connection conn = DBconnector.getConnection();
        Reminder reminder = null;
        try {
            Statement st = conn.createStatement();

            //to check the db
            String selectSql = "SELECT * FROM reminder WHERE NO=" + id;

            //String insertSql = "INSERT INTO reminder " + "VALUES ('01', 'MyBDay', '2016-10-09 12:06:00' ,'hnjncje')";
            ResultSet rs = st.executeQuery(selectSql);
            while (rs.next()) {
                //Retrieve by column name

                String no = rs.getString("NO");
                String name = rs.getString("Name");
                Timestamp datetime = rs.getTimestamp("DateTime");
                String desc = rs.getString("Description");
                reminder = new Reminder(name, no, datetime, desc);

            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ReminderFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReminderFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return reminder;
    }

    public void addReminder(Reminder reminder) {
        Connection conn = DBconnector.getConnection();

        try {
            Statement st = conn.createStatement();

            String insertSql = "INSERT INTO reminder VALUES (?,?,?,?)";

            /*int key = Integer.parseInt(reminder.getRemNumber());
            DefaultTableModel dtm = (DefaultTableModel) jTabel1.getModel();
            dtm.setRowCount(0);
            for (int i = 0; i < key; i++) {
                int r = jTabel1.getRowCount() + 1;
                dtm.setRowCount(r);*/
            PreparedStatement pstmt = conn.prepareStatement(insertSql);

            int h;
            DefaultTableModel model = new DefaultTableModel();
            h = jTable1.getRowCount() + 1;
            model.setRowCount(h);

            pstmt.setString(1, reminder.getRemNumber());
            pstmt.setString(2, reminder.getRemName());
            pstmt.setTimestamp(3, reminder.getRemDateTime());
            pstmt.setString(4, reminder.getRemDescription());
            pstmt.executeUpdate();

            ReminderTimer.getInstance().startTimer(reminder);

        } catch (SQLException ex) {
            Logger.getLogger(ReminderFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReminderFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteReminder(String no) {
        Connection conn = DBconnector.getConnection();

        try {
            Statement st = conn.createStatement();

            String deleteSql = "DELETE FROM reminder WHERE  NO =?";
            PreparedStatement pstmt = conn.prepareStatement(deleteSql);
            pstmt.setString(1, no);
            pstmt.executeUpdate();
            ReminderTimer.getInstance().cancelTimer(no);

            //ReminderTimer.getInstance().startTimer(reminder);
        } catch (SQLException ex) {
            Logger.getLogger(ReminderFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReminderFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
