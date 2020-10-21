package com.example.news_vk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sfedymob.R;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class TextAndImageAdapter extends BaseAdapter {
    private Context context;
    private List<ListItem> postsList;
    public TextAndImageAdapter(Context context, List<ListItem> postsList) {
        this.context = context;
        this.postsList = postsList;
    }

    @Override
    public int getCount() {
        return postsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {

            grid = new View(context);
            grid = inflater.inflate(R.layout.news_item, null);
            TextView textView = grid.findViewById(R.id.news_text_field);
            Button button = grid.findViewById(R.id.news_read_more_button);

            if(postsList.get(position).text_tv.equals("")) {
                textView.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
            } else {
                textView.setText(postsList.get(position).text_tv);
<<<<<<< HEAD
                button.setOnClickListener(v ->{
                    textView.setMaxLines(Integer.MAX_VALUE );
=======
                button.setOnClickListener(v -> {
                    textView.setMaxLines(postsList.get(position).text_tv.length());
>>>>>>> 3c8f72f... Последние обновы
                    button.setVisibility(View.GONE);
                });
            }

            ImageView imageView = grid.findViewById(R.id.news_image_field);

            if (postsList.get(position).uri_iv.equals("")) {

            } else {
                Glide.with(context)
                        .load(postsList.get(position).uri_iv)
                        .centerCrop()
                        .into(imageView);
            }

        } else {
            grid = convertView;
        }
        return grid;
    }
}
