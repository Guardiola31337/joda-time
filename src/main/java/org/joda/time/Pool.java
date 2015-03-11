package org.joda.time;

import java.util.HashMap;

public class Pool {

    private static Pool myInstance;
    private HashMap<Integer, Days> days;
    private HashMap<Integer, Minutes> minutes;
    private HashMap<Integer, Hours> hours;

    private Pool() {
        this.hours = new HashMap<Integer, Hours>();
        this.days = new HashMap<Integer, Days>();
        this.minutes = new HashMap<Integer, Minutes>();
    }

    public static Pool getInstance() {

        if (myInstance == null) {
            myInstance = new Pool();
        }

        return myInstance;
    }

    private void addDay(int numeral, Days day) {
        days.put(new Integer(numeral), day);
    }



    public static Days retrieveDays(int numeral) {
        Pool pool = Pool.getInstance();

        Object result = pool.getDays(numeral);

        if (result == null) {
            result =  new Days(numeral);
            pool.addDay(numeral, (Days) result);
        }

        return (Days) result;
    }

    public static Hours retrieveHours(int numeral) {

        Pool pool = Pool.getInstance();

        Object result = pool.getHours(numeral);

        if (result == null) {
            result =  new Hours(numeral);
            pool.addHours(numeral, (Hours) result);
        }

        return (Hours) result;
    }


    public static Minutes retrieveMinutes(int numeral) {

        Pool pool = Pool.getInstance();

        Object result = pool.getMinutes(numeral);

        if (result == null) {
            result =  new Minutes(numeral);
            pool.addMinutes(numeral, (Minutes) result);
        }

        return (Minutes) result;
    }

    private void addHours(int numeral, Hours hour) {
        hours.put(new Integer(numeral), hour);
    }

    private void addMinutes(int numeral, Minutes minute) {
        minutes.put(new Integer(numeral), minute);
    }


    private Object getDays(int numeral){
        Object instance = days.get(new Integer(numeral));

        return instance;

    }

    private Object getHours(int numeral) {
        Object instance = hours.get(new Integer(numeral));

        return instance;
    }

    private Object getMinutes(int numeral) {
        Object instance = minutes.get(new Integer(numeral));

        return instance;
    }


}
