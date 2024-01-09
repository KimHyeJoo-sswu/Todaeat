package com.sw.todaeat;
import android.content.Context;

import android.content.Intent;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

public class EditFragment extends Fragment implements View.OnClickListener {

    EditText editName;
    EditText editDate;
    Button addBtn;
    Button deleteBtn;
    CalendarView calendarView;
    View inputView;
    OnDatabaseCallback callback;

    InputMethodManager mInputMethodManager;

    String oldName;
    String date;
    boolean isDelete;
    String btnText;

    public EditFragment() {
        oldName = "";
        date = "";
        isDelete = false;
        btnText = "추가하기";
    }

    public EditFragment(String name, String date) {
        oldName = name;
        this.date = date;
        this.isDelete = true;
        this.btnText = "수정하기";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (OnDatabaseCallback) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.edit_fragment, container, false);
        inputView = rootView.findViewById(R.id.inputView);

        mInputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        addBtn = rootView.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);

        deleteBtn = rootView.findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(this);

        editName = rootView.findViewById(R.id.editName);
        editDate = rootView.findViewById(R.id.editDate);

        calendarView = rootView.findViewById(R.id.calendarView);
        calendarView.setVisibility(View.GONE);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //프레그먼트에서 키보드 내리기
                mInputMethodManager.hideSoftInputFromWindow(editName.getWindowToken(), 0);
                String str = year + "-" + (month + 1) + "-" + dayOfMonth;
                editDate.setText(str);
            }
        });

        editName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    calendarView.setVisibility(View.GONE);
                }
                return false;
            }
        });

        editDate = rootView.findViewById(R.id.editDate);
        editDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mInputMethodManager.hideSoftInputFromWindow(editName.getWindowToken(), 0);
                    calendarView.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        inputView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mInputMethodManager.hideSoftInputFromWindow(editName.getWindowToken(), 0);
                    calendarView.setVisibility(View.GONE);
                }
                return false;
            }
        });

        editName.setText(oldName);
        editDate.setText(date);
        addBtn.setText(btnText);
        if (isDelete) {
            deleteBtn.setVisibility(View.VISIBLE);
        } else {
            deleteBtn.setVisibility(View.GONE);
        }

        return rootView;
    }

    @Override
    public void onClick(View v) {
        String btnName = addBtn.getText().toString();

        String name = editName.getText().toString();
        String date = editDate.getText().toString();

        switch (v.getId()) {
            case R.id.addBtn:
                if (btnName.equals("추가하기")) {
                    if (name.equals("") || date.equals("")) { //빈칸이면
                        Toast.makeText(getContext(), "잘못 입력하셨습니다.", Toast.LENGTH_LONG).show();
                    } else if (((Yutong_MainActivity) getActivity()).search(name).equals(name)) { //이름 중복 검사
                        Toast.makeText(getContext(), "중복된 이름입니다.", Toast.LENGTH_LONG).show();
                    } else {
                        callback.insert(name, date);
                        ((Yutong_MainActivity) getActivity()).backMain();
                    }
                } else if (btnName.equals("수정하기")) {
                    if (name.equals("") || date.equals("")) { //빈칸이면
                        Toast.makeText(getContext(), "잘못 입력하셨습니다.", Toast.LENGTH_LONG).show();
                    } else if (((Yutong_MainActivity) getActivity()).search(name).equals(name)) { //중복검사
                        if (name.equals(oldName)) { //원래 이름이면 중복이라고 떠도 예외로 수정하기
                            callback.update(oldName, name, date);
                            ((Yutong_MainActivity) getActivity()).backMain();
                        } else { //중복이면 수정 안하기
                            Toast.makeText(getContext(), "중복된 이름입니다.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        callback.update(oldName, name, date);
                        ((Yutong_MainActivity) getActivity()).backMain();
                    }
                }
                break;
            case R.id.deleteBtn:
                callback.delete(name);
                ((Yutong_MainActivity) getActivity()).backMain();
                break;
        }
    }
}