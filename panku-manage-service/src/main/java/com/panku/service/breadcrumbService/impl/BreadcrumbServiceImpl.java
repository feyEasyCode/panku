package com.panku.service.breadcrumbService.impl;

import com.panku.dao.BreadcrumbMapper;
import com.panku.dto.breadCrumb.BreadcrumbDTO;
import com.panku.dto.breadCrumb.BreadcrumbResponseDTO;
import com.panku.entity.Breadcrumb;
import com.panku.service.breadcrumbService.BreadcrumbService;
import com.panku.service.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-23
 */
@Service
@Slf4j
public class BreadcrumbServiceImpl implements BreadcrumbService {

    @Resource
    private BreadcrumbMapper breadcrumbMapper;

    @Resource
    private RedisService redisService;


    @Override
    public BreadcrumbResponseDTO queryBreadcrumb() {
        BreadcrumbResponseDTO breadcrumbResponseDTO = new BreadcrumbResponseDTO();
        List<BreadcrumbDTO> breadcrumbls = new ArrayList<>();
        List<Breadcrumb> breadcrumbs = breadcrumbMapper.queryBreadcrumb();
        for (Breadcrumb breadcrumb : breadcrumbs) {
            BreadcrumbDTO breadcrumbDTO = new BreadcrumbDTO();
            breadcrumbDTO.setBreadcrumbId(breadcrumb.getBreadcrumbId());
            breadcrumbDTO.setBreadcrumbName(breadcrumb.getBreadcrumbName());
            breadcrumbDTO.setLevel(breadcrumb.getLevel());
            breadcrumbDTO.setUrl(breadcrumb.getUrl());
            breadcrumbDTO.setSort(breadcrumb.getSort());
            breadcrumbDTO.setParentId(breadcrumb.getPid());
            breadcrumbls.add(breadcrumbDTO);
        }
        //根节点
        List<BreadcrumbDTO> rootMenu = new ArrayList<BreadcrumbDTO>();
        for (BreadcrumbDTO nav : breadcrumbls) {
            //父节点是0的，为根节点。
            if (nav.getParentId().equals( "0" )){
                rootMenu.add(nav);
            }
        }
        //根据Menu类的order排序
        Collections.sort(rootMenu, order());
        //为根菜单设置子菜单，getClild是递归调用的
        for (BreadcrumbDTO breadcrumb : rootMenu) {
            //获取根节点下的所有子节点 使用getChild方法
            List<BreadcrumbDTO> childList = getChild(breadcrumb.getBreadcrumbId(), breadcrumbls);
            //给根节点设置子节点
            breadcrumb.setSubBreadcrumb(childList);
        }
        breadcrumbResponseDTO.setBreadcrumbs(rootMenu);
        return breadcrumbResponseDTO;
    }

    /**
     * 获取子节点
     * @param id 父节点id
     * @param allMenu 所有菜单列表
     * @return 每个根节点下，所有子菜单列表
     */
    public List<BreadcrumbDTO> getChild(String id,List<BreadcrumbDTO> allMenu){
        //子菜单
        List<BreadcrumbDTO> childList = new ArrayList<BreadcrumbDTO>();
        for (BreadcrumbDTO nav : allMenu) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if (nav.getParentId().equals(id)){
                childList.add(nav);
            }
        }
        //递归
        for (BreadcrumbDTO nav : childList) {
            nav.setSubBreadcrumb(getChild(nav.getBreadcrumbId(), allMenu));
        }
        //排序
        Collections.sort(childList,order());
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.size() == 0 ){
            return new ArrayList<BreadcrumbDTO>();
        }
        return childList;
    }

    /*
     * 排序,根据order排序
     */
    public Comparator<BreadcrumbDTO> order(){
        Comparator<BreadcrumbDTO> comparator = new Comparator<BreadcrumbDTO>() {
            @Override
            public int compare(BreadcrumbDTO o1, BreadcrumbDTO o2) {
                if (o1.getSort() != o2.getSort()){
                    return o1.getSort() - o2.getSort();
                }
                return 0 ;
            }
        };
        return comparator;
    }
}
