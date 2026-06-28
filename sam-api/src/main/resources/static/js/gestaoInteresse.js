document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.btn-adicionar').forEach(button => {
        button.addEventListener('click', function() {
            // "this" é o botão clicado
            const cardBody = this.closest('.card-body');
            
            // Busca pelos seletores corretos conforme sua imagem
            const idTurma = cardBody.querySelector('.id-turma').value;
            const matricula = cardBody.querySelector('.matricula').value;

            console.log("DEBUG -> ID Turma:", idTurma, "Matricula:", matricula);

            if (!idTurma || !matricula) {
                alert("Erro: Dados do card não encontrados!");
                return;
            }

            // Chama a função passando a matrícula e a turma
            executarPost(matricula, idTurma);
        });
    });
});

function executarPost(matricula, idTurma) {
    // Monta a URL usando a matrícula (String) e o id da turma
    fetch(`/api/interesses/${matricula}/${idTurma}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' }
    })
    .then(response => {
        if (response.ok) {
            alert("✅ Interesse registrado!");
            window.location.reload();
        } else {
            return response.text().then(text => { throw new Error(text); });
        }
    })
    .catch(err => {
        alert("⚠️ Erro: " + err.message);
    });
}