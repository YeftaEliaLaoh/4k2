package com.example.dynamicfeature.setting.ui.language;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.a4k.android.SampleMainActivity;
import com.example.dynamicfeature.setting.ui.Config;
import com.example.dynamicfeature.setting.ui.R;

import java.util.Locale;

public class LanguageDialog {

    private Dialog dialog;
    private ImageView imageClose;
    private RadioGroup radioGroup;
    RadioButton radioEnglish;
    RadioButton radioIndonesia;

    public LanguageDialog(Context context) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_language);
        initLayout();
        initData();
        initEvent(context);
        dialog.show();
    }

    private void initLayout() {
        imageClose = dialog.findViewById(R.id.language_setting_button_dismiss);
        radioGroup = dialog.findViewById(R.id.rdGroup);
        radioEnglish = dialog.findViewById(R.id.radioEnglish);
        radioIndonesia = dialog.findViewById(R.id.radioIndonesia);
    }

    private void initEvent(final Context context) {
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int indexRadio = radioGroup.indexOfChild(radioButton);
                // Add logic here
                switch (indexRadio) {
                    case 0:
                        dialog.dismiss();
                        updatePresistLanguage("en", context);
                        Config.language = indexRadio;
                        refreshActivity(context);
                        break;
                    case 1:
                        dialog.dismiss();
                        updatePresistLanguage("in", context);
                        Config.language = indexRadio;
                        refreshActivity(context);
                        break;
                }
            }
        });
    }


    void initData() {
        if (Config.language == 0)
            radioEnglish.setChecked(true);
        else if (Config.language == 1)
            radioIndonesia.setChecked(true);
    }

    public void refreshActivity(Context context) {
        Intent intent = new Intent(context, SampleMainActivity.class);
        context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    //Setting language
    public void updatePresistLanguage(String language, Context context) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources res = context.getResources();
        Configuration configuration = new Configuration(res.getConfiguration());
        configuration.setLocale(locale);
        res.updateConfiguration(configuration, res.getDisplayMetrics());
    }

}
