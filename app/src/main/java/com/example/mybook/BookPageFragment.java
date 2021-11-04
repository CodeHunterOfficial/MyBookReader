package com.example.mybook;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.InputStream;

public class BookPageFragment extends Fragment {

    private static final String LOG_TAG = "AndroidExample";
    MediaPlayer mPlayer;
    private String bookName;
    private ImageView image;
    private static int counter = 0;

    public BookPageFragment() {

    }

    public BookPageFragment(String bookName)
    {
        this.bookName = bookName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(
                R.layout.fragment_book_page, container, false);

        counter++;
        if (counter % 2 == 0) {
            view.setBackgroundColor(Color.parseColor("#ebdef0"));
        } else {
            view.setBackgroundColor(Color.parseColor("#e8f8f5"));
        }
        this.image = view.findViewById(R.id.image);
        return view;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.i(LOG_TAG, "onSaveInstanceState: save employee data to Bundle");
        // Convert employee object to Bundle.

        Bundle dataBundle = this.employeeToBundle(this.bookName);
        outState.putAll(dataBundle);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.i(LOG_TAG, "onViewStateRestored");
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.i(LOG_TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        if (this.bookName == null) {
            Log.i(LOG_TAG, "Get employee data from savedInstanceState");
            // The state was saved by onSaveInstanceState(Bundle outState) method.
            this.bookName = this.bundleToEmployee(savedInstanceState);
        }
        this.showInGUI(this.bookName);
    }




    // Call where View ready.
    private void showInGUI(String bookName) {
        try (InputStream inputStream = getActivity().getApplicationContext().getAssets().open(bookName)) {
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            this.image.setImageDrawable(drawable);
            this.image.setScaleType(ImageView.ScaleType.FIT_XY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Bundle employeeToBundle(String bookName) {
        Bundle bundle = new Bundle();
        bundle.putString("bookName", bookName);
        return bundle;
    }

    private String bundleToEmployee(Bundle savedInstanceState) {
        String bookName = savedInstanceState.getString("bookName");

        return bookName;
    }
}