package chiang.jar.struct.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

@Data
@Accessors(chain = true)
public class UserDetailVo {

    private String location;
}
