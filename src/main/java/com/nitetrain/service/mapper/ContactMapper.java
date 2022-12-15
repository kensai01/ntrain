package com.nitetrain.service.mapper;

import com.nitetrain.domain.Contact;
import com.nitetrain.service.dto.ContactDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Contact} and its DTO {@link ContactDTO}.
 */
@Mapper(componentModel = "spring")
public interface ContactMapper extends EntityMapper<ContactDTO, Contact> {}
