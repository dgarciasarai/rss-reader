package com.alesarcode.rssreader.presentation.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alesarcode.rssreader.R;
import com.alesarcode.rssreader.RSSReaderApplication;
import com.alesarcode.rssreader.di.components.DaggerRSSComponent;
import com.alesarcode.rssreader.di.modules.ActivityModule;
import com.alesarcode.rssreader.di.modules.RSSModule;
import com.alesarcode.rssreader.presentation.model.EntryModel;
import com.alesarcode.rssreader.presentation.mvp.presenters.EntryDetailsPresenter;
import com.alesarcode.rssreader.presentation.mvp.views.EntryDetailView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity for entry detail when feed item is clicked.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class EntryDetailActivity extends AppCompatActivity implements EntryDetailView {

    public static final String INTENT_EXTRA_ENTRY_ID = "entry_id";

    @BindView(R.id.rl_progress)
    RelativeLayout progressView;
    @BindView(R.id.coordinator_detail)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.toolbar_detail)
    Toolbar toolbar;
    @BindView(R.id.title_detail)
    TextView title;
    @BindView(R.id.description_detail)
    TextView description;
    @BindView(R.id.entry_image)
    ImageView image;

    @Inject
    EntryDetailsPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_detail);
        initializeInjector();

        ButterKnife.bind(this);
        initializeToolbar();
        initializePresenter();
    }

    /**
     * Initialize injection for this activity.
     */
    private void initializeInjector() {
        int entryId = getIntent().getIntExtra(INTENT_EXTRA_ENTRY_ID, -1);

        DaggerRSSComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((RSSReaderApplication) getApplication())
                        .getApplicationComponent())
                .rSSModule(new RSSModule(entryId))
                .build().inject(this);
    }

    private void initializePresenter() {
        this.mPresenter.setView(this);
        this.mPresenter.initialize();
    }

    /**
     * Initialize custom toolbar.
     */
    private void initializeToolbar() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("");
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void renderItem(EntryModel entry) {
        title.setText(entry.getTitle());
        description.setText(entry.getDescription());

        if (entry.getImageUrl() == null) {
            Glide.clear(image);
            image.setImageDrawable(null);
        } else {
            Glide.with(this)
                    .load(entry.getImageUrl())
                    .placeholder(R.drawable.placeholder)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(image);
        }
    }

    @Override
    public void showLoading() {
        this.progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.progressView.setVisibility(View.GONE);
    }


    @Override
    public void showError(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public Context context() {
        return getApplicationContext();
    }
}
