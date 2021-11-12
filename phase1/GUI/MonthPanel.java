import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;

public class MonthPanel extends JPanel implements ActionListener {

    private static final long   serialVersionUID    = 1L;

    protected int               month;
    protected int               year;

    protected String[]          monthNames          = { "January", "February",
            "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December"       };

    protected String[]          dayNames            = { "S", "M", "T", "W",
            "T", "F", "S"                           };

    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private LogIn loginController;
    private GroupController groupController;
    private String studentUsername;
    private CalendarController calendarController;
    private StudentController studentController;

    public MonthPanel(int month, int year, LogIn loginController, GroupController groupController,
                      CalendarController calendarController,
                      StudentController studentController, String studentUsername) {
        this.loginController = loginController;
        this.groupController = groupController;
        this.calendarController = calendarController;
        this.studentController = studentController;
        this.studentUsername = studentUsername;

        this.month = month;
        this.year = year;

        this.add(createGUI());
    }

    protected JPanel createGUI() {
        JPanel monthPanel = new JPanel(true);
        monthPanel.setBorder(BorderFactory
                .createLineBorder(SystemColor.activeCaption));
        monthPanel.setLayout(new BorderLayout());
        monthPanel.setBackground(Color.WHITE);
        monthPanel.setForeground(Color.BLACK);
        monthPanel.add(createTitleGUI(), BorderLayout.NORTH);
        monthPanel.add(createDaysGUI(), BorderLayout.SOUTH);

        return monthPanel;
    }

    protected JPanel createTitleGUI() {
        JPanel titlePanel = new JPanel(true);
        titlePanel.setBorder(BorderFactory
                .createLineBorder(SystemColor.activeCaption));
        titlePanel.setLayout(new FlowLayout());
        titlePanel.setBackground(Color.WHITE);

        JLabel label = new JLabel(monthNames[month] + " " + year);
        label.setForeground(SystemColor.activeCaption);

        titlePanel.add(label, BorderLayout.CENTER);

        return titlePanel;
    }

    protected JPanel createDaysGUI() {
        JPanel dayPanel = new JPanel(true);
        dayPanel.setLayout(new GridLayout(0, dayNames.length));

        Calendar today = Calendar.getInstance();
        int tMonth = today.get(Calendar.MONTH);
        int tYear = today.get(Calendar.YEAR);
        int tDay = today.get(Calendar.DAY_OF_MONTH);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        Calendar iterator = (Calendar) calendar.clone();
        iterator.add(Calendar.DAY_OF_MONTH,
                -(iterator.get(Calendar.DAY_OF_WEEK) - 1));

        Calendar maximum = (Calendar) calendar.clone();
        maximum.add(Calendar.MONTH, +1);

        for (int i = 0; i < dayNames.length; i++) {
            JPanel dPanel = new JPanel(true);
            dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel dLabel = new JLabel(dayNames[i]);
            dPanel.add(dLabel);
            dayPanel.add(dPanel);
        }

        int count = 0;


        int limit = dayNames.length * 6;

        while (iterator.getTimeInMillis() < maximum.getTimeInMillis()) {
            int lMonth = iterator.get(Calendar.MONTH);
            int lYear = iterator.get(Calendar.YEAR);

            JPanel dPanel = new JPanel(true);
            dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel dayLabel = new JLabel();

            if ((lMonth == month) && (lYear == year)) {
                int lDay = iterator.get(Calendar.DAY_OF_MONTH);
                dayLabel.setText(Integer.toString(lDay));
                JButton button = new JButton();
                this.buttons.add(button);
                button.addActionListener(this);
                dPanel.add(button);
                if ((tMonth == month) && (tYear == year) && (tDay == lDay)) {
                    dPanel.setBackground(Color.ORANGE);
                } else {
                    dPanel.setBackground(Color.WHITE);
                }
            } else {
                dayLabel.setText(" ");
                dPanel.setBackground(Color.WHITE);
            }
            dPanel.add(dayLabel);
            dayPanel.add(dPanel);
            iterator.add(Calendar.DAY_OF_YEAR, +1);
            count++;
        }


        for (int i = count; i < limit; i++) {
            JPanel dPanel = new JPanel(true);


            dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel dayLabel = new JLabel();
            dayLabel.setText(" ");
            dPanel.setBackground(Color.WHITE);
            dPanel.add(dayLabel);

            dayPanel.add(dPanel);
        }

        return dayPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i = 0; i < this.buttons.size(); i++) {
            if (e.getSource() == this.buttons.get(i)) {

                int day = i +  1;
                Float date = (float) this.month + 1 + (float)(day) / 100;
                GregorianCalendar calendar = new GregorianCalendar(year, month, day);
                int week = calendar.get(Calendar.DAY_OF_WEEK);

                if(week == 2){
                    System.out.println("Mon");
                } else if (week==3){
                    System.out.println("Tue");
                } else if (week==4){
                    System.out.println("Wed");
                } else if (week==5){
                    System.out.println("Thur");
                } else if (week==6){
                    System.out.println("Fri");
                } else if (week==7){
                    System.out.println("Sat");
                } else if (week==1){
                    System.out.println("Sun");
                }

            }
        }
    }


}
