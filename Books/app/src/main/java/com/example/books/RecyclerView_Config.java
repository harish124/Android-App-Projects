package com.example.books;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerView_Config {

    private Context mContext;
    private BookAdapter mBooksAdapter;
    public void setConfig(RecyclerView recyclerView,Context context,List<Books> books,List<String> keys)
    {
        mContext=context;
        mBooksAdapter = new BookAdapter(books,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mBooksAdapter);
    }
    class BookItemView extends RecyclerView.ViewHolder
    {
        private TextView mTitle;
        private TextView mAuthor;
        private TextView mIsbn;
        private TextView mCategory;

        private String key;

        public BookItemView(ViewGroup parent)
        {
            super(LayoutInflater.from(mContext).
            inflate(R.layout.time_table_list,parent,false));

            mTitle = (TextView)itemView.findViewById(R.id.title_textview);
            mCategory= (TextView)itemView.findViewById(R.id.category_textview);
            mAuthor= (TextView)itemView.findViewById(R.id.author_textview);
            mIsbn = (TextView)itemView.findViewById(R.id.isbn_textview);


        }

        public void bind(Books book,String key)
        {
            mTitle.setText(book.getTitle());
            mAuthor.setText(book.getAuthor());
            mCategory.setText(book.getCategory_name());
            mIsbn.setText(book.getIsbn());

            this.key=key;
        }
    }//eclassBookItemView

    class BookAdapter extends RecyclerView.Adapter<BookItemView>
    {
        private List<Books> mBookList;
        private List<String> mKeys;

        public BookAdapter(List<Books> mBookList, List<String> mKeys) {
            this.mBookList = mBookList;
            this.mKeys = mKeys;
        }


        @Override
        public BookItemView onCreateViewHolder(ViewGroup parent, int i) {
            return new BookItemView(parent);
        }

        @Override
        public void onBindViewHolder(BookItemView holder, int position) {
            holder.bind(mBookList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }
    }
}
