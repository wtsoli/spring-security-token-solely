package com.memorynotfound.spring.security.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bank")
public class BankController {

    @GetMapping("anonymous")
    @PreAuthorize("permitAll()")
    public String anonymously() {
        return "Hello, World! this is anonymous";
    }

    @GetMapping("/has-role/{id}")
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PreAuthorize("hasPermission(#id, 'read')")
    public String hasRole(@PathVariable String id) {
        return "Hi Bank, Hello, World! this needs admin";
    }

}
