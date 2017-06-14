package model;

import myUtils.MyDataDealing;
import myUtils.RoundTool;

import static java.lang.StrictMath.random;


/**
 * Created by LC on 2017/5/4.
 */
public class OutputLayer {
    private double alph = 0.1;

    private double[] weight = new double[3];  //权重
    private double theta ;  //阈值

    private double[] input = new double[3];   //从隐含层来的输入
    private double actualOutput;  //实际输出

    private double e;  //误差
    private double errorGradient;  //误差梯度
    private double[] delWeight = new double[3];  //权重校正值，不是更新后的值
    private double delTheta;


    public OutputLayer(double alph) {
        this.alph = alph;
    }

    //获得输出层 的各误差（double型）
    public double getE() {
        return e;
    }

    public double[] getWeight() {
        return weight;
    }

    public double getErrorGradient() {
        return errorGradient;
    }

    public double getTheta() {
        return theta;
    }

    // 初始 随机 权重和阈值
    public void init(){
        for (int i = 0; i < weight.length; i++) {
            weight[i] = random()*1.2-0.6;

        }
        theta = random()*1.2-0.6;

        e = 1;

    }


    public void setInput(double[] input) {
        this.input = input;
        //计算 输出层 的 实际输出
        actualOutput = MyDataDealing.function_foroOutput(input,weight,theta);
    }

    //计算 误差、误差梯度、权重校正值等
    public void calE(double expectResult){
            e = expectResult - actualOutput;
            e = RoundTool.roundDouble(e);

    }
    public void cal(){

        //顺便 计算误差梯度
        calErrorGradient();
        //顺便 再计算 权重校正值
        calDelWeight();
        calDelTheta();

        //更新
        updateWeight();
        updateTheta();
    }


    private void calErrorGradient(){
        errorGradient = MyDataDealing.errorGradient_forOutput(actualOutput,e);
    }

    private void calDelWeight(){
        delWeight = MyDataDealing.delWeight_forOutput(alph,actualOutput,errorGradient);
    }

    private void  calDelTheta(){
        delTheta = MyDataDealing.delTheta_forOutput(alph,errorGradient);
    }

    private void updateWeight(){
        for (int i = 0; i<weight.length; i++){

            weight[i] = weight[i] + delWeight[i];
            weight[i] = RoundTool.roundDouble(weight[i]);

        }
    }
    private void updateTheta(){
        theta = theta + delTheta;
        theta = RoundTool.roundDouble(theta);
    }


}
