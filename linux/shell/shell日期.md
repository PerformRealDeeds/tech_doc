## 昨天
    date -d last-day +%Y-%m-%d
    date -d "now -1 day" +%F
## 今天
    date +%F
    date -d now +%F

## 明天
    date -d "now + 1 day" +%F  # day 或者 days,  %F或+%Y-%m-%d, 单引号或者双引号都行
     
