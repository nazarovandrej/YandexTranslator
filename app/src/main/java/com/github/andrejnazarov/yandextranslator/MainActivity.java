package com.github.andrejnazarov.yandextranslator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.andrejnazarov.yandextranslator.bean.TranslationBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.github.andrejnazarov.yandextranslator.ApiClient.KEY;

public class MainActivity extends AppCompatActivity {

    private static final String LANG = "en-ru";
    private static final String FORMAT = "plain";
    private static final String OPTIONS = "1";

    @BindView(R.id.input_edit_text)
    EditText mInputEditText;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.translation_text_view)
    TextView mTranslationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.translate_button)
    void translate() {
        String input = mInputEditText.getText().toString();
        if (input.length() > 0) {
            mProgressBar.setVisibility(View.VISIBLE);
            getTranslation(input);
        }
    }

    private void getTranslation(String input) {
        ApiClient apiClient = new ApiClient();
        Call<TranslationBean> beanCall = apiClient.getTranslationService().getTranslation(KEY, input, LANG, FORMAT, OPTIONS);
        beanCall.enqueue(new Callback<TranslationBean>() {
            @Override
            public void onResponse(Call<TranslationBean> call, Response<TranslationBean> response) {
                if (response.isSuccessful()) {
                    TranslationBean bean = response.body();
                    if (bean != null) {
                        List<String> translations = bean.getTranslationList();
                        if (!translations.isEmpty()) {
                            String translation = translations.get(0);
                            showTranslation(translation);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<TranslationBean> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showTranslation(String text) {
        mProgressBar.setVisibility(View.GONE);
        mTranslationTextView.setText(text);
    }
}