package com.github.andrejnazarov.yandextranslator.presenter;

/**
 * @author Nazarov on 03.09.17.
 */

public interface MainPresenter {

    void showProgress();

    void hideProgress();

    void showError();

    void showTranslation(String translatedText);

    void showDefaultLanguageChecked(boolean checked);

    void getLanguage();

    void processTranslation(String sourceText);

    void changeLanguage(boolean isEnRu);
}