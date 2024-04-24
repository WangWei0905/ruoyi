package cn.iocoder.yudao.module.weapp.service.appslist;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.weapp.controller.admin.appslist.vo.*;
import cn.iocoder.yudao.module.weapp.dal.dataobject.appslist.AppsListDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.weapp.dal.mysql.appslist.AppsListMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.weapp.enums.ErrorCodeConstants.*;

/**
 * 小程序清单 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class AppsListServiceImpl implements AppsListService {

    @Resource
    private AppsListMapper appsListMapper;

    @Override
    public Integer createAppsList(AppsListSaveReqVO createReqVO) {
        // 插入
        AppsListDO appsList = BeanUtils.toBean(createReqVO, AppsListDO.class);
        appsListMapper.insert(appsList);
        // 返回
        return appsList.getId();
    }

    @Override
    public void updateAppsList(AppsListSaveReqVO updateReqVO) {
        // 校验存在
        validateAppsListExists(updateReqVO.getId());
        // 更新
        AppsListDO updateObj = BeanUtils.toBean(updateReqVO, AppsListDO.class);
        appsListMapper.updateById(updateObj);
    }

    @Override
    public void deleteAppsList(Integer id) {
        // 校验存在
        validateAppsListExists(id);
        // 删除
        appsListMapper.deleteById(id);
    }

    private void validateAppsListExists(Integer id) {
        if (appsListMapper.selectById(id) == null) {
            throw exception(APPS_LIST_NOT_EXISTS);
        }
    }

    @Override
    public AppsListDO getAppsList(Integer id) {
        return appsListMapper.selectById(id);
    }

    @Override
    public PageResult<AppsListDO> getAppsListPage(AppsListPageReqVO pageReqVO) {
        return appsListMapper.selectPage(pageReqVO);
    }

}