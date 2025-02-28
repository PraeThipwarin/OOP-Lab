package lab16.todolist.controller;

import lab16.todolist.dto.CreateTodoItem;
import lab16.todolist.dto.UpdateCreateTodoItem;
import lab16.todolist.model.TodoItem;
import lab16.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class MyController {
    private final TodoRepository todoRepository;

    @Autowired
    public MyController(@Qualifier("MyTodoRepository") TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/")
    public List<TodoItem> getTodoList(@RequestParam(name = "status",required = false) Boolean completed) {
        if(completed != null){
            return todoRepository.findByCompleted(completed);
        }
        return todoRepository.findAll();
    }

    @GetMapping("/search")
    public List<TodoItem> searchTodoList(@RequestParam(required = false) String title) {
        if(title != null){
            return todoRepository.search(title);
        }
        return null;
    }

    @PostMapping("/")
    public TodoItem addTodoItem(@RequestBody CreateTodoItem createTodoItem){
        return todoRepository.add(createTodoItem.getTitle());
    }

    @PostMapping("/upload")
    public List<TodoItem> addTodoItemList(@RequestBody List<UpdateCreateTodoItem> createTodoItems){
        List<TodoItem> todoItems = new ArrayList<>();
        for (UpdateCreateTodoItem item : createTodoItems) {
            TodoItem newItem = new TodoItem(item.getId(), item.getTitle(), item.isCompleted());
            todoItems.add(todoRepository.add(newItem));
        }
        return todoItems;
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable(name="id") int todoId){
        todoRepository.deleteById(todoId);
    }

    @PutMapping("/{id}")
    public void updateItem(@PathVariable(name="id") int todoId){
        todoRepository.updateById(todoId);
    }
}
