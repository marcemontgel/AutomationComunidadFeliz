Feature: Community Dashboard

  Background: Precondicion de Credenciales Válidas
    Given El usuario navega a la pagina de login
    When El usuario escribe las credenciales "usuariopruebaQA@comunidadfeliz.com" con password "Prueba2025."

  @regression
  Scenario: Ingresar al modulo de Egresos
    Given El usuario ingresa al menú lateral
    When El usuario busca el módulo "Egresos" muestra "Egresos" selecciona "Egresos"


  @regression
  Scenario: Ingresar al modulo de Configurar Comunidad
    Given El usuario ingresa al menú lateral
    When El usuario busca el módulo "Configurar comunidad" muestra "Configurar comunidad" selecciona "Configurar comunidad"