package com.vanessamatos.springBootMongoDB.resources;

import com.vanessamatos.springBootMongoDB.domain.User;
import com.vanessamatos.springBootMongoDB.dto.UserDTO;
import com.vanessamatos.springBootMongoDB.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = userService.findAll();
        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User userServiceById = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(userServiceById));
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> inserir(@RequestBody UserDTO userDTO) {
        User user = userService.fromDTO(userDTO);
        user = userService.inserir(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
