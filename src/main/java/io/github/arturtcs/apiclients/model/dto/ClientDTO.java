package io.github.arturtcs.apiclients.model.dto;

import io.github.arturtcs.apiclients.model.Client;

import java.io.Serializable;
import java.time.LocalDate;

public class ClientDTO implements Serializable {

    private Long id;
    private String name;
    private String cpf;
    private Double income;
    private LocalDate birthDate;
    private Integer children;

    public ClientDTO(Client entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.cpf = entity.getCpf();
        this.income = entity.getIncome();
        this.birthDate = entity.getBirthDate();
        this.children = entity.getChildren();
    }

}
