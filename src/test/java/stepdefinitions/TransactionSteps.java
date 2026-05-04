package stepdefinitions;

import java.util.Map;

import builders.TransactionBuilder;
import client.WompiClient;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import utils.Config;
import utils.SignatureUtil;

public class TransactionSteps {

    WompiClient client = new WompiClient();
    TransactionBuilder builder;
    Response response;
    boolean invalidKey = false;

    String reference;
    String acceptanceToken;

    private void prepararRequestBase(int amount) {

        reference = "ref-" + System.currentTimeMillis();
        acceptanceToken = client.getAcceptanceToken();

        builder = new TransactionBuilder()
                .defaultData(reference) 
                .withAmount(amount);
    }

    private void agregarFirma() {

        Map<String, Object> body = builder.build();

        int amount = (int) body.get("amount_in_cents");
        String currency = (String) body.get("currency");

        String signature = SignatureUtil.generate(
                reference,
                amount,
                currency,
                Config.get("integrity.key"));

        builder.withSecurity(acceptanceToken, signature);

        System.out.println("STRING A FIRMAR: " + reference + amount + currency + Config.get("integrity.key"));
        System.out.println("SIGNATURE: " + signature);
        System.out.println("BODY FINAL: " + builder.build());
    }

    private void agregarSeguridad() {

        Map<String, Object> body = builder.build();

        String reference = (String) body.get("reference");
        int amount = (int) body.get("amount_in_cents");
        String currency = (String) body.get("currency");

        String acceptanceToken = client.getAcceptanceToken();

        String signature = SignatureUtil.generate(
                reference,
                amount,
                currency,
                Config.get("integrity.key"));

        builder.withSecurity(acceptanceToken, signature);

        System.out.println("STRING A FIRMAR: " + reference + amount + currency + Config.get("integrity.key"));
        System.out.println("SIGNATURE: " + signature);
        System.out.println("BODY FINAL: " + builder.build());
    }

    // ESCENARIOS

    @Given("tengo un request válido de PSE")
    public void requestValido() {
        prepararRequestBase(150000);
        agregarFirma();
    }

    @Given("tengo un request con monto en cero")
    public void montoCero() {
        prepararRequestBase(0);
        agregarFirma();
    }

    @Given("tengo un request con email inválido")
    public void emailInvalido() {
        prepararRequestBase(150000);
        builder.withEmail("correo_invalido");
        agregarFirma();
    }

    @Given("uso una llave privada inválida")
    public void llaveInvalida() {
        prepararRequestBase(150000);
        agregarFirma();
        invalidKey = true;
    }

    @Given("tengo un request completo con firma")
    public void requestCompleto() {
        prepararRequestBase(150000);
        agregarFirma();
    }

    @Given("tengo un request base de PSE")
    public void requestBasePSE() {
        builder = new TransactionBuilder().defaultData("ref-" + System.currentTimeMillis());
    }

    @Given("asigno el monto {int}")
    public void asignoMonto(Integer monto) {
        builder.withAmount(monto);
        agregarSeguridad(); // 👈 clave: firmas después de setear monto
    }

    @When("envío la solicitud de creación de transacción")
    public void envioSolicitud() {
        if (invalidKey) {
            response = client.createTransactionWithInvalidKey(builder.build());
        } else {
            response = client.createTransaction(builder.build());
        }
    }

    @Then("el status code debe ser {int}")
    public void validarStatusCode(int statusCode) {
        response.then().log().all().statusCode(statusCode);
    }
}