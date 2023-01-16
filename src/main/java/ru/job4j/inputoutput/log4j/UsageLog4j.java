package ru.job4j.inputoutput.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
    public static void main(String[] args) {
        int maxInt = 2147483647;
        boolean defaultValue = false;
        byte maxByte = 127;
        short maxShort = 32767;
        long maxLong = 9223372036854775807L;
        float maxFloat = 3.4E+38f;
        double maxDouble = 1.7E+308d;
        char maxChar = '\u0000';
        LOG.debug("Primitive types: int: {},\n boolean: {},\n byte: {},\n short: {},\n long: {},\n float: {},\n double: {},\n char: {}",
                maxInt, defaultValue, maxByte, maxShort, maxLong, maxFloat, maxDouble, maxChar);
    }
}
