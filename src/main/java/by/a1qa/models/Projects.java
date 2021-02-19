package by.a1qa.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Projects {

    private final Set<Project> projects = new HashSet<>();

    public Project getProject(String name) {
        Project project;
        if (projects.stream().noneMatch(o -> Objects.equals(o.getName(), name))) {
            project = new Project(name);
            projects.add(project);
        }
        else {
            project = projects.stream().filter(o -> Objects.equals(o.getName(), name)).findAny().orElse(null);
        }
        return project;
    }
}
