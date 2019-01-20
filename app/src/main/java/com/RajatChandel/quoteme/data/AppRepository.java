package com.RajatChandel.quoteme.data;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.content.Context;

import com.RajatChandel.quoteme.utils.QuotesUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppRepository {

    private AppDatabase mDatabase;
    private final QuotesDao mDao;
    private static volatile AppRepository sInstance = null;
    private final ExecutorService mIoExecutor;

    public static AppRepository getInstance(Context context) {
        if (sInstance == null) {
            synchronized (AppRepository.class) {
                if (sInstance == null) {
                    AppDatabase database = AppDatabase.getInstance(context);
                    sInstance = new AppRepository(database.quotesDao(),
                            Executors.newSingleThreadExecutor());
                }
            }
        }
        return sInstance;
    }

    private AppRepository(QuotesDao dao, ExecutorService executor) {
        mIoExecutor = executor;
        mDao = dao;
    }

    public LiveData<PagedList<Quote>> getQuotes(String sortBy, boolean favorite){

        DataSource.Factory<Integer, Quote> quotesDataSource
                = mDao.getSortedQuotes(QuotesUtils.buildQuotesQuery(sortBy, favorite));
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(5)
                .setPrefetchDistance(15)
                .build();

        return new LivePagedListBuilder<>(quotesDataSource, config)
                .setFetchExecutor(mIoExecutor)
                .build();
    }

    public void addQuote(Quote quote) {
        mDao.insertQuote(quote);
    }

    public void deleteAll() {
        mDao.deleteAllQuotes();
    }
}
