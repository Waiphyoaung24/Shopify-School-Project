package xyz.waiphyoag.shopify.viewholders;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.adapters.ProductInDesignerAdapter;
import xyz.waiphyoag.shopify.data.vo.SampleVo;
import xyz.waiphyoag.shopify.delegates.ProductMainScreenDelegate;


/**
 * Created by WaiPhyoAg on 8/30/19.
 */

public class ItemInDesginerViewHolder extends BaseViewHolder<SampleVo> {
   @BindView(R.id.rv_designer_product)
   RecyclerView rvDesignerItems;
    private ProductInDesignerAdapter mDesignerAdapter;
    private ProductMainScreenDelegate mDelegate;

    public ItemInDesginerViewHolder(View itemView, ProductMainScreenDelegate productMainScreenDelegate) {
        super(itemView);
        ButterKnife.bind(this,itemView);

        mDelegate = productMainScreenDelegate;

        mDesignerAdapter = new ProductInDesignerAdapter(itemView.getContext(),mDelegate);
        LinearLayoutManager linearLayoutManagerForDesigner = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvDesignerItems.setAdapter(mDesignerAdapter);
        rvDesignerItems.setLayoutManager(linearLayoutManagerForDesigner);

    }

    @Override
    public void setData(SampleVo data) {

    }

    @Override
    public void onClick(View v) {


    }
}
