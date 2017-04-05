package ExtractEntity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import FeatureExtract.FeatureStore;
import PatternForm.GroupTupleStore;
import PatternForm.MatchVectorSim;
import StaticConstant.AdjustParameter;
import StaticConstant.NonChiSplit;
import TupleWeightForm.TuplesStore;

public class ExtractCandidateEntity {
	
	 String entity_start = "[entity_start]";
	 String entity_end = "[entity_end]";
	 float threhold = 0.78f;
	 //����ȡ�ĺ�ѡʵ�屣�浽�ı���
	 public void WriteIntoFile(String glaucoma){
		    //д���ļ�
		    BufferedWriter bw = null;
		    FileWriter fileWriter=null;
			try{
				fileWriter = new FileWriter("D:/YYLSoftware/Program/YYLRelationshipExtraction/CandidateEntityTuple.txt",true);
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
	 
	 //��ȡ��ѡʵ��
     public void GetCandidateEntity(GroupTupleStore group_pattern){
		   // GroupTupleStore group_pattern = new GroupTupleStore();
		   //TuplesStore text_sentence = new TuplesStore();
		   //System.out.println(text_sentence.Text_sentence);
    	 
    	   //System.out.println("ExtractCandidateEntitygroup_pattern:"+group_pattern.toString());
		  
		   Iterator it = group_pattern.temp_pattern.entrySet().iterator();
		   //Iterator it = group_pattern.entrySet().iterator();
		   String Entity = "";
		   
		   TempEntityStore entityStore = new TempEntityStore();
		   
		   MatchVectorSim matchVectorSim = new MatchVectorSim();
		   
		   //System.out.println(text_sentence.Text_sentence.size());
		   
		   //���ı��г�ȡʵ���
		   while(it.hasNext()){
			   Entry entry = (Entry)it.next();
				
			   Entity = (String)entry.getKey();
			  
			  // System.out.println("ExtractCandidateEntity:"+Entity.substring(0, Entity.indexOf(AdjustParameter.RecognizeFlag)));
				 
			   ArrayList<FeatureStore> group_pattern_collection = new ArrayList<>();
			   group_pattern_collection = (ArrayList<FeatureStore>) entry.getValue();
			   
			  // System.out.println("ExtractCandidateEntitygroup_pattern_collection:"+group_pattern_collection.toString());
			   
			   for(int j = 0; j < group_pattern_collection.size(); j++){
				   
				   FeatureStore pattern = group_pattern_collection.get(j);
				   //System.out.println("ÿ��ʵ��Զ�Ӧ��ģ�壺"+ pattern);
				   ArrayList<FeatureStore> temp_tuple = new ArrayList<>();
				   //��Ҫ����ɶ���һ��ʵ���ʵ�������ģ����б���
				   //��Ԫ����ַ�����ʽ
                   String string_pattern = "";
                   string_pattern = pattern.sentence;
       			   //��Ԫ���ַ�����ʽ����
                   
                   if(Entity.indexOf(AdjustParameter.RecognizeFlag) > 0){
					   if(entityStore.getPattern(Entity.substring(0, Entity.indexOf(AdjustParameter.RecognizeFlag))) != null){
						   if(!entityStore.string_tuple.contains(string_pattern)){
	                           entityStore.getPattern(Entity.substring(0, Entity.indexOf(AdjustParameter.RecognizeFlag))).add(pattern);	
	                           entityStore.string_tuple.add(string_pattern);
						   }
						   //2017.3.1�����һ�ε����Ѿ��кͱ��ε���һ����ģ�����������һ��ģ����и���
						   //2017.3.1
	          		   }else{
	          			   temp_tuple.add(pattern);
	          		       entityStore.putPattern(Entity.substring(0, Entity.indexOf(AdjustParameter.RecognizeFlag)),temp_tuple);
	          		       entityStore.string_tuple.add(string_pattern);
	          		     
	          		       //2017/2/13�޸Ŀ�ʼ
	          		       if(!entityStore.entity_pairs.contains(Entity.substring(0, Entity.indexOf(AdjustParameter.RecognizeFlag)))){
	          		          entityStore.entity_pairs.add(Entity.substring(0, Entity.indexOf(AdjustParameter.RecognizeFlag)));//���µ�ʵ�屣�浽������
	          		          entityStore.temp_entity_pairs.add(Entity.substring(0, Entity.indexOf(AdjustParameter.RecognizeFlag)));//����������ʵ�屣�浽��ʱ������
	          		       }
	          		       //2017/2/13�޸Ľ���
	          		   }
                   }
				   
				   for(int i = 0; i < FeatureStore.SentenceVector.size(); i++){
					   String sentence = FeatureStore.SentenceVector.get(i).sentence;
					   ArrayList<Float> vector = new ArrayList<>();
					   for(int g = 0; g < FeatureStore.SentenceVector.get(i).vector.size(); g++)
					       vector.add(FeatureStore.SentenceVector.get(i).vector.get(g));
					   
					   //���Ҿ����г��ֵ�ʵ����
					   int FirstEntityLocateStart = -1, FirstEntityLocateEnd = -1, SecondEntityLocateStart = -1, SecondEntityLocateEnd = -1;//ʵ��������е�λ��
					   FirstEntityLocateStart =sentence.indexOf(entity_start);
					   FirstEntityLocateEnd = sentence.indexOf(entity_end);
					    
					   SecondEntityLocateStart = sentence.indexOf(entity_start,FirstEntityLocateEnd);
					   SecondEntityLocateEnd = sentence.indexOf(entity_end,SecondEntityLocateStart);
					   
					   if(FirstEntityLocateStart != -1 && FirstEntityLocateEnd != -1 && SecondEntityLocateStart != -1&& SecondEntityLocateEnd != -1){
						   
						    float match_value = 0f;
						    match_value = matchVectorSim.VectorSim(pattern.vector,vector);
						    
						    //System.out.println("EXtractCandidateEntitymatch_value:" + match_value);
						    
						    if(match_value > threhold){
						    	
						    	 //System.out.println("��ֵ����������");
						    	 ArrayList<FeatureStore> tuple = new ArrayList<>();
	                             String NewEntity = "";
	                             if(FirstEntityLocateStart + entity_start.length() + 1 < FirstEntityLocateEnd - 1 && SecondEntityLocateStart + entity_start.length() + 1 < SecondEntityLocateEnd - 1){
		                             NewEntity = sentence.substring(FirstEntityLocateStart + entity_start.length() + 1,FirstEntityLocateEnd - 1) +"," + sentence.substring(SecondEntityLocateStart + entity_start.length() + 1,SecondEntityLocateEnd - 1);
		                             //System.out.println("������ʵ�壺"+NewEntity);
		                             //System.out.println("������ʵ���ģ�壺"+pattern);
		                             //��Ԫ����ַ�����ʽ
		                             String pattern_string = "";
		                             pattern_string = pattern.sentence;
	                     			 //��Ԫ���ַ�����ʽ����	 
		                             if(entityStore.getPattern(NewEntity) != null){
		                            	if(!entityStore.string_tuple.contains(pattern_string)){
		                            		entityStore.getPattern(NewEntity).add(pattern);
		                            		entityStore.string_tuple.add(pattern_string);
		                            	}
		                            	
		                            	//System.out.println("1");
		                    		 }else{
		                    		     tuple.add(pattern);
		                    		     entityStore.putPattern(NewEntity,tuple);
		                    		     //Ԫ����ַ�����ʽ
		                    		     entityStore.string_tuple.add(pattern_string);
		                    		     //Ԫ���ַ�����ʽ��
		                    		     //2017/2/12�޸Ŀ�ʼ
		   	                             if(!entityStore.entity_pairs.contains(NewEntity)){
		                    		        entityStore.entity_pairs.add(NewEntity);//���µ�ʵ�屣�浽������
		                    		        entityStore.temp_entity_pairs.add(NewEntity);//����������ʵ�屣�浽��ʱ������
		   	                             }
		   	                             //System.out.println("2");
		                    		     //2017/2/12�޸Ľ���
		                    		 }
	                             }
						    }
						   
							FirstEntityLocateStart = -1;
							FirstEntityLocateEnd = -1;
							    
							SecondEntityLocateStart = -1;
							SecondEntityLocateEnd = -1;
					   } 
					  
					   
				   }
			   }
		   }
		   
		   //��������
//		   System.out.println("extract entity finish");
//		   it = entityStore.temp_entity.entrySet().iterator();
//		   while(it.hasNext()){
//				 Entry entry = (Entry)it.next();
//				
//				 Entity = (String)entry.getKey();
//				 System.out.println(Entity);
//				 
//				 WriteIntoFile(Entity);
//				 
//				 ArrayList<ArrayList> GroupFiveTupleCollection = new ArrayList<>();
//				 GroupFiveTupleCollection = (ArrayList<ArrayList>) entry.getValue();
//				 System.out.println(GroupFiveTupleCollection);
//				 
//				 
//				 
//				 for(int j = 0; j < GroupFiveTupleCollection.size(); j++){
//					ArrayList<WeightTuples> test = GroupFiveTupleCollection.get(j);
//					String test_string = "";
//					System.out.println("=====");
//					test_string +="{";
//					for(int t = 0; t < test.size(); t++){
//						test_string += "<"+test.get(t).word+","+test.get(t).weight+">"+",";
//						System.out.println(test.get(t).word);
//						System.out.println(test.get(t).weight);
//					}
//					test_string +="}";
//					WriteIntoFile(test_string);
//				 }
//			   
//			   //
//		  }
	 }
}
