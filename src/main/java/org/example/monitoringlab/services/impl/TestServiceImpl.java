package org.example.monitoringlab.services.impl;

import java.util.OptionalInt;
import java.util.Random;
import java.util.random.RandomGenerator;
import lombok.extern.slf4j.Slf4j;
import org.example.monitoringlab.services.TestService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestServiceImpl implements TestService {

  /**
   * @return a random integer number each time that this method is called
   */
  @Override
  public String generateRandomNumber() {
    RandomGenerator randomGenerator = new Random();
    OptionalInt optRandomValue = randomGenerator.ints(1).findFirst();
    int randomValue = optRandomValue.orElse(-1);
    if (randomValue < 0) {
      randomValue = -randomValue;
    }

    int lastDigitValue = randomValue % 10;

    log.info("Random number: {}", randomValue);
    log.info("Last digit: {}", lastDigitValue);

    return Integer.toString(randomValue);
  }
}
