package lab16.todolist.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCreateTodoItem extends CreateTodoItem{
    private int id;
    private boolean completed;

    public UpdateCreateTodoItem(int id,String title,boolean completed) {
        super(title);
        this.id = id;
        this.completed = completed;
    }
}
