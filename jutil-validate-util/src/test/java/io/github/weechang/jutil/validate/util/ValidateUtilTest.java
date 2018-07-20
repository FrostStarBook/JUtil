package io.github.weechang.jutil.validate.util;


import io.github.weechang.jutil.validate.util.enums.ValidateEnum;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author zhangwei
 * date 2018/7/20
 * time 11:20
 */
public class ValidateUtilTest {

    @Test
    public void validateIp() {
        boolean vaildate = ValidateUtil.validate("192.168.0.1", ValidateEnum.IP);
        assertTrue(vaildate);
    }

    @Test
    public void validateEmail() {
        boolean vaildate = ValidateUtil.validate("zhangwei_sc@foxmail.com", ValidateEnum.EMAIL);
        assertTrue(vaildate);
    }

    @Test
    public void validateCellPhone() {
        boolean vaildate = ValidateUtil.validate("+8613888888888", ValidateEnum.CELL_PHONE_CH_ZN);
        assertTrue(vaildate);
    }

    @Test
    public void vaildateLine(){
        boolean vaildate = ValidateUtil.validate("0288-8888888", ValidateEnum.LAND_LINE_CH_ZN);
        assertTrue(vaildate);
    }

    @Test
    public void vaildateIdCard(){
        boolean vaildate = ValidateUtil.validate("510025198802021213", ValidateEnum.ID_CARD_CH_ZN);
        assertTrue(vaildate);
    }

    @Test
    public void vaildateZipCode(){
        boolean vaildate = ValidateUtil.validate("610225", ValidateEnum.ZIP_CODE);
        assertTrue(vaildate);
    }

    @Test
    public void vaildateMacAdd(){
        boolean vaildate = ValidateUtil.validate("00-FF-1B-6A-B9-07", ValidateEnum.MAC_ADD);
        assertTrue(vaildate);
    }

    @Test
    public void validateSelf() {
        boolean vaildate = ValidateUtil.validate("zhangwei_sc@foxmail.com", "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}");
        assertTrue(vaildate);
    }
}