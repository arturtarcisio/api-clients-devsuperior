package io.github.arturtcs.apiclients.services;

import io.github.arturtcs.apiclients.model.Client;
import io.github.arturtcs.apiclients.model.dto.ClientDTO;
import io.github.arturtcs.apiclients.repositories.ClientRepository;
import io.github.arturtcs.apiclients.services.exceptions.AttributeNullOrEntityInvalidException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ClientDTO save(ClientDTO clientDTO) {

        if (clientDTO.getName().equals("") ||
            clientDTO.getName() == null ||
            clientDTO.getCpf().equals("") ||
            clientDTO.getCpf() == null ||
            clientDTO.getChildren() == null ||
            clientDTO.getIncome() == null ||
            clientDTO.getBirthDate() == null)  {
            throw new AttributeNullOrEntityInvalidException("Every attribute of object must be filled.");
        }

        Client client = new Client();
        client.setChildren(clientDTO.getChildren());
        client.setCpf(clientDTO.getCpf());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setIncome(clientDTO.getIncome());
        client.setName(clientDTO.getName());

        client = repository.save(client);

        return new ClientDTO(client);
    }



}
