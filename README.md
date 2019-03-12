# [TFM] Generador de historias Android

Las historias tiene mucho más impacto si la relacionamos con elementos de la realidad. El uso de dispositivos móviles y de técnicas como la geolocalización o la realidad aumentada nos permiten ampliar una historia, relacionando parte de la misma con lugares y objetos existentes en la realidad. Este tipo de narrativas tiene un potencial importante en aplicaciones como son la educación o el turismo donde la información extra que obtienen los jugadores puede ser usada en los proceso de aprendizaje y también como fuerte motivador para visitar lugar de interés.

El objetivo del proyecto es desarrollar un sistema que permita diseñar historias interactivas que se integren en un juego con el que los jugadores puedan completar retos, buscar y coleccionar objetos, hablar con personajes virtuales y todo ello mientras exploran el mundo físico que esta a su alrededor.

El editor facilitará el diseño de las experiencias y generara ficheros de configuración que puedan ser usados en una aplicación móvil que permita coordinar y vivir las experiencias de juego previamente diseñadas.


## Rutas REST importantes

El listado de rutas del servidor a las que el cliente realizará peticiones es el siguiente:

- Registro: '/rest/usuario' [POST]
- Login:
- Solicitar listado básico de historias (id, nombre): '/historia/list' [GET]
- Solicitar información básica de una historia (nombre, descripción, imagen de título):
- Solicitar la información completa de una historia: '/historia/<id>' [GET]
- Informar que el usuario ha completado una misión: