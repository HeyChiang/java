package chiang.jar.struct;

import chiang.jar.struct.entity.User;
import chiang.jar.struct.entity.UserAggregate;
import chiang.jar.struct.entity.UserVo;
import com.alibaba.fastjson2.JSON;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    @Mapping(target = "detailVo", source = "userDetail")
    @Mapping(target = "gender", source = "sex")
    @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "detailVo.location",source = "userDetail.address")
    UserVo do2vo(User user);

    @Mapping(target = "userDetail", source = "detailVo")
    @Mapping(target = "userDetail.address", source = "detailVo.location")
    @Mapping(target = "sex", source = "gender")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    User vo2Do(UserVo userVo);

    /**
     * 两个对象合成一个对象
     */
    @Mapping(target = "id",source = "userVo.id")
    @Mapping(target = "level",source = "user.level")
    UserAggregate pojo2Aggregate(User user,UserVo userVo);

    List<UserVo> do2voList(List<User> userList);

    List<User> vo2List(List<UserVo> userVos);

    default List<UserVo.UserConfig> strConfigToListUserConfig(String config) {
        return JSON.parseArray(config, UserVo.UserConfig.class);
    }

    default String listUserConfigToStrConfig(List<UserVo.UserConfig> list) {
        return JSON.toJSONString(list);
    }
}