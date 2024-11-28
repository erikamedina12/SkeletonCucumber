@WebTesting @EmergenciasWebTest
  Feature: Test emergencias portal

    Scenario: User make a request to quote a health insurance
      Given I am waiting for the first page is loaded
      Then I am filling the following text boxes
        | Nombre     | Erika Medina |
        | Provincia  | CABA         |
        | Cod. Area  | 11           |
        | Celular    | 34455666     |