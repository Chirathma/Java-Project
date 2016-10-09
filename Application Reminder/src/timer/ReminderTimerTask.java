package timer;

import beans.Reminder;
import controllers.ReminderManager;

import java.awt.*;
import java.util.TimerTask;
import javax.swing.*;

public class ReminderTimerTask extends TimerTask {
    String id;

    public ReminderTimerTask(String id) {
        this.id = id;
    }

    @Override
    public void run() {
        JPanel somePanel = new JPanel(new GridBagLayout());
        Reminder reminder = ReminderManager.getInstance().getReminder(id);
        JOptionPane.showMessageDialog(somePanel, reminder.getRemDescription());
    }
}
