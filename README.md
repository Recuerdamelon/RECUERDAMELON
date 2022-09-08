# RECUERDAMELON
## INTRO
#### Applicación web desarrollada en Spring para el registro de tareas

## REQUISITOS

* Java >= 11
* Maven >= 11

## PONLO EN MARCHA

Limpieza del módulo Maven > mvn clean install
Ejecutable > mvn spring-boot:run

A continuación, inicie su navegador favorito y vaya a `http://localhost:8080` [Url de ejecución](http://localhost:8080)

## LENGUAJES DE PROGRAMACIÓN USADOS

* JAVA - Arquitectura de la aplicación
* HTML - Visualización Web
* CSS - Estilo HTML
* Javascript - Funcionalidades añadidas a algunos elementos concretos de la vista

## Diagrama de la Base de Datos (MySQL)
Para la correcta ejecución hay que crear un nuevo esquema de MySQL con nombre 'recuerdamelon'
El script de la base de datos viene en el archivo ../src/java/resources/data.sql 

<img width="1029" alt="bbdd" src="https://user-images.githubusercontent.com/106087948/189113601-2ef3e824-c07d-4037-ba53-c48964678f7e.png">

## CONTENIDO hasta ahora...

## POM

* Dependencias de: maven, jpa, thymeleaf, starter web, spring security, h2, MySql, test, openapi-ui (swagger), mapstruct, lombok, web-socket

## JAVA
----------------------------------------------------------------------------
* DATA
* Entity
* Repository
----------------------------------------------------------------------------
* DTO
----------------------------------------------------------------------------
* SERVICE
* Mapper
* AbstractService
* Service
----------------------------------------------------------------------------
* EVENT
----------------------------------------------------------------------------
* LISTENERS
----------------------------------------------------------------------------
* UTILS
----------------------------------------------------------------------------
* WEB
* Rest
----------------------------------------------------------------------------
* RecuerdaMelonApplication.java
----------------------------------------------------------------------------
[Url de ejecución en producción](http://localhost:8080/swagger-ui/index.html#/)
----------------------------------------------------------------------------
## RESOURCES
* data.sql
* yaml
* templates
* static
* properties
-------------------Directorio local de fotos de perfil
* user-photos
----------------------------------------------------------------------------
**Para una descripción más detallada del proyecto consultar /RECUERDAMELON/Memoria Recuerda Melón/Memoria tecnica Recuerda Melón.pdf


