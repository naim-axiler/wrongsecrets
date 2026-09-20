package org.owasp.wrongsecrets.challenges.docker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.owasp.wrongsecrets.Challenges.ErrorResponses.DECRYPTION_ERROR;

import org.junit.jupiter.api.Test;

class Challenge26Test {

  @Test
  void rightAnswerShouldSolveChallenge() {
    var challenge =
        new Challenge26(
            "wy+lRvJdaEw/3FCUIm/7zspVrzK54Axby22sfQxc2d091xRldmJ2vP70zG5nNXBsvha6kkwxeEI=");
    assertThat(challenge.spoiler().solution()).isNotEqualTo(DECRYPTION_ERROR);
    assertThat(challenge.answerCorrect(challenge.spoiler().solution())).isTrue();
  }
}
