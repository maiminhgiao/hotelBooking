package projectHotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projectHotel.entity.RoleEntity;
import projectHotel.entity.UserEntity;
import projectHotel.payload.request.SignUpRequest;
import projectHotel.repository.LoginRepository;
import projectHotel.service.imp.SignupServiceImp;

@Service
public class SignupService implements SignupServiceImp {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean insertUser(SignUpRequest signUpRequest) {
        boolean isSuccess = false;
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(signUpRequest.getEmail());
        userEntity.setUsername(signUpRequest.getPhone());

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(signUpRequest.getId_role());
        userEntity.setRoleEntity(roleEntity);

        userEntity.setPhone(signUpRequest.getPhone());

        userEntity.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userEntity.setUsername(signUpRequest.getUsername());

        try {
            loginRepository.save(userEntity);
            isSuccess = true;

        } catch (Exception e) {
            System.out.println("add failed " + e.getLocalizedMessage());
            isSuccess = false;
        }

        return isSuccess;
    }
}
