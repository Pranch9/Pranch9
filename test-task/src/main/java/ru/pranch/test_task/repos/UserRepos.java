package ru.pranch.test_task.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pranch.test_task.model.User;

public interface UserRepos extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
