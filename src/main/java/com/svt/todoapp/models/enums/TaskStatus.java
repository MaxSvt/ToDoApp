package com.svt.todoapp.models.enums;

public enum TaskStatus {
    NEW("Новая"),
    IN_WORK("В работе"),
    COMPLETED("Выполнена"),
    CLOSED("Закрыта");

    private final String title;

    TaskStatus(String title) {
        this.title=title;
    }

    public String getTitle(){
        return title;
    }
}
