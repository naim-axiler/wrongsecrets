package org.owasp.wrongsecrets.challenges.docker;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Challenge54Test {

  @Test
  void rightAnswerShouldSolveChallenge() throws Exception {
    var challenge = new Challenge54();

    String clearSecret = "G1t1gn0r3#S3cr3t!2026$xK9mP";

    assertThat(challenge.answerCorrect(clearSecret)).isTrue();
  }

  @Test
  void incorrectAnswerShouldNotSolveChallenge() throws Exception {
    var challenge = new Challenge54();

    String wrongSecret = "wrong answer";

    assertThat(challenge.answerCorrect(wrongSecret)).isFalse();
  }
}
