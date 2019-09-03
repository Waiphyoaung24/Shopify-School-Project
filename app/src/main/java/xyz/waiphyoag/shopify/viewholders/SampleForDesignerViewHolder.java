package xyz.waiphyoag.shopify.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.data.vo.DesignerVO;
import xyz.waiphyoag.shopify.data.vo.SampleVo;
import xyz.waiphyoag.shopify.delegates.ProductMainScreenDelegate;

/**
 * Created by WaiPhyoAg on 8/31/19.
 */

public class SampleForDesignerViewHolder extends BaseViewHolder<DesignerVO> {
    @BindView(R.id.tv_first_content_item)
    TextView tvProductName;
    private ProductMainScreenDelegate mDelegate;
    public SampleForDesignerViewHolder(View itemView, ProductMainScreenDelegate productMainScreenDelegate) {
        super(itemView);
        mDelegate = productMainScreenDelegate;
    }



    @Override
    public void onClick(View v) {
        mDelegate.onTapItem();

    }

    @Override
    public void setData(DesignerVO data) {
        tvProductName.setText(data.getProductTitle());

    }
}
