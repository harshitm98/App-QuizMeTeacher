package com.example.android.quizmeteacher;

/**
 * Created by Harshit Maheshwari on 02-09-2017.
 */

public class CandidateObject {

    private String registrtionNumber;

    private String marksObtained;

    private String questionsAttempted;

    private String frozen;

    public String getQuestionsAttempted() {
        return questionsAttempted;
    }

    public void setQuestionsAttempted(String questionsAttempted) {
        this.questionsAttempted = questionsAttempted;
    }

    public String getRegistrtionNumber() {
        return registrtionNumber;
    }

    public void setRegistrtionNumber(String registrtionNumber) {
        this.registrtionNumber = registrtionNumber;
    }

    public String getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(String marksObtained) {
        this.marksObtained = marksObtained;
    }

    public String getFrozen() {
        return frozen;
    }

    public void setFrozen(String frozen) {
        this.frozen = frozen;
    }
}
