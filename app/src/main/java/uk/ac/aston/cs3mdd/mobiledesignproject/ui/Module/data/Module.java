package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "modules")
public class Module implements Serializable {

    @ColumnInfo(name = "Module_id")
    @PrimaryKey(autoGenerate = true)
    int id;

    //module information
    @ColumnInfo(name = "moduleName")
    String ModuleName;
    @ColumnInfo(name = "ModuleCode")
    String ModuleCode;


    //module assignemnt information
    @ColumnInfo(name = "AssignmentName")
    String AssignmentName;

//    @ColumnInfo(name = "Assignmentdue")
//    String Assignmentdue;// is it due yes or no option to decide if this requires a to be displayed
    @ColumnInfo(name = "AssignmentDate")
    String AssignmentDate;


    //module exam information
    @ColumnInfo(name = "ExamName")
    String ExamName;
//    @ColumnInfo(name = "Examdue")
//    String Examdue;// is it due yes or no option to decide if this requires a to be displayed
    @ColumnInfo(name = "Examdate")
    String Examdate;

    @ColumnInfo(name = "LectureRoom")
    String lectureRoom;

    @ColumnInfo(name = "TutorialRoom")
    String tutorialRoom;



    public Module(String ModuleName, String ModuleCode, String AssignmentName, String AssignmentDate, String ExamName, String Examdate, String tutorialRoom, String lectureRoom) {
        this.ModuleName = ModuleName;
        this.ModuleCode = ModuleCode;
        this.AssignmentName = AssignmentName;
//        this.Assignmentdue = Assignmentdue;
        this.AssignmentDate = AssignmentDate;
        this.ExamName = ExamName;
//        this.Examdue = Examdue;
        this.Examdate = Examdate;
        this.tutorialRoom = tutorialRoom;
        this.lectureRoom = lectureRoom;
        this.id = 0;
    }
    public String getLectureRoom() {
        return lectureRoom;
    }

    public void setLectureRoom(String lectureRoom) {
        this.lectureRoom = lectureRoom;
    }

    public String getTutorialRoom() {
        return tutorialRoom;
    }

    public void setTutorialRoom(String tutorialRoom) {
        this.tutorialRoom = tutorialRoom;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String moduleName) {
        ModuleName = moduleName;
    }

    public String getModuleCode() {
        return ModuleCode;
    }

    public void setModuleCode(String moduleCode) {
        ModuleCode = moduleCode;
    }

    public String getAssignmentName() {
        return AssignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        AssignmentName = assignmentName;
    }

//    public String getAssignmentdue() {
//        return Assignmentdue;
//    }
//
//    public void setAssignmentdue(String assignmentdue) {
//        Assignmentdue = assignmentdue;
//    }

    public String getAssignmentDate() {
        return AssignmentDate;
    }

    public void setAssignmentDate(String assignmentDate) {
        AssignmentDate = assignmentDate;
    }

    public String getExamName() {
        return ExamName;
    }

    public void setExamName(String examName) {
        ExamName = examName;
    }

//    public String getExamdue() {
//        return Examdue;
//    }
//
//    public void setExamdue(String examdue) {
//        Examdue = examdue;
//    }

    public String getExamdate() {
        return Examdate;
    }

    public void setExamdate(String examdate) {
        Examdate = examdate;
    }
}