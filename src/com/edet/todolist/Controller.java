package com.edet.todolist;

import com.edet.todolist.datamodel.TodoData;
import com.edet.todolist.datamodel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<TodoItem> todoItems;
    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea itemDetailsTextArea;
    @FXML
    private Label deadLineLabel;

    public void initialize(){
        TodoItem item1 = new TodoItem("Mail birthday card", "Buy a 30th birthday card for John", LocalDate.of(2020, Month.JUNE, 25));
        TodoItem item2 = new TodoItem("Doctor's appointment", "See Dr. Smith at 123 Main Street. Bring paperwork", LocalDate.of(2020, Month.MAY, 23));
        TodoItem item3 = new TodoItem("Finish decision proposal for client", "I promised Mike I'd email website mockups by Friday 22nd April", LocalDate.of(2020, Month.APRIL, 22));
        TodoItem item4 = new TodoItem("Pickup Doug at the train station", "Doug's arriving on March 23 on the 5:00 train", LocalDate.of(2020, Month.MARCH, 23));
        TodoItem item5 = new TodoItem("Pickup dry cleaning", "The clothes should be ready by Wednesday", LocalDate.of(2020, Month.APRIL, 20));

        todoItems = new ArrayList<TodoItem>();
        todoItems.add(item1);
        todoItems.add(item2);
        todoItems.add(item3);
        todoItems.add(item4);
        todoItems.add(item5);

        TodoData.getInstance().setTodoItems(todoItems);

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observableValue, TodoItem todoItem, TodoItem t1) {
                if(t1 != null){
                    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                    itemDetailsTextArea.setText(item.getDetails());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM, d, yyyy");
                    deadLineLabel.setText(df.format(item.getDeadLine()));
                }
            }
        });

        todoListView.getItems().setAll(todoItems);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();



    }

    @FXML
    public void handleClickListView(){
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        itemDetailsTextArea.setText(item.getDetails());
        deadLineLabel.setText(item.getDeadLine().toString());
        //System.out.println("The selected item is" + item);
//        StringBuilder sb = new StringBuilder(item.getDetails());
//        sb.append("\n\n\n\n");
//        sb.append("Due: ");
//        sb.append(item.getDeadLine().toString());
//        itemDetailsTextArea.setText(sb.toString());
        //itemDetailsTextArea.setText(item.getDetails());
    }
}
