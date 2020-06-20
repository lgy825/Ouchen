package com.ouchen.back.controller;

import com.google.code.kaptcha.Producer;

import com.google.common.collect.Maps;
import com.ouchen.back.service.FileStorageService;
import com.ouchen.back.service.index.IndexService;
import com.ouchen.back.service.project.ProjectService;
import com.ouchen.back.service.sys.SysUserService;
import com.ouchen.core.base.BaseController;
import com.ouchen.core.base.Result;
import com.ouchen.core.constant.PermisionConstants;
import com.ouchen.core.model.project.Project;
import com.ouchen.core.model.sys.SysMenu;
import com.ouchen.core.model.sys.SysUser;
import com.ouchen.core.util.CalculateUtils;
import com.ouchen.core.util.DateUtils;
import com.ouchen.core.util.ShiroUtils;
import com.ouchen.core.util.ValidCodeUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ouchen.core.constant.SessionConstants.SESSION_KEY_ALL_MY_CINEMA;

/**
 * Created by 13544 on 2019/5/19.
 */
@Controller
//@RequestMapping("/index")
public class IndexController extends BaseController {

    private static  final Logger log= LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private Producer captchaProducer;

    @Autowired
    private Producer captchaProducerMath;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private IndexService indexService;

    @Autowired
    private FileStorageService fileStorageService;

//    @Autowired
//    private AdminService adminService;

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/tologin")
    public String toLogin(HttpSession session) {
        log.info("开始访问登录页面");
        return "/login";
    }

//    @RequestMapping("/workbench")
//    public String toWorkBench() {
//        log.info("开始访问登录页面");
//        return "/index/workbench";
//    }

    @RequestMapping(value = "/index/getCodeImg", method = RequestMethod.GET)
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response)
    {
        ServletOutputStream out = null;
        try
        {
            HttpSession session = request.getSession();
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/jpeg");

            String type = request.getParameter("type");
            String capStr = null;
            String code = null;
            BufferedImage bi = null;
            if ("math".equals(type))//验证码为算数 8*9 类型
            {
                String capText = captchaProducerMath.createText();
                capStr = capText.substring(0, capText.lastIndexOf("@"));
                code = capText.substring(capText.lastIndexOf("@") + 1);
                bi = captchaProducerMath.createImage(capStr);
            }
            else if ("char".equals(type))//验证码为 abcd类型
            {
                capStr = code = captchaProducer.createText();
                bi = captchaProducer.createImage(capStr);
            }
            session.setAttribute("validateCode", code);
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            out.flush();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/login")
    @ResponseBody
    public Result<String> login(String username,
                                String passwd,
                                String verifyCode,
                                String flag,
                                HttpSession session,
                                HttpServletResponse response,
                                HttpServletRequest request,
                                Model model) {
        if (!ValidCodeUtil.validate(request, verifyCode)) {
            return createFailedResult("验证码错误");
        }
        // 获取shiro中信息
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, passwd);

        Boolean loginSuccess = false;
        try {
            currentUser.login(token);
            loginSuccess = currentUser.isAuthenticated();
        } catch (Exception e) {
            log.error(username + ",登陆失败", e);
            return createFailedResult(e.getMessage());
        }

        if(loginSuccess) {
            SysUser sysUser = (SysUser)currentUser.getPrincipal();
            if ("1".equals(flag)) {
                Cookie cookie = new Cookie("cookie_user", username + "-" + passwd + "-" + flag);
                cookie.setMaxAge(60 * 60 * 24 * 30); // cookie 保存30天
                response.addCookie(cookie);
            } else {
                Cookie cookie = new Cookie("cookie_user", username + "-" + "" + "-" + flag);
                cookie.setMaxAge(60 * 60 * 24 * 30); // cookie 保存30天
                response.addCookie(cookie);
            }
            Cookie cookie = new Cookie("cookie_user_flychannle", null);
            response.addCookie(cookie);
            SecurityUtils.getSubject().getSession().setTimeout(3 * 60 * 60 * 1000);
            String companyCode = sysUser.getCompanyCode();
            List<Project>  cinemaListResult;
            if(sysUser.getChooseProjectId() == null) {
                return createFailedResult("用户已过期");
            }
            if(PermisionConstants.USER_CINEMA_CHOOSE_WAY_ALL == sysUser.getChooseProjectId()) {
                cinemaListResult = projectService.getProjectListByCompanyCode(companyCode);
            } else {
                cinemaListResult = sysUserService.getProjectByUserId(sysUser.getId());
            }
            if(cinemaListResult.size()==0) {
                // TODO 请求失败
                return createFailedResult("查询用户授权项目失败");
            }
            session.setAttribute(SESSION_KEY_ALL_MY_CINEMA, cinemaListResult);
        } else {
            return createFailedResult("登录失败");
        }

        return createSuccessResult("/main");
    }

    @RequestMapping("/main")
    public String toindex(Model model) {
        List<SysMenu> mResult = sysUserService.getMenusByUserId(ShiroUtils.getUserId());

        model.addAttribute("menus", mResult);
        Map<String, List<SysMenu>> menuChildMap =
                mResult
                        .stream()
                        .filter(
                                el -> PermisionConstants.RESOURCE_TYPE_MENU == el.getMenuType() &&
                                        StringUtils.isNotBlank(el.getParentId())
                        )
                        .collect(Collectors.groupingBy(SysMenu::getParentId));
        model.addAttribute("menuChildMap", menuChildMap);

        return "main";
    }

    @RequestMapping("/isout")
    @ResponseBody
    public Result<String> isOut() {
        return createSuccessResult("ingcore");
    }


    /**
     * 查询密码
     * @param sysUser
     * @return
     */
    @RequestMapping("/selectPassWorld")
    @ResponseBody
    public Result<Boolean> selectPassWorld(SysUser sysUser) {
        sysUser.setId(ShiroUtils.getUserId());
        //Result<Boolean> selectWeek = sysUserService.selectPassWorld(sysUser);
        return null;
    }



    /**
     * 登出后跳转到登陆页
     *
     * @param session
     *            session信息
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser)currentUser.getPrincipal();
        if(sysUser != null) {
            Cookie cookie = new Cookie("cookie_user_flychannle", null);
            response.addCookie(cookie);
            currentUser.logout();
        }

        return "login";
    }

    /**
     * 修改密码
     * @param sysUser
     * @return
     */
    @RequestMapping("/updatePassWord")
    @ResponseBody
    public Result<Boolean> updatePassWord(SysUser sysUser){
        sysUser.setId(ShiroUtils.getUserId());
        return createSuccessResult(sysUserService.updatePassWord(sysUser));
    }


    @RequestMapping("/selectWeakPass")
    @ResponseBody
    public Result<Boolean> selectWeakPass(String password) {
        Boolean selectWeek = sysUserService.selectWeakPass(password);
        return createSuccessResult(selectWeek);
    }

    /**
     * 下载文件
     * @param id appid
     * @param response
     */
    @RequestMapping(value="/download")
    public void download( HttpServletResponse response,String fileName){
        String filepath = "/user/local/app/android/"+fileName;//+fileName;
        File file = new File(filepath);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        byte[] b= new byte[1024];
        int len = 0;
        try {
            inputStream = new FileInputStream(file);
            outputStream = response.getOutputStream();
            response.setContentType("application/force-download");
            String filename = file.getName();
            response.addHeader("Content-Disposition","attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
            response.setContentLength( (int) file.length( ) );

            while((len = inputStream.read(b)) != -1){
                outputStream.write(b, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @RequestMapping("/workbench")
    public ModelAndView toWorkbench() {
        ModelAndView view=new ModelAndView("/index/workbench");

        //订单今天总收益
        Date today=new Date();
        long time=today.getTime()-24*60*60*1000;
        Date yesterday=new Date(time);
        String yeStr= DateUtils.parseToDateStr(yesterday);
        String realTime=DateUtils.parseToDateStr(today);
        String startTime=DateUtils.parseDate(today)+" 00:00:00";
        String yeStartStr=DateUtils.parseDate(yesterday)+" 00:00:00";

        List<Project> projects= ShiroUtils.getAllMyCinemaList();
        List<String> projectIds=projects.stream().map(cinema -> cinema.getId()).collect(Collectors.toList());


        //获取今天订单的总收益
        Map<String,Object> todayResult=indexService.getSellData(startTime,projectIds,realTime);
        //获取昨天数据
        Map<String,Object> yesResult=indexService.getSellData(yeStartStr,projectIds,yeStr);
        view.addObject("todayResult",todayResult);
        view.addObject("yesResult",yesResult);
        return view;
    }

    private Map<String,Object> compareWithYesterday(String today,String yesDay){
        Map<String,Object> result= Maps.newHashMap();
        boolean ismax=true;//今天的数据是否大于昨天的数据
        String dataStr="-";
        if(today.equals("-")||yesDay.equals("-")){
            result.put("isMax",new Boolean(ismax));
            result.put("data",dataStr);
            return result;
        }
        try{
            Double num1=Double.valueOf(today);
            Double num2=Double.valueOf(yesDay);
            Double temp=null;
            if(num2 == 0.0) {
                result.put("isMax",new Boolean(ismax));
                result.put("data",dataStr);
                return result;
            } else {
                if (num1.compareTo(num2) < 0) {
                    ismax = false;
                    temp = (num2 - num1) / num2 * 100;
                } else {
                    temp = (num1 - num2) / num2 * 100;
                }
            }
            dataStr= CalculateUtils.toDecimal(temp,1);
        }catch (Exception e){
            log.debug("数值转化失败，非程序异常，显式处理");
        }finally {
            result.put("isMax",new Boolean(ismax));
            result.put("data",dataStr);
        }
        return result;

    }

    /**
     * 计算num1/num2所占比例，结果保留1位小数，如果结果数值异常，那么返回"-"
     * @param num1
     * @param num2
     * @return
     */
    private String calculateRate(String num1,String num2,int scale){
        String result="-";
        try{
            Double n1=Double.valueOf(num1);
            Double n2=Double.valueOf(num2);
            if(n2 == 0){
                return result;
            }
            result = CalculateUtils.toDecimal(n1 / n2 * 100, scale);
        }catch (Exception e){
            log.info("异常数值计算结果",e);
        }
        return result;
    }


}
