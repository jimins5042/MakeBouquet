package personal.MakeBouquet.MakingMyOwnBouquet.entity;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class FlowerPackageEntity {


    private int packageId;
    private String packageName;
    private String packageImageLink;

    public FlowerPackageEntity() {
    }

    public FlowerPackageEntity(int packageId, String packageName, String packageImageLink) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.packageImageLink = packageImageLink;
    }
}
