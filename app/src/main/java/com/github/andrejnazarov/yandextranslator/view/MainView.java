package com.github.andrejnazarov.yandextranslator.view;

/**
 * @author Nazarov on 03.09.17.
 */

public interface MainView {

    void showProgress();

    void hideProgress();

    void showError();

    void showTranslation(String translatedText);
}