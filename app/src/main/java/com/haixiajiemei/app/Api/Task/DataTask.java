package com.haixiajiemei.app.Api.Task;


import com.haixiajiemei.app.Api.Basic.ApiException;

import com.orhanobut.logger.Logger;

public abstract class DataTask<T> {

    public final void action() {
        try {
            this.onStart();
            String s = this.load();
            this.onResult(parseData(s));
        } catch (ApiException e) {
            Logger.e(e, e.getMessage());
            this.onApiException(e);
        } catch (Exception e) {
            Logger.e(e, e.getMessage());
            this.onException(e);
        } finally {
            this.onFinish();
        }
    }

    protected void onStart() {

    }

    protected void onFinish() {

    }

    protected abstract String load() throws Exception;

    protected abstract T parseData(String s) throws Exception;

    protected void onResult(T t) throws Exception {

    }

    protected void onApiException(ApiException e) {
    }

    protected void onException(Exception e) {
    }
}
