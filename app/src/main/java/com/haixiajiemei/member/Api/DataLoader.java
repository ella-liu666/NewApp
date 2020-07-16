package com.haixiajiemei.member.Api;

import com.haixiajiemei.member.Api.Task.DataTask;

public class DataLoader {
    public static void run(final DataTask task) {
        new Thread(() -> task.action()).start();
    }
}
