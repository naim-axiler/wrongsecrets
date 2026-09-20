package org.owasp.wrongsecrets.challenges.docker;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.owasp.wrongsecrets.challenges.Spoiler;

class Challenge60Test {

  @Test
  void spoilerShouldReturnSecret() {
    var challenge = new Challenge60("MCP#T0ken!d3tect0r$42xQ");
    assertThat(challenge.spoiler()).isEqualTo(new Spoiler("MCP#T0ken!d3tect0r$42xQ"));
  }

  @Test
  void answerCorrectShouldReturnTrueForCorrectAnswer() {
    var challenge = new Challenge60("MCP#T0ken!d3tect0r$42xQ");
    assertThat(challenge.answerCorrect("MCP#T0ken!d3tect0r$42xQ")).isTrue();
  }

  @Test
  void answerCorrectShouldReturnFalseForIncorrectAnswer() {
    var challenge = new Challenge60("MCP#T0ken!d3tect0r$42xQ");
    assertThat(challenge.answerCorrect("wronganswer")).isFalse();
    assertThat(challenge.answerCorrect("")).isFalse();
    assertThat(challenge.answerCorrect(null)).isFalse();
  }

  @Test
  void answerCorrectShouldTrimWhitespace() {
    var challenge = new Challenge60("MCP#T0ken!d3tect0r$42xQ");
    assertThat(challenge.answerCorrect("  MCP#T0ken!d3tect0r$42xQ  ")).isTrue();
  }
}
