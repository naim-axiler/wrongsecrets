package org.owasp.wrongsecrets.challenges.docker;

import static org.owasp.wrongsecrets.Challenges.ErrorResponses.DECRYPTION_ERROR;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Base64;
import org.owasp.wrongsecrets.challenges.FixedAnswerChallenge;
import org.springframework.stereotype.Component;

/** This is a challenge based on the idea of leaking a secret trough a vulnerability report. */
@Slf4j
@Component
public class Challenge35 extends FixedAnswerChallenge {

  @Override
  public String getAnswer() {
    return getKey();
  }

  private String getKey() {
    String ciphertext = "8gI1B97AUSTwUIR8KbiBj0CvYoveA+T99+7dYi60A3/095SUAeap182JeYo/vgn3";
    try {
      return decrypt(ciphertext);
    } catch (Exception e) {
      log.warn("there was an exception with decrypting content in challenge35", e);
      return DECRYPTION_ERROR;
    }
  }

  @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(
      value = "CIPHER_INTEGRITY",
      justification =
          "The scheme is bad without hmac, but we wanted to make it a bit more fun for you")
  private String decrypt(String ciphertext)
      throws InvalidAlgorithmParameterException,
          InvalidKeyException,
          NoSuchPaddingException,
          NoSuchAlgorithmException,
          IllegalBlockSizeException,
          BadPaddingException {
    IvParameterSpec iv = new IvParameterSpec("N5wV3ct0r#2026!x".getBytes(StandardCharsets.UTF_8));
    SecretKeySpec skeySpec =
        new SecretKeySpec(
            "A7fK2mQ9pL4xR8vT3nZ6bH1cJ5yU0dAq".getBytes(StandardCharsets.UTF_8), "AES");

    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
    return new String(
        cipher.doFinal(Base64.decode(ciphertext.getBytes(StandardCharsets.UTF_8))),
        StandardCharsets.UTF_8);
  }
}
