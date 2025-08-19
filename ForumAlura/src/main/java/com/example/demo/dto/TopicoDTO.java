package com.example.demo.dto;

import com.example.demo.model.Topico;
import java.time.LocalDateTime;

public class TopicoDTO {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String autor;
    private String curso;

    public TopicoDTO(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.autor = topico.getAutor().getNome();
        this.curso = topico.getCurso().getNome();
    }

    // getters
}
