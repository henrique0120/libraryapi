package io.github.henrique0120.libraryapi.repository;

import io.github.henrique0120.libraryapi.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RolesRepository extends JpaRepository<Roles, UUID> {
}
