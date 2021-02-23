package com.mazing;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandController {

    @RequestMapping(value="/execute",method = RequestMethod.GET)
    public Response executeCommand(@RequestParam String command){
        return new Response(ResponseType.SUCCESS,command+" executed");
    }
}
