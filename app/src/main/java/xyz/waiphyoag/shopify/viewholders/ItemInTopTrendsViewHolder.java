package xyz.waiphyoag.shopify.viewholders;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.adapters.ProductInTopTrendsAdapter;
import xyz.waiphyoag.shopify.data.vo.SampleVo;
import xyz.waiphyoag.shopify.data.vo.TopTrendsVO;
import xyz.waiphyoag.shopify.delegates.ProductMainScreenDelegate;

/**
 * Created by WaiPhyoAg on 8/30/19.
 */

public class ItemInTopTrendsViewHolder extends BaseViewHolder<TopTrendsVO>{
    @BindView(R.id.rv_TopTrends)
    RecyclerView rvTopTrendsItem;
    private ProductInTopTrendsAdapter mTopTrendsAdapter;

    private ProductMainScreenDelegate mDelegate;
    public ItemInTopTrendsViewHolder(View itemView, ProductMainScreenDelegate productMainScreenDelegate) {
        super(itemView);
        ButterKnife.bind(this,itemView);

        mDelegate = productMainScreenDelegate;

        mTopTrendsAdapter = new ProductInTopTrendsAdapter(itemView.getContext(),mDelegate);
        LinearLayoutManager linearLayoutManagerForTopTrends = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvTopTrendsItem.setAdapter(mTopTrendsAdapter);
        rvTopTrendsItem.setLayoutManager(linearLayoutManagerForTopTrends);
    }


    @Override
    public void onClick(View v) {
        mDelegate.onTapItem();

    }

    @Override
    public void setData(TopTrendsVO data) {

    }
}
