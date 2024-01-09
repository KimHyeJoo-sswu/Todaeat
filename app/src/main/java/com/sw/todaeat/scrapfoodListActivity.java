package com.sw.todaeat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class scrapfoodListActivity extends AppCompatActivity {

    private TextView folderNameFromScrapPage;
    private Button back;
    private Button home;
    private EditText searchScrap;
    private Button searchIcon;
    String folderName;

    ArrayList<scrapFoods> scrapFoodsinFolder=new ArrayList<scrapFoods>();

    private ListView scrapListView;
    public scrapListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_food_list);

        back=findViewById(R.id.back);
        home=findViewById(R.id.home);
        folderNameFromScrapPage=findViewById(R.id.folderNameFromScrapPage);
        scrapListView=findViewById(R.id.scrapListView);
        searchScrap=findViewById(R.id.searchScrap);
        searchIcon=findViewById(R.id.searchIcon);

        final InputMethodManager manager=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        Intent intent=getIntent();
        folderName = intent.getStringExtra("folderName");
        folderNameFromScrapPage.setText(folderName);

        listViewAdapter=new scrapListViewAdapter(scrapfoodListActivity.this);
        scrapListView.setAdapter(listViewAdapter);

        Cursor iCursor=scrapPage.mDBOpenHelper.selectColumns();
        while(iCursor.moveToNext()) {
            String folderName = iCursor.getString(iCursor.getColumnIndex("folderName"));
            int foodImage=iCursor.getInt(iCursor.getColumnIndex("foodImg"));
            String foodName=iCursor.getString(iCursor.getColumnIndex("foodName"));
            int time=iCursor.getInt(iCursor.getColumnIndex("time"));

            scrapFoods scrapfood = new scrapFoods(foodImage,foodName,time);
            scrapfolderNames foldername = new scrapfolderNames(folderName);

            scrapFoodListByFolderName scrapfoodByFoldername =new scrapFoodListByFolderName(foldername,scrapfood);

            boolean exist=false;
            for(scrapFoodListByFolderName item : listViewAdapter.scrapFoodList_folderName){
                if(item==scrapfoodByFoldername){
                    exist=true;
                }
            }
            if(exist==false) {
                listViewAdapter.scrapFoodList_folderName.add(scrapfoodByFoldername);
            }
        }

        for(scrapFoodListByFolderName item : listViewAdapter.scrapFoodList_folderName) {
            String getFolderName=item.getFolderName().getFolderName();
            if (getFolderName.equals(folderName)) {
                scrapFoodsinFolder.add(item.getScrapFood());
            }
        }

        listViewAdapter.scrapFoodList_folderName.clear();
        for(scrapFoods item : scrapFoodsinFolder){
            scrapfolderNames foldername = new scrapfolderNames(folderName);
            scrapFoodListByFolderName scrap = new scrapFoodListByFolderName(foldername,item);
            listViewAdapter.scrapFoodList_folderName.add(scrap);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(scrapfoodListActivity.this,scrapPage.class);
                startActivity(intent1);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //홈으로 돌아가기
            }
        });

        searchScrap.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String filterText=s.toString();
                ((scrapListViewAdapter)scrapListView.getAdapter()).getFilter().filter(filterText);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String filterText=s.toString();
                ((scrapListViewAdapter)scrapListView.getAdapter()).getFilter().filter(filterText);
            }
        });


        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        scrapListView.setClickable(true);
        scrapListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String foodName = listViewAdapter.getItem(position).getScrapFood().getFoodName();
                switch (foodName){
                    case "배추짜장덮밥":
                        Intent intent1 = new Intent(getApplicationContext(), china_baechu_jjajang1.class);
                        startActivity(intent1);
                        break;
                    case "동파육":
                        Intent intent2 = new Intent(getApplicationContext(), china_dongpayuuk1.class);
                        startActivity(intent2);
                        break;
                    case "계란볶음밥":
                        Intent intent3 = new Intent(getApplicationContext(), china_egg_bok1.class);
                        startActivity(intent3);
                        break;
                    case "고추잡채":
                        Intent intent4 = new Intent(getApplicationContext(), china_gochu_japchae1.class);
                        startActivity(intent4);
                        break;
                    case "짬뽕라면":
                        Intent intent5 = new Intent(getApplicationContext(), china_jjamppong_ramen1.class);
                        startActivity(intent5);
                        break;
                    case "부타노쇼가야끼":
                        Intent intent6 = new Intent(getApplicationContext(), japan_butanoshogayaki1.class);
                        startActivity(intent6);
                        break;
                    case "오코노미야끼":
                        Intent intent7 = new Intent(getApplicationContext(), japan_okonomiyaki1.class);
                        startActivity(intent7);
                        break;
                    case "부타동":
                        Intent intent8 = new Intent(getApplicationContext(), japan_butadong1.class);
                        startActivity(intent8);
                        break;
                    case "볶음우동":
                        Intent intent9 = new Intent(getApplicationContext(), japan_yaki_udong1.class);
                        startActivity(intent9);
                        break;
                    case "오야코동":
                        Intent intent10 = new Intent(getApplicationContext(), japan_oyakodong1.class);
                        startActivity(intent10);
                        break;
                    case "김치볶음밥":
                        Intent intent11 = new Intent(getApplicationContext(), korea_kimchi_bokkeumbap1.class);
                        startActivity(intent11);
                        break;
                    case "깍두기볶음밥":
                        Intent intent12 = new Intent(getApplicationContext(), korea_kkacdugi_bokkeumbap1.class);
                        startActivity(intent12);
                        break;
                    case "된장찌개":
                        Intent intent13 = new Intent(getApplicationContext(), korea_denchangestew1.class);
                        startActivity(intent13);
                        break;
                    case "라볶이":
                        Intent intent14 = new Intent(getApplicationContext(), korea_raboggi1.class);
                        startActivity(intent14);
                        break;
                    case "잡채":
                        Intent intent15 = new Intent(getApplicationContext(), korea_japchae1.class);
                        startActivity(intent15);
                        break;
                    case "알리오올리오":
                        Intent intent16 = new Intent(getApplicationContext(), western_alioolio1.class);
                        startActivity(intent16);
                        break;
                    case "찹스테이크":
                        Intent intent17 = new Intent(getApplicationContext(), western_chopsteak1.class);
                        startActivity(intent17);
                        break;
                    case "크림리조또":
                        Intent intent18 = new Intent(getApplicationContext(), western_cream_risotto_1.class);
                        startActivity(intent18);
                        break;
                    case "에그인헬":
                        Intent intent19 = new Intent(getApplicationContext(), western_egg_in_hell1.class);
                        startActivity(intent19);
                        break;
                    case "토마토스튜":
                        Intent intent20 = new Intent(getApplicationContext(), western_tomato_stew1.class);
                        startActivity(intent20);
                        break;
                    case "팬케이크 시리얼":
                        Intent intent21=new Intent(getApplicationContext(),sns_pencake_cereal1.class);
                        startActivity(intent21);
                        break;
                    case "꿀젤리":
                        Intent intent22=new Intent(getApplicationContext(),sns_honey_jelly.class);
                        startActivity(intent22);
                        break;
                    case "네이쳐스 시리얼":
                        Intent intent23=new Intent(getApplicationContext(),sns_natures_cereal1.class);
                        startActivity(intent23);
                        break;
                    case "마약토스트":
                        Intent intent24=new Intent(getApplicationContext(),sns_mayak_toast1.class);
                        startActivity(intent24);
                        break;
                    case "마시멜로우 스모어 딥":
                        Intent intent25=new Intent(getApplicationContext(),sns_marshmello_smore_dip.class);
                        startActivity(intent25);
                        break;
                    case "아보카도 비빔밥":
                        Intent intent26=new Intent(getApplicationContext(),diet_abocado_deopbap1.class);
                        startActivity(intent26);
                        break;
                    case "닭가슴살 키토김밥":
                        Intent intent27=new Intent(getApplicationContext(),diet_chicken_breast_kimbap.class);
                        startActivity(intent27);
                        break;
                    case "닭가슴살 간장볶음밥":
                        Intent intent28=new Intent(getApplicationContext(),diet_chicken_breast_soysauce_bokkeumbap.class);
                        startActivity(intent28);
                        break;
                    case "카레 볶음밥":
                        Intent intent29=new Intent(getApplicationContext(),diet_curry_bokkeumbap.class);
                        startActivity(intent29);
                        break;
                    case "호기빵 샌드위치":
                        Intent intent30=new Intent(getApplicationContext(),diet_hogibread_sandwich.class);
                        startActivity(intent30);
                        break;
                    case "오트밀 매생이죽":
                        Intent intent31=new Intent(getApplicationContext(),diet_oatmeal_maesaengi1.class);
                        startActivity(intent31);
                        break;
                    case "파프리카 피자":
                        Intent intent32=new Intent(getApplicationContext(),diet_paprika_pizza1.class);
                        startActivity(intent32);
                        break;
                    case "단호박 에그슬럿":
                        Intent intent33=new Intent(getApplicationContext(),diet_pumpkin_eggslut1.class);
                        startActivity(intent33);
                        break;
                    case "두부 청경채 볶음":
                        Intent intent34=new Intent(getApplicationContext(),diet_tofu_chunggyungchae1.class);
                        startActivity(intent34);
                        break;
                    case "참치키토김밥":
                        Intent intent35=new Intent(getApplicationContext(),diet_tuna_kitokimbap1.class);
                        startActivity(intent35);
                        break;

                }
            }
        });


    }
}