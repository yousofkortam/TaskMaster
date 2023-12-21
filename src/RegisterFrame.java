import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.lang.String;

public class RegisterFrame extends JFrame {
    public static String role = "";
    public static String subj = "";

    public RegisterFrame() {
        super("Create an account");
        setLayout(null);

        JLabel back = new JLabel("<-Back");
        back.setBounds(2, 2, 50, 10);
        add(back);

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MainClass.Login();
                setVisible(false);
            }
        });

        JLabel fullName = new JLabel("Full name");
        fullName.setBounds(100, 40, 90, 10);
        add(fullName);

        JTextField nameText = new JTextField("");
        nameText.setBounds(220, 35, 200, 25);
        add(nameText);

        JLabel user = new JLabel("Username");
        user.setBounds(100, 85, 90, 10);
        add(user);


        JTextField userText = new JTextField("");
        userText.setBounds(220, 80, 200, 25);
        add(userText);


        JLabel password = new JLabel("Password");
        password.setBounds(100, 130, 90, 10);
        add(password);

        JPasswordField passwordText = new JPasswordField("");
        passwordText.setBounds(220, 125, 200, 25);
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

        String[] subject = {"Choose Subject", "Programming 2", "Operating System", "Network-1", "Maths3", "E-Business", "Modeling & Simulation", "Computer Organization"};
        JComboBox sub = new JComboBox(subject);

        JButton submit = new JButton("Register");
        submit.setBounds(205,240, 100, 35 );
        add(submit);

        submit.addActionListener(actionEvent -> {
            String fullname = nameText.getText();
            String userName = userText.getText();
            String Password = String.valueOf(passwordText.getPassword());
            if (teacher.isSelected()) {
                role = "Teacher";
            }else if (student.isSelected()) {
                role = "Student";
            }else {
                role = "";
            }

            // Check Validation Login Form
            if (fullname.equals("")) { JOptionPane.showMessageDialog(null, "Please enter all data..."); return; }
            if (userName.equals("")) { JOptionPane.showMessageDialog(null, "Please enter all data..."); return; }
            if (password.equals("")) { JOptionPane.showMessageDialog(null, "Please enter all data..."); return; }
            if (role.equals("")) { JOptionPane.showMessageDialog(null, "Please enter all data..."); return; }

            try {
                Connection con = database.connection();
                PreparedStatement stat;

                if (role.equals("Teacher")) {

                    if (userFound(userName, "teacher_user")) {
                        JOptionPane.showMessageDialog(null, "username is already used");
                        return;
                    }

                    int input = JOptionPane.showConfirmDialog(this,  sub, "Select subject", JOptionPane.DEFAULT_OPTION);
                    if (input == JOptionPane.OK_OPTION) {
                        subj = (String) sub.getSelectedItem();
                    }
                    
                    stat = con.prepareStatement("INSERT INTO teacher_user VALUES (?, ?, ?, ?, ?)");

                    stat.setString(1, null);
                    stat.setString(2, fullname);
                    stat.setString(3, userName);
                    stat.setString(4, Password);
                    stat.setString(5, subj);
                    stat.executeUpdate();

                }else if (role.equals("Student")) {

                    if (!userFound(userName, "student_user")) {
                        stat = con.prepareStatement("INSERT INTO student_user VALUES (?, ?, ?, ?)");

                        stat.setString(1, null);
                        stat.setString(2, fullname);
                        stat.setString(3, userName);
                        stat.setString(4, Password);
                        stat.executeUpdate();
                    } else {
                        JOptionPane.showMessageDialog(null, "username is already used");
                        return;
                    }

                }

                JOptionPane.showMessageDialog(null, "Success..!!\nBack to LOGIN page");

                MainClass.Login();
                setVisible(false);

            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    boolean userFound(String newUser, String table) {

        try {
            Connection con = database.connection();
            ResultSet rs = database.result_set(con, "SELECT * FROM "+table+" WHERE username = '"+newUser+"' ");
            if (rs.absolute(1)){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
