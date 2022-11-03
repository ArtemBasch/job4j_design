package ru.job4j.inputoutput.configreader;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("artem.bashlykov");
    }

    @Test
    void whenPartWithCommentAndEmptyStrings() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("phone")).isEqualTo("9065676637");
    }

    @Test
      public void whenIncompletePairs() {
        String path = "./data/pair_with_one_member.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo(null);
    }

    @Test
    void whenWithoutDelimiter() {
        String path = "./data/pair_without_delimiter.properties";
        Config config = new Config(path);
        config.load();
        assertThatIllegalArgumentException();

    }
}