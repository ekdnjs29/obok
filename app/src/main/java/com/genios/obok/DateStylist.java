package com.genios.obok;

import android.content.Context;
import android.graphics.Color;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

public class DateStylist  implements DayViewDecorator {

    private final HashSet<CalendarDay> dates;
    private final int textColor;


    public DateStylist(Context context,  Collection<CalendarDay> dates) {
        this.dates =new HashSet<>(dates);
        textColor = Color.GREEN; // 특별한 날짜의 텍스트 색상 (초록색)
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        // dates에 포함된 날짜만 스타일링합니다.
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        // 특별한 날짜에 색상을 적용합니다.
        view.addSpan(new DotSpan(7, Color.RED));
    }
}

