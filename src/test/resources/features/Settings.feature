Feature: Configuración de egresos e ingresos

  Background: Precondicion de Credenciales Válidas
    Given El usuario navega a la pagina de login
    When El usuario escribe las credenciales "usuariopruebaQA@comunidadfeliz.com" con password "Prueba2025."
    Given El usuario ingresa al menú lateral
    When El usuario busca el módulo "Configurar comunidad" muestra "Configurar comunidad" selecciona "Configurar comunidad"

  @regression
  Scenario: Desactivar el campo fecha de facturación en egresos
    Given El usuario abre la configuración de la comunidad
    When El usuario ingresa al panel de egresos e ingresos
    And El usuario selecciona la opción "Desactivado" en el campo fecha de facturación en egresos
    And Guarda los cambios

  @regression
  Scenario: Activar el campo fecha de facturación en egresos
    Given El usuario abre la configuración de la comunidad
    When El usuario ingresa al panel de egresos e ingresos
    And El usuario selecciona la opción "Activado" en el campo fecha de facturación en egresos
    And Guarda los cambios
