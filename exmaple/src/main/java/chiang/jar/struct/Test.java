package chiang.jar.struct;

import chiang.jar.struct.entity.User;
import chiang.jar.struct.entity.UserDetail;
import chiang.jar.struct.entity.UserDetailVo;
import chiang.jar.struct.entity.UserVo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * mapstruct 类映射工具Demo
 * 1. 可以将对象里的对象，映射到不同的对象上去
 * 2. 可以将多个对象映射到同一个对象上去
 * 3.
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("\nUser转VO");
        new Test().do2VoTest();
        System.out.println("\nVO转User");
        new Test().vo2DoTest();
        System.out.println("\nUserList转VoList");
        new Test().userListVo2UserList();
    }

    public void userListVo2UserList(){
        List<User> userList = makeUser();
        List<UserVo> userVos = UserConverter.INSTANCE.do2voList(userList);
        assert userVos != null;
        System.out.println(userVos);
    }

    /**
     * User转UserVo
     * user中config字符串，会自动转UserVo中config的集合对象
     */
    public void do2VoTest() {
        User user = makeUser().get(0);

        UserVo userVo = UserConverter.INSTANCE.do2vo(user);
        // asset
        assert userVo != null;
        assert userVo.getId().equals(user.getId());

        // print
        System.out.println(user);
        System.out.println(userVo);
    }

    private List<User> makeUser() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User()
                    .setId((long) i)
                    .setUsername("zhangsan")
                    .setSex(1)
                    .setPassword("abc123")
                    .setCreateTime(LocalDateTime.now())
                    .setUserDetail(new UserDetail().setAddress("湖南长沙"))
                    .setBirthday(LocalDate.of(1999, 9, 27))
                    .setConfig("[{\"field1\":\"Test Field1\",\"field2\":500}]");
            list.add(user);
        }
        return list;
    }

    /**
     * UserVo转User
     * userVo中List<UserConfig>变量config会自动转User的config字符串
     */
    public void vo2DoTest() {
        UserVo.UserConfig userConfig = new UserVo.UserConfig();
        userConfig.setField1("Test Field1");
        userConfig.setField2(500);

        UserVo userVo = new UserVo()
                .setId(1L)
                .setUsername("zhangsan")
                .setGender(2)
                .setDetailVo(new UserDetailVo().setLocation("长沙湖南"))
                .setCreateTime("2020-01-18 15:32:54")
                .setBirthday(LocalDate.of(1999, 9, 27))
                .setConfig(Collections.singletonList(userConfig));
        User user = UserConverter.INSTANCE.vo2Do(userVo);

        // asset
        assert userVo !=null;
        assert userVo.getId().equals(user.getId());

        // print
        System.out.println(user);
        System.out.println(userVo);
    }


}
