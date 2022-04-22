import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @version: 3.2.0
 * @copyright 广州松冬体育信息咨询有限公司
 * @Author: 翟有良
 * @DateTime: 2022-04-22 10:45
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Datas {
    private String name;

    private List<DataDemo> dataDemos;

    private String tableComment;
}
