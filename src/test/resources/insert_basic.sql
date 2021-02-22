insert into tipoaquario(descricao, dtatualizacao, dtcadastro, status, tipo, tipoagua) values('Reef', now(), now(), true, 'marinho', 'salgada');
insert into tipoaquario(descricao, dtatualizacao, dtcadastro, status, tipo, tipoagua) values('Corais', now(), now(), true, 'marinho', 'salgada');

insert into Aquario (nome, tipoAgua, volume, status, dtAtualizacao, dtCadastro, dtInicio, dtFinal, idTipoAquario) values('aquario 1', 'S', 200, 1, now(),now(), now(),now(), 1);
insert into Aquario (nome, tipoAgua, volume, status, dtAtualizacao, dtCadastro, dtInicio, dtFinal, idTipoAquario) values('aquario 2', 'S', 50, 1, now(),now(), now(),now(), 2);
insert into Aquario (nome, tipoAgua, volume, status, dtAtualizacao, dtCadastro, dtInicio, dtFinal, idTipoAquario) values('aquario 3', 'D', 100, 0, now(),now(), now(),now(), 1);

INSERT INTO biota (nomePopular, nomeCientifico, tipoAgua, nivelCuidado, volumeMinAquario, alimentacao, habitat, regiao, tamanho, riscoExtincao, avaliacao,dtCadastro, dtAtualizacao, usuarioCadastro, usuarioAtualizacao) VALUES('Tang Amarelo e Cirurgião Amarelo', 'Zebrasoma flavescens', 'salgada', 'iniciante', 180, 'herbívoro', 'Águas tropicais ricas em coral', 'Oceano Pacífico: Ryukyu, Mariana, Marshall, Marcus, ilhas havaianas. Foi relatado na costa da Flórida no Atlântico Centro-Oeste', 'G', 'LC', 5.0, NOW(), NOW(), 'sistema', 'sistema')
INSERT INTO biota (nomePopular, nomeCientifico, tipoAgua, nivelCuidado, volumeMinAquario, alimentacao, habitat, regiao, tamanho, riscoExtincao, avaliacao,dtCadastro, dtAtualizacao, usuarioCadastro, usuarioAtualizacao) VALUES('Tang Azul, Blue Tang e Peixe-cirurgião de paleta. Também conhecido como Dory em relação ao filme procurando Nemo da Disney.', 'Paracanthurus hepatus', 'salgada', 'intermediario', 378.54, 'Onívoro', 'Recife de Corais e Rochas', 'Indo-Pacífico: Leste da Africa, Japão, Sul da grande barreira de corais, Nova Caledonia e Samoa.', 'G', 'LC', 5.0, NOW(), NOW(), 'sistema', 'sistema')

insert into equipamento(nome, qtde, tipo, fabricante, modelo, potencia,dtaquisicao, avaliacao, dtcadastro, dtatualizacao) values('skimmer aviv-a600', '1', 'skimmer', 'aviv', 'a-600', 70, now(), 4, now(), now())
insert into equipamento(nome, qtde, tipo, fabricante, modelo, potencia,dtaquisicao, avaliacao, dtcadastro, dtatualizacao) values('bomba circulação', '2', 'bomba circulação', 'jebao', 'dc2000', 30, now(), 3, now(), now())

insert into equipamento_aquario(idAquario, idEquipamento) values(1, 1)
insert into equipamento_aquario(idAquario, idEquipamento) values(2, 1)
insert into equipamento_aquario(idAquario, idEquipamento) values(2, 2)