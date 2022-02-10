package com.flink.streaming.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhuhuipei
 * @Description
 * @date 2020-07-20
 * @time 00:06
 */
public class Test {

    public static void main(String[] args) throws Exception {
//        boolean ok= StrUtil.endWith("/123123/12312312/", "/");
//        System.out.println(ok);
//        boolean ok2= StrUtil.endWith("/123123/12312312", "/");
//        System.out.println(ok2);
//
//
//        boolean ok3= StrUtil.startWith("/123123/12312312", "/");
//        System.out.println(ok3);

//        String input="a_123123_flink-";
//        System.out.println(input.matches("[0-9A-Za-z_]*"));

//        String extJarPath = "/u123123/" + "udf/lib/" + DateUtil.formatDate(new Date()) + "/" + UUID.fastUUID();
//        System.out.println(extJarPath);
//
//
//        //String a = "Sun Nov 13 21:56:41 +0800 2011";
//        String a = "08/Sep/2020:16:21:59 +0800";
//       // SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);//MMM dd hh:mm:ss Z yyyy
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss Z ", Locale.US);//MMM dd hh:mm:ss Z yyyy
//
//        System.out.println(sdf.parse(a));

        String dtime1 = "08/Sep/2020";
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MMM/yyyy", Locale.US);
        Date date = sdf1.parse(dtime1);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sDt = sdf2.format(date);
        System.out.println(sDt);


        String jsonStr = "[{\"i\":\"110000\",\"n\":\"北京市\",\"a\":\"北京\",\"y\":\"BeiJing\",\"b\":\"BJ\",\"z\":\"100000\"},{\"i\":\"120000\",\"n\":\"天津市\",\"a\":\"天津\",\"y\":\"TianJin\",\"b\":\"TJ\",\"z\":\"300000\"},{\"i\":\"130000\",\"n\":\"河北省\",\"a\":\"河北\",\"y\":\"HeBei\",\"b\":\"HB\",\"z\":\"050000\"},{\"i\":\"140000\",\"n\":\"山西省\",\"a\":\"山西\",\"y\":\"ShanXi\",\"b\":\"SX\",\"z\":\"030000\"},{\"i\":\"150000\",\"n\":\"内蒙古自治区\",\"a\":\"内蒙古\",\"y\":\"NeiMengGu\",\"b\":\"NMG\",\"z\":\"010000\"},{\"i\":\"210000\",\"n\":\"辽宁省\",\"a\":\"辽宁\",\"y\":\"LiaoNing\",\"b\":\"LN\",\"z\":\"110000\"},{\"i\":\"220000\",\"n\":\"吉林省\",\"a\":\"吉林\",\"y\":\"JiLin\",\"b\":\"JL\",\"z\":\"130000\"},{\"i\":\"230000\",\"n\":\"黑龙江省\",\"a\":\"黑龙江\",\"y\":\"HeiLongJiang\",\"b\":\"HLJ\",\"z\":\"150000\"},{\"i\":\"310000\",\"n\":\"上海市\",\"a\":\"上海\",\"y\":\"ShangHai\",\"b\":\"SH\",\"z\":\"200000\"},{\"i\":\"320000\",\"n\":\"江苏省\",\"a\":\"江苏\",\"y\":\"JiangSu\",\"b\":\"JS\",\"z\":\"210000\"},{\"i\":\"330000\",\"n\":\"浙江省\",\"a\":\"浙江\",\"y\":\"ZheJiang\",\"b\":\"ZJ\",\"z\":\"310000\"},{\"i\":\"340000\",\"n\":\"安徽省\",\"a\":\"安徽\",\"y\":\"AnHui\",\"b\":\"AH\",\"z\":\"230000\"},{\"i\":\"350000\",\"n\":\"福建省\",\"a\":\"福建\",\"y\":\"FuJian\",\"b\":\"FJ\",\"z\":\"350000\"},{\"i\":\"360000\",\"n\":\"江西省\",\"a\":\"江西\",\"y\":\"JiangXi\",\"b\":\"JX\",\"z\":\"330000\"},{\"i\":\"370000\",\"n\":\"山东省\",\"a\":\"山东\",\"y\":\"ShanDong\",\"b\":\"SD\",\"z\":\"250000\"},{\"i\":\"410000\",\"n\":\"河南省\",\"a\":\"河南\",\"y\":\"HeNan\",\"b\":\"HN\",\"z\":\"450000\"},{\"i\":\"420000\",\"n\":\"湖北省\",\"a\":\"湖北\",\"y\":\"HuBei\",\"b\":\"HB\",\"z\":\"430000\"},{\"i\":\"430000\",\"n\":\"湖南省\",\"a\":\"湖南\",\"y\":\"HuNan\",\"b\":\"HN\",\"z\":\"410000\"},{\"i\":\"440000\",\"n\":\"广东省\",\"a\":\"广东\",\"y\":\"GuangDong\",\"b\":\"GD\",\"z\":\"510000\"},{\"i\":\"450000\",\"n\":\"广西壮族自治区\",\"a\":\"广西\",\"y\":\"GuangXi\",\"b\":\"GX\",\"z\":\"530000\"},{\"i\":\"460000\",\"n\":\"海南省\",\"a\":\"海南\",\"y\":\"HaiNan\",\"b\":\"HN\",\"z\":\"571000\"},{\"i\":\"500000\",\"n\":\"重庆市\",\"a\":\"重庆\",\"y\":\"ZhongQing\",\"b\":\"ZQ\",\"z\":\"400000\"},{\"i\":\"510000\",\"n\":\"四川省\",\"a\":\"四川\",\"y\":\"SiChuan\",\"b\":\"SC\",\"z\":\"610000\"},{\"i\":\"520000\",\"n\":\"贵州省\",\"a\":\"贵州\",\"y\":\"GuiZhou\",\"b\":\"GZ\",\"z\":\"550000\"},{\"i\":\"530000\",\"n\":\"云南省\",\"a\":\"云南\",\"y\":\"YunNan\",\"b\":\"YN\",\"z\":\"650000\"},{\"i\":\"540000\",\"n\":\"西藏自治区\",\"a\":\"西藏\",\"y\":\"XiCang\",\"b\":\"XC\",\"z\":\"850000\"},{\"i\":\"610000\",\"n\":\"陕西省\",\"a\":\"陕西\",\"y\":\"ShanXi\",\"b\":\"SX\",\"z\":\"710000\"},{\"i\":\"620000\",\"n\":\"甘肃省\",\"a\":\"甘肃\",\"y\":\"GanSu\",\"b\":\"GS\",\"z\":\"730000\"},{\"i\":\"630000\",\"n\":\"青海省\",\"a\":\"青海\",\"y\":\"QingHai\",\"b\":\"QH\",\"z\":\"810000\"},{\"i\":\"640000\",\"n\":\"宁夏回族自治区\",\"a\":\"宁夏\",\"y\":\"NingXia\",\"b\":\"NX\",\"z\":\"750000\"},{\"i\":\"650000\",\"n\":\"新疆维吾尔自治区\",\"a\":\"新疆\",\"y\":\"XinJiang\",\"b\":\"XJ\",\"z\":\"830000\"},{\"i\":\"710000\",\"n\":\"台湾省\",\"a\":\"台湾\",\"y\":\"TaiWan\",\"b\":\"TW\",\"z\":\"\"},{\"i\":\"810000\",\"n\":\"香港特别行政区\",\"a\":\"香港\",\"y\":\"HongKong\",\"b\":\"HK\",\"z\":\"\"},{\"i\":\"820000\",\"n\":\"澳门特别行政区\",\"a\":\"澳门\",\"y\":\"Macau\",\"b\":\"MAC\",\"z\":\"\"}]\n";
        JSONArray array = JSONObject.parseArray(jsonStr);
        array.forEach(prov-> {
            JSONObject obj = (JSONObject)prov;
            System.out.print(obj.getString("i"));
            System.out.print(" : ");
            System.out.print(obj.getString("n"));
            System.out.print(" : ");
            System.out.print(obj.getString("a"));
            System.out.print(" : ");
            System.out.print(obj.getString("y"));
            System.out.print(" : ");
            System.out.print(obj.getString("b"));
            System.out.print(" : ");
            System.out.println(obj.getString("z"));
        });
        System.out.println(jsonStr);

        String flinkCheckpointConfig = " -checkpointDir oss://inspiry-flink/kevin/flink-checkpoints/ ";
        Pattern pattern = Pattern.compile("-checkpointDir\\s(\\S*)");
        Matcher matcher = pattern.matcher(flinkCheckpointConfig);
        if(matcher.find()) {
            System.out.println("abc:" + matcher.group(1) + ":abc");

        }

        List<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        List<String> copyList = Arrays.asList(new String[list.size()]);
        System.out.println(copyList.size() + " : " + list.size());
        Collections.copy(copyList, list);
        copyList.stream().forEach(str -> {
            System.out.println(list);
            System.out.println(str);
            list.remove(str);
        });

    }

}
