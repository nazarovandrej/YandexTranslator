package com.github.andrejnazarov.yandextranslator;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * UI test of {@link MainActivity}
 *
 * @author Nazarov on 22.10.17.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String INPUT_STRING = "hello world";
    private static final String EXPECTED_STRING = "привет мир";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testTranslation() {

        onView(withId(R.id.input_edit_text)).perform(typeText(INPUT_STRING));

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.translate_button)).perform(click());

        onView(withId(R.id.translation_text_view)).check(matches(withText(EXPECTED_STRING)));
    }

    @Test
    public void testChangeLanguage() {

        onView(withId(R.id.switch_compat)).perform(click());

        onView(withId(R.id.translate_button)).check(matches(withText(R.string.translate_ru)));
    }
}
