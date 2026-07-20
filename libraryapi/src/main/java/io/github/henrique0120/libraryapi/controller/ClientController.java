package io.github.henrique0120.libraryapi.controller;

import io.github.henrique0120.libraryapi.controller.dto.ClientDTO;
import io.github.henrique0120.libraryapi.controller.mappers.ClientMapper;
import io.github.henrique0120.libraryapi.model.Client;
import io.github.henrique0120.libraryapi.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;
    private final ClientMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    @PreAuthorize("hasRole('GERENTE')")
    public void salvar (@RequestBody ClientDTO clientDto){
        var sla = mapper.toDTO(clientDto);
        service.salvar(sla);
    }

}
