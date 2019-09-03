package xyz.waiphyoag.shopify.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.data.vo.DesignerVO;
import xyz.waiphyoag.shopify.data.vo.RandomThingsVO;
import xyz.waiphyoag.shopify.data.vo.SampleVo;
import xyz.waiphyoag.shopify.data.vo.SharedParent;
import xyz.waiphyoag.shopify.data.vo.TopTrendsVO;
import xyz.waiphyoag.shopify.delegates.ProductMainScreenDelegate;
import xyz.waiphyoag.shopify.viewholders.BaseViewHolder;
import xyz.waiphyoag.shopify.viewholders.ItemInDesginerViewHolder;
import xyz.waiphyoag.shopify.viewholders.ItemInRandomViewHolder;
import xyz.waiphyoag.shopify.viewholders.ItemInTopTrendsViewHolder;

/**
 * Created by WaiPhyoAg on 8/31/19.
 */

public class HomePageAdapter extends BaseRecyclerAdapter<BaseViewHolder, SharedParent> {

    private static final int VT_DESIGNER = 0;
    private static final int VT_TOPTRENDS = 1;
    private static final int VT_RANDOMTHINGS = 2;

    private ProductMainScreenDelegate mDelegate;

    public HomePageAdapter(Context context, ProductMainScreenDelegate productMainScreenDelegate) {
        super(context);
        mDelegate = productMainScreenDelegate;

    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        BaseViewHolder viewHolder = null;
        switch (viewType) {
            case VT_DESIGNER:
                viewHolder = new ItemInDesginerViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_first_content, parent, false), mDelegate);
                break;
            case VT_TOPTRENDS:
                viewHolder = new ItemInTopTrendsViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_second_content, parent, false), mDelegate);
                break;
            case VT_RANDOMTHINGS:
                viewHolder = new ItemInRandomViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_random_things, parent, false), mDelegate);
                break;


        }
        return viewHolder;

    }



    @Override
    public int getItemViewType(int position) {
        if (mData.get(position) instanceof DesignerVO) {
            return VT_DESIGNER;
        } else if (mData.get(position) instanceof TopTrendsVO) {
            return VT_TOPTRENDS;
        } else if (mData.get(position) instanceof RandomThingsVO) {
            return VT_RANDOMTHINGS;


        }
        return position;
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }
}
