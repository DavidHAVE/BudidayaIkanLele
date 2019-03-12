package com.ari.android.budidayaikanlele.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ari.android.budidayaikanlele.helper.Constant;
import com.ari.android.budidayaikanlele.ui.DetailKolamActivity;
import com.ari.android.budidayaikanlele.R;
import com.ari.android.budidayaikanlele.ui.TambahKolamActivity;
import com.ari.android.budidayaikanlele.model.Pond;

import java.util.List;

/**
 * Created by David on 10/10/2017.
 */

public class PondAdapter extends ArrayAdapter<Pond> {

    public PondAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Pond> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_pond, parent, false);
        }

        TextView mPondNameTextView = (TextView) convertView.findViewById(R.id.pond_name_text_view);

        final Pond pond = getItem(position);

        mPondNameTextView.setText(pond.getName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailsPond(pond.getId());
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openEditPond(pond.getId());
                return true;
            }
        });

        return convertView;
    }

    public void openDetailsPond(int id){
        Intent i = new Intent(getContext(), DetailKolamActivity.class);
        i.putExtra(Constant.EDIT, false);
        i.putExtra(Constant.ID, id);
        getContext().startActivity(i);
    }

    public void openEditPond(int id){
        Intent i = new Intent(getContext(), TambahKolamActivity.class);
        i.putExtra(Constant.EDIT, true);
        i.putExtra(Constant.ID, id);
        getContext().startActivity(i);
    }
}
