package com.qacart.todo.apis;

import com.qacart.todo.base.Specs;
import com.qacart.todo.data.Route;
import com.qacart.todo.models.Todo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TodoApi {
    public static Response addTodo( Todo todo,String token )
        {
            return given ( )
                    .spec ( Specs.getRequestSpec () )
                    .auth ( ).oauth2 ( token )
                    .body ( todo )
                    .when ( ).post ( Route.TODOS_ROUTE )
                    .then ( )
                    .log ( ).all ( )
                    .extract ( ).response ( );
        }
    public static Response getTodo(String taskId,String token)
            {
                return given ()
                        .spec ( Specs.getRequestSpec () )
                        .auth ().oauth2 (  token)
                        .when ().get ( Route.GETTODO_ROUTE+"/"+taskId )
                        .then ()
                        .log ().all ()
                        .extract ().response ();
            }
    public static Response deleteTodo(String taskId,String token)
        {
            return given ()
                    .spec ( Specs.getRequestSpec () )
                    .auth ().oauth2 ( token )
                    .when ().delete ( Route.DELETE_ROUTE+"/"+taskId )
                    .then ()
                    .log ().all ()
                    .extract ( ).response ( );
        }





}
