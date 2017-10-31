package com.todolistoriginal.todolist.Service.impl;

import com.todolistoriginal.todolist.Service.TagService;
import com.todolistoriginal.todolist.entity.Tag;
import com.todolistoriginal.todolist.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TagServiceImpl implements TagService{

    @Autowired
    private TagRepository tagRepository;


    @Override
    @Transactional
    public List<Tag> findOrCreate(Collection<Tag> tags) {
        return tags.stream().map(tag -> {


            // i should to find how to convert to lambda
            List<Tag> foundTags = tagRepository.findByName(tag.getName());

            if(foundTags.size() == 1){
                return foundTags.get(0);
            }
            else {
                Tag newTag = new Tag(tag.getName());
                tagRepository.save(newTag);
                return newTag;
            }
        }).collect(Collectors.toList());
    }
}
