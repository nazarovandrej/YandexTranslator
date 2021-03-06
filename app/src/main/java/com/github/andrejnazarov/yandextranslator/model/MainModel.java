package com.github.andrejnazarov.yandextranslator.model;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.github.andrejnazarov.yandextranslator.TranslationService;
import com.github.andrejnazarov.yandextranslator.TranslatorApp;
import com.github.andrejnazarov.yandextranslator.bean.TranslationBean;
import com.github.andrejnazarov.yandextranslator.presenter.MainPresenter;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Nazarov on 03.09.17.
 */

public class MainModel {

    private static final String KEY = "trnsl.1.1.20170426T135336Z.48d648f8f882d563.d40bb959b17363f2bee9e4a816f36ca997ce55c2";
    private static final String LANG_DEFAULT = "en-ru";
    private static final String LANG_RU_EN = "ru-en";
    private static final String LANG = "lang";
    private static final String FORMAT = "plain";
    private static final String OPTIONS = "1";

    private final MainPresenter mMainPresenter;

    @Inject
    TranslationService mService;

    @Inject
    SharedPreferences mPreferences;

    public MainModel(MainPresenter mainPresenter) {
        TranslatorApp.getNetComponent().inject(this);
        mMainPresenter = mainPresenter;
    }

    public void setLang(boolean isEnRu) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(LANG, isEnRu);
        editor.apply();
        mMainPresenter.showDefaultLanguageChecked(isEnRu);
    }

    public void processTranslation(String sourceText) {
        mMainPresenter.showProgress();
        String lang = isDefaultLanguageChecked() ? LANG_DEFAULT : LANG_RU_EN;
        Call<TranslationBean> beanCall = mService.getTranslation(KEY, sourceText, lang, FORMAT, OPTIONS);
        beanCall.enqueue(new Callback<TranslationBean>() {
            @Override
            public void onResponse(@NonNull Call<TranslationBean> call, @NonNull Response<TranslationBean> response) {
                if (response.isSuccessful()) {
                    TranslationBean bean = response.body();
                    if (bean != null) {
                        List<String> translations = bean.getTranslationList();
                        if (!translations.isEmpty()) {
                            String translation = translations.get(0);
                            mMainPresenter.hideProgress();
                            mMainPresenter.showTranslation(translation);
                        }
                    } else {
                        mMainPresenter.showError();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<TranslationBean> call, @NonNull Throwable t) {
                mMainPresenter.hideProgress();
                mMainPresenter.showError();
            }
        });
    }

    public boolean isDefaultLanguageChecked() {
        boolean isDefaultChecked = mPreferences.getBoolean(LANG, true);
        mMainPresenter.changeLanguage(isDefaultChecked);
        return isDefaultChecked;
    }
}