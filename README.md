# Activiti Database Setup

Este repositorio contiene la configuración para la base de datos de Activiti. A continuación, se detallan los pasos para configurar y ejecutar el proyecto en tu entorno local.

## Pasos para configurar el entorno

### 1. Crear base de datos en tu ambiente local

Antes de comenzar, asegúrate de tener instalada y configurada una base de datos compatible (por ejemplo, PostgreSQL) en tu máquina local.

Puedes crear una base de datos utilizando los siguientes comandos en PostgreSQL:

```sql 
CREATE DATABASE activiti-db; 
```

### 2. Clonar repositorio

Clonar el repositorio utilizando el comando 

``` bash
git clone https://github.com/betzy-salas/activiti-database.git 
```

### 3. Modificar los atributos de Base de Datos

En el archivo application.properties modificar las configuraciones de base de datos que sean necesarias

``` properties
spring.datasource.url=jdbc:postgresql://localhost:5432/<DATABASE_NAME>
spring.datasource.username=<DATABASE_USER>
spring.datasource.password=<DATABASE_PASSWORD>
```

### 4. Ejecutar el proyecto para que se cree la base de datos

Ejecute el proyecto en su ambiente local a fin de que se cree la base de datos correctamente

