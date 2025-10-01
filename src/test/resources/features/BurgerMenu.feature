Feature: Community Dashboard

  Background:
    Given El usuario navega a la pagina de login

  @regression
  Scenario: Ingresar al modulo de Egresos
    When El usuario escribe las credenciales "usuariopruebaQA@comunidadfeliz.com" con password "Prueba2025."
    Then El usuario debería ver el dashboard de la comunidad
    Given El usuario ingresa al menú lateral
    When El usuario busca el módulo "Egresos"
    Then El sistema debe mostrar la opción "Egresos"
    And El usuario selecciona la opción "Egresos"


  @regression
  Scenario: Ingresar al modulo de Configurar Comunidad
    When El usuario escribe las credenciales "usuariopruebaQA@comunidadfeliz.com" con password "Prueba2025."
    Then El usuario debería ver el dashboard de la comunidad
    Given El usuario ingresa al menú lateral
    When El usuario busca el módulo "Configurar comunidad"
    Then El sistema debe mostrar la opción "Configurar comunidad"
    And El usuario selecciona la opción "Configurar comunidad"