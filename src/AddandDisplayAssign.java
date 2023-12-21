import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddandDisplayAssign extends JFrame {

    public AddandDisplayAssign() {
        super("Add or Display Assignment");

        setLayout(null);

        JLabel back = new JLabel("<-Back");
        back.setBounds(2, 2, 50, 10);
        add(back);

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (LoginFrame.choose.equals("Student")) {
                    MainClass.SubjectFrame();
                    setVisible(false);
                }else {
                    MainClass.Login();
                    setVisible(false);
                }
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

        JButton addassign = new JButton("Add Assignment");
        addassign.setBounds(0, 15, 260, 350);
        add(addassign);


        addassign.addActionListener(actionEvent -> {
            MainClass.addAssign();
            setVisible(false);
        });

        JButton displayassign = new JButton("Display Assignment");
        displayassign.setBounds(260, 15, 260, 350);
        add(displayassign);

        displayassign.addActionListener(actionEvent -> {
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
            MainClass.display();
            setVisible(false);
        });
    }



}
