package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module;

import androidx.annotation.Nullable;

public class Module {
    private String moduleName;
    private String assignmentsName;
    private String assignmentsDate;
    private String examName;
    private String examDate;
    private String lessonDateTime;
    private String lessonRoom;


    public Module(String moduleName, String assignmentsName, String assignmentsDate, String examName, String examDate, String lessonDateTime, String lessonRoom) {
        this.moduleName = moduleName;
        this.assignmentsName = assignmentsName;
        this.assignmentsDate = assignmentsDate;
        this.examName = examName;
        this.examDate =examDate;
        this.lessonDateTime =lessonDateTime;
        this.lessonRoom =lessonRoom;



    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getAssignmentsName() {
        return assignmentsName;
    }

    public void setAssignmentsName(String assignmentsName) {
        this.assignmentsName = assignmentsName;
    }

    public String getAssignmentsDate() {
        return assignmentsDate;
    }

    public void setAssignmentsDate(String assignmentsDate) {
        this.assignmentsDate = assignmentsDate;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getLessonDateTime() {
        return lessonDateTime;
    }

    public void setLessonDateTime(String lessonDateTime) {
        this.lessonDateTime = lessonDateTime;
    }

    public String getLessonRoom() {
        return lessonRoom;
    }

    public void setLessonRoom(String lessonRoom) {
        this.lessonRoom = lessonRoom;
    }

    @Override
    public boolean equals(@Nullable Object another) {
        Module otherModule = (Module) another;
        if(otherModule != null){
            return this.moduleName.equals(otherModule.getModuleName());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleName='" + moduleName + '\'' +
                ", assignmentsName='" + assignmentsName + '\'' +
                ", assignmentsDate='" + assignmentsDate + '\'' +
                ", examName='" + examName + '\'' +
                ", examDate='" + examDate + '\'' +
                ", lessonDateTime='" + lessonDateTime + '\'' +
                ", lessonRoom='" + lessonRoom + '\'' +
                '}';
    }



}
