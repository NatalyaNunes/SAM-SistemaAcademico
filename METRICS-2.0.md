## Métricas DORA do Projeto SAM - Resultados Atuais

### Histórico de Períodos

| Sprint       | Período                     | Duração      |
| ------------ | --------------------------- | ------------ |
| Sprint 0 + 1 | 18/04/2026 – 17/05/2026     | 29 dias      |
| Sprint 2     | 18/05/2026 – 07/06/2026     | 20 dias      |
| **Sprint 3** | **08/06/2026 – 27/06/2026** | **~19 dias** |

---

### Sprint 3 — Dados Coletados (08/06 – 27/06/2026)

#### Informações Gerais

| Métrica                              | Sprint 1+2        | Sprint 3          | Variação |
| ------------------------------------ | ----------------- | ----------------- | -------- |
| **Total de Commits**                 | 49                | ~18 estimados     | –        |
| **Merges para main/develop**         | 4                 | ~3                | –        |
| **CI/CD Ativo**                      | Sim (desde 16/05) | Sim               | =        |
| **Testes automatizados na pipeline** | Não (-DskipTests) | Não (-DskipTests) | =        |

---

### 1. Deployment Frequency (DF) — Frequência de Implementação

#### Sprint 3

- **Frequência:** ~1 merge por semana (branch `feat-dashboard-mapa` → develop/main)
- **Nível DORA:** 🟡 **MÉDIO**
- **Observação:** O padrão de trabalho em feature branches longas reduz a frequência de integração. A branch `feat-dashboard-mapa` concentrou várias entregas que poderiam ter sido mergeadas incrementalmente.

#### Evolução

```
Sprint 1: ~1x/semana  🟡 Médio
Sprint 2: ~1x/semana  🟡 Médio
Sprint 3: ~1x/semana  🟡 Médio (sem melhora)
```

**Meta Sprint 4:** Integrar em branches menores por funcionalidade, aumentar para 2x/semana.

---

### 2. Lead Time for Changes (LTC) — Tempo de Espera para Mudanças

#### Sprint 3

- **Lead Time estimado:** 3–7 dias (feature branch com múltiplas entregas)
- **Nível DORA:** 🟡 **MÉDIO**
- **Fator de impacto:** A branch `feat-dashboard-mapa` acumulou dashboard, turmas ociosas, ranking e correção do Docker em um único ciclo, aumentando o lead time.

#### Evolução

```
Sprint 1: 2–5 dias (sem CI/CD)     🟡 Médio
Sprint 2: 1–3 dias (com CI/CD)     🟡 Médio
Sprint 3: 3–7 dias (branch longa)  🟡 Médio
```

---

### 3. Change Failure Rate (CFR) — Taxa de Falha de Mudança

#### Sprint 3

- **Taxa de Falha estimada:** ~22% (commits de correção relacionados ao Docker/banco)
- **Principal causa:** Ciclo de tentativa e erro na configuração do `docker-compose.yml` e `data.sql` (ordem de inicialização MySQL → Hibernate → seed)
- **Nível DORA:** 🟡 **MÉDIO-ALTO**

#### Commits de correção identificados na Sprint 3:

```
1. Correção do ScriptSAM.sql (DELETE antes das tabelas existirem)
2. Adição de SPRING_SQL_INIT_MODE e SPRING_JPA_DEFER_DATASOURCE_INITIALIZATION
3. Troca de docker-entrypoint para data.sql com INSERT IGNORE
4. Ajuste no docker-compose (remoção do volume do script SQL)
```

#### Evolução

```
Sprint 1: 18% 🟡
Sprint 2: 16% 🟡
Sprint 3: ~22% 🔴 (regressão — problemas de infraestrutura)
```

---

### 4. Mean Time to Recovery (MTTR) — Tempo Médio de Recuperação

#### Sprint 3

- **Status:** ⚠️ **EM DESENVOLVIMENTO** — sem ambiente de produção ativo
- **Incidente relevante:** Falha de inicialização do banco Docker (MySQL exited with code 1)
  - Tempo até identificação: ~20 min (log claro: `Table 'sam_db.pessoa' doesn't exist`)
  - Tempo até resolução: ~2h (tentativas de configuração do docker-compose + data.sql)
  - **MTTR estimado para este incidente: ~2h**
- **Nível DORA:** ⚠️ N/A (sem produção), mas incidente de dev recuperado em ~2h

---

## Resumo Comparativo DORA

| Métrica                   | Sprint 1 | Sprint 2 | Sprint 3  | Meta Sprint 4    |
| ------------------------- | -------- | -------- | --------- | ---------------- |
| **Deployment Frequency**  | 🟡 Médio | 🟡 Médio | 🟡 Médio  | 🟢 Alto (2x/sem) |
| **Lead Time for Changes** | 🟡 Médio | 🟡 Médio | 🟡 Médio  | 🟢 < 24h         |
| **Change Failure Rate**   | 18% 🟡   | 16% 🟡   | ~22% 🔴   | < 10% 🟢         |
| **Mean Time to Recovery** | N/A      | N/A      | ~2h (dev) | Monitorar        |

**Última Atualização:** 27 de Junho de 2026 (Sprint 3)
