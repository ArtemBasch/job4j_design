package ru.job4j.inputoutput.argsname;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ArgsNameTest {
    @Test
    void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[] {"-request=?msg=Exit="});
        assertThat(jvm.get("request")).isEqualTo("?msg=Exit=");
    }

    @Test
    void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512"});
        assertThatThrownBy(() -> jvm.get("Xms")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenWrongSomeArgument() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void thereIsNoKey() {
        assertThatThrownBy(() -> ArgsName.of(new String[] {"-=?msg=Exit="}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void thereIsNoValue() {
        assertThatThrownBy(() -> ArgsName.of(new String[] {"-request="}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void noDashSymbol() {
        assertThatThrownBy(() -> ArgsName.of(new String[] {"request=?msg=Exit="}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void noEqualsSymbol() {
        assertThatThrownBy(() -> ArgsName.of(new String[] {"-?msg:Exit"}))
                .isInstanceOf(IllegalArgumentException.class);
    }
}