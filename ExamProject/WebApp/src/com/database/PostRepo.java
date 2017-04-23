package com.database;

import com.beans.PostMessageBean;

public class PostRepo extends ABaseRepo<PostMessageBean> {

	public PostRepo() {
		super(PostMessageBean.class);
	}

}
