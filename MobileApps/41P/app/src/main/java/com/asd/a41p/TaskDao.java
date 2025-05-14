package com.asd.a41p;

// TaskDao.java
@Dao
public interface TaskDao {
    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("SELECT * FROM task_table ORDER BY dueDate ASC")
    LiveData<List<Task>> getAllTasks();
}