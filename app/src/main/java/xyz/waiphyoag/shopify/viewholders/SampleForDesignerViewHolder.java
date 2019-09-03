package xyz.waiphyoag.shopify.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import xyz.waiphyoag.shopify.data.vo.SampleVo;
import xyz.waiphyoag.shopify.delegates.ProductMainScreenDelegate;

/**
 * Created by WaiPhyoAg on 8/31/19.
 */

public class SampleForDesignerViewHolder extends BaseViewHolder<SampleVo> {
    private ProductMainScreenDelegate mDelegate;
    public SampleForDesignerViewHolder(View itemView, ProductMainScreenDelegate productMainScreenDelegate) {
        super(itemView);
        mDelegate = productMainScreenDelegate;
    }

    @Override
    public void setData(SampleVo data) {

    }

    @Override
    public void onClick(View v) {
        mDelegate.onTapItem();

    }
}
