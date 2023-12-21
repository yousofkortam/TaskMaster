import javax.swing.*;
import java.lang.String;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChooseSubjectFrame extends JFrame{
    public static String Header;
    public static String subject;
    private final String[] Subject = {"Choose Subject", "Programming 2", "Operating System", "Network-1", "Maths3", "E-Business", "Modeling & Simulation", "Computer Organization"};
    public ChooseSubjectFrame() {
        super("Choose Subject");
        setLayout(null);

        JLabel back = new JLabel("<-Back");
        back.setBounds(2, 2, 50, 10);
        add(back);

        JLabel welcome = new JLabel("Welcome " + user_data.fullName);
        welcome.setBounds(200, 40, 350, 20);
        add(welcome); // getPanel border grid


        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setVisible(false);
                MainClass.Login();
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

        JLabel choose = new JLabel("Choose Subject");
        choose.setBounds(100, 110, 120, 40);
        add(choose);

        JComboBox sub = new JComboBox(Subject);
        sub.setBounds(240, 112, 170, 30);
        add(sub);

        JButton next = new JButton("NEXT");
        next.setBounds(205,220, 100, 35);
        add(next);

        next.addActionListener(actionEvent -> {
            if (sub.getSelectedItem().equals(Subject[0])) {
                JOptionPane.showMessageDialog(null, "Please choose your subject!");
            }else {
                subject = (String) sub.getSelectedItem();
                user_data.subject_name = subject;
                Header = (String) sub.getSelectedItem();

                AllAssignments.Assign_names.clear();
                AllAssignments.Assign_names.add("Assignments");
                try {
                    Connection con = database.connection();
                    Statement stat = con.createStatement();
                    ResultSet rs = stat.executeQuery("SELECT * FROM assign_names WHERE subject = '"+ChooseSubjectFrame.subject+"' ");
                    while (rs.next()) {
                        AllAssignments.Assign_names.add(rs.getString("assign_name"));
                    }
                }catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

                if (LoginFrame.choose.equals("Teacher")) {
                    MainClass.addAndDisplay();
                    setVisible(false);
                }else {
                    MainClass.AllAssign();
                    setVisible(false);
                }
            }
        });

    }



}
