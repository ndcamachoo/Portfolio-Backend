package com.nelcamacho.oauthservice.client;

import com.nelcamacho.oauthservice.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", path = "/users")
public interface UserClient {

    /* =============== Métodos =================== */

    @GetMapping("/search/username")
    User findByUsername(@RequestParam String username);

    @PutMapping("/{id}")
    User update(@RequestParam Long id, @RequestBody User user);

}
