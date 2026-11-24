import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import static assessments.AssessmentFeed.*;

import static assessments.AssessmentFeed.getResult;

public class SEAssessmentSystem {
    /*
    RULES: Final percentage for the ai asisted software project is 0.4*func + 0.6 verification
    MSC only for viva: 0.5 design +0.5 explanation
    Independent test: if explanation is unsuccessful cap at 40%


    READ: getStudentName(String CID) - string , getCID() - string, getStudentType(String CID) - string
    getResult(CID, assessmentName, subAssessment name)

    PROGRAMMING_TEST - PROGRAM_MARK, EXPLAINED_SUCCESSFULLY
    AI PROJECT - FUNCTIONALITY_MARK, VERIFICATION_MARK
    DESIGN VIVA (Msc only) - DESIGN_MARK, EXPLANATION_MARK

    DO: Load the data from AssessmentFeed.java

    SHOW:
    1. Per student: name, cid, type, each assessment type and final percentage (.2f), total mark
    2. console same thing as above + if AI discrepancy has been met
    3. if the difference between ai an no ai > 20 make red box

    CLASSES: Student with Subclass MsC student
     */
    ArrayList<String> studentCIDs = new ArrayList<>();
    HashMap<String,Student> students = new HashMap<>();

    private int COL_PER_ROW = 4;
    private int NO_ROWS = studentCIDs.size() % COL_PER_ROW;
    JLabel[] cells = new JLabel[studentCIDs.size()];
    private int tick = 0;

    public SEAssessmentSystem() {
        JFrame frame = new JFrame("SE Assessment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel studentPanel = new JPanel(new GridLayout(NO_ROWS, COL_PER_ROW, 10, 10));
        for (int i = 0; i < studentCIDs.size(); i++) {
            cells[i] = new JLabel("", SwingConstants.CENTER);
            cells[i].setOpaque(true);
            cells[i].setBackground(Color.RED);
            studentPanel.add(cells[i]);
        }
        frame.add(studentPanel);
        frame.setSize(400, 400);
        frame.setVisible(true);
        //TO DO: add refresh

        for (String student : assessments.AssessmentFeed.getCIDs()) {
            studentCIDs.add(student);
            System.out.println(student);
        }

        for (String CID : studentCIDs) {
            if(assessments.AssessmentFeed.getStudentType(CID).equals("MSc")){
                MScStudent student = new MScStudent(assessments.AssessmentFeed.getStudentName(CID), CID);
                student.logIPT(
                        getResult(CID, PROGRAMMING_TEST, PROGRAM_MARK),
                        getResult(CID, PROGRAMMING_TEST, EXPLAINED_SUCCESSFULLY)
                );
                student.logAIAPA(
                        getResult(CID, AI_PROJECT, FUNCTIONALITY_MARK),
                        getResult(CID, AI_PROJECT, VERIFICATION_MARK)
                );
                student.logSDV(
                        getResult(CID, DESIGN_VIVA, DESIGN_MARK),
                        getResult(CID, DESIGN_VIVA, EXPLANATION_MARK)
                );
                students.put(CID, student);
            } else {
                Student student = new Student(assessments.AssessmentFeed.getStudentName(CID), CID);
                student.logIPT(
                        getResult(CID, PROGRAMMING_TEST, PROGRAM_MARK),
                        getResult(CID, PROGRAMMING_TEST, EXPLAINED_SUCCESSFULLY)
                );
                student.logAIAPA(
                        getResult(CID, AI_PROJECT, FUNCTIONALITY_MARK),
                        getResult(CID, AI_PROJECT, VERIFICATION_MARK)
                );
                students.put(CID, student);
            }
        }
    }

    private void refresh() {
        for (int i = 0; i < cells.length; i++) {
            if(getStudentType(studentCIDs.get(i)).equals("MSc")){
                MScStudent student = (MScStudent)students.get(studentCIDs.get(i));
                cells[i].setText(String.format("<html>Name: %s<br>CID: %s<br>" +
                        "Independent Programming Test: %.2f<br>" +
                        "AI-Assisted Programming Project: %.2f<br>" +
                        "Design Viva: %.2f<br>" +
                        "Total Mark: %.2f</html>",
                        student.getName(),
                        student.getCID(),
                        student.getIndependentProgrammingTestMark(),
                        student.getAIAssistedProgrammingAssessmentMark(),
                        student.getSoftwareDesignVivaMark(),
                        student.totalMarks()));
            } else{
                Student student = (Student)students.get(studentCIDs.get(i));
                cells[i].setText(String.format("<html>Name: %s<br>CID: %s<br>" +
                                "Independent Programming Test: %.2f<br>" +
                                "AI-Assisted Programming Project: %.2f<br>" +
                                "Total Mark: %.2f</html>",
                        student.getName(),
                        student.getCID(),
                        student.getIndependentProgrammingTestMark(),
                        student.getAIAssistedProgrammingAssessmentMark(),
                        student.totalMarks()));
            }
            if(students.get(studentCIDs.get(i)).overReliant()){
                cells[i].setBackground(Color.RED);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SEAssessmentSystem());
    }
}
