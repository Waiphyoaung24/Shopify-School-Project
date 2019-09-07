package xyz.waiphyoag.shopify.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import javax.microedition.khronos.opengles.GL;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.data.model.ProductModel;
import xyz.waiphyoag.shopify.data.vo.DesignerVO;
import xyz.waiphyoag.shopify.data.vo.ShopNowVO;
import xyz.waiphyoag.shopify.data.vo.TopTrendsVO;

/**
 * Created by WaiPhyoAg on 9/1/19.
 */

public class ProductDetailActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    FrameLayout ivBack;
    @BindView(R.id.iv_product_image)
    ImageView ivProductDetail;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;
    @BindView(R.id.tv_price_tag)
    TextView tvPrice;
    @BindView(R.id.btn_white)
    Button btnWhite;
    @BindView(R.id.btnBlack)
    Button btnBlack;
    @BindView(R.id.btnGray)
    Button btnGray;

    @BindView(R.id.iv_Love)
    ImageView ivLove;
    @BindView(R.id.btn_addToCart)
    Button btnAddtoCart;
    @BindView(R.id.btn_explore)
    Button btnExplore;

    public static Intent detailIntentForList(Context context, String productId) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra("productId", productId);
        intent.putExtra("detailType", 0);
        return intent;
    }

    public static Intent detailIntentForDesigner(Context context, String productId) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra("productId", productId);
        intent.putExtra("detailType", 1);

        return intent;
    }
    public static Intent detailIntentForTopTrends(Context context, String productId) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra("productId", productId);
        intent.putExtra("detailType", 2);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this, this);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ProductListActivity.listIntent(getApplicationContext());
                startActivity(intent);
            }
        });

        int detailType = getIntent().getIntExtra("detailType", -1);

        if (detailType == 0) {
            String productId = getIntent().getStringExtra("productId");
            ShopNowVO shopNowVO = ProductModel.getInstance().getProductListDetilById(productId);
            getProductDetailByListScreen(shopNowVO);

        } else if (detailType == 1) {
            String productId = getIntent().getStringExtra("productId");
            DesignerVO designerVO = ProductModel.getInstance().getProductListDetilByIdForDesigner(productId);
            getProductDetailByDesignerItem(designerVO);

        }
        else if (detailType == 2) {
            String productId = getIntent().getStringExtra("productId");
            TopTrendsVO topTrendsVO = ProductModel.getInstance().getProductListDetilByIdForTopTrends(productId);
            getProductDetailByTopTrendsItem(topTrendsVO);

        }

        ivLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivLove.setImageDrawable(getDrawable(R.drawable.hearts_filled));




            }
        });






    }

    public void getProductDetailByListScreen(final ShopNowVO shopNowVO) {


        Glide.with(getApplicationContext())
                .load(shopNowVO.getProductImage())
                .into(ivProductDetail);

        tvProductName.setText(shopNowVO.getProductTitle());
        tvPrice.setText(shopNowVO.getProductPrice());

        btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentForAddToCart = AddToCartActivity.cartIntentForList(getApplicationContext(), shopNowVO.getProductId());
                startActivity(intentForAddToCart);
            }
        });


    }

    public void getProductDetailByDesignerItem(final DesignerVO designerVO) {


        Glide.with(getApplicationContext())
                .load(designerVO.getProductImage())
                .into(ivProductDetail);
        tvProductName.setText(designerVO.getProductTitle());
        tvPrice.setText(designerVO.getProductPrice());

        btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentForAddToCart = AddToCartActivity.cartIntentForDesigner(getApplicationContext(), designerVO.getProductId());
                startActivity(intentForAddToCart);
            }
        });
    }

        public void getProductDetailByTopTrendsItem(final TopTrendsVO topTrendsVO) {


            Glide.with(getApplicationContext())
                    .load(topTrendsVO.getProductImage())
                    .into(ivProductDetail);
            tvProductName.setText(topTrendsVO.getProductTitle());
            tvPrice.setText(topTrendsVO.getProductPrice());

            btnAddtoCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intentForAddToCart = AddToCartActivity.cartIntentForTopTrends(getApplicationContext(), topTrendsVO.getProductId());
                    startActivity(intentForAddToCart);
                }
            });

        }
}
