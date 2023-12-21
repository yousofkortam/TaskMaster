import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Arrays;

public class Details extends JFrame {

    public static String [][] data = new String[1000][3];
    public Details() {
        super("Details");

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

        clear(data);

        data[0][0] = "Student id";
        data[0][1] = "Student name";
        data[0][2] = "Student Answer";

        try {
            Connection con = database.connection();
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM student_answer WHERE subject = '"+ChooseSubjectFrame.subject+"' AND assign_name = '"+AllAssignments.assign_name+"' ");
            int j = 1;
            while (rs.next()) {
                data[j][0] = rs.getString("student_id");
                data[j][1] = rs.getString("student_name");
                data[j][2] = rs.getString("Answer");
                j++;
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        String[] columns = {"Student name", "answer", "degree"};

        JTable st_details = new JTable(data, columns);
        st_details.setBounds(2, 20, 520, 290);
        JScrollPane sp = new JScrollPane(st_details);
        add(st_details); st_details.add(sp);
    }

    void clear(String[][] arr) {
        for (int i=0; i<1000; i++) {
            Arrays.fill(arr[i], null);
        }
    }
}
