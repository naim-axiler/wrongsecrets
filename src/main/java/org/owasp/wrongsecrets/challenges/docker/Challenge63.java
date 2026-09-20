package org.owasp.wrongsecrets.challenges.docker;

import static org.owasp.wrongsecrets.Challenges.ErrorResponses.DECRYPTION_ERROR;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.owasp.wrongsecrets.challenges.FixedAnswerChallenge;
import org.springframework.stereotype.Component;

/**
 * Challenge demonstrating bad encryption practices: hardcoding both the encryption key and IV
 * directly in source code. Even though the secret is encrypted, the key is right here in the code,
 * making the encryption completely ineffective.
 */
@SuppressWarnings("java:S5542")
@SuppressFBWarnings(
    value = {"CIPHER_INTEGRITY", "PADDING_ORACLE"},
    justification = "Challenge intentionally demonstrates hardcoded key/IV and CBC weaknesses")
@Slf4j
@Component
public class Challenge63 extends FixedAnswerChallenge {

  private static final String HARDCODED_KEY = "Vaul7#K3y!2026xK";
  private static final String HARDCODED_IV = "Vaul7#1V!2026xK9";
  private static final String CIPHERTEXT = "hRI6FP3A5PIc3fmHwI64DgK1FrX9IazwbE16sgTROtw=";

  @Override
  public String getAnswer() {
    try {
      byte[] keyBytes = HARDCODED_KEY.getBytes(StandardCharsets.UTF_8);
      byte[] ivBytes = HARDCODED_IV.getBytes(StandardCharsets.UTF_8);
      byte[] cipherBytes = Base64.getDecoder().decode(CIPHERTEXT);
      SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
      IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
      // Intentionally using CBC mode to demonstrate padding oracle vulnerability
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
      byte[] decrypted = cipher.doFinal(cipherBytes);
      return new String(decrypted, StandardCharsets.UTF_8).trim();
    } catch (Exception e) {
      log.error("Decryption failed", e);
      return DECRYPTION_ERROR;
    }
  }
}
