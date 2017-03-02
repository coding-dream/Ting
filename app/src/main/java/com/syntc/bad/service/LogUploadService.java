package com.syntc.bad.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LogUploadService extends Service {
    public LogUploadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
