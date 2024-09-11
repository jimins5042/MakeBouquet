package personal.MakeBouquet.upload.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import personal.MakeBouquet.upload.Image;
import personal.MakeBouquet.upload.ImageMapper;
import personal.MakeBouquet.upload.service.UploadService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/spring")
public class UploadController {

    private final ImageMapper imageMapper;
    private final UploadService uploadService;

    @GetMapping("/upload")
    public String newFile() {
        return "upload/upload-form";
    }

    @PostMapping("/upload")
    public String saveFile(@RequestParam String itemName,
                           @RequestParam MultipartFile file, HttpServletRequest request,
                           Model model) throws IOException {

        log.info("=== 파일 업로드 ===");
        Image image = uploadService.uploadFile(file);

        model.addAttribute("imageLink", image.getImageLink());

        return "upload/showImage";
    }

    @GetMapping("/imageList")
    public String imageList(Model model) {

        log.info("=== 이미지 리스트 조회 ===");
        List<Image> list = imageMapper.findAll();


        ArrayList<String> link = new ArrayList<>();
        for (Image image : list) {
            link.add(image.getImageLink());
        }
        model.addAttribute("images", link);

        return "flower/SelectFlowerPackage";

    }

    @GetMapping("/imageList/manage")
    public String imageManageList(Model model) {

        log.info("=== 이미지 리스트 관리 ===");
        List<Image> list = imageMapper.findAll();

        model.addAttribute("items", list);

        return "upload/imageList";
    }


    @GetMapping("/imageList/{id}")
    public String item(@PathVariable String id, Model model) {

        log.info("=== 이미지 상세 조회 ===");

        String imageLink = imageMapper.findImageByImageLink(id);

        log.info("itemId={}, imageLink={}", id, imageLink);
        model.addAttribute("imageLink", imageLink);

        return "upload/showImage";
    }

    @GetMapping("/imageList/delete/{id}")
    public String deleteItem(@PathVariable String id) {
        log.info("=== {}번 이미지 삭제 ===", id);
        uploadService.deleteImage(id);

        return "redirect:/spring/imageList";

    }

}
