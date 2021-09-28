package com.zzw.community;

import com.zzw.community.utils.SensitiveFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author 张志伟
 * @version v1.0
 */
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class SensitiveTest {
    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSensitiveFilter(){
        sensitiveFilter.print();
        String text = "我不赌博哈哈哈开票拉";
        String result = sensitiveFilter.filter(text);
        System.out.println(result);
    }

}
