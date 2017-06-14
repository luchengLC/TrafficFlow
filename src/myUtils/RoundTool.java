package myUtils;

import java.math.BigDecimal;

/**
 * 用于控制计算过程的精度
 *
 * Created by LC on 2017/5/21.
 */
public class RoundTool {

    public static double roundDouble(double value){
        int scale = 6;  //精确到小数点之后7位
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, 5);  //5:  "ROUND_HALF_DOWN"
        double d = bd.doubleValue();
        return d;
    }

    public static double roundRate(double value){
        int scale = 5;  //精确到小数点之后5位
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, 5);  //5:
        double d = bd.doubleValue();
        return d;
    }

}
