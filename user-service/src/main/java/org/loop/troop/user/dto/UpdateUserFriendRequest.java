package org.loop.troop.user.dto;

import org.loop.troop.user.validator.ValidEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateUserFriendRequest {
    @ValidEnum(enumClass = Operation.class,message = "invalid operation type")
    private String operation;

    @NotBlank(message = "friend's user id cannot be blank")
    private UUID friendUserId;

    @NotBlank(message = "user's  id cannot be blank")
    private UUID userId;

}
