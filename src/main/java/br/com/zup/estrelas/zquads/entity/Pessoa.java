package br.com.zup.estrelas.zquads.entity;

import java.util.List;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa")
public class Pessoa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "id_pessoa")
    private Long idPessoa;
    
    private String nome;
    
    private String email;
    // Endereço
    private String bio;
    
    private String apelido;
    
    private String gitHub;

    private String insta;

    private Optional<String> raca;
    
    private Optional<String> orientacaoSexual;
    
    private List<String> funcoes;

    private List<Squad> squads;
    
    private List<Skill> skills;
    
    private List<Pessoa> amigos;

    private List<Task> tasks;
    
}
