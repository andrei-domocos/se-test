public class MScStudent extends Student {
    private double independentProgrammingTestMark;
    private double AIAssistedProgrammingAssessmentMark;
    private double softwareDesignVivaMark;
    public MScStudent(String name, String CID) {
        super(name, CID);
    }

    //logging Software Design Viva Mark
    public void logSDV(double designMark, double explanationMark){
        softwareDesignVivaMark = 0.5 * designMark + 0.5 * explanationMark;
    }

    @Override
    public double totalMarks() {
        return 0.25 * independentProgrammingTestMark
                + 0.45 * AIAssistedProgrammingAssessmentMark
                + 0.30 * softwareDesignVivaMark;
    }

    public double getSoftwareDesignVivaMark() {
        return softwareDesignVivaMark;
    }
}
