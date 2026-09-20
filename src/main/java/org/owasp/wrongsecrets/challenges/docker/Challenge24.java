package org.owasp.wrongsecrets.challenges.docker;

import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Hex;
import org.owasp.wrongsecrets.challenges.FixedAnswerChallenge;
import org.springframework.stereotype.Component;

/** This challenge is about using a publicly specified key to safeguard data. */
@Slf4j
@Component
public class Challenge24 extends FixedAnswerChallenge {

  private String getActualData() {
    return new String(
        Hex.decode(
            "6134663963326537206231643866336136203563376539643262203861336636633165206439623261356637203463366538643161206633623563396532203764346136663862"
                .getBytes(StandardCharsets.UTF_8)),
        StandardCharsets.UTF_8);
  }

  @Override
  public String getAnswer() {
    return getActualData();
  }
}
