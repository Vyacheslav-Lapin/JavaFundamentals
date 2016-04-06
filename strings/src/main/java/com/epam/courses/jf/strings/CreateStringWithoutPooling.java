package com.epam.courses.jf.strings;

public class CreateStringWithoutPooling {

    public static String toNonePooledString(CharSequence charSequence) {

        int length = charSequence.length();
        int breakInterval = length / 2;

        CharSequence charSequence1 = charSequence.subSequence(0, breakInterval);
        CharSequence charSequence2 = charSequence.subSequence(breakInterval, length);

        return new StringBuffer(charSequence1).append(charSequence2).toString();
    }
}
