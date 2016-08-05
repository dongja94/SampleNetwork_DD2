package com.begentgroup.samplenetwork;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.begentgroup.samplenetwork.autodata.Product;
import com.begentgroup.samplenetwork.autodata.TStore;
import com.begentgroup.samplenetwork.manager.NetworkManager;
import com.begentgroup.samplenetwork.manager.NetworkRequest;
import com.begentgroup.samplenetwork.manager.TStoreSearchRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class TStoreManagerActivity extends AppCompatActivity {


    @BindView(R.id.edit_input)
    EditText keywordView;

    @BindView(R.id.list_tstore)
    ListView listView;

    //    ArrayAdapter<Product> mAdapter;
    ProductAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tstore);

        ButterKnife.bind(this);

        mAdapter = new ProductAdapter();
        listView.setAdapter(mAdapter);


    }

    @OnClick(R.id.btn_search)
    public void onSerach(View view) {
        String keyword = keywordView.getText().toString();
        if (!TextUtils.isEmpty(keyword)) {
            TStoreSearchRequest request = new TStoreSearchRequest(keyword);
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<TStore>() {
                @Override
                public void onSuccess(NetworkRequest<TStore> request, TStore result) {
                    mAdapter.addAll(result.getProducts().getProductList());
                }

                @Override
                public void onFail(NetworkRequest<TStore> request, int errorCode, String errorMessage) {
                    Toast.makeText(TStoreManagerActivity.this, "fail", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @OnItemClick(R.id.list_tstore)
    public void onProductItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product p = (Product)listView.getItemAtPosition(position);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(p.getTinyUrl()));
        startActivity(intent);
    }

}
