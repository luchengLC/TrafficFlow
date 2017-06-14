import model.TrafficData;
import model.HiddenLayer;
import model.InputLayer;
import model.OutputLayer;
import myUtils.MyDataDealing;
import myUtils.ReadText;
import myUtils.RoundTool;

/**
 * Created by LC on 2017/5/4.
 */
public class Main {

    public static void main (String[] args){
        int p = 0;  //迭代次数
        int t = 0;  //用作进入下一个周期的int工具

        double alph = 0.05;  //学习速率
        //文件路径
        String file_train = "F:\\IDEAproject\\TrafficFlow\\train.txt";
        String file_test = "F:\\IDEAproject\\TrafficFlow\\text.txt";
        double[][] train_arr = new double[276][5];
        double[][] test_arr= new double[92][5];

        TrafficData trafficData = new TrafficData();
        InputLayer inputLayer = new InputLayer();
        HiddenLayer hiddenLayer = new HiddenLayer(alph);
        OutputLayer outputLayer = new OutputLayer(alph);


        //训练
        train_arr = ReadText.readFile(file_train,train_arr);

        //隐含层 和 输出层 初始化 权重和阈值
        hiddenLayer.init();
        outputLayer.init();

        //若不满足条件就循环
        while (MyDataDealing.eSquareSum_forOutput(outputLayer.getE()) > 0.001){

            //改变、更新Data的值， 并归一化
            trafficData.setPre_time1(RoundTool.roundDouble(train_arr[t][0]));
            trafficData.setPre_time2(RoundTool.roundDouble(train_arr[t][1]));
            trafficData.setPre_time3(RoundTool.roundDouble(train_arr[t][2]));
            trafficData.setPre_time4(RoundTool.roundDouble(train_arr[t][3]));

            /** 激活 */
            //将Data的数据传入 输入层
            inputLayer.setTrafficData_i(trafficData);

            //隐含层 更新 (输出层输出的) Data 的值，并在内部进行 实际输出 的计算
            hiddenLayer.setTrafficData_h(inputLayer.getTrafficData_i());

            //输出层 拿到 隐含层的输出 作为输入，并在内部进行 实际输出 的计算
            outputLayer.setInput(hiddenLayer.getActualOutput());

            /** 权重训练*/
            //输出层 拿到 期望输出
            double expectResult=train_arr[t][4];
            expectResult = MyDataDealing.pre(309.0, -13.0,expectResult);

            //输出层 计算 误差、误差梯度、权重校正值等
            outputLayer.calE(expectResult);
            outputLayer.cal();

            //隐含层 计算 误差梯度、权重校正值等
            hiddenLayer.cal(outputLayer.getWeight(),outputLayer.getErrorGradient());

            /*//------------------------------------------------------------
            // 以下用于测试输出
            System.out.print("迭代次数 = "+p+"\n");
            System.out.print("【隐含层】 权重：\n");
            for (int i = 0; i<hiddenLayer.getWeight().length; i++){
                for (int j = 0; j<3; j++){
                    System.out.print(hiddenLayer.getWeight()[i][j]+"\n");
                }
            }
            System.out.print("\n【隐含层】 阈值：\n");
            for (int i = 0; i<hiddenLayer.getTheta().length; i++){
                System.out.print(hiddenLayer.getTheta()[i]+"\n");
            }

            System.out.print("\n\n【输出层】 权重：\n");
            for (int i = 0; i<outputLayer.getWeight().length; i++){
                System.out.print(outputLayer.getWeight()[i]+"\n");
            }
            System.out.print("\n【输出层】 阈值：\n");
            System.out.print(outputLayer.getTheta()+"\n");

            System.out.print("\n");
            //------------------------------------------------------------------*/


            p++;  //迭代次数+1
            t++;  //t用于控制周期循环
            if (t==120){
                t=0;
            }
        }
/*



 */

        System.out.print("迭代次数 = "+p+"\n");
        System.out.print("【隐含层】 权重：\n");
        for (int i = 0; i<hiddenLayer.getWeight().length; i++){
            for (int j = 0; j<3; j++){
                System.out.print(hiddenLayer.getWeight()[i][j]+"\n");
            }
        }
        System.out.print("\n【隐含层】 阈值：\n");
        for (int i = 0; i<hiddenLayer.getTheta().length; i++){
            System.out.print(hiddenLayer.getTheta()[i]+"\n");
        }

        System.out.print("\n\n【输出层】 权重：\n");
        for (int i = 0; i<outputLayer.getWeight().length; i++){
            System.out.print(outputLayer.getWeight()[i]+"\n");
        }
        System.out.print("\n【输出层】 阈值：\n");
        System.out.print(outputLayer.getTheta()+"\n");




        //测试

        test_arr = ReadText.readFile(file_test,test_arr);
        int flag = 1;

        System.out.print("\n【测试】：92个测试样的差错率：\n");
        for (int k = 0;k<92;k++){
            trafficData.setPre_time1(test_arr[k][0]);
            trafficData.setPre_time2(test_arr[k][1]);
            trafficData.setPre_time3(test_arr[k][2]);
            trafficData.setPre_time4(test_arr[k][3]);

            //计算隐含层 实际输出
            hiddenLayer.setTrafficData_h(trafficData);
            //计算输出层 实际输出
            outputLayer.setInput(hiddenLayer.getActualOutput());

            //取期望值
            double expectResult=train_arr[t][4];
            //计算误差
            outputLayer.calE(expectResult);

            for (int i = 0; i<3; i++){

                double rate = RoundTool.roundRate(outputLayer.getE()/expectResult);
                System.out.print(rate +"  \n");

            }

        }
        /*
        if (flag == 0){
            System.out.print("\n失败！");
        }
        else {
            System.out.print("\n成功!");
        }*/


    }

}
