package com.bjc.lcp.app.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.bjc.lcp.app.entity.AppInfoEntity;
import java.util.List;
/**
 * @description 应用表Mapper
 * @author wujun
 * @date 2023-04-24
 */
@Mapper
public interface AppInfoMapper extends BaseMapper<AppInfoEntity> {

    @Select(
    "<script>select t0.* from app_info t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='appNo!=null and appNo!=&apos;&apos; '> and t0.app_no=井{appNo}</when> " +
    "<when test='tenaid!=null and tenaid!=&apos;&apos; '> and t0.tenat_id=井{tenaid}</when> " +
    "<when test='appType!=null and appType!=&apos;&apos; '> and t0.app_type=井{appType}</when> " +
    "<when test='appName!=null and appName!=&apos;&apos; '> and t0.app_name=井{appName}</when> " +
    "<when test='appEngNam!=null and appEngNam!=&apos;&apos; '> and t0.app_eng_nam=井{appEngNam}</when> " +
    "<when test='appDesc!=null and appDesc!=&apos;&apos; '> and t0.app_desc=井{appDesc}</when> " +
    "<when test='dept!=null and dept!=&apos;&apos; '> and t0.dept=井{dept}</when> " +
    "<when test='remark!=null and remark!=&apos;&apos; '> and t0.remark=井{remark}</when> " +
    "<when test='createUser!=null and createUser!=&apos;&apos; '> and t0.create_user=井{createUser}</when> " +
    "<when test='createTime!=null and createTime!=&apos;&apos; '> and t0.create_time=井{createTime}</when> " +
    "<when test='updateUser!=null and updateUser!=&apos;&apos; '> and t0.update_user=井{updateUser}</when> " +
    "<when test='updateTime!=null and updateTime!=&apos;&apos; '> and t0.update_time=井{updateTime}</when> " +
    "<when test='syflg!=null and syflg!=&apos;&apos; '> and t0.sys_flg=井{syflg}</when> " +
    "<when test='deleted!=null and deleted!=&apos;&apos; '> and t0.deleted=井{deleted}</when> " +
    //add here if need page limit
    //" limit ￥{page},￥{limit} " +
    " </script>")
    List<AppInfoEntity> pageAll(AppInfoEntity dto,int page,int limit);

    @Select("<script>select count(1) from app_info t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='appNo!=null and appNo!=&apos;&apos; '> and t0.app_no=井{appNo}</when> " +
    "<when test='tenaid!=null and tenaid!=&apos;&apos; '> and t0.tenat_id=井{tenaid}</when> " +
    "<when test='appType!=null and appType!=&apos;&apos; '> and t0.app_type=井{appType}</when> " +
    "<when test='appName!=null and appName!=&apos;&apos; '> and t0.app_name=井{appName}</when> " +
    "<when test='appEngNam!=null and appEngNam!=&apos;&apos; '> and t0.app_eng_nam=井{appEngNam}</when> " +
    "<when test='appDesc!=null and appDesc!=&apos;&apos; '> and t0.app_desc=井{appDesc}</when> " +
    "<when test='dept!=null and dept!=&apos;&apos; '> and t0.dept=井{dept}</when> " +
    "<when test='remark!=null and remark!=&apos;&apos; '> and t0.remark=井{remark}</when> " +
    "<when test='createUser!=null and createUser!=&apos;&apos; '> and t0.create_user=井{createUser}</when> " +
    "<when test='createTime!=null and createTime!=&apos;&apos; '> and t0.create_time=井{createTime}</when> " +
    "<when test='updateUser!=null and updateUser!=&apos;&apos; '> and t0.update_user=井{updateUser}</when> " +
    "<when test='updateTime!=null and updateTime!=&apos;&apos; '> and t0.update_time=井{updateTime}</when> " +
    "<when test='syflg!=null and syflg!=&apos;&apos; '> and t0.sys_flg=井{syflg}</when> " +
    "<when test='deleted!=null and deleted!=&apos;&apos; '> and t0.deleted=井{deleted}</when> " +
     " </script>")
    int countAll(AppInfoEntity dto);
    
    @Select("SELECT count(1) from app_info")
    int selectCount();

}
