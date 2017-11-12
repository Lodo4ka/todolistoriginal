package com.todolistoriginal.todolist.service.impl;

import com.todolistoriginal.todolist.service.TagService;
import com.todolistoriginal.todolist.entity.Tag;
import com.todolistoriginal.todolist.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService, UserDetailsService{

    @Autowired
    private TagRepository tagRepository;


    @Override
    @Transactional
    public List<Tag> findOrCreate(Collection<Tag> tags) {
        return tags.stream().map(tag -> {
            // i should to find how to convert to lambda
            Tag foundTag = tagRepository.findByName(tag.getName());
            return foundTag != null ? foundTag : tagRepository.save(tag);
        }).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {

        return null;
    }
}
