# Sistema de Gestión de Equipaje - 2024-2

## Descripción del Módulo

Este módulo gestiona el manejo adicional de equipaje. Permite registrar detalles del equipaje, como dimensiones, peso, cantidad, tipo y costos, y soporta múltiples tipos de equipaje como maletas, bicicletas, instrumentos musicales y guacales, en ubicaciones como bodega, mano y cabina. También define la política para asignar el valor del equipaje adicional.

### Funcionalidades MVP

1. **Gestión de Equipaje (CRUD):**
   - **Agregar equipaje:** Permite al usuario agregar equipaje adicional a una reserva específica. Los detalles a registrar incluyen:
     - **Id Equipaje**
     - **Tipo de Equipaje:** Ejemplo: Maleta, Bicicleta, etc.
     - **Dimensiones:** Largo, ancho y alto.
     - **Peso (kg)**
     - **Cantidad**
     - **Ubicación en el avión:** Bodega, mano, o cabina.
     - **Costo asociado**
   - **Eliminar equipaje:** Permite al usuario quitar un equipaje específico de una reserva.
   - **Editar equipaje:** Permite modificar las características del equipaje asociado a una reserva, como peso, dimensiones o costo.
   - **Consultar equipaje:** Muestra una lista de todos los equipajes asociados a una reserva, junto con sus características y costos.

2. **Políticas de Cobro de Equipaje:**
   - Permite gestionar en línea las políticas que determinan el valor del equipaje adicional.

### Interacciones con Otros Módulos:

- **Módulo de Reservas:** El módulo de gestión de equipaje es llamado para añadir, eliminar o consultar equipaje adicional asociado a una reserva, devolviendo el valor total del equipaje.
- **Módulo de Pagos:** Se consulta el valor total del equipaje adicional para incluirlo en el cálculo del monto final de una reserva.
- Se aclara que no se hizo la interaccion por motivos de tiempo.

## Integrantes

- Felipe Suarez  
- Leonardo Araque
- Dorian García
- Edwin Gutiérrez


## Descripción

Este proyecto incluye la implementación de un sistema de gestión de equipaje basado en una API GraphQL desarrollada con Spring Boot. Permite al usuario realizar operaciones CRUD sobre los registros de equipaje, interactuar con módulos externos como Reservas y Pagos, y definir políticas de cobro de equipaje.

## Herramientas Utilizadas

- **IDE:** IntelliJ IDEA, Visual Studio Code  
- **Back-end:** Spring Boot, Java, Apache Maven 3.5, JDK 11  
- **Base de Datos:** PostgreSQL  
- **Pruebas de API:** Postman  
- **Navegador Web:** Google Chrome  

## Cómo ejecutar el proyecto

1. Clona el repositorio:
    ```sh
    git clone https://github.com/Felipey55/ModuloEquipajeB.git
    ```

2. Compila y ejecuta el proyecto usando Maven:
    ```sh
    mvn spring-boot:run
    ```

3. Configura la base de datos:

    - Sigue las indicaciones de [Importar Base de Datos](#importar-base-de-datos) de este README.

4. Configura las propiedades de conexión en el archivo `application.properties` o `application.yml` del proyecto. Asegúrate de actualizar las credenciales de la base de datos:
    ```
    spring.datasource.url=jdbc:postgresql://localhost:5432/equipaje
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    ```

6. La aplicación estará disponible en `http://localhost:8080/graphql`.

## Importar Base de Datos

Sigue estos pasos para importar la base de datos necesaria para el proyecto:

1. Crea una base de datos vacía en PostgreSQL:
    ```sql
    CREATE DATABASE equipaje;
    ```

2. Ejecuta la aplicaion ya que graphql se encarga de estructurar la base de datos.

3. Verifica que la base de datos se haya importado correctamente con todas las tablas y datos.

## Postman

Crea una colección de Postman para probar las operaciones CRUD. Ajusta la URL base a `http://localhost:8080/graphql`.
