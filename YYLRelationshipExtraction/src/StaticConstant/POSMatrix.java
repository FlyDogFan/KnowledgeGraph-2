package StaticConstant;

import java.util.Random;

public class POSMatrix {
	 public static int colum = 37;
	 public static int row = 5;
     public static float[][] PosMatrix = new float[row][colum];
     public static void CaculateMatrix(){//�������һ��POS����
          int i,j;
          for(i = 0; i < row; i++){
        	  for(j = 0; j < colum; j++){
        		  Random random_float = new Random();//���������
        		  Random random_int = new Random();//�������
        		  PosMatrix[i][j] = random_float.nextFloat()*0.5f*(random_int.nextInt(3)-1);
        	  }
          }
          
//          for(i = 0; i < 5; i++){
//        	  System.out.println("������"+i);
//        	  for(j = 0; j < 37; j++){
//        		  System.out.println(PosMatrix[i][j]);
//        	  }
//          }
          
     } 
}
