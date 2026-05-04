package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.Config;

import static io.restassured.RestAssured.given;

public class WompiClient {

        public Response createTransaction(Object body) {
                return given()
                                .baseUri(Config.get("base.url"))
                                .header("Authorization", "Bearer " + Config.get("private.key"))
                                .contentType(ContentType.JSON)
                                .body(body)
                                .when()
                                .post("/transactions")
                                .then()
                                .extract().response();
        }

        public Response createTransactionWithInvalidKey(Object body) {
                return given()
                                .baseUri(Config.get("base.url"))
                                .header("Authorization", "Bearer INVALID")
                                .contentType(ContentType.JSON)
                                .body(body)
                                .when()
                                .post("/transactions")
                                .then()
                                .extract().response();
        }

        public String getAcceptanceToken() {
                return given()
                                .baseUri(Config.get("base.url"))
                                .when()
                                .get("/merchants/" + Config.get("public.key"))
                                .then()
                                .extract()
                                .path("data.presigned_acceptance.acceptance_token");
        }

}
