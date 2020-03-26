-- ****************** SqlDBM: MySQL ******************
-- ******************************************************************

-- ************************************** [Aquario]

CREATE TABLE Aquario
(
 `idAquario`     int NOT NULL ,
 `nome`          varchar(50) NOT NULL ,
 `dtAquisicao`   datetime(3) NULL ,
 `dtInicio`      datetime(3) NOT NULL ,
 `dtFinal`       datetime(3) NULL ,
 `tipoAgua`      enum('Doce', 'Salgada') NOT NULL ,
 `tamanho`       int NULL ,
 `volume`        int NULL ,
 `iluminacao`    varchar(50) NULL ,
 `filtragem`     varchar(50) NULL ,
 `sistemaCO2`    varchar(50) NULL ,
 `dosagem`       varchar(50) NULL ,
 `substrato`     varchar(50) NULL ,
 `parametros`    int NULL ,
 `status`        tinyint NULL ,
 `foto`          int NULL ,
 `observacao`    longtext NULL ,
 `idTipoAquario` int NOT NULL ,
 `dtCadastro`    datetime(3) NOT NULL ,
 `dtAtualizacao` datetime(3) NOT NULL ,


 CONSTRAINT `PK_Aquario` PRIMARY KEY (`idAquario` ASC),
 CONSTRAINT `FK_152` FOREIGN KEY (`idTipoAquario`)  REFERENCES TipoAquario(`idTipoAquario`)
);


CREATE NONCLUSTERED INDEX `fkIdx_152` ON [Aquario] 
 (
  `idTipoAquario` ASC
 )

GO


-- ************************************** [Biota]

CREATE TABLE Biota
(
 `idBiota`        int NOT NULL ,
 `nome`           varbinary(50) NOT NULL ,
 `dtAquisicao`    datetime(3) NOT NULL ,
 `dtNascimento`   datetime(3) NOT NULL ,
 `dtObito`        datetime(3) NOT NULL ,
 `idAquario`      int NOT NULL ,
 `tipoBiota`      int NULL ,
 `genero`         char(1) NULL ,
 `vlrUnitario`    decimal(15,4) NULL ,
 `qtde`           smallint NULL ,
 `foto`           longblob NULL ,
 `avaliacao`      decimal(4,2) NULL ,
 `observacao`     longtext NULL ,
 `familia`        varchar(50) NULL ,
 `nomeCientifico` varchar(50) NULL ,
 `nomePopular`    varchar(50) NULL ,
 `dtCadastro`     datetime(3) NOT NULL ,
 `dtAtualizacao`  datetime(3) NOT NULL ,


 CONSTRAINT `PK_Biota` PRIMARY KEY (`idBiota` ASC),
 CONSTRAINT `FK_42` FOREIGN KEY (`idAquario`)  REFERENCES Aquario(`idAquario`)
);


CREATE NONCLUSTERED INDEX `fkIdx_42` ON [Biota] 
 (
  `idAquario` ASC
 )

GO

-- ************************************** [Equipamento]

CREATE TABLE Equipamento
(
 `idEquipamento`  int NOT NULL ,
 `nome`           varchar(50) NOT NULL ,
 `dtAquisicao`    datetime(3) NULL ,
 `tipo`           varchar(50) NULL ,
 `vlrUnitario`    decimal(15,4) NULL ,
 `qtde`           smallint NOT NULL ,
 `foto`           longblob NULL ,
 `modelo`         varchar(50) NULL ,
 `fabricante`     varchar(50) NULL ,
 `dtSubstituicao` datetime(3) NULL ,
 `potencia`       smallint NULL ,
 `observacao`     longtext NULL ,
 `dtCadastro`     datetime(3) NOT NULL ,
 `dtAtualizacao`  datetime(3) NOT NULL ,


 CONSTRAINT `PK_equipamento` PRIMARY KEY (`idEquipamento` ASC)
);


-- ************************************** [EquipamentoAquario]

CREATE TABLE EquipamentoAquario
(
 `id`             NOT NULL ,
 `idAquario`     int NOT NULL ,
 `idEquipamento` int NOT NULL ,
 `dtInicio`      datetime(3) NOT NULL ,
 `dtFim`         datetime(3) NULL ,
 `dtAtualizacao` datetime(3) NOT NULL ,


 CONSTRAINT `PK_equipamentoAquario` PRIMARY KEY (`id` ASC),
 CONSTRAINT `FK_82` FOREIGN KEY (`idAquario`)  REFERENCES Aquario(`idAquario`),
 CONSTRAINT `FK_88` FOREIGN KEY (`idEquipamento`)  REFERENCES Equipamento(`idEquipamento`)
);


CREATE NONCLUSTERED INDEX `fkIdx_82` ON [EquipamentoAquario] 
 (
  `idAquario` ASC
 )

GO

CREATE NONCLUSTERED INDEX `fkIdx_88` ON [EquipamentoAquario] 
 (
  `idEquipamento` ASC
 )

GO


-- ************************************** [Parametro]

CREATE TABLE Parametro
(
 `idParametro`    int NOT NULL ,
 `nome`           varchar(50) NOT NULL ,
 `abreviacaoNome` char(5) NULL ,
 `escalaInicio`   smallint NULL ,
 `escalaFim`      smallint NULL ,
 `vlrIdealInicio` smallint NULL ,
 `vlrIdealFim`    smallint NULL ,
 `dtCadastro`     datetime(3) NOT NULL ,
 `dtAtualizacao`  datetime(3) NOT NULL ,


 CONSTRAINT `PK_parametro` PRIMARY KEY (`idParametro` ASC)
);


-- ************************************** [FaunaFlora]

CREATE TABLE Organismo
(
 `idOrganismo`    int NOT NULL ,
 `nome`            varchar(50) NOT NULL ,
 `nomeCientifico`  varchar(50) NULL ,
 `nomePopular`     varchar(50) NULL ,
 `avaliacao`       decimal(4,2) NULL ,
 `foto`            longblob NULL ,
 `tipoAgua`         enum('Doce', 'Salgada') NOT NULL,
 `nivelCuidado`     enum('Iniciante', 'Intermediário', 'Avançado') NULL,
 `disposicao`       enum('Pacífico', 'Semiagressivo', 'Agressivo') NULL,
 `volumeMinAquario` int NULL,
 `alimentacao`      varchar(50) NULL ,
 `regiao`		    varchar(50) NULL ,
 `tamanho`          enum('Pequeno', 'Médio', 'Grande') NULL,
 `observacao`       longtext NULL ,
 `dtCadastro`       datetime(3) NOT NULL ,
 `dtAtualizacao`    datetime(3) NOT NULL , 


 CONSTRAINT `PK_organismo` PRIMARY KEY (`idOrganismo` ASC)
);


-- ************************************** [ParametroSistema]

CREATE TABLE ParametroSistema
(
 `idParametroSistema` int NOT NULL ,
 `nome`               varchar(50) NOT NULL ,
 `modulo`             varchar(50) NOT NULL ,
 `dtCadastro`         datetime(3) NOT NULL ,
 `dtAtualizacao`      datetime(3) NOT NULL ,


 CONSTRAINT `PK_ParametroSistema` PRIMARY KEY (`idParametroSistema` ASC)
);


-- ************************************** [ParametroHistorico]

CREATE TABLE ParametroHistorico
(
 `idParametroHistorico` int NOT NULL ,
 `idParametro`          int NOT NULL ,
 `dtCadastro`           datetime(3) NOT NULL ,
 `vlrMedido`            smallint NOT NULL ,
 `idAquario`            int NOT NULL ,


 CONSTRAINT `PK_parametroHistorico` PRIMARY KEY (`idParametroHistorico` ASC),
 CONSTRAINT `FK_113` FOREIGN KEY (`idParametro`)  REFERENCES Parametro(`idParametro`),
 CONSTRAINT `FK_126` FOREIGN KEY (`idAquario`)  REFERENCES Aquario(`idAquario`)
);


CREATE NONCLUSTERED INDEX `fkIdx_113` ON [ParametroHistorico] 
 (
  `idParametro` ASC
 )

GO

CREATE NONCLUSTERED INDEX `fkIdx_126` ON [ParametroHistorico] 
 (
  `idAquario` ASC
 )

GO


-- ************************************** [TipoAquario]

CREATE TABLE TipoAquario
(
 `idTipoAquario` int NOT NULL ,
 `tipo`          varchar(50) NOT NULL ,
 `tipoAgua`      enum('Doce', 'Salgada') NOT NULL ,
 `status`        tinyint NOT NULL ,
 `descricao`     longtext NOT NULL ,
 `dtCadastro`    datetime(3) NOT NULL ,
 `dtAtualizacao` datetime(3) NOT NULL ,


 CONSTRAINT `PK_TipoAquario` PRIMARY KEY (`idTipoAquario` ASC)
);


-- ************************************** [ParametroSistemaValor]

CREATE TABLE ParametroSistemaValor
(
 `idParamSisValor`    int NOT NULL ,
 `idParametroSistema` int NOT NULL ,
 `valor`              varchar(50) NOT NULL ,
 `descricao`          varchar(50) NULL ,
 `dtCadastro`         datetime(3) NOT NULL ,
 `dtAtualizacao`      datetime(3) NOT NULL ,


 CONSTRAINT `PK_ParametroSistemaValor` PRIMARY KEY (`idParamSisValor` ASC),
 CONSTRAINT `FK_140` FOREIGN KEY (`idParametroSistema`)  REFERENCES ParametroSistema(`idParametroSistema`)
);


CREATE NONCLUSTERED INDEX `fkIdx_140` ON [ParametroSistemaValor] 
 (
  `idParametroSistema` ASC
 )

GO