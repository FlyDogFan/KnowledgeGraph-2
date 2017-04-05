package PatternForm;

import java.util.ArrayList;

public class MatchVectorSim {
    public float VectorSim(ArrayList<Float> tuple1, ArrayList<Float> tuple2){
    	float tuple1_module = 0.0f;//����1��ģ
    	float tuple2_module = 0.0f;//����2��ģ
    	float dot_product = 0.0f;//�����������ڻ�
    	float vector_cos = 0.0f;//����������cosֵ
    	
    	for(int i = 0; i < tuple1.size(); i++){
    		dot_product += tuple1.get(i)*tuple2.get(i);
    		tuple1_module += tuple1.get(i)*tuple1.get(i);
    		tuple2_module += tuple2.get(i)*tuple2.get(i);
    	}
    	
    	double tuple1_double = Double.parseDouble(String.valueOf(tuple1_module));
    	double tuple2_double = Double.parseDouble(String.valueOf(tuple2_module));
    	double module_double = Math.sqrt(tuple1_double*tuple2_double);
    	float module_product = (float)module_double;
    	vector_cos = dot_product/module_product;
    	return vector_cos;
    }
}
