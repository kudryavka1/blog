package com.zh.web;

import com.zh.exception.NotFoundException;
import com.zh.pojo.Blog;
import com.zh.service.BlogService;
import com.zh.service.TagService;
import com.zh.service.TypeService;
import com.zh.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendBlogs",blogService.listBlogTop(8));

        return "index";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC)
                                 Pageable pageable, @RequestParam String query, Model model){
        model.addAttribute("page",blogService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";
    }


    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.getAndConvent(id));
        return "blog";
    }

    @GetMapping("/footer/newBlog")
    public String newBlogs(Model model){
        List<Blog> blogs = blogService.listBlogTop(3);
        for (Blog blog : blogs) {
            if (blog.getTitle().length()>20){
                String substring = blog.getTitle().substring(0,14) + "...";
                blog.setTitle(substring);
            }
        }
        model.addAttribute("newBlogs",blogs);
        return "_fragments :: newBlogList";
    }


}
