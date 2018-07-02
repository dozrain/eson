package com.rain.utils.common;

/**
 * Created by Administrator on 2018-6-11 0011.
 */
public class LevenshteinDistance {
        /*输入两个字符串，返回这两个字符串的编辑距离*/
    public static int getDistance(String source, String target){
            int distance=-1;
            /*输入参数合法性检查*/
            if(null==source||null==target||source.isEmpty()||target.isEmpty()){
                return distance;
            }
            /*两个字符串相等，编辑距离为0*/
            if (source.equals(target)) {
                return 0;
            }
            System.out.println("第一个字符串："+source);
            System.out.println("第二个字符串："+target);
            int sourceLength = source.length();
            int targetlength = target.length();
            int length = Math.max(sourceLength,targetlength);
            /*申请一个二维数组，存储转移矩阵*/
            int array[][]=new int[length+1][length+1];
            /*边界条件初始化*/
            for(int i=0;i<=length;i++){
                array[i][0]=i;
            }
            /*边界条件初始化*/
            for(int j=0;j<=length;j++){
                array[0][j]=j;
            }
            /*状态转移方程*/
            for(int i=1;i<=sourceLength;i++){
                for(int j=1;j<=targetlength;j++){
                    array[i][j]=min(array[i-1][j]+1, array[i][j-1]+1,
                            array[i-1][j-1]+(source.charAt(i-1)==target.charAt(j-1)?0:1));
                }
            }
            /*打印转移表格*/
            System.out.println("状态转移表格：");
            for(int i=0;i<=sourceLength;i++){
                for(int j=0;j<=targetlength;j++){
                    System.out.print( array[i][j]+"    ");
                }
                System.out.println();
            }
            return array[sourceLength][targetlength];
        }
        /*取三个数中的最小值*/
    public static int  min(int a,int b, int c){
            return Math.min(Math.min(a,b),c);
        }

    public static void main(String[] args) {
      int instance = LevenshteinDistance.getDistance("haha","hehe");
      System.out.println("距离:"+instance);
    }
}
