package com.github.andrejnazarov.yandextranslator;

import com.github.andrejnazarov.yandextranslator.bean.TranslationBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author Nazarov on 03.09.17.
 */

public interface TranslationService {

    @FormUrlEncoded
    @POST("api/v1.5/tr.json/translate")
    Call<TranslationBean> getTranslation(@Field("key") String key,
                                         @Field("text") String text,
                                         @Field("lang") String lang,
                                         @Field("format") String format,
                                         @Field("options") String options);
}
