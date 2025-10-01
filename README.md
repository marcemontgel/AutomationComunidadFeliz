🧪 AutomationComunidadFeliz - Pruebas Automatizadas

https://img.shields.io/badge/Java-17-red.svg

https://img.shields.io/badge/Selenium-4.x-green.svg

https://img.shields.io/badge/Cucumber-BDD-brightgreen.svg

https://img.shields.io/badge/Maven-3.x-blue.svg

------------------------------------------------------------------------

Repositorio de pruebas automatizadas para validar el comportamiento del campo "Fecha de Facturación" en el formulario de Egresos de ComunidadFeliz. Por Fanny Marcela Montañez Gélvez

--------------------------------------------------------------------

📋 Tabla de Contenidos

🎯 Descripción del Proyecto

🔍 Funcionalidad Probada

🧪 Casos de Prueba

🛠  Tecnologías Utilizadas

📁 Estructura del Proyecto

⚙️ Instalación y Ejecución

📊 Reportes

⚙️ Configuración

---------------------------------------

🎯 Descripción del Proyecto
Este proyecto automatiza la validación del campo "Fecha de Facturación" en el formulario de Egresos, cuyo comportamiento depende de la configuración en el configurador de egresos:


✅ Configuración ACTIVADA: El campo "Fecha de Facturación" debe mostrarse

❌ Configuración DESACTIVADA: El campo "Fecha de Facturación" NO debe mostrarse


------------------------------------------------------------------

🔍 Funcionalidad Probada
Flujo Principal: Configuración de Egresos → Formulario de Egresos → Validación de Campo "Fecha de Facturación"


🧩 Usuarios existentes vs nuevos

🧩 Diferentes categorías y subcuentas

🧩 Límites de cuotas

🧩 Proveedores específicos


--------------------------------------------------------------

🧪 Casos de Prueba
ID	Caso de Prueba	Descripción	Estado
https://github.com/marcemontgel/AutomationComunidadFeliz/issues/3 - CP-001 Agregar nuevo egreso a usuario existente categoría Administración	✅ Completado

https://github.com/marcemontgel/AutomationComunidadFeliz/issues/4 - CP-002 Agregar nuevo egreso a usuario nuevo (categoría Gastos extraordinarios, subcuenta Proveedores)	✅ Completado

https://github.com/marcemontgel/AutomationComunidadFeliz/issues/5 - CP-003 | Limite de cuotas | Agregar nuevo egreso a usuario nuevo (categoría Mantención, subcuenta Remuneraciones por pagar)	✅ Completado

https://github.com/marcemontgel/AutomationComunidadFeliz/issues/6 - CP-004 | Agregar nuevo egreso a usuario existente con proveedor “Otro” para mensaje de RUT ya registrado 	✅ Completado

https://github.com/marcemontgel/AutomationComunidadFeliz/issues/7 - CP-005 | Agregar nuevo egreso a usuario existente (categoría Reparación)	✅ Completado

https://github.com/marcemontgel/AutomationComunidadFeliz/issues/8 - CP-006 | Agregar nuevo egreso a usuario nuevo (categoría Uso y Consumo, subcuenta Proveedores)	✅ Completado

https://github.com/marcemontgel/AutomationComunidadFeliz/issues/9 - CP-007 | Agregar nuevo egreso a usuario nuevo (categoría Otra, subcuenta Remuneraciones por pagar) ✅ Completado


------------------------------------------------

🛠 Tecnologías Utilizadas

🖥️ Framework de Automatización:

📌 Java 17

📌 Selenium WebDriver 4.x

📌 Cucumber (BDD)

📌 Maven 3.x

📌 JUnit5

🖥️ Patrones de Diseño:

📌 Page Object Model (POM)

📌 Step Definitions

📌 Hooks para configuración

-----------------------------------------------------------------

📁 Estructura del Proyecto

<img width="651" height="688" alt="image" src="https://github.com/user-attachments/assets/286e03f5-025b-4e5b-a2d5-e0a2c90feea1" />



------------------------------------------------------------------------

⚙️ Instalación y Ejecución


⚡ Prerrequisitos

📌 Java JDK 17+

📌 Maven 3.6+

📌 Navegador Chrome/Firefox/Edge

1. Clonar el Repositorio
   
   git clone [url-del-repositorio]
   cd automationComunidadFeliz


2. Ejecutar Todas las Pruebas

   mvn clean test

3. Ejecutar Pruebas Específicas

   # Ejecutar solo pruebas de Egresos
   mvn test -Dcucumber.filter.tags="@Expense"

   # Ejecutar pruebas de Login
   mvn test -Dcucumber.filter.tags="@Login"

   # Ejecutar pruebas de Dashboard
   mvn test -Dcucumber.filter.tags="@Dashboard"

4. Ejecutar con Script

   ./runSuite.sh

-----------------------------------------------------------------

📊 Reportes

Después de la ejecución, los reportes se generan en:

🧩 Reportes Cucumber: target/cucumber-reports/

🧩 Reportes JUnit: target/surefire-reports/

🧩 Logs de Ejecución: src/test/resources/logs/

Para visualizar reportes HTML:

mvn cluecumber-report:reporting

-------------------------------------------------------------

📊 Resultados Reportes

<img width="1884" height="954" alt="image" src="https://github.com/user-attachments/assets/b78acf8b-dac6-46e2-b56b-5c859c36a0eb" />


-------------------------------------------------------------

⚙️ Configuración

Archivos de Configuración

🧩 junit-platform.properties: Configuración del entorno de pruebas

🧩 pom.xml: Dependencias y plugins Maven

Variables de Entorno
# Configuración de navegador
browser=chrome
environment=test
timeout=30

-----------------------------------------------

👨‍💻 Autor: Fanny Marcela Montañez Gélvez

--------------------------------------------------

📞 Contacto: +57 3188847187

-----------------------------------------------------

⭐ ¡Si este proyecto te resulta útil, no olvides darle una estrella!




