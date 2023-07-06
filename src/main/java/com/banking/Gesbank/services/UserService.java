package com.banking.Gesbank.services;

import com.banking.Gesbank.dto.AuthenticationRequest;
import com.banking.Gesbank.dto.AuthenticationResponse;
import com.banking.Gesbank.dto.UserDto;

public interface UserService extends AbstractService<UserDto> {

    Integer validateAccount(Integer id);

    Integer invalidateAccount(Integer id);

    AuthenticationResponse register(UserDto userDto);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
