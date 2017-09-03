package com.github.andrejnazarov.yandextranslator;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Nazarov on 03.09.17.
 */

public class ApiClient {

    private static final String BASE_URL = "https://translate.yandex.net/";

    private static Retrofit sRetrofit = null;

    public static TranslationService getTranslationService() {
        TranslationService service = null;
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
            service = sRetrofit.create(TranslationService.class);
        }
        return service;
    }
}