Feature: Configuración de egresos e ingresos

  Background:
    Given El usuario navega a la pagina de login

  Scenario: Desactivar el campo fecha de facturación en egresos
    When El usuario escribe las credenciales "usuariopruebaQA@comunidadfeliz.com" con password "Prueba2025."
    Then El usuario debería ver el dashboard de la comunidad
    Given El usuario ingresa al menú lateral
    When El usuario busca el módulo "Configurar comunidad"
    Then El sistema debe mostrar la opción "Configurar comunidad"
    And El usuario selecciona la opción "Configurar comunidad"
    Given que el usuario abre la configuración de la comunidad
    When el usuario ingresa al panel de egresos e ingresos
    And selecciona la opción "Desactivado" en el campo fecha de facturación en egresos
    And guarda los cambios

  Scenario: Activar el campo fecha de facturación en egresos
    When El usuario escribe las credenciales "usuariopruebaQA@comunidadfeliz.com" con password "Prueba2025."
    Then El usuario debería ver el dashboard de la comunidad
    Given El usuario ingresa al menú lateral
    When El usuario busca el módulo "Configurar comunidad"
    Then El sistema debe mostrar la opción "Configurar comunidad"
    And El usuario selecciona la opción "Configurar comunidad"
    Given que el usuario abre la configuración de la comunidad
    When el usuario ingresa al panel de egresos e ingresos
    And selecciona la opción "Activado" en el campo fecha de facturación en egresos
    And guarda los cambios
