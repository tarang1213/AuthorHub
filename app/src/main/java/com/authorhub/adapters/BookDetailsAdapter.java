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
import com.authorhub.activity.BookDetailsActivity;
import com.authorhub.activity.BookDisplayActivity;
import com.authorhub.activity.BookMoreDetailsActivity;
import com.authorhub.activity.CatDetailsActivity;
import com.authorhub.models.BookDetailsModel;
import com.authorhub.models.CategoryModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class BookDetailsAdapter extends BaseAdapter {
    Context context;
    ArrayList<BookDetailsModel> bookDetailsModelArrayList;

    public BookDetailsAdapter(Context context, ArrayList<BookDetailsModel>bookDetailsModelArrayList) {

        this.context = context;
        this.bookDetailsModelArrayList = bookDetailsModelArrayList;

    }

    @Override
    public int getCount() {
        return bookDetailsModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookDetailsModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.raw_book, null);

       ImageView imgBook = convertView.findViewById(R.id.img_book);
        String url = bookDetailsModelArrayList.get(position).getBookImage();
        Glide.with(context).load(url).into(imgBook);
        TextView tvBook = convertView.findViewById(R.id.tv_book);
       // imgBook.setImageResource(Integer.parseInt(bookDetailsModelArrayList.get(position).getBookImage()));
        tvBook.setText(bookDetailsModelArrayList.get(position).getBookName());
       // tvBook.setText(bookDetailsModelArrayList.get(position).getAuthorName());

       // CircleImageView imgauthor = convertView.findViewById(R.id.img_author);
      //  String url = bookDetailsModelArrayList.get(position).getAuthorImg();
        //Glide.with(context).load(url).into(imgauthor);
        //TextView tvBook = convertView.findViewById(R.id.tv_book);




        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String BookImage = bookDetailsModelArrayList.get(position).getBookImage();
                String BookName = bookDetailsModelArrayList.get(position).getBookName();
                String bookDesc =bookDetailsModelArrayList.get(position).getBookDesc();
                String Sid = bookDetailsModelArrayList.get(position).getSid();
                String Athorname = bookDetailsModelArrayList.get(position).getAuthorName();

                Intent i = new Intent(context, BookMoreDetailsActivity.class);
                i.putExtra("KEY_ID",Sid);
                i.putExtra("KEY_NAME",BookName);
                i.putExtra("KEY_DESC",bookDesc);
                i.putExtra("KEY_BOOKIMG",BookImage);
                i.putExtra("KEY_AUTHOR",Athorname);

                context.startActivity(i);
            }
        });



        return convertView;
    }
}


