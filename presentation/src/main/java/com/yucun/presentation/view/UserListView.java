package com.yucun.presentation.view;

import com.yucun.data.entity.UserEntity;
import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link UserEntity}.
 */
public interface UserListView extends LoadDataView {

  void renderUserList(Collection<UserEntity> userModelCollection);

  void viewUser(UserEntity userModel);
}
