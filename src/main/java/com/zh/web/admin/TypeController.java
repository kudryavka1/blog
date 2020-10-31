package com.zh.web.admin;

import com.zh.pojo.Type;
import com.zh.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @GetMapping("/types")
    public String types(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.ASC)
                                    Pageable pageable, Model model){

        Page<Type> types = typeService.listType(pageable);
        model.addAttribute("page",types);
        return "admin/types";
    }
    @GetMapping("/types/input")
    public String input(){
        return "admin/type-input";
    }
    @PostMapping("/types")
    public String post(Type type, RedirectAttributes attributes){
        System.out.println(type);
        Type t = typeService.saveType(type);
        if(t == null){
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/types";
    }
}
