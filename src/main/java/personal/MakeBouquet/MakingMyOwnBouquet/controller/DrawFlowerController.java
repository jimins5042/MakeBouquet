package personal.MakeBouquet.MakingMyOwnBouquet.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import personal.MakeBouquet.MakingMyOwnBouquet.mapper.FlowerMapper;
import personal.MakeBouquet.upload.service.UploadService;

import java.io.IOException;
import java.util.List;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/flower")
public class DrawFlowerController {

    private final UploadService uploadService;
    private final FlowerMapper flowerMapper;
    private String backgroundImageLink;

    @GetMapping("/draw")
    public String draw() {
        return "flower/FlowerCanvas";
    }


    @GetMapping("/selectPackage")
    public String showPackageImage(Model model) throws IOException {

        List<String> images = flowerMapper.selectAll();

        model.addAttribute("images", images);

        return "flower/SelectFlowerPackage";
    }

    @GetMapping("/decorate")
    public String decorateBouquet(Model model) {

        model.addAttribute("backgroundImage", backgroundImageLink);

        return "flower/decorateBouquet";
    }

    @PostMapping("/decorate")
    public String  decorateBouquet1(@RequestParam String image) throws IOException {
        log.info("image={}", image);
        backgroundImageLink = image;

        return "flower/decorateBouquet";
    }

}
