    const btnSalvarTurma = document.getElementById("btnSalvarTurma");

    if (btnSalvarTurma) {
        btnSalvarTurma.addEventListener("click", async function () {
            
            const payloadTurma = {
                disciplina: {
                    idDisciplina: parseInt(document.getElementById("idDisciplinaTurma").value)
                },
                numero: parseInt(document.getElementById("numeroTurma").value),
                professor: {
                    idPessoa: parseInt(document.getElementById("idProfessorTurma").value) 
                },
                capacidade: parseInt(document.getElementById("capacidadeTurma").value),
                horario: document.getElementById("horarioTurma").value,
                ano: parseInt(document.getElementById("anoTurma").value),
                semestre: parseInt(document.getElementById("semestreTurma").value)
            };

            try {
                const response = await fetch("/api/turmas", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(payloadTurma)
                });

                if (response.status === 201) {
                    alert("Turma cadastrada com sucesso!");
                    document.getElementById("formNovaTurma").reset();
                    bootstrap.Modal.getInstance(document.getElementById('modalNovaTurma')).hide();
                    window.location.reload();
                } else {
                    alert("Erro ao cadastrar turma.");
                    console.error(await response.text());
                }
            } catch (error) {
                console.error("Erro:", error);
                alert("Erro de conexão.");
            }
        });
    }