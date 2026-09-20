package org.owasp.wrongsecrets.challenges.docker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.owasp.wrongsecrets.Challenges.ErrorResponses.DECRYPTION_ERROR;

import org.junit.jupiter.api.Test;

class Challenge25Test {

  @Test
  void rightAnswerShouldSolveChallenge() {
    var challenge =
        new Challenge25(
            "i2j7kLWuBtlP44hXmaOfX7WpjCqMfpu4dFyzPPe6a2P3845FGwx59xwoNlqZAlilcyEtk0UQfRuRuzRq");
    assertThat(challenge.spoiler().solution()).isNotEqualTo(DECRYPTION_ERROR);
    assertThat(challenge.answerCorrect(challenge.spoiler().solution())).isTrue();
  }
}
