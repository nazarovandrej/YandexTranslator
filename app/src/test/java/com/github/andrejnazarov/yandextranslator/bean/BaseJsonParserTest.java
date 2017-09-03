package com.github.andrejnazarov.yandextranslator.bean;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Base class for json parsing.
 * Inherit for junit tests.
 *
 * @author Nazarov on 03.09.17.
 */

public class BaseJsonParserTest {

    private ObjectMapper mObjectMapper;

    @Before
    public void setUp() {
        mObjectMapper = new ObjectMapper();
    }

    public void testParse(String testFile, Object objectToParse, Class clasz) {
        InputStream stream = clasz.getClassLoader().getResourceAsStream(testFile);
        Object actual = null;
        try {
            actual = mObjectMapper.readValue(stream, clasz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(actual);
        assertThat(objectToParse, is(actual));
    }
}