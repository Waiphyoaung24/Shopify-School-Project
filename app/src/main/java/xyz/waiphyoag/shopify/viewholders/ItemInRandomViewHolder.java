package xyz.waiphyoag.shopify.viewholders;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.adapters.RandomItemsAdapter;
import xyz.waiphyoag.shopify.data.vo.SampleVo;
import xyz.waiphyoag.shopify.delegates.ProductMainScreenDelegate;

/**
 * Created by WaiPhyoAg on 8/31/19.
 */

public class ItemInRandomViewHolder extends BaseViewHolder<SampleVo> {

    @BindView(R.id.rv_RandomThings)
    RecyclerView rvRandomItems;

    private RandomItemsAdapter mRandomItemAdapter;
    private ProductMainScreenDelegate mDelegate;

    public ItemInRandomViewHolder(View itemView, ProductMainScreenDelegate productMainScreenDelegate) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        mDelegate = productMainScreenDelegate;


        mRandomItemAdapter = new RandomItemsAdapter(itemView.getContext(),mDelegate);
        LinearLayoutManager linearLayoutManagerForRandomItems = new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.VERTICAL,false);
        rvRandomItems.setLayoutManager(linearLayoutManagerForRandomItems);
        rvRandomItems.setAdapter(mRandomItemAdapter);
    }

    @Override
    public void setData(SampleVo data) {

    }

    @Override
    public void onClick(View v) {
        mDelegate.onTapRandom();

    }
}
