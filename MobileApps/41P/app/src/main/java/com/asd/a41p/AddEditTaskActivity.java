package com.asd.a41p;

// AddEditTaskActivity.java
public class AddEditTaskActivity extends AppCompatActivity {
    private EditText editTextTitle, editTextDescription;
    private TextView textViewDueDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        textViewDueDate = findViewById(R.id.text_view_due_date);
        Button buttonSave = findViewById(R.id.button_save);

        textViewDueDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                textViewDueDate.setText(date);
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        });

        buttonSave.setOnClickListener(v -> {
            saveTask();
        });
    }

    private void saveTask() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String dueDate = textViewDueDate.getText().toString();

        if (title.trim().isEmpty() || dueDate.trim().isEmpty()) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Task task = new Task(title, description, dueDate);
        TaskDatabase.getInstance(this).taskDao().insert(task);
        finish();
    }
}
