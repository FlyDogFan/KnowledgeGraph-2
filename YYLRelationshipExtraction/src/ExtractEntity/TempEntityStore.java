package ExtractEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TempEntityStore {
	public static HashMap<String,ArrayList<ArrayList>> temp_entity = new HashMap<>();//ʵ���ϵ�Դ�����Ȩ�ص���Ԫ��
	public static ArrayList<String> entity_pairs = new ArrayList<>();//��ϵʵ��ԣ���������Ȩ�ص���Ԫ��
	
	public static Set<String> string_tuple = new HashSet<>(); //��Ԫ����ַ�����ʽ
	//��ȡģʽ
    public void putPattern(String key, ArrayList<ArrayList> Tuple){
  	     temp_entity.put(key, Tuple);
    }
    public  ArrayList<ArrayList> getPattern(String key){
  	     return temp_entity.get(key);
    }
}
