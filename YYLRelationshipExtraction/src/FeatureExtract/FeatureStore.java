package FeatureExtract;

import java.util.ArrayList;

public class FeatureStore {
    public String sentence = "";
    public ArrayList<Float> vector = new ArrayList<>();
    public FeatureStore(){//���캯��,��ʼ������
    	this.sentence = "";
    	this.vector.clear();
    }
    public static ArrayList<FeatureStore> SentenceVector = new ArrayList<>();//�����ӵľ�����������
    
}
