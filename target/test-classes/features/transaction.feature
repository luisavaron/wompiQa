Feature: Validación de transacciones PSE en Wompi

  Scenario: Crear transacción exitosa
    Given tengo un request válido de PSE
    When envío la solicitud de creación de transacción
    Then el status code debe ser 201

  Scenario: Monto inválido
    Given tengo un request con monto en cero
    When envío la solicitud de creación de transacción
    Then el status code debe ser 422

  Scenario: Email inválido
    Given tengo un request con email inválido
    When envío la solicitud de creación de transacción
    Then el status code debe ser 422

  Scenario: Llave inválida
    Given uso una llave privada inválida
    When envío la solicitud de creación de transacción
    Then el status code debe ser 401

  Scenario Outline: Validación de límites de monto
    Given tengo un request base de PSE
    And asigno el monto <amount>
    When envío la solicitud de creación de transacción
    Then el status code debe ser <status>

    Examples:
      | amount | status |
      | 149900 | 422    |
      | 150000 | 201    |