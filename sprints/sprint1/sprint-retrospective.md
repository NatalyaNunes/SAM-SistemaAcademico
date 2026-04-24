# Retrospectiva — Sprint 1

## O que foi entregue
- Configuração de ambiente e repositório (#21)
- Modelagem do banco de dados (#20)
- US01 - Cadastro de Perfil Acadêmico (#9)
- Início da prototipagem das telas do sistema

## O que foi bem
- A estrutura do projeto foi organizada desde o início seguindo o padrão
  Spring Boot (model, repository, service, controller), facilitando o desenvolvimento
- A decisão de usar repositório em memória permitiu avançar no desenvolvimento
  sem depender do banco de dados, que ainda estava sendo modelado
- A modelagem do banco ficou alinhada com as entidades já implementadas no código
- A prototipagem iniciada permite antecipar decisões de interface antes do desenvolvimento

## O que pode melhorar
- As issues foram criadas com descrições vagas no início e precisaram ser
  reescritas durante a sprint
- Os endpoints foram testados apenas manualmente no Swagger,
  sem testes automatizados

## O que faremos diferente na Sprint 2
- Escrever as issues com critérios de aceitação bem definidos desde o início
- Integrar o MySQL substituindo o repositório em memória
- Implementar as demais entidades: Disciplina, Turma e Professor
- Evoluir os protótipos de tela 