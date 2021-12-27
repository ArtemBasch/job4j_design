package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleNameIsMentor() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mentor"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Mentor"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mentor"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleNameIsMentor() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mentor"));
        store.add(new Role("1", "Student"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Mentor"));
    }

    @Test
    public void whenReplaceThenRoleNameIsStudent() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mentor"));
        store.replace("1", new Role("1", "Student"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Student"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mentor"));
        store.replace("10", new Role("10", "Student"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Mentor"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mentor"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleNameIsMentor() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mentor"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Mentor"));
    }

}

