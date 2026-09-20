package org.owasp.wrongsecrets.challenges.docker;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Challenge57Test {

  @Test
  void answerCorrect() {
    var challenge = new Challenge57();
    assertThat(challenge.answerCorrect(challenge.spoiler().solution())).isTrue();
  }

  @Test
  void answerIncorrect() {
    var challenge = new Challenge57();
    assertThat(challenge.answerCorrect("wrong answer")).isFalse();
  }

  @Test
  void getAnswerShouldReturnDecodedSecret() {
    var challenge = new Challenge57();
    assertThat(challenge.getAnswer()).isEqualTo("sk-ws7Kf2mQ9pL4xR8vT3nZ6bH1cJ5yU0dA");
  }
}
