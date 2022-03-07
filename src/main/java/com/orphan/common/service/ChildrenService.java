package com.orphan.common.service;

import com.orphan.api.children.dto.ChildrenDto;
import com.orphan.api.children.mapper.ChildrenMapper;
import com.orphan.common.entity.children.Children;
import com.orphan.common.repository.ChildrenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChildrenService {
    @Autowired
    private ChildrenRepository childrenRepository;

    public List<ChildrenDto> listAllChildren() {
        List<Children> childrenList = childrenRepository.findAll();
        List<ChildrenDto> childrenDtoList = new ArrayList<>();

        childrenList.stream().forEach(children -> childrenDtoList.add(ChildrenMapper.INSTANCE.childrenToChildrenDto(children)));
        return childrenDtoList;
    }

    public ChildrenDto findById(Integer id) {
        Children children = childrenRepository.findById(id).get();
        return ChildrenMapper.INSTANCE.childrenToChildrenDto(children);
    }
     public ChildrenDto save(ChildrenDto childrenDto) {
        Children children = ChildrenMapper.INSTANCE.childrenDtoToChildren(childrenDto);
        Children saveChildren = childrenRepository.save(children);
        return ChildrenMapper.INSTANCE.childrenToChildrenDto(saveChildren);
    }

    public ChildrenDto update(ChildrenDto childrenDto, Integer id) {
        Children existsChildren = childrenRepository.findById(id).get();
        Children children = ChildrenMapper.INSTANCE.childrenDtoToChildren(childrenDto);
        if (existsChildren != null) {
            if (childrenDto.getImage() == null && childrenDto.getImage().isEmpty()) {
                children.setImage(existsChildren.getImage());
            }
        }
        Children saveChildren = childrenRepository.save(children);
        return ChildrenMapper.INSTANCE.childrenToChildrenDto(saveChildren);
    }
}
