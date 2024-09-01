package org.gelecekbilimde.scienceplatform.user.service;

import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.model.request.UnfollowRequest;

import java.util.List;

public interface UserFollowService {

	List<User> findAllFollowings(String id);

	List<User> findAllFollowers(String id);

	void followToggle(String id);

	void removeFollower(UnfollowRequest request);

}
