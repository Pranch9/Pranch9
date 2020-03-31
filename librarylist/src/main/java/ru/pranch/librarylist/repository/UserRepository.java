package ru.pranch.librarylist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pranch.librarylist.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);



}
