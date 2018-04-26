package com.rain.service.java.orther;

/**
 * Created by Administrator on 2018\3\28 0028.
 */
public class Test {
    public static void main(String[] args) {
        String s = "Hello! How are you!";
        String s2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz! ";
        char[] arr = s2.toCharArray();
        char[] arr2 = s.toCharArray();
        char[] arr3 = new char[0];
        for(int i=0; i<arr2.length;i++){
            for(int j=0; j<arr.length;j++){
                if(arr2[i]==arr[j]){
                    arr3[i]=arr[j+1];
                }else if (arr2[i]=='z'||arr2[i]=='Z'){
                    arr3[i]=arr3[i-25];
                }else if (arr2[i]==' '||arr2[i]=='!'){
                    arr3[i]=arr[j];
                }
                }
        }
        System.out.println("alivetest:"+String.valueOf(arr3));
    }
}
