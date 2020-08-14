package com.example.projectApp.ui.language;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projectApp.R;

import java.util.Locale;

public class LanguageFragment extends Fragment {
Button lang;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        loadLocale();

        View root = inflater.inflate(R.layout.fragment_language, container, false);

       lang=root.findViewById(R.id.lang);

        lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguageDialog();
            }
        });


        return root;
    }
    private void showChangeLanguageDialog(){
        final String[] listItems={"English", "عربي"};
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(getActivity());
        mBuilder.setTitle("choose language");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(i==0){
                    setLocale("en");
                    getActivity().recreate();
                } else if(i==1){
                    setLocale("ar");
                    getActivity().recreate();
                }
                dialog.dismiss();
            }
        });
        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                getActivity().recreate();
            }
        });
        AlertDialog mDialog=mBuilder.create();

        mDialog.show();

    }

    public void setLocale(String lang){
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration config=new Configuration();
        config.locale=locale;
        getActivity().getBaseContext().getResources().updateConfiguration(config, getActivity().getBaseContext().getResources().getDisplayMetrics());
        //save data to shared preferences
        SharedPreferences.Editor editor=getActivity().getSharedPreferences("Settings", Activity.MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences prefs= getActivity().getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String lang=prefs.getString("My_Lang", "");
        setLocale(lang);
    }
}
