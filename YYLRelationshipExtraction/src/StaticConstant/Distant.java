package StaticConstant;

import java.util.Random;

public class Distant {
	public static int colum = 201;
	public static int row = 5;
    public static float[][] DisMatrix = new float[row][colum];
    public static void CaculateMatrix(){//�������һ��POS����
        int i,j;
        for(i = 0; i < row; i++){
      	  for(j = 0; j < colum; j++){
      		  Random random_float = new Random();//���������
      		  Random random_int = new Random();//�������
      		  DisMatrix[i][j] = random_float.nextFloat()*0.5f*(random_int.nextInt(3)-1);
      	  }
        }
        
//        for(i = 0; i < row; i++){
//      	  System.out.println("������"+i);
//      	  for(j = 0; j < colum; j++){
//      		  System.out.println(DisMatrix[i][j]);
//      	  }
//        }
        
   }
}
