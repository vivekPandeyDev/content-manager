package org.loop.troop.user.keycloak;

import org.loop.troop.user.dto.RegisterDto;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.UUID;

public interface KeyCloakService {
    UUID createUser(RegisterDto registerDto);

    UserRepresentation getKeycloakUser(String username);

    RoleRepresentation getKeycloakRoles(String roleName);

    void addRoles(UUID userId, String roleName);


    RoleRepresentation getKeycloakDefaultRoles();
}
