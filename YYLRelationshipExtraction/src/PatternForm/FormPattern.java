package PatternForm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.print.attribute.Size2DSyntax;

import ExtractEntity.ExtractCandidateEntity;
import ExtractEntity.TempEntityStore;
import FeatureExtract.FeatureStore;
import StaticConstant.AdjustParameter;

public class FormPattern {
	 float threhold = 0.79f;
	 //��һ���������ӣ�ÿ�ε�����ֵ
	// float deltathrehold = 0f;
	 //����ȡ�ĺ�ѡʵ�屣�浽�ı���
	 public void WriteIntoFile(String glaucoma){
		    //д���ļ�
		    BufferedWriter bw = null;
		    FileWriter fileWriter=null;
			try{
				fileWriter = new FileWriter("D:/YYLSoftware/Program/YYLRelationshipExtraction/CandidateEntityPattern.txt",true);
				bw = new BufferedWriter(fileWriter);
			    bw.write(glaucoma);
			    bw.newLine();  
			
			}catch (IOException e) {
				e.printStackTrace();
			}finally{
			    try {
			        bw.close();
			    }catch (IOException e) {
			        e.printStackTrace();
			    }
			}
	  }
	
    //ģ���γ�
	public void GetPattern(GroupTupleStore group_tuple){
		 
		 GroupTupleStore group_pattern = new GroupTupleStore();//����ۺ����ģ��
		 TempEntityStore tempEntityStore = new TempEntityStore();
		 
		 //System.out.println("FormPatternTempPattern:" + group_pattern.temp_pattern.size());
		 //System.out.println("FormPatternClusterSentenceVector:" + group_pattern.ClusterSentenceVector.toString());
		 
		 Iterator it = group_tuple.ClusterSentenceVector.entrySet().iterator();
		 String Entity = "";
 		 while(it.hasNext()){
 			 Entry entry = (Entry)it.next();
 			 
 			 Entity = (String)entry.getKey();
 			 
 			 //System.out.println("FromPatternEntity:"+Entity);
 			 
 			 ArrayList<FeatureStore> GroupFiveTupleCollection = new ArrayList<>();
 			 GroupFiveTupleCollection = (ArrayList<FeatureStore>) entry.getValue();
 			 
 			 //System.out.println("FromPatternGroupFiveTupleCollection:"+GroupFiveTupleCollection.toString());
 			 
			 //������������
			 ArrayList<Float> center_vector = new ArrayList<>();
			 center_vector.clear();
			 String center_sentence = "";
			 
			 //��ȡһ�������е������������������
 			 for(int j = 0; j < GroupFiveTupleCollection.size(); j++){
 				 //ArrayList<Float> temp_vector
 				 if(center_vector.isEmpty()){
 					 for(int t = 0; t < GroupFiveTupleCollection.get(j).vector.size(); t++)
 					    center_vector.add(GroupFiveTupleCollection.get(j).vector.get(t));
 				 }else{
	 				 for(int t = 0; t < GroupFiveTupleCollection.get(j).vector.size(); t++){
	 					 center_vector.set(t, center_vector.get(t) + GroupFiveTupleCollection.get(j).vector.get(t));
	 				 }
 				 }
 			 }
 			 
 			 for(int j = 0; j < center_vector.size(); j++){//�����������
 				center_vector.set(j, center_vector.get(j)/(float)GroupFiveTupleCollection.size());
 			 }
 			 
 			 //ÿ��������ÿ�����������������Ƚϣ���������Ƶ������������������Ժ��
 			 MatchVectorSim matchVectorSim = new MatchVectorSim();
 			 float SimValue = 0f, Max = 0f;
 			 int flag = 0;
 			 for(int j = 0; j < GroupFiveTupleCollection.size(); j++){
 				SimValue = matchVectorSim.VectorSim(center_vector, GroupFiveTupleCollection.get(j).vector);
 				if(SimValue > Max){
 					Max = SimValue;
 					flag = j;
 				}
 			 }
 			 center_sentence = GroupFiveTupleCollection.get(flag).sentence;
 			 
 			 //2017.3.22�޸ı��ε�������ģ�����һ�ε���������ģ��ƥ�䣬���������ֵ���������ô˴β�����ģ�����ʵ���ȡ
 			 FeatureStore temp_pattern = new FeatureStore();//��ʱģ��
		     temp_pattern.sentence = center_sentence;
		     temp_pattern.vector = center_vector;//���ﱾӦ��һ��һ����ӣ�ֱ�Ӹ�ֵ���ܻ������
			 ArrayList<FeatureStore> tuple = new ArrayList<>();
 			 if(tempEntityStore.getPattern(Entity.substring(0, Entity.indexOf(AdjustParameter.RecognizeFlag))) != null){//�ҵ���һ�ε����в������ʵ���ģ��
 				 ArrayList<FeatureStore> last_pattern = new ArrayList<>();
 				 last_pattern.clear();
 				 last_pattern = TempEntityStore.temp_entity.get(Entity.substring(0, Entity.indexOf(AdjustParameter.RecognizeFlag)));
 			     int judge = 0;
 				 for(int g = 0; g < last_pattern.size(); g++){
 					if(matchVectorSim.VectorSim(last_pattern.get(g).vector, temp_pattern.vector) > threhold){//�������ʵ�������ģ�����һ�ε���������ʵ���ģ��ֻҪ��һ��ģ���������ƶȳ�����ֵ
 						judge ++;
 						break;
 					}
 				 }
 				 
 				 //System.out.println("judge");
 				 
 				 if(judge == last_pattern.size()){
 					 
 					 //System.out.println("���");
					 
 					 if(group_pattern.temp_pattern.get(Entity) != null){
					    group_pattern.temp_pattern.get(Entity).add(temp_pattern);			    	 
				     }else{
				    	 tuple.add(temp_pattern);
				    	 group_pattern.temp_pattern.put(Entity,tuple);
				     }
 				 }
 			 }else{
 				 if(group_pattern.temp_pattern.get(Entity) != null){
				    group_pattern.temp_pattern.get(Entity).add(temp_pattern);			    	 
			     }else{
			    	 tuple.add(temp_pattern);
			    	 group_pattern.temp_pattern.put(Entity,tuple);
			     }
 			 }
 			 //2017.3.22�޸Ľ���
 		 }
    	 
 		 //��������
 		  //��������
//		   System.out.println("pattern finish");
//		   it = group_pattern.temp_pattern.entrySet().iterator();
//		 while(it.hasNext()){
//			 Entry entry = (Entry)it.next();
//			
//			 Entity = (String)entry.getKey();
//			 System.out.println(Entity);
//			 WriteIntoFile(Entity);
//			 
//			 ArrayList<ArrayList> GroupFiveTupleCollection = new ArrayList<>();
//			 GroupFiveTupleCollection = (ArrayList<ArrayList>) entry.getValue();
//			 System.out.println(GroupFiveTupleCollection);
//			 
//			 for(int j = 0; j < GroupFiveTupleCollection.size(); j++){
//				ArrayList<WeightTuples> test = GroupFiveTupleCollection.get(j);
//				String test_string = "";
//				System.out.println("=====");
//				test_string +="{";
//				for(int t = 0; t < test.size(); t++){
//					test_string += "<"+test.get(t).word+","+test.get(t).weight+">"+",";
//					System.out.println(test.get(t).word);
//					System.out.println(test.get(t).weight);
//				}
//				test_string +="}";
//				//��ģ��д���ļ���
//				WriteIntoFile(test_string);
//			 }
//			 
//		 }
		 //�������ݽ���
 		 
		 //���ú�����ȡ����
 		 ExtractCandidateEntity extractCandidateEntity = new ExtractCandidateEntity();
 		 extractCandidateEntity.GetCandidateEntity(group_pattern);
		 
    }
}
