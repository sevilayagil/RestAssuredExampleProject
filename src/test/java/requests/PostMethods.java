package requests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class PostMethods {
    //1.
    @Test
    public void Requests() {
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        double randomnumber = Math.random();
        given().urlEncodingEnabled(true)
                .param("name", "sevilay" + randomnumber)
                .param("salary", "927445495")
                .param("age", "25")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post("/create")
                .then().statusCode(200);
    }
}
