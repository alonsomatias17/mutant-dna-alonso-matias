# ADN Mutante Matias-Alonso
Projecto de evaluacion de adn mutante

# CÓMO PROBAR PROBAR LA APLICACIÓN

* Local: run MutantProjectApplication.java
    -Abrir el cliente REST "Postman" para realizar las pruebas:
    - Para probar request get: 
                * end point: http://localhost:8080/stats
                * Apesar de ser una ejecucion local, la conexión es con una base de datos hosteada en AWS.
    - Para probar request post: 
                * end point: http://localhost:8080/mutant
                * body: {"dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}
                *header: key= Content-Type value= application/json

Apesar de ser una ejecucion local, la conexión es con una base de datos hosteada en AWS.
 
- Servidor AWS:
    La API esta hosteada en AWS. en: http://mutant-dna-searcher.us-east-2.elasticbeanstalk.com
    Abrir el cliente REST "Postman" para realizar las pruebas:
      Para probar request get: 
                end point: http://mutant-dna-searcher.us-east-2.elasticbeanstalk.com/stats
      Para probar request post: 
                end point: http://mutant-dna-searcher.us-east-2.elasticbeanstalk.com/mutant
                body: {"dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}
                header: 
                    key= Content-Type
                    value= application/json

# CONSIDERACIONES 
A pesar que la implementacion local usa una DB en AWS, la version hosteada en AWS no. AWS presentaba diferentes probematicas con las propiedades que se necesitan para la conexión a la DB (que andaban perfertamente local), debido a que era un tema netamente de configuración, y por falta de tiempo, implemente una base in memory en la version hosteada en AWS.
