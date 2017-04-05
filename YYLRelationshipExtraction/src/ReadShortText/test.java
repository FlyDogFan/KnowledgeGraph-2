package ReadShortText;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import FeatureExtract.FeatureStore;
import StaticConstant.ConstantParameter;
import StaticConstant.Distant;
import StaticConstant.POSMatrix;
import TupleWeightForm.ReadSeedAndSentence;
import edu.stanford.nlp.maxent.Feature;

public class test {
	//����������
		public static void main(String[] args){
			 
			 
			//���зֶ����ļ� 
	        try { 
	        	String filepathDistant = "D:/YYLSoftware/Program/YYLRelationshipExtraction/Distant_Table.txt";
	        	String filepathPos = "D:/YYLSoftware/Program/YYLRelationshipExtraction/POS_Table.txt";
	        	String filepathWordVec = "D:/YYLSoftware/Program/YYLRelationshipExtraction/SentenceLableVectors.txt";
	        	//String filepathWordVec = "D:/YYLSoftware/Program/YYLRelationshipExtraction/test_vectors.txt";
	        	String filepathSentence = "D:/YYLSoftware/Program/YYLRelationshipExtraction/sentence_label.txt";
	        	//String filepathSentence = "D:/YYLSoftware/Program/YYLRelationshipExtraction/test_sentence.txt";
	        	//String filepathSentence = "D:/YYLSoftware/Program/YYLRelationshipExtraction/test.txt";
				ReadFile readFile = new ReadFile();
	
				readFile.ReadSentence(filepathDistant,1);//ʵ�������
				System.out.println("Distant");
				
				readFile.ReadSentence(filepathPos,0);//���Ա�
				System.out.println("POS");
				
				readFile.ReadSentence(filepathWordVec,2);//��������
				System.out.println("wordvec");
				
				Distant.CaculateMatrix();//����������ʾ���ʵ�����ľ����
				POSMatrix.CaculateMatrix();//����������Ծ�����
				
				readFile.ReadSentence(filepathSentence, 3);//��ȡ����,������������ʵ���ǰ�к�׺��ϵ�����
				System.out.println("FeatureStore.SentenceVector:"+FeatureStore.SentenceVector.size());
				
				ReadSeedAndSentence readSeedAndSentence = new ReadSeedAndSentence();//��ȡ����ʵ���
				String SeedFileName = "D:/YYLSoftware/Program/YYLRelationshipExtraction/SeedRelation.txt";
				//String SeedFileName = "D:/YYLSoftware/Program/YYLRelationshipExtraction/test_seed.txt";
				
                readSeedAndSentence.GetSeed(SeedFileName);
				
				System.out.println("All Finish!");
				
//				for(int i = 0; i < FeatureStore.SentenceVector.size(); i++){
//					System.out.println(FeatureStore.SentenceVector.get(i).sentence);
//					System.out.println(FeatureStore.SentenceVector.get(i).vector);
//				}
				
	        } catch (Exception e) {  
	        	System.out.println("fail");
	        	e.printStackTrace(); 
	        }  
			
		}	
		
}
