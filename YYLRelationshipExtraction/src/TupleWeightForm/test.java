package TupleWeightForm;

public class test {
	public static void main(String[] arg){
		 String SeedFileName = "E:/�����ļ�/��̳���/YYLRelationshipExtraction/SeedRelation.txt";
		 String SentenceFileName = "E:/�����ļ�/��̳���/YYLRelationshipExtraction/sentence_label.txt";
		 ReadSeedAndSentence get_seed = new ReadSeedAndSentence();
		 get_seed.GetSeed(SeedFileName,SentenceFileName);
		 //���ע��ע��
	 }
}
