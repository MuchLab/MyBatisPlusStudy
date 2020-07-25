package com.muchlab.mybatisplus01;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muchlab.mybatisplus01.entity.User;
import com.muchlab.mybatisplus01.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.Serializable;
import java.util.*;


public class TestApp {
    public static Logger logger = Logger.getLogger(TestApp.class);
    public static void main(String[] args) {
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        final UserMapper userMapper = context.getBean(UserMapper.class);

//        selectAll(userMapper);
////        selectById(userMapper);
////        selectBetween(userMapper, 5, 10);
//        selectPage(userMapper);
//
//        updateUserById(userMapper, 1, "陈哲凯");
//        updateBetween(userMapper, 2, 4);
//        updateEqual(userMapper, "name", "小明");
//
////        insertUser(userMapper, new User("小明", "广州", new Date()));
//
////        deleteUserById(userMapper, 6);
////        deleteBatch(userMapper, 8, 9);
////        final Map<String, Object> map = new HashMap<>();
////        map.put("id", 5);
////        deleteByMap(userMapper, map);
    }

    private static void selectPage(UserMapper userMapper) {
        IPage<User> page = new Page<>(1, 5);
        userMapper.selectPage(page, null);
        final long total = page.getTotal();
        logger.info("Total:"+total);
        final List<User> records = page.getRecords();
        for (User record : records) {
            logger.info("User:"+record);
        }
    }

    private static void selectBetween(UserMapper userMapper, int i, int i1) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("id", i, i1);
        final List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            logger.info("User:"+user);
        }
    }

    private static void deleteByMap(UserMapper userMapper, Map<String, Object> map) {
        final int i = userMapper.deleteByMap(map);
        if (i>0){
            logger.info("Delete:"+i);
        }
    }

    private static void deleteBatch(UserMapper userMapper, Integer... list) {
        Collection<Serializable> collection = new ArrayList<>();
        Collections.addAll(collection, list);
        final int i = userMapper.deleteBatchIds(collection);
        if (i>0){
            logger.info("Delete:"+i);
        }
    }

    private static void selectById(UserMapper userMapper) {
        userMapper.selectById(1);
        final User user = userMapper.selectById(1);
        logger.info("User:"+user);
    }

    private static void selectAll(UserMapper userMapper) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("id", "name", "address", "birth");
        final List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            logger.info("User:"+user);
        }
    }

    private static void deleteUserById(UserMapper userMapper, Integer id) {
        final int i = userMapper.deleteById(id);
        if (i>0){
            logger.info("Delete:"+i);
        }
    }

    private static void insertUser(UserMapper userMapper, User user) {
        final int insert = userMapper.insert(user);
        if (insert>0){
            logger.info("Insert:"+insert);
        }
    }

    private static void updateEqual(UserMapper userMapper, String column, String value) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(column, value);
        final int i = userMapper.update(new User("小红", "天津", new Date()), updateWrapper);
        if (i>0){
            logger.info("Update:"+i);
        }
    }

    private static void updateBetween(UserMapper userMapper, Integer val1, Integer val2) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.between("id", val1, val2);
        final int i = userMapper.update(new User("小明", "武汉", new Date()), updateWrapper);
        if (i>0){
            logger.info("Update:"+i);
        }
    }

    private static void updateUserById(UserMapper userMapper, Integer id, String name) {
        final User user = userMapper.selectById(id);
        user.setName(name);
        final int i = userMapper.updateById(user);
        if (i>0){
            logger.info("Update:"+i);
        }
    }
}
