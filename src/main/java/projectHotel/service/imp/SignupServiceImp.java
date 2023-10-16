package projectHotel.service.imp;

import projectHotel.payload.request.SignUpRequest;

public interface SignupServiceImp {
    boolean insertUser (SignUpRequest signUpRequest);
}
