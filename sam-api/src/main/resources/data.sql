INSERT IGNORE INTO pessoa (is_aluno, login, senha) VALUES 
(1, 'natalya.gabriele@ufrn', 'senha123'),
(1, 'joao.silva@ufrn', 'senha123'),
(1, 'maria.oliveira@ufrn', 'senha123'),
(0, 'carlos.augusto@imd', 'prof123'),
(0, 'ana.beatriz@imd', 'prof123'),
(0, 'marcos.paulo@imd', 'prof123');

INSERT IGNORE INTO aluno (id_aluno, id_pessoa, matricula, nome, curso, periodo, ira, iea, is_concluinte, nivel_prioridade, ingresso_vestibular_primeiro_periodo, perfil_inicial, periodos_regulares_cursados) VALUES 
(1, 1, '20240023849', 'Natalya Gabriele', 'Bacharelado em Tecnologia da Informacao', 4, 9.2, 8.8, 0, 'NIVEL_I', 0, 1, 3),
(2, 2, '20230011223', 'Joao Silva', 'Bacharelado em Tecnologia da Informacao', 6, 7.5, 7.0, 0, 'NIVEL_II', 0, 1, 5),
(3, 3, '20220044556', 'Maria Oliveira', 'Bacharelado em Tecnologia da Informacao', 8, 8.9, 8.5, 1, 'NIVEL_I', 1, 2, 7);

INSERT IGNORE INTO professor (id_professor, id_pessoa, siape, nome, departamento, titulacao) VALUES 
(1, 4, '1234567', 'Carlos Augusto', 'Instituto Metropole Digital', 'Doutorado'),
(2, 5, '7654321', 'Ana Beatriz', 'Instituto Metropole Digital', 'Mestrado'),
(3, 6, '1122334', 'Marcos Paulo', 'Instituto Metropole Digital', 'Doutorado');

INSERT IGNORE INTO disciplina (codigo, nome, ch_total, primeiro_nivel) VALUES 
('DIM0510', 'Processos de Software', 60, 0),
('IMD0029', 'Estrutura de Dados I', 60, 1),
('DIM0124', 'Banco de Dados', 60, 0);

INSERT IGNORE INTO turma (numero, horario, ano, semestre, capacidade, id_disciplina, id_professor) VALUES 
(1, '35T12', 2026, 1, 40, 1, 6),
(1, '24M34', 2026, 1, 50, 2, 4),
(2, '24T12', 2026, 1, 35, 3, 5);

INSERT IGNORE INTO interesse (aluno_id, turma_id, data_registro, posicao_lista) VALUES 
(1, 1, '2026-06-20', 1),
(1, 3, '2026-06-20', 5),
(2, 1, '2026-06-20', 2),
(3, 2, '2026-06-20', 1);