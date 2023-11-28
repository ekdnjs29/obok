package com.genios.obok;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Date;
import java.util.HashSet;

public class HomeFragment extends Fragment {


    private CalendarDay todayDate; // 오늘 날짜
    private CalendarDay specialDate; // 특별한 날짜

    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // CalendarView 초기화
        MaterialCalendarView calendarView = rootView.findViewById(R.id.Calendar_View);
        imageView = rootView.findViewById(R.id.pet_img);

        Glide.with(imageView).load(R.drawable.obok).circleCrop().into(imageView);

        todayDate = CalendarDay.today();
        specialDate = CalendarDay.from(2023,11,30);

        HashSet<CalendarDay> dates = new HashSet<>();
        dates.add(todayDate); // 오늘 날짜를 강조
        dates.add(specialDate); // 특별한 날짜를 강조

        // DayViewDecorator를 생성하고 설정합니다.
        DateStylist decorator = new DateStylist (requireContext(), dates);
        calendarView.addDecorator(decorator);



        return rootView;
    }
}