import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.*;
import java.sql.*;

public class LoginFrame extends JFrame{
    public static String choose = "";
    public LoginFrame() {
        super("LOGIN");
        setLayout(null);

        JLabel user = new JLabel("Username");
        user.setBounds(100, 60, 90, 10);
        add(user);

        JTextField userText = new JTextField("");
        userText.setBounds(220, 55, 200, 25);
        add(userText);


        JLabel password = new JLabel("Password");
        password.setBounds(100, 120, 90, 10);
        add(password);

        JPasswordField passwordText = new JPasswordField("");
        passwordText.setBounds(220, 115, 200, 25);
        add(passwordText);

        JRadioButton teacher = new JRadioButton("Teacher", false);
        teacher.setBounds(100, 160, 100, 50);
        add(teacher);

        JRadioButton student = new JRadioButton("Student", false);
        student.setBounds(220, 160, 100, 50);
        add(student);

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(teacher);
        radioGroup.add(student);

        teacher.setActionCommand("Teacher");
        student.setActionCommand("Student");

        JButton submit = new JButton("LOGIN");
        submit.setBounds(205,240, 100, 35 );
        add(submit);

        JLabel registerLabel = new JLabel("Register");
        registerLabel.setBounds(220, 280, 100, 35);
        add(registerLabel);

        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MainClass.Register();
                setVisible(false);
            }
        });

        submit.addActionListener(actionEvent -> {
            user_data.username = userText.getText();
            user_data.password = String.valueOf(passwordText.getPassword());
            if (teacher.isSelected()) {
                choose = "Teacher";
            }else if (student.isSelected()) {
                choose = "Student";
            }else {
                choose = "";
            }

            // Check Validation Login Form
            if (choose.equals("")) { JOptionPane.showMessageDialog(null, "Please enter all data..."); return; }
            if (user_data.username.equals("")) { JOptionPane.showMessageDialog(null, "Please enter all data..."); return; }
            if (user_data.password.equals("")) { JOptionPane.showMessageDialog(null, "Please enter all data..."); return; }

            Connection con;
            ResultSet rs;

            if (choose.equals("Teacher")) {
                try {
                    con = database.connection();
                    rs = database.result_set(con, "SELECT * FROM teacher_user");
                    boolean found = false;
                    while (rs.next()) {
                        if (user_data.username.equals(rs.getString("username")) && user_data.password.equals(rs.getString("password"))) {
                            found = true;
                            user_data.fullName = rs.getString("full_name");
                            user_data.id = rs.getInt("id");
                            ChooseSubjectFrame.subject = rs.getString("subject");
                            MainClass.addAndDisplay();
                            setVisible(false);
                        }
                    }
                    if (!found)
                        JOptionPane.showMessageDialog(null, "Incorrect username or password");
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }else {
                try {
                    con = database.connection();
                    rs = database.result_set(con, "SELECT * FROM student_user");
                    boolean found = false;
                    while (rs.next()) {
                        if (user_data.username.equals(rs.getString("username")) && user_data.password.equals(rs.getString("password"))) {
                            found = true;
                            user_data.fullName = rs.getString("full_name");
                            user_data.id = rs.getInt("id");
                            MainClass.SubjectFrame();
                            setVisible(false);
                        }
                    }
                    if (!found)
                        JOptionPane.showMessageDialog(null, "Incorrect username or password");
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

        });
    }

}