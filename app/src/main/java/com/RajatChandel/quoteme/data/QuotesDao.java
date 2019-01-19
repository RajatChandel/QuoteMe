package com.RajatChandel.quoteme.data;

import android.arch.paging.DataSource;
import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.RawQuery;

@Dao
public interface QuotesDao {

    @RawQuery
    DataSource.Factory<Integer, Quote> getSortedQuotes(SupportSQLiteQuery query);
}
