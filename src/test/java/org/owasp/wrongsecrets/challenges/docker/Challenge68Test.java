package org.owasp.wrongsecrets.challenges.docker;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.owasp.wrongsecrets.challenges.Spoiler;

class Challenge68Test {

  @Test
  void spoilerShouldRevealAnswer() {
    var challenge = new Challenge68();

    assertThat(challenge.spoiler()).isEqualTo(new Spoiler("Ch4tL1nk#S3cr3t!2026$xK9mP"));
  }

  @Test
  void rightAnswerShouldSolveChallenge() {
    var challenge = new Challenge68();

    assertThat(challenge.answerCorrect("Ch4tL1nk#S3cr3t!2026$xK9mP")).isTrue();
  }

  @Test
  void incorrectAnswerShouldNotSolveChallenge() {
    var challenge = new Challenge68();

    assertThat(challenge.answerCorrect("wrong answer")).isFalse();
  }
}
