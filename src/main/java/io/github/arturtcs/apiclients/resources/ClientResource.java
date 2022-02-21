package io.github.arturtcs.apiclients.resources;

import io.github.arturtcs.apiclients.model.dto.ClientDTO;
import io.github.arturtcs.apiclients.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    private final ClientService service;

    public ClientResource(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClientDTO> saveClient (@RequestBody ClientDTO clientDTO) {
        clientDTO = service.save(clientDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(clientDTO);
    }
}
