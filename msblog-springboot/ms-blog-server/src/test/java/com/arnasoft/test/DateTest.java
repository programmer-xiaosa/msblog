package com.arnasoft.test;

import com.arnasoft.vo.HolidayVo;
import com.arnasoft.utils.HolidayUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;


public class DateTest {
    /**
     * 调用免费API查询全年工作日、周末、法定节假日、节假日调休补班数据
     * https://www.cnblogs.com/huanzi-qch/p/14764989.html
     */

    ///////////////////////////////////////////////////////////////////////////
    // 公众号：程序猿小萨
    ///////////////////////////////////////////////////////////////////////////

    // TODO: 2024-03-19
    @Test
    public void testGenerate() {
        try {
            ArrayList<HolidayVo> HolidayVoList = HolidayUtil.getAllHolidayByYear("2023");
            System.err.println("全年完整数据：");
            for (HolidayVo HolidayVo : HolidayVoList) {
                System.err.println(HolidayVo);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }


}
