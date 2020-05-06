-- ****************** SqlDBM: MySQL ******************;
-- ***************************************************;


-- ************************************** `TipoAquario`

CREATE TABLE IF NOT EXISTS `TipoAquario`
(
 `idTipoAquario` INT NOT NULL AUTO_INCREMENT ,
 `tipo`          varchar(45) NOT NULL UNIQUE,
 `tipoAgua`      enum('doce', 'salgada') NOT NULL ,
 `status`        boolean NOT NULL ,
 `descricao`     text NOT NULL ,
 `dtCadastro`    datetime NOT NULL ,
 `dtAtualizacao` datetime NOT NULL ,
 
 PRIMARY KEY (`idTipoAquario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ************************************** `Aquario`

CREATE TABLE IF NOT EXISTS `Aquario`
(
 `idAquario`     int NOT NULL AUTO_INCREMENT ,
 `nome`          varchar(45) NOT NULL ,
 `dtAquisicao`   datetime NULL ,
 `dtInicio`      datetime NOT NULL ,
 `dtFinal`       datetime NULL ,
 `idTipoAquario` int NULL ,
 `tipoAgua`      enum('doce', 'salgada') NULL ,
 `tamanho`       varchar(45) NULL ,
 `volume`        int NULL ,
 `iluminacao`    varchar(45) NULL ,
 `filtragem`     varchar(45) NULL ,
 `sistemaCO2`    varchar(45) NULL ,
 `dosagem`       varchar(45) NULL ,
 `substrato`     varchar(45) NULL ,
 `parametros`    int NULL ,
 `status`        boolean NOT NULL ,
 `foto`          int NULL ,
 `observacao`    text NULL ,
 `dtCadastro`    datetime NOT NULL ,
 `dtAtualizacao` datetime NOT NULL ,

PRIMARY KEY (`idAquario`),
CONSTRAINT `FK_170` FOREIGN KEY (`idTipoAquario`) REFERENCES `TipoAquario` (`idTipoAquario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ************************************** `Biota`

CREATE TABLE IF NOT EXISTS `Biota`
(
 `idBiota`        int NOT NULL AUTO_INCREMENT ,
 `idAquario`      int NOT NULL ,
 `nome`           varchar(45) NOT NULL ,
 `nomePopular`    varchar(45) NULL ,
 `nomeCientifico` varchar(45) NULL ,
 `familia`        varchar(45) NULL ,
 `dtAquisicao`    datetime NULL ,
 `dtNascimento`   datetime NULL ,
 `tipoBiota`      varchar(45) NULL ,
 `genero`         enum('m', 'f') NULL ,
 `vlrUnitario`    decimal(10,4) NULL ,
 `qtde`           smallint NULL ,
 `status`         bit NULL ,
 `foto`           int NULL ,
 `nota`           decimal(4,2) NULL ,
 `observacao`     text NULL ,
 `dtCadastro`     datetime NOT NULL ,
 `dtAtualizacao`  datetime NOT NULL ,

PRIMARY KEY (`idBiota`),
CONSTRAINT `FK_195` FOREIGN KEY (`idAquario`) REFERENCES `Aquario` (`idAquario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ************************************** `Equipamento`

CREATE TABLE IF NOT EXISTS `Equipamento`
(
 `idEquipamento`  int NOT NULL AUTO_INCREMENT ,
 `nome`           varchar(45) NOT NULL ,
 `dtAquisicao`    datetime NULL ,
 `tipo`           varchar(45) NULL ,
 `vlrUnitario`    decimal(10,4) NULL ,
 `qtde`           smallint NULL ,
 `foto`           int NULL ,
 `modelo`         varchar(45) NULL ,
 `fabricante`     varchar(45) NULL ,
 `dtSubstituicao` datetime NULL ,
 `potencia`       varchar(45) NULL ,
 `observacao`     text NULL ,
 `dtCadastro`     datetime NOT NULL ,
 `dtAtualizacao`  datetime NOT NULL ,

PRIMARY KEY (`idEquipamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ************************************** `EquipamentoAquario`

CREATE TABLE IF NOT EXISTS `EquipamentoAquario`
(
 `idEquipamAquario` int NOT NULL AUTO_INCREMENT ,
 `idAquario`        int NOT NULL ,
 `idEquipamento`    int NOT NULL ,
 `dtInicio`         datetime NULL ,
 `dtFim`            datetime NULL ,
 `dtCadastro`       datetime NOT NULL ,
 `dtAtualizacao`    datetime NOT NULL ,

PRIMARY KEY (`idEquipamAquario`),
CONSTRAINT `FK_63` FOREIGN KEY (`idEquipamento`) REFERENCES `Equipamento` (`idEquipamento`),
CONSTRAINT `FK_66` FOREIGN KEY (`idAquario`) REFERENCES `Aquario` (`idAquario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ************************************** `parametro`

CREATE TABLE IF NOT EXISTS `parametro`
(
 `idParametro`    int NOT NULL AUTO_INCREMENT ,
 `nome`           varchar(45) NOT NULL ,
 `abreviacaoNome` varchar(45) NULL ,
 `escalaInicio`   decimal(8,4) NULL ,
 `escalaFim`      decimal(8,4) NULL ,
 `vlrIdealInicio` decimal(8,4) NULL ,
 `vlrIdealFim`    decimal(8,4) NULL ,
 `dtCadastro`     datetime NOT NULL ,
 `dtAtualizacao`  datetime NOT NULL ,

PRIMARY KEY (`idParametro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ************************************** `ParametroHistorico`

CREATE TABLE IF NOT EXISTS `ParametroHistorico`
(
 `idParamHistorico` int NOT NULL AUTO_INCREMENT ,
 `idParametro`      int NOT NULL ,
 `idAquario`        int NOT NULL ,
 `vlrMedido`        decimal(8,4) NULL ,
 `dtCadastro`       datetime NOT NULL ,
 `dtAtualizacao`    datetime NOT NULL ,

PRIMARY KEY (`idParamHistorico`),
CONSTRAINT `FK_164` FOREIGN KEY (`idParametro`) REFERENCES `parametro` (`idParametro`),
CONSTRAINT `FK_167` FOREIGN KEY (`idAquario`) REFERENCES `Aquario` (`idAquario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;