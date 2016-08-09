package com.begentgroup.samplenetwork.autodata;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.begentgroup.samplenetwork.R;
import com.begentgroup.samplenetwork.manager.ImageRequest;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-08-05.
 */
public class ProductView extends FrameLayout {
    public ProductView(Context context) {
        this(context, null);
    }

    @BindView(R.id.image_thumbnail)
    ImageView thumbView;
    @BindView(R.id.text_name)
    TextView nameView;
    @BindView(R.id.text_like)
    TextView likeView;
    @BindView(R.id.text_download)
    TextView downloadView;
    @BindView(R.id.text_detailDescription)
    TextView descriptionView;

    public ProductView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_product, this);
        ButterKnife.bind(this);
    }

    Product product;
    ImageRequest request;
    public void setProduct(Product product) {
        this.product = product;
        nameView.setText(product.getName());
        likeView.setText("" + product.getScore());
        downloadView.setText("" + product.getDownloadCount());
        descriptionView.setText("" + product.getDescription());
        Picasso.with(getContext())
                .load(product.getThumbnailUrl())
                .placeholder(R.drawable.ic_stub)
                .error(R.drawable.ic_error)
                .into(thumbView);

//        thumbView.setImageURL(product.getThumbnailUrl());
//        if (request != null) {
//            request.setCancel(true);
//            request = null;
//        }
//        if (!TextUtils.isEmpty(product.getThumbnailUrl())) {
//            request = new ImageRequest(product.getThumbnailUrl());
//            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<Bitmap>() {
//                @Override
//                public void onSuccess(NetworkRequest<Bitmap> request, Bitmap result) {
//                    thumbView.setImageBitmap(result);
//                    ProductView.this.request = null;
//                }
//
//                @Override
//                public void onFail(NetworkRequest<Bitmap> request, int errorCode, String errorMessage) {
//                    ProductView.this.request = null;
//                    thumbView.setImageResource(R.drawable.ic_error);
//                }
//            });
//            thumbView.setImageResource(R.drawable.ic_stub);
//        } else {
//            thumbView.setImageResource(R.drawable.ic_empty);
//        }
    }
}
