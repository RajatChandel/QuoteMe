package com.RajatChandel.quoteme.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.content.Context;

import com.RajatChandel.quoteme.data.AppRepository;
import com.RajatChandel.quoteme.data.Quote;

public class QuoteViewModel extends ViewModel {

    private LiveData<PagedList<Quote>> mQuoteList;
    private boolean mFavorite;
    private AppRepository mRepository;
    private String mSort;

    public QuoteViewModel(AppRepository repository, String sortBy) {
        mFavorite = false;
        mRepository = repository;
        mSort = sortBy;
    }

    public LiveData<PagedList<Quote>> getQuoteList() {
        mQuoteList = mRepository.getQuotes(mSort, mFavorite);
        return mQuoteList;
    }
}
