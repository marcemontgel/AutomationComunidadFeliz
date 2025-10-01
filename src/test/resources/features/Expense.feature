Feature: Configuración de egresos con campo Fecha Facturación Desactivado

  Background:
    Given El usuario navega a la pagina de login
    When El usuario escribe las credenciales "usuariopruebaQA@comunidadfeliz.com" con password "Prueba2025."
    Given El usuario ingresa al menú lateral
    When El usuario busca el módulo "Configurar comunidad" muestra "Configurar comunidad" selecciona "Configurar comunidad"
    Given El usuario abre la configuración de la comunidad
    When El usuario ingresa al panel de egresos e ingresos
    And El usuario selecciona la opción "Desactivado" en el campo fecha de facturación en egresos
    And Guarda los cambios
    When El usuario busca el módulo "Egresos" muestra "Egresos" selecciona "Egresos"

  @regression
  Scenario: Agregar nuevo egreso a usuario nuevo categoría Gastos extraordinarios subcuenta proveedores
    Given El usuario ingresa al menú de Egresos
    When El usuario ingresa a Nuevo Egreso
    And El usuario completa el formulario de egreso con categoría "Gastos extraordinarios"
    And El usuario selecciona el proveedor "Otro"
    And El usuario ingresa información de nombre "Isabella Santodomingo" rut "55.555.555-5" y subcuenta "Proveedores"
    And El usuario ingresa descripción "Gastos extraordinarios de plomería" y monto "200000"
    And El usuario ingresa fecha de pago "21/10/2025"
    And El usuario ingresa el número de cuotas "3"
    And El usuario ingresa fecha de facturación "23/10/2025"
    Then El usuario guarda los cambios

  @regression
  Scenario: Agregar nuevo egreso a usuario nuevo categoría Mantención subcuenta Remuneraciones por pagar - - Límite de cuotas
    Given El usuario ingresa al menú de Egresos
    When El usuario ingresa a Nuevo Egreso
    And El usuario completa el formulario de egreso con categoría "Mantención"
    And El usuario selecciona el proveedor "Otro"
    And El usuario ingresa información de nombre "Ismael Ochoa" rut "77.777.777-7" y subcuenta "Remuneraciones por pagar"
    And El usuario ingresa descripción "Mantención Comunidad" y monto "100000"
    And El usuario ingresa fecha de pago "21/10/2025"
    And El usuario ingresa el número de cuotas "50"
    And El usuario ingresa fecha de facturación "23/10/2025"
    Then El usuario guarda los cambios

  @regression
  Scenario: Agregar nuevo egreso a usuario existente por opción proveedor para mensaje de Rut ya registrado
    Given El usuario ingresa al menú de Egresos
    When El usuario ingresa a Nuevo Egreso
    And El usuario completa el formulario de egreso con categoría "Administración"
    And El usuario selecciona el proveedor "Otro"
    And El usuario ingresa información de nombre "Ismael Ochoa" rut "11.111.111-1" y subcuenta "Remuneraciones por pagar"
    And El usuario ingresa descripción "Administración Comunidad" y monto "100000"
    And El usuario ingresa fecha de pago "21/10/2025"
    And El usuario ingresa el número de cuotas "50"
    And El usuario ingresa fecha de facturación "23/10/2025"
    Then El usuario guarda los cambios