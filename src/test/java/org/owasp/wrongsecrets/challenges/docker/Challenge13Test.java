package org.owasp.wrongsecrets.challenges.docker;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.owasp.wrongsecrets.challenges.Spoiler;

@ExtendWith(MockitoExtension.class)
class Challenge13Test {

  @Test
  void spoilerShouldRevealAnswer() {
    var challenge =
        new Challenge13(
            "This is not the secret", "pHr78j1sLaDCJTsJsKSOUB1bhqZKEQG+qFTY+OLQ+OFzsC7YGvU=");

    assertThat(challenge.spoiler())
        .isEqualTo(
            new Spoiler(
                Base64.getEncoder()
                    .encodeToString(
                        "ghp_aK7xW92mQpL3zR8vN5tHy2Jc6BdE4sW"
                            .getBytes(StandardCharsets.UTF_8))));
  }

  @Test
  void rightAnswerShouldSolveChallenge() {
    var challenge =
        new Challenge13(
            "This is not the secret", "pHr78j1sLaDCJTsJsKSOUB1bhqZKEQG+qFTY+OLQ+OFzsC7YGvU=");

    assertThat(
            challenge.answerCorrect(
                Base64.getEncoder()
                    .encodeToString(
                        "ghp_aK7xW92mQpL3zR8vN5tHy2Jc6BdE4sW".getBytes(StandardCharsets.UTF_8))))
        .isTrue();
  }

  @Test
  void incorrectAnswerShouldNotSolveChallenge() {
    var challenge =
        new Challenge13(
            "This is not the secret", "pHr78j1sLaDCJTsJsKSOUB1bhqZKEQG+qFTY+OLQ+OFzsC7YGvU=");

    assertThat(challenge.answerCorrect("wrong answer")).isFalse();
  }
}
