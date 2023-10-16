package projectHotel.controller;


import com.google.gson.Gson;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import projectHotel.payload.request.LoginRequest;
import projectHotel.payload.request.SignUpRequest;
import projectHotel.response.BaseResponse;
import projectHotel.service.LoginAuthenService;
import projectHotel.service.imp.SignupServiceImp;
import projectHotel.util.JwtHelper;

import javax.crypto.SecretKey;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    LoginAuthenService loginAuthenService;

    @Autowired
    SignupServiceImp signupServiceImp;



    private Gson gson = new Gson();


    @PostMapping("/signin")
    public ResponseEntity<?> siginin(@Valid  @RequestBody LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        boolean isAuthenticated = loginAuthenService.authenticate(loginRequest);

        if (isAuthenticated) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            List<SimpleGrantedAuthority> roles = (List<SimpleGrantedAuthority>) authentication.getAuthorities();

            String jsonRoles = gson.toJson(roles.get(0));
            String token = jwtHelper.generateToken(jsonRoles);

            BaseResponse baseResponse = new BaseResponse();

            System.out.println(jsonRoles);

            if (jsonRoles.contains("ROLE_ADMIN")) {
                baseResponse.setStatusCode(200);
                baseResponse.setMessage(String.valueOf(roles.get(0)));
                baseResponse.setData(token);
            } else if (jsonRoles.contains("ROLE_USER")) {
                baseResponse.setStatusCode(200);
                baseResponse.setMessage(String.valueOf(roles.get(0)));
                baseResponse.setData(token);
            } else {
                baseResponse.setStatusCode(403);
                baseResponse.setMessage("Access Denied");
            }
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);


        } else {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setStatusCode(401);
            baseResponse.setMessage("Invalid username or password");
            return new ResponseEntity<>(baseResponse, HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest){

        boolean isSuccess = signupServiceImp.insertUser(signUpRequest);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("");
        baseResponse.setData(isSuccess);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
