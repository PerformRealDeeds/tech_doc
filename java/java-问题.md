
[去投资银行面试会遇到的10个Java问题](http://www.importnew.com/29199.html)

## java中双检锁为什么要加上volatile关键字！
http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html
https://blog.csdn.net/null_ruzz/article/details/72530826

## java URL打开连接超时 注意设置 setConnectTimeout setReadTimeout
    URL url = new URL("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=" + ip);
    HttpURLConnection htpcon = (HttpURLConnection) url.openConnection();
    htpcon.setRequestMethod("GET");
    htpcon.setDoOutput(true);
    htpcon.setDoInput(true);
    htpcon.setUseCaches(false);
    htpcon.setConnectTimeout(1000);
    htpcon.setReadTimeout(1000);
    InputStream in = htpcon.getInputStream();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

