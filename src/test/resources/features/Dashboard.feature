Feature: Community Dashboard

  Background: Precondicion de Credenciales de acceso válidas
    Given El usuario navega a la pagina de login
    When El usuario escribe las credenciales "usuariopruebaQA@comunidadfeliz.com" con password "Prueba2025."

  @regression
  Scenario: Verificar la UI del Dashboard
    Then El usuario debería ver el dashboard de la comunidad
    Then El usuario verifica que el dashboard de la comunidad se muestre correctamente
    Then El usuario verifica que el nombre de la comunidad sea "Comunidad Prueba"
