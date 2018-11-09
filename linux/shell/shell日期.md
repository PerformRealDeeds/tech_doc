## 昨天
    date -d last-day +%Y-%m-%d
    date -d "now -1 day" +%F
## 今天
    date +%F               # 输出 2018-10-01
    date -d now +%F        # 输出 2018-10-01
    date -d now +"%F %T" 或者  date +"%F %T"      # 输出 2018-10-01 01:02:03
## 明天
    date -d "now + 1 day" +%F  # day 或者 days,  %F或+%Y-%m-%d, 单引号或者双引号都行
     




