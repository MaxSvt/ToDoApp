package com.svt.todoapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.svt.todoapp.models.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    @JsonIgnore
    private User author;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "performer_id")
    @JsonIgnore
    private User performer;

    private Date createdDate;

    private Date changedDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore
    private Project project;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public Task() {
    }

    public Task(String title, String description, User author, User performer) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.performer = performer;
        this.status = TaskStatus.NEW;
        this.createdDate = new Date();
        this.changedDate = new Date();
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", changedDate=" + changedDate +
                '}';
    }

    public void addComment(Comment comment){
        comments.add(comment);
        comment.setTask(this);
    }

    public void removeComment(Comment comment){
        comments.remove(comment);
        comment.setTask(null);
    }
}
