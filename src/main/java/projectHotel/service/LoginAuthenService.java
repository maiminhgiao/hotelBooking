package projectHotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projectHotel.entity.UserEntity;
import projectHotel.payload.request.LoginRequest;
import projectHotel.repository.LoginRepository;
import projectHotel.service.imp.LoginAuthenServiceImp;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginAuthenService implements LoginAuthenServiceImp {

    @Autowired

    private LoginRepository loginRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public boolean authenticate(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        UserEntity userEntity = loginRepository.findByEmail(email);

        if(userEntity != null){
            boolean passwordMatches = passwordEncoder.matches(password,userEntity.getPassword());

            if (passwordMatches){
                return true;
            }else {
                System.out.println("password is not correct");
                return false;
            }
        }else {
            System.out.println("no user entity found");
            return false;
        }

    }
}
