package ru.job4j.list.listutils;

import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
            ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void removeWhen() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Predicate<Integer> filter = x -> x % 2 != 0;
        ListUtils.removeIf(input, filter);
        assertThat(input, is(Arrays.asList(2, 4, 6, 8, 10)));
    }

    @Test
    public void replaceWhen() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Predicate<Integer> filter = x -> x % 2 == 0;
        ListUtils.replaceIf(input, filter, 0);
        assertThat(input, is(Arrays.asList(1, 0, 3, 0, 5, 0, 7, 0, 9, 0)));
    }

    @Test
    public void ifRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 3, 4, 7, 10));
        ListUtils.removeAll(input, elements);
        assertThat(input, is(Arrays.asList(2, 5, 6, 8, 9)));

    }

}
