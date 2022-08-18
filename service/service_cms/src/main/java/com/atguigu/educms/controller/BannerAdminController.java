package com.atguigu.educms.controller;


import com.atguigu.commonutils.R;
import com.atguigu.educms.entity.CrmBanner;
import com.atguigu.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-08-10
 */
@RestController
@RequestMapping("/educms/banneradmin")
//@CrossOrigin
public class BannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;
    //分页查询
    @GetMapping("pageBanner/{page}/{limit}")
    public R PageBanner(@PathVariable("page") long page,@PathVariable("limit") long limit){
        Page<CrmBanner> bannerPage = new Page<>(page,limit);
        crmBannerService.page(bannerPage,null);
        return R.ok().data("items",bannerPage.getRecords()).data("total",bannerPage.getTotal());
    }
    // 添加banner
    @PostMapping("addBanner")
    public R AddBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.save(crmBanner);
        return R.ok();
    }
// 根据id查询banner
    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        CrmBanner banner = crmBannerService.getById(id);
        return R.ok().data("item", banner);
    }
// 更新banner
    @PutMapping("update")
    public R updateById(@RequestBody CrmBanner banner) {
        crmBannerService.updateById(banner);
        return R.ok();
    }
//删除banner
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        crmBannerService.removeById(id);
        return R.ok();
    }
}

