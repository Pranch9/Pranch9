package ru.pranch.authorizationapp.service;

import ru.pranch.authorizationapp.model.User;

/**
 * Service class for{@link ru.pranch.authorizationapp.model.User}
 *
 * @author Anatoly
 * @version 1.0
 */
public interface UserService {

    void save(User user);

    User findByUsername(String username);
}

