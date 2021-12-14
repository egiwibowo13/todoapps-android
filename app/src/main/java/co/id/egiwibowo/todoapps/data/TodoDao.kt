package co.id.egiwibowo.todoapps.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getAll(): List<Todo>

    @Query("SELECT * FROM todo WHERE id=:id")
    fun getOneTodo(id: Int): List<Todo>

    @Insert
    fun insertAll(vararg todos: Todo)

    @Delete
    fun delete(todo: Todo)
}