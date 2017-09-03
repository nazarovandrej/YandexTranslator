package com.github.andrejnazarov.yandextranslator.presenter;

/**
 * @author Nazarov on 03.09.17.
 */

public interface MainPresenter {

    void showProgress();

    void hideProgress();

    void showError();

    void showTranslation(String translatedText);

    void processTranslation(String sourceText);
}