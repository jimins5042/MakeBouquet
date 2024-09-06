package personal.MakeBouquet.MakingMyOwnBouquet.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
@Slf4j
@RestController
public class S3ProxyController {

    @GetMapping("/s3-image")
    public Resource getImage(@RequestParam String imageId) throws MalformedURLException {
        // S3 버킷의 이미지 URL
        String imageUrl = imageId;

        UrlResource ur = new UrlResource(imageUrl);
        log.info(String.valueOf(ur));

        // URL을 통해 이미지 리소스를 반환
        return new UrlResource(imageUrl);
    }
}
