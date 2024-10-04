package org.example.monitoringlab.controllers;

import org.example.monitoringlab.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  private final TestService testService;

  @Autowired
  public TestController(TestService testService) {
    this.testService = testService;
  }

  @GetMapping("/random")
  public ResponseEntity<?> randomNumber() {
    return new ResponseEntity<>(testService.generateRandomNumber(), HttpStatus.OK);
  }
}
