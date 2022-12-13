package com.nitetrain.service.mapper;

import com.nitetrain.domain.TrainingUser;
import com.nitetrain.domain.User;
import com.nitetrain.service.dto.TrainingUserDTO;
import com.nitetrain.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TrainingUser} and its DTO {@link TrainingUserDTO}.
 */
@Mapper(componentModel = "spring")
public interface TrainingUserMapper extends EntityMapper<TrainingUserDTO, TrainingUser> {
    @Mapping(target = "internalUser", source = "internalUser", qualifiedByName = "userId")
    TrainingUserDTO toDto(TrainingUser s);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);
}
