package ru.pranch.authorizationapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pranch.authorizationapp.model.User;

public interface UserDAO extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
