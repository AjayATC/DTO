package com.example.userapp;

import com.example.userapp.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {

    private final Map<Long, User> users =
            new ConcurrentHashMap<>();

    private final AtomicLong idGenerator =
            new AtomicLong(0);

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public User save(User user) {
        Long id = idGenerator.incrementAndGet();

        user.setId(id);
        users.put(id, user);

        return user;
    }

    public Optional<User> update(Long id, User updatedUser) {
        if (!users.containsKey(id)) {
            return Optional.empty();
        }

        updatedUser.setId(id);
        users.put(id, updatedUser);

        return Optional.of(updatedUser);
    }

    public boolean deleteById(Long id) {
        return users.remove(id) != null;
    }

    public List<User> findByName(String name) {
        return users.values()
                .stream()
                .filter(user -> user.getName() != null)
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .toList();
    }
}