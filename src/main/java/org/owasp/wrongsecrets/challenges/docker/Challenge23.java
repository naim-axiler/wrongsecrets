package org.owasp.wrongsecrets.challenges.docker;

import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.owasp.wrongsecrets.challenges.FixedAnswerChallenge;
import org.springframework.stereotype.Component;

/** This challenge is about finding a secret hardcoded in comments in a front-end. */
@Slf4j
@Component
public class Challenge23 extends FixedAnswerChallenge {

  @Override
  public String getAnswer() {
    return getActualData();
  }

  private String getActualData() {
    return new String(
        Base64.decode(
            Hex.decode(
                Base64.decode(
                    "NWE2ZTQ5Nzc2MjZlNTE3YTYyNmQ1MTZhNTk3YTQyNzQ2MjU0NGU3NTY0NDM0NjczNGQ3YTUyNzI0ZDMyNTI2NjRkNmE0MTc5NGU2ZTY4NGM0ZjU3MzE1MQ=="))),
        StandardCharsets.UTF_8);
  }
}
