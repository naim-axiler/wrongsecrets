package org.owasp.wrongsecrets.challenges.docker;

import static org.owasp.wrongsecrets.Challenges.ErrorResponses.DECRYPTION_ERROR;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.owasp.wrongsecrets.challenges.FixedAnswerChallenge;
import org.springframework.stereotype.Component;

/** Challenge with a secret in .gitignore */
@Component
@Slf4j
public class Challenge54 extends FixedAnswerChallenge {

  @Override
  public String getAnswer() {
    return decryptAES();
  }

  @SuppressFBWarnings(
      value = {"CIPHER_INTEGRITY", "PADDING_ORACLE"},
      justification =
          "Though using AES CBC is a bad idea now that we have GCM-SIV, we want to use a simple"
              + " example")
  private String decryptAES() {
    final String encryptedSecret = "gQQc9CWN4ITob1FVe04Y/w7ay7muu0o7tMR2JsZfyYw=";
    final String passphrase = "g1t1gn0r3_1s_n0t_4_v4ult_2026";
    try {

      MessageDigest sha = MessageDigest.getInstance("SHA-256");
      byte[] keyBytes = sha.digest(passphrase.getBytes(StandardCharsets.UTF_8));
      SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
      byte[] ivBytes = "g1t1v3ct0r#2026!".getBytes(StandardCharsets.UTF_8); // 16 chars = 128 bits
      IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
      byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedSecret));
      return new String(decryptedBytes, StandardCharsets.UTF_8);
    } catch (Exception e) {
      log.warn("Exception with Challenge 54", e);
      return DECRYPTION_ERROR;
    }
  }
}
