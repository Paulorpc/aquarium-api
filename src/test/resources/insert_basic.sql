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

insert into parametro (idAquario, nome, abreviacaoNome, escalaInicio, escalaFim, vlrIdealInicio, vlrIdealFim, unidadeMedida, dtcadastro, dtatualizacao) values (1, 'Cálcio', 'ca', 300.0, 500, 400.0, 420.00, 'ppm', now(), now())
insert into parametro (idAquario, nome, abreviacaoNome, escalaInicio, escalaFim, vlrIdealInicio, vlrIdealFim, unidadeMedida, dtcadastro, dtatualizacao) values (1, 'Magnésio', 'mg', 1000.0, 1800.0, 1200.0, 1300.0, 'ppm', now(), now())
insert into parametro (idAquario, nome, abreviacaoNome, escalaInicio, escalaFim, vlrIdealInicio, vlrIdealFim, unidadeMedida, dtcadastro, dtatualizacao) values (1, 'Alcalinidade', 'KH', 4.0, 10.0, 7.0, 8.0, 'dKh', now(), now())
insert into parametro (idAquario, nome, abreviacaoNome, escalaInicio, escalaFim, vlrIdealInicio, vlrIdealFim, unidadeMedida, dtcadastro, dtatualizacao) values (1, 'Fosfato', 'po4', 0.0, 0.1, 0.01, 0.04, 'ppm', now(), now())
insert into parametro (idAquario, nome, abreviacaoNome, escalaInicio, escalaFim, vlrIdealInicio, vlrIdealFim, unidadeMedida, dtcadastro, dtatualizacao) values (1, 'Nitrato', 'No3', 0.0, 10.0, 0.0, 5.0, 'ppm', now(), now())
insert into parametro (idAquario, nome, abreviacaoNome, escalaInicio, escalaFim, vlrIdealInicio, vlrIdealFim, unidadeMedida, dtcadastro, dtatualizacao) values (2, 'Cálcio', 'ca', 300.0, 500, 410.0, 420.00, 'ppm', now(), now())
insert into parametro (idAquario, nome, abreviacaoNome, escalaInicio, escalaFim, vlrIdealInicio, vlrIdealFim, unidadeMedida, dtcadastro, dtatualizacao) values (2, 'Magnésio', 'mg', 800.0, 1800.0, 1400.0, 1500.0, 'ppm', now(), now())
insert into parametro (idAquario, nome, abreviacaoNome, escalaInicio, escalaFim, vlrIdealInicio, vlrIdealFim, unidadeMedida, dtcadastro, dtatualizacao) values (2, 'Alcalinidade', 'KH', 6.0, 9.0, 7.5, 8.0, 'dKh', now(), now())

insert into procedimentoTeste (idParametro, nroEtapa, procedimento, dtcadastro, dtatualizacao) values (1, 1, 'Adicionar 2 gotas reagente A', now(), now())
insert into procedimentoTeste (idParametro, nroEtapa, procedimento, dtcadastro, dtatualizacao) values (1, 2, 'Verificar valor siringa e comparar com cartela', now(), now())
insert into procedimentoTeste (idParametro, nroEtapa, procedimento, dtcadastro, dtatualizacao) values (2, 1, 'Adicionar cinco gotas reagente A', now(), now())
insert into procedimentoTeste (idParametro, nroEtapa, procedimento, dtcadastro, dtatualizacao) values (2, 2, 'Adicionar tres gotas reagente B', now(), now())
insert into procedimentoTeste (idParametro, nroEtapa, procedimento, dtcadastro, dtatualizacao) values (2, 3, 'Misturar dois minutos e aguardar mudança de cor', now(), now())
