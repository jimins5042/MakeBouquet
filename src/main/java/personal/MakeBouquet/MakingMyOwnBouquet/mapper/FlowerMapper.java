package personal.MakeBouquet.MakingMyOwnBouquet.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FlowerMapper {

    @Select("select package_imageLink from bouquet_package_select")
    List<String> selectAll();
}
