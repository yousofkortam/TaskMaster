import javax.swing.*;

public class MainClass {

    public static void main(String[] args) {

        Login();

    }


    public static void Login() {
        LoginFrame login = new LoginFrame();
        login.setSize(520, 350);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        login.setResizable(false);
    }

    public static void Register() {
        RegisterFrame reg = new RegisterFrame();
        reg.setSize(520, 350);
        reg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reg.setLocationRelativeTo(null);
        reg.setVisible(true);
        reg.setResizable(false);
    }

    public static void display() {
        AllAssignments assignmets = new AllAssignments();
        assignmets.setSize(520, 350);
        assignmets.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        assignmets.setLocationRelativeTo(null);
        assignmets.setVisible(true);
        assignmets.setResizable(false);
    }

    public static void addAssign() {
        AddAssignment addAssign = new AddAssignment();
        addAssign.setSize(520, 350);
        addAssign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addAssign.setLocationRelativeTo(null);
        addAssign.setVisible(true);
        addAssign.setResizable(false);
    }


    public static void AssignDetails() {
        AssignmentDetails ASDetails = new AssignmentDetails();
        ASDetails.setSize(520, 350);
        ASDetails.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ASDetails.setLocationRelativeTo(null);
        ASDetails.setVisible(true);
        ASDetails.setResizable(false);
    }

    public static void addanswerFrame() {
        AddAnswer answerFrame = new AddAnswer();
        answerFrame.setSize(520, 350);
        answerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        answerFrame.setLocationRelativeTo(null);
        answerFrame.setVisible(true);
        answerFrame.setResizable(false);
    }

    public static void addAndDisplay() {
        AddandDisplayAssign add = new AddandDisplayAssign();
        add.setSize(520, 350);
        add.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add.setLocationRelativeTo(null);
        add.setVisible(true);
        add.setResizable(false);
    }

    public static void AllAssign() {
        AllAssignments allAssign = new AllAssignments();
        allAssign.setSize(520, 350);
        allAssign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        allAssign.setLocationRelativeTo(null);
        allAssign.setVisible(true);
        allAssign.setResizable(false);
    }
    public static void SubjectFrame() {
        ChooseSubjectFrame subject = new ChooseSubjectFrame();
        subject.setSize(520, 350);
        subject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        subject.setLocationRelativeTo(null);
        subject.setVisible(true);
        subject.setResizable(false);
    }

    public static void stDetails() {
        Details stD = new Details();
        stD.setSize(520, 350);
        stD.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stD.setLocationRelativeTo(null);
        stD.setVisible(true);
        stD.setResizable(false);
    }
}