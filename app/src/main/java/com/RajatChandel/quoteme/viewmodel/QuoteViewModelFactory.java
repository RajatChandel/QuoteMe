package com.RajatChandel.quoteme.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.RajatChandel.quoteme.data.AppRepository;

public class QuoteViewModelFactory implements ViewModelProvider.Factory {

    private final AppRepository mAppRepository;
    private final String mSortBy;

    public QuoteViewModelFactory(AppRepository appRepository, String sortBy){
        mAppRepository = appRepository;
        mSortBy = sortBy;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try{
            return modelClass.getConstructor(AppRepository.class, String.class)
                    .newInstance(mAppRepository, mSortBy);
        } catch (Exception exception) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, exception);
        }
    }
}
