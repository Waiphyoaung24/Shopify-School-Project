package xyz.waiphyoag.shopify.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.data.vo.SampleVo;
import xyz.waiphyoag.shopify.data.vo.ShopNowVO;
import xyz.waiphyoag.shopify.delegates.ProductListScreenDelegate;

/**
 * Created by WaiPhyoAg on 8/31/19.
 */

public class ItemInProductListViewHolder extends BaseViewHolder<ShopNowVO> {

    @BindView(R.id.tv_proudct_name)
    TextView tvProductName;

    private ProductListScreenDelegate mDelegate;


    public ItemInProductListViewHolder(View itemView,ProductListScreenDelegate productListScreenDelegate) {
        super(itemView);
        mDelegate = productListScreenDelegate;

    }


    @Override
    public void onClick(View v) {
        mDelegate.onTapProductListImage();
    }


    @Override
    public void setData(ShopNowVO data) {

        tvProductName.setText(data.getProductTitle());


    }
}
