package com.API.ToDoAPP.Controllers.ApiResponses;

public class GetResponse<T> extends APIResponse  {
    public T Data;
    public GetResponse(int status , T data){
        super(status);
        Data = data;

    }
}
