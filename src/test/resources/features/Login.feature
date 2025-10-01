Feature: Login

  Background: Precondicion de Login

    Given El usuario navega a la pagina de login

  @regression @smoke
  Scenario: Credenciales validas
    When El usuario escribe las credenciales "usuariopruebaQA@comunidadfeliz.com" con password "Prueba2025."
    Then El usuario verifica que el dashboard de la comunidad se muestre correctamente

  @regression @smoke
  Scenario: Verificar la UI de la pagina
    Then El usuario verifica que la UI de la pagina de login sea


