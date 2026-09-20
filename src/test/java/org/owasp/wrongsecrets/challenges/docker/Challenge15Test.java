package org.owasp.wrongsecrets.challenges.docker;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Challenge15Test {

  @Test
  void solveChallenge15() {
    Challenge15 challenge15 =
        new Challenge15(
            "x+dN0o9RG6Au61KHGfqFlwwA0TV8J399/jwRDngcTlsMCzQynVxht0v1vspOKLf0ue8twFBoFTnoX6htNVArgffyJPdDRDigMFkJM1UXgrLHaan/5lrs43ym8h4sBcTb1NhZxCcSzLHHTdyzepvI0FEPJejHhBAKqZV/NszVAo35lTQKz1pQYAuZIHC2CsC94Q14xo4TaTUdHEm0TrzEQa43Zw==");
    assertThat(challenge15.spoiler().toString()).contains("aws");
    assertThat(challenge15.answerCorrect(challenge15.spoiler().solution())).isTrue();
  }
}
