package com.svt.todoapp.models;

import com.svt.todoapp.models.enums.ProjectStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "projects")
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    public Project() {
    }

    public Project(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = ProjectStatus.CREATED;
    }

    public void addTask(Task task){
        tasks.add(task);
        task.setProject(this);
    }

    public void removeTask(Task task){
        tasks.remove(task);
        task.setProject(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, status, tasks);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", projectStatus=" + status +
                ", tasks=" + tasks +
                '}';
    }
}
