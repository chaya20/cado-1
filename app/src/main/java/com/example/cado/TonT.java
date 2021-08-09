package com.example.cado;

public class TonT {
    public static int withChip_top(String colorName){
        if(colorName.equals("노란색")) {
            return R.drawable.green;
        } else {
            return R.drawable.black;
        }
    }

    public static String withName_top(String colorName){
        if(colorName.equals("노란색")) {
            return "초록색";
        } else {
            return "어울리는 색이 없습니다";
        }
    }

    public static int withChip_btm(String colorName){
        if(colorName.equals("노란색")) {
            return R.drawable.red;
        } else {
            return R.drawable.black;
        }
    }

    public static String withName_btm(String colorName){
        if(colorName.equals("노란색")) {
            return "빨간색";
        } else {
            return "어울리는 색이 없습니다";
        }
    }
}
