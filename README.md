# API Rest Microservicios con Springboot simulando una tienda de electrodomesticos

* Realice esta API Rest como proyecto final aplicando todo lo aprendido en el curso de Microservicios En Java Con SpringBoot de Todocode
* Este proyecto fue realizado por Facundo Vandecaveye.


## Stack de Tecnologias

* Java
* Spring Framework
* Spring Boot
* Spring Docs
* Spring Cloud
* Lombok
* Spring Data JPA
* Hibernate
* MySQL
* Docker

## Patrones de diseño

* Eureka
* Spring Cloud Load Balancer
* Circuit Breaker mediante Resilience4J (utilizando @Retry)
* API Gateway

## Microservicios

* Microservicio de Carrito
* Microservicio de Producto
* Microservicio de Venta


## Instalacion y Ejecucion

* Antes de ejecutar la aplicacion debes tener instalado Docker Desktop, luego debes situarte en la carpeta raiz del proyecto en la terminal y ejecutar el comando `docker-compose up`. 


## Puntos de acceso de la API por API Gateway

`https://localhost:444/{nombre microservicio}/{nombre path}`

* Acceso a Documentacion por Swagger
`http://localhost:{puerto microservicio}/swagger-ui.html`


## API Endpoints

### Product

* `POST /products` : Crear un nuevo producto
* `GET /products` : Trae lista completa de productos
* `GET /products/{id_product}` : Trae producto en particular recibiendo parametro con la id del producto
* `GET /products/details` : Trae detalles de una LISTA de productos, recibiendo como body la Lista de productos
* `DELETE /products/{id_product}` : Eliminar un producto en particular recibiendo como parametro el id del producto
* `PUT /products/{id_product}` : Edita un producto recibiendo como parametro el codigo de producto y como Body el nuevo producto

### Cart

* `GET /carts` : Retorna lista completa de carritos
* `GET /carts/{id_cart}` : Trae un carrito recibiendo como parametro la id del carito recibiendo como parametro id del carrito
* `GET /carts/{id_cart}/totalAmmount` : Trae el precio total de un carrito recibiendo como parametro id del carrito
* `GET /carts/{id_cart}` : Trae todos los productos que se encuentran en el carrito con detalles, recibiendo como parametro id del carrito
* `POST /carts` : Crear un nuevo Carrito
* `POST /carts/{id_cart}/product/{id_product}` : Agrega un producto al carrito recibiendo como parametros la id del carrito y la id del producto
* `DELETE /carts/{id_cart}/product/{id_product}` : Elimina un producto del carrito recibiendo como parametros la id del carrito y la id del producto
* `DELETE /carts/{id_cart}` : Elimina un carrito recibiendo como parametro id del carrito


### Sale

* `GET /sales` : Trae lista completa de ventas
* `POST /sales` : Crear una nueva Venta
* `GET /sales/{id_sale}` : Trae venta en particular recibiendo como parametro el id de la venta
* `GET /sales/{id_sale}/total_price` : Trae el monto total a pagar de una venta recibiendo como parametro el id de la venta
* `GET /sales/products/{id_sale}` : Trae todos los productos de una venta recibiendo como parametro el id de la venta


### Recomendaciones

* Recomiendo realizar las consultas mediante la funcionalidad agregada de documentacion de Swagger, ingresando mediante la url `http://localhost:{puerto del microservicio}/swagger-ui.html` reemplazando el puerto por el del microservicio al cual querramos acceder y el puerto elegido en el docker-compose
