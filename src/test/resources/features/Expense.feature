Feature: Configuración de egresos e ingresos

  Background:
    Given El usuario navega a la pagina de login

  Scenario: Agregar nuevo egreso de tipo Test con fecha de facturación específica
    When El usuario escribe las credenciales "usuariopruebaQA@comunidadfeliz.com" con password "Prueba2025."
    Then El usuario debería ver el dashboard de la comunidad
    Given El usuario ingresa al menú lateral
    When El usuario busca el módulo "Egresos"
    Then El sistema debe mostrar la opción "Egresos"
    And El usuario selecciona la opción "Egresos"
    Given El usuario ingresa al menú de Egresos
    When El usuario ingresa a Nuevo Egreso
    And El usuario completa el formulario de egreso con categoría "Uso o consumo"
    And El usuario selecciona el proveedor "Test"
    And El usuario ingresa descripción "Pago servicio de Luz" y monto "100000"


  Scenario: Agregar nuevo egreso de tipo Otro Uso y Consumo con fecha de facturación específica
    When El usuario escribe las credenciales "usuariopruebaQA@comunidadfeliz.com" con password "Prueba2025."
    Then El usuario debería ver el dashboard de la comunidad
    Given El usuario ingresa al menú lateral
    When El usuario busca el módulo "Egresos"
    Then El sistema debe mostrar la opción "Egresos"
    And El usuario selecciona la opción "Egresos"
    Given El usuario ingresa al menú de Egresos
    When El usuario ingresa a Nuevo Egreso
    And El usuario completa el formulario de egreso con categoría "Uso o consumo"
    And El usuario selecciona el proveedor "Otro"
    And El usuario ingresa información de nombre "Alvaro Andres" rut "11.111.111-1" y subcuenta "Proveedores"
    And El usuario ingresa descripción "Pago servicio de Agua" y monto "1500000"
    And El usuario ingresa fecha de pago "21/10/2025"
    And El usuario ingresa el número de cuotas "5"
    And El usuario ingresa fecha de facturación "23/10/2025"
    Then El usuario guarda los cambios


  Scenario: Agregar nuevo egreso de tipo Otro Reparación con fecha de facturación específica
    When El usuario escribe las credenciales "usuariopruebaQA@comunidadfeliz.com" con password "Prueba2025."
    Then El usuario debería ver el dashboard de la comunidad
    Given El usuario ingresa al menú lateral
    When El usuario busca el módulo "Egresos"
    Then El sistema debe mostrar la opción "Egresos"
    And El usuario selecciona la opción "Egresos"
    Given El usuario ingresa al menú de Egresos
    When El usuario ingresa a Nuevo Egreso
    And El usuario completa el formulario de egreso con categoría "Reparación"
    And El usuario selecciona el proveedor "Otro"
    And El usuario ingresa información de nombre "Abelardo Castillo" rut "22.222.222-2" y subcuenta "Proveedores"
    And El usuario ingresa descripción "Pago servicio de Agua" y monto "1500000"
    And El usuario ingresa fecha de pago "21/10/2025"
    And El usuario ingresa el número de cuotas "5"
    And El usuario ingresa fecha de facturación "23/10/2025"
    Then El usuario guarda los cambios