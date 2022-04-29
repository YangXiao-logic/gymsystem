package com.example.gymsystem.booksystem;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymsystem.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;

public class BookFragment extends Fragment {

    private DatePickerDialog datepPickerDialog;
    private ImageView dateIcon;
    private TabLayout tabLayout;
    private String nowDate;
    private RecyclerView bookitems;
    ArrayList<String> mfacility =new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout = view.findViewById(R.id.tabLayout);
        Calendar calendar = Calendar.getInstance();
        findWeek(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        mfacility.add("1");
        mfacility.add("2");
        mfacility.add("3");
        mfacility.add("4");
        mfacility.add("5");
        mfacility.add("6");
        mfacility.add("7");
        bookitems = view.findViewById(R.id.bookitems);
        bookitems.setLayoutManager(new LinearLayoutManager(BookFragment.this.getContext()));
        bookitems.setAdapter(new BookListsAdapter(BookFragment.this.getContext(),
                new BookListsAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(int pos) { }
                }, mfacility, nowDate));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String selectDate = new String(tab.getText().toString());
                nowDate = selectDate.substring(0,selectDate.length() - 4);
                bookitems.setAdapter(new BookListsAdapter(BookFragment.this.getContext(),
                        new BookListsAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(int pos) { }
                        }, mfacility, nowDate));
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dateIcon = (ImageView) getActivity().findViewById(R.id.dateicon_book);
        dateIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();
        if (datepPickerDialog == null) {
            datepPickerDialog = new DatePickerDialog(this.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    findWeek(year, month, dayOfMonth);
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        }
        datepPickerDialog.updateDate(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datepPickerDialog.show();
    }


    private void findWeek(int year, int month, int dayOfMonth) {
        tabLayout.removeAllTabs();
        Calendar c = Calendar.getInstance();
        int current_day;
        int current_month;
        int current_year;
        current_day=dayOfMonth;
        current_month=month;
        current_year=year;
        c.clear();
        c.set(Calendar.MONTH,current_month);
        c.set(Calendar.DAY_OF_MONTH,current_day);
        c.set(Calendar.YEAR,current_year);
        boolean isFirstSunday = (c.getFirstDayOfWeek() == Calendar.SUNDAY);
        int weekDay = c.get(Calendar.DAY_OF_WEEK);
        if(isFirstSunday) {
            weekDay = weekDay - 1;
            if (weekDay == 0) {
                weekDay = 7;
            }
        }
        for(int i=1;i<=7;i++){
            switch (i){
                case 1:
                    c.clear();//记住一定要clear一次
                    c.set(Calendar.MONTH,current_month);
                    c.set(Calendar.DAY_OF_MONTH,current_day);
                    c.set(Calendar.YEAR,current_year);
                    c.add(Calendar.DATE,+(i-weekDay));
                    tabLayout.addTab(tabLayout.newTab().setText((c.get(Calendar.MONTH)+1) + "." + c.get(Calendar.DAY_OF_MONTH) + "\n" + "Mon"));
                    break;
                case 2:
                    c.clear();//记住一定要clear一次
                    c.set(Calendar.MONTH,current_month);
                    c.set(Calendar.DAY_OF_MONTH,current_day);
                    c.set(Calendar.YEAR,current_year);
                    c.add(Calendar.DATE,+(i-weekDay));
                    tabLayout.addTab(tabLayout.newTab().setText((c.get(Calendar.MONTH)+1) + "." + c.get(Calendar.DAY_OF_MONTH) + "\n" + "Tue"));
                    break;
                case 3:
                    c.clear();//记住一定要clear一次
                    c.set(Calendar.MONTH,current_month);
                    c.set(Calendar.DAY_OF_MONTH,current_day);
                    c.set(Calendar.YEAR,current_year);
                    c.add(Calendar.DATE,+(i-weekDay));
                    tabLayout.addTab(tabLayout.newTab().setText((c.get(Calendar.MONTH)+1) + "." + c.get(Calendar.DAY_OF_MONTH) + "\n" + "Wed"));
                    break;
                case 4:
                    c.clear();//记住一定要clear一次
                    c.set(Calendar.MONTH,current_month);
                    c.set(Calendar.DAY_OF_MONTH,current_day);
                    c.set(Calendar.YEAR,current_year);
                    c.add(Calendar.DATE,+(i-weekDay));
                    tabLayout.addTab(tabLayout.newTab().setText((c.get(Calendar.MONTH)+1) + "." + c.get(Calendar.DAY_OF_MONTH) + "\n" + "Thu"));
                    break;
                case 5:
                    c.clear();//记住一定要clear一次
                    c.set(Calendar.MONTH,current_month);
                    c.set(Calendar.DAY_OF_MONTH,current_day);
                    c.set(Calendar.YEAR,current_year);
                    c.add(Calendar.DATE,+(i-weekDay));
                    tabLayout.addTab(tabLayout.newTab().setText((c.get(Calendar.MONTH)+1) + "." + c.get(Calendar.DAY_OF_MONTH) + "\n" + "Fri"));
                    break;
                case 6:
                    c.clear();//记住一定要clear一次
                    c.set(Calendar.MONTH,current_month);
                    c.set(Calendar.DAY_OF_MONTH,current_day);
                    c.set(Calendar.YEAR,current_year);
                    c.add(Calendar.DATE,+(i-weekDay));
                    tabLayout.addTab(tabLayout.newTab().setText((c.get(Calendar.MONTH)+1) + "." + c.get(Calendar.DAY_OF_MONTH) + "\n" + "Sat"));
                    break;
                case 7:
                    c.clear();//记住一定要clear一次
                    c.set(Calendar.MONTH,current_month);
                    c.set(Calendar.DAY_OF_MONTH,current_day);
                    c.set(Calendar.YEAR,current_year);
                    c.add(Calendar.DATE,+(i-weekDay));
                    tabLayout.addTab(tabLayout.newTab().setText((c.get(Calendar.MONTH)+1) + "." + c.get(Calendar.DAY_OF_MONTH) + "\n" + "Sun"));
                    break;
            }
        }
        tabLayout.selectTab(tabLayout.getTabAt(weekDay-1));
        //获取选择标签的日期
        String selectDate = new String((tabLayout.getTabAt(weekDay-1).getText()).toString());
        nowDate = selectDate.substring(0,selectDate.length() - 4);
    }
}
