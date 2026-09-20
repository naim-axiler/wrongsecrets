package org.owasp.wrongsecrets.challenges.docker;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.owasp.wrongsecrets.Challenges;
import org.owasp.wrongsecrets.challenges.Spoiler;

class Challenge56Test {

  @Test
  void solveChallenge56WithoutFile(@TempDir Path dir) {
    var challenge = new Challenge56(dir.resolve("nonexistent.mdc").toString());
    assertThat(challenge.answerCorrect("AKIAQ7W2K9XM4P3R8")).isFalse();
    assertThat(challenge.answerCorrect(Challenges.ErrorResponses.FILE_MOUNT_ERROR)).isTrue();
  }

  @Test
  void solveChallenge56WithFile(@TempDir Path dir) throws Exception {
    var testFile = new File(dir.toFile(), "project-specification.mdc");
    var secretLine = "blabla\n- Example API key for testing: AKIAQ7W2K9XM4P3R8";
    Files.writeString(testFile.toPath(), "Some intro text\n" + secretLine + "\nSome outro text\n");

    var challenge = new Challenge56(testFile.getAbsolutePath());
    assertThat(challenge.answerCorrect("AKIAQ7W2K9XM4P3R8")).isTrue();
    assertThat(challenge.answerCorrect("wrongsecret")).isFalse();
  }

  @Test
  void spoilShouldReturnCorrectAnswer(@TempDir Path dir) throws IOException {
    var testFile = new File(dir.toFile(), "project-specification.mdc");
    var secretLine = "blabla\n- Example API key for testing: AKIAQ7W2K9XM4P3R8";
    Files.writeString(testFile.toPath(), secretLine + "\n");

    var challenge = new Challenge56(testFile.getAbsolutePath());
    assertThat(challenge.spoiler()).isEqualTo(new Spoiler("AKIAQ7W2K9XM4P3R8"));
  }
}
