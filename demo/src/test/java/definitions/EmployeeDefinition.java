package definitions;

import apiengine.Endpoint;
import context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class EmployeeDefinition {
    public static String baseUrl;
    public static Response response;
    public static String token;
    private final TestContext context;

    public EmployeeDefinition(TestContext context) {
        this.context = context;
    }

    @When("Send employee to register with body:")
    public void send_request_register(String body) {
        Endpoint endpoint = new Endpoint();
        response = endpoint.registerEmployee(body);
        context.setResponse(response);
    }

    @When("Send employee to login with body:")
    public void send_request_login(String body) {
        Endpoint endpoint = new Endpoint();
        response = endpoint.loginEmployee(body);
        context.setResponse(response);
    }

    @Then("The response status must be {int}")
    public void send_request_http(int statusCode) {
        assert context.getResponse().statusCode() == statusCode
                : "Error, due to actual status code is " + context.getResponse().statusCode();
    }

    @And("The response schema should be match with schema {string}")
    public void schema_validation(String schemaPath) {
        context.getResponse().then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
    }

    @And("Save the token from the response to local storage")
    public void save_the_token() {
        context.set(token, context.getResponse().jsonPath().getString("token"));
    }

    @Given("Make sure token in local storage not empty")
    public void assert_token_in_variable() {
        assert context.get(token, String.class) != null : "Token in context null";
    }
}
