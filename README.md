 
 # BuilderLibrary
 
 Librería para la utilización y reutilización del patrón **Builder**.
 
 ## Descripción del patrón
 
 El patrón de diseño **Builder** de la programación orientada a objetos permite delegar la creación de nuevas instancias 
 de una entidad en un modelo de negocios a una clase llamada comunmente **Builder** en vez de la clase que compone a la
 entidad, esto permite romper las dependencias directas entre la llamada al constructor de las implementaciones de la 
 entidad y la clase que la compone, logrando así tener una código mas reutilizable y escalable.
 
 ## Utilizacion del patron Builder**
 
 El patron Builder cuando se vuelve necesario utilizarlo, se crea una clase adicional llamada Builder que contendra las
 propiedades necesarias para poder construir la instancia y un metodo comunmente llamado build, que es el encargado de
 construir las instancias de la entidad.
 
 Una vez creadas las clases necesarias, la clase contenedora de la entidad es responsable de crear nuevas instancias
 utilizando la clase Builder en vez de llamar directamente al constructor de la instancia.
 
 ## Problema
 
 Cada vez que se vuelve necesario implementar el patron Builder y despues reutilizarlo se pierde un considerable tiempo
 en el proceso de implementacion por parte de los programadores, adicionalmente existen variadas variantes de 
 implementacion del patron, las cuales pueden ser usadas indistintamente.
 
 ## Propuesta de solucion
 
 Crear una libreria que garantice que las implementaciones del patron Builder se realicen de forma mas rapida y 
 manteniendo un estilo del patron que resuelva los problemas actuales en el uso del mismo.
 La libreria estara conformada por una clase llamada Builder que acepte un objeto generico con el cual reconocer el 
 tipo de dato que se instanciara, poseera una coleccion de las propiedades del Builder con el cual posteriormente 
 creara la nueva instancia y un objeto encargado del proceso de validacion antes de crear la nueva instancia.
 Cada propiedad del patron tendra una llave que identifique a la propiedad, lo cual hara la propiedad como unica entre
 el conjunto de propiedades, la propiedad ademas contendra el valor que sera utilizado en la creacion de la nueva 
 instancia y un objeto validador encargado de realizar la validacion del valor y que cumpla con los requisitos del 
 negocio.
 
 # Planificacion
 
 [X] - Establecer las propiedades del proyecto (a partir del proyecto DateUtils).  
 [X] - Implementar clases y relaciones.  
 [X] - Documentar clase, metodos y atributos.  
 [X] - Identificar nuevos usos de la librerias, asi como la forma de utilizarla y reutilizarla.  
 [X] - Realizar mejoras si es necesario.  
 [X] - Realizar documentacion y casos de pruebas unitarios.  
 [X] - Subir repositorio a GitHub y Maven.  
