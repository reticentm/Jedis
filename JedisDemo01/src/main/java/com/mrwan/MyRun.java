package com.mrwan;

import java.util.Random;

/**
 * @ClassName MyThread
 * @Description: TODO
 * @Author: ice_wan@msn.cn
 */
public class MyRun implements Runnable {
    private Service sc = null; // 业务层对象

    /**
     * @param id 用户级别
     * @param num 操作次数
     */
    public MyRun(String id, int num) {
        sc = new Service(id, num);
    }

    public void run() {
        while (true) {
            sc.service();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}