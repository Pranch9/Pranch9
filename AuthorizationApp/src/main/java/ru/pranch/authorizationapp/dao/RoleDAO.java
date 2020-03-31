package ru.pranch.authorizationapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pranch.authorizationapp.model.Role;

public interface RoleDAO extends JpaRepository<Role, Long> {
}