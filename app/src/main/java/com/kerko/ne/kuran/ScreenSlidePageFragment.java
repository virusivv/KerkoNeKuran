package com.kerko.ne.kuran;

/**
 * Created by ivasija on 12.05.2018.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import Model.SurahObject;

public class ScreenSlidePageFragment extends Fragment {
    int page;
    String ajetet;
    String surja;

    public static ScreenSlidePageFragment newInstance(int page, String ajetet, String surja) {
        ScreenSlidePageFragment fragmentFirst = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("ajetet", ajetet);
        args.putString("surja", surja);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("page");
        surja = getArguments().getString("surja");
        ajetet = getArguments().getString("ajetet");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.view_pager_item, container, false);

        TextView txt=(TextView)rootView.findViewById(R.id.txtPershkrimi);
        txt.setText(ajetet);
        return rootView;
    }

}
