package com.zh.web;

import com.zh.pojo.Type;
import com.zh.service.BlogService;
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

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;
    @GetMapping("/types/{id}")
    public String typesShow(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable, @PathVariable Long id, Model model){
        List<Type> types = typeService.listTypeTop(100);
        if (id == -1){
            id=types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types",types);
        model.addAttribute("page",blogService.listBlog(pageable,blogQuery));
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}
