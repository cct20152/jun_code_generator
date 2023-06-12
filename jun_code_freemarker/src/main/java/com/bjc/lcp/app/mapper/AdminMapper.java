package com.bjc.lcp.app.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.bjc.lcp.app.entity.AdminEntity;
import java.util.List;
/**
 * @description 管理员表Mapper
 * @author wujun
 * @date 2023-06-12
 */
@Mapper
public interface AdminMapper extends BaseMapper<AdminEntity> {

    @Select(
    "<script>select t0.* from t_admin t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='id!=null and id!=&apos;&apos; '> and t0.id=井{id}</when> " +
    "<when test='username!=null and username!=&apos;&apos; '> and t0.username=井{username}</when> " +
    "<when test='password!=null and password!=&apos;&apos; '> and t0.password=井{password}</when> " +
    "<when test='lasloginDate!=null and lasloginDate!=&apos;&apos; '> and t0.last_login_date=井{lasloginDate}</when> " +
    "<when test='createDate!=null and createDate!=&apos;&apos; '> and t0.create_date=井{createDate}</when> " +
    //add here if need page limit
    //" limit ￥{page},￥{limit} " +
    " </script>")
    List<AdminEntity> pageAll(AdminEntity dto,int page,int limit);

    @Select("<script>select count(1) from t_admin t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='id!=null and id!=&apos;&apos; '> and t0.id=井{id}</when> " +
    "<when test='username!=null and username!=&apos;&apos; '> and t0.username=井{username}</when> " +
    "<when test='password!=null and password!=&apos;&apos; '> and t0.password=井{password}</when> " +
    "<when test='lasloginDate!=null and lasloginDate!=&apos;&apos; '> and t0.last_login_date=井{lasloginDate}</when> " +
    "<when test='createDate!=null and createDate!=&apos;&apos; '> and t0.create_date=井{createDate}</when> " +
     " </script>")
    int countAll(AdminEntity dto);
    
    @Select("SELECT count(1) from t_admin ")
    int countAll();

}
