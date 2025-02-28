package lab16.todolist.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateTodoItem {
    private String title;

    public CreateTodoItem(String title) {
        this.title = title;
    }


}

