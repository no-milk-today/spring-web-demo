package com.yandex.blog.repository;

import com.yandex.blog.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    void save(User user);

    void deleteById(Long id);
}
