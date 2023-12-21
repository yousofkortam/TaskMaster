import javax.swing.*;

public class ShowStudentAnswer extends JFrame {
    public ShowStudentAnswer() {
        super("Details ans Student Answers");
        setLayout(null);

        JButton addassign = new JButton("Assignment Details");
        addassign.setBounds(0, 0, 260, 350);
        add(addassign);

        JButton displayassign = new JButton("Student Answers");
        displayassign.setBounds(260, 0, 260, 350);
        add(displayassign);
    }
}
