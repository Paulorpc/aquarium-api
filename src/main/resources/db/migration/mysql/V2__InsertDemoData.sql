-- ****************** MySQL: Demo Datas ******************;
-- *******************************************************;

-- TIPO AQUARIO
INSERT INTO TipoAquario (idTipoAquario, tipo, tipoAgua, status, descricao, dtCadastro, dtAtualizacao) VALUES
(null, 'Fish Only', 'Salgada',1, 'Aquário apenas de peixes', NOW(), NOW()),
(null, 'Reef', 'Salgada',1, 'Aquário de peixes e corais', NOW(), NOW());

-- AQUARIO
INSERT INTO Aquario (nome, dtInicio, idTipoAquario, tipoAgua, tamanho, volume, iluminacao, filtragem, substrato, status, dtCadastro, dtAtualizacao)
VALUES('CzarReef I', '2019-06-01', 2, 'Salgada', '130 X 30 X 50', 200, '4x T5 54x', 'Sump', 'Samoa Pink #1', 1, NOW(), NOW());

-- BIOTA
INSERT INTO Biota (nomePopular, nomeCientifico, tipoAgua, nivelCuidado, volumeMinAquario, alimentacao, habitat, regiao, tamanho, riscoExtincao, avaliacao,dtCadastro, dtAtualizacao, bloqueadoAlteracao) VALUES
('Tang Amarelo e Cirurgião Amarelo', 'Zebrasoma flavescens', 'salgada', 'iniciante', 180, 'herbívoro', 'Águas tropicais ricas em coral', 'Oceano Pacífico: Ryukyu, Mariana, Marshall, Marcus, ilhas havaianas. Foi relatado na costa da Flórida no Atlântico Centro-Oeste', 'G', 'LC', 5, NOW(), NOW(), false),
('Tang Azul, Blue Tang e Peixe-cirurgião de paleta. Também conhecido como Dory em relação ao filme procurando Nemo da Disney.', 'Paracanthurus hepatus', 'salgada', 'intermediario', 378.54, 'Onívoro', 'Recife de Corais e Rochas', 'Indo-Pacífico: Leste da Africa, Japão, Sul da grande barreira de corais, Nova Caledonia e Samoa.', 'G', 'LC', 5, NOW(), NOW(), false); 


