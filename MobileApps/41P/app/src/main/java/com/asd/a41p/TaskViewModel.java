package com.asd.a41p;

// TaskViewModel.java
public class TaskViewModel extends AndroidViewModel {
    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        TaskDatabase database = TaskDatabase.getInstance(application);
        taskDao = database.taskDao();
        allTasks = taskDao.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public void insert(Task task) {
        new InsertTaskAsyncTask(taskDao).execute(task);
    }

    private static class InsertTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao taskDao;
        private InsertTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.insert(tasks[0]);
            return null;
        }
    }
}