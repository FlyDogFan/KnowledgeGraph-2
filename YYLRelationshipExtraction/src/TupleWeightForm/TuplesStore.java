package TupleWeightForm;

import java.util.ArrayList;
import java.util.HashMap;

public class TuplesStore {
     public HashMap<String, ArrayList<ArrayList>> Tuples  = new HashMap<>();//����ʵ���ϵ�ԣ�������Ԫ��
     
     public HashMap<String,ArrayList<ArrayList>> weight = new HashMap<>();//ʵ���ϵ�Դ�����Ȩ�ص���Ԫ��
     
     static public ArrayList<String> Text_sentence = new ArrayList<>();//�����ı�����
     
     //��ȡ��Ԫ��
     public void putTuples(String key, ArrayList<ArrayList> Tuple){
    	 Tuples.put(key, Tuple);
     }
     public ArrayList<ArrayList> getKey(String key){
    	 return Tuples.get(key);
     }
     
     //��ȡ��Ȩ�ص���Ԫ��
     public void putWeightTuples(String key, ArrayList<ArrayList> Tuple){
    	 weight.put(key, Tuple);
     }
     public ArrayList<ArrayList> getweightKey(String key){
    	 return weight.get(key);
     }
     
     //��ȡ�ı�����
     public void putSentence(String sentence){
		 Text_sentence.add(sentence);
	 }
//     public String getSentence(String sentence){
//    	 return Text_sentence.get(index)
//     }
}
