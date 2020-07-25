package com.muchlab.mybatisplus03;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muchlab.mybatisplus03.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class Mybatisplus03ApplicationTests2 {
    @Test
    public void userTest(){
        selectAll();
//        selectById(1);
//        selectBetween("id", 5, 10);
//        selectPage(1, 5);
//        ===========================================
//        insert(new User("AxlRose", "America", new Date()));
//        ===========================================
//        updateUserById(14, "陈哲凯");
//        updateBetween("id", 1, 5, "肇庆");
//        updateEqual("name", "李四", "陈九");
//        insertOrUpdate(new User(200, "Kurt Cobain", "广州", new Date()));
//        ===========================================
//        deleteById(14);
//        deleteWrapper();
    }

    private void deleteWrapper() {
        final User user = new User();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Kurt Cobain");
        final boolean delete = user.delete(queryWrapper);
        if (delete){
            log.info("Delete:{}", "Successfully");
        }
    }

    private void deleteById(Integer id) {
        final User user = new User();
        user.setId(id);
        final boolean b = user.deleteById();
        if (b){
            log.info("Delete:{}", "Successfully");
        }
    }

    /**
     * 插入或更新
     * @param user
     */
    private void insertOrUpdate(User user) {
        final boolean b = user.insertOrUpdate();
        if (b){
            log.info("InsertOrUpdate:{}", "Successfully");
        }
    }
    private void updateBetween(String column, Integer val1, Integer val2, String address) {
        final User user = new User();
        user.setAddress(address);
        final UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.between(column, val1, val2);
        final boolean update = user.update(updateWrapper);
        if (update){
            log.info("Update:{}", "Successfully");
        }

    }
    private void updateEqual(String column, String name, String setName) {
        final User user = new User();
        user.setName(setName);
        final UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(column, name);
        final boolean update = user.update(updateWrapper);
        if (update){
            log.info("Update:{}", "Successfully");
        }
    }

    /**
     * 根据id更新
     * @param id
     * @param name
     */
    private void updateUserById(Integer id, String name) {
        final User user = new User();
        user.setId(id);
        user.setName(name);
        final boolean b = user.updateById();
        if (b){
            log.info("Update:{}", "Successfully");
        }
    }

    /**
     * 插入
     * @param user
     */
    private void insert(User user) {
        final boolean result = user.insert();
        if (result){
            log.info("Insert:{}", "Successfully");
        }
    }

    /**
     * 分页查询
     * @param current
     * @param pageSize
     */
    private void selectPage(long current, long pageSize) {
        final User user = new User();

        IPage<User> page = new Page<>(current, pageSize);
        user.selectPage(page, null);
        final List<User> records = page.getRecords();
        for (User record : records) {
            log.info("User:{}", record);
        }
    }

    /**
     * 查询在中间的项
     * @param column
     * @param val1
     * @param val2
     */
    private void selectBetween(String column, Integer val1, Integer val2) {
        final User user = new User();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.between(column, val1, val2);
        final List<User> users = user.selectList(queryWrapper);
        for (User user1 : users) {
            log.info("User:{}", user1);
        }
    }

    /**
     * 根据Id查询
     * @param id
     */
    private void selectById(Integer id) {
        final User user = new User();
        user.setId(id);
        final User user1 = user.selectById();
        log.info("User:{}", user1);
    }

    /**
     * 查询全部
     */
    private void selectAll() {
        final User user = new User();
        final List<User> users = user.selectAll();
        for (User user1 : users) {
            log.info("User:{}", user1);
        }
    }

}
