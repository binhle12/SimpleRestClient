package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by taq on 29/10/2016.
 */

public class TweetsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Tweet> mTweets;

    public TweetsAdapter(Context context) {
        mContext = context;
        mTweets = new ArrayList<>();
    }

    public Context getContext() {
        return mContext;
    }

    public void setTweets(List<Tweet> tweets) {
        mTweets.clear();
        mTweets.addAll(tweets);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Tweet tweet = mTweets.get(position);
        ((ViewHolder) holder).bindData(tweet);
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivProfileImage)
        ImageView ivProfileImage;

        @BindView(R.id.tvUserName)
        TextView tvUserName;

        @BindView(R.id.tvNameTag)
        TextView tvNameTag;

        @BindView(R.id.tvContent)
        TextView tvContent;

        @BindView(R.id.tvRetweet)
        TextView tvRetweet;

        @BindView(R.id.tvFavorite)
        TextView tvFavorite;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(Tweet tweet) {
            tvUserName.setText(tweet.getUser().getName());
            tvContent.setText(tweet.getText());
            tvRetweet.setText(String.valueOf(tweet.getRetweet()));
            tvFavorite.setText(String.valueOf(tweet.getFavorite()));
            final Context context = ivProfileImage.getContext();
            Glide.with(context)
                    .load(tweet.getUser().getBiggerImageUrl())
                    .asBitmap()
                    .into(new BitmapImageViewTarget(ivProfileImage) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable bitmap = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            bitmap.setCornerRadius(5f);
                            ivProfileImage.setImageDrawable(bitmap);
                        }
                    });
        }
    }
}