package com.epam.courses.jf.jdbc.common;

import lombok.AllArgsConstructor;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DaoTest {
    @Test
    public void toSqlName() throws Exception {
        assertThat(Dao.toSqlName("codeName"), is("code_name"));
    }

    @Test
    public void toCamelCaseName() throws Exception {
        assertThat(Dao.toCamelCaseName("code_name"), is("codeName"));
    }

    @Test
    public void toSelect() throws Exception {
        assertThat(Dao.toSelect(Person.class), is("select id, code_name from Person"));
    }

    @Test
    public void toSelectFromLambda() throws Exception {
//        assertThat(Dao.toSelect(Person::new), is("select id, code_name from Person"));
    }

    @Test
    public void toSelectById() throws Exception {
        assertThat(Dao.toSelect(Person.class, 0), is("select code_name from Person where id = 0"));

    }

    @AllArgsConstructor
    private class Person {
        private int id;
        private String codeName;
    }
}