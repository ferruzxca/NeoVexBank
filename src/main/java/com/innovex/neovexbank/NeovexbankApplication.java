/*
package com.innovex.neovexbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NeovexbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeovexbankApplication.class, args);
	}

}
*/

package com.innovex.neovexbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class NeovexbankApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeovexbankApplication.class, args);

        // 👉 Cifrado de la contraseña
        String password = "1234";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptedPassword = encoder.encode(password);

        System.out.println("🔐 Contraseña original: " + password);
        System.out.println("🔒 Contraseña cifrada (BCrypt): " + encryptedPassword);
    }

}