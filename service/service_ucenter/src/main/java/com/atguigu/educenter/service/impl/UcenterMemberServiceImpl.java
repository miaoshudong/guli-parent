package com.atguigu.educenter.service.impl;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.MD5;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.mapper.UcenterMemberMapper;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-11
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {


    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String login(UcenterMember ucenterMember) {
        String mobile = ucenterMember.getMobile();
        String password = ucenterMember.getPassword();

        // 判断手机号和密码是否为空
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new GuliException(20001, "登陆失败");
        }
        // 判断手机号是正确
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        UcenterMember mobileMember = baseMapper.selectOne(queryWrapper);

        if (mobileMember == null) {
            throw new GuliException(20001, "登陆失败");
        }
        if (!MD5.encrypt(password).equals(mobileMember.getPassword())) {
            throw new GuliException(20001, "登陆失败");

        }
        if (mobileMember.getIsDisabled()) {
            throw new GuliException(20001, "登陆失败");
        }

        //登陆成功 生成token字符串
        String token = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());


        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {

        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();

        // 判断是否为空
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname)) {
            throw new GuliException(20001, "注册失败");
        }

//        验证码是否正确
        String redisCode = (String) redisTemplate.opsForValue().get(mobile);
        if (!code.equals("1234")) {
            throw new GuliException(20001, "注册失败");
        }
        // 判断手机号是否重复
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(queryWrapper);

        if (count > 0) {
            throw new GuliException(20001, "注册失败");
        }
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setMobile(mobile);
        ucenterMember.setNickname(nickname);
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setIsDisabled(false);
        ucenterMember.setAvatar("http://rg3aoo7x7.hb-bkt.clouddn.com/1.jpg");
        baseMapper.insert(ucenterMember);
    }
// 根据openid查询
    @Override
    public UcenterMember getByOpenid(String openid) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        UcenterMember ucenterMember = baseMapper.selectOne(queryWrapper);
        return ucenterMember;
    }

    @Override
    public Integer countRegisterByDay(String day) {
        return baseMapper.selectRegisterCount(day);
    }
}
