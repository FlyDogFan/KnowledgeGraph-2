package StaticConstant;

import java.util.ArrayList;
import java.util.HashMap;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class ConstantParameter {
    public static HashMap<String, Integer> POSHash = new HashMap<>();//������Ա�
    public static HashMap<String, Integer> DisHash = new HashMap<>();//���浥�ʵ�ʵ������
    public static HashMap<String, ArrayList<Float>> WordVecHash = new HashMap<>();//���������
    public static MaxentTagger tagger = new MaxentTagger("taggers/english-left3words-distsim.tagger");//�������·����Ҳ����д����·��  
    //public static int test_num = 0;
}
