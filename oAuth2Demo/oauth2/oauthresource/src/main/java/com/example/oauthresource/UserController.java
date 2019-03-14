package com.example.oauthresource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by on 2017.03.08.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    public String readFoo() {
        return "read user " + UUID.randomUUID().toString();
    }

    @PreAuthorize("hasAuthority('FOO_WRITE')")
    @RequestMapping(method = RequestMethod.POST)
    public String writeFoo() {
        return "write user " + UUID.randomUUID().toString();
    }
}
