import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class AddAssignment extends JFrame {
    public AddAssignment() {
        super("ADD ASSIGNMENT");
        setLayout(null);

        JLabel back = new JLabel("<-Back");
        back.setBounds(2, 2, 50, 10);
        add(back);

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MainClass.addAndDisplay();
                setVisible(false);
            }
        });

        JLabel logout = new JLabel("Logout");
        logout.setBounds(450, 3, 50, 10);
        add(logout);

        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MainClass.Login();
                setVisible(false);
            }
        });

        JLabel Header = new JLabel("");
        Header.setBounds(200, 0, 200, 60);
        add(Header);
        Header.setText(ChooseSubjectFrame.Header);

        JLabel assign_num = new JLabel("Assign name");
        assign_num.setBounds(100, 65, 130, 10);
        add(assign_num);

        JTextField assing_numText = new JTextField("");
        assing_numText.setBounds(220, 60, 200, 25);
        add(assing_numText);

        JLabel q1 = new JLabel("Question");
        q1.setBounds(100, 108, 90, 10);
        add(q1);

        JTextArea q1Text = new JTextArea("");
        q1Text.setBounds(220, 100, 200, 25);
        add(q1Text);

        JLabel q2 = new JLabel("Deadline date");
        q2.setBounds(100, 148, 100, 10);
        add(q2);

        JTextField q2Text = new JTextField("Year-Month-Day");
        q2Text.setBounds(220, 140, 200, 25);
        add(q2Text);

        q2Text.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (q2Text.getText().equals("Year-Month-Day")) {
                    q2Text.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        JLabel q3 = new JLabel("Model answer");
        q3.setBounds(100, 188, 100, 10);
        add(q3);

        JTextArea q3Text = new JTextArea("");
        q3Text.setBounds(220, 180, 200, 25);
        add(q3Text);


        JButton submit = new JButton("SUBMIT");
        submit.setBounds(205,260, 100, 35);
        add(submit);

        submit.addActionListener(actionEvent -> {
            String subj = ChooseSubjectFrame.subject;
            String question = q1Text.getText();
            String ans = q3Text.getText();
            String assign_name = assing_numText.getText();
            String deadline = q2Text.getText();
            try {
                Connection con = database.connection();
                PreparedStatement stat;

                stat = con.prepareStatement("INSERT INTO assignment_info VALUES (?, ?, ?, ?, ?, ?, ?)");

                stat.setString(1, null);
                stat.setString(2, subj);
                stat.setString(3, assign_name);
                stat.setString(4, user_data.fullName);
                stat.setString(5, question);
                stat.setString(6, ans);
                stat.setString(7, deadline);
                stat.executeUpdate();

                stat = con.prepareStatement("INSERT INTO assign_names VALUES (?, ?, ?)");
                stat.setString(1, null);
                stat.setString(2, assign_name);
                stat.setString(3,ChooseSubjectFrame.subject);
                stat.executeUpdate();

                JOptionPane.showMessageDialog(null, "Question added successfully");

                MainClass.addAndDisplay();
                setVisible(false);

            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
    }


}
