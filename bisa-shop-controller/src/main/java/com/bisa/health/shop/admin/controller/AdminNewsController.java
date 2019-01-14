package com.bisa.health.shop.admin.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.bisa.health.shop.admin.service.IAdminNewsInternationalizationService;
import com.bisa.health.shop.model.NewsInternationalization;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.bisa.health.fastdfs.kit.FastDFSKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bisa.health.common.utils.RandomUtils;
import com.bisa.health.shop.admin.dto.OrderListPageDto;
import com.bisa.health.shop.admin.service.IAdminNewsService;
import com.bisa.health.shop.admin.util.JsonResult;
import com.bisa.health.shop.model.News;


/**
 * 新闻管理
 *
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/admin/news")
@RequiresRoles(value = {"ROLE_ADMIN", "ROLE_CUSTOMER"}, logical = Logical.OR)
public class AdminNewsController {

    @Autowired
    private IAdminNewsService newsService;

    @Autowired
    private IAdminNewsInternationalizationService newsInternationalizationService;

    /**
     * 进去 bisa 新闻列表 页面
     */
    @RequestMapping(value = "/listNews", method = RequestMethod.GET)
    public String listNews() {
        return "admin/admin_news/adminNewsList";
    }

    @RequestMapping(value = "/adminNewsInternationalization",method = RequestMethod.GET)
    public String adminNewsInternationalization(){
        return "admin/admin_news/adminNewsInternationalization";
    }
    /**
     * 进去 bisa 新闻列表 内容 页面
     */
    @RequestMapping(value = "/newsContent", method = RequestMethod.GET)
    public String newsContent() {
        return "admin/admin_news/adminNewsContent";
    }

    /**
     * 进去添加新闻页面
     * @return
     */
    @RequestMapping(value = "addNews",method = RequestMethod.GET)
    public String addNews(){
            return "admin/admin_news/addNews";
    }
    /**
     * 进去 bisa 新闻列表 内容 页面
     */
    @RequestMapping(value = "/addNewsPicture", method = RequestMethod.GET)
    public String addNewsPicture() {
        return "admin/admin_news/addNewsPicture";
    }

    /**
     * 新闻图片上传
     * @param file 接收图片的对象
     * @return
     */
    @RequestMapping(value = "/newsPictureUpload", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult newsPictureUpload(MultipartFile file) {

        JsonResult jsonResult = new JsonResult();
        String filename = UUID.randomUUID().toString() + ".jpg";
        try {
            // 上传图片到服务器返回一个图片的地址
            String url = FastDFSKit.getInstance().uploadPicToFastDFS(file.getBytes(), filename);

            jsonResult.setMsg(url);
            jsonResult.setFlag(true);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setFlag(false);
        }
        return jsonResult;
    }

    /**
     * 加载新闻数据
     * @param id 新闻表的id
     * @return
     */
    @RequestMapping(value = "/selectNewsById", method = RequestMethod.GET)
    @ResponseBody
    public News newsContent(Integer id) {

        if (id == null) {
            return null;
        } else {
            return newsService.getNewsById(id);
        }
    }

    /**
     * 加载国际化新闻数据
     * @param id
     * @param internationalization
     * @return
     */
    @RequestMapping(value = "/selectNewsInternationalizationById", method = RequestMethod.GET)
    @ResponseBody
    public News selectNewsInternationalizationById(Integer id,int internationalization) {
        int new_id=id;
        News news=new News();
        try {
            News news1= newsService.getNewsById(id);
            NewsInternationalization newsInternationalization=newsInternationalizationService.selectNewsInternation(new_id,internationalization);
            if (newsInternationalization!=null){
            news.setAuthor(newsInternationalization.getAuthor());
            news.setNews_title(newsInternationalization.getNews_title());
            news.setNews_subhead(newsInternationalization.getNews_subhead());
            news.setRead_quantity(news1.getRead_quantity());
            news.setImg_url(newsInternationalization.getImg_url());
            news.setNews_content(newsInternationalization.getNews_content());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }
    /**
     * 新增、编辑新闻
     * @param news 新闻表对象
     * @return
     */
    @RequestMapping(value = "/addUpdateNews", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addUpdateNews(@RequestBody  News news) {

        JsonResult jsonResult = new JsonResult();
        NewsInternationalization newsInternationalization=new NewsInternationalization();
        try {
            if (news.getId() == 0) {
                // 新增操作
               int news_id= Integer.parseInt(RandomUtils.randomSixNum());
                news.setNews_id(news_id);
                news.setRelease_time(new Date());
                newsService.addNews(news);
                News news1 =newsService.getNewsByNewsId(news_id);
                newsInternationalization.setNew_id(news1.getId());
                newsInternationalization.setInternationalization(2);
                newsInternationalization.setImg_url(news.getImg_url());
                newsInternationalization.setAuthor(news.getAuthor());
                newsInternationalization.setNews_title(news.getNews_title());
                newsInternationalization.setNews_subhead(news.getNews_subhead());
                newsInternationalization.setNews_content(news.getNews_content());
                newsInternationalizationService.addNewsInternationalization(newsInternationalization);

                jsonResult.setFlag(true);
                jsonResult.setMsg("100");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setFlag(false);
        }
        return jsonResult;
    }
    /**
     * 新增、新闻国际化
     *NewsInternationalization 新闻国际化对象
     * @return
     */
    @RequestMapping(value = "/addNewsInternationalization", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addNewsInternationalization(@RequestBody NewsInternationalization newsInternationalization) {

        JsonResult jsonResult = new JsonResult();
        NewsInternationalization news= newsInternationalizationService.selectNewsInternation(newsInternationalization.getNew_id(),newsInternationalization.getInternationalization());
        try {
            if (news==null){
                newsInternationalizationService.addNewsInternationalization(newsInternationalization);
                jsonResult.setFlag(true);
            }else{
                jsonResult.setFlag(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setFlag(false);
        }
        return jsonResult;
    }

    /**
     * 编辑新闻国际化
     * @param news
     * @return
     */
    @RequestMapping(value = "/updateNewsInternationalization",method =RequestMethod.POST)
    @ResponseBody
    public JsonResult updateNewsInternationalization(News news,int internationalization){
        JsonResult jsonResult=new JsonResult();
        NewsInternationalization newsInternationalization=new NewsInternationalization();
        News n= newsService.getNewsById(news.getId());
        try {
            if(!news.getNews_title().isEmpty()&& !news.getNews_subhead().isEmpty()&&!news.getNews_content().isEmpty()
            &&!news.getAuthor().isEmpty()&&!news.getImg_url().isEmpty()){

                newsInternationalization.setAuthor(news.getAuthor());
                newsInternationalization.setNews_title(news.getNews_title());
                newsInternationalization.setNews_content(news.getNews_content());
                newsInternationalization.setImg_url(news.getImg_url());
                newsInternationalization.setNews_subhead(news.getNews_subhead());
                newsInternationalization.setNew_id(news.getId());
                newsInternationalization.setInternationalization(internationalization);
                news.setRelease_time(n.getRelease_time());
                news.setNews_id(n.getNews_id());
                newsInternationalizationService.updateNewsInternationalization(newsInternationalization);
                newsService.updateNews(news);

                jsonResult.setFlag(true);
            }else{
                jsonResult.setFlag(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setFlag(false);
        }
        return jsonResult;
    }
    /**
     * 查询所有新闻
     *
     * @param page  第几页
     * @param limit 每页有多少数据
     */
    @RequestMapping(value = "/selectNewsList", method = RequestMethod.GET)
    @ResponseBody
    public OrderListPageDto<News> selectOrderList(Integer page,Integer limit, HttpServletRequest request) {

        String incontent = request.getParameter("key[incontent]");
        String searchabout = request.getParameter("key[searchabout]");
        OrderListPageDto<News> listPageDto = newsService.selectAllNews(page, limit, incontent, searchabout);

        return listPageDto;
    }

    /**
     * 删除新闻
     *
     * @param id 新闻表  id
     * @return
     */
    @RequestMapping(value = "/delectNews", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delectNews(Integer id) {

        JsonResult jsonResult = new JsonResult();
        int new_id=id;
        try {
            newsService.deleteNews(id);
            newsInternationalizationService.delNewsInternational(new_id);
            jsonResult.setFlag(true);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setFlag(false);
        }
        return jsonResult;
    }
}
