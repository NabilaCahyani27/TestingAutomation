package apiengine;

import helper.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Endpoint {
    public Endpoint() {
        // Set the base URI for the API
        String baseUrl = ConfigManager.getBaseUrl();
        RestAssured.baseURI = baseUrl;
    }

    public Response registerEmployee(String bodyRequest){
        Response responseCreateEmployee = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(bodyRequest)
                .log().all()
                .when()
                .post("/webhook/api/register");
        return responseCreateEmployee;
    }

    public Response loginEmployee(String bodyRequest) {
        Response responseLogin = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(bodyRequest)
                .log().all()
                .when()
                .post("/webhook/api/login");
        return responseLogin;
    }

    // public Response getEmployee(String token) {
    //     Response responseGetEmployee = RestAssured.given()
    //             .header("Content-Type", "application/json")
    //             .header("Authorization", "Bearer " + token)
    //             .log().all()
    //             .when()
    //             .get("/webhook/employee/get");
    //     return responseGetEmployee;
    // }

    // public Response updateEmployee(String bodyRequest, String token) {
    //     Response responseUpdateEmployee = RestAssured.given()
    //             .header("Content-Type", "application/json")
    //             .header("Authorization", "Bearer " + token)
    //             .body(bodyRequest)
    //             .log().all()
    //             .when()
    //             .put("/webhook/employee/update");
    //     return responseUpdateEmployee;
    // }

    // public Response deleteEmployee(String token) {
    //     Response responseDeleteEmployee = RestAssured.given()
    //             .header("Content-Type", "application/json")
    //             .header("Authorization", "Bearer " + token)
    //             .log().all()
    //             .when()
    //             .delete("/webhook/employee/delete/");
    //     return responseDeleteEmployee;
    // }

    // public Response getAllEmployee(){
    //     Response responseGetAllEmployee = RestAssured.given()
    //             .header("Content-Type", "application/json")
    //             .log().all()
    //             .when()
    //             .get("/webhook/employee/get_all");
    //     return responseGetAllEmployee;
    // }

    public Response getObjectsById(String token, String id) {
        Response responseGetObjectsById = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log().all()
                .when()
                .get("/webhook/api/objectslistId" + id);
        return responseGetObjectsById;
    }

    public Response addObject(String token, String bodyRequest){
        Response responseAddObject = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(bodyRequest)
                .log().all()
                .when()
                .post("/webhook/api/objects");
        return responseAddObject;
    }
}
