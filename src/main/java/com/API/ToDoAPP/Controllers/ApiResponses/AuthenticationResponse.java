package com.API.ToDoAPP.Controllers.ApiResponses;

public class AuthenticationResponse extends APIResponse{

    public String token;
    public AuthenticationResponse(int status,String token) {
        super(status);
        this.token = token;
    }

}
