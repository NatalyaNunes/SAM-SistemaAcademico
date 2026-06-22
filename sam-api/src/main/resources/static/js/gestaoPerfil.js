document.addEventListener("DOMContentLoaded", function () {

    //Login de Aluno
    const formLogin = document.getElementById("formLogin");

    if (formLogin) {
        formLogin.addEventListener("submit", async function (event) {
            event.preventDefault();

            const payload = {
                login: document.getElementById("emailInput").value,
                senha: document.getElementById("passwordInput").value
            };

            try {
                const response = await fetch("/api/login", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(payload)
                });

                if (response.status === 200) {
                    alert("Login realizado com sucesso! Bem-vindo(a).");
                    const modalInstancia = bootstrap.Modal.getInstance(document.getElementById('loginModal'));
                    modalInstancia.hide();

                    window.location.href = "/dashboardAluno";
                } else if (response.status === 401) {
                    alert("E-mail ou senha incorretos. Tente novamente.");
                } else {
                    alert("Erro ao tentar fazer login. Tente mais tarde.");
                }
            } catch (error) {
                console.error("Erro:", error);
                alert("Erro de conexão com o servidor.");
            }
        });
    }

    //Cadastro de Aluno
    const formCadastro = document.getElementById("formCadastroAluno");

    formCadastro.addEventListener("submit", async function (event) {
        event.preventDefault();

        const senha = document.getElementById("senhaProvisoria").value;
        const confirmaSenha = document.getElementById("confirmaSenha").value;

        if (senha !== confirmaSenha) {
            alert("As senhas não coincidem!");
            return;
        }

        const iraFormatado = document.getElementById("ira").value.replace(',', '.');
        const ieaFormatado = document.getElementById("iea").value.replace(',', '.');
        const isConcluinte = document.querySelector('input[name="is_concluinte"]:checked').value === "sim";

        const payload = {
            nome: document.getElementById("nomeAluno").value,
            matricula: document.getElementById("matricula").value,
            login: document.getElementById("emailInstitucional").value,
            senha: senha,
            isAluno: true,
            periodo: parseInt(document.getElementById("periodo").value),
            ira: parseFloat(iraFormatado),
            iea: parseFloat(ieaFormatado),
            isConcluinte: isConcluinte,
            curso: document.getElementById("curso").value
        };

        try {
            const response = await fetch("/api/alunos", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload)
            });

            if (response.status === 201) {
                alert("Cadastro realizado com sucesso!");
                formCadastro.reset();
                const modalInstancia = bootstrap.Modal.getInstance(document.getElementById('cadastroAlunoModal'));
                modalInstancia.hide();
            } else {
                alert("Erro ao realizar o cadastro. Verifique os dados no console.");
                console.log(await response.text());
            }
        } catch (error) {
            console.error("Erro:", error);
            alert("Erro de conexão com a API.");
        }
    });

    const modalCadastro = document.getElementById('cadastroAlunoModal');

    modalCadastro.addEventListener('hide.bs.modal', function () {
        if (document.activeElement) {
            document.activeElement.blur();
        }
    });

    const modalLogin = document.getElementById('loginModal');
    modalLogin.addEventListener('hide.bs.modal', function () {
        if (document.activeElement) {
            document.activeElement.blur();
        }
    });
});

