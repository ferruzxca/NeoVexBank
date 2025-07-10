package com.innovex.neovexbank.controller;

import com.innovex.neovexbank.model.User;
import com.innovex.neovexbank.repository.UserRepository;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/public/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        List<User> usuarios = userRepository.findAll();
        Map<String, Object> response = new HashMap<>();

        if (usuarios.isEmpty()) {
            response.put("mensaje", "No hay usuarios registrados.");
            response.put("estado", false);
            response.put("usuarios", Collections.emptyList());
        } else {
            response.put("mensaje", "Usuarios obtenidos correctamente.");
            response.put("estado", true);
            response.put("usuarios", usuarios);
        }

        return ResponseEntity.ok(response);
    }
}
