import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * @version: 3.2.0
 * @copyright 广州松冬体育信息咨询有限公司
 * @Author: 翟有良
 * @DateTime: 2022-04-22 10:05
 */
public class App {
    public static void main(String[] args) {
        try {
            String dbName = "sports-communication";
            String resource = "mybatis-config.xml";
            // 通过classLoader获取到配置文件
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            // 把配置文件和mapper文件封装到Configuration实体类
            SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            DataDemoMapper mapper = sqlSession.getMapper(DataDemoMapper.class);
            //查询所有表名
            List<TableInfo> tables = mapper.list(dbName);
            List<Datas> collect = tables.stream().map(x -> {
                return Datas.builder()
                        .name(x.getTableName())
                        .dataDemos(mapper.dtas(dbName, x.getTableName()))
                        .tableComment(x.getTableComment())
                        .build();
            }).collect(Collectors.toList());
            File file = new File(dbName+".csv");
            BufferedWriter out =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"GBK"));
            collect.forEach(x -> {
                try {
                    String write = write(x.getDataDemos(), x.getName(), x.getTableComment());
                    out.write(write);
                    out.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String write(List<DataDemo> datas, String tablename,String tableComment) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(tablename).append("\n").append(tableComment).append("\n").append(",,,").append("\n");
        stringBuilder.append("代码").append(",").append("名称").append(",").append("数据类型(MYSQL)").append(",").append("主键").append(",").append("备注").append("\n");
        for (DataDemo dataDemo : datas) {
            stringBuilder.append(dataDemo.getCOLUMN_NAME()).append(",").append(dataDemo.getCOLUMN_COMMENT()).append(",").append(dataDemo.getCOLUMN_TYPE()).append(",").append(dataDemo.getCOLUMN_KEY().equals("PRI")?"主键":" ").append(",").append(" ").append("\n");
        }
        stringBuilder.append(",,,\n");
        return stringBuilder.toString();
    }

}
