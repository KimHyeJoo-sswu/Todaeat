package com.sw.todaeat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity_china extends AppCompatActivity {

    //추가된 소스
    Toolbar myToolbar;
    ArrayList<foods> chinaFoods=new ArrayList<foods>();
    EditText search;

    private ListView chinaFoodsListView;
    public foodsListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_china);

        chinaFoodsListView=findViewById(R.id.chinaFoodListView);

        getFoods();
        filter();

        adapter=new foodsListViewAdapter(chinaFoods,MainActivity_china.this);
        chinaFoodsListView.setAdapter(adapter);

        search=findViewById(R.id.searchRecipe);

        //레시피 누르면 이동
        chinaFoodsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                foods currentFood=adapter.getItem(position);
                switch (currentFood.getFoodName()){
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

                }
            }
        });

        //delivery button(1)
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.coupang.com"));
                startActivity(intent);
            }
        });

        //(2)
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://emart.ssg.com"));
                startActivity(intent);
            }
        });

        //(3)
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://front.homeplus.co.kr"));
                startActivity(intent);
            }
        });

        //(4) 배민상회
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://mart.baemin.com"));
                startActivity(intent);
            }
        });

        //(5) G마켓
        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gmarket.com"));
                startActivity(intent);
            }
        });

        //(6) 마켓컬리
        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.kurly.com"));
                startActivity(intent);
            }
        });

        //(7) 옥션
        Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.acution.com"));
                startActivity(intent);
            }
        });

        //(8) 나우픽
        Button button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://nowpick.co.kr"));
                startActivity(intent);
            }
        });

        //(9) 위메프
        Button button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://front.wemakeprice.com"));
                startActivity(intent);
            }
        });


        //Toolbar를 생성
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //추가된 소스코드, Toolbar의 왼쪽에 버튼을 추가하고 버튼의 아이콘을 바꾼다.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back2);

        getSupportActionBar().setTitle("");  //해당 액티비티의 툴바에 있는 타이틀을 공백으로 처리

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String filterText=s.toString();
                ((foodsListViewAdapter)chinaFoodsListView.getAdapter()).getFilter().filter(filterText);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String filterText=s.toString();
                ((foodsListViewAdapter)chinaFoodsListView.getAdapter()).getFilter().filter(filterText);
            }
        });
    }



    //추가된 소스, ToolBar에 menu.xml을 인플레이트함
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    //추가된 소스, ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수 (환경 설정)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.search:
                // User chose the "Settings" item, show the app settings UI...
                final InputMethodManager manager=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return true;

            case R.id.bookmark:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "스크랩 페이지로 이동", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(getApplicationContext(), scrapPage.class);
                startActivity(intent1);
                return true;

            case R.id.camera:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "앨범으로 이동", Toast.LENGTH_LONG).show();
                return true;

            //toolbar 뒤로가는 부분
            case android.R.id.home:{
                Intent intent2 = new Intent(getApplicationContext(), Categoryfood.class);  // 눌렀을 때 메인화면으로 이동하기
                startActivity(intent2);

                return true;
            }

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);

        }
    }

    public void getFoods(){

        chinaFoods.add(new foods(R.drawable.china_food_1_baechu_jjajang,"배추짜장덮밥","아삭아삭한 식감이 더해진 짜장",25,850,new String[]{"배추","밥","대파","계란","짜장가루","다진마늘","소금","후추"}));
        chinaFoods.add(new foods(R.drawable.china_food_2_dongpayuuk,"동파육","달달한 간장 소스가 스며든 건강한 돼지요리",60,950,new String[]{"돼지고기","양파","간장","굴소스","설탕","소금","식용유"}));
        chinaFoods.add(new foods(R.drawable.china_food_3_egg_bokkeumbap,"계란볶음밥","일반 볶음밥과는 다른 중국식 볶음밥",25,650,new String[]{"밥","계란","대파","식용유","소금","후추"}));
        chinaFoods.add(new foods(R.drawable.china_food_4_gochu_japchae,"고추잡채","빵과 함께 먹으면 더 맛있는 고추잡채",25,650,new String[]{"돼지고기","피망","양파","버섯","다진마늘","고추기름","굴소스","후추"}));
        chinaFoods.add(new foods(R.drawable.china_food_5_ramyeon_jjamppong,"짬뽕라면","해물을 넣어 풍부한 맛이 나는 홈메이드 짬뽕",15,750,new String[]{"라면","대파","배추","양파","간장","고춧가루"}));
    }

    public void filter(){
        ArrayList<String> have = new ArrayList<String>();
        for(ingredients fridge : MainActivity.fridge){
            have.add(fridge.getIngredientName());
        }
        ArrayList<foods> tempChinaFoods = new ArrayList<foods>();
        for(foods temp : chinaFoods){
            ArrayList<String> need = new ArrayList<String>();
            for(String tmp : temp.getList()){
                need.add(tmp);
            }
            boolean possible=true;
            for(String n : need) {
                if (!have.contains(n)) {
                    possible=false;
                    break;
                }
                else
                    continue;
            }
            if(possible==true){
                tempChinaFoods.add(temp);
            }
        }
        chinaFoods=tempChinaFoods;
    }

}
