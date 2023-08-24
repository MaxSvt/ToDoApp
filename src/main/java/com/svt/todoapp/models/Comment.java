package com.svt.todoapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Date createdDate;
    private boolean isUpdated;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    @JsonIgnore
    private Task task;

    public Comment() {
    }

    public Comment(String description) {
        this.description = description;
        this.createdDate = new Date();
        this.isUpdated = false;
    }

    public void changeStatus(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }
}
