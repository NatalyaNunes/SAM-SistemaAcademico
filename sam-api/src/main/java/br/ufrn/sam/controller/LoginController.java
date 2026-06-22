package br.ufrn.sam.controller;

import br.ufrn.sam.model.PessoaModel;
import br.ufrn.sam.repository.JpaPessoaRepository;
import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final JpaPessoaRepository pessoaRepository;

    public LoginController(JpaPessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> fazerLogin(@RequestBody Map<String, String> request, HttpSession session) {
        String email = request.get("login");
        String senha = request.get("senha");

        System.out.println("=== TENTATIVA DE LOGIN ===");
        System.out.println("Email extraído: [" + email + "]");
        System.out.println("Senha extraída: [" + senha + "]");

        Optional<PessoaModel> pessoa = pessoaRepository.findByLoginAndSenha(email, senha);

        if (pessoa.isPresent()) {
            session.setAttribute("usuarioLogado", pessoa.get());
            return ResponseEntity.ok().body("{\"mensagem\": \"Login autorizado\"}");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"mensagem\": \"Credenciais inválidas\"}");
        }
    }
}