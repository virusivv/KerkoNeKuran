package com.kerko.ne.kuran;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.List;

import Controller.CategoriesListAdapter;
import Controller.ListController;
import Model.CategoriesListObject;

public class ListCategoriesActivity extends AppCompatActivity implements CategoriesListAdapter.ListItemClickListener {

    private static final int NUM_LIST_ITEMS = 100;

    private CategoriesListAdapter mAdapter;
    private RecyclerView mNumbersList;
    private ListController catListController;
    private Toast mToast;
    private List<CategoriesListObject> categoriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_list_categories);
        catListController = new ListController(this);
        ListController categoriesController = new ListController(this);
        categoriesController.open();
        categoriesList = categoriesController.getCategories("al", this);
        categoriesController.close();
        mNumbersList = (RecyclerView) findViewById(R.id.rv_numbers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNumbersList.setLayoutManager(layoutManager);
        mNumbersList.setHasFixedSize(true);
        mAdapter = new CategoriesListAdapter(categoriesList.size(), categoriesList, this);
        mNumbersList.setAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        /*if (mToast != null) {
            mToast.cancel();
        }
        String toastMessage = "Category " + categoriesList.get(clickedItemIndex).getCategory() + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
        mToast.show();*/
        Intent intent = new Intent(this, AyahListActivity.class);
        intent.putExtra("tagu", categoriesList.get(clickedItemIndex).getCategory());
        startActivity(intent);
    }
}
