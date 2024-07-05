package nl.bitsentools.eindprojectbackendmetabo.controllers;


import nl.bitsentools.eindprojectbackendmetabo.dto.user.UserInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.user.UserOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.BadRequestException;
import nl.bitsentools.eindprojectbackendmetabo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<UserOutputDto>> getUsers() {

        List<UserOutputDto> userOutputDTOS = userService.getAllUsers();
        return ResponseEntity.ok().body(userOutputDTOS);
    }


    @GetMapping(value = "/{username}")
    public ResponseEntity<UserOutputDto> getOneUser(@AuthenticationPrincipal UserDetails userdetails, @PathVariable("username") String username) {
       if (userdetails.getUsername().equals(username)){
           UserOutputDto optionalUser = userService.getOneUser(username);
           return ResponseEntity.ok().body(optionalUser);
       }
        else {
            throw new BadRequestException();
       }
           }

    @GetMapping("/{username}/exists")
    public ResponseEntity<Boolean> checkIfUserExists(@PathVariable String username) {
        boolean userExists = userService.userExists(username);
        return ResponseEntity.ok(userExists);
    }

    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getAuthority(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.getAuthorities(username));
    }

    @PostMapping()
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserInputDto userDto) {

            String newUsername = userService.createUser(userDto);
            userService.addAuthority(newUsername, "ROLE_CLIENT");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(newUsername)
                .toUri();

        return ResponseEntity.created(location).build();
        }

    @PostMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @Valid @RequestBody Map<String, Object> fields) {
        try {
            String autorityName = (String) fields.get("authorities");
            userService.addAuthority(username, autorityName);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }
    @PutMapping(value = "/{username}")
    public ResponseEntity<String> updateUser( @PathVariable("username") String username,@Valid @RequestBody UserInputDto dto) {
            userService.updateUser(username, dto);

            return ResponseEntity.ok().body("user updated");

    }



    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteAuthority(@PathVariable("username") String username, @PathVariable("authority") String autority) {
        userService.removeAuthority(username, autority);
        return ResponseEntity.noContent().build();
    }
}
