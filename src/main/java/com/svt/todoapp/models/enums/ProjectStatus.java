package com.svt.todoapp.models.enums;

public enum ProjectStatus {
    CREATED("Черновик"),
    IN_WORK("В разработке"),
    IN_TESTING("В тестировании"),
    COMPLETE("Завершен");
    private final String title;

    ProjectStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
