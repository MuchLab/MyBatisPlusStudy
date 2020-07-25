package com.muchlab.mybatisplus02;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muchlab.mybatisplus02.entity.User;
import com.muchlab.mybatisplus02.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class Mybatisplus02ApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        System.out.println(userMapper);
    }

    @Test
    public void userMapperTest(){
//        selectAll(userMapper);
//        log.info("===================================================");
//        selectById(userMapper);
//        log.info("===================================================");
//        selectBetween(userMapper, 5, 10);
        selectPage(userMapper, 1, 10);

//        updateUserById(userMapper, 1, "陈哲凯");
//        updateBetween(userMapper, 2, 4);
//        updateEqual(userMapper, "name", "小明");

//        insertUser(userMapper, new User("小明", "广州", new Date()));

        deleteUserById(userMapper, 14);
        deleteBatch(userMapper, 10, 11, 12, 13);
        final Map<String, Object> map = new HashMap<>();
        map.put("name", "小红");
        deleteByMap(userMapper, map);
    }

    /**
     * 分页查询
     * @param userMapper
     */
    private static void selectPage(UserMapper userMapper, long current, long pageSize) {
        IPage<User> page = new Page<>(current, pageSize);
        userMapper.selectPage(page, null);
        final long total = page.getTotal();
        log.info("Total:"+total);
        final List<User> records = page.getRecords();
        for (User record : records) {
            log.info("User:"+record);
        }
    }

    private static void selectBetween(UserMapper userMapper, int i, int i1) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("id", i, i1);
        final List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            log.info("User:"+user);
        }
    }

    private static void deleteByMap(UserMapper userMapper, Map<String, Object> map) {
        final int i = userMapper.deleteByMap(map);
        if (i>0){
            log.info("Delete:"+i);
        }
    }

    private static void deleteBatch(UserMapper userMapper, Integer... list) {
        Collection<Serializable> collection = new ArrayList<>();
        Collections.addAll(collection, list);
        final int i = userMapper.deleteBatchIds(collection);
        if (i>0){
            log.info("Delete:"+i);
        }
    }

    private static void selectById(UserMapper userMapper) {
        userMapper.selectById(1);
        final User user = userMapper.selectById(1);
        log.info("User:"+user);
    }

    private static void selectAll(UserMapper userMapper) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("id", "name", "address", "birth");
        final List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            log.info("User:"+user);
        }
    }

    private static void deleteUserById(UserMapper userMapper, Integer id) {
        final int i = userMapper.deleteById(id);
        if (i>0){
            log.info("Delete:"+i);
        }
    }

    private static void insertUser(UserMapper userMapper, User user) {
        final int insert = userMapper.insert(user);
        if (insert>0){
            log.info("Insert:"+insert);
        }
    }

    private static void updateEqual(UserMapper userMapper, String column, String value) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(column, value);
        final int i = userMapper.update(new User("小红", "天津", new Date()), updateWrapper);
        if (i>0){
            log.info("Update:"+i);
        }
    }

    private static void updateBetween(UserMapper userMapper, Integer val1, Integer val2) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.between("id", val1, val2);
        final int i = userMapper.update(new User("小明", "武汉", new Date()), updateWrapper);
        if (i>0){
            log.info("Update:"+i);
        }
    }

    private static void updateUserById(UserMapper userMapper, Integer id, String name) {
        final User user = userMapper.selectById(id);
        user.setName(name);
        final int i = userMapper.updateById(user);
        if (i>0){
            log.info("Update:"+i);
        }
    }

}
