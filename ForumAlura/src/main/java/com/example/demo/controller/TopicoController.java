package com.example.demo.controller;

import com.example.demo.dto.TopicoDTO;
import com.example.demo.dto.TopicoForm;
import com.example.demo.model.Topico;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.TopicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;

    public TopicoController(TopicoRepository topicoRepository, CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public List<TopicoDTO> listar() {
        return topicoRepository.findAll().stream().map(TopicoDTO::new).toList();
    }

    @PostMapping
    public TopicoDTO criar(@RequestBody TopicoForm form, @AuthenticationPrincipal com.example.demo.model.Usuario autor) {
        var curso = cursoRepository.findByNome(form.getNomeCurso())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Curso não encontrado"
                ));

        Topico topico = new Topico();
        topico.setTitulo(form.getTitulo());
        topico.setMensagem(form.getMensagem());
        topico.setCurso(curso);
        topico.setAutor(autor);

        topicoRepository.save(topico);
        return new TopicoDTO(topico);
    }
}