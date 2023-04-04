package nosql.controller.impl;

import nosql.domain.Sport;
import nosql.domain.User;
import nosql.exception.UserCreatingException;
import nosql.exception.UserNotFoundException;
import nosql.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController
 * Date: 02/18/2023
 *
 * @author Yauheni Antsipenka
 */
@RestController()
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public @ResponseBody User findUserById(@PathVariable String userId) {
        return userService.findById(userId);
    }

    @GetMapping("/email/{email}")
    public @ResponseBody List<User> findUsersByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestBody User newUser) {
        try {
            return new ResponseEntity<>("User " + userService.addUser(newUser) + " was created", HttpStatus.CREATED);
        } catch (UserCreatingException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/{userId}/sport")
    public ResponseEntity<String> addSportToUser(@PathVariable String userId, @RequestBody Sport sport) {
        try {
            userService.addSportToUser(userId, sport);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(String.format("Sport was added to user %s", userId), HttpStatus.CREATED);
    }

    @GetMapping(path = "/sport/{sportName}")
    public List<User> findBySportName(@PathVariable String sportName) {
        return userService.findBySportName(sportName);
    }
}
