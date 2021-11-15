package frontend;

import backend.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** CreateGroup window is made to join an existing group.*/

public class JoinGroup implements ActionListener {

    private JFrame frame;
    private JTextField groupName;
    private JButton submitButton;
    private JButton cancelButton;

    private LogIn loginController;
    private GroupController groupController;
    private CalendarController calendarController;
    private StudentController studentController;
    private String studentUsername;
    private  JLabel error;

    /** This constructor is used for joining an existing Group, has 5 parameters
     * @param loginController
     * @param groupController
     * @param calendarController
     * @param studentController
     * @param studentUsername
     * */

    public JoinGroup(LogIn loginController, GroupController groupController, CalendarController calendarController,
                     StudentController studentController, String studentUsername){
        this.loginController = loginController;
        this.groupController = groupController;
        this.calendarController = calendarController;
        this.studentController = studentController;
        this.studentUsername = studentUsername;

        this.frame = new JFrame();
        this.frame.setLayout(null);
        this.frame.setSize(500,500);

        JLabel groupNameLabel = new JLabel("Group ID: ");
        groupNameLabel.setBounds(110,50,115, 30);
        this.frame.add(groupNameLabel);

        this.groupName = new JTextField();
        this.groupName.setBounds(210,50,165,30);
        this.frame.add(this.groupName);

        this.submitButton = new JButton("Submit");
        this.submitButton.setBounds(100,100,100,30);
        this.submitButton.addActionListener(this);
        this.frame.add(this.submitButton);

        this.cancelButton = new JButton("Cancel");
        this.cancelButton.setBounds(240,100,100,30);
        this.cancelButton.addActionListener(this);
        this.frame.add(this.cancelButton);

        JLabel error = new JLabel();
        error.setText("Invalid Group ID! Try Again!");
        error.setBounds(100, 100, 300, 300);
        this.error = error;




        this.frame.setVisible(true);
    }

    /** Makes button to perform based on a choice of a user.
     * @param e
     */

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this.submitButton){

            String groupID = this.groupName.getText();

            Student curr = this.studentController.getAllStudents().get(this.studentUsername);
            boolean check = this.groupController.addToGroup(curr, groupID);

            if (check) {
                this.frame.dispose();
                MainMenu menu = new MainMenu(this.loginController, this.groupController, this.calendarController,
                        this.studentController, this.studentUsername);


            } else {


                this.frame.add(error);
                this.frame.revalidate();
                this.frame.repaint();

            }



        }
        else if(e.getSource() == this.cancelButton){
            this.frame.dispose();
            GroupMenu menu = new GroupMenu(this.loginController, this.groupController, this.calendarController,
                    this.studentController, this.studentUsername);
//            going back to GroupMenu
        }
    }
}

