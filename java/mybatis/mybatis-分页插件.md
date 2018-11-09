* [Mybatis-PageHelper](https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md)
* [MyBatis-Spring-Boot](https://github.com/abel533/MyBatis-Spring-Boot)


## 分页插件使用代码

    public List<DO> findListI(DO do){
        if(do.getPageNum()!=null && do.getPageSize()!=null){
            PageHelper.startPage(do);
        }
        return mapper.findList(do);
    }

    public PageInfo<DO> findPage(DO do){
        return new PageInfo<>(findList(do));
    }



