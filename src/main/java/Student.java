public class Student {
    private String Name;
    private String CID;
    private double independentProgrammingTestMark;
    private double AIAssistedProgrammingAssessmentMark;

    public Student(String name, String CID) {
        Name = name;
        this.CID = CID;
    }

    //Independent Program Test
    public void logIPT(double programMark, double explainedSuccessfuly){
        if(explainedSuccessfuly == 0.0){
            independentProgrammingTestMark = 40;
        } else if(explainedSuccessfuly == 1.0){
            independentProgrammingTestMark = programMark;
        }
    }
    //AI Assisted Programming Project
    public void logAIAPA(double functionalityMark, double verificationMark){
        AIAssistedProgrammingAssessmentMark = 0.4 * functionalityMark + 0.6 * verificationMark;
    }

    public boolean overReliant() {
        if (AIAssistedProgrammingAssessmentMark >= independentProgrammingTestMark + 20) {
            return true;
        } else {
            return false;
        }
    }

    public double totalMarks() {
        return 0.3 * independentProgrammingTestMark + 0.7 * AIAssistedProgrammingAssessmentMark;
    }

    public String getName() {
        return Name;
    }

    public String getCID() {
        return CID;
    }

    public double getIndependentProgrammingTestMark() {
        return independentProgrammingTestMark;
    }

    public double getAIAssistedProgrammingAssessmentMark() {
        return AIAssistedProgrammingAssessmentMark;
    }
}
