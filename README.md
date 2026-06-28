# SAM - Sistema de Apoio à Matrícula

> Sistema desenvolvido para auxiliar alunos e coordenações acadêmicas no planejamento do período de matrícula, oferecendo simulação de interesses, visualização de rankings e indicadores sobre ocupação das turmas.

---

## Sobre o projeto

O **SAM (Sistema de Apoio à Matrícula)** é uma aplicação web desenvolvida durante a disciplina **Processos de Software** da **Universidade Federal do Rio Grande do Norte (UFRN)**.

O sistema foi criado para reduzir a incerteza enfrentada pelos estudantes durante o período de matrícula, permitindo acompanhar a posição em rankings de prioridade, registrar interesse em turmas e fornecer à coordenação informações estratégicas para tomada de decisão.

Entre os principais benefícios estão:

- acompanhamento da posição no ranking das turmas;
- visualização da ocupação das turmas em tempo real;
- gerenciamento de disciplinas, professores e turmas;
- dashboard para coordenação acadêmica;
- apoio ao planejamento da oferta de vagas.

---

# Funcionalidades

### Área do Aluno

- Login no sistema
- Visualização de turmas
- Cadastro de interesse em turmas
- Consulta de ranking
- Dashboard do aluno

### Área da Coordenação

- Dashboard com indicadores
- Visualização de turmas ociosas
- Identificação de turmas excedentes
- Gerenciamento de professores
- Gerenciamento de disciplinas
- Gerenciamento de turmas

---

# 🛠 Tecnologias utilizadas

## Backend

- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Maven

## Banco de Dados

- MySQL 8

## Frontend

- Thymeleaf
- HTML5
- CSS3
- JavaScript

## Infraestrutura

- Docker
- Docker Compose

---

# 📂 Estrutura do projeto

```
sam-api
├── src
│   ├── main
│   │   ├── java
│   │   ├── resources
│   │   │   ├── templates
│   │   │   ├── static
│   │   │   ├── application.properties
│   │   │   └── data.sql
│   └── test
├── Dockerfile
├── docker-compose.yml
└── pom.xml
```

---

# Como executar

## Pré-requisitos

É necessário possuir instalado:

- Docker Desktop

Não é necessário instalar Java, Maven ou MySQL localmente.

---

## 1. Clonar o projeto

```bash
git clone https://github.com/NatalyaNunes/SAM-SistemaAcademico.git
```

Entre na pasta da API:

```bash
cd SAM-SistemaAcademico/sam-api
```

---

## 2. Executar a aplicação

```bash
docker compose up --build
```

Na primeira execução o Docker irá:

- baixar as imagens;
- criar o banco MySQL;
- criar todas as tabelas;
- executar automaticamente o arquivo `data.sql`;
- iniciar a aplicação.

Aguarde aparecer no terminal:

```
Started SamApiApplication
```

Caso a aplicação tente iniciar antes do banco estar disponível, basta aguardar alguns instantes e executar novamente (ou utilizar a configuração `restart: on-failure`).

---

# 🌐 Acesso ao sistema

| Página                | URL                                                    |
| --------------------- | ------------------------------------------------------ |
| Login                 | http://localhost:8080/sam                              |
| Dashboard Coordenação | http://localhost:8080/coordenacao/dashboard            |
| Dashboard Aluno       | http://localhost:8080/dashboardAluno                   |
| Turmas                | http://localhost:8080/turmas                           |

---

# 👤 Usuários de teste

### Aluno

```
Login:
natalya.gabriele@ufrn

Senha:
senha123
```

### Professor

```
Login:
carlos.augusto@imd

Senha:
prof123
```

---

# Banco de dados

O projeto utiliza **MySQL 8** executando em container Docker.

As tabelas são criadas automaticamente pelo Hibernate e os dados iniciais são inseridos através do arquivo:

```
src/main/resources/data.sql
```

Não é necessário importar nenhum script manualmente.

---

# Dashboard da Coordenação

O dashboard disponibiliza indicadores que auxiliam a coordenação acadêmica na tomada de decisão.

Entre eles:

- quantidade de turmas;
- número de alunos interessados;
- turmas ociosas;
- turmas excedentes;
- gráfico de ocupação das turmas;
- tabela com situação das turmas.

---

# Processo de Desenvolvimento

O projeto foi desenvolvido utilizando práticas de Engenharia de Software estudadas na disciplina **Processos de Software**, incluindo:

- Git Flow
- GitHub Actions
- Integração Contínua (CI)
- Métricas DORA
- Lean Software Development
- Value Stream Mapping (VSM)
- Retrospectivas e melhoria contínua

---

# 👥 Equipe

- Bianca Bezerra Pires
- Bruna Lucena da Costa Souto
- Natálya Gabriele Nunes de Azevedo

Universidade Federal do Rio Grande do Norte (UFRN)

Instituto Metrópole Digital (IMD)

Disciplina: Processos de Software

---

# Encerrando a aplicação

Para parar todos os containers:

```bash
docker compose down
```

---

# 📄 Licença

Projeto desenvolvido para fins acadêmicos na disciplina **Processos de Software** da Universidade Federal do Rio Grande do Norte (UFRN).
