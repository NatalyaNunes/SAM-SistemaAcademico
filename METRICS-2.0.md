### Sprint 3 — Dados Coletados (08/06 – 27/06/2026) — Sprint Final

#### Informações Gerais

| Métrica                 | Sprint 1 | Sprint 2 | Sprint 3 |
| ----------------------- | -------- | -------- | -------- |
| **Total de Commits**    | 37       | 15       | 13       |
| **Merges/PRs**          | 4        | 5        | 6        |
| **Commits de correção** | 9        | 3        | 5        |
| **CFR**                 | 24%      | 20%      | 38%      |
| **CI/CD com testes**    | Não      | Não      | Não      |

---

### 1. Deployment Frequency (DF)

#### Sprint 3

- **Total de merges:** 6 PRs (PR #43, #44, #47, #48, #49, #50)
- **Frequência:** ~1,5 merges/semana (melhor que sprints anteriores)
- **Nível DORA:** 🟡 **MÉDIO**

#### Evolução

```
Sprint 1: 4 merges / ~4 semanas  = 1x/semana   🟡 Médio
Sprint 2: 5 merges / ~3 semanas  = 1,6x/semana 🟡 Médio
Sprint 3: 6 merges / ~3 semanas  = 2x/semana   🟡 Médio (tendência positiva)
```

---

### 2. Lead Time for Changes (LTC)

#### Sprint 3

- **Lead Time estimado:** 2–5 dias por feature branch
- **Nível DORA:** 🟡 **MÉDIO**
- **Observação:** 6 PRs em ~3 semanas indica branches menores que sprints anteriores, reduzindo o lead time em relação à Sprint 2.

#### Evolução

```
Sprint 1: 2–5 dias  🟡 Médio
Sprint 2: 3–7 dias  🟡 Médio
Sprint 3: 2–5 dias  🟡 Médio (leve melhora)
```

---

### 3. Change Failure Rate (CFR)

#### Sprint 3 — dados reais do git log

- **Total de commits:** 13
- **Commits de correção:** 5
- **CFR real: 38%** 🔴 **ALTO** (regressão significativa)

#### Commits de correção identificados:

```
8ad6ae4 - correção em dados
f1113bd - correção de codigo da turma pra numero e do interesse
8147e36 - fix: mudança de turma model e adição de numero pra turma
114e7e6 - fix: mudança de turma model e adição de numero pra turma (repetido)
f4f3fc0 - fix: correção de erro em relação a cadastro de turma
```

**Causa raiz:** Alteração no `TurmaModel` (campo `codigo` → `numero`) feita no meio da sprint gerou efeito cascata em repositório, service, controller e tela — dois commits de fix para o mesmo problema (114e7e6 e 8147e36), além de correção posterior no interesse (f1113bd). Padrão clássico de mudança estrutural sem atualização completa de todos os pontos de impacto.

#### Evolução

```
Sprint 1: 9/37 = 24%  🟡
Sprint 2: 3/15 = 20%  🟡
Sprint 3: 5/13 = 38%  🔴 (regressão)
Média geral: 17/65 = 26% 🟡
```

---

### 4. Mean Time to Recovery (MTTR)

#### Sprint 3

- **Incidente 1:** Bug na mudança de TurmaModel (codigo → numero)
  - Commits de fix: 114e7e6, 8147e36, f1113bd (3 commits para resolver)
  - MTTR estimado: ~1 dia
- **Incidente 2:** Falha de inicialização do banco Docker
  - MTTR estimado: ~2h
- **Incidente 3:** Bug de strings frágeis na colorização da tabela
  - MTTR estimado: ~15 min

---

## Resumo Comparativo DORA — Projeto Completo

| Métrica                   | Sprint 1    | Sprint 2    | Sprint 3     | Média Geral        |
| ------------------------- | ----------- | ----------- | ------------ | ------------------ |
| **Deployment Frequency**  | 1x/sem 🟡   | 1,6x/sem 🟡 | 2x/sem 🟡    | Tendência positiva |
| **Lead Time for Changes** | 2–5 dias 🟡 | 3–7 dias 🟡 | 2–5 dias 🟡  | 🟡 Médio           |
| **Change Failure Rate**   | 24% 🟡      | 20% 🟡      | 38% 🔴       | 26% 🟡             |
| **Mean Time to Recovery** | N/A         | N/A         | ~1 dia (dev) | Em desenvolvimento |

## Análise Final

A Sprint 3 apresentou a maior CFR do projeto (38%), causada principalmente pela mudança estrutural no `TurmaModel` que gerou efeito cascata em múltiplos arquivos. Esse padrão — alteração de modelo já integrado exigindo múltiplos commits de correção — foi previsto no 5 Whys do documento de proposta de melhoria como consequência da ausência de pesquisa documental e definição de requisitos antes da modelagem.

O ponto positivo foi a Deployment Frequency, que atingiu 2 merges/semana na Sprint 3, a melhor marca do projeto, indicando evolução na granularidade das entregas. O pipeline CI/CD permaneceu sem execução de testes (-DskipTests) durante todo o projeto, o que teria potencialmente detectado os erros de TurmaModel antes do merge e reduzido a CFR.

---

**Última Atualização:** 27 de Junho de 2026 (Sprint 3 — Final)
