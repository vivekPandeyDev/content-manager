package org.loop.troop.user.app;

import org.loop.troop.user.dto.RegisterDto;
import org.loop.troop.user.dto.UserDto;

import java.util.UUID;

public interface UserService {
    UUID getUUIDFromUniqueName(String uniqueName);
    UserDto saveUser(RegisterDto registerDto);
    UserDto getUserByUsername(String username);

    UserDto getUserByUUID(UUID uuid);

    boolean isUserExist(String uniqueName,String email);
}
