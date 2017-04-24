package com.yucun.presentation.presenter;

import android.support.annotation.NonNull;
import com.yucun.data.entity.UserEntity;
import com.yucun.data.exception.DefaultErrorBundle;
import com.yucun.data.exception.ErrorBundle;
import com.yucun.data.interactor.DefaultObserver;
import com.yucun.data.interactor.GetUserList;
import com.yucun.presentation.exception.ErrorMessageFactory;
import com.yucun.presentation.di.PerActivity;
import com.yucun.presentation.view.UserListView;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity public class UserListPresenter implements Presenter {

  private UserListView viewListView;

  private final GetUserList getUserListUseCase;

  @Inject public UserListPresenter(GetUserList getUserListUserCase) {
    this.getUserListUseCase = getUserListUserCase;
  }

  public void setView(@NonNull UserListView view) {
    this.viewListView = view;
  }

  @Override public void resume() {
  }

  @Override public void pause() {
  }

  @Override public void destroy() {
    this.getUserListUseCase.dispose();
    this.viewListView = null;
  }

  /**
   * Initializes the presenter by start retrieving the user list.
   */
  public void initialize() {
    this.loadUserList();
  }

  /**
   * Loads all users.
   */
  private void loadUserList() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getUserList();
  }

  public void onUserClicked(UserEntity userEntity) {
    this.viewListView.viewUser(userEntity);
  }

  private void showViewLoading() {
    this.viewListView.showLoading();
  }

  private void hideViewLoading() {
    this.viewListView.hideLoading();
  }

  private void showViewRetry() {
    this.viewListView.showRetry();
  }

  private void hideViewRetry() {
    this.viewListView.hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage =
        ErrorMessageFactory.create(this.viewListView.context(), errorBundle.getException());
    this.viewListView.showError(errorMessage);
  }

  private void showUsersCollectionInView(Collection<UserEntity> usersCollection) {
    this.viewListView.renderUserList(usersCollection);
  }

  private void getUserList() {
    this.getUserListUseCase.execute(new UserListObserver(), null);
  }

  private final class UserListObserver extends DefaultObserver<List<UserEntity>> {

    @Override public void onComplete() {
      UserListPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      UserListPresenter.this.hideViewLoading();
      UserListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      UserListPresenter.this.showViewRetry();
    }

    @Override public void onNext(List<UserEntity> users) {
      UserListPresenter.this.showUsersCollectionInView(users);
    }
  }
}
