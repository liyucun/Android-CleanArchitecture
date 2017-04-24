package com.fernandocejas.android10.sample.data.net;

import com.fernandocejas.android10.sample.data.entity.UserEntity;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserApi {
  /**
   * Retrieves an {@link Observable} which will emit a List of {@link UserEntity}.
   */
  @GET("users.json") Observable<List<UserEntity>> userEntityList();

  /**
   * Retrieves an {@link Observable} which will emit a {@link UserEntity}.
   *
   * @param userId The user id used to get user data.
   */
  @GET("user_{id}") Observable<UserEntity> userEntityById(@Path("id") final int userId);
}
