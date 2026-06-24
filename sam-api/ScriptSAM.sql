-- Limpar tabelas existentes (Ordem inversa da criação para não dar erro de chave estrangeira)
DELETE FROM interesse;
DELETE FROM turma;
DELETE FROM disciplina;
DELETE FROM professor;
DELETE FROM aluno;
DELETE FROM pessoa;

-- Resetar os contadores de ID para garantir que o script sempre comece do ID 1
ALTER TABLE pessoa AUTO_INCREMENT = 1;
ALTER TABLE disciplina AUTO_INCREMENT = 1;
ALTER TABLE turma AUTO_INCREMENT = 1;
ALTER TABLE interesse AUTO_INCREMENT = 1;

-- =====================================================================
-- 1. Inserir Pessoas (Base para Alunos e Professores)
-- =====================================================================
-- Pessoas que serão Alunos (id_pessoa: 1, 2 e 3)
INSERT INTO pessoa (is_aluno, login, senha) VALUES 
(1, 'natalya.gabriele@ufrn', 'senha123'),
(1, 'joao.silva@ufrn', 'senha123'),
(1, 'maria.oliveira@ufrn', 'senha123');

-- Pessoas que serão Professores (id_pessoa: 4, 5 e 6)
INSERT INTO pessoa (is_aluno, login, senha) VALUES 
(0, 'carlos.augusto@imd', 'prof123'),
(0, 'ana.beatriz@imd', 'prof123'),
(0, 'marcos.paulo@imd', 'prof123');

-- =====================================================================
-- 2. Inserir Alunos 
-- (Adicionado o id_aluno explicitamente para o MySQL não reclamar)
-- =====================================================================
INSERT INTO aluno (id_aluno, id_pessoa, matricula, nome, curso, periodo, ira, iea, is_concluinte, nivel_prioridade, ingresso_vestibular_primeiro_periodo, perfil_inicial, periodos_regulares_cursados) VALUES 
(1, 1, '20240023849', 'Natálya Gabriele', 'Bacharelado em Tecnologia da Informação', 4, 9.2, 8.8, 0, 'NIVEL_I', 0, 1, 3),
(2, 2, '20230011223', 'João Silva', 'Bacharelado em Tecnologia da Informação', 6, 7.5, 7.0, 0, 'NIVEL_II', 0, 1, 5),
(3, 3, '20220044556', 'Maria Oliveira', 'Bacharelado em Tecnologia da Informação', 8, 8.9, 8.5, 1, 'NIVEL_I', 1, 2, 7);

-- =====================================================================
-- 3. Inserir Professores 
-- (Adicionado o id_professor explicitamente para o MySQL não reclamar)
-- =====================================================================
INSERT INTO professor (id_professor, id_pessoa, siape, nome, departamento, titulacao) VALUES 
(1, 4, '1234567', 'Carlos Augusto', 'Instituto Metrópole Digital', 'Doutorado'),
(2, 5, '7654321', 'Ana Beatriz', 'Instituto Metrópole Digital', 'Mestrado'),
(3, 6, '1122334', 'Marcos Paulo', 'Instituto Metrópole Digital', 'Doutorado');

-- =====================================================================
-- 4. Inserir Disciplinas
-- =====================================================================
INSERT INTO disciplina (codigo, nome, ch_total, primeiro_nivel) VALUES 
('DIM0510', 'Processos de Software', 60, 0),
('IMD0029', 'Estrutura de Dados I', 60, 1),
('DIM0124', 'Banco de Dados', 60, 0);

-- =====================================================================
-- 5. Inserir Turmas (Corrigido com codigo e nome exigidos pelo banco)
-- =====================================================================
-- Turma 1: Processos de Software com Prof. Marcos Paulo (id_pessoa = 6)
INSERT INTO turma (numero, horario, ano, semestre, capacidade, id_disciplina, id_professor, codigo, nome) VALUES 
(1, '35T12', 2026, 1, 40, 1, 6, 'T01', 'Turma 01 - Processos');

-- Turma 2: Estrutura de Dados I com Prof. Carlos Augusto (id_pessoa = 4)
INSERT INTO turma (numero, horario, ano, semestre, capacidade, id_disciplina, id_professor, codigo, nome) VALUES 
(1, '24M34', 2026, 1, 50, 2, 4, 'T02', 'Turma 01 - ED');

-- Turma 3: Banco de Dados com Profa. Ana Beatriz (id_pessoa = 5)
INSERT INTO turma (numero, horario, ano, semestre, capacidade, id_disciplina, id_professor, codigo, nome) VALUES 
(2, '24T12', 2026, 1, 35, 3, 5, 'T03', 'Turma 02 - BDI');

-- =====================================================================
-- 6. Inserir Interesses (Ligando Alunos e Turmas)
-- =====================================================================
-- Natálya (id_aluno = 1) tem interesse na Turma 1 e Turma 3
INSERT INTO interesse (aluno_id, turma_id, data_registro, posicao_lista) VALUES 
(1, 1, '2026-06-20', 1),
(1, 3, '2026-06-20', 5);

-- João (id_aluno = 2) tem interesse na Turma 1
INSERT INTO interesse (aluno_id, turma_id, data_registro, posicao_lista) VALUES 
(2, 1, '2026-06-20', 2);

-- Maria (id_aluno = 3) tem interesse na Turma 2
INSERT INTO interesse (aluno_id, turma_id, data_registro, posicao_lista) VALUES 
(3, 2, '2026-06-20', 1);