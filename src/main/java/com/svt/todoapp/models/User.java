package com.svt.todoapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    private String email;

    private String username;

    @Column(name = "first_name", nullable = false)
    @NotBlank
    private String firstname;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    private String lastname;

    private boolean isActive;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "projectManager", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> createdTasks = new ArrayList<>();

    @OneToMany(mappedBy = "performer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> receivedTasks = new ArrayList<>();

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProjectParticipant> participants = new ArrayList<>();


    public void removeProject(Project project){
        projects.remove(project);
        project.setProjectManager(null);
    }
}
