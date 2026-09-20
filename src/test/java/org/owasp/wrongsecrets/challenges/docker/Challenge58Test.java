package org.owasp.wrongsecrets.challenges.docker;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.owasp.wrongsecrets.challenges.Spoiler;

class Challenge58Test {

  @Test
  void spoilerShouldReturnCorrectAnswer() {
    var challenge = new Challenge58();
    assertThat(challenge.spoiler()).isEqualTo(new Spoiler("Rk9#mP2$vL8xQ7wZ"));
  }

  @Test
  void answerCorrectShouldReturnTrueForCorrectAnswer() {
    var challenge = new Challenge58();
    assertThat(challenge.answerCorrect("Rk9#mP2$vL8xQ7wZ")).isTrue();
  }

  @Test
  void answerCorrectShouldReturnFalseForIncorrectAnswer() {
    var challenge = new Challenge58();
    assertThat(challenge.answerCorrect("wronganswer")).isFalse();
    assertThat(challenge.answerCorrect("")).isFalse();
    assertThat(challenge.answerCorrect(null)).isFalse();
  }

  @Test
  void answerCorrectShouldTrimWhitespace() {
    var challenge = new Challenge58();
    assertThat(challenge.answerCorrect("  Rk9#mP2$vL8xQ7wZ  ")).isTrue();
  }

  @Test
  void simulateDatabaseConnectionErrorShouldExposeConnectionString() {
    var challenge = new Challenge58();
    String errorMessage = challenge.simulateDatabaseConnectionError();

    // Verify that the error message contains the exposed connection string
    assertThat(errorMessage).contains("jdbc:mysql://prod-db-01.internal.acme-corp.com:3306/orders_db");
    assertThat(errorMessage).contains("user=svc_order_app");
    assertThat(errorMessage).contains("password=Rk9#mP2$vL8xQ7wZ");
    assertThat(errorMessage).contains("Database connection failed");
  }
}
