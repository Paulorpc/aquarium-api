-- ****************** SqlDBM: MySQL ******************;
-- ***************************************************;


-- ************************************** `BiotaTaxonomia`

CREATE TABLE IF NOT EXISTS `BiotaTaxonomia`
(
 `idTaxonomia` int NOT NULL AUTO_INCREMENT ,
 `dominio`     varchar(100) NULL ,
 `reino`       varchar(100) NULL ,
 `filo`        varchar(100) NULL ,
 `classe`      varchar(100) NULL ,
 `ordem`       varchar(100) NULL ,
 `familia`     varchar(100) NULL ,
 `genero`      varchar(100) NULL ,
 `especie`     varchar(100) NULL , 

PRIMARY KEY (`idTaxonomia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Adicionar FK na tabela Biota
ALTER TABLE `Biota` ADD CONSTRAINT `FK_224` 
FOREIGN KEY (`idTaxonomia`) REFERENCES `BiotaTaxonomia` (`idTaxonomia`);