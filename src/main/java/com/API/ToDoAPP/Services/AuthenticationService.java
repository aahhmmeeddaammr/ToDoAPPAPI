package com.API.ToDoAPP.Services;

import com.API.ToDoAPP.Controllers.ApiResponses.AuthenticationResponse;
import com.API.ToDoAPP.Controllers.ApiResponses.GetResponse;
import com.API.ToDoAPP.Repositories.AdminRepository;
import com.API.ToDoAPP.Controllers.ApiResponses.APIResponse;
import com.API.ToDoAPP.Controllers.ControllersParams.LoginParams;
import com.API.ToDoAPP.Controllers.ControllersParams.RegisterParams;
import com.API.ToDoAPP.Models.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthenticationService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(AdminRepository adminRepository,PasswordEncoder passwordEncoder,
                                 JwtService jwtService, AuthenticationManager authenticationManager) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<APIResponse> login(LoginParams loginParams) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginParams.email,
                            loginParams.password
                    )
            );

            Optional<Admin> user = findUserByEmail(loginParams.email);

            if (user.isPresent()) {
                String token = jwtService.GenerateJwtToken(user.get());
                return ResponseEntity.ok(
                        new AuthenticationResponse(200, token)
                );
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new GetResponse<>(401, "Invalid email or password"));
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new GetResponse<>(401, "Invalid email or password"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new GetResponse<>(500, "An unexpected error occurred"));
        }
    }



    public ResponseEntity<APIResponse> Register(RegisterParams registerParams) {
        try {
            Optional<Admin> userFind = findUserByEmail(registerParams.email);
            if (userFind.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new GetResponse<>(409, "Email is already in use"));
            }

            User user = switch (registerParams.role) {
                case "User" -> new User();
                case "Admin" -> new Admin();
                default -> throw new IllegalArgumentException("Invalid Role");
            };

            user.setEmail(registerParams.email);
            user.setName(registerParams.name);
            user.setPassword(passwordEncoder.encode(registerParams.password));

            saveUser(user);

            String token = jwtService.GenerateJwtToken(user);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new AuthenticationResponse(201, token));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new GetResponse<>(400, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new GetResponse<>(500, "An unexpected error occurred"));
        }
    }



    private Optional<Admin> findUserByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
    private void saveUser(User user) {
            adminRepository.save((Admin) user);
    }
}
