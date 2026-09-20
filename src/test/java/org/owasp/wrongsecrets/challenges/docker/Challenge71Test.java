package org.owasp.wrongsecrets.challenges.docker;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.owasp.wrongsecrets.challenges.Spoiler;

class Challenge71Test {

  private static final String DEFAULT_SECRET = "dvc#T0k3n!2026$xK9mP";

  @Test
  void spoilerShouldRevealAnswer() {
    var challenge = new Challenge71(DEFAULT_SECRET);

    assertThat(challenge.spoiler()).isEqualTo(new Spoiler(DEFAULT_SECRET));
  }

  @Test
  void rightAnswerShouldSolveChallenge() {
    var challenge = new Challenge71(DEFAULT_SECRET);

    assertThat(challenge.answerCorrect(DEFAULT_SECRET)).isTrue();
  }

  @Test
  void incorrectAnswerShouldNotSolveChallenge() {
    var challenge = new Challenge71(DEFAULT_SECRET);

    assertThat(challenge.answerCorrect("wrong-secret-token")).isFalse();
  }
}
