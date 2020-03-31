package ru.pranch.authorizationapp.service;

/**
 * Service for Security.
 *
 * @author Anatoly
 * @version 1.0
 */
public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
