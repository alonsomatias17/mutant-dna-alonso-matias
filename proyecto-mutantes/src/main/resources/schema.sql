DROP TABLE IF EXISTS DNA_SEQUENCE;
CREATE TABLE DNA_SEQUENCE(
                  ID INT NOT NULL AUTO_INCREMENT,
                  TYPE VARCHAR(30) NOT NULL,
                  DNA varchar(255) NOT NULL,
                  PRIMARY KEY (id));