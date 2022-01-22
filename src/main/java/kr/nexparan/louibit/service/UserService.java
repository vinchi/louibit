package kr.nexparan.louibit.service;

import kr.nexparan.louibit.model.Role;
import kr.nexparan.louibit.model.User;
import kr.nexparan.louibit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User save(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        Role role = new Role();
        role.setId(1L);
        user.getRoles().add(role);
        String username = user.getEmail().split("@")[0];
        user.setUsername(username);
        user.setPoint(BigDecimal.ZERO);
        return userRepository.save(user);
    }
}
