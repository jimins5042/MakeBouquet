package personal.MakeBouquet.MakingMyOwnBouquet.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Getter@Setter
public class FlowerEntity {
    private MultipartFile file;
    private String packageName;

    public FlowerEntity() {
    }

    public FlowerEntity(MultipartFile file) {
        this.file = file;
    }

    public FlowerEntity(MultipartFile file, String packageName) {
        this.file = file;
        this.packageName = packageName;
    }
}
