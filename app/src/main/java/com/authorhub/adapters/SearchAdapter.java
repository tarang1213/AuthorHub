package com.authorhub.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.authorhub.R;
import com.authorhub.activity.BookDisplayActivity;
import com.authorhub.activity.CatDetailsActivity;
import com.authorhub.models.CategoryModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAdapter extends BaseAdapter {

    Context context;
    ArrayList<CategoryModel> categoryModelArrayList;

    public SearchAdapter(Context context, ArrayList<CategoryModel> categoryModelArrayList) {

        this.context = context;
        this.categoryModelArrayList = categoryModelArrayList;

    }

    @Override
    public int getCount() {
        return categoryModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.raw_grid, null);

        CircleImageView imgBook = convertView.findViewById(R.id.img_book);
        String url = categoryModelArrayList.get(position).getCat_url();
        Glide.with(context).load(url).into(imgBook);
        TextView tvBook = convertView.findViewById(R.id.tv_book);
        ImageView imgEdt = convertView.findViewById(R.id.img_edit);
        imgEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = categoryModelArrayList.get(position).getCat_url();
                String catName = categoryModelArrayList.get(position).getCat_name();
                String catDescription = categoryModelArrayList.get(position).getCat_description();
                String catID = categoryModelArrayList.get(position).getCat_id();

                Intent i = new Intent(context, CatDetailsActivity.class);
                i.putExtra("KEY_ID",catID);
                i.putExtra("KEY_NAME",catName);
                i.putExtra("KEY_DESC",catDescription);
                i.putExtra("KEY_URL",url);
                context.startActivity(i);

            }
        });
        //imgBook.setImageResource(categoryModelArrayList.get(position).getImgBook());
        tvBook.setText(categoryModelArrayList.get(position).getCat_name());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String catName = categoryModelArrayList.get(position).getCat_name();
                Intent i = new Intent(context, BookDisplayActivity.class);
                i.putExtra("KEY_CAT",catName);
                context.startActivity(i);
                          }
        });



        return convertView;
    }
}
