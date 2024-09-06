package com.example.demo.controller;

import com.example.demo.dto.DemoResponse;
import com.example.demo.dto.DemoUser;
import com.example.demo.exception.DemoServiceException;
import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private final DemoService demoService;

    // Field Level
    // Setter
    // constructor
    @Autowired
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @RequestMapping(value="/greeting", method = RequestMethod.GET)
    public String greeting() {
        return "Hello World!";
    }


    @GetMapping("/add")
    public int add(@RequestParam("num1") int a, @RequestParam int b) {
        return a + b;
    }

    @GetMapping("/employee/{employeeId}/{name}")
    public String getEmployeeByIdAndName(@PathVariable("employeeId") long employeeId, @PathVariable String name) {
        return "Employee Id: " + employeeId + " Name: " + name;
    }

    @GetMapping("/employee")
    public String getEmployeeById(@RequestParam long employeeId) {
        return "Employee Id: " + employeeId;
    }

    @GetMapping("/validateUserById/{id}")
    public ResponseEntity<DemoResponse> validateUserById(@PathVariable int id) {
        if (id == 1) {
            DemoResponse successResponse = new DemoResponse("Welcome user, how may I assist?", false, Collections.emptyList());
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        } else {
            DemoResponse userIdNotFound = new DemoResponse(null, true, List.of("User id not found"));
            return new ResponseEntity<>(userIdNotFound, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users")
    public List<DemoUser> getUsers() {
        return demoService.getUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody DemoUser demoUser) {
         try {
             demoService.createUser(demoUser);
             return ResponseEntity.ok("User added successfully!");
         } catch(DemoServiceException e) {
             return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
         }
    }
@PutMapping("/users/{id}")
public ResponseEntity<String> updateUser(@PathVariable long id, @RequestBody DemoUser demoUser) {
    try {
        demoService.updateUserById(id, demoUser);
        return ResponseEntity.ok("User updated successfully!");
    } catch (DemoServiceException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }
}
@DeleteMapping("/users/{id}")
public ResponseEntity<String> deleteUser(@PathVariable long id) {
    try {
        demoService.deleteUserById(id);
        return ResponseEntity.ok("User removed successfully!");
    } catch (DemoServiceException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }
}
}

// @QueryParam, @RequestParam vs @PathVariable vs @QueryParam when to use what
// Understand every annotation being used
// GetMapping, get users from list
// @PostMapping, maintain a list and add to that list whenever the API is hit, already exists through 400
// @PutMapping, update any property of list
// @DeleteMapping, delete list