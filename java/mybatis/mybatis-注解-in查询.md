

    @Select("<script>"
              + "SELECT IDFA FROM t_xxx WHERE IDFA IN "
              + "<foreach item='item' index='index' collection='strList' open='(' separator=',' close=')'>"
                  + "#{item}"
              + "</foreach>"
          + "</script>")
    @Results(value = { @Result(column = "user_name", property = "username") })
    public List<String> getXxxList(@Param("strList") List<String> strList);
