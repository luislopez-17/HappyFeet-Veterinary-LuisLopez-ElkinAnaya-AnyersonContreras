# 🐾 Sistema de Gestión Integral para la Veterinaria "Happy Feet"🏥

## Descripción del Contexto

La clínica veterinaria "Happy Feet" ha sido un pilar en su comunidad durante años, conocida por su trato compasivo y su excelente atención médica. Sin embargo, su éxito ha traído consigo un desafío abrumador: la gestión de sus operaciones. El sistema actual, una mezcla de fichas de papel, agendas manuales y hojas de cálculo, está colapsando. Los problemas diarios incluyen:

* **Historiales Clínicos Incompletos:** Encontrar el historial de vacunas o las alergias de una mascota durante una emergencia es una carrera contra el tiempo revisando archivadores desorganizados, lo que pone en riesgo la salud del paciente.
* **Fugas de Inventario:** No hay un control en tiempo real del stock de medicamentos. A menudo, un veterinario receta un medicamento para descubrir después que se ha agotado, forzando cambios de tratamiento de último minuto y generando desconfianza.
* **Agendamiento Caótico:** Las citas se solapan o se registran incorrectamente, generando largos tiempos de espera y dueños frustrados que perciben una falta de organización y respeto por su tiempo.
* **Facturación Lenta y Propensa a Errores:** Calcular manualmente los costos de una cirugía, incluyendo insumos y medicamentos, es un proceso lento que a menudo resulta en facturas incorrectas, causando pérdidas económicas y discusiones incómodas con los clientes.

"Happy Feet" necesita urgentemente un Sistema de Gestión Integral que centralice toda su operación, desde la ficha del paciente hasta la facturación, para poder seguir ofreciendo la atención de calidad que la caracteriza y para profesionalizar su gestión administrativa.

---

## Tecnologías Utilizadas

* **Backend:** Java (JDK 17)
* **Base de Datos:** MySQL
* **Conexión DB:** JDBC
* **Gestión de Dependencias:** Maven
* **Control de Versiones:** Git
* **Principios de Diseño:** SOLID
* **Patrones de Diseño:** (Ej. MVC, Singleton, Factory, DAO, etc.)
* **Programación Funcional:** API Stream y expresiones lambda

---

## 🚀 Funcionalidades Implementadas

El sistema se estructura en módulos clave para centralizar y optimizar la operación de la clínica "Happy Feet".

---

### 1. Módulo de Gestión de Pacientes (Mascotas y Dueños) 🐾

Este módulo es el núcleo, proporcionando un registro centralizado y fiable.

* **Registro Completo de Mascotas:** Incluye datos básicos (nombre, especie, raza, sexo, fecha de nacimiento), identificación única (microchip/tatuaje), URL a foto, y un **historial médico detallado** (alergias, condiciones preexistentes, historial de vacunas).
* **Registro de Dueños:** Perfiles con datos de contacto (dirección, teléfono, **email único**) y un campo para contacto de emergencia.
* **Gestión de Propiedad:** Asociación obligatoria de cada mascota a un único dueño y capacidad para transferir la propiedad.

---

### 2. Módulo de Servicios Médicos y Citas 🗓️

Gestiona el flujo de trabajo clínico y la atención a los pacientes.

* **Agenda de Citas Inteligente:** Sistema para programar, consultar y gestionar el estado de las citas (ej. 'Programada', 'Finalizada', 'Cancelada'), asociando cada cita a una mascota y un veterinario.
* **Registro de Consultas:** Interfaz para el personal que permite registrar la fecha, hora, motivo de la visita, diagnóstico, recomendaciones, y **prescripción de medicamentos/procedimientos**.
* **Seguimiento de Procedimientos Especiales:** Registro detallado para cirugías, cubriendo información preoperatoria, detalle del procedimiento y seguimiento postoperatorio.
* **Regla de Negocio Clave (Inventario):** **Deducción automática de stock.** Al prescribir un medicamento o registrar un insumo usado en una consulta/cirugía, la cantidad correspondiente se deduce inmediatamente del inventario.

---

### 3. Módulo de Inventario y Farmacia 💊

Controla los recursos físicos para asegurar la disponibilidad de insumos críticos.

* **Control Detallado de Stock:** Gestión de medicamentos, vacunas y material médico, incluyendo fabricante, lote, **cantidad en stock**, **stock mínimo** y **fecha de vencimiento**.
* **Alertas Inteligentes:** Genera notificaciones automáticas para:
    * Productos por debajo del stock mínimo definido.
    * Productos cercanos a su fecha de vencimiento.
* **Restricción por Vencimiento:** El sistema restringe el uso (prescripción o venta) de medicamentos o vacunas ya vencidos.
* **Gestión de Proveedores:** Permite registrar y consultar proveedores para facilitar los procesos de reabastecimiento.

---

### 4. Módulo de Facturación y Reportes 📊

Maneja las finanzas y proporciona inteligencia de negocio.

* **Generación de Facturas:** Capacidad para generar facturas detalladas en **formato de texto plano (limpio y profesional)** al finalizar una atención, incluyendo desglose de servicios/productos, valores unitarios, subtotales, impuestos y total a pagar.
* **Reportes Gerenciales (en Consola):** Generación de informes clave para la gestión de la clínica:
    * Servicios más solicitados.
    * Desempeño individual del equipo veterinario.
    * Estado crítico del inventario (productos a vencer/reabastecimiento).
    * Análisis de facturación por períodos específicos.

---

### 5. Módulo de Actividades Especiales ✨

Funcionalidades de valor agregado para la comunidad y la fidelización de clientes.

* **Días de Adopción:** Registro de mascotas disponibles, su historia y temperamento, y generación de un **contrato de adopción simple en texto**.
* **Jornadas de Vacunación:** Interfaz optimizada para el registro **masivo y rápido** de mascotas y aplicación de vacunas durante campañas.
* **Club de Mascotas Frecuentes:** Un sistema de fidelización que gestiona la **acumulación de puntos** por servicios y compras, permitiendo el canje de **beneficios** a clientes leales.
---

## Modelo de la Base de Datos

La base de datos está diseñada en MySQL y sigue un modelo relacional para asegurar la integridad de los datos. Las tablas principales incluyen `mascotas`, `duenos`, `citas`, `inventario` y `facturas`, conectadas a través de tablas de consulta (lookup tables) y relaciones lógicas que reflejan las reglas de negocio.

El script completo para la creación de la estructura (DDL) y los datos iniciales se encuentran en:
* `/database/schema.sql`

### Diagrama Entidad-Relación (E-R)
*<img width="886" height="765" alt="image" src="https://github.com/user-attachments/assets/4cd4a0dd-91ab-4f3b-8776-5d6c2710e8ba" />

``

---

## ⚙️ Instrucciones de Instalación y Ejecución

Siga esta guía paso a paso para configurar y ejecutar el proyecto en su entorno local.

---

### 1. Requisitos Previos

Asegúrese de tener instaladas las siguientes herramientas en su sistema:

* **Java Development Kit (JDK):** Versión 17 o superior.
* **Apache Maven:** Versión 3.8 o superior (para gestión de dependencias y compilación).
* **MySQL Server:** Versión 8.0 o superior (el motor de base de datos).
* **Git:** Para clonar el repositorio.

---

### 2. Clonación del Repositorio

Abra su terminal o línea de comandos y ejecute el siguiente comando para clonar el proyecto:

```bash
git clone https://github.com/luislopez-17/-luislopez-17-happy_feet_veterinaria_LuisLopezElkin_Anaya_Anyerson
cd happy_feet_veterinaria_LuisLopezElkin_Anaya_Anyerson
```

### 3. Configuración de la Base de Datos 🛠️


1.  **Ajustar Credenciales (Archivo de Propiedades):**
    
    Localice el archivo de configuración de la conexión (asumimos que es **`config.properties`**) dentro de la ruta **`src/main/resources`** (o donde maneje las utilidades de conexión) y actualice los siguientes parámetros con sus credenciales de MySQL:

    ```properties
    # Configuración de Conexión a MySQL
    DB_URL=jdbc:mysql://localhost:3306/happy_feet_veterinaria
    DB_USER=[TU_USUARIO_MYSQL]
    DB_PASSWORD=[TU_CONTRASENA_MYSQL]

    # Ejemplo:
    # DB_USER=root
    # DB_PASSWORD=misuperclave
    ```

2.  **Verificación del Esquema (Opcional):**
    
    Si necesita recrear la base de datos o verificar que las tablas estén correctas, puede ejecutar los scripts DDL y DML ubicados en la carpeta `/database`:

    ```bash
    # (Opcional) Usar este script si necesita RECREAR la estructura
    mysql -u [tu_usuario_mysql] -p < database/schema.sql
    
    # (Opcional) Usar este script para POBLAR las tablas con datos iniciales de catálogos
    mysql -u [tu_usuario_mysql] -p happy_feet_veterinaria < database/data.sql
    ```
    
3.  **Lógica en Java:** La aplicación utiliza la librería **JDBC** para leer estos parámetros del archivo y establecer la conexión.

## ⌨️ Guía de Uso

El sistema está diseñado para operarse completamente desde la **consola**, utilizando menús numéricos para la navegación.

1.  **Inicio:** Al ejecutar la aplicación (`mvn exec:java`), se presentará el **Menú Principal**.
2.  **Navegación:** Para acceder a un módulo, simplemente ingrese el **número** correspondiente a la opción deseada (ej. `1` para el Módulo de Pacientes).
3.  **Operaciones:** Dentro de cada módulo, se mostrará un submenú que le permitirá realizar las operaciones CRUD (Crear, Leer/Consultar, Actualizar, Eliminar) o ejecutar funcionalidades específicas (ej. Registrar Cita, Generar Factura, Consultar Stock).
4.  **Regresar:** La última opción en todos los menús (generalmente el número `0` o `9`) le permitirá **regresar al menú anterior** o salir de la aplicación.
5.  **Entrada de Datos:** El sistema solicitará la entrada de datos por línea, validando el tipo de información requerido (texto, números, fechas).

## 🧑‍💻 Autor(es)

El desarrollo de este Sistema de Gestión Integral para la Veterinaria "Happy Feet" fue realizado por:

* Anyerson Contreras
* Luis Lopes
* Elkin Anaya

## DATABASE
[Ver Contenido de la Carpeta Database](Database/)
