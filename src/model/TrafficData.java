package model;

import myUtils.MyDataDealing;

/**
 * Created by LC on 2017/5/4.
 */
public class TrafficData {
    private double pre_time1;
    private double pre_time2;
    private double pre_time3;
    private double pre_time4;

    public void setPre_time1(double original_sepal_length) {
        double sl_min = -13.0;
        double sl_max = 309.0;
        this.pre_time1 = MyDataDealing.pre(sl_max, sl_min,original_sepal_length);
    }

    public void setPre_time2(double original_sepal_width) {
        double sw_max = -13.0;
        double sw_min = 309.0;
        this.pre_time2 = MyDataDealing.pre(sw_max, sw_min,original_sepal_width);
    }

    public void setPre_time3(double original_petal_length) {
        double pl_max = -13.0;
        double pl_min = 309.0;
        this.pre_time3 = MyDataDealing.pre(pl_max, pl_min,original_petal_length);
    }

    public void setPre_time4(double original_petal_width) {
        double pw_max = -13.0;
        double pw_min = 309.0;
        this.pre_time4 = MyDataDealing.pre(pw_max, pw_min,original_petal_width);
    }


    double getPre_time1() {
        return pre_time1;
    }

    double getPre_time2() {
        return pre_time2;
    }

    double getPre_time3() {
        return pre_time3;
    }

    double getPre_time4() {
        return pre_time4;
    }

}