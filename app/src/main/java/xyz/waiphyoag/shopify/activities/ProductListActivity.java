package xyz.waiphyoag.shopify.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.adapters.ProductListAdapter;
import xyz.waiphyoag.shopify.data.model.ProductModel;
import xyz.waiphyoag.shopify.data.vo.ShopNowVO;
import xyz.waiphyoag.shopify.delegates.ProductListScreenDelegate;
import xyz.waiphyoag.shopify.events.LoadShopNowListEvent;
import xyz.waiphyoag.shopify.fragments.SuccessOrderFragment;
import xyz.waiphyoag.shopify.fragments.UserProfile;

/**
 * Created by WaiPhyoAg on 8/31/19.
 */

public class ProductListActivity extends BaseActivity implements ProductListScreenDelegate {


    @BindView(R.id.rv_product_list)
    RecyclerView rvProductList;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.ivProfile)
    ImageView ivProfile;

    @BindView(R.id.iv_shopping_cart)
    ImageView ivAddToCart;
    List<ShopNowVO> shopNowVOList = new ArrayList<>();


    private ProductListAdapter mAdapter;

    public static Intent listIntent(Context context) {
        Intent intent = new Intent(context, ProductListActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this, this);


        mAdapter = new ProductListAdapter(this, this);
        GridLayoutManager gridLayoutManagerForProductList = new GridLayoutManager(getApplicationContext(), 2);
        rvProductList.setAdapter(mAdapter);
        rvProductList.setLayoutManager(gridLayoutManagerForProductList);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch (id) {
                    case R.id.item_home:

                        Intent intentForHome = ProductMainActivity.mainIntent(getApplicationContext());
                        startActivity(intentForHome);
                        finish();


                        break;
                    case R.id.item_shop:

                        Toast.makeText(getApplicationContext(), "This is already a Shop List", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.item_cart:

                        Intent intentForCart = AddToCartActivity.cartIntentForNoItem(getApplicationContext());
                        startActivity(intentForCart);
                        finish();

                        break;


                }

                return true;
            }
        });


        ivAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AddToCartActivity.cartIntentForNoItem(getApplicationContext());
                startActivity(intent);
            }
        });

        ProductModel.getInstance().startLoadingShopNowProduct();



        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.pop_enter,R.anim.exit,R.anim.enter, R.anim.pop_exit)
                        .replace(R.id.rl_top_screen, UserProfile.newInstance())
                        .addToBackStack(null)
                        .commit();


            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }


    }


    @Override
    public void onStop() {
        super.onStop();

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataLoadedShopNow(LoadShopNowListEvent event) {


        if (!event.getShopNowVOList().isEmpty()) {

            mAdapter.setNewData(event.getShopNowVOList());
        }


    }


    @Override
    public void onTapProductListImage(String productId) {

        Intent intentForDetail = ProductDetailActivity.detailIntentForList(getApplicationContext(), productId);
        startActivity(intentForDetail);

    }
}
