package by.a1qa.models;

public class Project {
    private String name;
    private int testCount;

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTestCount() {
        return testCount;
    }

    public void setTestCount(int testCount) {
        this.testCount = testCount;
    }
}
