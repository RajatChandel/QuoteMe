package com.RajatChandel.quoteme.data;

import android.arch.paging.DataSource;
import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RawQuery;
import android.arch.persistence.room.Relation;

@Dao
public interface QuotesDao {

    @RawQuery(observedEntities = Quote.class)
    DataSource.Factory<Integer, Quote> getSortedQuotes(SupportSQLiteQuery query);

    @Insert
    void insertQuote(Quote quote);

    @Query("DELETE FROM quotes")
    void deleteAllQuotes();
}
