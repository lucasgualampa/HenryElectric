# HenryElectric
backend en JAVA para el M2

# Puntos a seguir
1.    Crear repo github y compartir con colaboradores
2.    Crear dos proyectos con Initializr (medidor y receptor)
3.    Emisor 8001(medidor) : crear modelos y metodo de envio con fallback persistente
4.    Receptor 8002(api de client) : crear modelos y endpoint para recibir info del medidor
5.    Métodos factura, consumo por fechas (kwH y $) y (medidas): endpoints en receptor.
6.    Levantar receptor, crear domicilio, medidor, cliente
7.    Levantar medidor, enviar medidas al receptor
8.    Consultar desde postman endpoints para factura y consumos

# Instrucciones
- clonar repo
- levantar mysql con docker : docker-compose up
- ejecutar BaseApplication de emisor y receptor para levantar el back
- testear endpoints!

# Endpoints(swagger)
http://localhost:8081/swagger.html
http://localhost:8082/swagger.html