package TupleWeightForm;

import java.util.ArrayList;
import java.util.HashMap;

import FeatureExtract.FeatureStore;

public class TuplesStore {
     public HashMap<String,ArrayList<FeatureStore>> Tuples  = new HashMap<>();//����ʵ���ϵ�ԣ�����Я�����Ӻ���Ԫ������
     
     //��ȡ��Ԫ��
     public void putTuples(String key, ArrayList<FeatureStore> Tuple){
    	 Tuples.put(key, Tuple);
     }
     public ArrayList<FeatureStore> getKey(String key){
    	 return Tuples.get(key);
     }
}
