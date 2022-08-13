package com.rpg175.linux;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class IntelSegmentDescriptor {

    public static void main(String[] args) {
        calSegmentDescriptor("kernel 4GB code",0x00cf9a000000ffffL);
        calSegmentDescriptor("user 4GB code",0x00cffa000000ffffL);
    }

    private static void calSegmentDescriptor(String desc,long segmentDescriptor) {
        String segmentDescriptorStr =Long.toBinaryString(segmentDescriptor);
        log(desc+" at : " + Long.toHexString(segmentDescriptor));
        log(segmentDescriptorStr + " 长度："+ segmentDescriptorStr.length());
        String base = "";
        for (int i = 0; i < 64- segmentDescriptorStr.length(); i++) {
            base += "0";
        }
        segmentDescriptorStr = base + segmentDescriptorStr;
        log(segmentDescriptorStr + " 补0，长度："+ segmentDescriptorStr.length());
        char[] chars = segmentDescriptorStr.toCharArray();

        int[] BaseIndex1 = {24,8};
        int[] GIndex = {23,1};
        int[] DorBIndex = {22,1};
        int[] LIndex = {21,1};
        int[] avlIndex = {20,1};
        int[] SegLimitIndex = {16,4};
        int[] PIndex = {15,1};
        int[] DPLIndex = {13,2};
        int[] SIndex = {12,1};
        int[] TypeIndex = {8,4};
        int[] BaseIndex2 = {0,8};
        int[] BaseIndex3 = {0,16};
        int[] SegmentLimitIndex3 = {16,16};

        log("Base:     "+getString(BaseIndex1,chars));
        log("G:        "+getString(GIndex,chars));
        log("D/B:      "+getString(DorBIndex,chars));
        log("L:        "+getString(LIndex,chars));
        log("AVL:      "+getString(avlIndex,chars));
        log("SegLimit: "+getString(SegLimitIndex,chars));
        log("P:        "+getString(PIndex,chars));
        log("DPL:      "+getString(DPLIndex,chars));
        log("S:        "+getString(SIndex,chars));
        log("Type:     "+getString(TypeIndex,chars));
        log("Base:     "+getString(BaseIndex2,chars));
        log("Base:     "+getItemString(BaseIndex3,chars,32));
        log("SegLimit: "+getItemString(SegmentLimitIndex3,chars,32));
    }
    private static String getItemString(int[] indexArr,char[] chars,int startIndex) {
        String s = "";
        int start = startIndex+indexArr[0];

        if (startIndex == 0) {
            start = 32-(indexArr[0]+indexArr[1]);
        }
        for (int i = 0; i < indexArr[1]; i++) {
            s += chars[start + i];
        }
        int end = startIndex+indexArr[0] + s.length()-1;
        if (end > startIndex+indexArr[0]) {
            s = s + "  [" + (startIndex+indexArr[0]) + ":" + end +"]";
        } else {
            s = s + "  [" + (startIndex+indexArr[0]) +"]";
        }

        return s;
    }

    private static String getString(int[] indexArr,char[] chars) {
        return getItemString(indexArr,chars,0);
    }

    private static void log(String log) {
        System.out.println(log);
    }
}
