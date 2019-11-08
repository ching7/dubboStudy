import com.cyn.service.OrderService;
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
 * @CreateDate: 2019/11/8 16:09
 * @UpdateUser: hspcadmin
 * @UpdateDate: 2019/11/8 16:09
 * @UpdateRemark: The modified content
 * @Date
 * @Version: 1.0
 * <p>
 * Copyright © 2019 Hundsun Technologies Inc. All Rights Reserved
 **/
public class MainApp {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("consumer.xml");
        OrderService orderService = applicationContext.getBean(OrderService.class);
        orderService.initOrder("1");
        System.out.println("调用完成");
        System.in.read();
    }
}
