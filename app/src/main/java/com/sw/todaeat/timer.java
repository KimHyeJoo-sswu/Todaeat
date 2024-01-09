package com.sw.todaeat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.DialogTitle;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class timer extends AppCompatActivity {

    Button close;
    TextView timerInfo;
    LinearLayout timer,timer3,timer4;
    TextView minute,second;
    EditText minuteSetting,secondSetting;
    Button timer3min,timer5min,timer10min,timer30min,timerReset;
    TextView timerEnd;
    int min,sec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM | Gravity.START;
        getWindow().setAttributes(params);

        close=findViewById(R.id.close);
        timerInfo=findViewById(R.id.timerInfo);
        timer=findViewById(R.id.timer);
        timer3=findViewById(R.id.timer3);
        timer4=findViewById(R.id.timer4);
        minute=findViewById(R.id.minute);
        second=findViewById(R.id.second);
        minuteSetting=findViewById(R.id.minuteSetting);
        secondSetting=findViewById(R.id.secondSetting);
        timer3min=findViewById(R.id.timer3min);
        timer5min=findViewById(R.id.timer5min);
        timer10min=findViewById(R.id.timer10min);
        timer30min=findViewById(R.id.timer30min);
        timerReset=findViewById(R.id.timerReset);
        timerEnd=findViewById(R.id.timerEnd);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer3.getVisibility() == View.VISIBLE) {
                    if(minute.getText().toString().equals("")) {
                        min=0;
                    }
                    else {
                        min = Integer.parseInt(minute.getText().toString());
                    }
                    if(second.getText().toString().equals("")) {
                        sec=0;
                    }
                    else {
                        sec = Integer.parseInt(second.getText().toString());
                    }

                    Timer timer=new Timer();
                    TimerTask timerTask=new TimerTask() {
                        @Override
                        public void run() {
                            if (sec != 0) {
                                sec--;
                            } else if (min != 0) {
                                sec = 60;
                                sec--;
                                min--;
                            }
                            if (sec <= 9) {
                                second.setText("0" + sec);
                            } else {
                                second.setText(Integer.toString(sec));
                            }

                            if (min <= 9) {
                                minute.setText("0" + min);
                            } else {
                                second.setText(Integer.toString(min));
                            }

                            if(min==0&&sec==0){
                                timer.cancel();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        timerEnd.setBackgroundColor(Color.WHITE);
                                    }
                                });
                                timerEnd.setText("타이머가 종료되었습니다");
                            }
                        }
                    };
                    timerInfo.setVisibility(View.INVISIBLE);
                    timer.schedule(timerTask,0,1000);
                }
                else {
                    timer4.setVisibility(View.INVISIBLE);
                    timer3.setVisibility(View.VISIBLE);

                    minute.setText(minuteSetting.getText().toString());
                    second.setText(secondSetting.getText().toString());

                    if(minute.getText().toString().equals("")) {
                        min=0;
                    }
                    else {
                        min = Integer.parseInt(minute.getText().toString());
                    }
                    if(second.getText().toString().equals("")) {
                        sec=0;
                    }
                    else {
                        sec = Integer.parseInt(second.getText().toString());
                    }

                    Timer timer=new Timer();
                    TimerTask timerTask=new TimerTask() {
                        @Override
                        public void run() {
                            if (sec != 0) {
                                sec--;
                            } else if (min != 0) {
                                sec = 60;
                                sec--;
                                min--;
                            }
                            if (sec <= 9) {
                                second.setText("0" + sec);
                            } else {
                                second.setText(Integer.toString(sec));
                            }

                            if (min <= 9) {
                                minute.setText("0" + min);
                            } else {
                                second.setText(Integer.toString(min));
                            }

                            if(min==0&&sec==0){
                                timer.cancel();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        timerEnd.setBackgroundColor(Color.WHITE);
                                    }
                                });
                                timerEnd.setText("타이머가 종료되었습니다");                            }
                        }
                    };
                    timerInfo.setVisibility(View.INVISIBLE);
                    timer.schedule(timerTask,0,1000);

                }
            }
        });


        timer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer3.getVisibility() == View.VISIBLE) {
                    if(minute.getText().toString().equals("")) {
                        min=0;
                    }
                    else {
                        min = Integer.parseInt(minute.getText().toString());
                    }
                    if(second.getText().toString().equals("")) {
                        sec=0;
                    }
                    else {
                        sec = Integer.parseInt(second.getText().toString());
                    }

                    Timer timer=new Timer();
                    TimerTask timerTask=new TimerTask() {
                        @Override
                        public void run() {
                            if (sec != 0) {
                                sec--;
                            } else if (min != 0) {
                                sec = 60;
                                sec--;
                                min--;
                            }
                            if (sec <= 9) {
                                second.setText("0" + sec);
                            } else {
                                second.setText(Integer.toString(sec));
                            }

                            if (min <= 9) {
                                minute.setText("0" + min);
                            } else {
                                second.setText(Integer.toString(min));
                            }

                            if(min==0&&sec==0){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        timerEnd.setBackgroundColor(Color.WHITE);
                                    }
                                });
                                timerEnd.setText("타이머가 종료되었습니다");
                                timer.cancel();
                            }
                        }
                    };
                    timerInfo.setVisibility(View.INVISIBLE);
                    timer.schedule(timerTask,0,1000);
                }
                else {
                    timer4.setVisibility(View.INVISIBLE);
                    timer3.setVisibility(View.VISIBLE);

                    minute.setText(minuteSetting.getText().toString());
                    second.setText(secondSetting.getText().toString());

                    if(minute.getText().toString().equals("")) {
                        min=0;
                    }
                    else {
                        min = Integer.parseInt(minute.getText().toString());
                    }
                    if(second.getText().toString().equals("")) {
                        sec=0;
                    }
                    else {
                        sec = Integer.parseInt(second.getText().toString());
                    }

                    Timer timer=new Timer();
                    TimerTask timerTask=new TimerTask() {
                        @Override
                        public void run() {
                            if (sec != 0) {
                                sec--;
                            } else if (min != 0) {
                                sec = 60;
                                sec--;
                                min--;
                            }
                            if (sec <= 9) {
                                second.setText("0" + sec);
                            } else {
                                second.setText(Integer.toString(sec));
                            }

                            if (min <= 9) {
                                minute.setText("0" + min);
                            } else {
                                second.setText(Integer.toString(min));
                            }

                            if(min==0&&sec==0){
                                timer.cancel();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        timerEnd.setBackgroundColor(Color.WHITE);
                                    }
                                });
                                timerEnd.setText("타이머가 종료되었습니다");
                            }
                        }
                    };
                    timerInfo.setVisibility(View.INVISIBLE);
                    timer.schedule(timerTask,0,1000);

                }
            }
        });

        timer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer3.getVisibility() == View.VISIBLE) {
                    if(minute.getText().toString().equals("")) {
                        min=0;
                    }
                    else {
                        min = Integer.parseInt(minute.getText().toString());
                    }
                    if(second.getText().toString().equals("")) {
                        sec=0;
                    }
                    else {
                        sec = Integer.parseInt(second.getText().toString());
                    }

                    Timer timer=new Timer();
                    TimerTask timerTask=new TimerTask() {
                        @Override
                        public void run() {
                            if (sec != 0) {
                                sec--;
                            } else if (min != 0) {
                                sec = 60;
                                sec--;
                                min--;
                            }
                            if (sec <= 9) {
                                second.setText("0" + sec);
                            } else {
                                second.setText(Integer.toString(sec));
                            }

                            if (min <= 9) {
                                minute.setText("0" + min);
                            } else {
                                second.setText(Integer.toString(min));
                            }

                            if(min==0&&sec==0){
                                timer.cancel();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        timerEnd.setBackgroundColor(Color.WHITE);
                                    }
                                });
                                timerEnd.setText("타이머가 종료되었습니다");
                            }
                        }
                    };
                    timerInfo.setVisibility(View.INVISIBLE);
                    timer.schedule(timerTask,0,1000);
                }
                else {
                    timer4.setVisibility(View.INVISIBLE);
                    timer3.setVisibility(View.VISIBLE);

                    minute.setText(minuteSetting.getText().toString());
                    second.setText(secondSetting.getText().toString());

                    if(minute.getText().toString().equals("")) {
                        min=0;
                    }
                    else {
                        min = Integer.parseInt(minute.getText().toString());
                    }
                    if(second.getText().toString().equals("")) {
                        sec=0;
                    }
                    else {
                        sec = Integer.parseInt(second.getText().toString());
                    }

                    Timer timer=new Timer();
                    TimerTask timerTask=new TimerTask() {
                        @Override
                        public void run() {
                            if (sec != 0) {
                                sec--;
                            } else if (min != 0) {
                                sec = 60;
                                sec--;
                                min--;
                            }
                            if (sec <= 9) {
                                second.setText("0" + sec);
                            } else {
                                second.setText(Integer.toString(sec));
                            }

                            if (min <= 9) {
                                minute.setText("0" + min);
                            } else {
                                second.setText(Integer.toString(min));
                            }

                            if(min==0&&sec==0){
                                timer.cancel();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        timerEnd.setBackgroundColor(Color.WHITE);
                                    }
                                });
                                timerEnd.setText("타이머가 종료되었습니다");
                            }
                        }
                    };
                    timerInfo.setVisibility(View.INVISIBLE);
                    timer.schedule(timerTask,0,1000);

                }
            }
        });

        timer3min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer4.setVisibility(View.INVISIBLE);
                timer3.setVisibility(View.VISIBLE);

                if(minute.getText().toString().equals("")) {
                    min=0;
                }
                else {
                    min = Integer.parseInt(minute.getText().toString());
                }
                minute.setText(Integer.toString(min+3));
            }
        });

        timer5min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer4.setVisibility(View.INVISIBLE);
                timer3.setVisibility(View.VISIBLE);

                if(minute.getText().toString().equals("")) {
                    min=0;
                }
                else {
                    min = Integer.parseInt(minute.getText().toString());
                }
                minute.setText(Integer.toString(min+5));
            }
        });

        timer10min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer4.setVisibility(View.INVISIBLE);
                timer3.setVisibility(View.VISIBLE);

                if(minute.getText().toString().equals("")) {
                    min=0;
                }
                else {
                    min = Integer.parseInt(minute.getText().toString());
                }

                minute.setText(Integer.toString(min+10));
            }
        });

        timer30min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer4.setVisibility(View.INVISIBLE);
                timer3.setVisibility(View.VISIBLE);

                if(minute.getText().toString().equals("")) {
                    min=0;
                }
                else {
                    min = Integer.parseInt(minute.getText().toString());
                }
                minute.setText(Integer.toString(min+30));
            }
        });

        timerReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer4.setVisibility(View.VISIBLE);
                timer3.setVisibility(View.INVISIBLE);

                minute.setText("");
                minuteSetting.setText("");
                min=0;

                second.setText("");
                secondSetting.setText("");
                sec=0;
            }
        });
    }
}