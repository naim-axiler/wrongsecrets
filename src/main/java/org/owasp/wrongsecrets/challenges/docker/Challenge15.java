package org.owasp.wrongsecrets.challenges.docker;

import com.google.common.base.Strings;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.owasp.wrongsecrets.Challenges;
import org.owasp.wrongsecrets.challenges.Challenge;
import org.owasp.wrongsecrets.challenges.Spoiler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/** This challenge is about AWS keys in git history, with actual canarytokens. */
@Slf4j
@Component
public class Challenge15 implements Challenge {

  private final String ciphterText;
  private final String encryptionKey;

  public Challenge15(@Value("${challenge15ciphertext}") String ciphterText) {
    this.ciphterText = ciphterText;
    encryptionKey =
        Base64.getEncoder().encodeToString("Kx9#mQ2vLp5$Rt8N".getBytes(StandardCharsets.UTF_8));
  }

  /** {@inheritDoc} */
  @Override
  public Spoiler spoiler() {
    return new Spoiler(quickDecrypt(ciphterText));
  }

  /** {@inheritDoc} */
  @Override
  public boolean answerCorrect(String answer) {
    String correctString = quickDecrypt(ciphterText);
    if (!correctString.equals(Challenges.ErrorResponses.DECRYPTION_ERROR)) {
      return answer.equals(correctString) || minimummatch_found(answer);
    } else {
      return false;
    }
  }

  private boolean minimummatch_found(String answer) {
    if (!Strings.isNullOrEmpty(answer)) {
      if (answer.length() < 19) {
        return false;
      }
      return quickDecrypt(ciphterText).contains(answer);
    }
    return false;
  }

  private String quickDecrypt(String cipherText) {
    try {
      final byte[] keyData = Base64.getDecoder().decode(encryptionKey);
      int aes256KeyLengthInBytes = 16;
      byte[] key = new byte[aes256KeyLengthInBytes];
      System.arraycopy(keyData, 0, key, 0, 16);
      Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
      SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
      int gcmTagLengthInBytes = 16;
      int gcmIVLengthInBytes = 12;
      byte[] initializationVector = new byte[gcmIVLengthInBytes];
      Arrays.fill(
          initializationVector,
          (byte) 0); // done for "poor-man's convergent encryption", please check actual convergent
      // cryptosystems for better implementation ;-)
      GCMParameterSpec gcmParameterSpec =
          new GCMParameterSpec(gcmTagLengthInBytes * 8, initializationVector);
      cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);
      byte[] plainTextBytes =
          cipher.doFinal(Base64.getDecoder().decode(cipherText.getBytes(StandardCharsets.UTF_8)));
      return new String(plainTextBytes, StandardCharsets.UTF_8);
    } catch (Exception e) {
      log.warn("Exception with Challenge 15", e);
      return Challenges.ErrorResponses.DECRYPTION_ERROR;
    }
  }

  // arcane:114,74
  // x+dN0o9RG6Au61KHGfqFlwwA0TV8J399/jwRDngcTlsMCzQynVxht0v1vspOKLf0ue8twFBoFTnoX6htNVArgffyJPdDRDigMFkJM1UXgrLHaan/5lrs43ym8h4sBcTb1NhZxCcSzLHHTdyzepvI0FEPJejHhBAKqZV/NszVAo35lTQKz1pQYAuZIHC2CsC94Q14xo4TaTUdHEm0TrzEQa43Zw==
  // wrongsecrets:115,75
  // x9FN3oBYDZht+ECANcbGlR4A/Qt0IWNR5AdaViECbHZtAEYi5Fhuulvpza9VQaaZ3fkb+WQFUnzqWr5GGVQrkPf1CMlLQiTzfiYqYlhlhuXSYaj75EXv4me1/RkxBdXS3NJbwCEUyLjZSN6gKKCk3lYDYcCFmCIO/do3YtbTGcPkxzseyVseMlPePGz1T5zu4FNndTmOPrK4rBA8wzpGNGm6L8NwvUTvsg==
  // wrongsecrets-2:115,75
  // x9FN3oBYDZht+ECANbbUqUkS1RlII2VB8itCbDM4fE0kWUZej1BkxlyavbQrKrT01p1452UPMQ24Gu14HUIXkffiJc1cfiDjbhwydH4u3ryiZfD+5kr24WWo8h0pA9HY1NZUxiQUzKDTTsW9LLao304PaNaKhyVLr+9iedPSHZatx2xNzEZRLhbLMGTyAM/uqAA/Y3WYK+z+O30Az4AW9kJWVfqba4MJiiAP

}
