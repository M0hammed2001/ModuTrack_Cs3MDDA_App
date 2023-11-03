package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.information;

public class ModuleInformation {

    private String CourseCode;//this i will call to assign it to someone

    private String ModuleCode;//code
    private String BuildingCode;
    private String ModuleName;//title
    private int credits;//credits
    private String description;//description
//    private String learningOutcomes;
    private static int moduleCount;

    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String courseCode) {
        CourseCode = courseCode;
    }

    public String getBuildingCode() {
        return BuildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        BuildingCode = buildingCode;
    }

    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String moduleName) {
        ModuleName = moduleName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static int getModuleCount() {
        return moduleCount;
    }

    public static void setModuleCount(int moduleCount) {
        ModuleInformation.moduleCount = moduleCount;
    }

    public String getModuleCode() {
        return ModuleCode;
    }

    public void setModuleCode(String moduleCode) {
        ModuleCode = moduleCode;
    }

    private static final Integer lock = 0;
    public ModuleInformation() {
        synchronized (lock) {
            moduleCount++;
            CourseCode = "CS3" + moduleCount;
        }
    }
}
