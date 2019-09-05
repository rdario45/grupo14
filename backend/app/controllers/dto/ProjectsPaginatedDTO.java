package controllers.dto;

import io.vavr.collection.List;

public class ProjectsPaginatedDTO {

    private List<ProjectDTO> projects;
    private int count;

    public ProjectsPaginatedDTO(List<ProjectDTO> projects, int count) {
        this.projects = projects;
        this.count = count;
    }

    public List<ProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDTO> projects) {
        this.projects = projects;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
