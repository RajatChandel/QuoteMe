package com.RajatChandel.quoteme.utils;

import android.arch.persistence.db.SimpleSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteQueryBuilder;

import com.RajatChandel.quoteme.data.AppDatabase;

public class QuotesUtils {

    public static SupportSQLiteQuery buildQuotesQuery(String sortOrder, boolean favorite){
        String columnName = "id";
     /*   switch (sortOrder){
            case "id":
                columnName = "id";
                break;
        }*/
        SupportSQLiteQuery query = SupportSQLiteQueryBuilder.builder("quotes")
                .orderBy(columnName).create();

        return query;
    }
}
