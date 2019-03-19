package com.infobite.niramayahospital.retrofit;

import retrofit2.Response;

public interface WebResponse {

    void onResponseSuccess(Response<?> result);

    void onResponseFailed(String error);
}