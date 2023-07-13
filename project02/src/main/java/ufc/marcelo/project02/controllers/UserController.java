package ufc.marcelo.project02.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufc.marcelo.project02.dto.requests.user.CreateUser;
import ufc.marcelo.project02.dto.requests.user.LoginUser;
import ufc.marcelo.project02.dto.responses.GenericResult;
import ufc.marcelo.project02.dto.responses.user.SimpleUser;
import ufc.marcelo.project02.services.UserServices;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/create")
    public ResponseEntity<GenericResult> create(@RequestBody CreateUser createUser) {
        try {
            boolean userExists = userServices.emailExists(createUser.email());

            if (userExists) {
                GenericResult result = new GenericResult(false, "User already exists", null);
                return ResponseEntity.badRequest().body(result);
            }

            userServices.create(createUser.name(), createUser.urlImage(), createUser.email(), createUser.password());

            GenericResult result = new GenericResult(true, "User created", null);

            return ResponseEntity.ok().body(result);
        }catch (Exception e) {
            GenericResult result = new GenericResult(false, "Error", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<GenericResult> login(@RequestBody LoginUser login) {
        try {
            boolean userExists = userServices.emailAndPasswordExists(login.email(), login.password());

            if (!userExists) {
                GenericResult result = new GenericResult(false, "User not found", null);
                return ResponseEntity.badRequest().body(result);
            }

            GenericResult result = new GenericResult(true, "User logged", null);
            return ResponseEntity.ok().body(result);
        }catch (Exception e) {
            GenericResult result = new GenericResult(false, "Error", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ArrayList<SimpleUser>> getAll() {
        try {
            return ResponseEntity.ok().body(userServices.getAll());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }
}