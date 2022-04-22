import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @version: 3.2.0
 * @copyright 广州松冬体育信息咨询有限公司
 * @Author: 翟有良
 * @DateTime: 2022-04-22 10:03
 */
@Data
public class DataDemo {

    @ExcelProperty("代码")
    private String COLUMN_NAME;
    @ExcelProperty("数据类型")
    private String COLUMN_TYPE;
    @ExcelProperty("是否主键")
    private String COLUMN_KEY;
    @ExcelProperty("说明")
    private String COLUMN_COMMENT;

}
