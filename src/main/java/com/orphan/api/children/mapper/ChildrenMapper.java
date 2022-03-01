package com.orphan.api.children.mapper;

import com.orphan.api.children.dto.ChildrenDto;
import com.orphan.common.entity.children.Children;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChildrenMapper {
    ChildrenMapper INSTANCE = Mappers.getMapper(ChildrenMapper.class);

    ChildrenDto childrenToChildrenDto(Children children);

    Children childrenDtoToChildren(ChildrenDto childrenDto);
}
