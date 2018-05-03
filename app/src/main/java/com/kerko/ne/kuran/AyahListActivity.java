package com.kerko.ne.kuran;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Controller.AyahsListAdapter;
import Controller.CategoriesListAdapter;
import Controller.ListController;
import Model.AyahListObject;
import Model.CategoriesListObject;

public class AyahListActivity extends AppCompatActivity implements AyahsListAdapter.ListItemClickListener {
    String tagu = "";
    private List<AyahListObject> ayahsList;
    private AyahsListAdapter mAdapter;
    private RecyclerView mNumbersList;
    private ListController catListController;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayah_list);


        tagu = getIntent().getStringExtra("tagu");
        if (tagu.equals(""))
            this.finish();

        catListController = new ListController(this);
        ListController categoriesController = new ListController(this);
        categoriesController.open();
        ayahsList = categoriesController.getAyahsForCategory("al", this, tagu);
        categoriesController.close();
        mNumbersList = (RecyclerView) findViewById(R.id.rv_numbers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNumbersList.setLayoutManager(layoutManager);
        mNumbersList.setHasFixedSize(true);
        mAdapter = new AyahsListAdapter(ayahsList.size(), ayahsList, this);
        mNumbersList.setAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        /*if (mToast != null) {
            mToast.cancel();
        }
        String toastMessage = "Category " + ayahsList.get(clickedItemIndex).getAjetikuranor() + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
        mToast.show();*/

        final Dialog dialog = new Dialog(this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        final View view = getLayoutInflater()
                .inflate(R.layout.dialog_screen, null, false);// inflates
        // the
        // dev_about
        AyahListObject obj2 = ayahsList.get(clickedItemIndex);

        String ajetithot = obj2.getAjetikuranor();
        String pershkrimi = obj2.getAjeti();
        final TextView ckameshkrujt = (TextView) view
                .findViewById(R.id.Text_ViewItem_Time);
        ckameshkrujt.setText(R.string.ajetikuranor);

        final TextView Idetagut = (TextView) view
                .findViewById(R.id.Text_ViewItem_Speaker);
        Idetagut.setText(ajetithot + " "
                + getString(R.string.ajetithot));

        String teksti = Idetagut.getText().toString();
        final TextView pershkrimetemarrura = (TextView) view
                .findViewById(R.id.Text_ViewItem_Description);
        pershkrimetemarrura.setText(pershkrimi);

        dialog.setContentView(view);
        dialog.setTitle(tagu + "Hello world");
        // now that the dialog is set up, it's time to show it
        dialog.show();

    }
}
