package com.epam.courses.jf.jdbc.common;

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

}