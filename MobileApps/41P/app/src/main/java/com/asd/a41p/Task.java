package com.asd.a41p;

// Task.java (Model)
@Entity(tableName = "task_table")
public class Task {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String title;

    public String description;

    @NonNull
    public String dueDate;

    public Task(@NonNull String title, String description, @NonNull String dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }
}

