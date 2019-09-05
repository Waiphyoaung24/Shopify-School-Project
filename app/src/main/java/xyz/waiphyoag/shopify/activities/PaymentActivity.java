package xyz.waiphyoag.shopify.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.waiphyoag.shopify.R;
import xyz.waiphyoag.shopify.data.model.ProductModel;
import xyz.waiphyoag.shopify.data.vo.DesignerVO;
import xyz.waiphyoag.shopify.data.vo.PurchaseVO;
import xyz.waiphyoag.shopify.data.vo.ShopNowVO;
import xyz.waiphyoag.shopify.data.vo.UserVO;

/**
 * Created by WaiPhyoAg on 9/4/19.
 */

public class PaymentActivity extends AppCompatActivity {

    @BindView(R.id.tv_product_price)
    TextView tvProductPrice;
    @BindView(R.id.CashOnDelivery)
    LinearLayout linearLayout;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    @BindView(R.id.view)
    View view;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.tv_order)
    TextView tvOrderList;
    @BindView(R.id.btn_pay_now)
    Button btnPaynow;


    public static Intent paymentIntentForList(Context context, String productId) {
        Intent intent = new Intent(context, PaymentActivity.class);
        intent.putExtra("productId", productId);
        intent.putExtra("detailType", 0);
        return intent;
    }

    public static Intent paymentIntentForDesigner(Context context, String productId) {
        Intent intent = new Intent(context, PaymentActivity.class);
        intent.putExtra("productId", productId);
        intent.putExtra("detailType", 1);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this, this);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }


        int detailType = getIntent().getIntExtra("detailType", -1);

        if (detailType == 0) {
            String productId = getIntent().getStringExtra("productId");
            ShopNowVO shopNowVO = ProductModel.getInstance().getProductListDetilById(productId);
            String userName = ProductModel.getInstance().getUserId();
            getProductPaymentSectionByListScreen(shopNowVO);


            onTapBtnPaynow(userName, productId, etAddress.getText().toString());


        } else if (detailType == 1) {
            String productId = getIntent().getStringExtra("productId");
            DesignerVO designerVO = ProductModel.getInstance().getProductListDetilByIdForDesigner(productId);
            String userName = ProductModel.getInstance().getUserId();
            getProductPaymentSectionByDesignerScreen(designerVO);
            onTapBtnPaynow(userName, productId, etAddress.getText().toString());


        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                }
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    public void getProductPaymentSectionByListScreen(final ShopNowVO shopNowVO) {


        tvProductPrice.setText(shopNowVO.getProductPrice());

        tvOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    linearLayout.setBackgroundColor(getColor(R.color.secondary_text));
                }
                Toast.makeText(getApplicationContext(), "You have now been selceted for cash on delivery", Toast.LENGTH_SHORT).show();


            }
        });
    }

    public void getProductPaymentSectionByDesignerScreen(final DesignerVO designerVO) {


        tvProductPrice.setText(designerVO.getProductPrice());

        tvOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    linearLayout.setBackgroundColor(getColor(R.color.secondary_text));
                }
                Toast.makeText(getApplicationContext(), "You have now been selceted for cash on delivery", Toast.LENGTH_SHORT).show();


            }
        });

    }

    public void onTapBtnPaynow(final String userName, final String productId, final String address) {
        btnPaynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getApplicationContext(), "Your purchase has now been completed", Toast.LENGTH_SHORT).show();
                Intent intent = ProductMainActivity.mainIntent(getApplicationContext());
                startActivity(intent);

                ProductModel.getInstance().purchaseProduct(userName, productId, address);

            }
        });
    }
}
