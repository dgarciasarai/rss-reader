package com.alesarcode.rssreader.presentation.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.alesarcode.rssreader.R;
import com.alesarcode.rssreader.RSSReaderApplication;
import com.alesarcode.rssreader.di.components.DaggerRSSComponent;
import com.alesarcode.rssreader.di.modules.ActivityModule;
import com.alesarcode.rssreader.presentation.adapters.EntriesAdapter;
import com.alesarcode.rssreader.presentation.adapters.OnEntryClickListener;
import com.alesarcode.rssreader.presentation.model.EntryModel;
import com.alesarcode.rssreader.presentation.model.Model;
import com.alesarcode.rssreader.presentation.mvp.presenters.EntryListPresenter;
import com.alesarcode.rssreader.presentation.mvp.views.EntryListView;
import com.alesarcode.rssreader.presentation.navigation.Navigator;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Main activity for the App. Shows the list of RSS feed entries.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class EntryListActivity extends AppCompatActivity implements EntryListView, OnEntryClickListener {

    @BindView(R.id.rl_progress)
    RelativeLayout progressView;
    @BindView(R.id.rv_entries)
    RecyclerView recyclerView;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    EntryListPresenter mPresenter;
    @Inject
    Navigator navigator;
    @Inject
    EntriesAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list);
        this.initializeInjector();

        ButterKnife.bind(this);
        initializeToolbar();
        initializeRecyclerView();
        initializePresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.mPresenter.onViewResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.mPresenter.onViewPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mPresenter.onViewDestroy();
    }

    /**
     * Initialize injection for this activity.
     */
    private void initializeInjector() {
        DaggerRSSComponent.builder()
                .applicationComponent(((RSSReaderApplication) getApplication())
                        .getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build().inject(this);
    }

    private void initializePresenter() {
        this.mPresenter.setView(this);
        this.mPresenter.initialize();
    }

    private void initializeRecyclerView() {
        this.mAdapter.setOnEntryClickListener(this);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(mAdapter);
    }

    /**
     * Initialize custom toolbar.
     */
    private void initializeToolbar() {
        setSupportActionBar(toolbar);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Model> void add(Collection<T> models) {
        this.mAdapter.setEntriesCollection((Collection<EntryModel>) models);
    }

    @Override
    public void showFeedTitle(String title) {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(title);
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

    @Override
    public void onEntryClicked(EntryModel entry) {
        this.navigator.navigateToEntryDetail(this, entry.getId());
    }
}
