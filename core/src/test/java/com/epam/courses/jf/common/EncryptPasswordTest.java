package com.epam.courses.jf.common;

import org.junit.Test;

import static com.epam.courses.jf.common.EncryptPassword.encryptPassword;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EncryptPasswordTest {

    @Test
    public void heffelfingerWasRight() {
        assertThat(encryptPassword("s3cret"), is("33e1b232a4e6fa0028a6670753749a17"));
    }

}
