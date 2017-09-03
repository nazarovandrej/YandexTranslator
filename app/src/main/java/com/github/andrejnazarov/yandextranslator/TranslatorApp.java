package com.github.andrejnazarov.yandextranslator;

import android.app.Application;

import com.github.andrejnazarov.yandextranslator.dagger.*;

/**
 * @author Nazarov on 03.09.17.
 */

public class TranslatorApp extends Application {

    private static final String BASE_URL = "https://translate.yandex.net/";
    private static NetComponent sNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BASE_URL))
                .build();
    }

    public static NetComponent getNetComponent() {
        return sNetComponent;
    }
}