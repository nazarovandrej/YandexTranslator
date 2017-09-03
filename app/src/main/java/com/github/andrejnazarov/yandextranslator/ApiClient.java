package com.github.andrejnazarov.yandextranslator;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Nazarov on 03.09.17.
 */

public class ApiClient {

    public static final String KEY = "trnsl.1.1.20170426T135336Z.48d648f8f882d563.d40bb959b17363f2bee9e4a816f36ca997ce55c2";
    private static final String BASE_URL = "https://translate.yandex.net/";

    private TranslationService mService;

    public TranslationService getTranslationService() {
        if (mService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
            mService = retrofit.create(TranslationService.class);
        }
        return mService;
    }
}