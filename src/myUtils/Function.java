package myUtils;

import static java.lang.StrictMath.cos;
import static java.lang.StrictMath.exp;

/**
 * Created by LC on 2017/5/21.
 */
public class Function {
    public static double getFunctionValue(double x){
        double f = 0;
        f= cos(1.75*x)*exp(-(x*x/2));
        f = RoundTool.roundDouble(f);
        return f;
    }
}
