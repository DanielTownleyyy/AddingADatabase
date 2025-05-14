package com.asd.a41p;

// TaskAdapter.java
public class TaskAdapter extends ListAdapter<Task, TaskAdapter.TaskHolder> {
    public TaskAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Task> DIFF_CALLBACK = new DiffUtil.ItemCallback<Task>() {
        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.title.equals(newItem.title) &&
                    oldItem.description.equals(newItem.description) &&
                    oldItem.dueDate.equals(newItem.dueDate);
        }
    };

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        Task task = getItem(position);
        holder.textViewTitle.setText(task.title);
        holder.textViewDueDate.setText(task.dueDate);
    }

    class TaskHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDueDate;

        public TaskHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDueDate = itemView.findViewById(R.id.text_view_due_date);
        }
    }
}
