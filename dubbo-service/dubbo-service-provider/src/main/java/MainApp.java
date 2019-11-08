import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: dubbo-service
 * @Package: PACKAGE_NAME
 * @Description: note
 * @Author: hspcadmin
 * @CreateDate: 2019/11/8 15:54
 * @UpdateUser: hspcadmin
 * @UpdateDate: 2019/11/8 15:54
 * @UpdateRemark: The modified content
 * @Date
 * @Version: 1.0
 * <p>
 * Copyright © 2019 Hundsun Technologies Inc. All Rights Reserved
 **/
public class MainApp {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("provider.xml");
        ioc.start();
        System.in.read();
    }
}
