# GrafoOptiLib V2

## Instrucciones
1. Crear un proyecto con (al menos) Java 17 e importar el jar de GrafoOptiLibV2
2. Crear la estructura del problema
   1. La instancia debe heredar de ```Instance```
   2. La solución debe heredar de ```Solution```
   3. Hay que crear una factoría que herede de ```InstanceFactory```
3. Crear los algoritmos que se vayan a ejecutar
   1. Los constructivos implementarán la interfaz ```Constructive```
   2. Las búsquedas locales implementarán la interfaz ```Improvement```
   3. Los algoritmos completos implementarán la interfaz ```Algorithm```
4. Utilizar la clase experiment para lanzar los experimentos que necesitemos
   1. Le tenemos que pasar un array con los algoritmos que queramos ejecutar y la factoría de instancias para poder ir leyéndolas
   2. Ejecutamos el método ```launch``` pasándole el directorio con las instancias que queremos ejecutar
   3. Creará una carpeta experiments y dentro otra con la fecha en la que se lanzó el experimento.
   4. Se creará un excel por cada algoritmo ejecutado que tendrá el nombre del método ```toString``` del algoritmo

## Utilidades
### ```RandomManager```
- Gestor de números aleatorios para que los experimentos sean reproducibles. 
- La semilla está prefijada para cada ejecución para evitar problemas.
- Está implementado a modo de *Singleton* por lo que el mismo aleatorio estará disponible en cualquier parte del código, es todo estático.
- Basta con llamar a ```getRandom()``` para obtener el ```Random``` que necesitemos.

### ```Timer```
- Gestor de tiempo de ejecución para nuestro código
- Al comenzar a ejecutar, llamar a ```initTimer``` con o sin tiempo límite (no se para al llegar el límite, es solo para que nos indique si ha llegado cuando preguntemos)
- Para obtener el tiempo, llamar a ```getTime```.
- El tiempo lo da en **milisegundos**.