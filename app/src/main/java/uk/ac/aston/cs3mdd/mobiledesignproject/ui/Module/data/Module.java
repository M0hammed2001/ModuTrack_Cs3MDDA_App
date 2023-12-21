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
    @ColumnInfo(name = "AssignmentName1")
    String AssignmentName1;
    @ColumnInfo(name = "AssignmentDate1")
    String AssignmentDate1;

    @ColumnInfo(name = "AssignmentName2")
    String AssignmentName2;
    @ColumnInfo(name = "AssignmentDate2")
    String AssignmentDate2;

    //module exam information
    @ColumnInfo(name = "ExamName")
    String ExamName;

    @ColumnInfo(name = "Examdate")
    String Examdate;

    //room information

    @ColumnInfo(name = "LectureRoom")
    String lectureRoom;

    @ColumnInfo(name = "TutorialRoom")
    String tutorialRoom;



    public Module(String ModuleName, String ModuleCode, String AssignmentName1, String AssignmentDate1, String AssignmentName2, String AssignmentDate2, String ExamName, String Examdate, String tutorialRoom, String lectureRoom) {
        this.ModuleName = ModuleName;
        this.ModuleCode = ModuleCode;
        this.AssignmentName1 = AssignmentName1;
        this.AssignmentDate1 = AssignmentDate1;
        this.AssignmentName2 = AssignmentName2;
        this.AssignmentDate2 = AssignmentDate2;
        this.ExamName = ExamName;
        this.Examdate = Examdate;
        this.tutorialRoom = tutorialRoom;
        this.lectureRoom = lectureRoom;
        this.id = 0;
    }

    public String getAssignmentName1() {
        return AssignmentName1;
    }

    public void setAssignmentName1(String assignmentName1) {
        AssignmentName1 = assignmentName1;
    }

    public String getAssignmentDate1() {
        return AssignmentDate1;
    }

    public void setAssignmentDate1(String assignmentDate1) {
        AssignmentDate1 = assignmentDate1;
    }

    public String getAssignmentName2() {
        return AssignmentName2;
    }

    public void setAssignmentName2(String assignmentName2) {
        AssignmentName2 = assignmentName2;
    }

    public String getAssignmentDate2() {
        return AssignmentDate2;
    }

    public void setAssignmentDate2(String assignmentDate2) {
        AssignmentDate2 = assignmentDate2;
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

//    public String getAssignmentName() {
//        return AssignmentName;
//    }
//
//    public void setAssignmentName(String assignmentName) {
//        AssignmentName = assignmentName;
//    }

//    public String getAssignmentdue() {
//        return Assignmentdue;
//    }
//
//    public void setAssignmentdue(String assignmentdue) {
//        Assignmentdue = assignmentdue;
//    }

//    public String getAssignmentDate() {
//        return AssignmentDate;
//    }
//
//    public void setAssignmentDate(String assignmentDate) {
//        AssignmentDate = assignmentDate;
//    }

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