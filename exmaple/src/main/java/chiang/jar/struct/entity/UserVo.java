package chiang.jar.struct.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

@Data
@Accessors(chain = true)
public class UserVo {
    private Long id;
    private String username;
    private String password;
    private Integer gender;
    private LocalDate birthday;
    private String createTime;
    private UserDetailVo detailVo;
    private List<UserConfig> config;
    @Data
    public static class UserConfig {
        private String field1;
        private Integer field2;
    }
}
