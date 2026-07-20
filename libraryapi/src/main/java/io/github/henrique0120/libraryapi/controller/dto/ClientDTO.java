package io.github.henrique0120.libraryapi.controller.dto;

public record ClientDTO(

        String clientId,
        String clientSecret,
        String redirectURI,
        String scope

) { }
