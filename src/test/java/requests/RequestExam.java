package requests;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RequestExam {
    @Test
    public void testscenarios() {
        //1.
        Response response = null;
        response = RestAssured.given().when().get("https://samples.openweathermap.org/");
        System.out.println("Response 1 : " + response.asString());
        System.out.println("Status code : " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        //2.
        response = given().when().get("http://ergast.com/api/f1/drivers.json?callback=myParser");
        System.out.println("Response 2 : " + response.asString());
        int statusCode = response.getStatusCode();
        // Assert that correct status code is returned.
        Assert.assertEquals(statusCode, 200);
        //3. Weather check
        response = given().when().get("http://restapi.demoqa.com/utilities/weather/city/Istanbul");
        JsonPath jsonPathEvaluator = response.jsonPath();
        String city = jsonPathEvaluator.get("City");
        System.out.println("City received from Response " + city);
        Assert.assertEquals(city, "Istanbul" );
        System.out.println("Temperature received from Response " + jsonPathEvaluator.get("Temperature"));
        System.out.println("Humidity received from Response " + jsonPathEvaluator.get("Humidity"));
        System.out.println("Weather description received from Response " + jsonPathEvaluator.get("Weather"));
        System.out.println("City received from Response " + jsonPathEvaluator.get("WindSpeed"));
        System.out.println("City received from Response " + jsonPathEvaluator.get("WindDirectionDegree"));
        //4.Employee List
        response = given().when().get("http://dummy.restapiexample.com/api/v1/employees");
        JsonPath jsonPathEvaluator2 = response.jsonPath();
        String Name = jsonPathEvaluator2.get("employee_name[1]");
        String Id =jsonPathEvaluator2.get("id[1]");
        System.out.println("Response 4 : " + response.asString());
        System.out.println("Status code : " + response.getStatusCode());
        System.out.println("Second Name"+Name);
        System.out.println("id: "+Id);
        Assert.assertEquals(response.getStatusCode(), 200);
        //5.Ustekini altta param olarak kullanma
        response = given().pathParam("id",Id).when().get("http://dummy.restapiexample.com/api/v1/employee/{id}");
        System.out.println("Response 5 : " + response.asString());
        System.out.println("Status code : " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        //6.Body contains Erwin Howell
        response = given().when().get("https://jsonplaceholder.typicode.com/users");
        RequestSpecification httpRequest = RestAssured.given();
        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();
        Assert.assertEquals(bodyAsString.contains("Ervin Howell"), true);
    }
}