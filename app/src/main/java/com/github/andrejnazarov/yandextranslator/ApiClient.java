package com.github.andrejnazarov.yandextranslator;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Nazarov on 03.09.17.
 */

public class ApiClient {

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