package com.fernandocejas.android10.sample.data.interactor;

import com.fernandocejas.android10.sample.data.entity.UserEntity;
import com.fernandocejas.android10.sample.data.executor.PostExecutionThread;
import com.fernandocejas.android10.sample.data.executor.ThreadExecutor;
import com.fernandocejas.android10.sample.data.interactor.UseCase;
import com.fernandocejas.android10.sample.data.net.UserApi;
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
