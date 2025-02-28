package lab16.todolist.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoItem {
    private static int nextId = 0;
    private static int getNextId() {
        return nextId++;
    }

    private int id;
    private String title;
    private Boolean completed = false;

    public TodoItem(String title) {
        this.id = getNextId();
        this.title = title;
    }
    public TodoItem(int id, String title, Boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
}
