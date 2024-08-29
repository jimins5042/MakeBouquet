package personal.MakeBouquet.MakingMyOwnBouquet.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import personal.MakeBouquet.FlowerMapper;
import personal.MakeBouquet.Image;
import personal.MakeBouquet.MakingMyOwnBouquet.entity.FlowerEntity;
import personal.MakeBouquet.MakingMyOwnBouquet.entity.FlowerPackageEntity;
import personal.MakeBouquet.service.UploadService;

import java.io.IOException;
import java.util.List;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/flower")
public class DrawFlowerController {

    private final UploadService uploadService;
    private final FlowerMapper flowerMapper;
    FlowerEntity flowerEntity = new FlowerEntity();

    @GetMapping("/draw")
    public String draw() {
        return "flower/FlowerCanvas";
    }


    @PostMapping("/selectPackage")
    public String uploadImage(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        flowerEntity.setFile(file);

        List<String> list = flowerMapper.selectAll();

        model.addAttribute("packages", list);

        return "showImage";
    }

}
