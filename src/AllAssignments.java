import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Vector;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class AllAssignments extends JFrame {
    public static String ans = "";

    public static String assign_name;
    static Vector<String> Assign_names = new Vector<>();
    public AllAssignments() {
        super("All " + ChooseSubjectFrame.subject + " Assignments");

        setLayout(null);

        JLabel back = new JLabel("<-Back");
        back.setBounds(2, 2, 50, 10);
        add(back);

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (LoginFrame.choose.equals("Student"))
                    MainClass.SubjectFrame();
                else {
                    MainClass.addAndDisplay();
                }
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


        JLabel choose = new JLabel("All Assignments");
        choose.setBounds(100, 110, 120, 40);
        add(choose);

        JComboBox sub = new JComboBox(Assign_names);
        sub.setBounds(240, 112, 170, 30);
        add(sub);

        JButton next = new JButton("DETAILS");
        next.setBounds(205,220, 100, 35);
        add(next);

        next.addActionListener(actionEvent -> {
            assign_name = (String) sub.getSelectedItem();
            AssignmentDetails.head = assign_name;
            if (AllAssignments.Assign_names.size() == 1) {
                JOptionPane.showMessageDialog(null, "There are no assignments");
                return;
            }else if (assign_name.equals("Assignments")) {
                JOptionPane.showMessageDialog(null, "Please choose the assignment name");
                return;
            }

            try {
                Connection con = database.connection();
                Statement stat = con.createStatement();
                ResultSet rs = stat.executeQuery("SELECT * FROM assignment_info WHERE subject = '"+ChooseSubjectFrame.subject+"' AND Assign_name = '"+assign_name+"' ");
                boolean found = false;
                while (rs.next()) {
                    ans = rs.getString("Assign_file_question");
                    found = true;
                }
                if (!found) {
                    ans = "";
                }
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            MainClass.AssignDetails();
            setVisible(false);
        });
    }

}
