package com.qacart.todo.testcases;
import com.qacart.todo.apis.UserApi;
import com.qacart.todo.data.ErrorsMessages;
import com.qacart.todo.models.Error;
import com.qacart.todo.models.User;
import com.qacart.todo.steps.Usersteps;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class UserTest {
    @Test
    public void shouldBeAbleToRegister()
        {
            User user= Usersteps.generateUser ();
            Response response= UserApi.register (user  );
            User returnedUser=response.body ().as ( User.class );
            assertThat ( response.statusCode (),equalTo ( 201 ) );
            assertThat ( returnedUser.getFirstName (),equalTo ( user.getFirstName () ) );
        }
    @Test
    public void shouldNotBeAbleToRegisterWithTheSameEmail()
        {
           User user=Usersteps.getRegisteredUser ();
           Response response=UserApi.register ( user );
           Error returnedError=response.body().as ( Error.class );
           assertThat (response.statusCode() , equalTo ( 400 ) );
           assertThat( returnedError.getMessage () ,equalTo( ErrorsMessages.EMAIL_IS_ALREADY_REGISTERED ));
        }
    @Test
    public void shouldBeAbleToLogin()
        {
            User user =Usersteps.getRegisteredUser ();
            User loginData=new User ( user.getEmail (),user.getPassword () );
            Response response=UserApi.login ( loginData );
            User returnedUser=response.body ().as ( User.class );
            assertThat ( response.statusCode (),equalTo ( 200 ) );
            assertThat ( returnedUser.getFirstName (),equalTo ( user.getFirstName ()));
            assertThat ( returnedUser.getAccessToken (),not (  equalTo ( null) ) );
        }
    @Test
    public void shouldNotBeAbleToLoginIfThePasswordIsNotCorrect()
        {
            User user=Usersteps.getRegisteredUser ();
            User loginData=new User ( user.getEmail ( ),"wrongpassword");
            Response response=UserApi.login ( user );
            Error returnedError=response.body ().as ( Error.class );
            assertThat ( response.statusCode (),equalTo ( 400 ) );
            assertThat (returnedError.getMessage (),equalTo ( ErrorsMessages.PASSWORD_OR_PASSWORD_IS_WRONG));
            assertThat ( response.path ( "access_token" ),equalTo ( null )  );
        }
}
