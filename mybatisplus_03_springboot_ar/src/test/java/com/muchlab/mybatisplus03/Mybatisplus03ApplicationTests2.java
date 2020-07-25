package com.muchlab.mybatisplus03;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.muchlab.mybatisplus03.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class Mybatisplus03ApplicationTests2 {
    @Test
    public void userTest(){
//        selectAll();
//        selectById(1);
        selectBetween("id", 5, 10);
    }

    private void selectBetween(String column, Integer val1, Integer val2) {
        final User user = new User();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.between(column, val1, val2);
        final List<User> users = user.selectList(queryWrapper);
        for (User user1 : users) {
            log.info("User:{}", user1);
        }
    }

    private void selectById(Integer id) {
        final User user = new User();
        user.setId(id);
        final User user1 = user.selectById();
        log.info("User:{}", user1);
    }

    private void selectAll() {
        final User user = new User();
        final List<User> users = user.selectAll();
        for (User user1 : users) {
            log.info("User:{}", user1);
        }
    }

}
