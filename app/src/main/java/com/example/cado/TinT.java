package com.example.cado;

public class TinT {
    public static int withChip_top(String colorName){
        if(colorName.equals("노란색")) {
            return R.drawable.blue;
        } else {
            return R.drawable.black;
        }
    }

    public static String withName_top(String colorName){
        if(colorName.equals("노란색")) {
            return "파란색";
        } else {
            return "어울리는 색이 없습니다";
        }
    }

    public static int withChip_btm(String colorName){
        if(colorName.equals("노란색")) {
            return R.drawable.blue;
        } else {
            return R.drawable.black;
        }
    }

    public static String withName_btm(String colorName){
        if(colorName.equals("노란색")) {
            return "파란색";
        } else {
            return "어울리는 색이 없습니다";
        }
    }
}
