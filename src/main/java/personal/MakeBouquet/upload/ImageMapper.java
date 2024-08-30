package personal.MakeBouquet.upload;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ImageMapper {

    // 이미지 리스트 반환
    @Select("SELECT id, image_link, uuid as imageUuid, image_name, saved_date as imageDate " +
            "FROM image_storage where show_yn = 0")
    List<Image> findAll();

    //이미지 정보 저장
    @Insert("insert into image_storage(image_link, uuid, image_name) values (#{imageLink}, #{imageUuid}, #{imageName})")
    void insertImageInfo(Image image);

    //이미지 링크 반환
    @Select("SELECT image_link from image_storage where id = #{id}")
    String findImageByImageLink(String id);

    //에러 이미지 반환
    @Select("SELECT id, image_link, uuid as imageUuid, image_name, saved_date as imageDate from image_storage where id = 13")
    Image findErrorImageLink();

    //이미지 삭제시 키값 받아옴
    @Select("SELECT uuid as imageUuid from image_storage where id = #{id}")
    String findImageByUuid(String id);

    //이미지 정보 삭제
    @Delete("DELETE FROM image_storage WHERE id = #{id}")
    void deleteImage(String id);



}
