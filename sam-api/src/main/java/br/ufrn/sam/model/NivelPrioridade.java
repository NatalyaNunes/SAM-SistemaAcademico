package br.ufrn.sam.model;

/**
 * Legenda de Prioridades:
 * I    - Aluno Nivelado
 * II   - Aluno Pré-concluinte
 * III  - Aluno em Recuperação
 * IIIE - Aluno em Recuperação com prioridade (III*)
 * IV   - Aluno Adiantado
 * V    - Aluno cursando componente eletivo
 *
 * A ordem dos valores no enum reflete a ordem de prioridade:
 * quanto menor o ordinal, maior a prioridade.
 */
public enum NivelPrioridade {
    NIVEL_I,
    NIVEL_II,
    NIVEL_III,
    NIVEL_III_PRIORIDADE,
    NIVEL_IV,
    NIVEL_V
}