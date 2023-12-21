import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class AddAnswer extends JFrame {
    public AddAnswer() {
        super("Add Answer");
        setLayout(null);

        JLabel back = new JLabel("<-Back");
        back.setBounds(2, 2, 50, 10);
        add(back);

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MainClass.AssignDetails();
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

        JLabel addanswer = new JLabel("Answer");
        addanswer.setBounds(225, 15, 90, 10);
        add(addanswer);

        JTextArea answerText = new JTextArea("");
        answerText.setBounds(0, 30, 520, 227);
        add(answerText);

        JButton submitanswer = new JButton("SUBMIT");
        submitanswer.setBounds(205,265, 100, 35);
        add(submitanswer);

        submitanswer.addActionListener(actionEvent -> {
            String st_answer = answerText.getText();

            if (st_answer.equals("")) {
                JOptionPane.showMessageDialog(null, "Please Enter your answer before submit..!");
                return;
            }

            Connection con; PreparedStatement stat;
            try {
                con = database.connection();
                stat = con.prepareStatement("INSERT INTO student_answer VALUES (?, ?, ?, ?, ?, ?) ");

                stat.setString(1, null);
                stat.setInt(2, user_data.id);
                stat.setString(3, user_data.fullName);
                stat.setString(4, ChooseSubjectFrame.subject);
                stat.setString(5, st_answer);
                stat.setString(6, AllAssignments.assign_name);
                stat.executeUpdate();

                JOptionPane.showMessageDialog(null, "Your answer added successfully...");

                MainClass.AllAssign();
                setVisible(false);

            }catch(Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
