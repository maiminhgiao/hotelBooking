package projectHotel.service.imp;

import org.springframework.stereotype.Service;
import projectHotel.payload.request.LoginRequest;

public interface LoginAuthenServiceImp {

    boolean authenticate(LoginRequest loginRequest);
}
