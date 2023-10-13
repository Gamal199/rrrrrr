package com.qacart.todo.testcases;
import com.qacart.todo.apis.TodoApi;
import com.qacart.todo.data.ErrorsMessages;
import com.qacart.todo.models.Error;
import com.qacart.todo.models.Todo;
import com.qacart.todo.steps.TodoSteps;
import com.qacart.todo.steps.Usersteps;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TodoTest {
    @Test
    public void shouldBeAbleToAddTodo ( )
        {
           Todo todo= TodoSteps.generateTodo ( );
           String token=Usersteps.getUserToken ();
           Response response= TodoApi.addTodo ( todo,token );
           Todo returnedTodo=response.body ().as ( Todo.class );
           assertThat ( response.statusCode (),equalTo ( 201 ));
           assertThat ( returnedTodo.getItem (),equalTo (todo.getItem ()  ) );
           assertThat ( returnedTodo.getIsCompleted (),equalTo (todo.getIsCompleted ()  ) );
        }
    @Test
    public void shouldNotBeAbleToAddTodoIfISCompletedIsMissing ( )
        {
            Todo todo=new Todo ( "Learn Appium" );
            String token=Usersteps.getUserToken ();
            Response response=TodoApi.addTodo ( todo,token );
            Error returnedError=response.body ().as ( Error.class );
            assertThat (response.statusCode (), equalTo ( 400 ) );
            assertThat ( returnedError.getMessage (),equalTo ( ErrorsMessages.IS_COMPLETED_IS_REQUIRED ) );
        }
    @Test
    public void shouldBeAbleToGetTodoByID ( )
        {
            String token=Usersteps.getUserToken ();
            Todo todo=TodoSteps.generateTodo ();
            String todoID=TodoSteps.getTodoID ( todo,token );
            Response response=TodoApi.getTodo ( todoID,token );
            Todo returnedTodo=response.body ().as ( Todo.class );
            assertThat (response.statusCode(), equalTo ( 200 ) );
            assertThat ( returnedTodo.getIsCompleted (),equalTo ( false ) );
           assertThat ( returnedTodo.getItem (),equalTo ( todo.getItem ()  ) );
        }
    @Test
    public void shouldBeAbleToDeleteTodoByID()
        {
            String token=Usersteps.getUserToken ();
            Todo todo=TodoSteps.generateTodo ();
            String todoID=TodoSteps.getTodoID ( todo,token );
            Response response=TodoApi.deleteTodo ( todoID,token );
            Todo returnedTodo=response.body ().as ( Todo.class );
            assertThat ( response.statusCode(),  equalTo ( 200 ) );
            assertThat ( returnedTodo.getIsCompleted (),equalTo ( false ) );
            assertThat ( returnedTodo.getItem (),equalTo ( todo.getItem () ) );
        }
}
