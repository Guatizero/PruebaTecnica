# language: es

Característica: Compra

  Escenario: Compra exitosa
    Dado el usuario se encuentra en la pagina DEMOBLAZE
    Cuando el usuario se encuentra en la pagina principal
    Y el usuario elije la categoriay el articulo parametrizado en "src/test/resources/data/datosUsuario.csv"
    Y esta en la pagina del producto
    Y selecciona agregar al carrito
    Y esta ne la pagina del producto
    Y selecciona ir al carrito
    Y esta en la pagina del carrito
    Y selecciona el boton de place order
    Y Se visualizan los campos para ingresar los datos de la compra
    Y ingresa los datos parametrizados en "src/test/resources/data/informacionCompras.csv"
    Y selecciona el boton purchased
    Entonces Se visualiza el mensaje  "Thank you for your purchase!", con la informacion de la compra