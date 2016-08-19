package com.epam.courses.jf.common;

import org.junit.Test;

import static com.epam.courses.jf.common.StringEncryptUtil.encrypt;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StringEncryptUtilTest {

    @Test
    public void heffelfingerWasRight() {
        assertThat(encrypt("s3cret"), is("33e1b232a4e6fa0028a6670753749a17"));
    }

}
