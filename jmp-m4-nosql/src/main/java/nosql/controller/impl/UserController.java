package nosql.controller.impl;

import nosql.domain.Sport;
import nosql.domain.User;
import nosql.service.api.IUserService;
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

    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
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

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestBody User newUser) {
        String userId = userService.addUser(newUser);
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>("User with " + userId + " id was created", HttpStatus.CREATED);
    }

    @PutMapping(path = "/{userId}/sport")
    public ResponseEntity<String> addSportToUser(@PathVariable String userId, @RequestBody Sport sport) {
        return new ResponseEntity<>(userService.addSportToUser(userId, sport), HttpStatus.CREATED);
    }

    @GetMapping(path = "/sport/{sportName}")
    public List<User> addSportToUser(@PathVariable String sportName) {
        return userService.findBySportName(sportName);
    }
}
