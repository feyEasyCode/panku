package com.panku.service.breadcrumbService.impl;

import com.panku.dao.BreadcrumbMapper;
import com.panku.dto.breadCrumb.BreadcrumbDTO;
import com.panku.dto.breadCrumb.BreadcrumbResponseDTO;
import com.panku.dto.breadCrumb.RouterDTO;
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
        List<RouterDTO> breadcrumbls = new ArrayList<>();
        List<Breadcrumb> breadcrumbs = breadcrumbMapper.queryBreadcrumb();
        for (Breadcrumb breadcrumb : breadcrumbs) {
            RouterDTO routerDTO = new RouterDTO();
            routerDTO.setBreadcrumbId(breadcrumb.getBreadcrumbId());
            routerDTO.setRouterName(breadcrumb.getBreadcrumbName());
            routerDTO.setLevel(breadcrumb.getLevel());
            routerDTO.setPath(breadcrumb.getPath());
            routerDTO.setSort(breadcrumb.getSort());
            routerDTO.setParentId(breadcrumb.getPid());
            routerDTO.setComponent(breadcrumb.getUrl());
            routerDTO.setName(breadcrumb.getUrl());
            breadcrumbls.add(routerDTO);
        }
        //根节点
        List<RouterDTO> rootMenu = new ArrayList<RouterDTO>();
        for (RouterDTO nav : breadcrumbls) {
            //父节点是0的，为根节点。
            if (nav.getParentId().equals( "0" )){
                rootMenu.add(nav);
            }
        }
        //根据Menu类的order排序
        Collections.sort(rootMenu, order());
        //为根菜单设置子菜单，getClild是递归调用的
        for (RouterDTO breadcrumb : rootMenu) {
            //获取根节点下的所有子节点 使用getChild方法
            List<RouterDTO> childList = getChild(breadcrumb.getBreadcrumbId(), breadcrumbls);
            //给根节点设置子节点
            breadcrumb.setChildren(childList);
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
    public List<RouterDTO> getChild(String id,List<RouterDTO> allMenu){
        //子菜单
        List<String> breadcrumdName = new ArrayList<>();
        List<RouterDTO> childList = new ArrayList<RouterDTO>();
        for (RouterDTO nav : allMenu) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if (nav.getParentId().equals(id)){
                breadcrumdName.add(nav.getRouterName());
                nav.setBreadcrumbName(breadcrumdName);
                childList.add(nav);
            }
        }
        //递归
        for (RouterDTO nav : childList) {
            nav.setChildren(getChild(nav.getBreadcrumbId(), childList));
        }
        //排序
        Collections.sort(childList,order());
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.size() == 0 ){
            return new ArrayList<RouterDTO>();
        }
        return childList;
    }

    /*
     * 排序,根据order排序
     */
    public Comparator<RouterDTO> order(){
        Comparator<RouterDTO> comparator = new Comparator<RouterDTO>() {
            @Override
            public int compare(RouterDTO o1, RouterDTO o2) {
                if (o1.getSort() != o2.getSort()){
                    return o1.getSort() - o2.getSort();
                }
                return 0 ;
            }
        };
        return comparator;
    }
}
