package StaticConstant;

import java.sql.SQLNonTransientConnectionException;
import java.util.HashSet;
import java.util.Set;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class AdjustParameter {
	 //ǰ׺��׺��׺���ʵ����Ƹ���
     public static int PreWordNum = 0;
     public static int MidWordNUm = 6;
     public static int SufWordNUm = 0;
     
     //ǰ׺��׺��׺Ȩ�ؼ���ĵ�������
//     public static double PreFactor = 0.2;
//     public static double MidFactor = 0.6;
//     public static double SuffFactor = 0.2;
     
     //��Ԫ�����ʱ��ʵ��Ժ;�������֮��Ļ��ֱ��
     public static String RecognizeFlag = "###";
     
     //���Ա�ע
     //public static MaxentTagger tagger = new MaxentTagger("taggers/english-left3words-distsim.tagger");//�������·����Ҳ����д����·��  
     
     //ƥ���⼸��������ʽ2017.2.27
//     public boolean isVerb(String c)
//     {
//    	 Set<String> verb = new HashSet<>();
//    	 verb.add("VB");
//    	 verb.add("VBD");
//    	 verb.add("VBG");
//    	 verb.add("VBN");
//    	 verb.add("VBP");
//    	 verb.add("VBZ");
//    	 verb.add("VBD");
//    	 verb.add("RB");
//    	 verb.add("RBR");
//    	 verb.add("RBS");
//    	 verb.add("WRB");
//    	 if(verb.contains(c))
//    	     return  true;
//    	 else
//    		 return false;
//     }
}
