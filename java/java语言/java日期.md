
1：老方法

    Date as = new Date(new Date().getTime()-24*60*60*1000);
    SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
    String time = matter1.format(as);
    System.out.println(time);
  取出数字型的时间  再减去24*60*60*1000，就得到昨天的时间了；
这个有点过时了！
2：

    Calendar   cal   =   Calendar.getInstance();
    cal.add(Calendar.DATE,   -1);
    String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
    System.out.println(yesterday);

这个方法很方便，年月日都可以随心所欲的变！
3，用apache的DateUtils（ 需要 import org.apache.commons.lang.time.DateUtils;）

    Date currentTime = AppUtils.getCurrentDate();
    //获取昨天时间
    Date backupTime=DateUtils.addDays(currentTime, -1);

## 昨天
 LocalData.now.minusDays(1).format(DateTimeFormatter.BASIC_ISO_DATA);