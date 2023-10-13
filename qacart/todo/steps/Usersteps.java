package com.qacart.todo.steps;

import com.github.javafaker.Faker;
import com.qacart.todo.apis.UserApi;
import com.qacart.todo.models.User;
import io.restassured.response.Response;

public class Usersteps {
    public static User generateUser()
        {
         Faker faker = new Faker();
        String firstName=faker.name().fullName();;
        String lastName=faker.name().firstName();
        String email=faker.internet ().emailAddress ();
        String password="ilikeQAcart";
        return new User (firstName,lastName,email,password  );
    }
    public static User getRegisteredUser()
        {
            User user=generateUser ();
            UserApi.register ( user );
            return user;
        }
    public static String getUserToken()
    {
        User user=generateUser ();
        Response response=UserApi.register ( user);
        return response.body ().path ("access_token"  );


    }




}
