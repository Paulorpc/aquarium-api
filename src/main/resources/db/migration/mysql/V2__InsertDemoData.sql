-- ****************** MySQL: Demo Datas ******************;
-- *******************************************************;

INSERT INTO TipoAquario (idTipoAquario, tipo, tipoAgua, status, descricao, dtCadastro, dtAtualizacao) VALUES
(null, 'Fish Only', 'Salgada',1, 'Aquário apenas de peixes', NOW(), NOW()),
(null, 'Reef', 'Salgada',1, 'Aquário de peixes e corais', NOW(), NOW());

INSERT INTO Aquario (nome, dtInicio, idTipoAquario, tipoAgua, tamanho, volume, iluminacao, filtragem, substrato, status, dtCadastro, dtAtualizacao)
VALUES('CzarReef I', '2019-06-01', 2, 'Salgada', '130 X 30 X 50', 200, '4x T5 54x', 'Sump', 'Samoa Pink #1', 1, NOW(), NOW());
