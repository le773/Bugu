package cn.bugu.algorithm;

public class LongRepeatStr {
    String reg,left;
    //最长的重复字串,极端情况就比如abcabc,最长重复字串就是abc

    //即为字符串长度的一半,当然这是极端情况,通常都是小于串长一半的
    public  String getMaxLenStr(String str){

        for(int len=str.length()/2;len>0;len--){

            //将字符串分割成若干个最长字符串
            for(int i=0;i<str.length()/len;i++){
                //获取最长子串
                reg=str.substring(0,len+1);
                //获得最长字串剩下的串
                left=str.substring(len+1);

                //如果剩下的串中包含"最长子串"
                if(left.indexOf(reg)!=-1){
                    return reg;
                }

            }

        }
        return null;

    }
    public static void main(String[]args){
        String str="abababac";
        LongRepeatStr s=new LongRepeatStr();
        System.out.println(s.getMaxLenStr(str));
    }
}
