# Retrospectiva — Sprint 3 (Final)

## O que foi entregue

- Dashboard da Coordenação completamente dinâmico:
  - Gráfico de disciplinas mais procuradas via `/api/interesses/demanda-disciplinas`
  - Card "Total de Alunos Simulando" conectado a `/api/alunos/contagem`
  - Card "Disciplinas com Gargalo" calculado dinamicamente via `/api/turmas/com-ocupacao`
  - Card "Turmas Ociosas" conectado a `/api/turmas/ociosas/contagem`
  - Tabela "Fila de Espera" dinâmica com vagas reais, interessados e excedente
  - Colorização por ocupação: 🔴 excedente (> 100%), 🟡 ociosa (≤ 25%)
- Página de Turmas Ociosas com listagem e percentual de ocupação
- Endpoints no TurmaController: `/api/turmas/com-ocupacao`, `/api/turmas/ociosas`, `/api/turmas/ociosas/contagem`
- Endpoint `/api/alunos/contagem` no AlunoController
- TurmaService com lógica de cálculo de ocupação e filtro de turmas ociosas (≤ 25%)
- InteresseRankingComparator com regras de prioridade (§1º prioridade absoluta, NivelPrioridade, IEA como desempate)
- Endpoint de simulação de conflito de grade: `GET /api/interesses/aluno/{matricula}/simular-conflito/{idTurma}`
- Seed de dados via `data.sql` com `INSERT IGNORE`
- docker-compose.yml com `SPRING_SQL_INIT_MODE: always` e `SPRING_JPA_DEFER_DATASOURCE_INITIALIZATION: true`

## O que foi bem

- A separação entre lógica de negócio (TurmaService) e apresentação (Controller) permitiu adicionar os endpoints de ocupação sem alterar código existente
- O `InteresseRankingComparator` encapsula bem as três regras de prioridade, fiel às normas acadêmicas
- A solução com `data.sql` + `INSERT IGNORE` resolveu de forma limpa o problema de ordem de inicialização
- A query JPQL no `JpaInteresseRepository` funcionou corretamente
- O agrupamento por disciplina no frontend (somando turmas de mesma disciplina) permitiu uma visão consolidada sem precisar alterar o backend
- A colorização via `tr.style.borderLeft` e `tdInteressados.style.color` ficou limpa e sem strings de atributos frágeis

## O que pode melhorar

- O pipeline CI/CD ainda executa com `-DskipTests`, sem validação automatizada de código
- Ausência de testes unitários e de integração para Services e Controllers
- Ciclo de tentativa e erro na configuração do Docker consumiu tempo significativo — ausência de checklist de ambiente documentado

## Reflexão Final

O SAM evoluiu de um projeto sem dados reais para um dashboard completamente conectado à API em uma sprint. O principal aprendizado do ciclo foi que problemas de infraestrutura (Docker, seed de banco) têm raiz em decisões de processo — a ausência de documentação prévia sobre a ordem de inicialização gerou retrabalho que poderia ter sido evitado com um checklist simples. A qualidade do código backend foi consistente ao longo das sprints, mas a ausência de testes automatizados permanece como a maior dívida técnica do projeto.
