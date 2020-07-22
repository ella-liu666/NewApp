package com.haixiajiemei.app.Api;

import com.haixiajiemei.app.Api.Task.DataTask;

public class DataLoader {
    public static void run(final DataTask task) {
        new Thread(() -> task.action()).start();
    }
}
