package com.cbg.exam.api;

import com.cbg.exam.domain.entity.Article;
import com.cbg.exam.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URL;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ArticleApiController {

    private final ArticleService articleService;

    // 게시물 삭제(1개)
    @DeleteMapping("/article/{articleId}")
    public boolean deleteArticle(@PathVariable(name="articleId") Long articleId){
        return articleService.apiDelArticle(articleId);
    }

    // 게시물 삭제(여러개 선택삭제)
    @DeleteMapping("/articles/{articleIds}")
    public boolean deleteCheckedArticles(@PathVariable(name="articleIds") List<Long> articleIds){
        return articleService.apiDelCheckedArticles(articleIds);
    }

    // upload test
    @PostMapping("/up")
    public boolean uploadFile(@RequestParam("files") MultipartFile[] uploadFile){

        String uploadFolder = "C:\\upload";

        File uploadPath = new File(uploadFolder);

        if( !uploadPath.exists() ){
            uploadPath.mkdirs();
        }

        System.out.println("param>>" + uploadFile);

        for(MultipartFile multipartFile : uploadFile){
            String fileName = multipartFile.getOriginalFilename();
            System.out.println("originName>>" + fileName);

            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
            System.out.println("subStringName>>" + fileName);

            File saveFile = new File(uploadPath, fileName);

            try{
                multipartFile.transferTo(saveFile);
            }catch(Exception e){
                System.out.println("ex>>" + e);
            }
        }

        return true;
    }

}
