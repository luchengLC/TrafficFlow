package myUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by LC on 2017/5/4.
 */
public class ReadText {


    /**
     * 读取文件，并将鸢尾花的类 转换成  100， 010 ，001 的数据
     * @param filePath
     * @param arr
     * @return
     */
    public static double[][] readFile(String filePath, double[][] arr){
        int i = 0;

        File file = new File(filePath);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert sc != null;
        while (sc.hasNext()) {
            arr[i][0] = sc.nextDouble();
            arr[i][1] = sc.nextDouble();
            arr[i][2] = sc.nextDouble();
            arr[i][3] = sc.nextDouble();
            arr[i][4] = sc.nextDouble();
            i++;
        }
/*
        for (int i1 = 0; i1<arr.length; i1++){
            for (int i2 = 0; i2<7; i2++){
                System.out.print(arr[i1][i2]+"  ");
            }
            System.out.print('\n');
        }*/
        return arr;
    }
}
