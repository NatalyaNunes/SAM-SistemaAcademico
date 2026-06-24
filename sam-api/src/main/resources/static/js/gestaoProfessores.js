document.addEventListener("DOMContentLoaded", function () {

    const btnSalvarProf = document.getElementById("btnSalvarProfessor");

    if (btnSalvarProf) {
        btnSalvarProf.addEventListener("click", async function () {

            const payload = {
                nome: document.getElementById("nomeProfessor").value,
                siape: document.getElementById("siapeProfessor").value,
                departamento: document.getElementById("departamentoProfessor").value,
                login: document.getElementById("emailProfessor").value,
                isAluno: false,
                senha: "mudar123",
                titulacao: ""
            };

            try {
                const response = await fetch("/api/professores", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(payload)
                });

                if (response.status === 201) {
                    alert("Professor cadastrado com sucesso!");

                    document.getElementById("formNovoProfessor").reset();
                    const modal = bootstrap.Modal.getInstance(document.getElementById('modalNovoProfessor'));
                    modal.hide();

                    window.location.reload();
                } else {
                    alert("Erro ao cadastrar. Verifique o console.");
                    console.log(await response.text());
                }
            } catch (error) {
                console.error("Erro:", error);
                alert("Erro de conexão com a API.");
            }
        });
    }

    function prepararEdicaoProfessor(botao) {
        document.getElementById("idProfessorEdit").value = botao.getAttribute("data-id");
        document.getElementById("nomeProfessor").value = botao.getAttribute("data-nome");
        document.getElementById("siapeProfessor").value = botao.getAttribute("data-siape");
        document.getElementById("emailProfessor").value = botao.getAttribute("data-email");
        document.getElementById("departamentoProfessor").value = botao.getAttribute("data-dept");
        document.getElementById("titulacaoProfessor").value = botao.getAttribute("data-tit");
    }
});