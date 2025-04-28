package edu.uptc.swii.edamicrokafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uptc.swii.edamicrokafka.model.Login;
import edu.uptc.swii.edamicrokafka.repository.LoginRepository;

@Service
public class LoginService {
    @Autowired
    private final LoginRepository LoginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.LoginRepository = loginRepository;
    }

    public Login save(Login login){
        return LoginRepository.save(login);
    }
}
