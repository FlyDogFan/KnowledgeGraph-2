package StaticConstant;

import java.sql.SQLNonTransientConnectionException;

public class AdjustParameter {
	 //前缀后缀单词的限制个数
     public static int PreWordNum = 3;
     public static int SufWordNUm = 3;
     
     //前缀中缀后缀权重计算的调整因子
     public static double PreFactor = 0.2;
     public static double MidFactor = 0.6;
     public static double SuffFactor = 0.2;
     
     //对元组聚类时，实体对和聚类索引之间的划分标记
     public static String RecognizeFlag = "###";
}
