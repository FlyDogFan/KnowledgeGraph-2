package PatternForm;

import java.util.ArrayList;
import java.util.HashMap;

public class GroupTupleStore {
	public HashMap<String,ArrayList<ArrayList>> weight = new HashMap<>();//ʵ���ϵ�Դ�����Ȩ�ص���Ԫ��
	
	public HashMap<String,ArrayList<ArrayList>> temp_pattern = new HashMap<>();//ʵ���ϵ�Դ�����Ȩ�ص�ģ��
	
	//��ȡ��Ȩ�ص���Ԫ��
    public void putWeightTuples(String key, ArrayList<ArrayList> Tuple){
   	     weight.put(key, Tuple);
    }
    public  ArrayList<ArrayList> getweightKey(String key){
   	     return weight.get(key);
    }
    
    //��ȡģʽ
    public void putPattern(String key, ArrayList<ArrayList> Tuple){
  	     temp_pattern.put(key, Tuple);
    }
    public  ArrayList<ArrayList> getPattern(String key){
  	     return temp_pattern.get(key);
    }
}
