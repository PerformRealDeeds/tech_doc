## controller层的常用注解

@RestController
@RequestMapping("/url_path/")
public class TestController{
    @GetMapping
    public ModelAndView welcome(){
         return new ModelAndView("welcome");//返回templates下的 welcome.ftl的文件
    }

    @GetMapping("/download/{filename}")
    public String download(@PathVariable String filename){

    }


}


