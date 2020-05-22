-- ****************** SqlDBM: MySQL ******************;
-- ***************************************************;


-- ************************************** `BiotaTaxonomia`

CREATE TABLE IF NOT EXISTS `BiotaTaxonomia`
(
 `idBiota` int NOT NULL ,
 `dominio`     varchar(100) NULL ,
 `reino`       varchar(100) NULL ,
 `filo`        varchar(100) NULL ,
 `classe`      varchar(100) NULL ,
 `ordem`       varchar(100) NULL ,
 `familia`     varchar(100) NULL ,
 `genero`      varchar(100) NULL ,
 `especie`     varchar(100) NULL , 

PRIMARY KEY (`idBiota`),
CONSTRAINT `FK_224` FOREIGN KEY (`idBiota`) REFERENCES `Biota` (`idBiota`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;