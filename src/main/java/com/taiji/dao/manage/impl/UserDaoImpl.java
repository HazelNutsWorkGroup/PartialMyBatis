package com.taiji.dao.manage.impl;

import com.taiji.dao.manage.UserDao;
import com.taiji.entity.manage.UserEntity;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Sleeb on 2017/5/2.
 */
@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public UserEntity getUser(Long userId) {
        UserDao userDao = sqlSessionTemplate.getMapper(UserDao.class);

        return userDao.getUser(userId);
    }
}
