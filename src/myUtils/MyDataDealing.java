package myUtils;

/**
 * Created by LC on 2017/5/4.
 */
public class MyDataDealing {

    /**
     * 计算误差平方和——隐含层
     * @param e 输入的误差数组
     * @return
     */
    public static double eSquareSum_forHidden(double e[]){
        double result = 0;
        for (int i = 0; i<e.length; i++){
            result = result + e[i]*e[i];
        }
        result = RoundTool.roundDouble(result);
        return result;
    }

    /**
     * 计算误差平方和——输出层
     * @param e
     * @return
     */
    public static double eSquareSum_forOutput(double e){
        double result = 0;
        result = result + e*e;
        result = RoundTool.roundDouble(result);
        return result;
    }


    /**
     * 归一化
     * @param max 数据集对应的最大值
     * @param min 数据集对应的最小值
     * @param x 输入的
     * @return
     */
    public static double pre(double max, double min, double x){
        double result = (x-min)/(max-min);
        result = RoundTool.roundDouble(result);
        return result;
    }


    /**
     * 计算实际输出——隐含层
     * @param input  对应的层的上一层传来的值
     * @param weight  对应的层的权重
     * @param theta  对应的层的阈值
     * @return
     */
    public static double[] function_forHidden(double[] input, double[][] weight, double[] theta){
        double[] result = new double[theta.length];
        for (int i = 0; i<result.length; i++){
            result[i] = 0;
            for (int j = 0; j<input.length; j++) {
                result[i] = result[i] + weight[j][i] * input[i];
            }
            result[i] -= theta[i];
            result[i] = RoundTool.roundDouble(result[i]);
        }
        return result;
    }

    /**
     * 计算实际输出——输出层
     * @param input
     * @param weight
     * @param theta
     * @return
     */
    public static double function_foroOutput(double[] input, double[] weight, double theta){
        double result = 0;

        for (int j = 0; j<input.length; j++) {
            result = result + weight[j] * input[j];
        }
            result -= theta;
            result = RoundTool.roundDouble(result);

        return result;
    }


    /**
     * 计算误差梯度——隐含层
     * @param outPut 对应的层的实际输出
     * @param e 对应的层的误差（数组）
     * @return
     */
    public static double[] errorGradient_forHidden(double[] outPut, double[] e){
        double[] result = new double[e.length];
        for (int i = 0; i<result.length; i++){
            result[i] = outPut[i]*(1-outPut[i])*e[i];
            result[i] = RoundTool.roundDouble(result[i]);
        }
        return result;
    }

    /**
     * 计算误差梯度——输出层
     * @param outPut
     * @param e
     * @return
     */
    public static double errorGradient_forOutput(double outPut, double e){
        double result ;
        result = outPut*(1-outPut)*e;
        result = RoundTool.roundDouble(result);
        return result;
    }


    /**
     * 校正阈值(的权重)——隐含层
     * @param alph 学习速率
     * @param errorGradient  误差梯度
     * @return
     */
    public static double[] delTheta_forHidden(double alph, double[] errorGradient){
        double[] result = new double[errorGradient.length];
        for (int i = 0; i<result.length; i++) {
            result[i] = alph * (-1) * errorGradient[i];
            result[i] = RoundTool.roundDouble(result[i]);
        }
        return result;
    }

    /**
     * 校正阈值(的权重)——隐输出层
     * @param alph 学习速率
     * @param errorGradient  误差梯度
     * @return
     */
    public static double delTheta_forOutput(double alph, double errorGradient){
        double result;
        result= alph * (-1) * errorGradient;
        result = RoundTool.roundDouble(result);
        return result;
    }


    /**
     * 权重校正值——隐含层
     * @param alph 学习速率
     * @param outPut 实际输出
     * @param errorGradient 误差梯度
     * @return
     */
    public static double[][] delWeight_forHidden(double alph, double[] outPut, double[] errorGradient){
        double[][] result = new double[outPut.length][3];
        for (int i = 0; i<result.length; i++) {
            for (int j = 0; j<3; j++){
                result[i][j] = alph * outPut[i] * errorGradient[j];
                result[i][j] = RoundTool.roundDouble(result[i][j]);
            }

        }
        return result;
    }



    /**
     * 权重校正值——输出层
     * @param alph 学习速率
     * @param outPut 实际输出
     * @param errorGradient 误差梯度
     * @return
     */
    public static double[] delWeight_forOutput(double alph, double outPut, double errorGradient){
        double[] result = new double[3];
        for (int i = 0; i<result.length; i++) {
            result[i] = alph * outPut * errorGradient;
            result[i] = RoundTool.roundDouble(result[i]);

        }
        return result;
    }
}
