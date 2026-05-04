package builders;

import java.util.HashMap;
import java.util.Map;

public class TransactionBuilder {

    private Map<String, Object> body = new HashMap<>();

    public TransactionBuilder defaultData(String reference) {

        body.put("reference", reference);
        body.put("currency", "COP");
        body.put("customer_email", "test@test.com");

        Map<String, Object> paymentMethod = new HashMap<>();
        paymentMethod.put("type", "PSE");
        paymentMethod.put("user_type", 0);
        paymentMethod.put("user_legal_id_type", "CC");
        paymentMethod.put("user_legal_id", "123456789");
        paymentMethod.put("financial_institution_code", "1022");
        paymentMethod.put("payment_description", "Pago de prueba");

        body.put("payment_method", paymentMethod);

        return this;
    }

    public TransactionBuilder withAmount(int amount) {
        body.put("amount_in_cents", amount);
        return this;
    }

    public TransactionBuilder withEmail(String email) {
        body.put("customer_email", email);
        return this;
    }

    public TransactionBuilder withSecurity(String acceptanceToken, String signature) {
        body.put("acceptance_token", acceptanceToken);
        body.put("signature", signature);
        return this;
    }

    public Map<String, Object> build() {
        return body;
    }
}