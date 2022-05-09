package com.rpg175.linux;

import java.util.HashMap;
import java.util.Map;

public class IntelSegmentDescriptor {
    public static void main(String[] args) {
        String segmentDescriptor = "0xCF9A000000FFFF";
        String segmentDescriptorStr =Long.toBinaryString(0xCF9A000000FFFFL);
        System.out.println(segmentDescriptor);
        System.out.println(segmentDescriptorStr + " 长度："+segmentDescriptorStr.length());
        System.out.println("00000000"+segmentDescriptorStr + " 补0，长度：64");
        char[] chars =("00000000"+segmentDescriptorStr).toCharArray();
        Map<String,int[]> segmentDescriptorItemMap = new HashMap<>();
        int[] BaseIndex1 = {0,8};
        int[] GIndex = {8,9};
        int[] DorBIndex = {9,10};
        int[] LIndex = {11,12};
        int[] avlIndex = {12,13};
        int[] SegLimitIndex = {14,17};
        int[] PIndex = {17,18};
        int[] DPLIndex = {18,19};
        int[] SIndex = {20,21};
        int[] TypeIndex = {21,24};
        int[] BaseIndex2 = {24,32};
        int[] BaseIndex3 = {32,48};
        int[] SegmentLimitIndex3 = {48,64};

        System.out.println("Base:     "+getString(BaseIndex1,chars));
        System.out.println("G:        "+getString(GIndex,chars));
        System.out.println("D/B:      "+getString(DorBIndex,chars));
        System.out.println("L:        "+getString(LIndex,chars));
        System.out.println("AVL:      "+getString(avlIndex,chars));
        System.out.println("SegLimit: "+getString(SegLimitIndex,chars));
        System.out.println("P:        "+getString(PIndex,chars));
        System.out.println("DPL:      "+getString(DPLIndex,chars));
        System.out.println("S:        "+getString(SIndex,chars));
        System.out.println("Type:     "+getString(TypeIndex,chars));
        System.out.println("Base:     "+getString(BaseIndex2,chars));
        System.out.println("Base:     "+getString(BaseIndex3,chars));
        System.out.println("SegLimit: "+getString(SegmentLimitIndex3,chars));
    }

    private static String getString(int[] indexArr,char[] chars) {
        String s = "";
        for (int i = indexArr[0]; i < indexArr[1]; i++) {
            s += chars[i];
        }
        return s;
    }
}
