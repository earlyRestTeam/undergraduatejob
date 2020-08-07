/**
 * Copyright(c) Runsdata Technology Co.,Ltd.
 * All Rights Reserved.
 * <p>
 * This software is the confidential and proprietary information of Runsdata
 * Technology Co.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with Runsdata Technology Co.,Ltd.
 * For more information about Runsdata, welcome to http://www.runsdata.com
 * <p>
 * project: springboot
 * <p>
 * Revision History:
 * Date Version Name Description
 * 1/15/2020 1.0 zhuxiaowei Creation File
 */

import java.security.MessageDigest;

/**
 * Description:
 *
 *
 * @author zhuxiaowei
 * @date 1/15/2020 5:22 PM
 */
public class Main {

    private static final String hexDigIts[] = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};

    public static void main(String[] args) {
        String idNumber = "140429199306010823";
        String mobile = "13378052140";
        String bae008 = "sxmsapp";
        String zdxx00 = mobile;
        String timestamp = System.currentTimeMillis() + "";
        System.out.println(timestamp);
        String signature = bae008 + "SXYLZSBKYY" + timestamp + zdxx00 + idNumber;
//        System.out.println(signature);
        signature = MD5Encode(signature);
        System.out.println(signature);
    }

    public static String MD5Encode(String origin){
        String resultString = null;
        try{
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        }catch (Exception e){
        }
        return resultString;
    }

    public static String byteArrayToHexString(byte b[]){
        StringBuffer resultSb = new StringBuffer();
        for(int i = 0; i < b.length; i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    protected static String byteToHexString(byte b){
        int n = b;
        if(n < 0){
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigIts[d1] + hexDigIts[d2];
    }

    public enum CarStatusEnum {

        封存("0"), 正常("1"), 挂失("2"), 冻结("3"), 注销("9"), 临时挂失("A");
        String code;

        CarStatusEnum(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }


        public static void main(String[] args) {
            for (CarStatusEnum carStatusEnum: CarStatusEnum.values()) {
                System.out.println(carStatusEnum.getCode());
            }
        }

        public String getName(String code){
            String name = "";
            switch (code){
                case "0":
                    name = "封存";
                    break;
                case "1":
                    name = "正常";
                    break;
                case "2":
                    name = "挂失";
                    break;
                case "3":
                    name = "冻结";
                    break;
                case "9":
                    name = "注销";
                    break;
                case "A":
                    name = "临时挂失";
                    break;
                default:
                    name = "状态异常";
                    break;
            }
            return name;
        }

    }

}