package com.yucun.data.interactor;

import com.yucun.data.entity.UserEntity;
import com.yucun.data.executor.PostExecutionThread;
import com.yucun.data.executor.ThreadExecutor;
import com.yucun.data.interactor.UseCase;
import com.yucun.data.net.UserApi;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;

public class GetUserList extends UseCase<List<UserEntity>, Void> {

  private final UserApi userApi;

  @Inject
  GetUserList(UserApi userApi, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.userApi = userApi;
  }

  @Override Observable<List<UserEntity>> buildUseCaseObservable(Void unused) {
    return this.userApi.userEntityList();
  }
}
