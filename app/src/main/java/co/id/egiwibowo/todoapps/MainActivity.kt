package co.id.egiwibowo.todoapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import co.id.egiwibowo.todoapps.data.AppDatabase
import co.id.egiwibowo.todoapps.data.Todo

class MainActivity : AppCompatActivity(), onClickListener {


    private lateinit var db: AppDatabase
    private lateinit var todoAdapter: TodoAdapter

    private lateinit var rvTodos: RecyclerView
    private lateinit var fab: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTodos = findViewById(R.id.rv_todos)
        fab = findViewById(R.id.fab)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "todo-db")
            .allowMainThreadQueries() // can access at main thread
            .fallbackToDestructiveMigration() // clear data when schema is changes, doesn't need migration anymore
            .build()

        val todoDao = db.todoDao()
        val todos: List<Todo> = todoDao.getAll()

        todoAdapter = TodoAdapter(todos, this)

        rvTodos.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = todoAdapter
        }

        fab.setOnClickListener {
            val intent = Intent(this, CreateTodoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("AAA","onResume masuk lagi")
        val todoDao = db.todoDao()
        val todos: List<Todo> = todoDao.getAll()

        todoAdapter = TodoAdapter(todos, this)

        rvTodos.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = todoAdapter
        }
    }

    private fun generateDummyDataTodos(): List<Todo> {
        val todos: ArrayList<Todo> = arrayListOf()
        todos.add(Todo(1,"title todo 1", "description 1"))
        todos.add(Todo(2,"title todo 2", "description 2"))
        return todos
    }

    override fun onClickItem(todo: Todo) {
        val intent = Intent(this, CreateTodoActivity::class.java)
        intent.apply {
            putExtra("todo", todo)
        }
        startActivity(intent)
    }
}