package org.owasp.wrongsecrets.challenges.docker;

import static org.owasp.wrongsecrets.Challenges.ErrorResponses.DECRYPTION_ERROR;

import java.nio.charset.StandardCharsets;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Base64;
import org.owasp.wrongsecrets.challenges.FixedAnswerChallenge;
import org.springframework.stereotype.Component;

/**
 * This is a challenge based on LLM where people need to extract the secret from
 * https://https://gandalf.lakera.ai//
 */
@Slf4j
@Component
public class Challenge32 extends FixedAnswerChallenge {

  @Override
  public String getAnswer() {
    return getSolution();
  }

  private String getSolution() {
    return decrypt(
        decrypt(
            decrypt(
                "o/fB6bLVhAZ8AgAAs4hH8KiaN+dfZcqSnSjaCadBnEQwFVnhh9Dl/gD3TkwsiTssAyntVOahSx+mkVL89M0EYzEGDMfdFMd7chY6aXShFSDlgI2+9zIrBbjr7wM47WjIvNwjBeNj2zETokM9itwH9DYmE00erUgcu3ym9Mt/0C5W2fs1NHooxuJzoss1KobHLC15I85VW0c=")));
  }

  private String decrypt(String cipherTextString) {
    try {
      final Cipher decryptor = Cipher.getInstance("AES/GCM/NoPadding");
      SecretKey decryptKey =
          new SecretKeySpec("LLM_K3y_2026!xQ9".getBytes(StandardCharsets.UTF_8), "AES");
      AlgorithmParameterSpec gcmIv =
          new GCMParameterSpec(128, Base64.decode(cipherTextString), 0, 12);
      decryptor.init(Cipher.DECRYPT_MODE, decryptKey, gcmIv);
      return new String(
          decryptor.doFinal(
              Base64.decode(cipherTextString.getBytes(StandardCharsets.UTF_8)),
              12,
              Base64.decode(cipherTextString.getBytes(StandardCharsets.UTF_8)).length - 12),
          StandardCharsets.UTF_8);
    } catch (Exception e) {
      log.warn("Exception in Challenge32", e);
      return DECRYPTION_ERROR;
    }
  }
}
