package com.RajatChandel.quoteme.data;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.content.Context;

import com.RajatChandel.quoteme.utils.QuotesUtils;

public class AppRepository {

    private QuotesDao mDao;
    private AppDatabase mDatabase;

    public AppRepository(Context context){
        mDatabase = AppDatabase.getInstance(context.getApplicationContext());
    }

    public LiveData<PagedList<Quote>> getQuotes(String sortBy, boolean favorite){

        DataSource.Factory<Integer, Quote> quotesDataSource
                = mDao.getSortedQuotes(QuotesUtils.buildQuotesQuery(sortBy, favorite));

        LiveData<PagedList<Quote>> quotesList = new LivePagedListBuilder<>(quotesDataSource, 20)
                .build();
        return quotesList;
    }
}
