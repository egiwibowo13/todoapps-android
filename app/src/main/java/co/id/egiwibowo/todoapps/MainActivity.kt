package co.id.egiwibowo.todoapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvTodos: RecyclerView = findViewById(R.id.rv_todos)
        val fab: View = findViewById(R.id.fab)

        val todoAdapter = TodoAdapter(generateDummyDataTodos())

        rvTodos.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = todoAdapter
        }

        fab.setOnClickListener {
            val intent = Intent(this, CreateTodoActivity::class.java)
            startActivity(intent)
        }
    }

    fun generateDummyDataTodos(): List<Todo> {
        val todos: ArrayList<Todo> = arrayListOf()
        todos.add(Todo("title todo 1", "description 1"))
        todos.add(Todo("title todo 2", "description 2"))
        return todos
    }
}