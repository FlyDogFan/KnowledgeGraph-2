package ExtractEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import FeatureExtract.FeatureStore;

public class TempEntityStore {
	public static HashMap<String,ArrayList<FeatureStore>> temp_entity = new HashMap<>();//ʵ���ϵ�Դ���Ԫ����Ӻ���Ԫ������
	public static ArrayList<String> entity_pairs = new ArrayList<>();//��ϵʵ���
	public static ArrayList<String> temp_entity_pairs = new ArrayList<>();//��ʱ�����������ʵ���
	
	public static Set<String> string_tuple = new HashSet<>(); //��Ԫ����ַ�����ʽ
	//��ȡģʽ
    public void putPattern(String key, ArrayList<FeatureStore> Tuple){
  	     temp_entity.put(key, Tuple);
    }
    public  ArrayList<FeatureStore> getPattern(String key){
  	     return temp_entity.get(key);
    }
}
