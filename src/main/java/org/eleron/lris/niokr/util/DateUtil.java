package org.eleron.lris.niokr.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {

    private static Map<Integer,String> months;
    private static void initMonths() {
        months = new HashMap<Integer,String>();
        months.put(12,"Январь");
        months.put(1,"Февраль");
        months.put(2,"Март");
        months.put(3,"Апрель");
        months.put(4,"Май");
        months.put(5,"Июнь");
        months.put(6,"Июль");
        months.put(7,"Август");
        months.put(8,"Сентябрь");
        months.put(9,"Октябрь");
        months.put(10,"Ноябрь");
        months.put(11,"Декабрь");
        months.put(24,"января");
        months.put(13,"февраля");
        months.put(14,"марта");
        months.put(15,"апреля");
        months.put(16,"мая");
        months.put(17,"июня");
        months.put(18,"июля");
        months.put(19,"августа");
        months.put(20,"сентября");
        months.put(21,"октября");
        months.put(22,"ноября");
        months.put(23,"декабря");
    }

    public static String getCurrentMonth(){
        if (months==null){
            initMonths();
        }
        return months.get(Calendar.getInstance().get(Calendar.MONTH));
    }

    public static String getCurrentMonthR(){
        if (months==null){
            initMonths();
        }
        return months.get(Calendar.getInstance().get(Calendar.MONTH)+12);
    }

    public static Map<Integer,String> getMonths(){
        if(months == null) initMonths();
        return months;
    }
}
