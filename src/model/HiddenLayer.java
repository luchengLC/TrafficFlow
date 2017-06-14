package model;

import myUtils.MyDataDealing;
import myUtils.RoundTool;

import static java.lang.StrictMath.random;


/**
 * Created by LC on 2017/5/4.
 */
public class HiddenLayer {
    private TrafficData trafficData_h;
    private double alph = 0.01;
    private double[] flowerArr = new double[4];

    //隐含层只设了3个节点
    private double[][] weight = new double[4][3];  //权重
    private double[] theta = new double[3];  //阈值

    private double[] actualOutput = new double[3];  //实际输出

    private double[] errorGradient = new double[3];  //误差梯度
    private double[][] delWeight = new double[4][3];  //权重校正值，不是更新后的值
    private double[] delTheta = new double[3];

    public HiddenLayer(double alph) {
        this.alph = alph;
    }

    public double[][] getWeight() {
        return weight;
    }

    public double[] getTheta() {
        return theta;
    }

    // 初始 随机 权重和阈值
    public void init(){
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j<3; j++){
                weight[i][j] = random()*1.2-0.6;
            }
        }
        for (int i = 0; i < theta.length; i++){
            theta[i] = random()*1.2-0.6;
        }

    }

    public void setTrafficData_h(TrafficData trafficData) {
        trafficData_h = trafficData;
        flowerArr[0] = RoundTool.roundDouble(trafficData_h.getPre_time1());
        flowerArr[1] = RoundTool.roundDouble(trafficData_h.getPre_time2());
        flowerArr[2] = RoundTool.roundDouble(trafficData_h.getPre_time3());
        flowerArr[3] = RoundTool.roundDouble(trafficData_h.getPre_time4());

        //计算 隐含层的 实际输出
        actualOutput = MyDataDealing.function_forHidden(flowerArr, weight, theta);
    }

    public double[] getActualOutput() {
        return actualOutput;
    }

    public void cal(double[] weight_o, double errorGradient_o){
        //先算总和E
        double[] sumE = new double[actualOutput.length];
        for (int i = 0; i<sumE.length; i++){
            sumE[i] = sumE[i] + (errorGradient_o * weight_o[i]);
            sumE[i] = RoundTool.roundDouble(sumE[i]);

        }
        errorGradient = MyDataDealing.errorGradient_forHidden(actualOutput,sumE);
        calDelWeight();
        calDelTheta();

        //更新
        updateWeight();
        updateTheta();
    }

    private void calDelWeight(){
        delWeight = MyDataDealing.delWeight_forHidden(alph,flowerArr,errorGradient);
    }
    private void calDelTheta(){
        delTheta = MyDataDealing.delTheta_forHidden(alph,errorGradient);
    }
    private void updateWeight(){
        for (int i = 0; i<4; i++){
            for (int j=0; j<3 ; j++){
                weight[i][j] += delWeight[i][j];
                weight[i][j] = RoundTool.roundDouble(weight[i][j]);
            }
        }
    }
    private void updateTheta(){
        for (int i = 0; i<theta.length; i++){
            theta[i] += delTheta[i];
            theta[i] = RoundTool.roundDouble(theta[i]);
        }
    }
}
