/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kerko.ne.kuran.R;

import java.util.ArrayList;
import java.util.List;

import Model.CategoriesListObject;


public class CategoriesListAdapter extends RecyclerView.Adapter<CategoriesListAdapter.NumberViewHolder> {

    private static final String TAG = CategoriesListAdapter.class.getSimpleName();

    private static int viewHolderCount;
    final private ListItemClickListener mOnClickListener;
    private int mNumberItems;
    private List<CategoriesListObject> categoriesList = new ArrayList<CategoriesListObject>();

    public CategoriesListAdapter(int numberOfItems, List<CategoriesListObject> categoriesListIncoming, ListItemClickListener listener) {
        mNumberItems = numberOfItems;
        viewHolderCount = 0;
        categoriesList = categoriesListIncoming;
        mOnClickListener = listener;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.categories_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        //viewHolder.viewHolderIndex.setText("ViewHolder index: " + viewHolderCount);

        //viewHolderCount++;
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: "
                + viewHolderCount);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    /**
     * Cache of the children views for a list item.
     */
    class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Will display the position in the list, ie 0 through getItemCount() - 1
        TextView listItemNumberView;
        // Will display which ViewHolder is displaying this data
        TextView viewHolderName;
        TextView viewHolderDescription;

        public NumberViewHolder(View itemView) {
            super(itemView);

            listItemNumberView = (TextView) itemView.findViewById(R.id.tvItemNumber);
            viewHolderName = (TextView) itemView.findViewById(R.id.tvName);
            viewHolderDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            itemView.setOnClickListener(this);
        }

        void bind(int listIndex) {
            listItemNumberView.setText(String.valueOf(listIndex + 1));
            viewHolderName.setText(categoriesList.get(listIndex).getCategory());
            viewHolderDescription.setText(categoriesList.get(listIndex).getDescription());
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
