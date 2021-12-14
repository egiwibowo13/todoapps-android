package co.id.egiwibowo.todoapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import co.id.egiwibowo.todoapps.data.AppDatabase
import co.id.egiwibowo.todoapps.data.Todo

class CreateTodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_todo)

        val etTitle: EditText = findViewById(R.id.et_title)
        val etDescription: EditText = findViewById(R.id.et_descriptions)
        val btnSave: Button = findViewById(R.id.btn_save)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "todo-db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        val todoDao = db.todoDao()

        btnSave.setOnClickListener {
            todoDao.insertAll(Todo(
                title = etTitle.text.toString(),
                descriptions = etDescription.text.toString()
            ))
            finish()
        }
    }
}