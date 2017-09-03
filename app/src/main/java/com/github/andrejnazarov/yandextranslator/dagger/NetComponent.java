package com.github.andrejnazarov.yandextranslator.dagger;

import com.github.andrejnazarov.yandextranslator.model.MainModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Nazarov on 03.09.17.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    void inject(MainModel model);
}