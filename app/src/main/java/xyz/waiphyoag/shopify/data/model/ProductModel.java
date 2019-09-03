package xyz.waiphyoag.shopify.data.model;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import xyz.waiphyoag.shopify.ShopifyApplication;
import xyz.waiphyoag.shopify.data.vo.DesignerVO;
import xyz.waiphyoag.shopify.data.vo.PromotionVO;
import xyz.waiphyoag.shopify.data.vo.RandomThingsVO;
import xyz.waiphyoag.shopify.data.vo.SharedParent;
import xyz.waiphyoag.shopify.data.vo.ShopNowVO;
import xyz.waiphyoag.shopify.data.vo.ShopifyVO;
import xyz.waiphyoag.shopify.data.vo.TopTrendsVO;
import xyz.waiphyoag.shopify.events.LoadProductListEvent;
import xyz.waiphyoag.shopify.events.LoadShopNowListEvent;

/**
 * Created by WaiPhyoAg on 9/2/19.
 */

public class ProductModel {
    private static final String SHOPIFY = "Shopify";


    public ArrayList<DesignerVO> designerVOS = new ArrayList<>();
    public ArrayList<TopTrendsVO> topTrendsVo = new ArrayList<>();
    public ArrayList<RandomThingsVO> randomThingsVOS = new ArrayList<>();
    public ArrayList<ShopNowVO> shopNowVOS = new ArrayList<>();
    public ArrayList<PromotionVO> promotionVOS = new ArrayList<>();


    public List<ShopNowVO> shopNowVOList = new ArrayList<>();

    public List<ShopNowVO> getShopNowVOList() {
        return shopNowVOList;
    }

    public List<SharedParent> mCollectionList;

    public List<SharedParent> getCollectionList() {
        return mCollectionList;
    }

    private static ProductModel mInstance;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mFirebaseUser;
    private FirebaseAuth mFirebaseAuth;


    public ProductModel() {

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        mCollectionList = new ArrayList<>();


    }

    public static ProductModel getInstance() {
        if (mInstance == null) {
            mInstance = new ProductModel();
        }

        return mInstance;
    }


    public void startLoadingDesignerProduct() {
        DatabaseReference shopifyNodeDBR = mDatabaseReference.child(SHOPIFY).child("Designer");


        shopifyNodeDBR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot != null) {
                    for (DataSnapshot products : dataSnapshot.getChildren()) {
                        DesignerVO designerVO = products.getValue(DesignerVO.class);
                        designerVOS.add(designerVO);
                    }
                    mCollectionList.addAll(designerVOS);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void startLoadingTopTrendsProduct() {
        DatabaseReference shopifyNodeDBR = mDatabaseReference.child(SHOPIFY).child("TopTrend");


        shopifyNodeDBR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot != null) {
                    for (DataSnapshot products : dataSnapshot.getChildren()) {
                        TopTrendsVO topTrendsVO = products.getValue(TopTrendsVO.class);
                        topTrendsVo.add(topTrendsVO);
                    }

                    mCollectionList.addAll(topTrendsVo);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void startLoadingShopNowProduct() {
        DatabaseReference shopifyNodeDBR = mDatabaseReference.child(SHOPIFY).child("ShopNow");


        shopifyNodeDBR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot != null) {
                    for (DataSnapshot products : dataSnapshot.getChildren()) {
                        ShopNowVO shopNowVO = products.getValue(ShopNowVO.class);
                        shopNowVOS.add(shopNowVO);
                    }
                    shopNowVOList.addAll(shopNowVOS);
                    LoadShopNowListEvent loadShopNowListEvent = new LoadShopNowListEvent(shopNowVOList);
                    EventBus.getDefault().post(loadShopNowListEvent);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




    public void startLoadingRandomThingsProduct() {
        DatabaseReference shopifyNodeDBR = mDatabaseReference.child(SHOPIFY).child("RandomThings");


        shopifyNodeDBR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot != null) {
                    for (DataSnapshot products : dataSnapshot.getChildren()) {
                        RandomThingsVO randomThingsVO = products.getValue(RandomThingsVO.class);
                        randomThingsVOS.add(randomThingsVO);
                    }
                    mCollectionList.addAll(randomThingsVOS);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void startLoadingPromotionProduct() {
        DatabaseReference shopifyNodeDBR = mDatabaseReference.child(SHOPIFY).child("Promotion");


        shopifyNodeDBR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot != null) {
                    for (DataSnapshot products : dataSnapshot.getChildren()) {
                        PromotionVO promotionVO = products.getValue(PromotionVO.class);
                        promotionVOS.add(promotionVO);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
