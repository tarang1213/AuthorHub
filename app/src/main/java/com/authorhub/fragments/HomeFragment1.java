package com.authorhub.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.authorhub.R;
import com.authorhub.adapters.BookAdapter;
import com.authorhub.models.BookModel;

import java.util.ArrayList;

public class HomeFragment1 extends Fragment {

    ListView listView;

    String[] strBook = {"Book 1", "Book 2", "Book 3", "Book 4", "Book 5", "Book 6", "Book 7", "Book 8"};

    int[] imgBook = {R.drawable.ic_home, R.drawable.ic_home, R.drawable.ic_home,
            R.drawable.ic_home, R.drawable.ic_home, R.drawable.ic_home, R.drawable.ic_home, R.drawable.ic_home};

    ArrayList<BookModel> bookModelArrayList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_home1, container, false);
        listView = rootView.findViewById(R.id.list_view);
        bookModelArrayList = new ArrayList<BookModel>();

        for (int i = 0; i < strBook.length; i++) {
            BookModel bookModel = new BookModel(strBook[i], imgBook[i]);
            bookModelArrayList.add(bookModel);
        }

        BookAdapter bookAdapter = new BookAdapter(getActivity(),bookModelArrayList);
        listView.setAdapter(bookAdapter);

        return rootView;
    }
}