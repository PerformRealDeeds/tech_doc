## 一个卡主的sql
    insert into a
    select *
    from b      join  c on b.id=c.id
           left join  a on b.id=a.id
    where a.id is null;

b join c改成 b inner join c 就好了

`