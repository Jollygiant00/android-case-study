package com.target.dealbrowserpoc.dealbrowser;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import com.squareup.picasso.Picasso;
import com.target.dealbrowserpoc.dealbrowser.deals.DealItem;

public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<DealItem> mDealsList;
    private OnDealClickListener mListener;

    public interface OnDealClickListener {
        void onItemClick(int position);
    }

    public void setOnClickListener(OnDealClickListener listener) {
        mListener = listener;
    }

    public DealsAdapter(Context context, ArrayList<DealItem> dealsList) {
        this.mDealsList = dealsList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.deal_list_item, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        DealItem currentItem = mDealsList.get(position);

        String title = currentItem.getTitle();
        String originalPrice = currentItem.getOriginalPrice();
        String aisle = currentItem.getAisle();
        String image = currentItem.getProductImage();

        holder.mTitleView.setText(title);
        holder.mPriceView.setText(originalPrice);
        holder.mAisle.setText(aisle);

        Picasso.with(mContext)
                .load(image)
                .fit().centerInside()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mDealsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTitleView;
        private TextView mPriceView;
        private TextView mAisle;

        public ViewHolder(View view) {
            super(view);

            mTitleView = (TextView) view.findViewById(R.id.tv_deal_list_title);
            mPriceView = (TextView) view.findViewById(R.id.tv_deal_list_price);
            mAisle = (TextView) view.findViewById(R.id.tv_deal_list_aisle);
            mImageView = (ImageView) view.findViewById(R.id.iv_deal_list_image);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
