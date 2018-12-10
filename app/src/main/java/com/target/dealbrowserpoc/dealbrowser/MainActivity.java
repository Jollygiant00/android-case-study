package com.target.dealbrowserpoc.dealbrowser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.target.dealbrowserpoc.dealbrowser.deals.DealItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class MainActivity extends Activity implements DealsAdapter.OnDealClickListener {
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DESC = "description";
    public static final String EXTRA_PRICE = "originalPrice";
    public static final String EXTRA_SALE = "salePrice";
    public static final String EXTRA_URL = "image";

    private RecyclerView mRecyclerView;
    private ArrayList<DealItem> mDealList;
    private DealsAdapter mDealsAdapter;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.deal_view_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDealList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        loadUrlData();
    }

    private void loadUrlData() {
        String url = "http://target-deals.herokuapp.com/api/deals";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject deal = jsonArray.getJSONObject(i);

                        String dealTitle = deal.getString("title");
                        String dealPrice = deal.getString("price");
                        String dealAisle = deal.getString("aisle");
                        String dealImage = deal.getString("image");
                        String dealDesc = deal.getString("description");
                        String dealSalePrice = deal.getString("salePrice");
                        int dealIndex = deal.getInt("index");
                        String dealId = deal.getString("_id");



                        mDealList.add(new DealItem(dealIndex,
                                dealId,
                                dealTitle,
                                dealDesc,
                                dealPrice,
                                dealSalePrice,
                                dealImage,
                                dealAisle));
                    }

                    mDealsAdapter = new DealsAdapter(MainActivity.this, mDealList);
                    mRecyclerView.setAdapter(mDealsAdapter);
                    mDealsAdapter.setOnClickListener(MainActivity.this);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("DealContent", "Problem parsing JSON results");
            }
        });

        mRequestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, ItemDetail.class);
        DealItem clickedItem = mDealList.get(position);

        detailIntent.putExtra(EXTRA_TITLE, clickedItem.getTitle());
        detailIntent.putExtra(EXTRA_DESC, clickedItem.getDescription());
        detailIntent.putExtra(EXTRA_PRICE, clickedItem.getOriginalPrice());
        detailIntent.putExtra(EXTRA_URL, clickedItem.getProductImage());

        if(clickedItem.getSalePrice() != null) {
            detailIntent.putExtra(EXTRA_SALE, clickedItem.getSalePrice());
        }

        startActivity(detailIntent);
    }
}
