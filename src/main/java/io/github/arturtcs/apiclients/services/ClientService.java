package io.github.arturtcs.apiclients.services;

import io.github.arturtcs.apiclients.model.Client;
import io.github.arturtcs.apiclients.model.dto.ClientDTO;
import io.github.arturtcs.apiclients.repositories.ClientRepository;
import io.github.arturtcs.apiclients.services.exceptions.AttributeNullOrEntityInvalidException;
import io.github.arturtcs.apiclients.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ClientDTO save(ClientDTO clientDTO) {

        validateObject(clientDTO);

        Client client = new Client();
        client.setChildren(clientDTO.getChildren());
        client.setCpf(clientDTO.getCpf());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setIncome(clientDTO.getIncome());
        client.setName(clientDTO.getName());

        client = repository.save(client);

        return new ClientDTO(client);
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("The resource you're looking for doesn't exist!");
        }
    }

    @Transactional
    public ClientDTO update (Long id, ClientDTO clientDTO) {

        validateObject(clientDTO);

        var optionalClient = repository.findById(id);
        var client = optionalClient.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        client.setName(clientDTO.getName());
        client.setIncome(clientDTO.getIncome());
        client.setCpf(clientDTO.getCpf());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setChildren(clientDTO.getChildren());

        var updatedClient = repository.save(client);
        return new ClientDTO(updatedClient);
    }

    private void validateObject(ClientDTO entity){
        if (entity.getName().equals("") ||
                entity.getName() == null ||
                entity.getCpf().equals("") ||
                entity.getCpf() == null ||
                entity.getChildren() == null ||
                entity.getIncome() == null ||
                entity.getBirthDate() == null)  {
            throw new AttributeNullOrEntityInvalidException("Every attribute of object must be filled.");
        }
    }


}
