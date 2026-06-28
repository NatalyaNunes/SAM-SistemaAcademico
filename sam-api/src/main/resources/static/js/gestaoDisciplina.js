    const btnSalvarDisc = document.getElementById("btnSalvarDisciplina");
    const isPrimeiroNivel = document.getElementById("primeiroNivelDisciplina").checked;

    if (btnSalvarDisc) {
        btnSalvarDisc.addEventListener("click", async function () {

            const payloadDisc = {
                codigo: document.getElementById("codigoDisciplina").value,
                nome: document.getElementById("nomeDisciplina").value,
                chTotal: parseInt(document.getElementById("chDisciplina").value),
                primeiroNivel: isPrimeiroNivel
            };

            try {
                const response = await fetch("/api/disciplinas", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(payloadDisc)
                });

                if (response.status === 201) {
                    alert("Disciplina cadastrada com sucesso!");
                    document.getElementById("formNovaDisciplina").reset();
                    bootstrap.Modal.getInstance(document.getElementById('modalNovaDisciplina')).hide();
                    window.location.reload();
                } else {
                    alert("Erro ao cadastrar disciplina.");
                    console.error(await response.text());
                }
            } catch (error) {
                console.error("Erro:", error);
                alert("Erro de conexão.");
            }
        });
    }