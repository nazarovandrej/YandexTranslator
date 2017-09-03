package com.github.andrejnazarov.yandextranslator.presenter;

import com.github.andrejnazarov.yandextranslator.ApiClient;
import com.github.andrejnazarov.yandextranslator.model.MainModel;
import com.github.andrejnazarov.yandextranslator.view.MainView;

/**
 * @author Nazarov on 03.09.17.
 */

public class MainPresenterImpl implements MainPresenter {

    private final MainView mMainView;
    private final MainModel mMainModel;

    public MainPresenterImpl(MainView mainView) {
        mMainView = mainView;
        mMainModel = new MainModel(this, new ApiClient());
    }

    @Override
    public void showProgress() {
        mMainView.showProgress();
    }

    @Override
    public void hideProgress() {
        mMainView.hideProgress();
    }

    @Override
    public void showError() {
        mMainView.showError();
    }

    @Override
    public void showTranslation(String translatedText) {
        mMainView.showTranslation(translatedText);
    }

    @Override
    public void processTranslation(String sourceText) {
        mMainModel.processTranslation(sourceText);
    }
}