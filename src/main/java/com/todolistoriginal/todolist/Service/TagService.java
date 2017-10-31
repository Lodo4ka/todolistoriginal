package com.todolistoriginal.todolist.Service;

import com.todolistoriginal.todolist.entity.Tag;

import java.util.Collection;
import java.util.List;

public interface TagService {

    List<Tag> findOrCreate(Collection<Tag> tags);

}
