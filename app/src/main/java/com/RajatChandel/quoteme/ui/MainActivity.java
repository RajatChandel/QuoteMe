package com.RajatChandel.quoteme.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.RajatChandel.quoteme.R;
import com.RajatChandel.quoteme.data.AppRepository;
import com.RajatChandel.quoteme.data.Quote;
import com.RajatChandel.quoteme.ui.adapter.QuotesAdapter;
import com.RajatChandel.quoteme.viewmodel.QuoteViewModel;
import com.RajatChandel.quoteme.viewmodel.QuoteViewModelFactory;


public class MainActivity extends AppCompatActivity implements QuotesAdapter.ClickAction {

    RecyclerView mRecyclerView;
    Spinner mSpinner;
    String mSortBy;
    AppRepository mRepository;
    QuoteViewModel mViewModel;
    QuotesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.rv_quote_list);
        mSpinner = findViewById(R.id.spinner);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mAdapter = new QuotesAdapter(this, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_items, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mSpinner.setAdapter(spinnerAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSortBy = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mRepository = AppRepository.getInstance(this);
        mRepository.deleteAll();
        for (int i = 0; i < 40; i++) {
            Quote quote = new Quote("this is test" + i, "movie", null, "none", false);
            mRepository.addQuote(quote);
        }
        setupViewModel();
    }

    private void setupViewModel() {
        QuoteViewModelFactory factory = new QuoteViewModelFactory(mRepository, null);
        mViewModel = ViewModelProviders.of(this, factory).get(QuoteViewModel.class);
        mViewModel.getQuoteList().observe(this, new Observer<PagedList<Quote>>() {
            @Override
            public void onChanged(@Nullable PagedList<Quote> quotes) {
                mAdapter.submitList(quotes);
            }
        });
    }

    @Override
    public void onClick(int quoteId) {
        Toast.makeText(getApplicationContext(), "launch detal activity", Toast.LENGTH_SHORT).show();
    }
}
