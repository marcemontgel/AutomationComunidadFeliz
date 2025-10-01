Feature: Configuración de egresos con campo Fecha Facturación Activado

  Background:
    Given El usuario navega a la pagina de login
    When El usuario escribe las credenciales "usuariopruebaQA@comunidadfeliz.com" con password "Prueba2025."
    Given El usuario ingresa al menú lateral
    When El usuario busca el módulo "Configurar comunidad" muestra "Configurar comunidad" selecciona "Configurar comunidad"
    Given El usuario abre la configuración de la comunidad
    When El usuario ingresa al panel de egresos e ingresos
    And El usuario selecciona la opción "Activado" en el campo fecha de facturación en egresos
    And Guarda los cambios
    When El usuario busca el módulo "Egresos" muestra "Egresos" selecciona "Egresos"

  @regression
  Scenario: Agregar nuevo egreso a usuario existente categoría Reparación
    Given El usuario ingresa al menú de Egresos
    When El usuario ingresa a Nuevo Egreso
    And El usuario completa el formulario de egreso con categoría "Reparación"
    And El usuario selecciona el proveedor "Alvaro Rey"
    And El usuario ingresa descripción "Reparaciones Comunidad" y monto "10000"
    And El usuario ingresa fecha de pago "21/10/2025"
    And El usuario ingresa el número de cuotas "5"
    And El usuario ingresa fecha de facturación "23/10/2025"
    Then El usuario guarda los cambios

  @regression
  Scenario: Agregar nuevo egreso a usuario nuevo categoría Uso y Consumo subcuenta proveedores
    Given El usuario ingresa al menú de Egresos
    When El usuario ingresa a Nuevo Egreso
    And El usuario completa el formulario de egreso con categoría "Uso o consumo"
    And El usuario selecciona el proveedor "Otro"
    And El usuario ingresa información de nombre "Carlos Mejia" rut "88.888.888-8" y subcuenta "Proveedores"
    And El usuario ingresa descripción "Uso y Consumo Comunidad" y monto "20000"
    And El usuario ingresa fecha de pago "21/10/2025"
    And El usuario ingresa el número de cuotas "2"
    And El usuario ingresa fecha de facturación "23/10/2025"
    Then El usuario guarda los cambios

  @regression
  Scenario: Agregar nuevo egreso a usuario nuevo categoría Otra subcuenta Remuneraciones por pagar
    Given El usuario ingresa al menú de Egresos
    When El usuario ingresa a Nuevo Egreso
    And El usuario completa el formulario de egreso con categoría "Otra"
    And El usuario selecciona el proveedor "Otro"
    And El usuario ingresa información de nombre "Karol Gil" rut "66.666.666-6" y subcuenta "Remuneraciones por pagar"
    And El usuario ingresa descripción "Pago servicio de Agua" y monto "50000"
    And El usuario ingresa fecha de pago "21/10/2025"
    And El usuario ingresa el número de cuotas "4"
    And El usuario ingresa fecha de facturación "23/10/2025"
    Then El usuario guarda los cambios

