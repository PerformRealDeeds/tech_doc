# dubbo-dubbo协议传输文件
1. 定义方法: 
   
        @Override
        public byte[] download(String path) {
            System.out.println(path + "******************************");
            try {
                InputStream is = new FileInputStream(new File(path));
                byte[] byteArr = new byte[is.available()];
                is.read(byteArr);
                return byteArr;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

2. 服务提供者设置超时时间,dubbo默认超时1s,太短
   
        <dubbo:service interface="com.alibaba.dubbo.demo.TransFileService" ref="transFileService" protocol="dubbo"  timeout="60000"/>

3. 调用

            TransFileService transFileService = context.getBean(TransFileService.class); // 获取远程服务代理
            byte[] bArr = transFileService.download("d:/1.mp3");

            // System.out.println("输出流:" + new String(bArr));
            OutputStream os = new FileOutputStream(new File("d:/2.mp3"));
            os.write(bArr);
            os.close();