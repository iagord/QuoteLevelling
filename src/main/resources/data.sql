INSERT INTO TB_ENDERECO (rua_endereco, cid_endereco, uf_endereco, cep_endereco, num_endereco)
VALUES ('Rua da Laranjinha', 'Laranjais', 'SP', '03026920', '30');

INSERT INTO TB_ENDERECO (rua_endereco, cid_endereco, uf_endereco, cep_endereco, num_endereco)
VALUES ('Rua da Bananeira Madura', 'Laranjais', 'LA', '03026123', '80');

INSERT INTO TB_ENDERECO (rua_endereco, cid_endereco, uf_endereco, cep_endereco, num_endereco)
VALUES ('Rua das Uvas Verdes', 'Laranjais', 'RJ', '03026333', '23');



INSERT INTO TB_EMPRESA (nm_empresa, desc_empresa, tp_empresa, id_endereco)
VALUES ('Kalunga', 'Escritorio/Eletronicos', 'FORNECEDOR', '1');

INSERT INTO TB_EMPRESA (nm_empresa, desc_empresa, tp_empresa, id_endereco)
VALUES ('Fiap', 'Instituicao de ensino', 'CLIENTE', '2');

INSERT INTO TB_EMPRESA (nm_empresa, desc_empresa, tp_empresa, id_endereco)
VALUES ('Tok&Stock', 'Moveis', 'FORNECEDOR', '3');

INSERT INTO TB_FORNECEDOR (avaliacao_fornecedor, cnpj_fornecedor, id_empresa)
VALUES (4.5, '11111111111222', '1');

INSERT INTO TB_FORNECEDOR (avaliacao_fornecedor, cnpj_fornecedor, id_empresa)
VALUES (4.7, '11111111111442', '3');

INSERT INTO TB_CLIENTE (cnpj_cliente, id_empresa)
VALUES ('11111111111333', '2');



INSERT INTO TB_MATERIAL (nm_material, desc_material, qtd_material, valunit_material, id_fornecedor)
VALUES ('Caneta', 'Caneta Azul Bic', '100', 1.50, '1');

INSERT INTO TB_MATERIAL (nm_material, desc_material, qtd_material, valunit_material, id_fornecedor)
VALUES ('Cadeira', 'Cadeira Escritorio', '10', 200, '1');

INSERT INTO TB_MATERIAL (nm_material, desc_material, qtd_material, valunit_material, id_fornecedor)
VALUES ('Mesa', 'Mesa para Reunioes', '5', 1000, '3');



INSERT INTO TB_RESPONSAVEL (nm_responsavel, desc_responsavel, matri_responsavel, id_fornecedor, id_cliente)
VALUES ('Hebert Lins', 'Responsavel pela empresa Kalunga', '1', '1', '2');



INSERT INTO TB_COTACAO (qtdtot_cotacao, valtot_cotacao, id_cliente, id_fornecedor)
VALUES ('15', 221.00, '2', '1');

INSERT INTO TB_COTACAO_MATERIAL (id_cotacao, id_material)
VALUES 
('1', '1'),
('1', '2');

