package personal.MakeBouquet.upload;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;

@Slf4j
@Getter
@Setter
public class Image {
    private int id;
    private String imageLink;
    private String imageUuid;
    private String imageName;
    private String imageDate;

    public Image() {

    }

    public Image(String imageLink, String imageUuid, String imageName) {
        this.imageLink = imageLink;
        this.imageUuid = imageUuid;
        this.imageName = imageName;

    }

    public Image(int id, String imageLink, String imageUuid, String imageName, String imageDate) {
        this.id = id;
        this.imageLink = imageLink;
        this.imageUuid = imageUuid;
        this.imageName = imageName;
        this.imageDate = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss").format(imageDate);
    }
}

