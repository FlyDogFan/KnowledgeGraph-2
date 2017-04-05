package TupleWeightForm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import ExtractEntity.TempEntityStore;
import FeatureExtract.FeatureStore;

import java.util.Scanner;

import PatternForm.GroupTuple;
import PatternForm.GroupTupleStore;
import PatternForm.MatchVectorSim;
import StaticConstant.AdjustParameter;
import StaticConstant.NonChiSplit;


public class ReadSeedAndSentence {
	 //��������ʵ���ϵ��
	 TempEntityStore SeedList = new TempEntityStore();
	 
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
	 
	  //������������2��ʱ�򱣴�һ���ֿ������
	 public void WriteIntoFile2(String glaucoma,int iterator_flag){
		    //д���ļ�
		    BufferedWriter bw = null;
		    FileWriter fileWriter=null;
			try{
				fileWriter = new FileWriter("D:/YYLSoftware/Program/YYLRelationshipExtraction/IteratorResult/CandidateEntityTuple"+String.valueOf(iterator_flag)+".txt",true);
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
	 
     //��ȡ�ı��е����ӹ�ϵ��
	 public void GetSeed(String SeedFileName){
		  try{
			 //��ȡ���ӶԵ��ڴ�
		     Scanner EntityPairs = new Scanner(new File(SeedFileName),"UTF-8");
		     while (EntityPairs.hasNextLine()){
		    	 String Entity = EntityPairs.nextLine();
		    	 SeedList.entity_pairs.add(Entity);//��ʵ��Ա��浽ͳһ�ľ�̬������
		    	 SeedList.temp_entity_pairs.add(Entity);//��ʱ����ÿ���²�����ʵ���
		     }
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  System.out.println("���ӶԸ���"+SeedList.entity_pairs.size());
		  
		  GetSentence();
	 }
	 
	 
     //��ȡ�ڴ��еľ��ӣ�����ȡ��������������Ԫ��
	 public void GetSentence(){
	   	 String Sentence = "";
         int i,j;
         int iterator_flag = 0;
         //������е�������ȡʵ���ģ��
		 while(true){
			 //��������
			 iterator_flag ++;
			 //2017/2/12�޸ģ�ÿ�ε������汾�ε�����������Ԫ����Ӻ���Ԫ������
			 TuplesStore TheTupleStore = new TuplesStore();
			 
			 //System.out.println("ReadSeedAndSentenceTuples:"+TheTupleStore.Tuples.size());
			 
			 //ÿ�ε���ʱ����ʼ����ȡԪ�����
			 ExtractTuple extract_tuple = new ExtractTuple();
			 //2017/2/12�޸Ľ���
			
			 
			 //������ȡ��ʼ
		   	 for(i = 0; i < SeedList.temp_entity_pairs.size();i++){
		   		 for(j = 0; j < FeatureStore.SentenceVector.size(); j++){
		   			//��ȡ��ʵ��Զ�Ӧ����Ԫ�������;���
		   		    extract_tuple.ExtractTuples(j,SeedList.temp_entity_pairs.get(i).toString(),TheTupleStore);
		   	     }
		   	 }
		   	 
		   	 //System.out.println("ReadSeedAndSentence_temp_entity_pairs:"+ SeedList.temp_entity_pairs.toString());
		   	 
		   	 SeedList.temp_entity_pairs.clear();//��ʹ����ʱʵ��������Ժ��������ȡ�������
		   	 
		   	 //System.out.println("ReadSeedAndSentence_temp_entity_pairs:"+ SeedList.temp_entity_pairs.size());
		   	 
		   	 
		     WaitForGroup(TheTupleStore);
		    
			 System.out.println("�ڼ��ε�����"+iterator_flag);
			
			//��������������2��ʱ���ȱ���һ�Σ����������ô��
            //if(iterator_flag == 2){
            	TempEntityStore entityStore2 = new TempEntityStore();
    			Iterator it2 = entityStore2.temp_entity.entrySet().iterator();
            	while(it2.hasNext()){
					 Entry entry = (Entry)it2.next();
					
					 String Entity = (String)entry.getKey();
					 //System.out.println(Entity);
					 
					 WriteIntoFile2(Entity,iterator_flag);
					 
					 
					 ArrayList<FeatureStore> GroupFiveTupleCollection = new ArrayList<>();
					 GroupFiveTupleCollection = (ArrayList<FeatureStore>) entry.getValue();
					// System.out.println(GroupFiveTupleCollection);
					 
					 for(j = 0; j < GroupFiveTupleCollection.size(); j++){
						FeatureStore test = new FeatureStore();
						test =	GroupFiveTupleCollection.get(j);
						String test_string = "";//��Ԫ�����
						ArrayList<Float> test_vector = new ArrayList<>();//��Ԫ������
						test_string  = test.sentence;
						test_vector = test.vector;
						
						WriteIntoFile2("{" + test_string + "}" + "\n" + test_vector.toString(),iterator_flag);
					 }
			    }
            	System.out.println("������������"+iterator_flag+"���������");
		   // }
            //��������������2��ʱ�򱣴����
			
			//�������������ʵ���û�����ӣ���������� 
            if(SeedList.temp_entity_pairs.size() == 0){
				//��������
			    System.out.println("����������"+iterator_flag);
			    
			    
//			    TempEntityStore entityStore2 = new TempEntityStore();
//    			Iterator it2 = entityStore2.temp_entity.entrySet().iterator();
//            	while(it2.hasNext()){
//					 Entry entry = (Entry)it2.next();
//					
//					 String Entity = (String)entry.getKey();
//					 //System.out.println(Entity);
//					 
//					 WriteIntoFile(Entity);
//					 
//					 
//					 ArrayList<FeatureStore> GroupFiveTupleCollection = new ArrayList<>();
//					 GroupFiveTupleCollection = (ArrayList<FeatureStore>) entry.getValue();
//					// System.out.println(GroupFiveTupleCollection);
//					 
//					 for(j = 0; j < GroupFiveTupleCollection.size(); j++){
//						FeatureStore test = new FeatureStore();
//						test =	GroupFiveTupleCollection.get(j);
//						String test_string = "";//��Ԫ�����
//						ArrayList<Float> test_vector = new ArrayList<>();//��Ԫ������
//						test_string  = test.sentence;
//						test_vector = test.vector;
//						
//						WriteIntoFile("{" + test_string + "}" + "\n" + test_vector.toString());
//					 }
//					 
////					 for(j = 0; j < GroupFiveTupleCollection.size()-1; j++){
////						 for(int yyl = j+1; yyl < GroupFiveTupleCollection.size(); yyl ++){
////							MatchVectorSim YYlmatchVectorSim = new MatchVectorSim();
////							System.out.println(YYlmatchVectorSim.VectorSim(GroupFiveTupleCollection.get(j).vector, GroupFiveTupleCollection.get(yyl).vector));
////							
////							//WriteIntoFile(test_string +"\n"+ test_vector.toString());
////						 }
////					 }
//					 
//			    }
            	System.out.println("�������������������");
				System.out.println("extract entity finish");
				break;
			}
            
//            SeedList.temp_entity_pairs.clear();//�����ʱ�����е�
		}
	 }
	 
	 //׼������Ԫ����������з���
	 public  void WaitForGroup(TuplesStore tuplesStore){
		 int i,j;
		 //ʵ���
		 String Entity = "";
		 Iterator it = tuplesStore.Tuples.entrySet().iterator();
		// TuplesStore get_tuple_weight = new TuplesStore();
		 
		 //System.out.println("ReadSeedAndSentenceWaitForGroup:" +  tuplesStore.Tuples.size());
		 //System.out.println("ReadSeedAndSentenceWaitForGroup:" +  tuplesStore.Tuples.toString());
		 
		 GroupTuple group_tuple = new GroupTuple(); 
		 while(it.hasNext()){
			 Entry entry = (Entry)it.next();
			
			 Entity = (String)entry.getKey();
			 //System.out.println("ReadSeedAndSentenceEntity:"+Entity);
			 
			 ArrayList<FeatureStore> FiveTupleCollection = new ArrayList<>();
			 FiveTupleCollection = (ArrayList<FeatureStore>) entry.getValue();
			 //System.out.println(FiveTupleCollection);
			 //System.out.println("ReadSeedAndSentenceFiveTupleCollection:" +  FiveTupleCollection.toString());
			 
			 //2016/11/29�Ķ�
			 //if(Entity.equals("arthur Conan Doyle,adventure of sherlock holme")){
			 //2016/11/29�Ķ�����
		     group_tuple.GetGroupTuple(Entity,FiveTupleCollection);
			 //2016/11/29�Ķ�
		     //}
			 //2016/11/29�Ķ�����
		 }
	     
		
		 
	 }
		   
}
