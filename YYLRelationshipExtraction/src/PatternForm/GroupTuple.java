package PatternForm;

import java.awt.datatransfer.FlavorEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import ExtractEntity.TempEntityStore;
import FeatureExtract.FeatureStore;
import StaticConstant.AdjustParameter;
import TupleWeightForm.TuplesStore;


public class GroupTuple {
	   float threhold = 0.77f;//�������ʱ�������ֵ��˵���ŷ��Ͼ��������
       public void GetGroupTuple(String Entity, ArrayList<FeatureStore> FiveTupleCollection){
    	   
    	     // Iterator it = tuples.weight.entrySet().iterator();
    	     int k = 0;//��Ԫ�����������
  			     
		     GroupTupleStore group_tuple = new GroupTupleStore();//����ۺ������Ԫ��
		     
		     //System.out.println("GroupTupleClusterSentenceVector:"+group_tuple.ClusterSentenceVector.size());
		     //System.out.println("GroupTupleTempPattern:"+group_tuple.temp_pattern.size());

		     //������ͬһ��ʵ���ϵ�Ե���Ԫ����з���
		     for(int j = 0; j < FiveTupleCollection.size(); j++){
		    	 //��ȡԪ������
		    	 FeatureStore featureStore = new FeatureStore();
		    	 featureStore = FiveTupleCollection.get(j);
		    	 
		    	 if(group_tuple.ClusterSentenceVector.isEmpty()){//��������������
		         	ArrayList<FeatureStore> SenVecList = new ArrayList<>(); 
		         	SenVecList.add(featureStore);
		         	group_tuple.ClusterSentenceVector.put(Entity+AdjustParameter.RecognizeFlag+String.valueOf(k),SenVecList);
		         }else{
		         	String flag = "";
		         	float Max = -1f;
		         	Iterator iterator = group_tuple.ClusterSentenceVector.entrySet().iterator();
		         	
		         	while(iterator.hasNext()){//����ÿ����
		         		Entry group_entry = (Entry)iterator.next();
		   			    //���������
		   			    String group_k = (String)group_entry.getKey();
		   			    
		   			    //ÿһ������������ӵ���������
		   			    ArrayList<FeatureStore> group_senvec = (ArrayList<FeatureStore>)group_entry.getValue();
		   			    
		   			    //System.out.println("GroupTupleGroup_K:"+group_k+":"+group_senvec.size());
		   			    
		   			    MatchVectorSim match_tuple = new MatchVectorSim();
		   			    float Average = 0.0f;
		   			    for(int i = 0; i < group_senvec.size(); i++){
		   			    	float match_degree = match_tuple.VectorSim(featureStore.vector,group_senvec.get(i).vector);
		   			    	
		   			    	//System.out.println("GroupTuplematch_degree:" + match_degree);
		   			    	
		   			    	if(match_degree < threhold){//��������ľ������������͸�����ĳһ�����������������ƶȵ�����ֵ
		   			    		Average = 0.0f;
		   			    		break;
		   			    	}else{
		   			    		Average += match_degree;
		   			    	}
		   			    }
		   			   // System.out.println("GroupTupleƽ��ֵ:" + Average/(float)group_senvec.size());
		   			    if(Average/(float)group_senvec.size() > threhold){
		   			    	if(Average/(float)group_senvec.size() < Max){
		   			    		;
		   			    	}else{
		   			    		 Max = Average/group_senvec.size();
		  			    		 flag = group_k; 
		   			    	}
		   			    }
		         	}
		         	
		         	if(!"".equals(flag) && !String.valueOf(Max).equals(String.valueOf(-1f))){//��������Ѿ����ڵ���
		         		group_tuple.ClusterSentenceVector.get(flag).add(featureStore);
		    		}else{//���ֵ��µ���
		    		    	k ++;//�����µ���
		    			    ArrayList<FeatureStore> Tuple = new ArrayList<>();
		 	    		    Tuple.add(featureStore);
		 	    		    group_tuple.ClusterSentenceVector.put(Entity+AdjustParameter.RecognizeFlag+String.valueOf(k),Tuple);
		    		}
		         }
		    	 
		    	 
		     }
		
  		   //}
		     
		    
    	   
		     //��������
//  		   System.out.println("group finish");
//  		 Iterator it = group_tuple.weight.entrySet().iterator();
//  		 while(it.hasNext()){
//  			 Entry entry = (Entry)it.next();
//  			
//  			 Entity = (String)entry.getKey();
//  			 System.out.println(Entity);
//  			 
//  			 ArrayList<ArrayList> GroupFiveTupleCollection = new ArrayList<>();
//  			 GroupFiveTupleCollection = (ArrayList<ArrayList>) entry.getValue();
//  			 System.out.println(GroupFiveTupleCollection);
//  			 
//  			 for(int j = 0; j < GroupFiveTupleCollection.size(); j++){
//  				ArrayList<WeightTuples> test = GroupFiveTupleCollection.get(j);
//  				System.out.println("=====");
//  				for(int t = 0; t < test.size(); t++){
//  					System.out.println(test.get(t).word);
//					System.out.println(test.get(t).weight);
//  				}
//  			 }
//  			 
//  		 }
  		 
  		 FormPattern formPattern = new FormPattern();
	     formPattern.GetPattern(group_tuple);
  		   
       }
}
