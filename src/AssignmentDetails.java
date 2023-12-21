import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssignmentDetails extends JFrame {
    public static String head;
    public AssignmentDetails() {
        super("Assignment Details");

        setLayout(null);

        JLabel back = new JLabel("<-Back");
        back.setBounds(2, 2, 50, 10);
        add(back);

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MainClass.AllAssign();
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

        JLabel header = new JLabel(head);
        header.setBounds(200, 0, 200, 60);
        add(header);

        JTextArea assign_question = new JTextArea();
        assign_question.setBounds(0, 40, 520, 198);
        assign_question.setText(AllAssignments.ans);
        add(assign_question);

        if (LoginFrame.choose.equals("Student")) {
            JButton addAns = new JButton("ADD ANSWER");
            addAns.setBounds(180,240, 150, 35 );
            add(addAns);
            addAns.addActionListener(actionEvent -> {

                if (foundAnswer()) {
                    JOptionPane.showMessageDialog(null, "You already submitted your answer");
                    return;
                }
                MainClass.addanswerFrame();
                setVisible(false);
            });
        }else {
            JButton stDetailsbtn = new JButton("Student Details");
            stDetailsbtn.setBounds(180,240, 150, 35 );
            add(stDetailsbtn);
            stDetailsbtn.addActionListener(actionEvent -> {
                MainClass.stDetails();
                setVisible(false);
            });
        }
    }

    boolean foundAnswer() {
        try {
            Connection con = database.connection();
            ResultSet rs = database.result_set(con, "SELECT * FROM student_answer WHERE student_id = '"+user_data.id+"' AND subject = '"+ChooseSubjectFrame.subject+"' AND Assign_name = '"+AllAssignments.assign_name+"' ");
            if (rs.absolute(1)) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
