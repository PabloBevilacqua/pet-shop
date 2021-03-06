# 馃 Magic and Strange Pet Shop

Negocio dedicado a la investigaci贸n, desarrollo y comercializaci贸n de
productos m谩gicos para **personas no humanas**.

## 馃悎 Descripci贸n

Nuestros clientes de Magic and Strange Pet Shop acudieron a nosotros
para mejorar su visibilidad en internet. A medida que coordin谩bamos con
ellos nos dimos cuenta que necesitaban algo m谩s que una vitrina de
productos.

Por ese motivo les propusimos desarrollar una aplicaci贸n web donde
pueden no solo exponer sus productos, sino tambi茅n llevar un control de
stock y recibir feedback de sus clientes.

Adem谩s se desarroll贸 en principio un registro y login de usuarios para
que en un futuro sus clientes puedan: comentar sobre un producto en
particular, elegir sus favoritos y hacer una tienda online con carrito
de compras.

## 馃悤 Contexto

Este proyecto fue realizado como proyecto final del curso de
programaci贸n de *Egg Educaci贸n*. Curso 3, comisi贸n A y B.

**Fecha de entrega:** 14 de octubre del 2021

### 馃 Equipo

- Javiera Cabello
- Juan Manuel Espinosa
- Gino Fiorellini
- Pablo Bevilacqua
- Enzo Sosa
- Pedro Ragni
- Brian Gabriel Gonzalez
- Dar铆o Parra

## 馃挮 Instalaci贸n

### Requisitos

- Java 8
- Maven
- MySQL 8

En MySQL debe haber una base de datos llamada `catalogo` o modificar el
archivo de configuraci贸n `application.yml`

### Pasos

Copiar cada l铆nea en la terminal los siguientes comandos:

```
git clone https://github.com/PabloBevilacqua/pet-shop.git
cd pet-shop
mvn clean package spring-boot:run
```

Esto descarga el proyecto y todas las dependencias necesarias.

Por defecto el proyecto correr谩 en el puerto `8080` del localhost.

- Ingresar a `http://localhost:8080`
- Ir al formulario de registro
- Registrar un usuario
- En MySQLWorkbench o el cliente MySQL de preferencia ingresar: `UPDATE
  usuario SET rol='ADMIN' WHERE username='{nombre-de-usuario}';`
  - *Cambiar `{nombre-de-usuario}` por su nombre de usuario, sin
    corchetes*
- Volver al formulario de login, ingrese sus credenciales

## Elecciones

### 馃 Front-end

Se opt贸 realizar el front con una est茅tica minimalista y a su vez
llamativa, con colores pastel y bordes redondeados.

Por este motivo determinamos que la mejor opci贸n iba a ser escribir
nuestro propios estilos CSS y as铆 practicar lo aprendido en el curso.

### 馃 Back-end

En el back se realizaron algunos controladores espec铆ficos para el panel
de administraci贸n, as铆 utilizar la etiqueta `@PreAuthorize` para manejar
todas las peticiones dentro del controlador.

## 馃Ξ Gracias

A todos nuestros compa帽eros que nos ayudaron en todo el proceso de
crecimiento desde el principio del cursado hasta la entrega final.

A todos nuestros profesores y couchs: Agust铆n Fiordelisi, Bel茅n Lima
Flavia Cipolla, Gast贸n Vidal y Nahuel Bull贸n.

