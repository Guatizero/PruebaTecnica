# language: es
Característica: Resgistro usuario

  Yo como nuevo usuario en demoblaze
  Quiero registrarme en la pagina
  Para poder acceder a sus servicios

  @Registro
  Escenario: Registro usuario exitos
    Dado el usuario se encuentra en la pagina DEMOBLAZE
    Cuando seleeciona singUp
    Y ingresa los datos del rormulñario de registro
    Entonces se crea el nuevo usuario y se visualiza un mensaje : Sign up successful.

  Escenario: Ingreso con el usuario creado
    Dado  que el usuario selecciona SingIn
    Cuando the user fills in the input fields in the Log In modal with the data from "src/test/resources/data/input_data.csv" and presses the action button
    Entonces the user is logged in to the website and returned to the index page with the Username
