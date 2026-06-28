# Retrospectiva — Sprint 3

## O que foi entregue

- US Dashboard Coordenação: gráfico de disciplinas mais procuradas via `/api/interesses/demanda-disciplinas` (Chart.js + endpoint REST)
- Página de Turmas Ociosas com listagem e percentual de ocupação (`/coordenacao/turmasOciosas`)
- Endpoints novos no TurmaController: `/api/turmas/com-ocupacao`, `/api/turmas/ociosas`, `/api/turmas/ociosas/contagem`
- TurmaService com lógica de cálculo de ocupação (`calcularOcupacao`) e filtro de turmas ociosas (≤ 25%)
- InteresseRankingComparator implementando as regras de prioridade (§1º prioridade absoluta, NivelPrioridade, IEA como desempate)
- Endpoint de simulação de conflito de grade: `GET /api/interesses/aluno/{matricula}/simular-conflito/{idTurma}`
- Dados de seed populados via `data.sql` com `INSERT IGNORE` (corrigindo o problema de inicialização do banco)
- docker-compose.yml com `SPRING_SQL_INIT_MODE: always` e `SPRING_JPA_DEFER_DATASOURCE_INITIALIZATION: true`

## O que foi bem

- A separação entre lógica de negócio (TurmaService) e apresentação (Controller) facilitou adicionar os endpoints de ocupação sem alterar código existente
- O `InteresseRankingComparator` encapsula bem as três regras de prioridade, tornando o código legível e fiel às normas acadêmicas
- A solução com `data.sql` + `INSERT IGNORE` resolveu de forma limpa o problema de ordem de inicialização (script rodando antes das tabelas existirem)
- A query JPQL no `JpaInteresseRepository` para disciplinas mais procuradas funcionou corretamente, com o Hibernate gerando SQL otimizado

## O que pode melhorar

- Ausência de testes automatizados: nenhum teste unitário ou de integração foi criado para os Services e Controllers novos desta sprint
