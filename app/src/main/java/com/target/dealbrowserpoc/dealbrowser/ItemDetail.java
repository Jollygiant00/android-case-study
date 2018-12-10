package com.target.dealbrowserpoc.dealbrowser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import static com.target.dealbrowserpoc.dealbrowser.MainActivity.EXTRA_DESC;
import static com.target.dealbrowserpoc.dealbrowser.MainActivity.EXTRA_PRICE;
import static com.target.dealbrowserpoc.dealbrowser.MainActivity.EXTRA_SALE;
import static com.target.dealbrowserpoc.dealbrowser.MainActivity.EXTRA_TITLE;
import static com.target.dealbrowserpoc.dealbrowser.MainActivity.EXTRA_URL;

public class ItemDetail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail_view);

        //Handle incoming intent
        Intent intent = getIntent();
        String title = intent.getStringExtra(EXTRA_TITLE);
        String description = intent.getStringExtra(EXTRA_DESC);
        String price = intent.getStringExtra(EXTRA_PRICE);
        String salePrice = intent.getStringExtra(EXTRA_SALE);
        String image = intent.getStringExtra(EXTRA_URL);

        TextView textViewTitle = (TextView) findViewById(R.id.tv_detail_view_title);
        TextView textViewDesc = (TextView) findViewById(R.id.tv_detail_view_desc);
        TextView textViewPrice = (TextView) findViewById(R.id.tv_deal_list_price);
        TextView textViewSale = (TextView) findViewById(R.id.tv_detail_view_sale);
        ImageView imageView = (ImageView) findViewById(R.id.iv_detail_view_image);

        Picasso.with(this).load(image).centerInside().into(imageView);

        textViewTitle.setText(title);
        textViewDesc.setText(description);


        if(salePrice.equals("null")) {
            textViewPrice.setText(price);
            textViewSale.setText("");
        } else {
            textViewPrice.setText(salePrice);
            textViewSale.setText(price);
        }

    }
}
