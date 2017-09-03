package com.github.andrejnazarov.yandextranslator.bean;


import org.junit.Test;

import java.util.Collections;

/**
 * Test for {@link TranslationBean}
 *
 * @author Nazarov on 03.09.17.
 */
public class TranslationBeanTest extends BaseJsonParserTest {

    private static final String TEST_FILE = "translation.json";

    @Test
    public void testParseObject() {
        testParse(TEST_FILE, getTranslationBean(), TranslationBean.class);
    }

    private TranslationBean getTranslationBean() {
        return new TranslationBean(200, "en-ru", Collections.singletonList("Здравствуй, Мир!"));
    }
}