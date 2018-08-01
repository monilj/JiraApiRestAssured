package ContractTest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Jira extends BaseClassForJiraApi{

    @Test
    public void JiraAPICreateIssue() throws IOException {
        //Creating Issue/Defect

        RestAssured.baseURI = getBaseUri();
        Response res = given().header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionKEY()).
                body(FileUtils.readFromClassPath("JiraAPICreateIssue.json")).when().
                post("/rest/api/2/issue").then().assertThat().statusCode(201).extract().response();

        JsonPath js = ReusableMethods.rawToJson(res);
        String id = js.get("id");
        System.out.println(id);

    }

    @Test
    public void JiraAPICreateComment() throws IOException {

        RestAssured.baseURI =getBaseUri();
        Response res = given().header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionKEY()).
                        body(FileUtils.readFromClassPath("JiraCreateComment.json")).
                        when().
                        post("/rest/api/2/issue/10207/comment/").then().assertThat().statusCode(201).extract().response();
        JsonPath js = ReusableMethods.rawToJson(res);
        String id = js.get("id");
    }

    @Test
    public void JiraAPIUpdate() throws IOException {

        RestAssured.baseURI = getBaseUri();
        Response res = given().header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionKEY()).
                pathParams("commentid", "10103").
                body(FileUtils.readFromClassPath("JiraUpdateComment.json")).
                when().
                put("/rest/api/2/issue/10207/comment/{commentid}")
                .then().assertThat().statusCode(200).extract().response();

    }

}
