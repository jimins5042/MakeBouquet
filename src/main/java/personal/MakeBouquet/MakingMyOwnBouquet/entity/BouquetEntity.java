package personal.MakeBouquet.MakingMyOwnBouquet.entity;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class BouquetEntity {

    private int  bouquetId;
    private String bouquetName;
    private String bouquetUuid;
    private String bouquetImageLink;
    private String bouquetDate;

    public BouquetEntity() {
    }

    public BouquetEntity(String bouquetName, String bouquetUuid, String bouquetImageLink) {
        this.bouquetName = bouquetName;
        this.bouquetUuid = bouquetUuid;
        this.bouquetImageLink = bouquetImageLink;
    }

    public BouquetEntity(int bouquetId, String bouquetName, String bouquetUuid, String bouquetImageLink, String bouquetDate) {
        this.bouquetId = bouquetId;
        this.bouquetName = bouquetName;
        this.bouquetUuid = bouquetUuid;
        this.bouquetImageLink = bouquetImageLink;
        this.bouquetDate = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss").format(bouquetDate);
    }
}
