package lab16.todolist.repository;
import lab16.todolist.model.TodoItem;
import java.util.List;

public interface TodoRepository {
    List<TodoItem> findAll();
    List<TodoItem> findByCompleted(boolean completed);
    TodoItem add(String title);
    TodoItem add(TodoItem item);
    void deleteById(int id);
    void updateById(int id);
    List<TodoItem> search(String title);

}
