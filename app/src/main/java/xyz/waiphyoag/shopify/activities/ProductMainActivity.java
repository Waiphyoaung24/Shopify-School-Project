package xyz.waiphyoag.shopify.activities;

import android.content.Intent;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.adapters.HomePageAdapter;
import xyz.waiphyoag.shopify.adapters.ProductInDesignerAdapter;
import xyz.waiphyoag.shopify.adapters.ProductInTopTrendsAdapter;
import xyz.waiphyoag.shopify.adapters.PromotionItemViewPager;
import xyz.waiphyoag.shopify.adapters.RandomItemsAdapter;
import xyz.waiphyoag.shopify.data.model.ProductModel;
import xyz.waiphyoag.shopify.data.vo.SharedParent;
import xyz.waiphyoag.shopify.data.vo.ShopNowVO;
import xyz.waiphyoag.shopify.data.vo.ShopifyVO;
import xyz.waiphyoag.shopify.delegates.ProductMainScreenDelegate;
import xyz.waiphyoag.shopify.events.LoadProductListEvent;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

public class ProductMainActivity extends AppCompatActivity implements ProductMainScreenDelegate {


    @BindView(R.id.tabDots)
    TabLayout tabLayout;
    @BindView(R.id.vp_promotion_item)
    ViewPager vpPromotionItem;
    @BindView(R.id.rv_HomeScreen)
    RecyclerView rvHomeScreen;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.btn_explore)
    Button btnExplore;


    public List<SharedParent> mShopifyList = new ArrayList<>();

    public List<SharedParent> getmShopifyList() {
        return mShopifyList;
    }


    private HomePageAdapter mHomePageAdapter;
    private PromotionItemViewPager mPromotionItemViewPager;

    public static Intent mainIntent(Context context) {
        Intent intent = new Intent(context, ProductMainActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_main);
        getSupportActionBar().hide();
        ButterKnife.bind(this, this);


        mPromotionItemViewPager = new PromotionItemViewPager();
        vpPromotionItem.setAdapter(mPromotionItemViewPager);
        tabLayout.setupWithViewPager(vpPromotionItem, true);


        mHomePageAdapter = new HomePageAdapter(this, this);
        LinearLayoutManager linearLayoutManagerforHomePage = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvHomeScreen.setAdapter(mHomePageAdapter);
        rvHomeScreen.setLayoutManager(linearLayoutManagerforHomePage);


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
                        Toast.makeText(getApplicationContext(), "This is already a home screen", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.item_shop:
                        Intent intentForShop = ProductListActivity.listIntent(getApplicationContext());
                        startActivity(intentForShop);


                        break;

                    case R.id.item_cart:
                        Intent intentforCart = AddToCartActivity.cartIntent(getApplicationContext());
                        startActivity(intentforCart);

                        break;

//                    case R.id.item_profile :
//                        break;
                }

                return true;
            }
        });

        btnExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ProductListActivity.listIntent(getApplicationContext());
                startActivity(intent);
            }
        });


        ProductModel.getInstance().startLoadingTopTrendsProduct();
        ProductModel.getInstance().startLoadingRandomThingsProduct();

        ProductModel.getInstance().startLoadingDesignerProduct();




        ProductModel.getInstance().startLoadingPromotionProduct();


//


    }


    @Override
    public void onTapItem() {
        Intent intent = ProductDetailActivity.detailIntent(getApplicationContext());
        startActivity(intent);

    }

    @Override
    public void onTapSeeMore() {

    }

    @Override
    public void onTapRandom() {
        Intent intent = ProductListActivity.listIntent(getApplicationContext());
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }



    @Subscribe
    public void onLoadDesignerProdut(LoadProductListEvent.LoadDesignerProduct designerProduct) {
        mShopifyList.addAll(designerProduct.getLoadDeisngerProduct());

    }

    @Subscribe
    public void onLoadTopTrends(LoadProductListEvent.loadTopTrendsProduct topTrendsProduct) {
        mShopifyList.addAll(topTrendsProduct.getLoadTopTrendsList());



    }

    @Subscribe
    public void onLoadsRandomthings(LoadProductListEvent.loadRandomThingsProduct randomThingsProduct) {
        mShopifyList.addAll(randomThingsProduct.getLoadRandomThingsList());
        mHomePageAdapter.setNewData(mShopifyList);



    }
}



