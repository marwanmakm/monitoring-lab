package org.example.monitoringlab.controllers;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.example.monitoringlab.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  private final TestService testService;
  private final MeterRegistry meterRegistry;

  @Autowired
  public TestController(TestService testService, MeterRegistry meterRegistry) {
    this.testService = testService;
    this.meterRegistry = meterRegistry;
  }

  @GetMapping("/random")
  public ResponseEntity<?> randomNumber() {

    String randomNumberLastDigit = Integer.toString(testService.generateRandomNumber() % 10);
    Counter counter =
        Counter.builder("random_number_registry")
            .tag("value", randomNumberLastDigit)
            .register(meterRegistry);
    counter.increment();

    return new ResponseEntity<>(randomNumberLastDigit, HttpStatus.OK);
  }
}
