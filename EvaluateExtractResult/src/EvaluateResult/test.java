package EvaluateResult;

import java.util.Random;

public class test {
    public static void main(String[] arg){
    	ExtractResult extractResult = new ExtractResult();
    	String FileName = "E:/�����ļ�/��̳���/YYLRelationshipExtraction/CandidateEntityTuple.txt";
    	
    	int i;
    	Random random = new Random();
    	i = random.nextInt(30)+1;
    	
    	System.out.println("������ɵ����֣�"+i);
    	
    	extractResult.ReadSentence(FileName,i);
    }
}
