package personal.MakeBouquet;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import personal.MakeBouquet.MakingMyOwnBouquet.entity.FlowerPackageEntity;

import java.util.List;

@Mapper
public interface FlowerMapper {

    @Select("select package_imageLink from bouquet_package_select")
    List<String> selectAll();
}
