import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @version: 3.2.0
 * @copyright 广州松冬体育信息咨询有限公司
 * @Author: 翟有良
 * @DateTime: 2022-04-22 10:15
 */
@Mapper
public interface DataDemoMapper {
    @Select("SELECT \n" +
            "TABLE_NAME tableName,TABLE_COMMENT tableComment\n" +
            "FROM information_schema.tables\n" +
            "WHERE TABLE_SCHEMA = #{dbName} \n" +
            "ORDER BY TABLE_NAME;")
    List<TableInfo> list(String dbName);

    @Select("SELECT\n" +
            "\tinformation_schema.`COLUMNS`.COLUMN_NAME, \n" +
            "\tinformation_schema.`COLUMNS`.COLUMN_TYPE, \n" +
            "\tinformation_schema.`COLUMNS`.COLUMN_KEY, \n" +
            "\tinformation_schema.`COLUMNS`.COLUMN_COMMENT\n" +
            "FROM\n" +
            "\tinformation_schema.`COLUMNS`\n" +
            "WHERE\n" +
            "\tTABLE_SCHEMA = #{dbName}\n" +
            "\tand `COLUMNS`.TABLE_NAME = #{tableName}")
    List<DataDemo> dtas(@Param("dbName") String dbName, @Param("tableName") String tableName);
}
