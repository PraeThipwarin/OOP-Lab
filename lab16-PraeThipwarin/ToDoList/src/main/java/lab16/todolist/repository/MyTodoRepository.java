package lab16.todolist.repository;

import lab16.todolist.model.TodoItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository("MyTodoRepository")
public class MyTodoRepository implements TodoRepository {
    private List<TodoItem> todoItems;

    public MyTodoRepository() {
        this.todoItems = new ArrayList<>();
    }

    @Override
    public List<TodoItem> findAll() {
        return todoItems;
    }

    @Override
    public List<TodoItem> findByCompleted(boolean completed) {
        List<TodoItem> result = new ArrayList<>();
        for (TodoItem todoItem : todoItems){
            if (todoItem.getCompleted() == completed){
                result.add(todoItem);
            }
        }
        return result;
    }

    @Override
    public TodoItem add(String title) {
        TodoItem todoItem = new TodoItem(title);
        todoItems.add(todoItem);

        return todoItem;
    }

    @Override
    public TodoItem add(TodoItem item) {
        todoItems.add(item);
        return item;
    }

    @Override
    public void deleteById(int id) {
        for (TodoItem todoItem : todoItems){
            if(todoItem.getId() == id){
                todoItems.remove(todoItem);
                return;
            }
        }
    }

    @Override
    public void updateById(int id) {
        for (TodoItem todoItem : todoItems){
            if(todoItem.getId() == id){
                todoItem.setCompleted(true);
                return;
            }
        }
    }

    @Override
    public List<TodoItem> search(String title) {
        List<TodoItem> result = new ArrayList<>();
        for (TodoItem todoItem : todoItems){
            if(Objects.equals(todoItem.getTitle(), title)){
                result.add(todoItem);
            }
        }
        return result;
    }
}
