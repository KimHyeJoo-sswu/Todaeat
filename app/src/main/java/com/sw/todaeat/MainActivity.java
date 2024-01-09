package com.sw.todaeat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText searchIngredient;
    private Button searchIcon;
    private Button showRecipe;
    private TextView showFridge;
    private DrawerLayout drawerLayout;
    private View drawerView;
    TextView textview2;
    String inputText;
    private Button openDrawer;

    private RecyclerView ingredient_category;

    private RecyclerViewAdapter_ingredientCategory adapter;

    ArrayList<ingredientsCategory> parentModelArrayList = new ArrayList<>();
    static ArrayList<ingredients> fridge = new ArrayList<>();

    RecyclerView.LayoutManager parentLayoutManager;

    public void openActivityForResult() {
        Intent intent = new Intent(this, Fridge.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ingredient_category = findViewById(R.id.ingredient_category);

        searchIngredient = findViewById(R.id.searchIngredient);
        searchIcon = findViewById(R.id.searchIcon);
        showRecipe = findViewById(R.id.showRecipe);
        showFridge = findViewById(R.id.showFridge);
        openDrawer = findViewById(R.id.openDrawer);

        adapter = new RecyclerViewAdapter_ingredientCategory(parentModelArrayList, MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        ingredient_category.setLayoutManager(layoutManager);
        ingredient_category.setAdapter(adapter);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (View) findViewById(R.id.drawerView);
        drawerLayout.removeDrawerListener(listener);

        textview2 = findViewById(R.id.textview2);

        SharedPreferences sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
        inputText = sharedPreferences.getString("inputText", "");
        textview2.setText(inputText + " 님");

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            }
        });

        showRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Categoryfood.class);
                startActivity(intent);
                finish();
            }
        });

        showFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityForResult();
            }
        });

        searchIngredient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchedVal = s.toString();

                String categoryString = "";
                ArrayList<ingredientsCategory> parentList = new ArrayList<>();
                ArrayList<ingredients> childrenList = new ArrayList<>();
                if (TextUtils.isEmpty(searchedVal)) {
                    adapter.setmIngredientsCategoryList(parentModelArrayList);
                } else {
                    for (ingredientsCategory i : parentModelArrayList) {
                        ArrayList<ingredients> ingredients = i.getIngredients();
                        for (ingredients j : ingredients) {
                            if (j.getIngredientName().contains(searchedVal)) {
                                childrenList.add(j);
                            }
                        }
                    }
                    parentList.add(new ingredientsCategory(categoryString, childrenList));

                    adapter.setmIngredientsCategoryList(parentList);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        openDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });
        getIngredients();
        fridge.clear();

    }

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {


        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {
        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {
        }

        @Override
        public void onDrawerStateChanged(int newState) {
        }

    };

    //튜토리얼 연결
    public void OnClick1(View view) {
        Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
        startActivity(intent);
    }

    // 회원정보 연결
    public void OnClick2(View view) {
        Intent intent = new Intent(getApplicationContext(), userinfo.class);
        startActivity(intent);
    }

    // 스크랩 연결
    public void OnClick3(View view) {
        Intent intent = new Intent(getApplicationContext(), scrapPage.class);
        startActivity(intent);
    }

    // 캘린더 연결
    public void OnClick4(View view) {
        Intent intent = new Intent(getApplicationContext(), calendar.class);
        intent.putExtra("userName", inputText);
        startActivity(intent);
    }

    // 하루 8잔 연결
    public void OnClick5(View view) {
        Intent intent = new Intent(getApplicationContext(), WaterActivity.class);
        startActivity(intent);
        finish();
    }

    // 유통기한 연결
    public void OnClick6(View view) {
        Intent intent = new Intent(getApplicationContext(), Yutong_MainActivity.class);
        startActivity(intent);
        finish();
    }

    // 장보기 메모 연결
    public void OnClick7(View view) {
        Intent intent = new Intent(getApplicationContext(), Memo_MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void getIngredients() {
        ArrayList<ingredients> grain = new ArrayList<ingredients>();
        ArrayList<ingredients> meat = new ArrayList<ingredients>();
        ArrayList<ingredients> veg = new ArrayList<ingredients>();
        ArrayList<ingredients> eggmilk = new ArrayList<ingredients>();
        ArrayList<ingredients> sea = new ArrayList<ingredients>();
        ArrayList<ingredients> other = new ArrayList<ingredients>();
        ArrayList<ingredients> sauce = new ArrayList<ingredients>();

        grain.add(new ingredients("밥", R.drawable.grain_rice_color, R.drawable.ic_baseline_check_24));
        grain.add(new ingredients("밀가루", R.drawable.griain_flour_color, R.drawable.ic_baseline_check_24));
        grain.add(new ingredients("튀김가루", R.drawable.grain_fryingflour_color, R.drawable.ic_baseline_check_24));
        grain.add(new ingredients("전분", R.drawable.grain_starch_color, R.drawable.ic_baseline_check_24));
        grain.add(new ingredients("파스타면", R.drawable.grain_pasta_color, R.drawable.ic_baseline_check_24));
        grain.add(new ingredients("우동면", R.drawable.grain_noodles_color, R.drawable.ic_baseline_check_24));
        grain.add(new ingredients("라면", R.drawable.grain_ramen_color, R.drawable.ic_baseline_check_24));
        grain.add(new ingredients("당면", R.drawable.grain_plastic_noodles_color, R.drawable.ic_baseline_check_24));

        meat.add(new ingredients("돼지고기", R.drawable.meat_pork_color, R.drawable.ic_baseline_check_24));
        meat.add(new ingredients("소고기", R.drawable.meat_beef_color, R.drawable.ic_baseline_check_24));
        meat.add(new ingredients("닭고기", R.drawable.meat_chicken_color, R.drawable.ic_baseline_check_24));
        meat.add(new ingredients("햄", R.drawable.meat_ham_color, R.drawable.ic_baseline_check_24));
        meat.add(new ingredients("베이컨",R.drawable.meat_bacon,R.drawable.ic_baseline_check_24));

        veg.add(new ingredients("감자", R.drawable.veg_potato_color, R.drawable.ic_baseline_check_24));
        veg.add(new ingredients("고추", R.drawable.veg_greenpepper_color, R.drawable.ic_baseline_check_24));
        veg.add(new ingredients("당근", R.drawable.veg_carrot_color, R.drawable.ic_baseline_check_24));
        veg.add(new ingredients("대파", R.drawable.veg_bigleek_color, R.drawable.ic_baseline_check_24));
        veg.add(new ingredients("쪽파", R.drawable.veg_leek_color, R.drawable.ic_baseline_check_24));
        veg.add(new ingredients("양배추", R.drawable.veg_cabbage_color, R.drawable.ic_baseline_check_24));
        veg.add(new ingredients("배추", R.drawable.veg_chinesecabbage_color, R.drawable.ic_baseline_check_24));
        veg.add(new ingredients("버섯", R.drawable.veg_mushrooms_color, R.drawable.ic_baseline_check_24));
        veg.add(new ingredients("부추", R.drawable.veg_scallions_color, R.drawable.ic_baseline_check_24));
        veg.add(new ingredients("양파", R.drawable.veg_onion_color, R.drawable.ic_baseline_check_24));
        veg.add(new ingredients("토마토", R.drawable.veg_tomato_color, R.drawable.ic_baseline_check_24));
        veg.add(new ingredients("파프리카", R.drawable.veg_bellpepper_color, R.drawable.ic_baseline_check_24));
        veg.add(new ingredients("피망", R.drawable.veg_pepper_color, R.drawable.ic_baseline_check_24));
        veg.add(new ingredients("애호박", R.drawable.youngsquash_color, R.drawable.ic_baseline_check_24));

        eggmilk.add(new ingredients("계란", R.drawable.eggmilk_egg_color, R.drawable.ic_baseline_check_24));
        eggmilk.add(new ingredients("두부", R.drawable.eggmilk_tofu_color, R.drawable.ic_baseline_check_24));
        eggmilk.add(new ingredients("버터", R.drawable.eggmilk_butter_color, R.drawable.ic_baseline_check_24));
        eggmilk.add(new ingredients("식빵", R.drawable.eggmilk_bread_color, R.drawable.ic_baseline_check_24));
        eggmilk.add(new ingredients("우유", R.drawable.eggmilk_milk_color, R.drawable.ic_baseline_check_24));
        eggmilk.add(new ingredients("치즈", R.drawable.eggmilk_cheese_color, R.drawable.ic_baseline_check_24));

        sea.add(new ingredients("새우", R.drawable.sea_shrimp_color, R.drawable.ic_baseline_check_24));
        sea.add(new ingredients("오징어", R.drawable.sea_squid_color, R.drawable.ic_baseline_check_24));
        sea.add(new ingredients("어묵", R.drawable.sea_fishcake_color, R.drawable.ic_baseline_check_24));

        other.add(new ingredients("가쓰오부시", R.drawable.else_seasoning_color, R.drawable.ic_baseline_check_24));
        other.add(new ingredients("김치", R.drawable.else_kimchi_color, R.drawable.ic_baseline_check_24));
        other.add(new ingredients("깍두기", R.drawable.else_radishkimchi_color, R.drawable.ic_baseline_check_24));
        other.add(new ingredients("짜장가루", R.drawable.else_blackbeanpowder, R.drawable.ic_baseline_check_24));
        other.add(new ingredients("쌀뜨물", R.drawable.else_ricewater, R.drawable.ic_baseline_check_24));
        other.add(new ingredients("페페론치노", R.drawable.else_seasoning_color, R.drawable.ic_baseline_check_24));
        other.add(new ingredients("토마토주스", R.drawable.else_tomatojuice_color, R.drawable.ic_baseline_check_24));

        sauce.add(new ingredients("간장",R.drawable.sauce_soysauce_color,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("고추기름",R.drawable.sauce_pepperoil_color,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("참기름",R.drawable.sauce_oil_color,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("식용유",R.drawable.sauce_oil_color,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("올리브유",R.drawable.sauce_oliveoil_color,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("고추장",R.drawable.sauce_peppersauce,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("된장",R.drawable.sauce_soybeansauce,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("고춧가루",R.drawable.sauce_pepperpowder,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("굴소스",R.drawable.sauce_oystersauce_color,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("데리야끼소스",R.drawable.sauce_deriyakkisauce,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("돈가스소스",R.drawable.sauce_friedpork,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("케첩",R.drawable.sauce_ketchup,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("마요네즈",R.drawable.sauce_mayo,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("맛술",R.drawable.sauce_housewine_color,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("쯔유",R.drawable.sauce_tseuyou,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("설탕",R.drawable.sauce_sugar,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("소금",R.drawable.sauce_salt,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("후추",R.drawable.sauce_pepper,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("식초",R.drawable.sauce_benegar_color,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("다진마늘",R.drawable.sauce_garlic_color,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("마늘",R.drawable.sauce_garlic_color,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("생강",R.drawable.sauce_ginger_color,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("깨",R.drawable.else_sesame,R.drawable.ic_baseline_check_24));
        sauce.add(new ingredients("토마토소스",R.drawable.sauce_tomatosauce,R.drawable.ic_baseline_check_24));

        parentModelArrayList.add(new ingredientsCategory("소스류", sauce));
        parentModelArrayList.add(new ingredientsCategory("곡류/면류", grain));
        parentModelArrayList.add(new ingredientsCategory("고기류", meat));
        parentModelArrayList.add(new ingredientsCategory("과일/채소류", veg));
        parentModelArrayList.add(new ingredientsCategory("계란/유제품/콩류", eggmilk));
        parentModelArrayList.add(new ingredientsCategory("해산물", sea));
        parentModelArrayList.add(new ingredientsCategory("기타", other));
    }
}