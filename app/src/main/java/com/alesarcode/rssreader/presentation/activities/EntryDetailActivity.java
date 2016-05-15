package com.alesarcode.rssreader.presentation.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alesarcode.rssreader.R;

/**
 * Activity for entry detail when feed item is clicked.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class EntryDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_detail);
    }
}
