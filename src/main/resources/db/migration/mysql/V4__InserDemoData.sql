-- TAXONOMIA
INSERT INTO BiotaTaxonomia(dominio, reino, filo, classe, ordem, familia, genero, especie) VALUES
('Animalia', 'Animalia', null, null, 'Acanthuriformes', 'Acanthuridae', 'Paracanthurus', 'hepatus');

-- VINCULA BIOTA A SUA TAXONOMIA
UPDATE Biota SET idTaxonomia = 1 WHERE idBiota = 2;