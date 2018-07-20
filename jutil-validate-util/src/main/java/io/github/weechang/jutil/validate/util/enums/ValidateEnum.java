package io.github.weechang.jutil.validate.util.enums;

/**
 * 验证枚举
 *
 * @author zhangwei
 * date 2018/7/20
 * time 10:33
 */
public enum ValidateEnum {
    // ip
    IP(null),
    // ipv4
    IPV4("(((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))"),
    // ipv6
    IPV6(""),
    // 中国大陆手机号码
    CELL_PHONE_CH_ZN("0?(13|14|15|17|18)[0-9]{9}|\\+86?(13|14|15|17|18)[0-9]{9}"),
    // 中国大陆座机
    LAND_LINE_CH_ZN("0[1-9][0-9]-[0-9]{8}|0[1-9]{2}[0-9]-[0-9]{7}"),
    // 中国大陆身份证号码
    ID_CARD_CH_ZN("\\d{17}[\\d|x]|\\d{15}"),
    // 电子邮箱
    EMAIL("\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}"),
    // 邮编
    ZIP_CODE("\\d{6}"),
    // MAC 地址
    MAC_ADD("^([0-9a-fA-F]{2})(([0-9a-fA-F]{2}){5})$"),
    ;

    private String regex;

    ValidateEnum(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
}
