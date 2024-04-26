package my.food.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;

import my.food.foodapp.Domain.Foods;
import my.food.foodapp.Helper.ManagmentCart;
import my.food.foodapp.R;
import my.food.foodapp.databinding.ActivityDetailBinding;

public class DetailActivity extends BaseActivity {

    ActivityDetailBinding binding;
    private Foods object;
    private int num=1;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));

        getIntentExtra();
        setVariable();
    }

    private void setVariable() {
        managmentCart=new ManagmentCart(this);
        binding.backBtnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Glide.with(DetailActivity.this)
                .load(object.getImagePath())
                .into(binding.pic);

        binding.priceTxt.setText("€"+object.getPrice());
        binding.titleTxt.setText(object.getTitle());
        binding.descriptionTxt.setText(object.getDescription());
        binding.rateTxt.setText(object.getStar() + "Rating");
        binding.ratingBar.setRating((float) object.getStar());
        binding.totalTxt.setText((num*object.getPrice() + "€"));

        binding.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num=num+1;
                binding.numTxt.setText(num+" ");
                binding.totalTxt.setText("€"+(num*object.getPrice()));
            }
        });

        binding.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num>1){
                    num=num-1;
                    binding.numTxt.setText(num+"");
                    binding.totalTxt.setText("€"+(num*object.getPrice()));
                }
            }
        });

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(num);
                managmentCart.insertFood(object);
            }
        });
    }

    private void getIntentExtra() {
        object = (Foods) getIntent().getSerializableExtra("object");
    }
}