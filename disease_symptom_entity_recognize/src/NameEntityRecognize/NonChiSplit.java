package NameEntityRecognize;

class NonChiSplit
{
 // private static final String C_E_SEPERATOR = "��������������������������{}��������!?:;,()<>[]{}\"'\n\r\t ";

  static boolean isCharSeperator(char c)
  {
     return ("..��������������������������{}��������!?:;,()<>[]{}\"\n\r\t".indexOf(c) != -1);
  }
  static boolean isSentence(char c){
	 return(";!?.".indexOf(c) != -1);
  }
}