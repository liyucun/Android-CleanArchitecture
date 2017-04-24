/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.android10.sample.presentation.internal.di.modules;

import android.content.Context;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.fernandocejas.android10.sample.data.executor.JobExecutor;
import com.fernandocejas.android10.sample.data.executor.PostExecutionThread;
import com.fernandocejas.android10.sample.data.executor.ThreadExecutor;
import com.fernandocejas.android10.sample.data.net.UserApi;
import com.fernandocejas.android10.sample.presentation.AndroidApplication;
import com.fernandocejas.android10.sample.presentation.UIThread;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module public class ApplicationModule {
  private final String API_BASE_URL =
      "https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture";
  private final AndroidApplication application;

  public ApplicationModule(AndroidApplication application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides @Singleton PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Provides @Singleton Gson provideGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
    return gsonBuilder.create();
  }

  @Provides @Singleton OkHttpClient provideOkhttpClient() {
    OkHttpClient.Builder client = new OkHttpClient.Builder();
    client.addNetworkInterceptor(new StethoInterceptor());
    return client.build();
  }

  @Provides @Singleton Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
    return new Retrofit.Builder().baseUrl(API_BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build();
  }

  @Provides @Singleton UserApi provideUserApi(Retrofit retrofit) {
    return retrofit.create(UserApi.class);
  }
}
