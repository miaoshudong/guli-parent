package com.atguigu.educms.service;

import com.atguigu.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-10
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> selectAllBanner();

    void pageBanner(Page<CrmBanner> pageParam, Object o);

    CrmBanner getBannerById(String id);

    @CacheEvict(value = "banner", allEntries=true)
    void saveBanner(CrmBanner banner);

    @CacheEvict(value = "banner", allEntries=true)
    void updateBannerById(CrmBanner banner);

    @CacheEvict(value = "banner", allEntries=true)
    void removeBannerById(String id);
}
