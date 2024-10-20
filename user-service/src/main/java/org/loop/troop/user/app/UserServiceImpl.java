package org.loop.troop.user.app;


import jakarta.transaction.Transactional;
import org.loop.troop.user.dto.RegisterDto;
import org.loop.troop.user.dto.UserDto;
import org.loop.troop.user.exception.ServiceException;
import org.loop.troop.user.keycloak.KeyCloakService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final KeyCloakService keyCloakService;

    private static final String USER_DETAIL = "user details -> {}";

    @Override
    public UUID getUUIDFromUniqueName(String uniqueName){
        return getUserByUsername(uniqueName).getUserId();
    }

    @Override
    public UserDto saveUser(RegisterDto registerDto) {
        if (isUserExist(registerDto.getUsername(), registerDto.getEmail())) {
            log.error("Cannot save user as email or username is already present in database {}", registerDto.getEmail());
            throw new ServiceException("User is already present choose different email or username!", HttpStatus.FOUND, "User Already exists");
        }
        var userId = keyCloakService.createUser(registerDto);
        var defaultRole = keyCloakService.getKeycloakDefaultRoles();

        User user = mapper.map(registerDto, User.class);
        user.setUserId(userId);
        user.setRoles(Collections.singletonList(defaultRole.getName()));

        if (log.isDebugEnabled()) log.debug(USER_DETAIL, user);
        final var savedUser = userRepository.save(user);
        return mapper.map(savedUser, UserDto.class);
    }

    @Override
    @Cacheable(value = "user", key = "#username")
    public UserDto getUserByUsername(String username) {
        final var savedUser = userRepository.findByUsername(username).orElseThrow(
                () -> new ServiceException("No User Found with username: %s".formatted(username), HttpStatus.NOT_FOUND, "No User Found")
        );
        if (log.isDebugEnabled()) log.debug(USER_DETAIL, savedUser);
        return mapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getUserByUUID(UUID uuid) {
        final var savedUser = userRepository.findByUserId(uuid).orElseThrow(
                () -> new ServiceException("No User Found with user with userId: %s".formatted(uuid), HttpStatus.NOT_FOUND, "No User Found")
        );
        if (log.isDebugEnabled()) log.debug(USER_DETAIL, savedUser);
        return mapper.map(savedUser, UserDto.class);
    }

    @Override
    public boolean isUserExist(String username, String email) {
        return Boolean.TRUE.equals(userRepository.existsByEmail(email)) ||
                Boolean.TRUE.equals(userRepository.existsByUsername(username));
    }
}
