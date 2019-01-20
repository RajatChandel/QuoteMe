package com.RajatChandel.quoteme.ui.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.RajatChandel.quoteme.R;
import com.RajatChandel.quoteme.data.Quote;

public class QuotesAdapter extends PagedListAdapter<Quote, QuotesAdapter.QuotesViewHolder> {

    private Context mContext;
    private ClickAction mClickAction;

    public QuotesAdapter(Context context, ClickAction clickAction) {
        super(DIFF_CALLBACK);
        mContext = context;
        mClickAction = clickAction;
    }

    public interface ClickAction {
        void onClick(int quoteId);
    }

    @NonNull
    @Override
    public QuotesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, viewGroup, false);
        return new QuotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuotesViewHolder quotesViewHolder, int i) {
        Quote quote = getItem(i);
        if (quote != null) {
            quotesViewHolder.mAuthor.setText(quote.getAuthor());
            quotesViewHolder.mContent.setText(quote.getContent());
        } else {
            quotesViewHolder.clear();
        }
    }

    private static final DiffUtil.ItemCallback<Quote> DIFF_CALLBACK = new DiffUtil.ItemCallback<Quote>() {
        @Override
        public boolean areItemsTheSame(@NonNull Quote quoteOld, @NonNull Quote quoteNew) {
            return quoteOld.getId() == quoteNew.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Quote quoteOld, @NonNull Quote quoteNew) {
            return quoteOld == quoteNew;
        }
    };

    public class QuotesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mAuthor;
        TextView mContent;

        public QuotesViewHolder(@NonNull View itemView) {
            super(itemView);
            mAuthor = itemView.findViewById(R.id.tv_author);
            mContent = itemView.findViewById(R.id.tv_content);
            itemView.setOnClickListener(this);
        }

        public void clear() {
            mAuthor.setText(null);
            mContent.setText(null);
        }

        @Override
        public void onClick(View v) {
            mClickAction.onClick(getItem(getAdapterPosition()).getId());
        }
    }
}
